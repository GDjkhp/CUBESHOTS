/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import gamemakerstudio_.entities.*;
import gamemakerstudio_.entities.boss.crazyboss_;
import gamemakerstudio_.entities.experimental.*;
import gamemakerstudio_.gui.*;
import gamemakerstudio_.misc.*;
import gamemakerstudio_.misc.audiostuff.audioplayer_;
import gamemakerstudio_.misc.audiostuff.xtaudio.XtAudio;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;
import gamemakerstudio_.misc.graphicsstuff.*;
import gamemakerstudio_.world.levels_;
import gamemakerstudio_.world.spawn_;
import gamemakerstudio_.world.world_;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static gamemakerstudio_.gui.window_.frame;

/**
 *
 * @author ACER
 */
public class game_ extends GameEngine {

    // lezgo?!
    public static void main(String[] args) {
        System.out.println("game_ Engine [Version 1.0] by KENNEDY");
        System.out.println("(c) 2020 The Karakters Kompany, game_ Engine. All rights reserved.");

        new game_();
    }

    // dimension
    public static int WIDTH = 1360/2, HEIGHT = 720;
    // the game state
    public static STATE gameState = STATE.Load; // default is STATE.Load
    // difficulty
    public int diff = 0;
    // i dunno why is this here
    public static boolean currentGameStateIsBeta;
    // options
    public static boolean smoothFix = false;
    public static boolean music = false; // this is fake
    public static boolean sfx = false; // this is fake
    public static boolean metronomeSounds = true;
    public static boolean ldm = true; // this is fake
    public static boolean multiplayer = false;
    public static boolean isInvincible = false;
    public static boolean autoRestart = true;
    public static boolean JOptionPaneOption = false;
    public static boolean isSelecting = false; // do not touch, unless this is xbox
    public static boolean computerP1 = false;
    public static boolean computerP2 = false;
    public static boolean espLineP1 = false;
    public static boolean espLineP2 = false;
    public static boolean hideHud = false;
    public static boolean backgroundRender = true;
    public static boolean mouseCursor = false; // info stuff
    public static boolean gridLines = false; // info stuff
    public static boolean recordVideo = false; // this is fake
    public static boolean recordAudio = false; // this is fake
    public static boolean recordSession = false; // for recording purposes only

    // var stuff
    public Random r = new Random();

    public static String stringsforloading = "loading failed, press alt + f4 to exit...";
    public static double loadstate;

    // enter classes here
    public handler_ handler = new handler_();
    public hud_ hud = new hud_(handler, this);
    public hud2_ hud2 = new hud2_();
    public spawn_ spawner = new spawn_(handler, hud, this);
    public shop_ shop = new shop_(handler, hud, hud2, this);
    public loading_ load = new loading_();
    public levels_ levels = new levels_(handler, hud, this, hud2);
    public devconsole_ devconsole = new devconsole_(this, handler, hud, hud2);
    public KeyInput keyMain = new KeyInput(handler, this, hud, hud2, levels, devconsole);
    public leveleditor_ editor = new leveleditor_(this, handler);
    // requires graphics
    public window_ window;
    public world_ world;
    public gamecamera_ gamecamera;
    public menu_ menu;
    // textures
    public BufferedImageLoader loader = new BufferedImageLoader();
    public static BufferedImage spritesheet;

    // xuggler
    private static final double FRAME_RATE = 50;

    private static final int SECONDS_TO_RUN_FOR = 20;

    private static final String outputFilename = "C:\\Users\\ACER\\Desktop\\mydesktop.mp4";

    private static Dimension screenBounds = new Dimension(WIDTH, HEIGHT);

    public static IMediaWriter writer = ToolFactory.makeWriter(outputFilename);

    public static long startTime = System.nanoTime();

