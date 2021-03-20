/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.world;

import gamemakerstudio_.entities.*;
import gamemakerstudio_.entities.boss.crazyboss_;
import gamemakerstudio_.game_;
import gamemakerstudio_.gui.hud2_;
import gamemakerstudio_.gui.hud_;
import gamemakerstudio_.misc.*;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author ACER
 */
public class levels_ implements MouseMotionListener, MouseListener, KeyListener, ActionListener {
    private handler_ handler;
    private hud_ hud;
    private game_ game;
    private hud2_ hud2;
    private Random r = new Random();

    // duration bar
    public static double durationBar;
    int durationValue = 255;

    // music misc
    public static double bpm;
    public double tpm;
    public double spm;
    public static int endBar;

    public static double scoreKeep = 0;
    public static double scoreKeepStep = 0;

    public double difference;
    public double stepDifference;

    // multiple bpm
    int time, newBpm;
    Map<Integer, Integer> multiBpm = new HashMap<>();

    // level
    public static int page = 1;
    public static int levelid = 0;
    public int maxPage = 7;
    public String testString = "";

    // select if lazy
    int gridSelectX = 50, gridSelectY = 50;

    // bug fix
    public static int lazyDelayFix = 100;

    public levels_(handler_ handler, hud_ hud, game_ game, hud2_ hud2) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
        this.hud2 = hud2;
        game.addKeyListener(this);
        game.addMouseMotionListener(this);
        game.addMouseListener(this);
    }

    public void hoverOnLevels(int mx, int my) {
        if (page == 1) {
            // first row
            if (mouseOver(mx, my, 50, 50, 50, 50)) {
                testString = "1. Shiatsu";
            } else if (mouseOver(mx, my, 150, 50, 50, 50)) {
                testString = "2. Astronomia";
            } else if (mouseOver(mx, my, 250, 50, 50, 50)) {
                testString = "3. Andromeda";
            } else if (mouseOver(mx, my, 350, 50, 50, 50)) {
                testString = "4. Rock the House";
            } else if (mouseOver(mx, my, 450, 50, 50, 50)) {
                testString = "5. End of Time";
            } else if (mouseOver(mx, my, 550, 50, 50, 50)) {
                testString = "6. Breathe";
            }
            // second row
            else if (mouseOver(mx, my, 50, 150, 50, 50)) {
                testString = "7. Time Leaper";
            } else if (mouseOver(mx, my, 150, 150, 50, 50)) {
                testString = "8. GG WP";
            } else if (mouseOver(mx, my, 250, 150, 50, 50)) {
                testString = "9. Moment of Truth";
            } else if (mouseOver(mx, my, 350, 150, 50, 50)) {
                testString = "10. Another World";
            } else if (mouseOver(mx, my, 450, 150, 50, 50)) {
                testString = "11. There";
            } else if (mouseOver(mx, my, 550, 150, 50, 50)) {
                testString = "12. Harmonies";
            }
            // third row
            else if (mouseOver(mx, my, 50, 250, 50, 50)) {
                testString = "13. Fisher Price";
            } else if (mouseOver(mx, my, 150, 250, 50, 50)) {
                testString = "14. Dance of the Violins";
            } else if (mouseOver(mx, my, 250, 250, 50, 50)) {
                testString = "15. Aether";
            } else if (mouseOver(mx, my, 350, 250, 50, 50)) {
                testString = "16. Clickbait";
            } else if (mouseOver(mx, my, 450, 250, 50, 50)) {
                testString = "17. Debug";
            } else if (mouseOver(mx, my, 550, 250, 50, 50)) {
                testString = "18. Fuchsia City";
            }
            // fourth row
            else if (mouseOver(mx, my, 50, 350, 50, 50)) {
                testString = "19. Ye";
            } else if (mouseOver(mx, my, 150, 350, 50, 50)) {
                testString = "20. Mocha";
            } else if (mouseOver(mx, my, 250, 350, 50, 50)) {
                testString = "21. Everything Falls";
            } else if (mouseOver(mx, my, 350, 350, 50, 50)) {
                testString = "22. Lonely Forest";
            } else if (mouseOver(mx, my, 450, 350, 50, 50)) {
                testString = "23. Magical";
            } else if (mouseOver(mx, my, 550, 350, 50, 50)) {
                testString = "24. Overcharge";
            }
            // fifth row
            else if (mouseOver(mx, my, 50, 450, 50, 50)) {
                testString = "25. Flavored Ice";
            } else if (mouseOver(mx, my, 150, 450, 50, 50)) {
                testString = "26. Shape of the Sun";
            } else if (mouseOver(mx, my, 250, 450, 50, 50)) {
                testString = "27. Angels";
            } else if (mouseOver(mx, my, 350, 450, 50, 50)) {
                testString = "28. Perseverance";
            } else if (mouseOver(mx, my, 450, 450, 50, 50)) {
                testString = "29. Dynasty";
            } else if (mouseOver(mx, my, 550, 450, 50, 50)) {
                testString = "30. Bloom";
            }
            // sixth row
            else if (mouseOver(mx, my, 50, 550, 50, 50)) {
                testString = "31. Canyon";
            } else if (mouseOver(mx, my, 150, 550, 50, 50)) {
                testString = "32. Overcloud";
            } else if (mouseOver(mx, my, 250, 550, 50, 50)) {
                testString = "33. Ppaper Pplanes";
            } else if (mouseOver(mx, my, 350, 550, 50, 50)) {
                testString = "34. Prelude";
            } else if (mouseOver(mx, my, 450, 550, 50, 50)) {
                testString = "35. Spirit";
            } else if (mouseOver(mx, my, 550, 550, 50, 50)) {
                testString = "36. Catalyze";
            }
            // seventh row
            else if (mouseOver(mx, my, 50, 650, 50, 50)) {
                testString = "37. Stray";
            } else if (mouseOver(mx, my, 150, 650, 50, 50)) {
                testString = "38. Jazz Battle";
            } else if (mouseOver(mx, my, 250, 650, 50, 50)) {
                testString = "39. Success";
            } else if (mouseOver(mx, my, 350, 650, 50, 50)) {
                testString = "40. Supernova";
            } else if (mouseOver(mx, my, 450, 650, 50, 50)) {
                testString = "41. The Beginning of Time";
            } else if (mouseOver(mx, my, 550, 650, 50, 50)) {
                testString = "42. Marbl";
            }
            // else change to zero
            else testString = "";
        }
        if (page == 2) {
            // first row
            if (mouseOver(mx, my, 50, 50, 50, 50)) {
                testString = "43. A Dreamer";
            } else if (mouseOver(mx, my, 150, 50, 50, 50)) {
                testString = "44. Ghost House";
            } else if (mouseOver(mx, my, 250, 50, 50, 50)) {
                testString = "45. Jude";
            } else if (mouseOver(mx, my, 350, 50, 50, 50)) {
                testString = "46. Don't Hold Back";
            } else if (mouseOver(mx, my, 450, 50, 50, 50)) {
                testString = "47. Ouais Ouais";
            } else if (mouseOver(mx, my, 550, 50, 50, 50)) {
                testString = "48. Things That You Do";
            }
            // second row
            else if (mouseOver(mx, my, 50, 150, 50, 50)) {
                testString = "49. Sad Summer";
            } else if (mouseOver(mx, my, 150, 150, 50, 50)) {
                testString = "50. Wishing";
            } else if (mouseOver(mx, my, 250, 150, 50, 50)) {
                testString = "51. Call the Popo";
            } else if (mouseOver(mx, my, 350, 150, 50, 50)) {
                testString = "52. Ocean Ambiance Remix";
            } else if (mouseOver(mx, my, 450, 150, 50, 50)) {
                testString = "53. Snowcone";
            } else if (mouseOver(mx, my, 550, 150, 50, 50)) {
                testString = "54. Sawdust Angels";
            }
            // third row
            else if (mouseOver(mx, my, 50, 250, 50, 50)) {
                testString = "55. Sky High";
            } else if (mouseOver(mx, my, 150, 250, 50, 50)) {
                testString = "56. My Heart";
            } else if (mouseOver(mx, my, 250, 250, 50, 50)) {
                testString = "57. Nekozilla";
            } else if (mouseOver(mx, my, 350, 250, 50, 50)) {
                testString = "58. Cloud 9";
            } else if (mouseOver(mx, my, 450, 250, 50, 50)) {
                testString = "59. Sunburst";
            } else if (mouseOver(mx, my, 550, 250, 50, 50)) {
                testString = "60. Hellcat";
            }
            // fourth row
            else if (mouseOver(mx, my, 50, 350, 50, 50)) {
                testString = "61. Song for Denise";
            }
            else if (mouseOver(mx, my, 150, 350, 50, 50)) {
                testString = "62. Dollar Needles 1";
            }
            else if (mouseOver(mx, my, 250, 350, 50, 50)) {
                testString = "63. Dollar Needles 3";
            }
            else if (mouseOver(mx, my, 350, 350, 50, 50)) {
                testString = "64. Behind These Closed Doors";
            }
            else if (mouseOver(mx, my, 450, 350, 50, 50)) {
                testString = "65. Otis McMusic";
            }
            else if (mouseOver(mx, my, 550, 350, 50, 50)) {
                testString = "66. Not for Nothing";
            }
            // fifth row
            else if (mouseOver(mx, my, 50, 450, 50, 50)) {
                testString = "67. Mind Control";
            }
            else if (mouseOver(mx, my, 150, 450, 50, 50)) {
                testString = "68. Memories";
            }
            else if (mouseOver(mx, my, 250, 450, 50, 50)) {
                testString = "69. 2011";
            }
            else if (mouseOver(mx, my, 350, 450, 50, 50)) {
                testString = "70. 10000";
            }
            else if (mouseOver(mx, my, 450, 450, 50, 50)) {
                testString = "71. Merry Christmas";
            }
            else if (mouseOver(mx, my, 550, 450, 50, 50)) {
                testString = "72. Crab Rave";
            }
            // sixth row
            else if (mouseOver(mx, my, 50, 550, 50, 50)) {
                testString = "73. Insurgent";
            }
            else if (mouseOver(mx, my, 150, 550, 50, 50)) {
                testString = "74. Late Night Lo-Fi";
            }
            else if (mouseOver(mx, my, 250, 550, 50, 50)) {
                testString = "75. Lith Harbor";
            }
            else if (mouseOver(mx, my, 350, 550, 50, 50)) {
                testString = "76. Lost in Thought";
            }
            else if (mouseOver(mx, my, 450, 550, 50, 50)) {
                testString = "77. Holystone";
            }
            else if (mouseOver(mx, my, 550, 550, 50, 50)) {
                testString = "78. Pounce";
            }
            // seventh row
            else if (mouseOver(mx, my, 50, 650, 50, 50)) {
                testString = "79. Futa Dick";
            }
            else if (mouseOver(mx, my, 150, 650, 50, 50)) {
                testString = "80. Golden Haze";
            }
            else if (mouseOver(mx, my, 250, 650, 50, 50)) {
                testString = "81. Grim Reaper";
            }
            else if (mouseOver(mx, my, 350, 650, 50, 50)) {
                testString = "82. Highscore";
            }
            else if (mouseOver(mx, my, 450, 650, 50, 50)) {
                testString = "83. Purity";
            }
            else if (mouseOver(mx, my, 550, 650, 50, 50)) {
                testString = "84. Yellow Orange Afternoon";
            }
            // else change to zero
            else testString = "";
        }//43
        if (page == 3) {
            // first row
            if (mouseOver(mx, my, 50, 50, 50, 50)) {
                testString = "85. Surface";
            }
            else if (mouseOver(mx, my, 150, 50, 50, 50)) {
                testString = "86. Arabic Nokia Type Beat";
            }
            else if (mouseOver(mx, my, 250, 50, 50, 50)) {
                testString = "87. Energy Drink";
            }
            else if (mouseOver(mx, my, 350, 50, 50, 50)) {
                testString = "88. Happy";
            }
            else if (mouseOver(mx, my, 450, 50, 50, 50)) {
                testString = "89. Moonsugar";
            }
            else if (mouseOver(mx, my, 550, 50, 50, 50)) {
                testString = "90. Rubik";
            }
            // second row
            else if (mouseOver(mx, my, 50, 150, 50, 50)) {
                testString = "91. Ignition";
            }
            else if (mouseOver(mx, my, 150, 150, 50, 50)) {
                testString = "92. The Force";
            }
            else if (mouseOver(mx, my, 250, 150, 50, 50)) {
                testString = "93. Candyland";
            }
            else if (mouseOver(mx, my, 350, 150, 50, 50)) {
                testString = "94. Infectious";
            }
            else if (mouseOver(mx, my, 450, 150, 50, 50)) {
                testString = "95. Crazy";
            }
            else if (mouseOver(mx, my, 550, 150, 50, 50)) {
                testString = "96. Race Around the World";
            }
            // third row
            else if (mouseOver(mx, my, 50, 250, 50, 50)) {
                testString = "97. Ice Flow";
            }
            else if (mouseOver(mx, my, 150, 250, 50, 50)) {
                testString = "98. Kalimba";
            }
            else if (mouseOver(mx, my, 250, 250, 50, 50)) {
                testString = "99. Who Likes to Party";
            }
            else if (mouseOver(mx, my, 350, 250, 50, 50)) {
                testString = "100. Invasion of the Gabber Robots";
            }
            else if (mouseOver(mx, my, 450, 250, 50, 50)) {
                testString = "101. Hazmat";
            }
            else if (mouseOver(mx, my, 550, 250, 50, 50)) {
                testString = "102. Panda Dance";
            }
            // fourth row
            else if (mouseOver(mx, my, 50, 350, 50, 50)) {
                testString = "103. Get Up";
            }
            else if (mouseOver(mx, my, 150, 350, 50, 50)) {
                testString = "104. River";
            }
            else if (mouseOver(mx, my, 250, 350, 50, 50)) {
                testString = "105. Follow";
            }
            else if (mouseOver(mx, my, 350, 350, 50, 50)) {
                testString = "106. Slime";
            }
            else if (mouseOver(mx, my, 450, 350, 50, 50)) {
                testString = "107. Euphoria";
            }
            else if (mouseOver(mx, my, 550, 350, 50, 50)) {
                testString = "108. Nautilus";
            }
            // fifth row
            else if (mouseOver(mx, my, 50, 450, 50, 50)) {
                testString = "109. Crystal Corruption";
            }
            else if (mouseOver(mx, my, 150, 450, 50, 50)) {
                testString = "110. Crazy Frog";
            }
            else if (mouseOver(mx, my, 250, 450, 50, 50)) {
                testString = "111. Never Lose Hope";
            }
            else if (mouseOver(mx, my, 350, 450, 50, 50)) {
                testString = "112. Skystrike";
            }
            else if (mouseOver(mx, my, 450, 450, 50, 50)) {
                testString = "113. Starchaser";
            }
            else if (mouseOver(mx, my, 550, 450, 50, 50)) {
                testString = "114. A Newer Dawn";
            }
            // sixth row
            else if (mouseOver(mx, my, 50, 550, 50, 50)) {
                testString = "115. Monody";
            }
            else if (mouseOver(mx, my, 150, 550, 50, 50)) {
                testString = "116. Unity";
            }
            else if (mouseOver(mx, my, 250, 550, 50, 50)) {
                testString = "117. Xenogenesis";
            }
            else if (mouseOver(mx, my, 350, 550, 50, 50)) {
                testString = "118. Time Stops";
            }
            else if (mouseOver(mx, my, 450, 550, 50, 50)) {
                testString = "119. Badland";
            }
            else if (mouseOver(mx, my, 550, 550, 50, 50)) {
                testString = "120. Challenger";
            }
            // seventh row
            else if (mouseOver(mx, my, 50, 650, 50, 50)) {
                testString = "121. Bluemoon";
            }
            else if (mouseOver(mx, my, 150, 650, 50, 50)) {
                testString = "122. Cherry Blossoms";
            }
            else if (mouseOver(mx, my, 250, 650, 50, 50)) {
                testString = "123. Walkman";
            }
            else if (mouseOver(mx, my, 350, 650, 50, 50)) {
                testString = "124. Beyond the Walls";
            }
            else if (mouseOver(mx, my, 450, 650, 50, 50)) {
                testString = "125. April";
            }
            else if (mouseOver(mx, my, 550, 650, 50, 50)) {
                testString = "126. Sunlight";
            }
            // else change to zero
            else testString = "";
        }//85
        if (page == 4) {
            // first row
            if (mouseOver(mx, my, 50, 50, 50, 50)){
                testString = "127. Cyptik Dance";
            }
            else if (mouseOver(mx, my, 150, 50, 50, 50)){
                testString = "128. Leaving Leafwood Forest";
            }
            else if (mouseOver(mx, my, 250, 50, 50, 50)){
                testString = "129. Troglodyte";
            }
            else if (mouseOver(mx, my, 350, 50, 50, 50)){
                testString = "130. The Maze of Mayonnaise";
            }
            else if (mouseOver(mx, my, 450, 50, 50, 50)){
                testString = "131. Starship Showdown";
            }
            else if (mouseOver(mx, my, 550, 50, 50, 50)){
                testString = "132. Kumquat";
            }
            // second row
            else if (mouseOver(mx, my, 50, 150, 50, 50)){
                testString = "133. O-Oh Hi-i T-There, J-J-Jaclyn";
            }
            else if (mouseOver(mx, my, 150, 150, 50, 50)){
                testString = "134. This Chap Named Jacques";
            }
            else if (mouseOver(mx, my, 250, 150, 50, 50)){
                testString = "135. Liftoff";
            }
            else if (mouseOver(mx, my, 350, 150, 50, 50)){
                testString = "136. Never Be Alone";
            }
            else if (mouseOver(mx, my, 450, 150, 50, 50)){
                testString = "137. Solitude";
            }
            else if (mouseOver(mx, my, 550, 150, 50, 50)){
                testString = "138. Close to the Sun";
            }
            // third row
            else if (mouseOver(mx, my, 50, 250, 50, 50)){
                testString = "139. Nanamori";
            }
            else if (mouseOver(mx, my, 150, 250, 50, 50)){
                testString = "140. Fury";
            }
            else if (mouseOver(mx, my, 250, 250, 50, 50)){
                testString = "141. Desu Ka";
            }
            else if (mouseOver(mx, my, 350, 250, 50, 50)){
                testString = "142. Voices";
            }
            else if (mouseOver(mx, my, 450, 250, 50, 50)){
                testString = "143. Dancing";
            }
            else if (mouseOver(mx, my, 550, 250, 50, 50)){
                testString = "144. Shining Space";
            }
            // fourth row
            else if (mouseOver(mx, my, 50, 350, 50, 50)){
                testString = "145. Space Invaders";
            }
            else if (mouseOver(mx, my, 150, 350, 50, 50)){
                testString = "146. Drippy Dub";
            }
            else if (mouseOver(mx, my, 250, 350, 50, 50)){
                testString = "147. Fake Princess";
            }
            else if (mouseOver(mx, my, 350, 350, 50, 50)){
                testString = "148. The Beauty and The Lazergun";
            }
            else if (mouseOver(mx, my, 450, 350, 50, 50)){
                testString = "149. Afterimage";
            }
            else if (mouseOver(mx, my, 550, 350, 50, 50)){
                testString = "150. Star Wars Remix";
            }
            // fifth row
            else if (mouseOver(mx, my, 50, 450, 50, 50)){
                testString = "151. Kiss Me Kiss You";
            }
            else if (mouseOver(mx, my, 150, 450, 50, 50)){
                testString = "152. Enigma";
            }
            else if (mouseOver(mx, my, 250, 450, 50, 50)){
                testString = "153. Never Make It";
            }
            else if (mouseOver(mx, my, 350, 450, 50, 50)){
                testString = "154. Flight";
            }
            else if (mouseOver(mx, my, 450, 450, 50, 50)){
                testString = "155. Journey";
            }
            else if (mouseOver(mx, my, 550, 450, 50, 50)){
                testString = "156. Lonely Forest";
            }
            // sixth row
            else if (mouseOver(mx, my, 50, 550, 50, 50)){
                testString = "157. He's a Pirate";
            }
            else if (mouseOver(mx, my, 150, 550, 50, 50)){
                testString = "158. Aquamarine";
            }
            else if (mouseOver(mx, my, 250, 550, 50, 50)){
                testString = "159. Boombox 2012";
            }
            else if (mouseOver(mx, my, 350, 550, 50, 50)){
                testString = "160. Dreams";
            }
            else if (mouseOver(mx, my, 450, 550, 50, 50)){
                testString = "161. Requiem for a Dream";
            }
            else if (mouseOver(mx, my, 550, 550, 50, 50)){
                testString = "162. Eurodancer";
            }
            // seventh row
            else if (mouseOver(mx, my, 50, 650, 50, 50)){
                testString = "163. Hello";
            }
            else if (mouseOver(mx, my, 150, 650, 50, 50)){
                testString = "164. Mayday";
            }
            else if (mouseOver(mx, my, 250, 650, 50, 50)){
                testString = "165. Firefiles (Zyzyx Remix)";
            }
            else if (mouseOver(mx, my, 350, 650, 50, 50)){
                testString = "166. Echolands";
            }
            else if (mouseOver(mx, my, 450, 650, 50, 50)){
                testString = "167. Voiceless";
            }
            else if (mouseOver(mx, my, 550, 650, 50, 50)){
                testString = "168. Vaporwave Aesthetics";
            }

            // else change to zero
            else testString = "";
        }//127
        if (page == 5) {
            // first row
            if (mouseOver(mx, my, 50, 50, 50, 50)){
                testString = "169. The Happy Troll";
            }
            else if (mouseOver(mx, my, 150, 50, 50, 50)){
                testString = "170. Dimension";
            }
            else if (mouseOver(mx, my, 250, 50, 50, 50)){
                testString = "171. Crystal Caves";
            }
            else if (mouseOver(mx, my, 350, 50, 50, 50)){
                testString = "172. Elevate";
            }
            else if (mouseOver(mx, my, 450, 50, 50, 50)){
                testString = "173. Okiba Crackdown";
            }
            else if (mouseOver(mx, my, 550, 50, 50, 50)){
                testString = "174. The Falling Mysts";
            }
            // second row
            else if (mouseOver(mx, my, 50, 150, 50, 50)){
                testString = "175. Return to NG LOL";
            }
            else if (mouseOver(mx, my, 150, 150, 50, 50)){
                testString = "176. Body Jammer";
            }
            else if (mouseOver(mx, my, 250, 150, 50, 50)){
                testString = "177. Flirt Flirt Oh It Hurts";
            }
            else if (mouseOver(mx, my, 350, 150, 50, 50)){
                testString = "178. Retry";
            }
            else if (mouseOver(mx, my, 450, 150, 50, 50)){
                testString = "179. Jet Set";
            }
            else if (mouseOver(mx, my, 550, 150, 50, 50)){
                testString = "180. The Calling";
            }
            // third row
            else if (mouseOver(mx, my, 50, 250, 50, 50)){
                testString = "181. Tria";
            }
            else if (mouseOver(mx, my, 150, 250, 50, 50)){
                testString = "182. Endgame";
            }
            else if (mouseOver(mx, my, 250, 250, 50, 50)){
                testString = "183. Night Out";
            }
            else if (mouseOver(mx, my, 350, 250, 50, 50)){
                testString = "184. April Showers";
            }
            else if (mouseOver(mx, my, 450, 250, 50, 50)){
                testString = "185. Bathtub";
            }
            else if (mouseOver(mx, my, 550, 250, 50, 50)){
                testString = "186. Supernova";
            }
            // fourth row
            else if (mouseOver(mx, my, 50, 350, 50, 50)){
                testString = "187. Force";
            }
            else if (mouseOver(mx, my, 150, 350, 50, 50)){
                testString = "188. Spectre";
            }
            else if (mouseOver(mx, my, 250, 350, 50, 50)){
                testString = "189. Faded";
            }
            else if (mouseOver(mx, my, 350, 350, 50, 50)){
                testString = "190. Bangarang";
            }
            else if (mouseOver(mx, my, 450, 350, 50, 50)){
                testString = "191. Make It Bun Dem";
            }
            else if (mouseOver(mx, my, 550, 350, 50, 50)){
                testString = "192. Sleepyhead";
            }
            // fifth row
            else if (mouseOver(mx, my, 50, 450, 50, 50)){
                testString = "193. Dance till You're Dead";
            }
            else if (mouseOver(mx, my, 150, 450, 50, 50)){
                testString = "194. Animals";
            }
            else if (mouseOver(mx, my, 250, 450, 50, 50)){
                testString = "195. Yeah";
            }
            else if (mouseOver(mx, my, 350, 450, 50, 50)){
                testString = "196. November";
            }
            else if (mouseOver(mx, my, 450, 450, 50, 50)){
                testString = "197. Florescence (737702)";
            }
            else if (mouseOver(mx, my, 550, 450, 50, 50)){
                testString = "198. Mantis Shrimp Showdown (677479)";
            }
            // sixth row
            else if (mouseOver(mx, my, 50, 550, 50, 50)){
                testString = "199. Holy War (771703)";
            }
            else if (mouseOver(mx, my, 150, 550, 50, 50)){
                testString = "200. Massacre (579857)";
            }
            else if (mouseOver(mx, my, 250, 550, 50, 50)){
                testString = "201. Believe (703408)";
            }
            else if (mouseOver(mx, my, 350, 550, 50, 50)){
                testString = "202. Karma (747194)";
            }
            else if (mouseOver(mx, my, 450, 550, 50, 50)){
                testString = "203. Refraction (743152)";
            }
            else if (mouseOver(mx, my, 550, 550, 50, 50)){
                testString = "204. Glome (838726)";
            }
            // seventh row
            else if (mouseOver(mx, my, 50, 650, 50, 50)){
                testString = "205. Red Roses (672293)";
            }
            else if (mouseOver(mx, my, 150, 650, 50, 50)){
                testString = "206. A Little Older, A Little Wiser (754078)";
            }
            else if (mouseOver(mx, my, 250, 650, 50, 50)){
                testString = "207. When Time Tears (807337)";
            }
            else if (mouseOver(mx, my, 350, 650, 50, 50)){
                testString = "208. Hold On To Me (796490)";
            }
            else if (mouseOver(mx, my, 450, 650, 50, 50)){
                testString = "209. I'm Still Alive (887538)";
            }
            else if (mouseOver(mx, my, 550, 650, 50, 50)){
                testString = "210. Nirmiti (730246)";
            }
            // else change to zero
            else testString = "";
        }//169
        if (page == 6) {
            // first row
            if (mouseOver(mx, my, 50, 50, 50, 50)){
                testString = "211. Avast Your Ass (659744)";
            }
            else if (mouseOver(mx, my, 150, 50, 50, 50)){
                testString = "212. Soulwind (586990)";
            }
            else if (mouseOver(mx, my, 250, 50, 50, 50)){
                testString = "213. Zenith (749676)";
            }
            else if (mouseOver(mx, my, 350, 50, 50, 50)){
                testString = "214. Thinking With Portals (738894)";
            }
            else if (mouseOver(mx, my, 450, 50, 50, 50)){
                testString = "215. Mellow (759591)";
            }
            else if (mouseOver(mx, my, 550, 50, 50, 50)){
                testString = "216. Lullaby Remix (551385)";
            }
            // second row
            else if (mouseOver(mx, my, 50, 150, 50, 50)){
                testString = "217. Florescentia (843207)";
            }
            else if (mouseOver(mx, my, 150, 150, 50, 50)){
                testString = "218. Bioluminescent (583598)";
            }
            else if (mouseOver(mx, my, 250, 150, 50, 50)){
                testString = "219. Let's Bounce (569208)";
            }
            else if (mouseOver(mx, my, 350, 150, 50, 50)){
                testString = "220. Orbit (750199)";
            }
            else if (mouseOver(mx, my, 450, 150, 50, 50)){
                testString = "221. Luminous (721172)";
            }
            else if (mouseOver(mx, my, 550, 150, 50, 50)){
                testString = "222. Mako [Resonate] (588818)";
            }
            // third row
            else if (mouseOver(mx, my, 50, 250, 50, 50)){
                testString = "223. Love Talk (896264)";
            }
            else if (mouseOver(mx, my, 150, 250, 50, 50)){
                testString = "224. Trouble (939885)";
            }
            else if (mouseOver(mx, my, 250, 250, 50, 50)){
                testString = "225. Peepee Song (922510)";
            }
            else if (mouseOver(mx, my, 350, 250, 50, 50)){
                testString = "226. Rose (779227)";
            }
            else if (mouseOver(mx, my, 450, 250, 50, 50)){
                testString = "227. Fateful Mist (673470)";
            }
            else if (mouseOver(mx, my, 550, 250, 50, 50)){
                testString = "228. FlashYizz - Crazy (746583)";
            }
            // fourth row
            else if (mouseOver(mx, my, 50, 350, 50, 50)){
                testString = "229. Glorious Morning (91476)";
            }
            else if (mouseOver(mx, my, 150, 350, 50, 50)){
                testString = "230. FlyBoy&GabberGirl (629681)";
            }
            else if (mouseOver(mx, my, 250, 350, 50, 50)){
                testString = "231. Battletown (664136)";
            }
            else if (mouseOver(mx, my, 350, 350, 50, 50)){
                testString = "232. Nanobyte (665585)";
            }
            else if (mouseOver(mx, my, 450, 350, 50, 50)){
                testString = "233. Fantasy (785100)";
            }
            else if (mouseOver(mx, my, 550, 350, 50, 50)){
                testString = "234. Artificial (799832)";
            }
            // fifth row
            else if (mouseOver(mx, my, 50, 450, 50, 50)){
                testString = "235. Run Away (721929)";
            }
            else if (mouseOver(mx, my, 150, 450, 50, 50)){
                testString = "236. Dream (785099)";
            }
            else if (mouseOver(mx, my, 250, 450, 50, 50)){
                testString = "237. Drained (740883)";
            }
            else if (mouseOver(mx, my, 350, 450, 50, 50)){
                testString = "238. Just Kidding! (788777)";
            }
            else if (mouseOver(mx, my, 450, 450, 50, 50)){
                testString = "239. Saunter (831097)";
            }
            else if (mouseOver(mx, my, 550, 450, 50, 50)){
                testString = "240. Help Me Up (163061)";
            }
            // sixth row
            else if (mouseOver(mx, my, 50, 550, 50, 50)){
                testString = "241. Ocean of Stars";
            }
            else if (mouseOver(mx, my, 150, 550, 50, 50)){
                testString = "242. Bash (579626)";
            }
            else if (mouseOver(mx, my, 250, 550, 50, 50)){
                testString = "243. Whirlwind (589599)";
            }
            else if (mouseOver(mx, my, 350, 550, 50, 50)){
                testString = "244. Screamroom (586809)";
            }
            else if (mouseOver(mx, my, 450, 550, 50, 50)){
                testString = "245. Ichor (644761)";
            }
            else if (mouseOver(mx, my, 550, 550, 50, 50)){
                testString = "246. Time Lapse (621139)";
            }
            // seventh row
            else if (mouseOver(mx, my, 50, 650, 50, 50)){
                testString = "247. Calm (702912)";
            }
            else if (mouseOver(mx, my, 150, 650, 50, 50)){
                testString = "248. Gloomy (794193)";
            }
            else if (mouseOver(mx, my, 250, 650, 50, 50)){
                testString = "249. Love's Song (412494)";
            }
            else if (mouseOver(mx, my, 350, 650, 50, 50)){
                testString = "250. Blast Em (988166)";
            }
            else if (mouseOver(mx, my, 450, 650, 50, 50)){
                testString = "251. Lost In The Rhythm (761410)";
            }
            else if (mouseOver(mx, my, 550, 650, 50, 50)){
                testString = "252. Zelda II: Palace Theme (Xtrullor Remix) (687436)";
            }
            // else change to zero
            else testString = "";
        }//211
        if (page == 7) {
            // first row
            if (mouseOver(mx, my, 50, 50, 50, 50)){
                testString = "253. Form (781768)";
            }
            else if (mouseOver(mx, my, 150, 50, 50, 50)){
                testString = "254. Paladin (539635)";
            }
            else if (mouseOver(mx, my, 250, 50, 50, 50)){
                testString = "255. Myriad (757640)";
            }
            else if (mouseOver(mx, my, 350, 50, 50, 50)){
                testString = "256. Pyrolysis (734427)";
            }
            else if (mouseOver(mx, my, 450, 50, 50, 50)){
                testString = "257. Elevatia (704606)";
            }
            else if (mouseOver(mx, my, 550, 50, 50, 50)){
                testString = "258. Prelude Remix (734755)";
            }
            // second row
            else if (mouseOver(mx, my, 50, 150, 50, 50)){
                testString = "259. Could It Be? (847502)";
            }
            else if (mouseOver(mx, my, 150, 150, 50, 50)){
                testString = "260. Spooky (523173)";
            }
            else if (mouseOver(mx, my, 250, 150, 50, 50)){
                testString = "261. Ena (681533)";
            }
            else if (mouseOver(mx, my, 350, 150, 50, 50)){
                testString = "262. Reapers (714209)";
            }
            else if (mouseOver(mx, my, 450, 150, 50, 50)){
                testString = "263. Last Dance (866074)";
            }
            else if (mouseOver(mx, my, 550, 150, 50, 50)){
                testString = "264. Reminisce (836173)";
            }
            // third row
            else if (mouseOver(mx, my, 50, 250, 50, 50)){
                testString = "265. Who You Are";
            }
            else if (mouseOver(mx, my, 150, 250, 50, 50)){
                testString = "266. Stardrive";
            }
            else if (mouseOver(mx, my, 250, 250, 50, 50)){
                testString = "267. Level One";
            }
            else if (mouseOver(mx, my, 350, 250, 50, 50)){
                testString = "268. Lights";
            }
            else if (mouseOver(mx, my, 450, 250, 50, 50)){
                testString = "269. Skybound";
            }
            else if (mouseOver(mx, my, 550, 250, 50, 50)){
                testString = "270. Open Your Eyes";
            }
            // fourth row
            else if (mouseOver(mx, my, 50, 350, 50, 50)){
                testString = "271. Party Hard Remix";
            }
            else if (mouseOver(mx, my, 150, 350, 50, 50)){
                testString = "272. Nice Vibes";
            }
            else if (mouseOver(mx, my, 250, 350, 50, 50)){
                testString = "273. Horizons Remix";
            }
            else if (mouseOver(mx, my, 350, 350, 50, 50)){
                testString = "274. Ablixa";
            }
            else if (mouseOver(mx, my, 450, 350, 50, 50)){
                testString = "275. Untitled";
            }
            else if (mouseOver(mx, my, 550, 350, 50, 50)){
                testString = "276. Litoff";
            }
            // fifth row
            else if (mouseOver(mx, my, 50, 450, 50, 50)){
                testString = "277. Nuetronium";
            }
            else if (mouseOver(mx, my, 150, 450, 50, 50)){
                testString = "278. Sky Venture";
            }
            else if (mouseOver(mx, my, 250, 450, 50, 50)){
                testString = "279. Sad Machine Remix";
            }
            else if (mouseOver(mx, my, 350, 450, 50, 50)){
                testString = "280. Jazz Jackrabbit Remix";
            }
            else if (mouseOver(mx, my, 450, 450, 50, 50)){
                testString = "281. Shadow Queen Pt. 2 Remix";
            }
            else if (mouseOver(mx, my, 550, 450, 50, 50)){
                testString = "282. Let's Stomp";
            }
            // sixth row
            else if (mouseOver(mx, my, 50, 550, 50, 50)){
                testString = "283. Twinrova";
            }
            else if (mouseOver(mx, my, 150, 550, 50, 50)){
                testString = "284. Our Home";
            }
            else if (mouseOver(mx, my, 250, 550, 50, 50)){
                testString = "285. Geometry Dance";
            }
            else if (mouseOver(mx, my, 350, 550, 50, 50)){
                testString = "286. This is Geometry Dash";
            }
            else if (mouseOver(mx, my, 450, 550, 50, 50)){
                testString = "287. Waves";
            }
            else if (mouseOver(mx, my, 550, 550, 50, 50)){
                testString = "288. Among Us Trap Remix";
            }
            // seventh row
            else if (mouseOver(mx, my, 50, 650, 50, 50)){
                testString = "289. Legend";
            }
            else if (mouseOver(mx, my, 150, 650, 50, 50)){
                testString = "290. This Girl";
            }
            else if (mouseOver(mx, my, 250, 650, 50, 50)){
                testString = "291. Sunwalker";
            }
            else if (mouseOver(mx, my, 350, 650, 50, 50)){
                testString = "292. Afterglow";
            }
            else if (mouseOver(mx, my, 450, 650, 50, 50)){
                testString = "293. A Cow That Is Actually A Chicken";
            }
            else if (mouseOver(mx, my, 550, 650, 50, 50)){
                testString = "294. ストーカー";
            }
            // else change to zero
            else testString = "";
        }//253
    }

    // lazy locate
    public int mx, my;

    // changing libraries might not show these errors bcz they are logical
    public void levelsList(int mx, int my) {
        // lazy locate
        this.mx = mx;
        this.my = my;

        if (game.loadstate == 100) {

            // fix for delay, not working, remove this
            // isPlaying = false;

            if (page == 1) {
                if (mouseOver(mx, my, 50, 50, 50, 50)) {
                    levelid = 1;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("dead_meme").play();
                    // world misc
                    bpm = 125;
                    endBar = 84;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 50, 50, 50)) {
                    levelid = 2;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("dead_meme_2").play();
                    // world misc
                    bpm = 126;
                    endBar = 130;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 50, 50, 50)) {
                    levelid = 3;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("eschew").play();
                    // world misc
                    bpm = 120;
                    endBar = 144;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 50, 50, 50)) {
                    levelid = 4;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("rock_the_house").play();
                    // world misc
                    bpm = 128;
                    endBar = 106;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 50, 50, 50)) {
                    levelid = 5;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("end_of_time").play();
                    // world misc
                    bpm = 132;
                    endBar = 122;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 50, 50, 50)) {
                    levelid = 6;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("nova_music").play();
                    // world misc
                    bpm = 105;
                    endBar = 102;
                    // reset method
                    resetMethod();
                }
                // second row
                if (mouseOver(mx, my, 50, 150, 50, 50)) {
                    levelid = 7;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("time_leaper").play();
                    // world misc
                    bpm = 87.5;
                    endBar = 88;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 150, 50, 50)) {
                    levelid = 8;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("gg").play();
                    // world misc
                    bpm = 87.5;
                    endBar = 110;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 150, 50, 50)) {
                    levelid = 9;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("namice1").play();
                    // world misc
                    bpm = 128;
                    endBar = 90;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 150, 50, 50)) {
                    levelid = 10;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("namice2").play();
                    // world misc
                    bpm = 128;
                    endBar = 94;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 150, 50, 50)) {
                    levelid = 11;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("there").play();
                    // world misc
                    bpm = 127;
                    endBar = 139;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 150, 50, 50)) {
                    levelid = 12;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("namice3").play();
                    // world misc
                    bpm = 128;
                    endBar = 72;
                    // reset method
                    resetMethod();
                }
                // third row
                if (mouseOver(mx, my, 50, 250, 50, 50)) {
                    levelid = 13;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("fisher_price").play();
                    // world misc
                    bpm = 92.5;
                    endBar = 73;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 250, 50, 50)) {
                    levelid = 14;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("dance_violins").play();
                    // world misc
                    bpm = 130;
                    endBar = 179;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 250, 50, 50)) {
                    levelid = 15;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("aether").play();
                    // world misc
                    bpm = 165;
                    endBar = 66;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 250, 50, 50)) {
                    levelid = 16;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("clickbait").play();
                    // world misc
                    bpm = 140;
                    endBar = 113;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 250, 50, 50)) {
                    levelid = 17;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("debug").play();
                    // world misc
                    bpm = 130;
                    endBar = 218;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 250, 50, 50)) {
                    levelid = 18;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("iy").play();
                    // world misc
                    bpm = 114;
                    endBar = 125;
                    // reset method
                    resetMethod();
                }
                // fourth row
                if (mouseOver(mx, my, 50, 350, 50, 50)) {
                    levelid = 19;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("ye").play();
                    // world misc
                    bpm = 110;
                    endBar = 30;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 350, 50, 50)) {
                    levelid = 20;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("mocha").play();
                    // world misc
                    bpm = 160;
                    endBar = 138;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 350, 50, 50)) {
                    levelid = 21;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("everything_falls").play();
                    // world misc
                    bpm = 90;
                    endBar = 66;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 350, 50, 50)) {
                    levelid = 22;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("lonely_forest").play();
                    // world misc
                    bpm = 101;
                    endBar = 66;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 350, 50, 50)) {
                    levelid = 23;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("saxophone").play();
                    // world misc
                    bpm = 96;
                    endBar = 31;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 350, 50, 50)) {
                    levelid = 24;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("overcharge").play();
                    // world misc
                    bpm = 80;
                    endBar = 70;
                    // reset method
                    resetMethod();
                }
                // fifth row
                if (mouseOver(mx, my, 50, 450, 50, 50)) {
                    levelid = 25;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("flavored_ice").play();
                    // world misc
                    bpm = 160;
                    endBar = 66;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 450, 50, 50)) {
                    levelid = 26;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("shape_of_the_sun").play();
                    // world misc
                    bpm = 82.52;
                    endBar = 68;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 450, 50, 50)) {
                    levelid = 27;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("angels").play();
                    // world misc
                    bpm = 128;
                    endBar = 114;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 450, 50, 50)) {
                    levelid = 28;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("perseverance").play();
                    // world misc
                    bpm = 130;
                    endBar = 162;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 450, 50, 50)) {
                    levelid = 29;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("dynasty").play();
                    // world misc
                    bpm = 128;
                    endBar = 131;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 450, 50, 50)) {
                    levelid = 30;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("bloom").play();
                    // world misc
                    bpm = 106;
                    endBar = 86;
                    // reset method
                    resetMethod();
                }
                // sixth row
                if (mouseOver(mx, my, 50, 550, 50, 50)) {
                    levelid = 31;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("canyon").play();
                    // world misc
                    bpm = 85;
                    endBar = 66;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 550, 50, 50)) {
                    levelid = 32;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("overcloud").play();
                    // world misc
                    bpm = 150;
                    endBar = 138;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 550, 50, 50)) {
                    levelid = 33;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("ppaper_pplanes").play();
                    // world misc
                    bpm = 89.8;
                    endBar = 57;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 550, 50, 50)) {
                    levelid = 34;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("prelude").play();
                    // world misc
                    bpm = 113;
                    endBar = 100;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 550, 50, 50)) {
                    levelid = 35;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("spirit").play();
                    // world misc
                    bpm = 87;
                    endBar = 91;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 550, 50, 50)) {
                    levelid = 36;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("catalyze").play();
                    // world misc
                    bpm = 128;
                    endBar = 170;
                    // reset method
                    resetMethod();
                }
                // seventh row
                if (mouseOver(mx, my, 50, 650, 50, 50)) {
                    levelid = 37;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("stray").play();
                    // world misc
                    bpm = 108;
                    endBar = 99;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 650, 50, 50)) {
                    levelid = 38;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("jazz").play();
                    // world misc
                    bpm = 110;
                    endBar = 91;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 650, 50, 50)) {
                    levelid = 39;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("success").play();
                    // world misc
                    bpm = 110;
                    endBar = 140;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 650, 50, 50)) {
                    levelid = 40;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("supernova").play();
                    // world misc
                    bpm = 85;
                    endBar = 110;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 650, 50, 50)) {
                    levelid = 41;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("time").play();
                    // world misc
                    bpm = 170;
                    endBar = 116;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 650, 50, 50)) {
                    levelid = 42;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("marbl").play();
                    // world misc
                    bpm = 128;
                    endBar = 134;
                    // reset method
                    resetMethod();
                }
            }
            if (page == 2) {
                // first row
                if (mouseOver(mx, my, 50, 50, 50, 50)) {
                    levelid = 43;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("dreamer").play();
                    // world misc
                    bpm = 128;
                    endBar = 139;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 50, 50, 50)) {
                    levelid = 44;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("ghost_house").play();
                    // world misc
                    bpm = 130;
                    endBar = 117;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 50, 50, 50)) {
                    levelid = 45;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("jude").play();
                    // world misc
                    bpm = 90;
                    endBar = 57;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 50, 50, 50)) {
                    levelid = 46;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("don't_hold_back").play();
                    // world misc
                    bpm = 116;
                    endBar = 111;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 50, 50, 50)) {
                    levelid = 47;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("ouais_ouais").play();
                    // world misc
                    bpm = 120;
                    endBar = 116;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 50, 50, 50)) {
                    levelid = 48;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("things_that_you_do").play();
                    // world misc
                    bpm = 120;
                    endBar = 138;
                    // reset method
                    resetMethod();
                }
                // second row
                if (mouseOver(mx, my, 50, 150, 50, 50)) {
                    levelid = 49;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("sad_summer").play();
                    // world misc
                    bpm = 75;
                    endBar = 49;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 150, 50, 50)) {
                    levelid = 50;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("wishing").play();
                    // world misc
                    bpm = 74;
                    endBar = 48;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 150, 50, 50)) {
                    levelid = 51;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("popo").play();
                    // world misc
                    bpm = 79;
                    endBar = 61;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 150, 50, 50)) {
                    levelid = 52;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("ocean_biome").play();
                    // world misc
                    bpm = 140;
                    endBar = 120;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 150, 50, 50)) {
                    levelid = 53;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("snowcone").play();
                    // world misc
                    bpm = 87;
                    endBar = 90;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 150, 50, 50)) {
                    levelid = 54;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("sawdust_angels").play();
                    // world misc
                    bpm = 174;
                    endBar = 142;
                    // reset method
                    resetMethod();
                }
                // third row
                if (mouseOver(mx, my, 50, 250, 50, 50)) {
                    levelid = 55;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("sky_high").play();
                    // world misc
                    bpm = 128;
                    endBar = 120;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 250, 50, 50)) {
                    levelid = 56;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("my_heart").play();
                    // world misc
                    bpm = 87;
                    endBar = 98;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 250, 50, 50)) {
                    levelid = 57;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("nekozilla").play();
                    // world misc
                    bpm = 128;
                    endBar = 88;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 250, 50, 50)) {
                    levelid = 58;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("cloud_9").play();
                    // world misc
                    bpm = 128;
                    endBar = 146;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 250, 50, 50)) {
                    levelid = 59;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("sunburst").play();
                    // world misc
                    bpm = 128;
                    endBar = 100;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 250, 50, 50)) {
                    levelid = 60;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("hellcat").play();
                    // world misc
                    bpm = 87;
                    endBar = 82;
                    // reset method
                    resetMethod();
                }
                // fourth row
                if (mouseOver(mx, my, 50, 350, 50, 50)) {
                    levelid = 61;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("denise").play();
                    // world misc
                    bpm = 102.42;
                    endBar = 82;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 350, 50, 50)) {
                    levelid = 62;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("dollar_needles_1").play();
                    // world misc
                    bpm = 88;
                    endBar = 53;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 350, 50, 50)) {
                    levelid = 63;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("dollar_needles_3").play();
                    // world misc
                    bpm = 83;
                    endBar = 50;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 350, 50, 50)) {
                    levelid = 64;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("behind_these_closed_doors").play();
                    // world misc
                    bpm = 88;
                    endBar = 48;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 350, 50, 50)) {
                    levelid = 65;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("otis_mcmusic").play();
                    // world misc
                    bpm = 92;
                    endBar = 50;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 350, 50, 50)) {
                    levelid = 66;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("not_for_nothing").play();
                    // world misc
                    bpm = 86.4;
                    endBar = 44;
                    // reset method
                    resetMethod();
                }
                // fifth row
                if (mouseOver(mx, my, 50, 450, 50, 50)) {
                    levelid = 67;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("mind_control").play();
                    // world misc
                    bpm = 129.46;
                    endBar = 140;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 450, 50, 50)) {
                    levelid = 68;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("memories").play();
                    // world misc
                    bpm = 128;
                    endBar = 130;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 450, 50, 50)) {
                    levelid = 69;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("2011").play();
                    // world misc
                    bpm = 102.1;
                    endBar = 82;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 450, 50, 50)) {
                    levelid = 70;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("10000").play();
                    // world misc
                    bpm = 100;
                    endBar = 102;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 450, 50, 50)) {
                    levelid = 71;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("merry_christmas").play();
                    // world misc
                    bpm = 200;
                    endBar = 50;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 450, 50, 50)) {
                    levelid = 72;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("crab_rave").play();
                    // world misc
                    bpm = 125;
                    endBar = 82;
                    // reset method
                    resetMethod();
                }
                // sixth row
                if (mouseOver(mx, my, 50, 550, 50, 50)) {
                    levelid = 73;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("insurgent").play();
                    // world misc
                    bpm = 140;
                    endBar = 128;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 550, 50, 50)) {
                    levelid = 74;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("late_night_lo-fi").play();
                    // world misc
                    bpm = 128;
                    endBar = 98;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 550, 50, 50)) {
                    levelid = 75;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("lith_harbor").play();
                    // world misc
                    bpm = 127;
                    endBar = 79;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 550, 50, 50)) {
                    levelid = 76;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("lost_in_thought").play();
                    // world misc
                    bpm = 90;
                    endBar = 31;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 550, 50, 50)) {
                    levelid = 77;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("holystone").play();
                    // world misc
                    bpm = 110;
                    endBar = 186;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 550, 50, 50)) {
                    levelid = 78;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("pounce").play();
                    // world misc
                    bpm = 110;
                    endBar = 76;
                    // reset method
                    resetMethod();
                }
                // seventh row
                if (mouseOver(mx, my, 50, 650, 50, 50)) {
                    levelid = 79;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("dick").play();
                    // world misc
                    bpm = 121;
                    endBar = 59;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 650, 50, 50)) {
                    levelid = 80;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("golden_haze").play();
                    // world misc
                    bpm = 140;
                    endBar = 90;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 650, 50, 50)) {
                    levelid = 81;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("grim_reaper").play();
                    // world misc
                    bpm = 90;
                    endBar = 46;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 650, 50, 50)) {
                    levelid = 82;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("highscore").play();
                    // world misc
                    bpm = 110;
                    endBar = 118;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 650, 50, 50)) {
                    levelid = 83;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("purity").play();
                    // world misc
                    bpm = 140;
                    endBar = 91;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 650, 50, 50)) {
                    levelid = 84;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("yellow_orange").play();
                    // world misc
                    bpm = 105;
                    endBar = 77;
                    // reset method
                    resetMethod();
                }
            }
            if (page == 3) {
                // first row
                if (mouseOver(mx, my, 50, 50, 50, 50)) {
                    levelid = 85;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("surface").play();
                    // world misc
                    bpm = 85;
                    endBar = 91;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 50, 50, 50)) {
                    levelid = 86;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("nokia_arabic").play();
                    // world misc
                    bpm = 82;
                    endBar = 38;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 50, 50, 50)) {
                    levelid = 87;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("nrg_drink").play();
                    // world misc
                    bpm = 128;
                    endBar = 162;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 50, 50, 50)) {
                    levelid = 88;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("happy").play();
                    // world misc
                    bpm = 195;
                    endBar = 125;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 50, 50, 50)) {
                    levelid = 89;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("moonsugar").play();
                    // world misc
                    bpm = 128;
                    endBar = 140;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 50, 50, 50)) {
                    levelid = 90;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("rubik").play();
                    // world misc
                    bpm = 128;
                    endBar = 109;
                    // reset method
                    resetMethod();
                }
                // second row
                if (mouseOver(mx, my, 50, 150, 50, 50)) {
                    levelid = 91;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("ignition").play();
                    // world misc
                    bpm = 104;
                    endBar = 66;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 150, 50, 50)) {
                    levelid = 92;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("the_force").play();
                    // world misc
                    bpm = 140;
                    endBar = 139;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 150, 50, 50)) {
                    levelid = 93;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("candyland").play();
                    // world misc
                    bpm = 128;
                    endBar = 107;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 150, 50, 50)) {
                    levelid = 94;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("infectious").play();
                    // world misc
                    bpm = 128;
                    endBar = 138;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 150, 50, 50)) {
                    levelid = 95;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("crazy").play();
                    // world misc
                    bpm = 120;
                    endBar = 110;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 150, 50, 50)) {
                    levelid = 96;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("race").play();
                    // world misc
                    bpm = 90;
                    endBar = 65;
                    // reset method
                    resetMethod();
                }
                // third row
                if (mouseOver(mx, my, 50, 250, 50, 50)) {
                    levelid = 97;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("ice_flow").play();
                    // world misc
                    bpm = 140;
                    endBar = 82;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 250, 50, 50)) {
                    levelid = 98;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("kalimba").play();
                    // world misc
                    bpm = 119.7;
                    endBar = 174;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 250, 50, 50)) {
                    levelid = 99;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("party").play();
                    // world misc
                    bpm = 117;
                    endBar = 127;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 250, 50, 50)) {
                    levelid = 100;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("bass").play();
                    // world misc
                    bpm = 160;
                    endBar = 160;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 250, 50, 50)) {
                    levelid = 101;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("hazmat").play();
                    // world misc
                    bpm = 110;
                    endBar = 56;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 250, 50, 50)) {
                    levelid = 102;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("panda_dance").play();
                    // world misc
                    bpm = 128;
                    endBar = 63;
                    // reset method
                    resetMethod();
                }
                // fourth row
                if (mouseOver(mx, my, 50, 350, 50, 50)) {
                    levelid = 103;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("get_up").play();
                    // world misc
                    bpm = 84;
                    endBar = 58;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 350, 50, 50)) {
                    levelid = 104;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("river").play();
                    // world misc
                    bpm = 82;
                    endBar = 30;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 350, 50, 50)) {
                    levelid = 105;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("follow").play();
                    // world misc
                    bpm = 144;
                    endBar = 141;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 350, 50, 50)) {
                    levelid = 106;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("slime").play();
                    // world misc
                    bpm = 115;
                    endBar = 107;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 350, 50, 50)) {
                    levelid = 107;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("euphoria").play();
                    // world misc
                    bpm = 110;
                    endBar = 84;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 350, 50, 50)) {
                    levelid = 108;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("nautilus").play();
                    // world misc
                    bpm = 124;
                    endBar = 167;
                    // reset method
                    resetMethod();
                }
                // fifth row
                if (mouseOver(mx, my, 50, 450, 50, 50)) {
                    levelid = 109;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("liaquo").play();
                    // world misc
                    bpm = 128;
                    endBar = 183;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 450, 50, 50)) {
                    levelid = 110;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("crazy_frog").play();
                    // world misc
                    bpm = 138;
                    endBar = 98;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 450, 50, 50)) {
                    levelid = 111;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("never_lose_hope").play();
                    // world misc
                    bpm = 170;
                    endBar = 125;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 450, 50, 50)) {
                    levelid = 112;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("skystrike").play();
                    // world misc
                    bpm = 128;
                    endBar = 114;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 450, 50, 50)) {
                    levelid = 113;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("starchaser").play();
                    // world misc
                    bpm = 128;
                    endBar = 168;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 450, 50, 50)) {
                    levelid = 114;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("a_newer_dawn").play();
                    // world misc
                    bpm = 105;
                    endBar = 73;
                    // reset method
                    resetMethod();
                }
                // sixth row
                if (mouseOver(mx, my, 50, 550, 50, 50)) {
                    levelid = 115;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("monody").play();
                    // world misc
                    bpm = 107;
                    endBar = 128;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 550, 50, 50)) {
                    levelid = 116;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("unity").play();
                    // world misc
                    bpm = 105;
                    endBar = 105;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 550, 50, 50)) {
                    levelid = 117;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("xenogenesis").play();
                    // world misc
                    bpm = 145;
                    endBar = 140;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 550, 50, 50)) {
                    levelid = 118;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("time_stops").play();
                    // world misc
                    bpm = 160;
                    endBar = 177;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 550, 50, 50)) {
                    levelid = 119;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("badland").play();
                    // world misc
                    bpm = 128;
                    endBar = 154;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 550, 50, 50)) {
                    levelid = 120;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("challenger").play();
                    // world misc
                    bpm = 128;
                    endBar = 94;
                    // reset method
                    resetMethod();
                }
                // seventh row
                if (mouseOver(mx, my, 50, 650, 50, 50)) {
                    levelid = 121;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("bluemoon").play();
                    // world misc
                    bpm = 112.5;
                    endBar = 120;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 650, 50, 50)) {
                    levelid = 122;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("cherry_blossoms").play();
                    // world misc
                    bpm = 138;
                    endBar = 141;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 650, 50, 50)) {
                    levelid = 123;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("walkman").play();
                    // world misc
                    bpm = 128;
                    endBar = 102;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 650, 50, 50)) {
                    levelid = 124;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("beyond_the_walls").play();
                    // world misc
                    bpm = 140;
                    endBar = 148;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 650, 50, 50)) {
                    levelid = 125;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("april").play();
                    // world misc
                    bpm = 118;
                    endBar = 149;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 650, 50, 50)) {
                    levelid = 126;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("sunlight").play();
                    // world misc
                    bpm = 106;
                    endBar = 76;
                    // reset method
                    resetMethod();
                }
            }
            if (page == 4) {
                // first row
                if (mouseOver(mx, my, 50, 50, 50, 50)) {
                    levelid = 127;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("cyptik_dance").play();
                    // world misc
                    bpm = 120;
                    endBar = 50;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 50, 50, 50)) {
                    levelid = 128;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("leaving_leafwood_forest").play();
                    // world misc
                    bpm = 125;
                    endBar = 107;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 50, 50, 50)) {
                    levelid = 129;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("troglodyte").play();
                    // world misc
                    bpm = 134;
                    endBar = 150;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 50, 50, 50)) {
                    levelid = 130;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("mayo").play();
                    // world misc
                    bpm = 135;
                    endBar = 106;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 50, 50, 50)) {
                    levelid = 131;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("starship_showdown").play();
                    // world misc
                    bpm = 147;
                    endBar = 152;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 50, 50, 50)) {
                    levelid = 132;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("kumquat").play();
                    // world misc
                    bpm = 100;
                    endBar = 75;
                    // reset method
                    resetMethod();
                }
                // second row
                if (mouseOver(mx, my, 50, 150, 50, 50)) {
                    levelid = 133;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("jaclyn").play();
                    // world misc
                    bpm = 130;
                    endBar = 133;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 150, 50, 50)) {
                    levelid = 134;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("jacques").play();
                    // world misc
                    bpm = 115;
                    endBar = 69;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 150, 50, 50)) {
                    levelid = 135;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("liftoff").play();
                    // world misc
                    bpm = 140;
                    endBar = 148;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 150, 50, 50)) {
                    levelid = 136;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("never_be_alone").play();
                    // world misc
                    bpm = 132;
                    endBar = 143;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 150, 50, 50)) {
                    levelid = 137;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("solitude").play();
                    // world misc
                    bpm = 138;
                    endBar = 120;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 150, 50, 50)) {
                    levelid = 138;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("close_to_the_sun").play();
                    // world misc
                    bpm = 89;
                    endBar = 71;
                    // reset method
                    resetMethod();
                }
                // third row
                if (mouseOver(mx, my, 50, 250, 50, 50)) {
                    levelid = 139;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("nanamori").play();
                    // world misc
                    bpm = 150;
                    endBar = 122;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 250, 50, 50)) {
                    levelid = 140;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("fury").play();
                    // world misc
                    bpm = 150;
                    endBar = 115;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 250, 50, 50)) {
                    levelid = 141;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("desu_ka").play();
                    // world misc
                    bpm = 140;
                    endBar = 132;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 250, 50, 50)) {
                    levelid = 142;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("voices").play();
                    // world misc
                    bpm = 75;
                    endBar = 85;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 250, 50, 50)) {
                    levelid = 143;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("dancing").play();
                    // world misc
                    bpm = 75;
                    endBar = 59;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 250, 50, 50)) {
                    levelid = 144;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("shining_space").play();
                    // world misc
                    bpm = 150;
                    endBar = 116;
                    // reset method
                    resetMethod();
                }
                // fourth row
                if (mouseOver(mx, my, 50, 350, 50, 50)) {
                    levelid = 145;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("space_invaders").play();
                    // world misc
                    bpm = 128;
                    endBar = 188;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 350, 50, 50)) {
                    levelid = 146;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("drippy_dub").play();
                    // world misc
                    bpm = 150;
                    endBar = 160;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 350, 50, 50)) {
                    levelid = 147;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("fake_princess").play();
                    // world misc
                    bpm = 88;
                    endBar = 128;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 350, 50, 50)) {
                    levelid = 148;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("lazergun").play();
                    // world misc
                    bpm = 128;
                    endBar = 116;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 350, 50, 50)) {
                    levelid = 149;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("afterimage").play();
                    // world misc
                    bpm = 75;
                    endBar = 42;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 350, 50, 50)) {
                    levelid = 150;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("star_wars_remix").play();
                    // world misc
                    bpm = 71.5;
                    endBar = 56;
                    // reset method
                    resetMethod();
                }
                // fifth row
                if (mouseOver(mx, my, 50, 450, 50, 50)) {
                    levelid = 151;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("kiss_me_kiss_you").play();
                    // world misc
                    bpm = 128;
                    endBar = 98;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 450, 50, 50)) {
                    levelid = 152;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("enigma").play();
                    // world misc
                    bpm = 125;
                    endBar = 123;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 450, 50, 50)) {
                    levelid = 153;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("never_make_it").play();
                    // world misc
                    bpm = 114;
                    endBar = 111;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 450, 50, 50)) {
                    levelid = 154;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("flight").play();
                    // world misc
                    bpm = 180;
                    endBar = 204;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 450, 50, 50)) {
                    levelid = 155;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("journey").play();
                    // world misc
                    bpm = 130;
                    endBar = 114;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 450, 50, 50)) {
                    levelid = 156;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("lonley_forest").play();
                    // world misc
                    bpm = 125;
                    endBar = 108;
                    // reset method
                    resetMethod();
                }
                // sixth row
                if (mouseOver(mx, my, 50, 550, 50, 50)) {
                    levelid = 157;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("pirate").play();
                    // world misc
                    bpm = 150;
                    endBar = 130;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 550, 50, 50)) {
                    levelid = 158;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("aquamarine").play();
                    // world misc
                    bpm = 128;
                    endBar = 128;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 550, 50, 50)) {
                    levelid = 159;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("boombox").play();
                    // world misc
                    bpm = 128;
                    endBar = 153;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 550, 50, 50)) {
                    levelid = 160;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("niconico_dreams").play();
                    // world misc
                    bpm = 75;
                    endBar = 48;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 550, 50, 50)) {
                    levelid = 161;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("requiem_dream").play();
                    // world misc
                    bpm = 120;
                    endBar = 88;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 550, 50, 50)) {
                    levelid = 162;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("eurodancer").play();
                    // world misc
                    bpm = 150;
                    endBar = 80;
                    // reset method
                    resetMethod();
                }
                // seventh row
                if (mouseOver(mx, my, 50, 650, 50, 50)) {
                    levelid = 163;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("hello").play();
                    // world misc
                    bpm = 105;
                    endBar = 100;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 650, 50, 50)) {
                    levelid = 164;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("mayday").play();
                    // world misc
                    bpm = 150;
                    endBar = 154;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 650, 50, 50)) {
                    levelid = 165;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("fireflies_remix").play();
//                    handler.addObject(new osc_(0, 0, ID.NULL));
                    // world misc
                    bpm = 87;
                    endBar = 124;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 650, 50, 50)) {
                    levelid = 166;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("echolands").play();
                    // world misc
                    bpm = 140;
                    endBar = 112;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 650, 50, 50)) {
                    levelid = 167;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("voiceless").play();
                    // world misc
                    bpm = 128;
                    endBar = 139;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 650, 50, 50)) {
                    levelid = 168;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("vaporwave_aesthetics").play();
                    // world misc
                    bpm = 160;
                    endBar = 179;
                    // reset method
                    resetMethod();
                }
            }
            if (page == 5) {
                // first row
                if (mouseOver(mx, my, 50, 50, 50, 50)) {
                    levelid = 169;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("happy_troll").play();
                    // world misc
                    bpm = 150;
                    endBar = 120;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 50, 50, 50)) {
                    levelid = 170;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("dimension").play();
                    // world misc
                    bpm = 115;
                    endBar = 91;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 50, 50, 50)) {
                    levelid = 171;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("crystal_caves").play();
                    // world misc
                    bpm = 110;
                    endBar = 78;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 50, 50, 50)) {
                    levelid = 172;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("elevate").play();
                    // world misc
                    bpm = 87;
                    endBar = 84;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 50, 50, 50)) {
                    levelid = 173;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("okiba").play();
                    // world misc
                    bpm = 100;
                    endBar = 109;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 50, 50, 50)) {
                    levelid = 174;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("falling_mysts").play();
                    // world misc
                    bpm = 132;
                    endBar = 161;
                    // reset method
                    resetMethod();
                }
                // second row
                if (mouseOver(mx, my, 50, 150, 50, 50)) {
                    levelid = 175;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("newgrounds_return").play();
                    // world misc
                    bpm = 97;
                    endBar = 31;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 150, 50, 50)) {
                    levelid = 176;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("body_jammer").play();
                    // world misc
                    bpm = 140;
                    endBar = 153;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 150, 50, 50)) {
                    levelid = 177;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("flirt").play();
                    // world misc
                    bpm = 168;
                    endBar = 148;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 150, 50, 50)) {
                    levelid = 178;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("retry").play();
                    // world misc
                    bpm = 111.11;
                    endBar = 68;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 150, 50, 50)) {
                    levelid = 179;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("jet_set").play();
                    // world misc
                    bpm = 124;
                    endBar = 144;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 150, 50, 50)) {
                    levelid = 180;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("the_calling").play();
                    // world misc
                    bpm = 110;
                    endBar = 108;
                    // reset method
                    resetMethod();
                }
                // third row
                if (mouseOver(mx, my, 50, 250, 50, 50)) {
                    levelid = 181;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("tria").play();
                    // world misc
                    bpm = 130;
                    endBar = 134;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 250, 50, 50)) {
                    levelid = 182;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("endgame").play();
                    // world misc
                    bpm = 123;
                    endBar = 72;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 250, 50, 50)) {
                    levelid = 183;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("night_out").play();
                    // world misc
                    bpm = 122;
                    endBar = 98;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 250, 50, 50)) {
                    levelid = 184;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("april_showers").play();
                    // world misc
                    bpm = 94;
                    endBar = 107;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 250, 50, 50)) {
                    levelid = 185;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("bathtub").play();
                    // world misc
                    bpm = 72.50;
                    endBar = 48;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 250, 50, 50)) {
                    levelid = 186;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("laszlo").play();
                    // world misc
                    bpm = 128;
                    endBar = 174;
                    // reset method
                    resetMethod();
                }
                // fourth row
                if (mouseOver(mx, my, 50, 350, 50, 50)) {
                    levelid = 187;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("force").play();
                    // world misc
                    bpm = 105;
                    endBar = 103;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 350, 50, 50)) {
                    levelid = 188;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("spectre").play();
                    // world misc
                    bpm = 128;
                    endBar = 123;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 350, 50, 50)) {
                    levelid = 189;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("fade").play();
                    // world misc
                    bpm = 90;
                    endBar = 100;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 350, 50, 50)) {
                    levelid = 190;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("bangarang").play();
                    // world misc
                    bpm = 110;
                    endBar = 99;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 350, 50, 50)) {
                    levelid = 191;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("bun_dem").play();
                    // world misc
                    bpm = 140;
                    endBar = 123;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 350, 50, 50)) {
                    levelid = 192;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("sleepyhead").play();
                    // world misc
                    bpm = 125;
                    endBar = 140;
                    // reset method
                    resetMethod();
                }
                // fifth row
                if (mouseOver(mx, my, 50, 450, 50, 50)) {
                    levelid = 193;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("dance_till_you're_dead").play();
                    // world misc
                    bpm = 155;
                    endBar = 50;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 450, 50, 50)) {
                    levelid = 194;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("animals").play();
                    // world misc
                    bpm = 128;
                    endBar = 100;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 450, 50, 50)) {
                    levelid = 195;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("yeah").play();
                    // world misc
                    bpm = 110;
                    endBar = 98;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 450, 50, 50)) {
                    levelid = 196;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("november").play();
                    // world misc
                    bpm = 115;
                    endBar = 107;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 450, 50, 50)) {
                    levelid = 197;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("florescence").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 132;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 450, 50, 50)) {
                    levelid = 198;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("mantis_shrimp_showdown").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 171;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 550, 50, 50)) {
                    levelid = 199;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("holy_war").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 133;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 550, 50, 50)) {
                    levelid = 200;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("massacre").play();
                    // world misc
                    bpm = 144.0;
                    endBar = 128;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 550, 50, 50)) {
                    levelid = 201;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("believe").play();
                    // world misc
                    bpm = 87.0;
                    endBar = 83;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 550, 50, 50)) {
                    levelid = 202;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("karma").play();
                    // world misc
                    bpm = 100.0;
                    endBar = 94;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 550, 50, 50)) {
                    levelid = 203;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("refraction").play();
                    // world misc
                    bpm = 140.0;
                    endBar = 108;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 550, 50, 50)) {
                    levelid = 204;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("glome").play();
                    // world misc
                    bpm = 87.0;
                    endBar = 93;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 650, 50, 50)) {
                    levelid = 205;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("red_roses").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 125;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 650, 50, 50)) {
                    levelid = 206;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("a_little_older").play();
                    // world misc
                    bpm = 90.0;
                    endBar = 68;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 650, 50, 50)) {
                    levelid = 207;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("when_time_tears").play();
                    // world misc
                    bpm = 130.0;
                    endBar = 70;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 650, 50, 50)) {
                    levelid = 208;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("hold_on_to_me").play();
                    // world misc
                    bpm = 115.0;
                    endBar = 70;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 650, 50, 50)) {
                    levelid = 209;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("i'm_still_alive").play();
                    // world misc
                    bpm = 132.0;
                    endBar = 42;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 650, 50, 50)) {
                    levelid = 210;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("nirmiti").play();
                    // world misc
                    bpm = 91.0;
                    endBar = 112;
                    // reset method
                    resetMethod();
                }
            }
            if (page == 6) {
                if (mouseOver(mx, my, 50, 50, 50, 50)) {
                    levelid = 211;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("avast_your_ass").play();
                    // world misc
                    bpm = 160.0;
                    endBar = 94;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 50, 50, 50)) {
                    levelid = 212;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("soulwind").play();
                    // world misc
                    bpm = 140.0;
                    endBar = 187;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 50, 50, 50)) {
                    levelid = 213;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("zenith").play();
                    // world misc
                    bpm = 174.0;
                    endBar = 180;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 50, 50, 50)) {
                    levelid = 214;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("portals").play();
                    // world misc
                    bpm = 100.0;
                    endBar = 125;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 50, 50, 50)) {
                    levelid = 215;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("mellow").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 132;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 50, 50, 50)) {
                    levelid = 216;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("lullaby_remix").play();
                    // world misc
                    bpm = 86.0;
                    endBar = 79;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 150, 50, 50)) {
                    levelid = 217;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("florescentia").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 112;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 150, 50, 50)) {
                    levelid = 218;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("bioluminescent").play();
                    // world misc
                    bpm = 133.0;
                    endBar = 214;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 150, 50, 50)) {
                    levelid = 219;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("let's_bounce").play();
                    // world misc
                    bpm = 140.0;
                    endBar = 108;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 150, 50, 50)) {
                    levelid = 220;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("orbit").play();
                    // world misc
                    bpm = 160.0;
                    endBar = 186;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 150, 50, 50)) {
                    levelid = 221;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("luminous").play();
                    // world misc
                    bpm = 138.0;
                    endBar = 145;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 150, 50, 50)) {
                    levelid = 222;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("mako").play();
                    // world misc
                    bpm = 129.0;
                    endBar = 110;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 250, 50, 50)) {
                    levelid = 223;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("love_talk").play();
                    // world misc
                    bpm = 143.0;
                    endBar = 154;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 250, 50, 50)) {
                    levelid = 224;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("trouble").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 87;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 250, 50, 50)) {
                    levelid = 225;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("peepee").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 78;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 250, 50, 50)) {
                    levelid = 226;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("rose").play();
                    // world misc
                    bpm = 174.0;
                    endBar = 195;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 250, 50, 50)) {
                    levelid = 227;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("fateful_mist").play();
                    // world misc
                    bpm = 140.0;
                    endBar = 111;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 250, 50, 50)) {
                    levelid = 228;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("flashyizz_crazy").play();
                    // world misc
                    bpm = 140.0;
                    endBar = 91;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 350, 50, 50)) {
                    levelid = 229;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("glorious_morning").play();
                    // world misc
                    bpm = 152.0;
                    endBar = 72;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 350, 50, 50)) {
                    levelid = 230;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("flyboy_and_gabbergirl").play();
                    // world misc
                    bpm = 178.0;
                    endBar = 173;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 350, 50, 50)) {
                    levelid = 231;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("battletown").play();
                    // world misc
                    bpm = 157.0;
                    endBar = 124;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 350, 50, 50)) {
                    levelid = 232;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("nanobyte").play();
                    // world misc
                    bpm = 98.0;
                    endBar = 52;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 350, 50, 50)) {
                    levelid = 233;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("fantasy").play();
                    // world misc
                    bpm = 110.0;
                    endBar = 96;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 350, 50, 50)) {
                    levelid = 234;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("artificial").play();
                    // world misc
                    bpm = 87.0;
                    endBar = 95;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 450, 50, 50)) {
                    levelid = 235;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("run_away").play();
                    // world misc
                    bpm = 150.0;
                    endBar = 138;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 450, 50, 50)) {
                    levelid = 236;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("dream").play();
                    // world misc
                    bpm = 125.0;
                    endBar = 99;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 450, 50, 50)) {
                    levelid = 237;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("drained").play();
                    // world misc
                    bpm = 85.0;
                    endBar = 84;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 450, 50, 50)) {
                    levelid = 238;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("just_kidding").play();
                    // world misc
                    bpm = 95.0;
                    endBar = 78;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 450, 50, 50)) {
                    levelid = 239;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("saunter").play();
                    // world misc
                    bpm = 150.0;
                    endBar = 155;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 450, 50, 50)) {
                    levelid = 240;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("help_me_up").play();
                    // world misc
                    bpm = 79.0;
                    endBar = 66;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 550, 50, 50)) {
                    levelid = 241;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("ocean_of_stars").play();
                    // world misc
                    bpm = 171.0;
                    endBar = 181;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 550, 50, 50)) {
                    levelid = 242;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("bash").play();
                    // world misc
                    bpm = 125.0;
                    endBar = 50;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 550, 50, 50)) {
                    levelid = 243;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("whirlwind").play();
                    // world misc
                    bpm = 110.0;
                    endBar = 35;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 550, 50, 50)) {
                    levelid = 244;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("screamroom").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 126;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 550, 50, 50)) {
                    levelid = 245;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("ichor").play();
                    // world misc
                    bpm = 156.0;
                    endBar = 136;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 550, 50, 50)) {
                    levelid = 246;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("time_lapse").play();
                    // world misc
                    bpm = 127.0;
                    endBar = 97;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 650, 50, 50)) {
                    levelid = 247;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("calm").play();
                    // world misc
                    bpm = 97.72;
                    endBar = 119;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 650, 50, 50)) {
                    levelid = 248;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("gloomy").play();
                    // world misc
                    bpm = 128.92;
                    endBar = 128;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 650, 50, 50)) {
                    levelid = 249;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("love's_song").play();
                    // world misc
                    bpm = 140.0;
                    endBar = 117;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 650, 50, 50)) {
                    levelid = 250;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("blast_em").play();
                    // world misc
                    bpm = 75.0;
                    endBar = 60;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 650, 50, 50)) {
                    levelid = 251;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("lost_in_the_rhythm").play();
                    // world misc
                    bpm = 120.0;
                    endBar = 126;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 650, 50, 50)) {
                    levelid = 252;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("zelda_palace").play();
                    // world misc
                    bpm = 75.0;
                    endBar = 71;
                    // reset method
                    resetMethod();
                }
            }
            if (page == 7) {
                if (mouseOver(mx, my, 50, 50, 50, 50)) {
                    levelid = 253;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("form").play();
                    // world misc
                    bpm = 105.0;
                    endBar = 71;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 50, 50, 50)) {
                    levelid = 254;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("paladin").play();
                    // world misc
                    bpm = 130.0;
                    endBar = 129;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 50, 50, 50)) {
                    levelid = 255;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("myriad").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 115;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 50, 50, 50)) {
                    levelid = 256;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("pyrolysis").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 171;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 50, 50, 50)) {
                    levelid = 257;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("elevatia").play();
                    // world misc
                    bpm = 100.0;
                    endBar = 92;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 50, 50, 50)) {
                    levelid = 258;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("prelude_remix").play();
                    // world misc
                    bpm = 77.5;
                    endBar = 80;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 150, 50, 50)) {
                    levelid = 259;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("could_it_be").play();
                    // world misc
                    bpm = 81.0;
                    endBar = 34;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 150, 50, 50)) {
                    levelid = 260;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("spooky").play();
                    // world misc
                    bpm = 90.0;
                    endBar = 29;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 150, 50, 50)) {
                    levelid = 261;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("ena").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 107;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 150, 50, 50)) {
                    levelid = 262;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("reapers").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 117;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 150, 50, 50)) {
                    levelid = 263;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("last_dance").play();
                    // world misc
                    bpm = 175.0;
                    endBar = 151;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 150, 50, 50)) {
                    levelid = 264;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("reminisce").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 84;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 250, 50, 50)) {
                    levelid = 265;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("who_you_are").play();
                    // world misc
                    bpm = 140.0;
                    endBar = 146;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 250, 50, 50)) {
                    levelid = 266;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("stardrive").play();
                    // world misc
                    bpm = 185.0;
                    endBar = 204;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 250, 50, 50)) {
                    levelid = 267;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("level_one").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 113;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 250, 50, 50)) {
                    levelid = 268;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("lights").play();
                    // world misc
                    bpm = 140.0;
                    endBar = 116;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 250, 50, 50)) {
                    levelid = 269;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("skybound").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 228;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 250, 50, 50)) {
                    levelid = 270;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("open_your_eyes").play();
                    // world misc
                    bpm = 140.0;
                    endBar = 35;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 350, 50, 50)) {
                    levelid = 271;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("party_hard_remix").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 197;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 350, 50, 50)) {
                    levelid = 272;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("nice_vibes").play();
                    // world misc
                    bpm = 110.0;
                    endBar = 104;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 350, 50, 50)) {
                    levelid = 273;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("horizons_remix").play();
                    // world misc
                    bpm = 130.0;
                    endBar = 220;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 350, 50, 50)) {
                    levelid = 274;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("ablixa").play();
                    // world misc
                    bpm = 122.0;
                    endBar = 129;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 350, 50, 50)) {
                    levelid = 275;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("untitled").play();
                    // world misc
                    bpm = 111.0;
                    endBar = 75;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 350, 50, 50)) {
                    levelid = 276;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("litoff").play();
                    // world misc
                    bpm = 140.0;
                    endBar = 148;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 450, 50, 50)) {
                    levelid = 277;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("nuetronium").play();
                    // world misc
                    bpm = 140.0;
                    endBar = 182;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 450, 50, 50)) {
                    levelid = 278;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("sky_venture").play();
                    // world misc
                    bpm = 125.0;
                    endBar = 40;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 450, 50, 50)) {
                    levelid = 279;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("sad_machine_remix").play();
                    // world misc
                    bpm = 177.0;
                    endBar = 244;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 450, 50, 50)) {
                    levelid = 280;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("jazz_jackrabbit_remix").play();
                    // world misc
                    bpm = 100.0;
                    endBar = 81;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 450, 50, 50)) {
                    levelid = 281;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("shadow_queen_part_2_remix").play();
                    // world misc
                    bpm = 140.0;
                    endBar = 135;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 450, 50, 50)) {
                    levelid = 282;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("let's_stomp").play();
                    // world misc
                    bpm = 128.0;
                    endBar = 133;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 550, 50, 50)) {
                    levelid = 283;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("twinrova").play();
                    // world misc
                    bpm = 110.0;
                    endBar = 88;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 550, 50, 50)) {
                    levelid = 284;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("our_home").play();
                    // world misc
                    bpm = 105.0;
                    endBar = 100;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 550, 50, 50)) {
                    levelid = 285;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("geometry_dance").play();
                    // world misc
                    bpm = 112.0;
                    endBar = 71;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 550, 50, 50)) {
                    levelid = 286;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("this_is_geometry_dash").play();
                    // world misc
                    bpm = 87.0;
                    endBar = 82;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 550, 50, 50)) {
                    levelid = 287;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("waves").play();
                    // world misc
                    bpm = 100.0;
                    endBar = 100;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 550, 50, 50)) {
                    levelid = 288;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("among_us_trap").play();
                    // world misc
                    bpm = 94.0;
                    endBar = 31;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 50, 650, 50, 50)) {
                    levelid = 289;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("legend").play();
                    // world misc
                    bpm = 87.5;
                    endBar = 94;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 150, 650, 50, 50)) {
                    levelid = 290;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("this_girl").play();
                    // world misc
                    bpm = 122.0;
                    endBar = 125;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 250, 650, 50, 50)) {
                    levelid = 291;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("sunwalker").play();
                    // world misc
                    bpm = 113.0;
                    endBar = 113;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 350, 650, 50, 50)) {
                    levelid = 292;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("afterglow").play();
                    // world misc
                    bpm = 175.0;
                    endBar = 220;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 450, 650, 50, 50)) {
                    levelid = 293;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("a_cow_that_is_actually_a_chicken").play();
                    // world misc
                    bpm = 90.0;
                    endBar = 100;
                    // reset method
                    resetMethod();
                }
                if (mouseOver(mx, my, 550, 650, 50, 50)) {
                    levelid = 294;
                    System.out.println("world " + levelid);
                    if (game_.music) audioplayer_.getMusic("ストーカー").play();
                    // world misc
                    bpm = 110.0;
                    endBar = 107;
                    // reset method
                    resetMethod();
                }
            }
        }
    }

    // this shit doesn't work, why? edit: now it works but unexpected happened.
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (!game.isSelecting) hoverOnLevels(mx, my);
    }

    public void mouseReleased(MouseEvent e) {
        // spam fix
        if (lazyDelayFix == 0) {
            if (game.gameState == STATE.LevelSelect) {
                int mx = e.getX();
                int my = e.getY();
                // prev button
                if (page > 1) {
                    if (mouseOver(mx, my, 16, 366, 20, 20)) {
                        page--;
                        if (game_.sfx) audioplayer_.getSound("click_sound").play();
                    }
                }
                // next button
                if (page < maxPage) {
                    if (mouseOver(mx, my, 616, 366, 20, 20)) {
                        page++;
                        if (game_.sfx) audioplayer_.getSound("click_sound").play();
                    }
                }
                // grid
                for (int x = 50; x <= 550; x += 100) {
                    for (int y = 50; y <= 650; y += 100) {
                        if (x < mx && mx % 100 > 50) {
                            if (y < my && my % 100 > 50) {
                                if (game_.sfx) audioplayer_.getSound("click_sound").play();
                            }
                        }
                    }
                }
                levelsList(mx, my);
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (game.gameState == STATE.LevelSelect) {
            if (!game.isSelecting) {
                switch (key) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        game.isSelecting = true;
                        handler.addObject(new CURSOR_SELECT(gridSelectX + 10, gridSelectY + 10, ID.CURSORSELECT));
                }
            }
            else {
                if (key == KeyEvent.VK_RIGHT) {
                    // if at prev
                    if (gridSelectX == 0)
                        gridSelectX = 50;
                    // main
                    else if (gridSelectX < 550)
                        gridSelectX += 100;
                    // if you want to go to next
                    else if (page < maxPage) {
                        gridSelectX = 600;
                        gridSelectY = 350;
                    }
                }
                if (key == KeyEvent.VK_LEFT) {
                    // if at next
                    if (gridSelectX == 600)
                        gridSelectX -= 50;
                    // main
                    else if (gridSelectX >= 150)
                        gridSelectX -= 100;
                    // if you want to go to prev
                    else if (page > 1) {
                        gridSelectX = 0;
                        gridSelectY = 350;
                    }
                }
                if (key == KeyEvent.VK_UP) {
                    if (!(gridSelectX == 0 && gridSelectY == 350)) {
                        if (!(gridSelectX == 600 && gridSelectY == 350)) {
                            if (gridSelectY >= 150)
                                gridSelectY -= 100;
                        }
                    }
                }
                if (key == KeyEvent.VK_DOWN) {
                    if (!(gridSelectX == 0 && gridSelectY == 350)) {
                        if (!(gridSelectX == 600 && gridSelectY == 350)) {
                            if (gridSelectY < 650)
                                gridSelectY += 100;
                        }
                    }
                }
                handler.removeAllSelectedObjects(ID.CURSORSELECT);
                handler.addObject(new CURSOR_SELECT(gridSelectX + 10, gridSelectY + 10, ID.CURSORSELECT));
                // run level
                if (key == KeyEvent.VK_ENTER) {
                    if (game_.sfx) audioplayer_.getSound("click_sound").play();
                    if (gridSelectX == 0 && gridSelectY == 350 && page > 1){
                        page--;
                    } else if (gridSelectX == 600 && gridSelectY == 350 && page < maxPage){
                        page++;
                    } else {
                        gameobject_ tempObject = handler.getObject(ID.CURSORSELECT);
                        for (int x = 60; x <= 560 && tempObject != null; x += 100) {
                            for (int y = 60; y <= 660 && tempObject != null; y += 100) {
                                game.isSelecting = false;
                                if (tempObject.getBounds().intersects(x, y, 32, 32)) {
                                    levelsList(x, y);
                                }
                            }
                        }
                        handler.removeAllSelectedObjects(ID.CURSORSELECT);
                    }
                }
            }
        }
    }

    // vars for gameloop fix
    public long lastTime = System.nanoTime();
    double amountOfTicks = 100.0;
    double ns = 1000000000 / amountOfTicks;
    public double delta = 0;

    // test for player
    // public static boolean isPlaying = false;

    public void tick() {
        if (lazyDelayFix != 0) lazyDelayFix--;

        // hover
        if (game.isSelecting && game.gameState == STATE.LevelSelect)
            hoverOnLevels(gridSelectX + 30, gridSelectY + 30);

        // THE GODDAMN GAMELOOP WORKS! still has issues at starting and pausing the tick
        // edit: starting a level works now so well, see gameloop fix at reset method, but pausing is still an issue
        // edit2: pausing now fixed, see gameloop fix at KeyInput VK_P
        // edit3: if you want to implement this, you have to fix restart and pause
        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;

        while (delta >= 1) {
            delta--; // don't forget this
            if (game.gameState == STATE.GameBeta/* && isPlaying*/) {
                levelTick++;
                // THE LOGIC THAT MADE ME INSANE!, pls fix this if gameloop is noob
                // edit: see comments above about gameloop
                tpm = (60000 / bpm) / 10;
                spm = tpm * 4 / 16;

                if (levelid == 1) {
                    // spawn code
                    scoreKeep++;
                    scoreKeepStep++;
                    // beats
                    if (scoreKeep >= tpm) {
                        handler.metronomeCode();
                        difference = scoreKeep - tpm;
                        scoreKeep = difference;

                        if (handler.total_bars >= 2 && handler.total_bars != 25 && handler.total_bars != 42 && handler.total_bars != 43) {
                            handler.addObject(new laserpointer_(r.nextInt(game_.WIDTH), 1, ID.Laser, handler, 30, 30, 0));
                        }

                        if (handler.total_bars >= 10 && handler.total_bars != 25 && handler.total_bars != 42 && handler.total_bars != 43) {
                            handler.addObject(new heart_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                                    ID.HeartFriend, handler, 0, 0));
                            handler.addObject(new basecircle_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                                    ID.BaseCircle, handler, 0, 0, 0));
                        }

                        if (handler.total_beats == 69 /*nice*/) {
                            handler.addObject(new ghost_(r.nextInt(game_.WIDTH - 1), r.nextInt(game_.HEIGHT - 1), ID.GHOST, 1, 1));
                            handler.addObject(new starwrathenemy_(0, 0, ID.STARGHOST, handler, 30, 30, 0));
                        }

                        if (handler.total_beats == 97) handler.removeObjectsExceptPlayers();

                        if (handler.total_bars == 25) {
                            crazyboss_.customRotateByTick = false;
                            // animation crazy boss
                            // frame one
                            if (handler.fourbarticks == 1) {
                                System.out.println("frame one");
                                handler.addObject(new crazyboss_(-128, 0, ID.CrazyBoss, handler, handler.goRight, handler.stay, 0));
                            }
                            // frame two
                            if (handler.fourbarticks == 2) {
                                System.out.println("frame two");
                                handler.removeAllSelectedObjects(ID.CrazyBoss);
                                handler.addObject(new crazyboss_(game.WIDTH, 0, ID.CrazyBoss, handler, handler.goLeft, handler.stay, 0));
                            }
                            // frame three
                            if (handler.fourbarticks == 3) {
                                System.out.println("frame three");
                                handler.removeAllSelectedObjects(ID.CrazyBoss);
                                handler.addObject(new crazyboss_(game.WIDTH / 2 - 128, -128, ID.CrazyBoss, handler, handler.stay, handler.goDown, 0));
                            }
                            // frame four
                            if (handler.fourbarticks == 4) {
                                System.out.println("frame four");
                                handler.removeAllSelectedObjects(ID.CrazyBoss);
                                handler.addObject(new crazyboss_(game.WIDTH / 2 - 128, game_.HEIGHT, ID.CrazyBoss, handler, handler.stay, handler.goUp, 0));
                            }
                        }

                        if (handler.total_beats == 101) {
                            handler.removeAllSelectedObjects(ID.CrazyBoss);
                            handler.addObject(new crazyboss_(game_.WIDTH / 2 - 128, 0, ID.CrazyBoss, handler, 0, 0, 0));
                        }

                        if (handler.total_beats >= 101) {
                            // run this code once
                            crazyboss_.customRotateByTick = true;
                            crazyboss_.minRotate = -45;
                            crazyboss_.maxRotate = 45;
                            crazyboss_.rotateThisTick = 20;
                            // run this code once the tick was called
                            if (crazyboss_.increment) crazyboss_.increment = false;
                            else crazyboss_.increment = true;
                        }

                        if (handler.total_bars == 42 || handler.total_bars == 43) {
                            // rest
                            handler.removeObjectsExceptPlayers();
                        }
                    }
                    // steps
                    if (scoreKeepStep >= spm) {
                        handler.stepsBeta();
                        stepDifference = scoreKeepStep - spm;
                        scoreKeepStep = stepDifference;
                    }
                    // end code
                    winCode();
                }
                if (levelid == 2) {
                    // spawn code
                    scoreKeep++;
                    scoreKeepStep++;
                    // beats
                    if (scoreKeep >= tpm) {
                        handler.metronomeCode();
                        difference = scoreKeep - tpm;
                        scoreKeep = difference;
                        handler.addObject(new heart_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                                ID.HeartFriend, handler, 0, 0));
                        handler.addObject(new laserpointer_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                                ID.Laser, handler, 30, 30, 0));
                    }
                    // steps
                    if (scoreKeepStep >= spm) {
                        handler.stepsBeta();
                        stepDifference = scoreKeepStep - spm;
                        scoreKeepStep = stepDifference;
                    }
                    // end code
                    winCode();
                }
                // new code
                if (levelid >= 3) {
                    // spawn code
                    scoreKeep++;
                    scoreKeepStep++;
                    // beats
                    if (scoreKeep >= tpm) {
                        handler.metronomeCode();
                        difference = scoreKeep - tpm;
                        scoreKeep = difference;
                        handler.addObject(new heart_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                                ID.HeartFriend, handler, 0, 0));
                        handler.addObject(new basecircle_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                                ID.BaseCircle, handler, 0, 0, 0));
                    }
                    // steps
                    if (scoreKeepStep >= spm) {
                        handler.stepsBeta();
                        stepDifference = scoreKeepStep - spm;
                        scoreKeepStep = stepDifference;
                    }
                    // end code
                    winCode();
                }
            }
        }
        // duration bar
        durationBar = ((double) handler.total_steps / ((double) endBar * 16 - 15)) * 100;
        durationValue = (int) durationBar * 2;
        durationValue = MathUtil.clamp(durationValue, 0, 255);
    }
    public void render(Graphics g) {
        if (game.gameState == STATE.LevelSelect) {
            g.setColor(Color.green);
            g.drawString(testString, 0, 40);
            g.drawString("Page: " + page, game.WIDTH - 70, 38);
            // prev and next
            Polygon trianglePrev = new Polygon(), triangleNext = new Polygon();

            trianglePrev.xpoints = new int[]{616, 616 + 20, 616};
            trianglePrev.ypoints = new int[]{366, 366 + 10, 366 + 20};
            trianglePrev.npoints = 3;

            triangleNext.xpoints = new int[]{16 + 20, 16, 16 + 20};
            triangleNext.ypoints = new int[]{366, 366 + 10, 366 + 20};
            triangleNext.npoints = 3;

            if (page > 1) {
//                g.drawRect(16, 366, 20, 20);
                g.drawPolygon(triangleNext);
            }
            if (page < maxPage) {
//                g.drawRect(616, 366, 20, 20);
                g.drawPolygon(trianglePrev);
            }
            // numbers
            int number = 1;
            if (page == 2) number = 43;
            if (page == 3) number = 85;
            if (page == 4) number = 127;
            if (page == 5) number = 169;
            if (page == 6) number = 211;
            if (page == 7) number = 253;

            for (int yCoord = 50; yCoord <= 650; yCoord += 100){
                for (int xCoord = 50; xCoord <= 550; xCoord += 100){
                    // bound box
                    Rectangle boxBounds = new Rectangle(xCoord, yCoord, 50, 50);
                    g.drawRect(boxBounds.x, boxBounds.y, boxBounds.width, boxBounds.height);
                    // center
                    double widthFix;
                    widthFix = (25) - (game.getTextWidth(g, String.valueOf(number))/2);
                    // number
                    g.drawString(String.valueOf(number), (int)(xCoord + widthFix), yCoord + 30);
                    number++;
                }
            }
        }
        // my monstrosity
        if (game.gameState == STATE.GameBeta && !game.hideHud) {
            // offset is 16
            g.drawString("Level ID: " + levelid + ", BPm: " + bpm + ", End (Bar): " + endBar,
                    game.WIDTH - 300, 15);
            g.drawString("BPm: " + (float)(60000/(tpm * 10)) + ", Bar: " + handler.total_bars + ", Beats: " + handler.total_beats + ", Beat: " + handler.fourbarticks,
                    game.WIDTH - 300, 64);
            g.drawString("Difference (Beats): " + difference,
                    game.WIDTH - 300, 80);
            g.drawString("SPm: " + (float)(60000/(spm * 10)) + ", Bar: " + handler.total_bars_steps + ", Steps: " + handler.total_steps + ", Step: " + handler.fourbarsteps,
                    game.WIDTH - 300, 96);
            g.drawString("Difference (Steps): " + stepDifference,
                    game.WIDTH - 300, 112);
            // sync this with crappy watch
            // beta audio engine info
            /*g.drawString("Time: " + osc_.audioTime + ", Progress: " + osc_.audioProgress,
                    game.WIDTH - 300, 128);
            g.drawString("Time: " + hud.tellTime(),
                    game.WIDTH - 300, 144);*/
            // slick2d
            if (game_.music)
                g.drawString("Elapsed: " + musicTime(audioplayer_.getMusic(audioplayer_.currentMusic).getPosition()) +
                                ", Total: " + trimCenti(),
                        game.WIDTH - 300, 128);
            else
                g.drawString("Total: " + trimCenti(),
                        game.WIDTH - 300, 128);
            // xt audio stuff
            /*g.drawString("BYTES (" + BYTES.length + "): " + Arrays.toString(BYTES).,
                    game.WIDTH - 300, 144);
            g.drawString("bytesToFloats (" + bytesToFloats(BYTES).length + "): " + Arrays.toString(bytesToFloats(BYTES)),
                    game.WIDTH - 300, 160);*/

            /*g.drawString("???: " + (hud.score/100) + "|" + (hud.hudTick/100) + "|" + (levelTick/100),
                    game.WIDTH - 300, 128);*/
            // duration bar
            g.setColor(new Color(75, durationValue, 0));
            g.fillRect(game.WIDTH - 300, 18, (int) durationBar * 2, 32);
            g.setColor(Color.green);
            g.drawRect(game.WIDTH - 300, 18, 200, 32);
            g.drawString((int)durationBar + "%", game.WIDTH - 64, 38);
        }
    }

    public String musicTime(float var){
        try {
            int centi = (int)(var * 100) % 100, sec = (int)(var % 60), min = (int)(var / 60);
            String tempCenti, tempSec, tempMin;

            if (sec < 10) tempSec = "0" + sec;
            else tempSec = String.valueOf(sec);

            if (min < 10) tempMin = "0" + min;
            else tempMin = String.valueOf(min);

            if (centi < 10) tempCenti = "0" + centi;
            else tempCenti = String.valueOf(centi);

            return tempMin + ":" + tempSec + ":" + tempCenti;
        } catch (Exception e){
            e.printStackTrace();
        }
        return "null";
    }

    public String trimCenti(){
        String var = musicTime((60000f/(float)levels_.bpm/1000f) * ((float)levels_.endBar * 4 - 3));
        return var.substring(0, var.length() - 3);
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void resetMethod() {
        // reset
        game.gameState = STATE.GameBeta;
        hud.resetTimer();
        handler.clearEnemies();
        hud.setLevel(1);
        scoreKeep = 0;
        scoreKeepStep = 0;

        // p1 reset
        hud.setScore(0);
        hud.setXp(0);
        hud_.HEALTH = 100;
        handler_.spdp1 = 5;
        hud_.bounds = 0;
        hud.heartsTaken = 0;

        // p2 reset
        hud2.setScore(0);
        hud2.setXp(0);
        hud2_.HEALTH = 100;
        handler_.spdp2 = 5;
        hud2_.bounds = 0;
        hud2.heartsTaken = 0;

        // reset music
        handler.total_bars = 1;
        handler.total_beats = 1;
        handler.fourbarticks = 1;

        handler.total_bars_steps = 1;
        handler.total_steps = 1;
        handler.fourbarsteps = 1;

        // gameloop fix
        game.gameloopFix(); // gameloop fix

        // for test purposes
        levelTick = 0;
        hud.hudTick = 0;
        // add something here
        handler.addObject(new portalblue_(r.nextInt(game.WIDTH-64), r.nextInt(game.HEIGHT-64), ID.PortalBlue,
                handler,0, 0));
        handler.addObject(new portalred_(r.nextInt(game.WIDTH-64), r.nextInt(game.HEIGHT-64), ID.PortalRed,
                handler,0, 0));

    }
    public int levelTick = 0; // responsible for time

    private void winCode() {
        // end code
        if (handler.total_bars == endBar) {
            if (game_.sfx) audioplayer_.getSound("win_1").play();
            if (game_.music) audioplayer_.getMusic(audioplayer_.currentMusic).pause();
            game.gameState = STATE.End;
            // modified endCode for beta gameplay
            handler.clearEnemies();
            if (game.customTicksBoolean) game.customTicksMethod();
            else {
                for (int i = 1; i <= 50; i++)
                    handler.addObject(new spicymenu_(r.nextInt(game.WIDTH - 20), r.nextInt(game.HEIGHT - 20), ID.Spicy, handler));
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (game.gameState == STATE.GameBeta){
            System.out.println("action triggered");
        }
    }
}
