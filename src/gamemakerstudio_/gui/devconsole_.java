package gamemakerstudio_.gui;

import gamemakerstudio_.entities.*;
import gamemakerstudio_.entities.experimental.*;
import gamemakerstudio_.game_;
import gamemakerstudio_.misc.*;
import gamemakerstudio_.misc.audiostuff.audioplayer_;
import gamemakerstudio_.misc.entitystuff.FACE;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.handler_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class devconsole_ extends JFrame implements ActionListener, KeyListener {
    // random
    Random r = new Random();
    // classes
    game_ game;
    handler_ handler;
    hud_ hud;
    hud2_ hud2;
    // temp text
    String latest, old, oldest;
    // Js used
    JPanel panel;
    JLabel history_text1, history_text2, history_text3;
    JTextField console_text = new JTextField();
    JButton submit = new JButton("SUBMIT");
//        JScrollPane scrollPane = new JScrollPane();
    public devconsole_ (game_ game, handler_ handler, hud_ hud, hud2_ hud2) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
        this.hud2 = hud2;

        // history (disposes after 3, might fix later)
        history_text1 = new JLabel();
        history_text2 = new JLabel();
        history_text3 = new JLabel();

        // set panels
        panel = new JPanel(new GridLayout(5, 0));
        panel.add(history_text3);
        panel.add(history_text2);
        panel.add(history_text1);
        panel.add(console_text);
        panel.add(submit);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Adding the listeners to components..
        console_text.addKeyListener(this);
        add(panel);
        setTitle("Developer Console");
        setSize(500,200);
    }
    // important stuff
    public void performCommands() {
        // time
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        // try to parse
        String keyBuffer = console_text.getText();
        String[] list = keyBuffer.split("\\s+");
        ArrayList<String> tokens = new ArrayList<String>();
        for (int i = 0; i < list.length; i++){
            tokens.add(list[i]);
        }
        try {
            // your custom codes here
            // token 0 == command
            // token 1, 2, 3, ... == arg (start at 1)
            switch (tokens.get(0)) {
                case "spawn":
                    if (tokens.size() == 2){
                        tokens.add(String.valueOf(r.nextInt(game_.WIDTH)));
                        tokens.add(String.valueOf(r.nextInt(game_.HEIGHT)));
                        tokens.add(String.valueOf(r.nextInt(50)));
                        tokens.add(String.valueOf(r.nextInt(50)));
                        tokens.add(String.valueOf(r.nextInt(10)));
                    }
                    if(summonEntity(tokens.get(1), Float.parseFloat(tokens.get(2)), Float.parseFloat(tokens.get(3)),
                            Float.parseFloat(tokens.get(4)), Float.parseFloat(tokens.get(5)), Float.parseFloat(tokens.get(6))))
                        updateConsole(now + ": Spawned " + tokens.get(1) + " at " + tokens.get(2) + ", " + tokens.get(3));
                    else
                        updateConsole("Syntax error: " + tokens.get(1) + " is not a valid entity!");
                    break;
                case "kill":
                    if(removeEntities(tokens.get(1)))
                        updateConsole(now + ": Killed " + tokens.get(1));
                    else
                        updateConsole("Syntax error: " + tokens.get(1) + " is not a valid entity!");
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "ldm":
                    game_.ldm = !game_.ldm;
                    updateConsole(now + ": LDM " + game_.ldm);
                    break;
                case "god":
                    game_.isInvincible = !game_.isInvincible;
                    updateConsole(now + ": GOD " + game_.isInvincible);
                    break;
                case "gridLines":
                    game_.gridLines = !game_.gridLines;
                    updateConsole(now + ": Grid Lines " + game_.gridLines);
                    break;
                case "autoRestart":
                    game_.autoRestart = !game_.autoRestart;
                    updateConsole(now + ": Auto-restart " + game_.autoRestart);
                    break;
                case "metronomeSounds":
                    game_.metronomeSounds = !game_.metronomeSounds;
                    updateConsole(now + ": Metronome Sounds " + game_.metronomeSounds);
                    break;
                case "music":
                    if (game_.music) {
                        game_.music = false;
                        audioplayer_.getMusic("null").play(); // library change error
                        audioplayer_.stopRandomGenMusic(); // library change error
                    }
                    else {
                        game_.music = true;
                        // weird fix while in-game
                        if (game_.gameState == STATE.GameBeta) {
                            game_.gameState = STATE.Load;
                            game.levels.levelsList(game.levels.mx, game.levels.my);
                        }
                        // else audioplayer_.getMusic("music").loop(); // library change error
                        else audioplayer_.playRandomGenMusic(); // library change error
                    }
                    updateConsole(now + ": Music " + game_.music);
                    break;
                case "sfx":
                    if (game_.music) {
                        game_.sfx = false;
                        audioplayer_.getSound("null").play();
                    }
                    else {
                        game_.sfx = true;
                        audioplayer_.getSound("click_sound").play();
                    }
                    updateConsole(now + ": SFX " + game_.sfx);
                    break;
                case "allSound":
                    if (game_.music) {
                        game_.music = false;
                        game_.sfx = false;
                        audioplayer_.getSound("null").play();
                        audioplayer_.getMusic("null").play(); // library change error
                        audioplayer_.stopRandomGenMusic(); // library change error
                    }
                    else {
                        game_.music = true;
                        game_.sfx = true;
                        audioplayer_.getSound("click_sound").play();
                        // weird fix while in-game
                        if (game_.gameState == STATE.GameBeta) {
                            game_.gameState = STATE.Load;
                            game.levels.levelsList(game.levels.mx, game.levels.my);
                        }
                        // else audioplayer_.getMusic("music").loop(); // library change error
                        else audioplayer_.playRandomGenMusic(); // library change error
                    }
                    updateConsole(now + ": Music and SFX " + game_.music);
                    break;
                case "ai":
                    if (tokens.get(1).equals("p1")) {
                        game_.computerP1 = !game_.computerP1;
                        updateConsole(now + ": P1 AI " + game_.computerP1);
                    } else if (tokens.get(1).equals("p2")) {
                        game_.computerP2 = !game_.computerP2;
                        updateConsole(now + ": P2 AI " + game_.computerP2);
                    } else {
                        updateConsole("Syntax error: " + tokens.get(1) + " is not a valid player!");
                        updateConsole("Usage: ai [p1 | p2]");
                    }
                    break;
                case "esp":
                    if (tokens.get(1).equals("p1")) {
                        game_.espLineP1 = !game_.espLineP1;
                        updateConsole(now + ": P1 ESP " + game_.espLineP1);
                    } else if (tokens.get(1).equals("p2")) {
                        game_.espLineP2 = !game_.espLineP2;
                        updateConsole(now + ": P2 ESP " + game_.espLineP2);
                    } else {
                        updateConsole("Syntax error: " + tokens.get(1) + " is not a valid player!");
                        updateConsole("Usage: esp [p1 | p2]");
                    }
                    break;
                case "smoothfix":
                    game_.smoothFix = !game_.smoothFix;
                    // main gameloop vars fix, no need to use this, i extracted the vars to while(running)
                    // edit: i was wrong lol
                    game.delta = 0;
                    game.lastTime = System.nanoTime();
                    updateConsole(now + ": Smooth Fix " + game_.smoothFix);
                    break;
                case "load":
                    game_.gameState = STATE.Load;
                    updateConsole(now + ": Forced Load Screen");
                    break;
                case "playMusic":
                    if (game_.music)
                        audioplayer_.getMusic(tokens.get(1)).play();
                    else updateConsole("Syntax error: Couldn't play " + tokens.get(1) + ", MUSIC is FALSE!");
                    break;
                case "playSfx":
                    if (game_.sfx)
                        audioplayer_.getSound(tokens.get(1)).play();
                    else updateConsole("Syntax error: Couldn't play " + tokens.get(1) + ", SFX is FALSE!");
                    break;
                default:
                    updateConsole("Syntax error: " + tokens.get(0) + " is not a valid command!");
                    break;
            }
        } catch (Exception exception) {
            updateConsole("Syntax error: " + exception);
            exception.printStackTrace();
        }
    }
    public boolean summonEntity (String tempObject, float x, float y, float velX, float velY, float spawnTimer) {
        switch (tempObject) {
            // entity classes
            case "basicenemy_":
                handler.addObject(new basicenemy_((int)x, (int)y, ID.BasicEnemy, handler));
                return true;
            case "fastenemy_":
                handler.addObject(new fastenemy_((int)x, (int)y, ID.FastEnemy, handler));
                return true;
            case "hardenemy_":
                handler.addObject(new hardenemy_((int)x, (int)y, ID.HardEnemy, handler));
                return true;
            case "smartenemy_":
                handler.addObject(new smartenemy_((int)x, (int)y, ID.SmartEnemy, handler, 3, 3));
                return true;
            case "basecircle_":
                handler.addObject(new basecircle_((int)x, (int)y, ID.BaseCircle, handler, velX, velY, (int)spawnTimer));
                return true;
            case "bullethellgenerator_":
                // spawn bullethellgenerator_ 300 300 0 0 0
                handler.addObject(new bullethellgenerator_(x, y, ID.BULLETHELLGHOST, handler, game, (int)spawnTimer,
                        1, 1, 0, 0, 0, false,
                        false));
                return true;
            case "triangleshooter_":
                // spawn triangleshooter_ 300 300 0 0 50
                handler.addObject(new triangleshooter_(x, y, ID.TriangleShooter, handler, velX, velY, (int)spawnTimer, FACE.EAST));
                return true;
                // misc
            // spawn water_ 0 0 0 0 0
            case "water_":
                handler.addObject(new water_(x, y, ID.Water));
                return true;
            case "tictactoe_":
                handler.addObject(new tictactoe_(x, y, ID.SquareGame, game));
                return true;
            // spawn pathfinder_ 0 0 0 0 0
            case "pathfinder_":
                handler.addObject(new pathfinder_(x, y, ID.Pathfinder, game));
                return true;
                // spawn conwaysgameoflife_ 0 0 0 0 0
            case "conwaysgameoflife_":
                handler.addObject(new conwaysgameoflife_(x, y, ID.Conway, game));
                return true;
            // spawn moresmarterenemy_ 0 0 0 0 0
            case "moresmarterenemy_":
                handler.addObject(new moresmarterenemy_(x, y, ID.MoreSmarter, handler, 20, 20));
                return true;
            case "osc_":
                handler.addObject(new betterosc_(x, y, ID.OSC, game));
                return true;
            case "camera_":
                handler.addObject(new camera_(0, 0, ID.Camera, game));
                return true;
            case "rendertexture_":
                handler.addObject(new rendertexture_(game_.WIDTH/2 - (game_.WIDTH/2)/2, game_.HEIGHT/2 - (game_.HEIGHT/2)/2, ID.RenderTexture, game_.WIDTH/2, game_.HEIGHT/2, 0, 0));
                handler.addObject(new rendertexture_(game_.WIDTH/2 - (game_.WIDTH/4)/2, game_.HEIGHT/2 - (game_.HEIGHT/4)/2, ID.RenderTexture, game_.WIDTH/4, game_.HEIGHT/4, 0, 0));
                return true;
            case "raycast_":
                handler.addObject(new raycast_(0, 0, ID.Raycast, game));
                return true;
            case "mandelbrot_":
                handler.addObject(new mandelbrot_(0, 0, ID.Mandelbrot));
                return true;
            case "maze_":
                handler.addObject(new maze_(0, 0, ID.MazeGen, game));
                return true;
            case "julia_":
                handler.addObject(new julia_(0, 0, ID.Julia));
                return true;
            default:
                return false;
        }
    }
    public boolean removeEntities (String tempObjectID) {
        switch (tempObjectID) {
            // kill code that suck, and more detailed
            case "basicenemy_":
                handler.removeAllSelectedObjects(ID.BasicEnemy);
                return true;
            case "fastenemy_":
                handler.removeAllSelectedObjects(ID.FastEnemy);
                return true;
            case "hardenemy_":
                handler.removeAllSelectedObjects(ID.HardEnemy);
                return true;
                // experimental
            case "camera_":
                handler.removeAllSelectedObjects(ID.Camera);
                return true;
            case "water_":
                handler.removeAllSelectedObjects(ID.Water);
                return true;
            case "rendertexture_":
                handler.removeAllSelectedObjects(ID.RenderTexture);
                return true;
            case "tictactoe_":
                handler.removeAllSelectedObjects(ID.SquareGame);
                return true;
            case "conwaysgameoflife_":
                handler.removeAllSelectedObjects(ID.Conway);
                return true;
            case "bullethellgenerator_":
                handler.removeAllSelectedObjects(ID.BULLETHELLGHOST);
                handler.removeAllSelectedObjects(ID.BASECIRCLEGHOST);
                return true;
            case "osc_":
                handler.removeAllSelectedObjects(ID.OSC);
                return true;
            case "pathfinder_":
                handler.removeAllSelectedObjects(ID.Pathfinder);
                return true;
            case "raycast_":
                handler.removeAllSelectedObjects(ID.Raycast);
                return true;
            case "mandelbrot_":
                handler.removeAllSelectedObjects(ID.Mandelbrot);
                return true;
            case "maze_":
                handler.removeAllSelectedObjects(ID.MazeGen);
                return true;
            case "moresmarterenemy_":
                handler.removeAllSelectedObjects(ID.MoreSmarter);
                return true;
            case "triangleshooter_":
                handler.removeAllSelectedObjects(ID.TriangleShooter);
                return true;
            case "portal_":
                handler.removeAllSelectedObjects(ID.PortalBlue);
                handler.removeAllSelectedObjects(ID.PortalRed);
                return true;
            case "julia_":
                handler.removeAllSelectedObjects(ID.Julia);
                return true;
                // kill all
            case "all":
                handler.clearEnemies();
                // to kill players, use this instead
                hud_.HEALTH = 0;
                hud2_.HEALTH = 0;
                return true;
            case "player_":
                hud_.HEALTH = 0;
                return true;
            case "player2_":
                hud2_.HEALTH = 0;
                return true;
            case "enemies":
                handler.removeObjectsExceptPlayers();
                return true;
            default:
                return false;
        }
    }
    // info and fixes, quality of life
    public void keyReleased (KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            performCommands();
        }
        if (key == KeyEvent.VK_ESCAPE || key == 192) {
            this.dispose();
        }
    }
    public void actionPerformed(ActionEvent e) {
        performCommands();
    }
    public void swapHistory(){
        oldest = old;
        old = latest;
    }
    public void setText() {
        history_text3.setText(oldest);
        history_text2.setText(old);
        history_text1.setText(latest);
    }
    public void updateConsole(String latest) {
        swapHistory();
        this.latest = latest;
        setText();
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
}
