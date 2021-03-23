/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.world;

import gamemakerstudio_.entities.*;
import gamemakerstudio_.entities.boss.creeperboss_;
import gamemakerstudio_.entities.boss.skullface_;
import gamemakerstudio_.game_;
import gamemakerstudio_.gui.hud_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.util.Random;

/**
 *
 * @author ACER
 */
public class spawn_ {
    
    private handler_ handler;
    private hud_ hud;
    private game_ game;
    private Random r = new Random();

    // game vars
    int timer, timer2;
    gameobject_ boss;
    
    public static int scoreKeep = 0;
    
    public spawn_(handler_ handler, hud_ hud, game_ game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    // run dis code once
    public void difficulty(int diff) {
        // easy
        if (diff == 0) {
            handler.addObject(new basicenemy_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                    ID.BasicEnemy, handler));
            handler.addObject(new heart_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                    ID.HeartFriend, handler, 0, 0));
            handler.addObject(new basecircle_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                    ID.BaseCircle, handler, 0, 0, 0));
            handler.addObject(new laserpointer_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                    ID.Laser, handler, 30, 30, 0));
            if (hud.getLevel() == 10){
                // remove objects except players
                handler.removeObjectsExceptPlayers();
                boss = handler.addObject(new skullface_((game_.WIDTH / 2) - 128, (game_.HEIGHT) - 128, ID.Xgamer, handler, 0, -5, 0));
                timer = 100;
                timer2 = 200;
            }
        }
        // medium
        if (diff == 1) {
            handler.addObject(new hardenemy_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                    ID.HardEnemy, handler));
            handler.addObject(new basecircle_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                    ID.BaseCircle, handler, 0, 0, 0));
            handler.addObject(new laserpointer_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                    ID.Laser, handler, 30, 30, 0));
            if (hud.getLevel() % 3 == 0)
                handler.addObject(new fastenemy_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                        ID.FastEnemy, handler));
            if (hud.getLevel() % 5 == 0)
                handler.addObject(new smartenemy_(r.nextInt(game_.WIDTH - 10), r.nextInt(game_.HEIGHT - 10),
                        ID.SmartEnemy, handler, 3, 3));

            if (hud.getLevel() == 10) {
                // remove objects except players
                handler.removeObjectsExceptPlayers();
                // boss code beta
                boss = handler.addObject(new creeperboss_((game_.WIDTH / 2) - 200, -253, ID.CreeperBoss, handler, 0, 5, 0));
                timer = 50;
                timer2 = 200;
            }
        }
        // hardmode
    }

    // vars for gameloop fix
    public long lastTime = System.nanoTime();
    double amountOfTicks = 100.0;
    double ns = 1000000000 / amountOfTicks;
    public double delta = 0;

    public void tick() {
        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;

        while (delta >= 1) {
            delta--;
            scoreKeep++;
            if (scoreKeep == 100) {
                scoreKeep = 0;
                hud.setLevel(hud.getLevel() + 1);
                difficulty(game.diff);
            }
            // boss tick, skull
            if (game.diff == 0 && hud.getLevel() >= 10){
                if (timer == 0) {
                    boss.setVelY(0);
                } else timer--;
                if (timer2 == 0) {
                    timer2 = 100;
                    handler.addObject(new laserpointer_((int) boss.getX() + 169, (int) boss.getY() + 93, ID.Laser, handler, 30, 30, 0));
                    handler.addObject(new laserpointer_((int) boss.getX() + 57, (int) boss.getY() + 101, ID.Laser, handler, 30, 30, 0));
                } else timer2--;
            }
            // boss tick, creeper
            if (hud.getLevel() >= 10 && game.diff == 1){
                if (timer == 0)
                    boss.setVelY(0);
                else timer--;

                if (timer2 == 0) {
                    if (boss.getVelX() == 0) boss.setVelX(20);
                    int spawn = r.nextInt(5);
                    if (spawn == 0) handler.addObject(new tnt_((int) boss.getX() + 200, (int) boss.getY() + 200, ID.TNT, handler, (r.nextInt(5 - -5) + -5), 5));
                } else timer2--;
            }
        }

    }
}
