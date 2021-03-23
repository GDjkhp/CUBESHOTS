/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;
import java.util.Random;

/**
 *
 * @author ACER
 */
public class tnt_ extends gameobject_ {
    
    private handler_ handler;
    Random r = new Random();
    
    public tnt_ (int x, int y, ID id, handler_ handler, float velX, float velY) {
        super(x, y, id);
        
        this.handler = handler;
        this.width = 10;
        this.height = 10;
        this.velX = velX;
        this.velY = velY;
        color = Color.red;

        // rewrite this!
        /*velX = (r.nextInt(5 - -5) + -5);
        velY = 5;*/
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
    
    public void tick() {
        x += velX;
        y += velY;

        // screen limit
//        if (x <= 0 || x >= game_.WIDTH) velX *= -1;
//        if (y <= 0 || y >= game_.HEIGHT) velY *= -1;

        if (x <= 0 || x >= game_.WIDTH) handler.removeObject(this);
        if (y <= 0 || y >= game_.HEIGHT) handler.removeObject(this);
        // trail
        if (!game_.ldm) handler.addObject(new trail_((int) x, (int) y, ID.Trail, color, width, height, 0.1f, handler));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
    }


}
