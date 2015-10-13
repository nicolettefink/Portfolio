#include <stdio.h>
#include <stdlib.h>
#include "ground.h"
#include "game.h"
#include "text.h"
#include "heart.h"
#include "coin.h"
#include "gameOver.h"
#include "titleScreen.h"
#include "flagpole.h"

int died = 0;

int main() {
	REG_DISPCTL = MODE3 | BG2_ENABLE;


    while(1) {
        died = 0;
        drawImage3(0,0,TITLESCREEN_WIDTH,TITLESCREEN_HEIGHT,titleScreen);
        if (KEY_DOWN_NOW(BUTTON_START)) {    
            CHARACTER character = mario;
            mario.col = 50;
            QUESTIONBLOCK questionBlocks[] = {questionBlock1,questionBlock2};
            int numBlocks = 2;
            ENEMY enemies[] = {goomba1,goomba2,goomba3,goomba4};
            int numEnemies = 4;
            int jumpHeight = 0;
            levelColLeft = 0;
            levelColRight = 240;
            backgroundCol = 0;
            backgroundCount = 0;
            lives = 3;
            coins = 0;
            flag.col = 500;
            drawMenuIcons();
	        while(1) {
                character.atBoundary = 0;
                if (KEY_DOWN_NOW(BUTTON_SELECT)) { break; }
                if (KEY_DOWN_NOW(BUTTON_LEFT)) {
                    moveLeft(&character,questionBlocks,numBlocks,enemies,numEnemies);
                }
                if (KEY_DOWN_NOW(BUTTON_RIGHT)) {
                    moveRight(&character,questionBlocks,numBlocks,enemies,numEnemies);
                }
                if (KEY_DOWN_NOW(BUTTON_A) && !character.jumpingUp && !character.fallingDown) {
                    character.jumpingUp = 1;
                    character.fallingDown = 0;
                    int *rowPtr;
                    rowPtr = (int *) malloc(sizeof(int));
                    *rowPtr = character.row;
                    jumpHeight = *rowPtr - 2 * character.height[character.mode] + 7;
                }
                if (character.jumpingUp) {
                    jump(&character,jumpHeight,questionBlocks,numBlocks);
                } else if (character.fallingDown) {
                    fall(&character,enemies,numEnemies);
                }
		        WaitForVblank();
                if (character.atBoundary && character.direction == 0) {
                    scrollBackgroundRight();
                    int b;
                    for (b = 0; b < numBlocks; b++) {
                        questionBlocks[b].col -= 1;
                        int screenCol = questionBlocks[b].col - levelColLeft;
                        if (questionBlocks[b].col > levelColLeft && questionBlocks[b].col + questionBlocks[b].width < levelColRight) {
                            drawImage3(questionBlocks[b].row,screenCol,questionBlocks[b].width,
                                questionBlocks[b].height,questionBlocks[b].images[questionBlocks[b].mode]);
                        }
                    }
                    int e;
                    for (e = 0; e < numEnemies; e++) {
                        enemies[e].col -= 1;
                        int screenCol = enemies[e].col - levelColLeft;
                        if (enemies[e].isDead == 0 && enemies[e].col > levelColLeft && enemies[e].col + enemies[e].width < levelColRight) {
                            drawImage3(enemies[e].row,screenCol,enemies[e].width, enemies[e].height,
                                enemies[e].images[enemies[e].mode]);
                        }
                    }
                    flag.col -= 1;
                    int screenCol = flag.col - levelColLeft;
                    if (flag.col > levelColLeft && flag.col + flag.width < levelColRight) {
                        drawImage3(flag.row,screenCol,flag.width,flag.height,flagpole);
                    }
                } else if (character.atBoundary && character.direction == 1) {
                    scrollBackgroundLeft();
                    int b;
                    for (b = 0; b < numBlocks; b++) {
                        questionBlocks[b].col += 1;
                        int screenCol = questionBlocks[b].col - levelColLeft;
                        if (questionBlocks[b].col > levelColLeft && questionBlocks[b].col + questionBlocks[b].width < levelColRight) {
                            drawImage3(questionBlocks[b].row,screenCol,questionBlocks[b].width,
                                questionBlocks[b].height,questionBlocks[b].images[questionBlocks[b].mode]);
                        }
                    }
                    int e;
                    for (e = 0; e < numEnemies; e++) {
                        enemies[e].col += 1;
                        int screenCol = enemies[e].col - levelColLeft;
                        if (enemies[e].isDead == 0 && enemies[e].col > levelColLeft && enemies[e].col + enemies[e].width < levelColRight) {
                            drawImage3(enemies[e].row,screenCol,enemies[e].width, enemies[e].height,
                                enemies[e].images[enemies[e].mode]);
                        }
                    }
                    flag.col += 1;
                    int screenCol = flag.col - levelColLeft;
                    if (flag.col > levelColLeft && flag.col + flag.width < levelColRight) {
                        drawImage3(flag.row,screenCol,flag.width,flag.height,flagpole);
                    }
                } else {
                    drawBackground();
                    int b;
                    for (b = 0; b < numBlocks; b++) {
                        int screenCol = questionBlocks[b].col - levelColLeft;
                        if (questionBlocks[b].col > levelColLeft && questionBlocks[b].col + questionBlocks[b].width < levelColRight) {
                            drawImage3(questionBlocks[b].row,screenCol,questionBlocks[b].width,
                                questionBlocks[b].height,questionBlocks[b].images[questionBlocks[b].mode]);
                        }
                    }
                    int e;
                    for (e = 0; e < numEnemies; e++) {
                        int screenCol = enemies[e].col - levelColLeft;
                        if (enemies[e].isDead == 0 && enemies[e].col > levelColLeft && enemies[e].col + enemies[e].width < levelColRight) {
                            drawImage3(enemies[e].row,screenCol,enemies[e].width, enemies[e].height,
                                enemies[e].images[enemies[e].mode]);
                        }
                    }
                    int screenCol = flag.col - levelColLeft;
                    if (flag.col > levelColLeft && flag.col + flag.width < levelColRight) {
                        drawImage3(flag.row,screenCol,flag.width,flag.height,flagpole);
                    }
                }
                
                drawImage3(character.row,character.col,character.width[character.mode],
                    character.height[character.mode],character.images[character.mode]);
                if (lives < 0) { 
                    died = 1;                    
                    break; 
                }
                if (character.col > flag.col - levelColLeft) {
                    died = 0;
                    break;
                }
	            updateVars();
	        }
            if (died == 1) {
                while (1) {
                    drawImage3(0,0,GAMEOVER_WIDTH,GAMEOVER_HEIGHT,gameOver);
                    if (KEY_DOWN_NOW(BUTTON_START) || KEY_DOWN_NOW(BUTTON_SELECT)) {
                        break;
                    }
                }        
            } else if (died == 0) {
                drawRect(0,0,20,240,BLACK);
                char levelComplete[50] = "Level Complete!\0";
                drawString(5,78,levelComplete,RED);
                while (1) {
                    if (KEY_DOWN_NOW(BUTTON_START) || KEY_DOWN_NOW(BUTTON_SELECT)) {
                        break;
                    }
                }
            }
        }  
    }
}
