# Mario GBA

This is a Mario level written in C which is designed to run on a GBA emulator. The tools to run in on Linux (which is the only platform it has been tested on) are in the gba-tools folder.

## Controls
* Start -> Enter: Starts the level
* Select -> Backspace: Resets the level
* A -> Z: Jump
* Left -> Left Arrow: Move left
* Right -> Right Arrow: Move right

## Installation Instructions
1. Download the appropriate version of cs2110-tools_1.0-1 and cs2110-tools-emulator-1.3.0 (amd64 for 64-bit or i386 for 32-bit)
2. Open terminal and navigate to the folder containing the two files
3. For each one, run *sudo dpkg -i \<complete file name\>* (if this works, skip to step 5)
4. If the output says you need to install other packages, run *sudo apt-get install \<complete package name\>* and then retry installing the emulator tools
5. Start the game by running *make vba* while inside the directory containing the source code
