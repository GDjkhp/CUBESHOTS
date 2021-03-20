/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.misc;

import gamemakerstudio_.entities.CURSOR_POINTER;
import gamemakerstudio_.entities.RangeArea;
import gamemakerstudio_.entities.player2_;
import gamemakerstudio_.entities.player_;
import gamemakerstudio_.game_;
import gamemakerstudio_.gui.devconsole_;
import gamemakerstudio_.gui.hud2_;
import gamemakerstudio_.gui.hud_;
import gamemakerstudio_.gui.shop_;
import gamemakerstudio_.world.levels_;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import static gamemakerstudio_.gui.window_.frame;

/**
 *
 * @author ACER
 */
public class KeyInput extends KeyAdapter implements MouseListener {
    // class
    handler_ handler;
    game_ game;
    levels_ levels;
    hud_ hud;
    hud2_ hud2;
    devconsole_ devconsole;

    private Random r = new Random();

    public boolean[] keyDownP1 = new boolean[6];
    public boolean[] keyDownP2 = new boolean[6];

    // fix
    public boolean lazyFixForRandomLevels = true;
    public int delayCount = 100;

//    // north
//    private static int northVelY = -5;
//    // east
//    private static int eastVelX = 5;
//    // west
//    private static int westVelX = -5;
//    int cooldownp1 = 0, cooldownp2 = 0;
//    public static int defaultcooldown = 5;
    
