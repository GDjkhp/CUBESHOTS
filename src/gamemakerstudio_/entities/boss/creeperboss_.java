/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.entities.boss;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.gameobject_;
import gamemakerstudio_.misc.handler_;

import java.awt.*;
import java.util.Random;

/**
 *
 * @author ACER
 */
public class creeperboss_ extends gameobject_ {
    
    private handler_ handler;
    private int timer = 50;
    private int timer2 = 200;
    Random r = new Random();
    
    public creeperboss_ (int x, int y, ID id, handler_ handler, float velX, float velY, int spawnTimer) {
        super(x, y, id);
        
        this.handler = handler;
        this.width = 400;
        this.height = 400;
        this.velX = velX;
        this.velY = velY;
        this.spawnTimer = spawnTimer;
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
    
    public void tick() {
        x += velX;
        y += velY;

        // old codes

        /*if (timer == 0) velY = 0;
        else timer--;
        
        if (timer2 == 0) {
            if (velX == 0) velX = 20;
            int spawn = r.nextInt(5);
            if (spawn == 0) handler.addObject(new tnt_((int) x + 200, (int) y + 200, ID.TNT, handler, (r.nextInt(5 - -5) + -5), 5, 0));
        } else timer2--;*/
        
        // screen limit
        if (x <= 0 || x >= game_.WIDTH - 400) velX *= -1;
//        if (y <= 0 || y >= game_.HEIGHT - 50) velY *= -1;
        // trail
//        handler.addObject(new trail_((int) x, (int) y, ID.Trail, Color.green, width, height, 0.1f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, width, height);
        g.setColor(Color.black);
        g.fillRect((int) x + 50, (int) y + 50, 100, 100);
        g.fillRect((int) x + 250, (int) y + 50, 100, 100);
        g.fillRect((int) x + 150, (int) y + 150, 100, 100);
        g.fillRect((int) x + 150, (int) y + 200, 100, 100);
        g.fillRect((int) x + 100, (int) y + 200, 50, 150);
        g.fillRect((int) x + 250, (int) y + 200, 50, 150);
        
    }


}
