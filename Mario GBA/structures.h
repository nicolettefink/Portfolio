#include "myLib.h"
#include "marioRight.h"
#include "marioLeft.h"
#include "marioJumpRight.h"
#include "marioJumpLeft.h"
#include "questionBlock.h"
#include "hitBlock.h"
#include "goombaLeft.h"
#include "goombaRight.h"
#include "flagpole.h"

typedef struct {
    /*
        mode 0: facing right
        mode 1: facing left
        mode 2: jumping right
        mode 3: jumping left
        mode 4:
        mode 5:
        mode 6:
        mode 7:
        mode 8:
    */
    int mode;
    int direction;
    int jumpingUp;
    int fallingDown;
    int height[4];
    int width[4];
    int row;
    int col;
    int oldRow;
    int oldCol;
    int atBoundary;
    const u16* images[4];
} CHARACTER;

typedef struct {
    /*
        mode 0: unbroken
        mode 1: broken
    */
    int mode;
    int height;
    int width;
    int row;
    int col;
    const u16* image;
} BRICKBLOCK;

typedef struct {
    /*
        mode 0: unbroken
        mode 1: broken
    */
    int mode;
    int height;
    int width;
    int row;
    int col;
    const u16* images[2];
} QUESTIONBLOCK;

typedef struct {
    int mode;
    int height;
    int width;
    int row;
    int col;
    int oldRow;
    int oldCol;
    const u16* images[2];
    int worth;
    int isDead;
} ENEMY;

typedef struct {
    int height;
    int width;
    int row;
    int col;
    const u16* image;
} FLAGPOLE;

extern CHARACTER mario;

extern QUESTIONBLOCK questionBlock1;
extern QUESTIONBLOCK questionBlock2;

extern ENEMY goomba1;
extern ENEMY goomba2;
extern ENEMY goomba3;
extern ENEMY goomba4;

extern FLAGPOLE flag;
