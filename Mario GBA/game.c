#include "game.h"

int GROUND_ROW = 66;

void moveRight(CHARACTER *character,QUESTIONBLOCK blocks[],int numBlocks,ENEMY enemies[],int numEnemies) {
    character->mode = 0;
    character->direction = 0;
    character->oldCol = character->col;
    character->col++;
    checkBoundary(character,blocks,numBlocks,enemies,numEnemies);
}

void moveLeft(CHARACTER *character,QUESTIONBLOCK blocks[],int numBlocks,ENEMY enemies[],int numEnemies) {
    character->mode = 1;
    character->direction = 1;
    character->oldCol = character->col;
    character->col--;
    checkBoundary(character,blocks,numBlocks,enemies,numEnemies);
}

void jump(CHARACTER *character,int jumpHeight,QUESTIONBLOCK blocks[],int numBlocks) {
    character->mode = character->direction ? 3 : 2;
    character->jumpingUp = 1;
    character->fallingDown = 0;
    character->oldRow = character->row;
    character->row -= 2;
    checkHeightBoundary(character,jumpHeight,blocks,numBlocks,'\0','\0');
}

void fall(CHARACTER *character,ENEMY enemies[],int numEnemies) {
    character->mode = character->direction ? 3 : 2;
    character->jumpingUp = 0;
    character->fallingDown = 1;
    character->oldRow = character->row;
    character->row += 2;
    checkHeightBoundary(character,0,'\0','\0',enemies,numEnemies);
}

void checkBoundary(CHARACTER *character,QUESTIONBLOCK blocks[],int numBlocks,ENEMY enemies[],int numEnemies) {
    if (character->col < 50 || character->col + character->width[character->mode] > 190) {
        character->atBoundary = 1;
        character->col = character->oldCol;
    }
    checkBlocksBoundary(character,blocks,numBlocks);
    checkEnemiesBoundary(character,enemies,numEnemies);
}

void checkHeightBoundary(CHARACTER *character,int jumpHeight,QUESTIONBLOCK blocks[],int numBlocks,ENEMY enemies[],int numEnemies) {
    if (character->jumpingUp) {
        if (character->row < 0) {
            character->row = 0;
            character->jumpingUp = 0;
            character->fallingDown = 1;
        }
        if (character->row < jumpHeight) {
            character->row = jumpHeight;
            character->jumpingUp = 0;
            character->fallingDown = 1;
        }
        int index = hitBlockCheck(character,blocks,numBlocks);
        if (index >= 0) {
            if (blocks[index].mode == 0) {
                coins += 1;
            }
            character->jumpingUp = 0;
            character->fallingDown = 1;
            blocks[index].mode = 1;
        }
    }
    if (character->fallingDown) {
        int characterMin = GROUND_ROW + *character->height;
        if (character->row > characterMin) {
            character->row = characterMin;
            character->mode = character->direction ? 1 : 0;
            character->fallingDown = 0;
        }
        int index = hitEnemyCheck(character,enemies,numEnemies);
        if (index >= 0) {
            if (enemies[index].isDead == 0) {
                coins += enemies[index].worth;
                enemies[index].isDead = 1;
                enemies[index].mode = -1;
            }
        }
    }
}

int hitBlockCheck(CHARACTER *character,QUESTIONBLOCK blocks[],int numBlocks) {
    int i; 
    for (i = 0; i < numBlocks; i++) {
        int screenCol = blocks[i].col - levelColLeft;
        if (character->col + character->width[character->mode] > screenCol
            && character->col < screenCol + blocks[i].width
            && character->row < (blocks[i].row + blocks[i].height)) {
            return i;
        }
    }
    return -1;
}

int hitEnemyCheck(CHARACTER *character,ENEMY enemies[],int numEnemies) {
    int i;
    for (i = 0; i < numEnemies; i++) {
        int screenCol = enemies[i].col - levelColLeft;
        if (character->col + character->width[character->mode] > screenCol
            && character->col < screenCol + enemies[i].width
            && character->row + character->height[character->mode] > enemies[i].row) {
            return i;
        }
    }
    return -1;
}

void checkBlocksBoundary(CHARACTER *character, QUESTIONBLOCK blocks[], int numBlocks) {
    int i;
    for (i = 0; i < numBlocks; i++) {
        int screenCol = blocks[i].col - levelColLeft;
        if (character->mode == 0 || character->mode == 2) {
            int i;
            for (i = 0; i < numBlocks; i++) {
                if (character->row <= blocks[i].row + blocks[i].height
                    && character->row + character->height[character->mode] >= blocks[i].row
                    && character->col + character->width[character->mode] > screenCol
                    && character->col + character->width[character->mode] < screenCol + blocks[i].width) {
                    character->col = character->oldCol;
                }
            }
        }
        if (character->mode == 1 || character->mode == 3) {
            int i;
            for (i = 0; i < numBlocks; i++) {
                if (character->row <= blocks[i].row + blocks[i].height
                    && character->row + character->height[character->mode] >= blocks[i].row
                    && character->col < screenCol + blocks[i].width
                    && character->col > screenCol) {
                    character->col = character->oldCol;
                }
            }
        }
    }
}

void checkEnemiesBoundary(CHARACTER *character, ENEMY enemies[], int numEnemies) {
    int i;
    for (i = 0; i < numEnemies; i++) {
        int screenCol = enemies[i].col - levelColLeft;
        if (character->mode == 0 || character->mode == 2) {
            int i;
            for (i = 0; i < numEnemies; i++) {
                if (enemies[i].mode >= 0 && character->row <= enemies[i].row + enemies[i].height
                    && character->row + character->height[character->mode] >= enemies[i].row
                    && character->col + character->width[character->mode] > screenCol
                    && character->col + character->width[character->mode] < screenCol + enemies[i].width) {
                        
                    lives = (lastDeathTimer == 0) ? (lives - 1) : lives;
                    lastDeathTimer = (lastDeathTimer == 0) ? 100 : lastDeathTimer;
                }
            }
        } else if (character->mode == 1 || character->mode == 3) {
            int i;
            for (i = 0; i < numEnemies; i++) {
                if (enemies[i].mode >= 0 && character->row <= enemies[i].row + enemies[i].height
                    && character->row + character->height[character->mode] >= enemies[i].row
                    && character->col < screenCol + enemies[i].width
                    && character->col > screenCol) {

                    lives = (lastDeathTimer == 0) ? (lives - 1) : lives;
                    lastDeathTimer = (lastDeathTimer == 0) ? 100 : lastDeathTimer;
                }
            }
        }
    }
}
