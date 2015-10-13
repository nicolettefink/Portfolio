#include "structures.h"
#include "text.h"

// Motion Prototypes

void moveRight(CHARACTER *character,QUESTIONBLOCK blocks[],int numBlocks,ENEMY enemies[],int numEnemies);
void moveLeft(CHARACTER *character,QUESTIONBLOCK blocks[],int numBlocks,ENEMY enemies[],int numEnemies);
void jump(CHARACTER *character,int jumpHeight,QUESTIONBLOCK blocks[],int numBlocks);
void fall(CHARACTER *character,ENEMY enemies[],int numEnemies);
void checkBoundary(CHARACTER *character,QUESTIONBLOCK blocks[],int numBlocks,ENEMY enemies[],int numEnemies);
void checkHeightBoundary(CHARACTER *character,int jumpHeight,QUESTIONBLOCK blocks[],int numBlocks,ENEMY enemies[],int numEnemies);
int hitBlockCheck(CHARACTER *character,QUESTIONBLOCK blocks[],int numBlocks);
int hitEnemyCheck(CHARACTER *character,ENEMY enemies[],int numEnemies);
void checkBlocksBoundary(CHARACTER *character, QUESTIONBLOCK blocks[], int numBlocks);
void checkEnemiesBoundary(CHARACTER *character, ENEMY enemies[], int numEnemies);