    // load codes here, run this once
    public game_() {
        // warning
        if (JOptionPaneOption) {
            int seizure = JOptionPane.showConfirmDialog(null, "seizure warning", "Surprise Motherf*!", JOptionPane.INFORMATION_MESSAGE);
            if (seizure == JOptionPane.NO_OPTION) System.exit(0);
        }
        // load
        System.out.println("==============================================================");
        System.out.println("loading window: " + WIDTH + "x" + HEIGHT);
        window = new window_(WIDTH, HEIGHT, "game_ alpha edition v1", this, handler);

        // init textures
        stringsforloading = "loading textures...";
        System.out.println(stringsforloading);
        // assets
        spritesheet = loader.loadImage("resources_/image_/gamespritesheet.png");
        assets_.init();
        loadstate += 25;

        // classes with textures
        world = new world_(this, "resources_/levels_/basicworldgen.txt");
//        gamecamera = new gamecamera_(0, 0);
//        this.getGameCamera().move(0, 0);
        menu = new menu_(this, handler, hud, hud2);

        // test graphics

        // custom arrow
        BufferedImage cursorImg = assets_.arrow;
        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);

        // arrow pos
        if (mouseCursor) handler.addObject(new CURSOR_POINTER(0, 0, ID.CURSOR, this));
//        handler.addObject(new osc_(0, 0, ID.OSC));

        // test immediately here
        handler.addObject(new betterosc_(0, 0, ID.OSC, this));
        // handler.addObject(new mandelbrot_(0, 0, ID.Mandelbrot));

//        handler.addObject(new evenbetterosc_(0, 0, ID.OSC));
//        handler.addObject(new camera_(0, 0, ID.NULL, this));

        // draw the smallest first
        /*handler.addObject(new rendertexture_(WIDTH - WIDTH/2, HEIGHT - HEIGHT/2, ID.NULL, WIDTH/2, HEIGHT/2, 0, 0));
        handler.addObject(new rendertexture_(0, 0, ID.NULL, WIDTH/2, HEIGHT/2, 0, 0));
        handler.addObject(new rendertexture_(WIDTH - WIDTH/2, 0, ID.NULL, WIDTH/2, HEIGHT/2, 0, 0));
        handler.addObject(new rendertexture_(0, HEIGHT - HEIGHT/2, ID.NULL, WIDTH/2, HEIGHT/2, 0, 0));*/

        // handler.addObject(new rendertexture_(WIDTH/2 - (WIDTH/2)/2, HEIGHT/2 - (HEIGHT/2)/2, ID.RenderTexture, WIDTH/2, HEIGHT/2, 0, 0));
        // handler.addObject(new rendertexture_(WIDTH/2 - (WIDTH/4)/2, HEIGHT/2 - (HEIGHT/4)/2, ID.RenderTexture, WIDTH/4, HEIGHT/4, 0, 0));

        // reset
        endCodes();

        // init fonts, do i still use this?
        stringsforloading = "loading fonts...";
        System.out.println(stringsforloading);
        FontClass.loadfont();
        loadstate += 25;

        // init input, might remove class input later, do not remove keyMain
        stringsforloading = "loading input...";
        System.out.println(stringsforloading);
        this.addKeyListener(keyMain);
        this.addMouseListener(keyMain);
        // this.addKeyListener(new SPKeyInput());
        loadstate += 25;

        // FIXME: real ldm setting, to fix annoying sort bug
        ldm = false;

        // init sounds (buggy), to fix, replace Slick2D lib, it's the only way
        // edit: or not
        stringsforloading = "loading sounds...";
        System.out.println(stringsforloading);
        System.out.println("==============================================================");
        // TinySound.init();
        audioplayer_.load("");
        loadstate += 25;

        // for testing purposes: if current state isn't load, clear screen

        boolean CLEAR = false;

