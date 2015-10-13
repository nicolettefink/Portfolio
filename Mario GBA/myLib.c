#include "myLib.h"
#include "text.h"
#include "background.h"
#include "ground.h"
#include "text.h"
#include "heart.h"
#include "coin.h"

unsigned short *videoBuffer = (unsigned short *)0x6000000;
int levelColLeft = 0;
int levelColRight = 240;
int backgroundCol = 0;
int backgroundCount = 0;

char buffer[41];
volatile int lastDeathTimer = 0;
int lives = 3;
int coins = 0;


void setPixel(int row, int col, u16 color) {
	videoBuffer[OFFSET(row, col, 240)] = color;
}

void drawRect(int row, int col, int height, int width, u16 color) {
	/*for(int r=0; r<height; r++) {
		for(int c=0; c<width; c++) {
			setPixel(row+r, col+c, color);
		}
	}*/
    int i;
	for (i = 0; i < height; i++) {
		DMA[3].src = &color;
	    DMA[3].dst = videoBuffer + OFFSET(row + i,col,240);
	    DMA[3].cnt = width | DMA_ON | DMA_SOURCE_FIXED;
	}
}

void scrollBackgroundRight() {
	if (backgroundCount == 4) {
        backgroundCol -= 1;
        if (backgroundCol == -240) {
            backgroundCol = 0;
        }
        backgroundCount = 0;
        levelColLeft += 1;
        levelColRight += 1;
    }
    backgroundCount += 1;        
    drawImage3(0,backgroundCol,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,background);
}

void scrollBackgroundLeft() {
	if (backgroundCount == 4) {
        backgroundCol += 1;
        if (backgroundCol == -240) {
            backgroundCol = 0;
        }
        backgroundCount = 0;
        levelColLeft -= 1;
        levelColRight -= 1;
    }
    backgroundCount += 1;        
    drawImage3(0,backgroundCol,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,background);
}

void drawBackground() {
    drawImage3(0,backgroundCol,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,background);
}

void drawImage3(int r,int c,int width,int height,const u16 *image) {
    int i;
	for (i = 0; i < height; i++) {
		DMA[3].src = image + OFFSET(i,0,width);
	    DMA[3].dst = videoBuffer + OFFSET(r + i,c,240);
	    DMA[3].cnt = width | DMA_ON;
	}
}

int boundsCheck(int *var, int bound, int *delta, int size) {
	if(*var < 0) {
		*var = 0;
		*delta = -*delta;
		return 1;
	}
	if(*var > bound-size+1) {
		*var = bound-size+1;
		*delta = -*delta;
	}
	return 0;

}

void updateVars() {
    drawRect(152,12,8,18,BLACK);
    sprintf(buffer,"x0%d",lives);
    drawString(152,12,buffer,WHITE);
    drawRect(152,48,8,12,BLACK);
    sprintf(buffer,"%d",coins);
    drawString(152,48,buffer,WHITE);
    lastDeathTimer = (lastDeathTimer == 0) ? 0 : (lastDeathTimer - 1);
}

void drawMenuIcons() {
    drawImage3(126,0,GROUND_WIDTH,GROUND_HEIGHT,ground);
    drawRect(150,0,10,240,BLACK);
    drawString(152,182,"World 1-1",WHITE);
    drawImage3(152,3,HEART_WIDTH,HEART_HEIGHT,heart);
    drawImage3(152,40,COIN_WIDTH,COIN_HEIGHT,coin);
}

void WaitForVblank() {
	while(SCANLINECOUNTER > 160);
	while(SCANLINECOUNTER < 160);
}


