/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.graphicsstuff.assets_;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;

/**
 *
 * @author ACER
 */
public class heart_ extends gameobject_ {
    
    private handler_ handler;
    public static boolean magnet;

    // optimize var
    public int countHearts = 0;
    /*change this number to limit heart entities*/
    public int heartsLimit = 50;
    
    public heart_ (int x, int y, ID id, handler_ handler, float velX, float velY) {
        super((x / 16) * 16, (y / 16) * 16, id);
        
        this.handler = handler;
        this.width = 16;
        this.height = 16;
        this.velX = velX;
        this.velY = velY;

    }
    
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
    
    public void tick() {
        // screen limit
        if (x <= 0 || x >= game_.WIDTH) velX *= -1;
        if (y <= 0 || y >= game_.HEIGHT) velY *= -1;
        // todo: delete this if offscreen

//        collision();

//        if (game_.isInvincible) handler.removeObject(this);

//        optimizeHeartsPartII();

        // weird bug
        try {
            optimizeHearts();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
//        if (!game_.isInvincible)
        g.drawImage(assets_.heartimage, (int) x, (int) y, null);
    }

    // this supposed to kill hearts that spawned with the same pos
    public void optimizeHeartsPartII(){
        for (int i = handler.object.size() - 1; i >= 0; i--) {
            gameobject_ tempObject = handler.object.get(i);
            switch (tempObject.getId()) {
                case HeartFriend:
                    if (getBounds().intersects(tempObject.getBounds())) {
                        handler.removeObject(this);
                    }
            }
        }
    }

    public void optimizeHearts(){
        // optimization for hearts, aka limit
        // edit: damn, not what i was expecting, but it's gud
        countHearts = 0;
        for (int i = 0; i < handler.object.size(); i++) {
            gameobject_ tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.HeartFriend)
                countHearts++;
        }
        if (countHearts > heartsLimit) handler.removeObject(this);
    }

    // destroy hearts that overlaps bosses
    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            gameobject_ tempObject = handler.object.get(i);
            switch (tempObject.getId()) {
                case CreeperBoss:
                case Xgamer:
                    if(getBounds().intersects(tempObject.getBounds())) {
                        handler.removeObject(this);
                    }
            }
            /*if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.CreeperBoss || tempObject.getId() == ID.Xgamer) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    hud_.HEALTH -= 2;
                }
            }*/
        }
    }
}
