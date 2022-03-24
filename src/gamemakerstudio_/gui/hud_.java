/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.gui;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.*;
import gamemakerstudio_.misc.audiostuff.audioplayer_;
import gamemakerstudio_.misc.entitystuff.GunManager;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;

/**
 *
 * @author ACER
 */
public class hud_ {
    handler_ handler;
    game_ game;
    public static int heartsTaken = 0;
    public static int bounds = 0;
    public static int HEALTH = 100;
    private int blueValue = 255;

    public static int score = 0;
    public static int level = 1;
    public static int xp = 0;

    public int centiseconds = 0;
    public int seconds = 0;
    public int minutes = 0;

    // vars for gameloop fix
    public static long lastTime = System.nanoTime();
    double amountOfTicks = 100.0;
    double ns = 1000000000 / amountOfTicks;
    public static double delta = 0;

    // testing purposes
    public int hudTick = 0;

    public void tick() {
        // gameloop
        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;
        while (delta >= 1) {
            delta--;
            HEALTH = MathUtil.clamp(HEALTH, 0, 100 + (bounds / 2));
            blueValue = HEALTH * 2;
            blueValue = MathUtil.clamp(blueValue, 0, 255);
            if (HEALTH != 0) {
                if (!game_.isInvincible && game_.gameState != STATE.Edit && game_.gameState != STATE.End) {
                    score++;
                    xp++;
                }
            }
            // crappy watch
            if (((game_.gameState == STATE.GameBeta/* && levels_.isPlaying*/) || game_.gameState == STATE.Game)) {
                hudTick++;
//                System.out.println(hudTick);
                centiseconds++;
                if (centiseconds == 100) {
                    centiseconds = 0;
                    seconds++;
                }
                if (seconds == 60) {
                    centiseconds = 0;
                    seconds = 0;
                    minutes++;
                }
            }
        }
    }
    public void render(Graphics g) {
        // player hud
        // health bar
        // TODO: fix pos, must snap to grid
        g.setColor(Color.gray);
        g.fillRect(15, 18, 200 + bounds, 32);
        g.setColor(new Color(75, 0, blueValue));
        g.fillRect(15, 18, HEALTH * 2, 32);
        // boarder
        g.setColor(Color.CYAN);
        g.drawRect(15, 18, 200 + bounds, 32);
        // level and score and health and xp
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Experience: " + xp, 15, 80);
        g.drawString("Health: " + (HEALTH) + "/" + (100 + (bounds / 2)) + ", Hearts: " + heartsTaken + ", " +
                "Gun: " + GunManager.playerOneGunLoadOut, 15, 15);
        g.setColor(Color.WHITE);
        if (game_.gameState == STATE.Game) g.drawString("Level: " + level, 15, game_.HEIGHT - 50);
        else g.drawString("Level: " + audioplayer_.currentMusic, 15, game_.HEIGHT - 50);
        // render crappy watch
        g.setColor(Color.green);
        String watchThingy = tellTime();
        g.drawString(watchThingy, /*game_.WIDTH / 2 - 50*/
                game_.WIDTH/2 - game_.getTextWidth(g, watchThingy)/2, game_.HEIGHT - 100);
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getXp() {
        return xp;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void resetTimer(){
        centiseconds = 0;
        seconds = 0;
        minutes = 0;
    }
    public String tellTime() {
        String tempMilli, tempSec, tempMin;
        if (centiseconds / 10 == 0) tempMilli = "0" + centiseconds;
        else tempMilli = String.valueOf(centiseconds);
        if (seconds / 10 == 0) tempSec = "0" + seconds;
        else tempSec = String.valueOf(seconds);
        if (minutes / 10 == 0) tempMin = "0" + minutes;
        else tempMin = String.valueOf(minutes);
        return tempMin + ":" + tempSec + ":" + tempMilli;
    }
}