    public KeyInput(handler_ handler, game_ game, hud_ hud, hud2_ hud2, levels_ levels, devconsole_ devconsole) {
        this.handler = handler;
        this.game = game;
        this.hud = hud;
        this.hud2 = hud2;
        this.levels = levels;
        this.devconsole = devconsole;
        keyDownP1[0] = false;
        keyDownP1[1] = false;
        keyDownP1[2] = false;
        keyDownP1[3] = false;
        keyDownP1[4] = false;
        keyDownP1[5] = false;
        keyDownP2[0] = false;
        keyDownP2[1] = false;
        keyDownP2[2] = false;
        keyDownP2[3] = false;
        keyDownP2[4] = false;
        keyDownP2[5] = false;
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // p1 misc
        if (key == KeyEvent.VK_E) {player_.isShooting = true; keyDownP1[4] = true;}
        if (key == KeyEvent.VK_Q) {player_.isDashing = true; keyDownP1[5] = true;}

        // p2 misc
        if (key == KeyEvent.VK_CONTROL) {player2_.isShooting = true; keyDownP2[4] = true;}
        if (key == KeyEvent.VK_SHIFT) {player2_.isDashing = true; keyDownP2[5] = true;}

        for (int i = 0; i < handler.object.size(); i++) {

            gameobject_ tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                // player one key map
                if (key == KeyEvent.VK_W) {tempObject.setVelY(-handler.spdp1); keyDownP1[0] = true;}
                if (key == KeyEvent.VK_S) {tempObject.setVelY(handler.spdp1); keyDownP1[1] = true;}
                if (key == KeyEvent.VK_A) {tempObject.setVelX(-handler.spdp1); keyDownP1[2] = true;}
                if (key == KeyEvent.VK_D) {tempObject.setVelX(handler.spdp1); keyDownP1[3] = true;}
            }

            if (tempObject.getId() == ID.Player2) {
                // player two key map
                if (key == KeyEvent.VK_UP) {tempObject.setVelY(-handler.spdp2); keyDownP2[0] = true;}
                if (key == KeyEvent.VK_DOWN) {tempObject.setVelY(handler.spdp2); keyDownP2[1] = true;}
                if (key == KeyEvent.VK_LEFT) {tempObject.setVelX(-handler.spdp2); keyDownP2[2] = true;}
                if (key == KeyEvent.VK_RIGHT) {tempObject.setVelX(handler.spdp2); keyDownP2[3] = true;}
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // player one key map
        if (key == KeyEvent.VK_W) keyDownP1[0] = false;
        if (key == KeyEvent.VK_S) keyDownP1[1] = false;
        if (key == KeyEvent.VK_A) keyDownP1[2] = false;
        if (key == KeyEvent.VK_D) keyDownP1[3] = false;
        if (key == KeyEvent.VK_E) keyDownP1[4] = false;
        if (key == KeyEvent.VK_Q) keyDownP1[5] = false;

        // player two key map
        if (key == KeyEvent.VK_UP) keyDownP2[0] = false;
        if (key == KeyEvent.VK_DOWN) keyDownP2[1] = false;
        if (key == KeyEvent.VK_LEFT) keyDownP2[2] = false;
        if (key == KeyEvent.VK_RIGHT) keyDownP2[3] = false;
        if (key == KeyEvent.VK_CONTROL) keyDownP2[4] = false;
        if (key == KeyEvent.VK_SHIFT) keyDownP2[5] = false;

        // p1 reset
        if (!keyDownP1[4]) {player_.isShooting = false; player_.cooldownp1 = 0;}
        if (!keyDownP1[5]) {player_.dashcooldown = 15;}

        // p2 reset
        if (!keyDownP2[4]) {player2_.isShooting = false; player2_.cooldownp2 = 0;}
        if (!keyDownP2[5]) {player2_.dashcooldown = 15;}

        for (int i = 0; i < handler.object.size(); i++) {
            
            gameobject_ tempObject = handler.object.get(i);
            
            if (tempObject.getId() == ID.Player) {
                // vertical movement
                if (!keyDownP1[0] && !keyDownP1[1]) tempObject.setVelY(0);
                // horizontal movement
                if (!keyDownP1[2] && !keyDownP1[3]) tempObject.setVelX(0);
            }
            
            if (tempObject.getId() == ID.Player2) {
                // vertical movement
                if (!keyDownP2[0] && !keyDownP2[1]) tempObject.setVelY(0);
                // horizontal movement
                if (!keyDownP2[2] && !keyDownP2[3]) tempObject.setVelX(0);
            }
        }

        // misc keys, doesn't require holding

        // misc, if not paused
        if (!game.paused) {
            // p2 gun switch
            if (key == KeyEvent.VK_SLASH) {
                if (game.multiplayer){
                    GunManager.playerTwoGunLoadOut = GunManager.switchWeapon(GunManager.playerTwoGunLoadOut);
                    if (game_.sfx) audioplayer_.getSound("click_sound").play();
                }
            }
            // p1 switch gun
            if (key == KeyEvent.VK_C) {
                GunManager.playerOneGunLoadOut = GunManager.switchWeapon(GunManager.playerOneGunLoadOut);
                if (game_.sfx) audioplayer_.getSound("click_sound").play();
            }
            // editor
            if (key == KeyEvent.VK_F) {
                levels.resetMethod();
                if (game.customTicksBoolean) game.customTicksMethod(); // remove this at future release
                game.gameState = STATE.Edit;
                levels.bpm = 0;
                levels.tpm = 0;
                levels.spm = 0;
                levels.difference = 0;
                levels.stepDifference = 0;
                audioplayer_.currentMusic = "null";
                if (game_.sfx) audioplayer_.getSound("click_sound").play();
                audioplayer_.getMusic("null").play(); // library change error
            }
            // shop
            if (key == KeyEvent.VK_SPACE) {
                if (game.gameState == STATE.Game) {
                    if (game_.music) {
                        audioplayer_.getMusic("shop_music").loop(); // library change error
                    }
                    game.gameState = STATE.Shop;
                }
                else if (game.gameState == STATE.Shop) {
                    if (game_.music) {
                        audioplayer_.getMusic("music").loop(); // library change error
                    }
                    game.gameState = STATE.Game;
                    game.gameloopFixDeltaOff(); // gameloop fix
                    if (game_.sfx) audioplayer_.getSound("click_sound").play();

                }
                if (game.gameState == STATE.Game)
                    if (game_.sfx) audioplayer_.getSound("click_sound").play();
            }
            // shop keys
            if (game.gameState == STATE.Game || game.gameState == STATE.Shop) {
                if (key == KeyEvent.VK_1) {
                    if (hud.getXp() >= shop_.B1) {
                        hud.setXp(hud.getXp() - shop_.B1);
                        shop_.B1 += shop_.B1;
                        hud_.bounds += 20;
                        if (game_.sfx) audioplayer_.getSound("click_sound").play();
                    }
                }
                if (key == KeyEvent.VK_2) {
                    if (hud.getXp() >= shop_.B2) {
                        hud.setXp(hud.getXp() - shop_.B2);
                        shop_.B2 += shop_.B2;
                        handler_.spdp1 += 5;
                        if (game_.sfx) audioplayer_.getSound("click_sound").play();
                    }
                }
                if (key == KeyEvent.VK_3) {
                    if (hud.getXp() >= shop_.B3) {
                        if (hud.HEALTH != (100 + (hud.bounds / 2))) {
                            hud.setXp(hud.getXp() - shop_.B3);
                            hud.HEALTH = (100 + (hud.bounds / 2));
                            if (game_.sfx) audioplayer_.getSound("click_sound").play();
                        }
                    }
                }
                if (key == KeyEvent.VK_4) {
                    if (hud2.getXp() >= shop_.B4) {
                        hud2.setXp(hud2.getXp() - shop_.B4);
                        shop_.B4 += shop_.B4;
                        hud2_.bounds += 20;
                        if (game_.sfx) audioplayer_.getSound("click_sound").play();
                    }
                }
                if (key == KeyEvent.VK_5) {
                    if (hud2.getXp() >= shop_.B5) {
                        hud2.setXp(hud2.getXp() - shop_.B5);
                        shop_.B5 += shop_.B5;
                        handler_.spdp2 += 5;
                        if (game_.sfx) audioplayer_.getSound("click_sound").play();
                    }
                }
                if (key == KeyEvent.VK_6) {
                    if (hud2.getXp() >= shop_.B6) {
                        if (hud2.HEALTH != (100 + (hud2.bounds / 2))) {
                            hud2.setXp(hud2.getXp() - shop_.B6);
                            hud2.HEALTH = (100 + (hud2.bounds / 2));
                            if (game_.sfx) audioplayer_.getSound("click_sound").play();
                        }
                    }
                }
            }
        }

        // misc, if paused

        // fullscreen
        if (key == KeyEvent.VK_F11){
            if (game.WIDTH == 1360/2) {
                game.WIDTH = 1360;
            }
            else {
                game.WIDTH = 1360/2;
            }
            frame.setPreferredSize(new Dimension(game_.WIDTH + 16, game_.HEIGHT + 39));
            frame.setMinimumSize(new Dimension(game_.WIDTH + 16, game_.HEIGHT + 39));
            frame.setMaximumSize(new Dimension(game_.WIDTH + 16, game_.HEIGHT + 39));
            frame.setSize(new Dimension(game_.WIDTH + 16, game_.HEIGHT + 39));
            frame.setLocationRelativeTo(null);
        }
        // smooth fix
        if (key == KeyEvent.VK_F12){
            if (game.smoothFix) game.smoothFix = false;
            else game.smoothFix = true;

            // main gameloop vars fix, no need to use this, i extracted the vars to while(running)
            // edit: i was wrong lol
            game.delta = 0;
            game.lastTime = System.nanoTime();
        }
        // tp to level select
        if (key == KeyEvent.VK_BACK_SPACE && game.gameState != STATE.Edit){
            // modified escape code
            if (game.paused) game.paused = false;
            if (audioplayer_.currentMusic != "") // TODO: this triggers a bug when currentmusic is not null, it stops the main menu music, same as the original escape and play new music
                if (game_.music) audioplayer_.getMusic(audioplayer_.currentMusic).pause();
            if (game.gameState != STATE.Menu)
                if (game_.sfx) audioplayer_.getSound("click_sound").play();
            // end codes
            if (game.gameState == STATE.Game || game.gameState == STATE.GameBeta) {
                game.endCodes();
            }
            // xbox feature escape
            handler.removeAllSelectedObjects(ID.CURSORSELECT);
            game.isSelecting = false;
            // actual code
            game.gameState = STATE.LevelSelect;
            if (game_.sfx) audioplayer_.getSound("tick").play();
            levels_.lazyDelayFix = 100;
        }
        // ldm
        if (key == KeyEvent.VK_L) {
            if (game.ldm) game.ldm = false;
            else game.ldm = true;

            if (game_.sfx) audioplayer_.getSound("click_sound").play();
        }
        // hud
        if (key == KeyEvent.VK_F1) {
            if (game.hideHud) game.hideHud = false;
            else game.hideHud = true;

            if (game_.sfx) audioplayer_.getSound("click_sound").play();
        }
        // god
        if (key == KeyEvent.VK_X) {
            if (game.isInvincible) game.isInvincible = false;
            else game.isInvincible = true;

            if (game_.sfx) audioplayer_.getSound("click_sound").play();
        }
        // quick game, speedrun
        if (key == KeyEvent.VK_F3) {
            // easy
//            game.easy();
            // medium
            game.medium();
            // sfx
            if (game_.sfx) audioplayer_.getSound("click_sound").play();
        }
        // multiplayer
        if (key == KeyEvent.VK_F2) {
            if (game.gameState != STATE.End) {
                if (game_.multiplayer) {
                    game_.multiplayer = false;
                    for (int i = handler.object.size() - 1; i >= 0; i--) {
                        gameobject_ tempObject = handler.object.get(i);
                        if (tempObject.getId() == ID.Player2 || tempObject.getId() == ID.P2Range)
                            handler.removeObject(tempObject);
                    }
                    if (game_.sfx) audioplayer_.getSound("alert").play();
                } else {
                    if (game_.sfx) audioplayer_.getSound("alert").play();
                    game_.multiplayer = true;
                    handler.addObject(new player2_(game_.WIDTH - 128, 200, ID.Player2, handler, hud2));
                    handler.addObject(new RangeArea(0, 0, ID.P2Range, handler));
                }
            }
        }
        // console
        if (key == 192) {
            devconsole.setVisible(true);
        }

        // IMPORTANT KEYS

        // restart
        if (key == KeyEvent.VK_Z) {
            // levels.isPlaying = false; // bug fix test
            game.restartBeta();
            if ((game.gameState == STATE.Game || game.gameState == STATE.End) && game.diff == 0 && !game.currentGameStateIsBeta) {
                // reset
                game.easy();
                if (game_.sfx) audioplayer_.getSound("click_sound").play();
            }
            if ((game.gameState == STATE.Game || game.gameState == STATE.End) && game.diff == 1 && !game.currentGameStateIsBeta) {
                // reset
                game.medium();
                if (game_.sfx) audioplayer_.getSound("click_sound").play();
            }
        }
        // random
        if (key == KeyEvent.VK_R && lazyFixForRandomLevels && delayCount == 0) {
            // levels.isPlaying = false; // bug fix test
            if (game.paused) game.paused = false;
            if (audioplayer_.currentMusic != "")
                if (game_.music) audioplayer_.getMusic(audioplayer_.currentMusic).pause();
            // pre lazy fix
            lazyFixForRandomLevels = false;
            delayCount = 100;
            // xbox feature escape
            handler.removeAllSelectedObjects(ID.CURSORSELECT);
            game.isSelecting = false;
            // insert level randomizer
            game.gameState = STATE.LevelSelect;
            int x = ((r.nextInt(6) * 100) + 50) + 25;
            int y = ((r.nextInt(7) * 100) + 50) + 25;
            levels.page = r.nextInt(levels.maxPage) + 1;
            if (game_.sfx) audioplayer_.getSound("click_sound").play();
            levels.levelsList(x, y);
            // post lazy fix
            lazyFixForRandomLevels = true;
        }
        // main menu TODO: merge pause here
        if (key == KeyEvent.VK_ESCAPE) {
            // levels.isPlaying = false; // bug fix test
            if (game.paused) game.paused = false;
            if (audioplayer_.currentMusic != "")
                if (audioplayer_.getMusic(audioplayer_.currentMusic) != null)
                    if (game_.music) audioplayer_.getMusic(audioplayer_.currentMusic).pause();
            if (game.gameState != STATE.Menu)
                if (game_.sfx) audioplayer_.getSound("click_sound").play();
            // end codes
            if (game.gameState == STATE.Game || game.gameState == STATE.GameBeta) {
                game.endCodes();
            }
            // xbox feature escape
            handler.removeAllSelectedObjects(ID.CURSORSELECT);
            game.isSelecting = false;
            // escape, pls fix this
            game.gameState = STATE.Menu;
        }
        // pause
        if (key == KeyEvent.VK_P) {
            // levels.isPlaying = false; // bug fix test
            if (game.gameState == STATE.Game || game.gameState == STATE.Edit) {
                if (game_.paused) game_.paused = false;
                else game_.paused = true;

                game.gameloopFixDeltaOff(); // gameloop fix
                if (game_.sfx) audioplayer_.getSound("click_sound").play();
            }
            if (game.gameState == STATE.GameBeta) {
                if (game.paused) {
                    game.paused = false;
                    if (game_.music)audioplayer_.getMusic(audioplayer_.currentMusic).resume();
                } else {
                    game.paused = true;
                    if (game_.music)audioplayer_.getMusic(audioplayer_.currentMusic).pause();
                }
                if (game_.sfx) audioplayer_.getSound("click_sound").play();

                game.gameloopFixDeltaOff(); // gameloop fix
            }
        }
    }

    // i did this to lazily fix r button, which suck
    public void tick(){
        if(lazyFixForRandomLevels && delayCount != 0){
            delayCount--;
        } else if (!lazyFixForRandomLevels && delayCount == 0) delayCount = 100;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
        if (button == MouseEvent.BUTTON3){
            if (game.mouseCursor){
                game.mouseCursor = false;
                handler.removeObject(handler.getObject(ID.CURSOR));
            } else {
                game.mouseCursor = true;
                handler.addObject(new CURSOR_POINTER(0, 0, ID.CURSOR, game));
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
