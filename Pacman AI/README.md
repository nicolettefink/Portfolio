# Pacman AI
For this project, I used inference techniques to control the actions of Pacman to locate and move towards the ghosts. The first method was exact inference using the "sensed" locations of all ghosts to calculate the probability of the ghost being in a particular location. The second utilized a particle filter to approximate the probability inference based on a sampling of the known locations. All code is written in Python and requires Python 2.7.5+ (32-bit) to run.

The files and methods I edited are outlined below:

**bustersAgent.py**

* chooseAction

**inference.py**

ExactInference class

* observe
* elapseTime

ParticleFilter class

* initializeUniformly
* getBeliefDistribution
* observe
* elapseTime


**Note: The graphics are not mine. Only the methods specified above are my original code. **