        if (CLEAR){
            gameState = STATE.Edit;
            handler.clearEnemies();
            // to kill players, use this instead
            hud_.HEALTH = 0;
            hud2_.HEALTH = 0;
            hideHud = true;

            // custom kill
            if (!recordSession)
                handler.removeAllSelectedObjects(ID.OSC);

            // spawn codes here
            // handler.addObject(new pathfinder_(0, 0, ID.Pathfinder, this));
            // handler.addObject(new raycast_(0, 0, ID.Raycast, this));
            // handler.addObject(new mandelbrot_(0, 0, ID.Mandelbrot));
            // handler.addObject(new maze_(0, 0, ID.MazeGen, this));
            handler.addObject(new julia_(0, 0, ID.Julia));
        }

        // weird thread sleep code for new audio capture engine
        new XtAudio();
    }

    public static void doneLoadingCodes(){
        // loading done codes
        if (gameState == STATE.Load) {
            gameState = STATE.Menu;
            // let the user change this option
            int confirmSound = JOptionPane.showConfirmDialog(null,
                    "Enable sounds?", "Music", JOptionPane.INFORMATION_MESSAGE);
            if (confirmSound == JOptionPane.YES_OPTION) {
                // real setting
                music = true;
                sfx = true;
            }
            if (gameState != STATE.Edit && music) audioplayer_.getMusic("music").loop();
        }
    }
    // TODO: implement this to game engine, also fix lag
    public void errorCaughtButContinued() {
        // FIXME: if error caught but continued, still work in progress
        if (game_.gameState == STATE.GameBeta) {
            levels.resetMethod();
            if (game_.music) {
                audioplayer_.getMusic(audioplayer_.currentMusic).play();
            }
            if (game_.sfx) audioplayer_.getSound("click_sound").play();
        }
    }

    // for memory info test
    public int index = 0;
    public ArrayList<Integer> arrayList = new ArrayList<>();
    int mb = 1024;

    Runtime rt = Runtime.getRuntime();
    long prevTotal = 0;
    long prevFree = rt.freeMemory();

    long totalMem, freeMem, usedMem;

    public void ramInfo() {
        if (index < 2_000_000) {
            long total = rt.totalMemory();
            long free = rt.freeMemory();
            if (total != prevTotal || free != prevFree) {
                /*System.out.println(
                        String.format("#%s, Total: %s, Free: %s, Diff: %s",
                                index,
                                total,
                                free,
                                prevFree - free));*/
                totalMem = total/mb;
                freeMem = free/mb;
                usedMem = (prevFree - free)/mb;
                // reset
                prevTotal = total;
                prevFree = free;
            }
            arrayList.add(index);
            index++;
        } else {
            index = 0;
            arrayList.clear();
        }
    } // weird stats

    // info, requires update, located at tick()
    public String ramInfo2;
    public String ramInfo = "";
    public String FPS = "";
    public String objectInfo = "";

    @Override
    public void tick() {
        // info stuff, requires update
        /*ramInfo();
        ramInfo = "Memory: " + usedMem + "/" + totalMem;*/
        if (!smoothFix) FPS = "FPS/TPS: " + throwFrames + "/" + throwTick;
        else FPS = "FPS: " + throwFrames;
        objectInfo = "Objects: " + handler.object.size();
        // class tick
        // if (loadstate == 100) world.tick();
        window.tick();
        keyMain.tick();
        if (!paused) {
            // handler tick
            if (gameState == STATE.GameBeta) {
                if (multiplayer) {
                    if (hud_.HEALTH != 0 || hud2_.HEALTH != 0) {
                        handler.tick();
                    }
                } else {
                    if (hud_.HEALTH != 0) {
                        handler.tick();
                    }
                }
            }
            else if (gameState != STATE.Shop) handler.tick();
            // custom
            if (gameState == STATE.Game) {
                // i dunno why i'm doing this
                currentGameStateIsBeta = false;
                // tick codes here
                spawner.tick();
                hud.tick();
                if (multiplayer) hud2.tick();
                // end codes
                if (multiplayer) {
                    if (hud_.HEALTH == 0 && hud2_.HEALTH == 0) {
                        endCodes();
                    }
                } else {
                    if (hud_.HEALTH == 0) {
                        endCodes();
                    }
                }
            }
            if (gameState == STATE.LevelSelect || gameState == STATE.GameBeta || gameState == STATE.Edit) {
                // i dunno why i'm doing this
                currentGameStateIsBeta = true;
                // tick codes here
                levels.tick();
                hud.tick();
                if (multiplayer) hud2.tick();
                // end codes beta
                if (multiplayer) {
                    if (hud_.HEALTH == 0 && hud2_.HEALTH == 0 && gameState == STATE.GameBeta) {
                        endCodesBeta();
                    }
                } else {
                    if (hud_.HEALTH == 0 && gameState == STATE.GameBeta) {
                        hud2.HEALTH = 0;
                        endCodesBeta();
                    }
                }
            }
            if (gameState == STATE.Menu || gameState == STATE.Help ||
                    gameState == STATE.End || gameState == STATE.Select || gameState == STATE.Options) {
                menu.tick();
            }
            if (gameState == STATE.Load) {
                load.tick();
            }
            if (customTicksBoolean && gameState != STATE.GameBeta && gameState != STATE.Game) {
                // only trigger this at menu only
                customTickLoopMethod();
            }
            if (gameState == STATE.Edit){
                editor.tick();
            }
        }
    }

    // the actual image to be rendered
    public static BufferedImage image;

    @Override
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        // init image
        image = ImageProcessing.processHandlerInit(WIDTH, HEIGHT);

        Graphics g = image.createGraphics();

        // main render

        /*rendertest.render(g);

        if (loadstate == 100) tiles_.tilesarray[1].render(g, 0, 0);
        if (loadstate == 100) world.render(g);*/

        // camera codes, i like this part
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform at = g2d.getTransform();

        g.fillRect(0, 0, WIDTH, HEIGHT);

        // pre camera codes
        at.scale(sx, sy);
        at.translate(tx, ty);
        // post camera codes
        g2d.setTransform(at);

        // bg
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // grid
        if (gridLines){
            g.setColor(Color.BLACK);
            for(int xAxis = 0; xAxis <= 680; xAxis += 10){
                for(int yAxis = 0; yAxis <= 730; yAxis += 10){
                    g.drawRect(xAxis, yAxis, 10, 10);
                }
            }
            g.setColor(Color.ORANGE);
            for(int xAxis = 0; xAxis <= 650; xAxis += 50){
                for (int yAxis = 0; yAxis <= 700; yAxis += 50){
                    g.drawRect(xAxis, yAxis, 50, 50);
                }
            }
            g.setColor(Color.RED);
            for(int xAxis = 0; xAxis <= 600; xAxis += 200){
                for (int yAxis = 0; yAxis <= 600; yAxis += 200){
                    g.drawRect(xAxis, yAxis, 200, 200);
                }
            }
        }

        // windows bounds
        g.setColor(Color.GREEN);
        g.drawRect(0, 0, WIDTH, HEIGHT);

        // handler, what a mess
        try {
            handler.render(g);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gameState == STATE.Credits || gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
            menu.render(g);
        }

        if (gameState == STATE.LevelSelect) {
            Font fnt = new Font("mojangles", 1, 16);
            g.setFont(fnt);
            levels.render(g);
        }

        if (gameState == STATE.Game || gameState == STATE.GameBeta || gameState == STATE.Edit) {
            Font fnt = new Font("mojangles", 1, 10);
            g.setFont(fnt);
            levels.render(g);
            if (!hideHud) {
                hud.render(g);
                if (multiplayer) hud2.render(g);
            }
        }

        if (gameState == STATE.Edit){
            editor.render(g);
        }

        if (gameState == STATE.Shop) {
            shop.render(g);
        }

        if (gameState == STATE.Load) {
            load.render(g);
        }

        // if still loading?
        if (loadstate != 100 && gameState != STATE.Load) {
            if (gameState == STATE.Game || gameState == STATE.GameBeta || gameState == STATE.Edit)
                g.drawString(stringsforloading, WIDTH / 2 - 50, HEIGHT - 75);
            else g.drawString(stringsforloading, 0, 20);
        }

        // info graphics
        g.setColor(Color.green);
        if (gameState != STATE.LevelSelect && !hideHud){
            /*g.drawString(ramInfo2,
                    WIDTH - getTextWidth(g, ramInfo2) - 10, HEIGHT - 125);*/
            g.drawString(ramInfo,
                    WIDTH - getTextWidth(g, ramInfo) - 10, HEIGHT - 100);
            g.drawString(FPS,
                    WIDTH - getTextWidth(g, FPS) - 10, HEIGHT - 75);
            g.drawString(objectInfo,
                    WIDTH - getTextWidth(g, objectInfo) - 10, HEIGHT - 50);
        }

        if (paused) {
            Font fnthead = new Font("mojangles", 1, 40);
            g.setFont(fnthead);
            g.setColor(Color.red);
            g.drawString("paused_", WIDTH / 2 - getTextWidth(g, "paused_")/2, HEIGHT / 2);
        }

        g = bs.getDrawGraphics();

        if (game_.backgroundRender){
            // grids (background), aka n00b l00p
            g.setColor(Color.black);
            for (int a = 50, b = 50; a <= 1250 && b <= 650; a += 10) {
                g.drawRect(a, b, 10, 10);
                if (a == 1250) {
                    a = 40;
                    b += 10;
                }
            }
            // pixels (background), aka rand0m n00b
            g.setColor(Color.green);
            int x = ((50 + (int)(Math.random() * 1250)) / 10) * 10, y = ((50 + (int)(Math.random() * 650)) / 10) * 10;
            g.fillRect(x, y, 10, 10);
        }

        // image processing codes
        image = ImageProcessing.postProcessing(image);

        // draw everything
        g.drawImage(image, 0, 0, null);
        bs.show();

        // xuggler shit, run this everytime
        if (recordVideo && writer.isOpen()){
            BufferedImage bgrScreen = convertToType(image,BufferedImage.TYPE_3BYTE_BGR);
            writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
        }

        // dispose properly
        g.dispose();
    }



    public static BufferedImage convertToType(BufferedImage sourceImage, int targetType) {

        BufferedImage image;

        // if the source image is already the target type, return the source image

        if (sourceImage.getType() == targetType) {

            image = sourceImage;

        }

        // otherwise create a new image of the target type and draw the new image

        else {

            image = new BufferedImage(sourceImage.getWidth(),

                    sourceImage.getHeight(), targetType);

            image.getGraphics().drawImage(sourceImage, 0, 0, null);

        }

        return image;

    }

    // the ultimate fix, for center
    public static int getTextWidth(Graphics g, String message){
        return g.getFontMetrics().stringWidth(message);
    }

    // DO NOT USE! IT WAS WEIRD AF!, IT'S LIKE POKEMON AND EARTHBOUND CAMERA!
    public gamecamera_ getGameCamera() {
        return gamecamera;
    }

    // camera vars
    double tx = 0, ty = 0, sx = 1, sy = 1;
    public void cameraControls(double tx, double ty, double sx, double sy){
        this.tx = tx;
        this.sx = sx;
        this.ty = ty;
        this.sy = sy;
    }

    int restartCooldown = 100;

    // halt
    public void endCodes() {
        hud.HEALTH = 100;
        hud2.HEALTH = 100;
        // FIXME: buggy state here
        if (gameState != STATE.Load && gameState != STATE.Edit) gameState = STATE.End;
        handler.clearEnemies();
        if (customTicksBoolean) customTicksMethod();
        else {
            for (int i = 1; i <= 50; i++)
                handler.addObject(new spicymenu_(r.nextInt(WIDTH - 20), r.nextInt(HEIGHT - 20), ID.Spicy, handler));
        }
    }
    public void endCodesBeta() {
        if (!autoRestart) {
            if (restartCooldown == 100) {
                if (gameState == STATE.GameBeta && audioplayer_.currentMusic != "")
                    audioplayer_.getMusic(audioplayer_.currentMusic).pause();
                handler.removeAllSelectedObjects(ID.Trail);
            }
            restartCooldown--;
            if (restartCooldown == 0) {
                if (music) audioplayer_.getMusic("game_over").loop();
                restartCooldown = 100;
                endCodes();
            }
        } else {
            if (restartCooldown == 100) {
                if (gameState == STATE.GameBeta && audioplayer_.currentMusic != "")
                    audioplayer_.getMusic(audioplayer_.currentMusic).stop();
                handler.removeAllSelectedObjects(ID.Trail);
            }
            restartCooldown--;
            if (restartCooldown == 0) {
                restartCooldown = 100;
                levels.resetMethod();
                if (gameState == STATE.GameBeta && audioplayer_.currentMusic != "") {
                    audioplayer_.getMusic(audioplayer_.currentMusic).play();
                }
            }
        }
    }
    public void restartBeta() {
        if (paused) paused = false;
        // restart
        if ((gameState == STATE.GameBeta || gameState == STATE.End) && currentGameStateIsBeta) {
            levels.resetMethod();
            if (audioplayer_.currentMusic != "") audioplayer_.getMusic(audioplayer_.currentMusic).stop();
            if (music) audioplayer_.getMusic(audioplayer_.currentMusic).play(); // library change error
            if (sfx) audioplayer_.getSound("click_sound").play();
        }
    }
    
    // reset
    public void easy(){
        gameState = STATE.Game;
        hud.resetTimer();
        handler.clearEnemies();
        hud.setLevel(1);
        spawn_.scoreKeep = 0;
        levels_.scoreKeep = 0;
        // p1 reset
        hud.setScore(0);
        hud.setXp(0);
        hud_.HEALTH = 100;
        handler_.spdp1 = 5;
        hud_.bounds = 0;
        // p2 reset
        hud2.setScore(0);
        hud2.setXp(0);
        hud2_.HEALTH = 100;
        handler_.spdp2 = 5;
        hud2_.bounds = 0;
        // reset shop
        shop_.B1 = 100;
        shop_.B2 = 100;
        shop_.B3 = 100;
        shop_.B4 = 100;
        shop_.B5 = 100;
        shop_.B6 = 100;
        // spawn
        handler.addObject(new basicenemy_(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.BasicEnemy, handler));
        diff = 0;
        gameloopFix(); // gameloop fix
        // testing purposes
        hud.hudTick = 0;
    }
    public void medium(){
        gameState = STATE.Game;
        hud.resetTimer();
        handler.clearEnemies();
        hud.setLevel(1);
        spawn_.scoreKeep = 0;
        levels_.scoreKeep = 0;
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
        // reset shop
        shop_.B1 = 1000;
        shop_.B2 = 1000;
        shop_.B3 = 1000;
        shop_.B4 = 1000;
        shop_.B5 = 1000;
        shop_.B6 = 1000;
        // spawn
        handler.addObject(new hardenemy_(r.nextInt(WIDTH - 1), r.nextInt(HEIGHT - 1), ID.HardEnemy, handler));
        diff = 1;
        gameloopFix(); // gameloop fix
        // testing purposes
        hud.hudTick = 0;
    }

    // pause
    public void gameloopFixDeltaOff(){
        // gameloop fix
        // edit: when pausing the tick, DO NOT WRITE DELTA TO 0!, it will unsync!
        // only write access when resetting

        levels.lastTime = System.nanoTime();
//        levels.delta = 0;

        spawner.lastTime = System.nanoTime();
//        spawner.delta = 0;

        hud.lastTime = System.nanoTime();
//        hud.delta = 0;

        hud2.lastTime = System.nanoTime();
//        hud2.delta = 0;

        // main gameloop vars fix, no need to use this, i extracted the vars to while(running)
        // edit: i was wrong lol

        lastTime = System.nanoTime();
//        delta = 0;
    }
    // restart
    public void gameloopFix(){
        levels.delta = 0;
        levels.lastTime = System.nanoTime();
        spawner.delta = 0;
        spawner.lastTime = System.nanoTime();
        hud.lastTime = System.nanoTime();
        hud.delta = 0;
        hud2.lastTime = System.nanoTime();
        hud2.delta = 0;
        // main gameloop vars fix, no need to use this, i extracted the vars to while(running)
        // edit: i was wrong lol
        delta = 0;
        lastTime = System.nanoTime();
    }

    // test custom codes
    public boolean customTicksBoolean = false;
    int customTicks = 0;
    int timer, timer2;
    gameobject_ boss;

    public void customTicksMethod() {
        System.out.println("Custom Code Activated!");
        // test entities
//        handler.addObject(new ghost_(r.nextInt(WIDTH - 10), r.nextInt(HEIGHT - 10), ID.GHOST, 1, 1));
//        handler.addObject(new starwrathenemy_(0, 0, ID.STARGHOST, handler, 30, 30, 0));
//        handler.addObject(new crazyboss_(WIDTH / 2 - 128, HEIGHT / 2 - 128, ID.CrazyBoss, handler, 0, 0, 0));
        /*boss = handler.addObject(new skullface_((WIDTH / 2) - 128, (HEIGHT) - 128, ID.Xgamer, handler,
                0, -5, 0));
        timer = 100;
        timer2 = 200;*/

        /*handler.addObject(new moresmarterenemy_(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MoreSmarter,
                handler, 20, 20));*/

//        handler.addObject(new osc_(0, 0, ID.NULL));

        /*handler.addObject(new portalblue_(r.nextInt(WIDTH-64), r.nextInt(HEIGHT-64), ID.PortalBlue,
                handler,0, 0));
        handler.addObject(new portalred_(r.nextInt(WIDTH-64), r.nextInt(HEIGHT-64), ID.PortalRed,
                handler,0, 0));*/

        // run this code once
        crazyboss_.minRotate = -45;
        crazyboss_.maxRotate = 45;
        crazyboss_.rotateThisTick = 20;

//        handler.addObject(new testSpawnScreenLimit(0, 0, ID.NULL, handler));
//        handler.addObject(new water_(0, 0, ID.NULL));
//        handler.addObject(new tictactoe_(0, 0, ID.NULL, this));
//        handler.addObject(new conwaysgameoflife_(0, 0, ID.NULL, this));
//        handler.addObject(new pathfinder_(0, 0, ID.NULL, this));
//        handler.addObject(new spike_(0, 0, ID.Spikes, 0, 0, FACE.SOUTH));
        handler.addObject(new spikewall_(0, 0, ID.NULL, handler));
    }

    public void customTickLoopMethod(){
        // run this code once the tick was called
        customTicks++;

        // old temp codes
        /*if (customTicks == 100) {
            customTicks = 0;
            if (crazyboss_.increment) crazyboss_.increment = false;
            else crazyboss_.increment = true;
        }*/

        if (customTicks == 100) {
            customTicks = 0;
        }

        /*if (timer == 0) {
            boss.setVelY(0);
        } else timer--;
        if (timer2 == 0) {
            timer2 = 100;
            handler.addObject(new laserpointer_((int) boss.getX() + 169, (int) boss.getY() + 93, ID.Laser, handler, 30, 30, 0));
            handler.addObject(new laserpointer_((int) boss.getX() + 57, (int) boss.getY() + 101, ID.Laser, handler, 30, 30, 0));
        } else timer2--;*/

    }
}
