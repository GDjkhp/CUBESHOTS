# CUBESHOTS
A rhythmic bullet hell game written in Java (based from the tutorial and game Wave© by RealTutsGML).\
\
[CUBESHOTS PORTABLE](https://github.com/GDjkhp/CUBESHOTS_PORTABLE) (for Android and HTML)\
\
![image 1](https://github.com/GDjkhp/CUBESHOTS/blob/master/readme%20pics/1%20attempt%20storm.PNG)\
\
![image 2](https://github.com/GDjkhp/CUBESHOTS/blob/master/readme%20pics/game%20showcase%202.jpg)\
\
![image 3](https://github.com/GDjkhp/CUBESHOTS/blob/master/readme%20pics/game_%20showcase.jpg)\
\
![image 4](https://github.com/GDjkhp/CUBESHOTS/blob/master/readme%20pics/conway.PNG)\
\
![image 5](https://github.com/GDjkhp/CUBESHOTS/blob/master/readme%20pics/path.PNG)\
\
![image 6](https://github.com/GDjkhp/CUBESHOTS/blob/master/readme%20pics/tictactoe.PNG)\
\
![image 7](https://github.com/GDjkhp/CUBESHOTS/blob/master/readme%20pics/water.PNG)\
\
![image 8](https://github.com/GDjkhp/CUBESHOTS/blob/master/readme%20pics/render%20texture.png)
# Libraries
Java 11: https://www.oracle.com/ph/java/technologies/javase-jdk11-downloads.html/
\
Slick2D: https://slick.ninjacave.com/
\
Xt-Audio: https://sjoerdvankreel.github.io/xt-audio/
\
Beads: http://www.beadsproject.net/
# Notes
The code will not work properly if you didn't follow these:
1. Edit the main code's (game_.java) run configuration's VM options to -Djava.library.path="path to libraries"
2. Add paths of the libraries and resources_ used.
# Building
You need JarSplice to compile the code (sorry i'm so lazy), so follow the instructions carefully.
1. Make sure ".idea\artifacts\CUBESHOTS__release_alpha__jar.xml" exist, or you can create your own.
2. Build > Build Artifacts > CUBESHOTS (release alpha).jar > Build
3. Open JarSplice then Add Jars > Import CUBESHOTS (release alpha).jar, all library jars
4. Leave natives blank.
5. Input main class "gamemakerstudio_.game_"
6. Create Fat Jar.
7. Copy [resources_](https://github.com/GDjkhp/CUBESHOTS_RESOURCES/) folder to the same directory of the created fat jar.
# Installation (for non-programmers)
Download the [resources_](https://github.com/GDjkhp/CUBESHOTS_RESOURCES/) folder (use the "download as zip" option) for the resources, and the latest release (the fat jar, located at [bin](https://github.com/GDjkhp/CUBESHOTS/tree/master/bin)).\
\
Edit: Must be in this directory structure:\
![add_dir](https://github.com/GDjkhp/CUBESHOTS/blob/master/readme%20pics/dir.png)
# FAQs
1. Mobile when?\
A: soon >:)
2. Mobile when??\
A: libgdx was irritating as hell!!
3. Another game?\
A: yes, i am confirming Beginning Words! a word puzzle game hahaha!
4. Another game??\
A: yes, i am confirming an unnamed 2.5D rpg sandbox game.
5. Another game???\
A: yes, i am confirming an unnamed platformer game.
6. Another game????\
A: yes, i am confirming ports from my old games, rng and color game.
