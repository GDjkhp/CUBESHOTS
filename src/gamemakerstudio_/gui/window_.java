/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.gui;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.audiostuff.xtaudio.XtAudio;
import gamemakerstudio_.misc.entitystuff.handler_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
/**
 *
 * @author ACER
 */
public class window_ extends WindowAdapter implements KeyListener {
    boolean STIOPTHECRINGE = false;

    handler_ handler;
    game_ game;

    static Random r = new Random();

    int randInt;
    boolean obfuscated = false;
    int obfuscatedChance = r.nextInt(100) + 1;

    public static JFrame frame = new JFrame();

    // hide cursor

    // Transparent 16 x 16 pixel cursor image.
    // BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

    // Create a new blank cursor.
    // Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
    //         cursorImg, new Point(0, 0), "blank cursor");

    public window_ (int width, int height, String title, game_ game, handler_ handler) {
        // splash cringe
        if (STIOPTHECRINGE) {
            randInt = /*112*/354;
            obfuscatedChance = 2;
        }
        else randInt = randIntFormula;

        // blank
        // frame.getContentPane().setCursor(blankCursor);
        // default
        // frame.getContentPane().setCursor(Cursor.getDefaultCursor());

        if (obfuscatedChance == 1) obfuscated = true;

        this.handler = handler;

        game.addKeyListener(this);

        // window shenanigans
        ImageIcon icon = new ImageIcon("resources_/image_/icon remastered.png");

        frame.addWindowListener(this);
        
        frame.setPreferredSize(new Dimension(width + 16, height + 39));
        frame.setMaximumSize(new Dimension(width + 16, height + 39));
        frame.setMinimumSize(new Dimension(width + 16, height + 39));
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        frame.setIconImage(icon.getImage());

        // moved at game_.java
        // game.start();
    }
    public void windowClosing(WindowEvent e) {
        if (game_.JOptionPaneOption) {
            int a = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Quit", JOptionPane.INFORMATION_MESSAGE);
            if (a == JOptionPane.YES_OPTION) frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public void tick() {
        // before anything else
        timerForXtAudioThread();

        randIntFormula = r.nextInt(randomLimit) + 1;

        while (randomSplash(randInt).equals(""))
            randInt = r.nextInt(randomLimit) + 1;;

        if (obfuscated) randInt = randIntFormula;
        frame.setTitle("CUBESHOTS: " + randomSplash(randInt));
    }

    // prepare to cringe
    public String randomSplash(int randInt) {
        switch (randInt) {
            case 1:
                return "That's a lot of DAMAGE!";
            case 2:
                return "Squirrels in my Pants";
            case 3:
                return "Dumb Kids Edition";
            case 4:
                return "Say the magic word";
            case 5:
                return "Do you even lift, bro?";
            case 6:
                return "Shotgun King 2077";
            case 7:
                return "Cool Stuff";
            case 8:
                return "NullPointerException";
            case 9:
                return "Controller not included";
            case 10:
                return "Ok boomer";
            case 11:
                return "Sup guys, we did it!";
            case 12:
                return "Getting shot at the back";
            case 13:
                return "Big Bear Theory";
            case 14:
                return "Powered by Java";
            case 15:
                return "609567216262790763"; // PewDiePie's Minecraft Seed
            case 16:
                return "2151901553968352745"; // Minecraft's Title Screen Seed
            case 17:
                return "3257840388504953787"; // pack.png Minecraft Seed
            case 18:
                return "PDFYFCD"; // minecraft seed 0, jump to case 302 for more
            case 19:
                return "wubba lubba dub dub";
            case 20:
                return "Not to be confused with Just Shapes and Beats"; // NOT TO BE CONFUSED
            case 21:
                return "Not to be confused with Touhou Project";
            case 22:
                return "Not to be confused with GALAGA";
            case 23:
                return "matpat's holy number";
            case 24:
                return "Not to be confused with Space Invaders";
            case 25:
                return "gg terraria";
            case 26:
                return "let's port pac-man multiplayer";
            case 27:
                return "copyright public domain";
            case 28:
                return "polar coordinate system";
            case 29:
                return "tu tu tu turn it up!";
            case 30:
                return "sine, cosine, and tangent";
            case 31:
                return "pi, radius, and theta";
            case 32:
                return "rectangular vs. polar";
            case 33:
                return "v in -v- is mouth?";
            case 34:
                return "you have been distracted";
            case 35:
                return "pickle rick!";
            case 36:
                return "outdated"; // Why Is EVERYONE Singing Baka Mitai?
            case 37:
                return "the cake is a lie";
            case 38:
                return "Understandable, Have A Nice Day";
            case 39:
                return "yo, it's him!";
            case 40:
                return "are you winning, son?";
            case 41:
                return "coffin dance";
            case 42:
                return "this game has " + handler.object.size() + " objects!";
            case 43:
                return "a man has fallen into the river in lego city";
            case 44:
                return "[removed]";
            case 45:
                return "joe mama";
            case 46:
                return "stonks";
            case 47:
                return "FBI OPEN UP!";
            case 48:
                return "dogelore"; // doge and cheems and walter
            case 49:
                return "dogelore"; // i like fire trucks and moster trucks
            case 50:
                return "kenos npestaposting";
            case 51:
                return "A E S T H E T I C";
            case 52:
                return "have you send it to robtop yet?";
            case 53:
                return "E";
            case 54:
                return "yo can i get uhhhh, lamp";
            case 55:
                return "sj8Sg8qnjOg"; // the golden ratio
            case 56:
                return "dQw4w9WgXcQ";
            case 57:
                return "UCrHkYaEEXKoK57GAwyC5JTg"; // ytp channel?
            case 58:
                return "The Mandelbrot Set";
            case 59:
                return "fibonacci sequence";
            case 60:
                return "did it work?";
            case 61:
                return "never tell the odds!";
            case 62:
                return "r/gamephysics";
            case 63:
                return "V A P O R W A V E";
            case 64:
                return "this game is running at " + game_.throwFrames + " fps!";
            case 65:
                return "click the ghost and see the magic";
            case 66:
                return "r/woosh";
            case 67:
                return "p2 is green, p1 is blue";
            case 68:
                return "up up down down left right left right b a start";
            case 69:
                return "nice";
            case 70:
                return "espionage";
            case 71:
                return "i built a level";
            case 72:
                return "stinky brainer";
            case 73:
                return "rip windows 7";
            case 74:
                return "rip adobe flash";
            case 75:
                return "also try Geometry Dash";
            case 76:
                return "also try Minecraft";
            case 77:
                return "also try Terraria";
            case 78:
                return "also try Super Meat Boy";
            case 79:
                return "also try Electronic Super Joy";
            case 80:
                return "also try Among Us";
            case 81:
                return "also try Bombsquad";
            case 82:
                return "also try Cuphead";
            case 83:
                return "also try Cave Story";
            case 84:
                return "also try Stick Fight: The Game";
            case 85:
                return "what, you never played tuber simulator?";
            case 86:
                return "hey, that's pretty good";
            case 87:
                return "when there's smoke, there's fire";
            case 88:
                return "the golden ratio";
            case 89:
                return "the pentomino puzzle";
            case 90:
                return "the silver ratio, and how to cut your nails";
            case 91:
                return "Turn Your Face Into a Troll Face!";
            case 92:
                return "deepfaked";
            case 93:
                return "the power";
            case 94:
                return "DO NOT OPEN!";
            case 95:
                return "damn son, where'd you find this?";
            case 96:
                return "java -jar Geyser.jar";
            case 97:
                return "Random r = new Random();";
            case 98:
                return "Telltale Games is Back!";
            case 99:
                return "rip digital chocolate";
            case 100:
                return "making you insane since 2020";
            case 101:
                return "unintelligible";
            case 102:
                return "also try The Henry Stickmin Collection";
            case 103:
                return "there are 4 impostors among us";
            case 104:
                return "rip unnamed rpg game";
            case 105:
                return "poorgrammer for $25";
            case 106:
                return "what the hell was a git/vcs?";
            case 107:
                return "github.com/GDjkhp/CUBESHOTS";
            case 108:
                return "terraria otherworld confirmed?";
            case 109:
                return "thanks for a free copy of minecraft windows 10 edition!";
            case 110:
                return "UwU";
            case 111:
                return "wanted: PROgrammer";
            case 112:
                return String.valueOf(game_.gameState);
            case 113:
                return "[YTP]";
            case 114:
                return "opie, boogie, kaki, edgar, maya";
            case 115:
                return String.valueOf(randIntFormula);
            case 116:
                return "Dank Room Soup.avi";
            case 117:
                return "includes anti-piracy measures, although it's free";
            case 118:
                return "-_-";
            case 119:
                return "VR Edition";
            case 120:
                return "morty manipulator chip included";
            case 121:
                return "¯\\_(ツ)_/¯";
            case 122:
                return "( ͡° ͜ʖ ͡°)";
            case 123:
                return "are dragons reptiles?";
            case 124:
                return "loops vs. recursion";
            case 125:
                return "he was like a father to me";
            case 126:
                return "pufferfish eating carrot";
            case 127:
                return "i like turtles";
            case 128:
                return "can't believe you done this";
            case 129:
                return "don't kill monsters, you dolphin!";
            case 130:
                return "place a block above the turtle eggs to prevent it from stepping by someone. pls help me";
            case 131:
                return "newton's 4th law";
            case 132:
                return "when will the bass drop";
            case 133:
                return "segmentation fault";
            case 134:
                return "???";
            case 135:
                return "i'll have two number nines";
            case 136:
                return "the waiting game";
            case 137:
                return "police line do not cross";
            case 138:
                return "pulp fiction burger reference #1";
            case 139:
                return "pulp fiction burger reference #2";
            case 140:
                return "searching for digital chocolate's forgotten pc games";
            case 141:
                return "food broke";
            case 142:
                return "perfectly unbalanced game";
            case 143:
                return "why do you still play these, ily";
            case 144:
                return "i ate everyone";
            case 145:
                return "the blues";
            case 146:
                return "this is not a minecraft skin, silly";
            case 147:
                return "are you still there?";
            case 148:
                return "there you are";
            case 149:
                return "lol is lol backwards? did robtop said that?";
            case 150:
                return "i did not hit her";
            case 151:
                return "music used was copyright by it's owners, i think";
            case 152:
                return "robtop, pls send me newgrounds api db for music";
            case 153:
                return "GOTY Edition";
            case 154:
                return "made by everyone just for you";
            case 155:
                return "there is a game which doesn't exist";
            case 156:
                return "this game suck";
            case 157:
                return "making music was harder than i thought";
            case 158:
                return "i hate this place"; // now i'm not
            case 159:
                return "i hate everything";
            case 160:
                return "raster vs. vector";
            case 162:
                return "can't install unity due to storage capacity problems"; // guess what i already did
            case 163:
                return "@Override is optional, i think";
            case 164:
                return "press f2 to go to setup"; // why this was f4?
            case 165:
                return "press f13 to reveal a secret";
            case 166:
                return "Face Reveal at 100 mil subs";
            case 167:
                return "imagine scrolling this far just to find this";
            case 168:
                return "google collab helped me out";
            case 169:
                return "0w0";
            case 170:
                return "how's it going bros";
            case 171:
                return "top of the morning";
            case 172:
                return "android version soon";
            case 173:
                return "marquee tag"; // make this marquee anim
            case 174:
                return "tinnitus";
            case 175:
                return "nocebo effect";
            case 176:
                return "nothing, and me, nothing, nothing, no more"; // are you a nihilist?
            case 177:
                return "libgdx ported this game to android";
            case 178:
                return "send conway's game of life patterns";
            case 179:
                return "2.2 when"; // sneak peek out now
            case 180:
                return "impossible";
            case 181:
                return "stop it, get some help";
            case 182:
                return "there is no splash";
            case 183:
                return "solves the halting problem using the famous turing machine";
            case 184:
                return "I've gotta take a little time"; // i wanna know what love is
            case 185:
                return "pet the crewmate";
            case 186:
                return "missingno included";
            case 187:
                return "vs. the world";
            case 188:
                return "vs. the forces of evil";
            case 189:
                return "too many damn chores";
            case 190:
                return "use cotton as clouds";
            case 191:
                return "so it's about this scientist named rick";
            case 192:
                return "the kee games quiz show font released!";
            case 193:
                return "you had a goal, but not that many"; // notorious big
            case 194:
                return "load multiplier included";
            case 195:
                return "transparency destroyed my pc";
            case 196:
                return "a hungry programmer takes mega bytes";
            case 197:
                return "circle of fifths";
            case 198:
                return "storming area 51 since 2019";
            case 199:
                return "coincidence? i think not";
            case 200:
                return "baby fights";
            case 201:
                return "you're a good kid franklin";
            case 202:
                return "felix, the meme lord behind youtube";
            case 203:
                return "add something here"; // i can't be touched
            case 204:
                return "don't blame me, blame your side";
            case 205:
                return "ninja cops"; // new game idea?
            case 206:
                return "what just happened?";
            case 207:
                return "just as planned";
            case 208:
                return "thread ripper";
            case 209:
                return "render (Graphics g)";
            case 210:
                return "render (SpriteBatch sb)";
            case 211:
                return "better than reality";
            case 212:
                return "future funk";
            case 213:
                return "don't vote on seven";
            case 214:
                return "define angles first";
            case 215:
                return "follow the angle's pattern";
            case 216:
                return "inducegamecrashforrealz";
            case 217:
                return "hacking the game since 2015";
            case 218:
                return "titanium hwhite"; // bob ross
            case 219:
                return "son of a gun";
            case 220:
                return "sign that rascal"; // bob ross
            case 221:
                return "keep yourself distracted";
            case 222:
                return "cute aggression";
            case 223:
                return "please don't sue";
            case 224:
                return "thank you";
            case 225:
                return "auto tune and it's finest";
            case 226:
                return "also try Carrion";
            case 227:
                return "also try Kindergarten";
            case 228:
                return "under pressure";
            case 229:
                return "now you can eat sunlight";
            case 230:
                return ":/";
            case 231:
                return "UwU";
            case 232:
                return "31337 H4X0R";
            case 233:
                return "string encryption";
            case 234:
                return "laser polygon";
            case 235:
                return "uhh cringe"; // upload barah bonaks
            case 236:
                return "secret tunnel";
            case 237:
                return "for dummies";
            case 238:
                return "pop the cherry"; // illegal, spanish inquisition
            case 239:
                return "hail santa";
            case 240:
                return "fruit snake";
            case 241:
                return "I.T. hertz WAN I.P.";
            case 242:
                return "Pong Lenis";
            case 243:
                return "why did the chicken cross the road?";
            case 244:
                return "PC Master Race";
            case 245:
                return "you've watched too much TV";
            case 246:
                return "enlarge your... MEMORY!";
            case 247:
                return "digital works has no value";
            case 248:
                return "pass out the sauce";
            case 249:
                return "aye";
            case 250:
                return "i got a laptop in my backpack";
            case 251:
                return "when the moon shines";
            case 252:
                return "if (playing && thenLooped) {go back to start}";
            case 253:
                return "quality of life";
            case 254:
                return "I'M BACK AT MP3 WORLD!";
            case 255:
                return "WhHAt'S YoOuUurR FfaAvVoOrRiItTeE bBlLoOcCKk IinN mMiInNeEcCrRaAfFtT?¿";
            case 256:
                return "Don’t = do not, Won’t = wo not ";
            case 257:
                return "lord gaben take my money";
            case 258:
                return "imma end this man's whole career";
            case 259:
                return "glasses, jacket, shirt";
            case 260:
                return "always has been";
            case 261:
                return "sike that's the wrong number";
            case 262:
                return "save all unsaved posts";
            case 263:
                return "force multiplier";
            case 264:
                return "ma boi";
            case 265:
                return "excuse me, princess";
            case 266:
                return "hit 'em in the pancreas"; // uhh i wanna eat your pancreas?
            case 267:
                return "check out the closed instruction book";
            case 268:
                return "the koopalings and i have taken over the mushroom kingdom";
            case 269:
                return "too many toasters";
            case 270:
                return "i hope she made lots of spaghetti";
            case 271:
                return "all toasters toast toast";
            case 272:
                return "you bring a light?";
            case 273:
                return "i'm your biggest fan";
            case 274:
                return "hey you, get out of my cloud";
            case 275:
                return "it's been one of those days"; // might remove
            case 276:
                return "you're the best player, ever";
            case 277:
                return "random boss battles"; // pewds video
            case 278:
                return "that's enough for me";
            case 279:
                return "this is a placeholder text";
            case 280:
                return "this is a placeholder option";
            case 281:
                return "roshambo edition";
            case 282:
                return "what happened to Unus Annus?";
            case 283:
                return "memento mori";
            case 284:
                return "includes navier stokes equations";
            case 285:
                return "perlin noise";
            case 286:
                return "sorting algorithm";
            case 287:
                return "greedy algorithm";
            case 288:
                return "arithmetic algorithm";
            case 289:
                return "shift+click selects an interval";
            case 290:
                return "electric boogaloo";
            case 291:
                return "it is what it is";
            case 292:
                return "moist";
            case 293:
                return "dundundududundudundudun";
            case 294:
                return "for lease navidad";
            case 295:
                return "kids see ghosts";
            case 296:
                return "God wants 20%";
            case 297:
                return "God helped me";
            case 298:
                return "you are an idiot :)";
            case 299:
                return "after egypt";
            case 300:
                return "sorry quillton :(";
            case 301:
                return "really punish";
            case 302:
                return "478868574082066804"; // herobrine minecraft seed
            case 303:
                return "-6984854390176336655"; // skull painting seed
            case 304:
                return "HELL NO! HELL NO!";
            case 305:
                return "haters are my motivators";
            case 306:
                return "objects in the window are closer than they appear";
            case 307:
                return "keeping it cool";
            case 308:
                return "another fender bender";
            case 309:
                return "the quick brown fox jumps over the lazy dog";
            case 310:
                return "F";
            case 311:
                return "60, 75, 80, 100, 120, 125, 150, 200"; // limited bpm
            case 312:
                return "player then range";
            case 313:
                return "next time on the house of cosbys";
            case 314:
                return "NaN (Not a Number) makes the player go outside the box or pops out of existence";
            case 315:
                return "the future so bright, we gonna wear shades";
            case 316:
                return "i don't wanna ruin the surprise";
            case 317:
                return "9999 ties in a row";
            case 318:
                return "zombie sized chickens";
            case 319:
                return "chicken sized zombies";
            case 320:
                return "lag spike of death";
            case 321:
                return "fool me once, shame on you, fool me twice, i punch your face";
            case 322:
                return "jinx";
            case 323:
                return "#POTATO451";
            case 324:
                return "127142"; // what the hell is this number? EDIT: found it, dictionary.txt line 127142
            case 325:
                return "acoustic fingerprinting and query by humming";
            case 326:
                return "if not ok then return end";
            case 327:
                return "B R E A T H T A K I N G";
            case 328:
                return "the absence of evidence is not the evidence of absence"; // might fix this
            case 329:
                return "ugh fine i guess you are my little pogchamp"; // delete this EDIT: i've watched kill la kil, it was good
            case 330:
                return "machine learning and neural networks";
            case 331:
                return "sourceDataLine";
            case 332:
                return "mark's a masochist";
            case 333:
                return "baloney and donut code";
            case 334:
                return "my code is mine, it belongs to me, you can't have my code";
            case 335:
                return "DOOR STUCK! DOOR STUCK!";
            case 336:
                return "nosebleed";
            case 337:
                return "photoshop has gone too far";
            case 338:
                return "tumbleweed";
            case 339:
                return "procrastinate";
            case 340:
                return "terrain generation";
            case 341:
                return "quantum computing";
            case 342:
                return "being dumb the smart way";
            case 343:
                return "being smart the dumb way";
            case 344:
                return "sorry my crosshair was 0.5 pixel off";
            case 345:
                return "where physics has gone wrong";
            case 346:
                return "hyperbolic rendering";
            case 347:
                return "death to facepunch";
            case 348:
                return "pentesting";
            case 349:
                return "starstuff";
            case 350:
                return "3fish.exe";
            case 351:
                return "infinite stairs";
            case 352:
                return "byte[] pcmData";
            case 353:
                return "the art of bodging";
            case 354:
                return "will return in " + XtAudio.milli + "ms";
            case 355:
                return "ritar, iterate elements of array in reverse order";
            case 356:
                return "weeeeeeeeeeeeeeeeeee";
            case 357:
                return "also try Friday Night Funkin";
            case 358:
                return "also try Beat Stomper";
            case 359:
                return "also try Smash Hit";
            case 360:
                return "also try Antichamber";
            case 361:
                return "also try Superliminal";
            case 362:
                return "also try VVVVVV";
            case 363:
                return "ok take your time";
            case 364:
                return "we can do this the easy way, or we can do it the hard way";
            case 365:
                return "akinator almost beat my game";
            case 366:
                return "VIP Edit";
            case 367:
                return "low pass filter";
            case 368:
                return "wrap text";
            case 369:
                return "what? how?";
            case 370:
                return "zipf mystery explained";
            case 371:
                return "infinite procedural platforms";
            case 372:
                return "3d maze generator";
            case 373:
                return "rtx rtx rtx";
            case 374:
                return "eagle eye";
            case 375:
                return "falcon punch";
            case 376:
                return "god i love myself";
            case 377:
                return "say, fuzzy pickles";
            case 378:
                return "=+ is different from +=";
            case 379:
                return "++x is the same as x++";
            case 380:
                return "still a prototype";
            case 381:
                return "Robot robot = new Robot();";
            case 382:
                return "your computer is an advanced clock";
            case 383:
                return "Math.round(inaccurateData)";
            case 384:
                return "thank you sjoerd van kreel";
            case 385:
                return "music by GDjkhp"; // make this updating
            case 386:
                return "created by KENNEDY";
            case 387:
                return "i made it despite your directions";
            case 388:
                return "hold on, if love is the answer, you're home...";
            case 389:
                return "also try Friday Night Funkin";
            case 390:
                return "also try Untitled Goose Game";
            case 391:
                return "also try Papers, Please";
            case 392:
                return "dedicated to Marcus Hutchins";
            case 393:
                return "dedicated to Aaron McGruder";
            case 394:
                return "dedicated to Terry Davis";
            case 395:
                return "my name is jeff";
            case 396:
                return "say the password";
            case 397:
                return "bro, it's just a metaphor";
            case 398:
                return ".";
            case 399:
                return "don't act like you ain't say what you just said";
            case 400:
                return "don't trash the aspect ratio";
            case 401:
                return "dc offset";
            case 402:
                return "hoik method";
            case 403:
                return "in the future, humor will be randomly generated";
            case 404:
                return "#1 victory royale"; // ok, 19 dollar fortnite card, who wants it?
            case 405:
                return "and yes, i'm giving it away";
            case 406:
                return "remember, share, share, share";
            case 407:
                return "and trolls, don't get blocked";
            case 408:
                return "hiragana/katakana";
            case 409:
                return "application level streams";
            case 410:
                return "d5e5fb653780dbc3";
            case 411:
                return "schrodinger's cat paradox";
            case 412:
                return "\\\\.\\globalroot\\device\\condrv\\kernelconnect";
            case 413:
                return "rip LnrFlyDvs";
            case 414:
                return "soundcloud.com/john-kennedy-pena";
            case 415:
                return "gdjkhp.github.io";
            case 416:
                return "jkhp.newgrounds.com";
            case 417:
                return "getting freaky on a friday night yeah";
            case 418:
                return "i believe in you";
            case 419:
                return "Pollyanna";
            case 420:
                return "(c) 20XX The Karakters Kompany. on behalf of the people that make silly and weird games. All rights reserved.";
            case 421:
                return "it's not over, till it's over";
            case 422:
                return "a game, inside a game, inside a game";
            case 423:
                return "drawing text with wrapping and text alignment";
            case 424:
                return "slice of life"; // best genre
            case 425:
                return "inspired from the art of almighty Bob Ross";
            case 426:
                return "are chickens dinosaurs?";
            case 427:
                return "the game that made me question my existence";
            case 428:
                return "enough talk";
            case 429:
                return "the noob, the pro, and the hacker";
            case 430:
                return "oriental riff";
            case 431:
                return "the lick";
            case 432:
                return "exactly that's my reaction";
            case 433:
                return "contains optical illusions";
            case 434:
                return "smash like for mike";
            case 435:
                return "bound to the earth";
            case 436:
                return "frequency analyzed";
            case 437:
                return "existence is pain";
            case 438:
                return "insert coin";
            case 439:
                return "dedicated to LnrFlyDvs";
            case 440:
                return "better than friday night funkin, fight me ninjamuffin99";
            case 441:
                return "corporate art styles";
            case 442:
                return "8091867987493326313"; // Minecraft's Title Screen Seed
            case 443:
                return "-1044887956651363087"; // skull painting seed
            case 444:
                return "1458140401"; // update aquatic panorama seed
            case 445:
                return "2802867088795589976"; // village and pillage update panorama seed
            case 446:
                return "-4404205509303106230"; // buzzy bees panorama seed
            case 447:
                return "6006096527635909600"; // nether update panorama seed
            case 448:
                return "Who else couldn't grasp the true form of gigyas's attack";
            case 449:
                return "Don't cry because it's over, smile because it happened";
            case 450:
                return "CMYBk vs. RGBA";
            case 451:
                return "pulse code modulation";
            case 452:
                return "digital signal processing";
            case 453:
                return "faulty battery level indicator circuit";
            case 454:
                return "burger, nuggets, nuggets, burger";
            case 455:
                return "deception";
            case 456:
                return "drip time";
            case 457:
                return "among drip";
            case 458:
                return "buy borger";
            case 459:
                return "re-iterate";
            case 460:
                return "unbelievably perfect declamations";
            case 461:
                return "pump up the volume";
            case 462:
                return "i wanted to solve the problem, not run away from them";
            case 463:
                return "Hello, World!";
            case 464:
                return "ye, uh-huh";
            case 465:
                return "war is coming";
            case 466:
                return "why didn't you pay for this beat tho";
            case 467:
                return "you ain't rappin ova dis one";
            case 468:
                return "roko's basilisk";
            case 469:
                return "top 10 rappers Eminem was too afraid to diss";
            case 470:
                return "what i'm saying is, there's known knowns and known unknowns, but there's also unknown unknowns";
            case 471:
                return "i love having bugs in my code";
            case 472:
                return "when i type one character, i make a waterfall of 10 bugs";
            case 473:
                return "who cares? make games";
            case 474:
                return "hacker == programmer";
            case 475:
                return "intellisense";
            case 476:
                return "Intelligent code completion";
            case 477:
                return "you don't have to write pretty code to make a fun game";
            case 478:
                return "ludum dare";
            case 479:
                return "sweaty speedrunner approved";
            case 480:
                return "npesta approved";
            case 481:
                return "how much is a $10 uber ride";
            case 482:
                return "how heavy is a 10kg one";
            case 483:
                return "artificial intelligence A.I.";
            case 484:
                return "60 seconds in-game, a minute passes";
            case 485:
                return "one does not simply leave a random splash on the title";
            case 486:
                return "i will do anything to eat a meal by the power of slaps";
            case 487:
                return "a small bug in nature's design";
            case 488:
                return "minor glitch in the matrix";
            case 489:
                return "the computer business is too competitive";
            case 490:
                return "it takes two to make it outta sight";
            case 491:
                return "salutations and such";
            case 492:
                return "you did an amazing turron here";
            case 493:
                return "also try Before Your Eyes";
            case 494:
                return "life is like a dream now, let's kill the boss with me now"; // reference to a newgrounds contest, this is where the game's reboot happens
            case 495:
                return "drop the beat, drop the beat";
            case 496:
                return "flipping bits using cosmic rays";
            case 497:
                return "more efforts to break your records";
            case 498:
                return "a whole day supply of vitamin c";
            case 499:
                return "smells sour like funky";
            case 500:
                return "what da dog doin?";
            case 501:
                return "dedicated to Daisuke \"Pixel\" Amaya";
            case 502:
                return "we bring the war to them";
            case 503:
                return "blah blah blah coolswag";
            case 504:
                return "ouch, a time traveler walks into a bar";
            case 505:
                return "contains fragments of code from polybius";
            case 506:
                return "so i started watching anime...";
            case 507:
                return "bruh";
            case 508:
                return "amogus";
            case 509:
                return "dedicated to ONE";
            case 510:
                return "operation soda steal";
            case 511:
                return "09/10/2021";
            case 512:
                return "printer lore"; // so you don't have to cut me off
            case 513:
                return "kid amogus";
            case 514:
                return "stun amogus";
            case 515:
                return "STOP POSTING ABOUT AMONG US";
            case 516:
                return "i forgor 💀";
            case 517:
                return "today we're going to talk about the pixels";
            case 518:
                return "steve's sigma grindset";
            case 519:
                return "industrial society and it's future by theodore john kaczynski";
            case 520:
                return "what'chu know about rolling down in the deep";
            case 521:
                return "think mark";
            case 522:
                return "sheesh";
            case 523:
                return "rip el risitas";
            case 524:
                return "drip cars";
            case 525:
                return "youtube kids at 3 am";
            case 526:
                return "4/24/2021"; // ah yes the josh fight
            case 527:
                return "fans explain why this game was so bad yet so good"; // horriblesubs
            case 528:
                return "⚠ TRADE OFFER ⚠";
            case 529:
                return "lezgo"; // dababy
            case 530:
                return "snap back to reality";
            case 531:
                return "been's spendin' most their lives livin in the gangsta's paradise";
            case 532:
                return "1 + 2 = 3";
            case 533:
                return "power and the money, money and the power";
            case 534:
                return "minute after minute, hour after hour";
            case 535:
                return "ok $19 fortnite card who wants it?";
            case 536:
                return "64 32 16 8 4 2 1 1/2 1/4 the wrist game";
            case 537:
                return "konichiwa sekai";
            case 538:
                return "wassup can a loc come up in your crib?";
            case 539:
                return "man"; // juan
            case 540:
                return "you are under attack from the sun and its gamma waves";
            case 541:
                return "we now return to gangstalicious resurrection";
            case 542:
                return "god got involved, god is gangsta";
            case 543:
                return "thank you for your attention";
            case 544:
                return "what's done is done, let's be solution-oriented, u mad?";
            case 545:
                return "placebo effect";
            case 546:
                return "i programmed you to believe that";
            case 547:
                return "i beg your pardon";
            case 548:
                return "you just got saved by jesus christ";
            case 549:
                return "i'm in, i'm in, what's the job? i'm out, i quit, whose kidneys were these?";
            case 550:
                return "this game can save and change your life";
            case 551:
                return "free grunt";
            case 552:
                return "i paid good money on this game and if i don't deserve a refund, i shall take my business elsewhere"; // fix this
            case 553:
                return "doodly do doodly doodly do";
            case 554:
                return "go beyond plus ultra";
            case 555:
                return "dedicated to toby fox";
            case 556:
                return "dedicated to robtop";
            case 557:
                return "dedicated to markus \"notch\" persson";
            case 558:
                return "compassion to fellow men";
            case 559:
                return "last bit"; // lemkuuja
            case 560:
                return "drop the generation gap crap";
            case 561:
                return "assert dominance"; // t pose
            case 562:
                return "like taking candy from a baby";
            case 563:
                return "for the love of all that is holy"; // do not connect the red wire to the blue wire
            case 564:
                return "man when i played this game i was like, never again"; // and it happened again
            case 565:
                return "aimbot + autododge included"; // fuck anime arc
            case 566:
                return "asciente";
            case 567:
                return "chocomint is just toothpaste, change my mind";
            case 568:
                return "call 911 what's the number";
            case 569:
                return "hustle hustle muscle muscle";
            case 570:
                return "";
            case 571:
                return "";
            case 572:
                return "";
            case 573:
                return "";
            case 574:
                return "";
            case 575:
                return "";
            case 576:
                return "";
            case 577:
                return "";
            case 578:
                return "";
            case 579:
                return "";
            case 580:
                return "";
            case 581:
                return "";
            case 582:
                return "";
            case 583:
                return "";
            case 584:
                return "";
            case 585:
                return "";
            case 586:
                return "";
            case 587:
                return "";
            case 588:
                return "";
            case 589:
                return "";
            case 590:
                return "";
            case 591:
                return "";
            case 592:
                return "";
            case 593:
                return "";
            case 594:
                return "";
            case 595:
                return "";
            case 596:
                return "";
            case 597:
                return "";
            case 598:
                return "";
            case 599:
                return "";
            case 600:
                return "";
            case 601:
                return "";
            case 602:
                return "";
            case 603:
                return "";
            case 604:
                return "";
            case 605:
                return "";
            case 606:
                return "";
            case 607:
                return "";
            case 608:
                return "";
            case 609:
                return "";
            case 610:
                return "";
            case 611:
                return "";
            case 612:
                return "";
            case 613:
                return "";
            case 614:
                return "";
            case 615:
                return "";
            case 616:
                return "";
            case 617:
                return "";
            case 618:
                return "";
            case 619:
                return "";
            case 620:
                return "";
            case 621:
                return "";
            case 622:
                return "";
            case 623:
                return "";
            case 624:
                return "";
            case 625:
                return "";
            case 626:
                return "";
            case 627:
                return "";
            case 628:
                return "";
            case 629:
                return "";
            case 630:
                return "";
            case 631:
                return "";
            case 632:
                return "";
            case 633:
                return "";
            case 634:
                return "";
            case 635:
                return "";
            case 636:
                return "";
            case 637:
                return "";
            case 638:
                return "";
            case 639:
                return "";
            case 640:
                return "";
            case 641:
                return "";
            case 642:
                return "";
            case 643:
                return "";
            case 644:
                return "";
            case 645:
                return "";
            case 646:
                return "";
            case 647:
                return "";
            case 648:
                return "";
            case 649:
                return "";
            case 650:
                return "";
            case 651:
                return "";
            case 652:
                return "";
            case 653:
                return "";
            case 654:
                return "";
            case 655:
                return "";
            case 656:
                return "";
            case 657:
                return "";
            case 658:
                return "";
            case 659:
                return "";
            case 660:
                return "";
            case 661:
                return "";
            case 662:
                return "";
            case 663:
                return "";
            case 664:
                return "";
            case 665:
                return "";
            case 666:
                return "";
            case 667:
                return "";
            case 668:
                return "";
            case 669:
                return "";
            case 670:
                return "";
            case 671:
                return "";
            case 672:
                return "";
            case 673:
                return "";
            case 674:
                return "";
            case 675:
                return "";
            case 676:
                return "";
            case 677:
                return "";
            case 678:
                return "";
            case 679:
                return "";
            case 680:
                return "";
            case 681:
                return "";
            case 682:
                return "";
            case 683:
                return "";
            case 684:
                return "";
            case 685:
                return "";
            case 686:
                return "";
            case 687:
                return "";
            case 688:
                return "";
            case 689:
                return "";
            case 690:
                return "";
            case 691:
                return "";
            case 692:
                return "";
            case 693:
                return "";
            case 694:
                return "";
            case 695:
                return "";
            case 696:
                return "";
            case 697:
                return "";
            case 698:
                return "";
            case 699:
                return "";
            case 700:
                return "";
            case 701:
                return "";
            case 702:
                return "";
            case 703:
                return "";
            case 704:
                return "";
            case 705:
                return "";
            case 706:
                return "";
            case 707:
                return "";
            case 708:
                return "";
            case 709:
                return "";
            case 710:
                return "";
            case 711:
                return "";
            case 712:
                return "";
            case 713:
                return "";
            case 714:
                return "";
            case 715:
                return "";
            case 716:
                return "";
            case 717:
                return "";
            case 718:
                return "";
            case 719:
                return "";
            case 720:
                return "";
            case 721:
                return "";
            case 722:
                return "";
            case 723:
                return "";
            case 724:
                return "";
            case 725:
                return "";
            case 726:
                return "";
            case 727:
                return "";
            case 728:
                return "";
            case 729:
                return "";
            case 730:
                return "";
            case 731:
                return "";
            case 732:
                return "";
            case 733:
                return "";
            case 734:
                return "";
            case 735:
                return "";
            case 736:
                return "";
            case 737:
                return "";
            case 738:
                return "";
            case 739:
                return "";
            case 740:
                return "";
            case 741:
                return "";
            case 742:
                return "";
            case 743:
                return "";
            case 744:
                return "";
            case 745:
                return "";
            case 746:
                return "";
            case 747:
                return "";
            case 748:
                return "";
            case 749:
                return "";
            case 750:
                return "";
            case 751:
                return "";
            case 752:
                return "";
            case 753:
                return "";
            case 754:
                return "";
            case 755:
                return "";
            case 756:
                return "";
            case 757:
                return "";
            case 758:
                return "";
            case 759:
                return "";
            case 760:
                return "";
            case 761:
                return "";
            case 762:
                return "";
            case 763:
                return "";
            case 764:
                return "";
            case 765:
                return "";
            case 766:
                return "";
            case 767:
                return "";
            case 768:
                return "";
            case 769:
                return "";
            case 770:
                return "";
            case 771:
                return "";
            case 772:
                return "";
            case 773:
                return "";
            case 774:
                return "";
            case 775:
                return "";
            case 776:
                return "";
            case 777:
                return "";
            case 778:
                return "";
            case 779:
                return "";
            case 780:
                return "";
            case 781:
                return "";
            case 782:
                return "";
            case 783:
                return "";
            case 784:
                return "";
            case 785:
                return "";
            case 786:
                return "";
            case 787:
                return "";
            case 788:
                return "";
            case 789:
                return "";
            case 790:
                return "";
            case 791:
                return "";
            case 792:
                return "";
            case 793:
                return "";
            case 794:
                return "";
            case 795:
                return "";
            case 796:
                return "";
            case 797:
                return "";
            case 798:
                return "";
            case 799:
                return "";
            case 800:
                return "";
            case 801:
                return "";
            case 802:
                return "";
            case 803:
                return "";
            case 804:
                return "";
            case 805:
                return "";
            case 806:
                return "";
            case 807:
                return "";
            case 808:
                return "";
            case 809:
                return "";
            case 810:
                return "";
            case 811:
                return "";
            case 812:
                return "";
            case 813:
                return "";
            case 814:
                return "";
            case 815:
                return "";
            case 816:
                return "";
            case 817:
                return "";
            case 818:
                return "";
            case 819:
                return "";
            case 820:
                return "";
            case 821:
                return "";
            case 822:
                return "";
            case 823:
                return "";
            case 824:
                return "";
            case 825:
                return "";
            case 826:
                return "";
            case 827:
                return "";
            case 828:
                return "";
            case 829:
                return "";
            case 830:
                return "";
            case 831:
                return "";
            case 832:
                return "";
            case 833:
                return "";
            case 834:
                return "";
            case 835:
                return "";
            case 836:
                return "";
            case 837:
                return "";
            case 838:
                return "";
            case 839:
                return "";
            case 840:
                return "";
            case 841:
                return "";
            case 842:
                return "";
            case 843:
                return "";
            case 844:
                return "";
            case 845:
                return "";
            case 846:
                return "";
            case 847:
                return "";
            case 848:
                return "";
            case 849:
                return "";
            case 850:
                return "";
            case 851:
                return "";
            case 852:
                return "";
            case 853:
                return "";
            case 854:
                return "";
            case 855:
                return "";
            case 856:
                return "";
            case 857:
                return "";
            case 858:
                return "";
            case 859:
                return "";
            case 860:
                return "";
            case 861:
                return "";
            case 862:
                return "";
            case 863:
                return "";
            case 864:
                return "";
            case 865:
                return "";
            case 866:
                return "";
            case 867:
                return "";
            case 868:
                return "";
            case 869:
                return "";
            case 870:
                return "";
            case 871:
                return "";
            case 872:
                return "";
            case 873:
                return "";
            case 874:
                return "";
            case 875:
                return "";
            case 876:
                return "";
            case 877:
                return "";
            case 878:
                return "";
            case 879:
                return "";
            case 880:
                return "";
            case 881:
                return "";
            case 882:
                return "";
            case 883:
                return "";
            case 884:
                return "";
            case 885:
                return "";
            case 886:
                return "";
            case 887:
                return "";
            case 888:
                return "";
            case 889:
                return "";
            case 890:
                return "";
            case 891:
                return "";
            case 892:
                return "";
            case 893:
                return "";
            case 894:
                return "";
            case 895:
                return "";
            case 896:
                return "";
            case 897:
                return "";
            case 898:
                return "";
            case 899:
                return "";
            case 900:
                return "";
            case 901:
                return "";
            case 902:
                return "";
            case 903:
                return "";
            case 904:
                return "";
            case 905:
                return "";
            case 906:
                return "";
            case 907:
                return "";
            case 908:
                return "";
            case 909:
                return "";
            case 910:
                return "";
            case 911:
                return "";
            case 912:
                return "";
            case 913:
                return "";
            case 914:
                return "";
            case 915:
                return "";
            case 916:
                return "";
            case 917:
                return "";
            case 918:
                return "";
            case 919:
                return "";
            case 920:
                return "";
            case 921:
                return "";
            case 922:
                return "";
            case 923:
                return "";
            case 924:
                return "";
            case 925:
                return "";
            case 926:
                return "";
            case 927:
                return "";
            case 928:
                return "";
            case 929:
                return "";
            case 930:
                return "";
            case 931:
                return "";
            case 932:
                return "";
            case 933:
                return "";
            case 934:
                return "";
            case 935:
                return "";
            case 936:
                return "";
            case 937:
                return "";
            case 938:
                return "";
            case 939:
                return "";
            case 940:
                return "";
            case 941:
                return "";
            case 942:
                return "";
            case 943:
                return "";
            case 944:
                return "";
            case 945:
                return "";
            case 946:
                return "";
            case 947:
                return "";
            case 948:
                return "";
            case 949:
                return "";
            case 950:
                return "";
            case 951:
                return "";
            case 952:
                return "";
            case 953:
                return "";
            case 954:
                return "";
            case 955:
                return "";
            case 956:
                return "";
            case 957:
                return "";
            case 958:
                return "";
            case 959:
                return "";
            case 960:
                return "";
            case 961:
                return "";
            case 962:
                return "";
            case 963:
                return "";
            case 964:
                return "";
            case 965:
                return "";
            case 966:
                return "";
            case 967:
                return "";
            case 968:
                return "";
            case 969:
                return "";
            case 970:
                return "";
            case 971:
                return "";
            case 972:
                return "";
            case 973:
                return "";
            case 974:
                return "";
            case 975:
                return "";
            case 976:
                return "";
            case 977:
                return "";
            case 978:
                return "";
            case 979:
                return "";
            case 980:
                return "";
            case 981:
                return "";
            case 982:
                return "";
            case 983:
                return "";
            case 984:
                return "";
            case 985:
                return "";
            case 986:
                return "";
            case 987:
                return "";
            case 988:
                return "";
            case 989:
                return "";
            case 990:
                return "";
            case 991:
                return "";
            case 992:
                return "";
            case 993:
                return "";
            case 994:
                return "";
            case 995:
                return "";
            case 996:
                return "";
            case 997:
                return "";
            case 998:
                return "";
            case 999:
                return "";
            case 1000:
                return "";
            default:
                return "";
        }
    }
    int randomLimit = 1000;
    int randIntFormula = r.nextInt(randomLimit) + 1;

    public static String title(){
        return frame.getTitle();
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_F4) randInt = randIntFormula;
        if (randomSplash(randInt).equals(""))
            System.out.println(randInt + " is null!");
    }

    public long lastTime = System.nanoTime();
    public double delta = 0;
    double amountOfTicks = 100.0;
    double ns = 1000000000 / amountOfTicks;
    long timer = System.currentTimeMillis();

    void timerForXtAudioThread(){
        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;
        while (delta >= 1) {
            delta--;
            if (XtAudio.run) {
                if (System.currentTimeMillis() - timer > 1) {
                    timer += 1;
                    XtAudio.milli--;
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}
}
