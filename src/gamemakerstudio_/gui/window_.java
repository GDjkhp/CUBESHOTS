/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.gui;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.assets_;
import gamemakerstudio_.misc.audiostuff.xtaudio.XtAudio;
import gamemakerstudio_.misc.handler_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
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
        this.game = game;

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

        game.start();
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
                return "Why Is EVERYONE Singing Baka Mitai?";
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
                return "doge and cheems and walter";
            case 49:
                return "i like fire trucks and moster trucks";
            case 50:
                return "kenos npestaposting";
            case 51:
                return "A E S T H E T I C";
            case 52:
                return "have you send it to robtop yet?";
            case 53:
                return "E";
            case 54:
                return "can i get uhhhh, lamp";
            case 55:
                return "sj8Sg8qnjOg"; // the golden ratio
            case 56:
                return "dQw4w9WgXcQ";
            case 57:
                return "UCrHkYaEEXKoK57GAwyC5JTg";
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
                return String.valueOf(game.gameState);
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
                return "there are 3 impostors among us";
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
                return "lol is lol backwards, alright i'mma stop now";
            case 150:
                return "i did not hit her";
            case 151:
                return "music used was copyright by it's owners, i think";
            case 152:
                return "robtop, pls send me newgrounds api for music";
            case 153:
                return "GOTY Edition";
            case 154:
                return "made by everyone just for you";
            case 155:
                return "there is a game which doesn't exist";
            case 156:
                return "this game suck";
            case 157:
                return "creating music was harder than i thought";
            case 158:
                return "i hate this place";
            case 159:
                return "i hate everything";
            case 160:
                return "raster vs. vector";
            case 162:
                return "can't install unity due to storage capacity problems";
            case 163:
                return "@Override is optional, i think";
            case 164:
                return "press f4 to go to setup";
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
                return "marquee tag";
            case 174:
                return "tinnitus";
            case 175:
                return "nocebo effect";
            case 176:
                return "nothing, and me, nothing, nothing, no more";
            case 177:
                return "libgdx ported this game to android";
            case 178:
                return "send conway's game of life patterns";
            case 179:
                return "2.2 when";
            case 180:
                return "impossible";
            case 181:
                return "stop it, get some help";
            case 182:
                return "there is no splash";
            case 183:
                return "solves the halting problem using the famous turing machine";
            case 184:
                return "I've gotta take a little time";
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
                return "you had a goal, but not that many";
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
                return "i can't be touched";
            case 204:
                return "don't blame me, blame your side";
            case 205:
                return "ninja cops";
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
                return "upload barah bonaks";
            case 236:
                return "secret tunnel";
            case 237:
                return "for dummies";
            case 238:
                return "pop the cherry"; // illegal
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
                return "hit 'em in the pancreas";
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
                return "it's been one of those days";
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
                return "127142";
            case 325:
                return "acoustic fingerprinting and query by humming";
            case 326:
                return "if not ok then return end";
            case 327:
                return "B R E A T H T A K I N G";
            case 328:
                return "the absence of evidence is not the evidence of absence  "; // might fix this
            case 329:
                return "ugh fine i guess you are my little pogchamp"; // delete this
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
                return "ok, 19 dollar fornite card, who wants it?";
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
                return "it's not over";
            case 421:
                return "till it's over";
            case 422:
                return "a game, inside a game, inside a game";
            case 423:
                return "drawing text with wrapping and text alignment";
            case 424:
                return "slice of life";
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
                return "";
            case 476:
                return "";
            case 477:
                return "";
            case 478:
                return "";
            case 479:
                return "";
            case 480:
                return "";
            case 481:
                return "";
            case 482:
                return "";
            case 483:
                return "";
            case 484:
                return "";
            case 485:
                return "";
            case 486:
                return "";
            case 487:
                return "";
            case 488:
                return "";
            case 489:
                return "";
            case 490:
                return "";
            case 491:
                return "";
            case 492:
                return "";
            case 493:
                return "";
            case 494:
                return "";
            case 495:
                return "";
            case 496:
                return "";
            case 497:
                return "";
            case 498:
                return "";
            case 499:
                return "";
            case 500:
                return "";
            default:
                return "";
        }
    }
    int randomLimit = 500;
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
