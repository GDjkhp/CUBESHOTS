/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.gui.hud2_;
import gamemakerstudio_.misc.*;
import gamemakerstudio_.misc.audiostuff.audioplayer_;
import gamemakerstudio_.misc.entitystuff.GunManager;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;
import java.util.Random;

/**
 *
 * @author ACER
 */
public class player2_ extends gameobject_ {
    
    Random r = new Random();
    handler_ handler;
    hud2_ hud2;

    // north
    private static int northVelY = -5;
    // east
    private static int eastVelX = 5;
    // west
    private static int westVelX = -5;

    //shoot
    public static int cooldownp2 = 0;
    public static int defaultcooldown = 15;
    //dash
    public static int dashcooldown = 15;
    private int delay = 5;

    public static boolean isShooting = false;
    public static boolean isDashing = false;

    // dumb ai
    boolean searchHearts = true;
    gameobject_ heart;

    public player2_(int x, int y, ID id, handler_ handler, hud2_ hud2) {
        super(x, y, id);
        this.handler = handler;
        this.width = 30;
        this.height = 30;
        this.hud2 = hud2;
        color = Color.GREEN;
        // gameloop fix
        hud2.lastTime = System.nanoTime();
        hud2.delta = 0;
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
    
    public void tick() {
        // dumb ai
        if (game_.computerP2){
            // if player touched heart bounds
            if (heart != null){
                if (getBounds().intersects(heart.getBounds())){
                    heart = null;
                    searchHearts = true;
                }
            }

            // search fo hearts, old dumb codes
            if (searchHearts){
                if (heart == null) {
                    for (int i = 0; i < handler.object.size() && heart == null; i++){
                        gameobject_ tempObject = handler.object.get(i);
                        if (tempObject.getId() == ID.HeartFriend)
                            heart = tempObject;
                    }
                }
                if (heart != null) searchHearts = false;
            }

            // better ai
            gameobject_ tempHeart = null;
            float tempDistance = 420;
            for (int i = 0; i < handler.object.size(); i++){
                gameobject_ tempObject = handler.object.get(i);
                if (tempHeart == null){
                    if (tempObject.getId() == ID.HeartFriend) {
                        tempHeart = tempObject;
                        tempDistance = (float) Math.sqrt((x - tempObject.getX()) * (x - tempObject.getX()) + (y - tempObject.getY()) * (y - tempObject.getY()));
                    }
                } else {
                    if (tempObject.getId() == ID.HeartFriend){
                        float tempDistance2 = (float) Math.sqrt((x - tempObject.getX()) * (x - tempObject.getX()) + (y - tempObject.getY()) * (y - tempObject.getY()));
                        if (tempDistance > tempDistance2){
                            tempHeart = tempObject;
                            tempDistance = tempDistance2;
                        }
                    }
                }
            }
            heart = tempHeart;

            // ai move
            if (heart != null) {
                float diffX = x - heart.getX();
                float diffY = y - heart.getY();
                float distance = (float) Math.sqrt((x - heart.getX()) * (x - heart.getX()) + (y - heart.getY()) * (y - heart.getY()));
                float pathX = (float) ((-handler.spdp2 / distance) * diffX);
                float pathY = (float) ((-handler.spdp2 / distance) * diffY);
                x += pathX;
                y += pathY;
            }

            // collision
            if (!game_.isInvincible &&
                    (game_.gameState == STATE.Game || game_.gameState == STATE.GameBeta)) collision();
        }

        // shooting codes
        if (isShooting) {
            if (cooldownp2 == 0) {
                cooldownp2 = defaultcooldown;
                GunManager.shootCodes(x, y, handler, GunManager.playerTwoGunLoadOut, id);
            } else cooldownp2--;
        }

        // FIXME: dashing code
        if (isDashing && dashcooldown != 0) {
            x += (velX * 3);
            y += (velY * 3);
            dashcooldown--;
        }

        // non-ai computation work
        else if (!game_.computerP2){
            isDashing = false;
            x += velX;
            y += velY;
            if (!game_.isInvincible &&
                    (game_.gameState == STATE.Game || game_.gameState == STATE.GameBeta)) collision();
        }

        // death
        if (hud2_.HEALTH == 0) {
            x = 1000;
            y = 1000;
        }

        // clamp codes
        else {
            // clamp
            x = MathUtil.clamp((int) x, 0, game_.WIDTH - 30);
            y = MathUtil.clamp((int) y, 0, game_.HEIGHT - 30);

            // snake portal
            /*if (x <= 0) x = game_.WIDTH - 30;
            else if (x >= game_.WIDTH - 30) x = 0;
            else if (y <= 0) y = game_.HEIGHT - 30;
            else if (y >= game_.HEIGHT - 30) y = 0;*/
        }
        
        if (!game_.ldm) handler.addObject(new trail_((int) x, (int) y, ID.Trail, color, width, height, 0.1f, handler));
    }
    
    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            gameobject_ tempObject = handler.object.get(i);
            switch (tempObject.getId()) {
                case BasicEnemy:
                case FastEnemy:
                case SmartEnemy:
                case HardEnemy:
                case BaseCircle:
                case Laser:
                case Star:
                case TNT:
                case CircleWithPatterns:
                case MoreSmarter:
                    if(getBounds().intersects(tempObject.getBounds())) {
                        hud2.HEALTH -= 2;
//                        handler.removeObject(tempObject);
                        if (hud2.HEALTH == 0)
                            if (game_.sfx) audioplayer_.getSound("death").play();
                    }
            }
            /*if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.CreeperBoss) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    hud2_.HEALTH -= 2;
                }
            }*/
            if(tempObject.getId() == ID.HeartFriend) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    hud2.HEALTH += 10;
                    hud2.heartsTaken++;
                    heart = handler.removeObject(tempObject);
                    searchHearts = true;
//                    hud2.xp++;
                }
            }
        }
    }
    
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
        g.setColor(Color.CYAN);
        if (game_.espLineP2) {
            for (int i = 0; i < handler.object.size(); i++) {
                gameobject_ gO = handler.object.get(i);
                g.drawLine((int) x + 15, (int) y + 15,
                        (int) gO.getX() + (gO.getWidth() / 2), (int) gO.getY() + (gO.getHeight() / 2));
            }
        }
    }


}
