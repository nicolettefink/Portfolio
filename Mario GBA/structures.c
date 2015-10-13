#include "structures.h"

CHARACTER mario = {
    .mode = 0,
    .direction = 0,
    .jumpingUp = 0,
    .fallingDown = 0,
    .height = {MARIORIGHT_HEIGHT,MARIOLEFT_HEIGHT,MARIOJUMPRIGHT_HEIGHT,MARIOJUMPLEFT_HEIGHT},
    .width = {MARIORIGHT_WIDTH,MARIOLEFT_WIDTH,MARIOJUMPRIGHT_WIDTH,MARIOJUMPLEFT_WIDTH},
    .row = 98,
    .col = 50,
    .oldRow = 98,
    .oldCol = 50,
    .atBoundary = 1,
    .images = {marioRight,marioLeft,marioJumpRight,marioJumpLeft}
};

QUESTIONBLOCK questionBlock1 = {
	.mode = 0,
    .height = QUESTIONBLOCK_HEIGHT,
    .width = QUESTIONBLOCK_WIDTH,
    .row = 70,
    .col = 140,
    .images = {questionBlock,hitBlock}
};

QUESTIONBLOCK questionBlock2 = {
    .mode = 0,
    .height = QUESTIONBLOCK_HEIGHT,
    .width = QUESTIONBLOCK_WIDTH,
    .row = 70,
    .col = 125,
    .images = {questionBlock,hitBlock}
};

ENEMY goomba1 = {
    .mode = 0,
    .height = GOOMBALEFT_HEIGHT,
    .width = GOOMBALEFT_WIDTH,
    .row = 109,
    .col = 180,
    .oldRow = 109,
    .oldCol = 180,
    .images = {goombaLeft,goombaRight},
    .worth = 2,
    .isDead = 0
};

ENEMY goomba2 = {
    .mode = 0,
    .height = GOOMBALEFT_HEIGHT,
    .width = GOOMBALEFT_WIDTH,
    .row = 109,
    .col = 240,
    .oldRow = 109,
    .oldCol = 240,
    .images = {goombaLeft,goombaRight},
    .worth = 2,
    .isDead = 0
};

ENEMY goomba3 = {
    .mode = 0,
    .height = GOOMBALEFT_HEIGHT,
    .width = GOOMBALEFT_WIDTH,
    .row = 109,
    .col = 310,
    .oldRow = 109,
    .oldCol = 310,
    .images = {goombaLeft,goombaRight},
    .worth = 2,
    .isDead = 0
};

ENEMY goomba4 = {
    .mode = 0,
    .height = GOOMBALEFT_HEIGHT,
    .width = GOOMBALEFT_WIDTH,
    .row = 109,
    .col = 400,
    .oldRow = 109,
    .oldCol = 400,
    .images = {goombaLeft,goombaRight},
    .worth = 2,
    .isDead = 0
};

FLAGPOLE flag = {
    .height = FLAGPOLE_HEIGHT,
    .width = FLAGPOLE_WIDTH,
    .row = 50,
    .col = 500,
    .image = flagpole
};
