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
public class spicymenu_ extends gameobject_ {

    private handler_ handler;
    Random r = new Random();

//    private Color col;
    
    public spicymenu_ (int x, int y, ID id, handler_ handler) {
        super(x, y, id);
        
        this.handler = handler;
        this.width = 20;
        this.height = 20;
        
        velX = (r.nextInt(5 - -5) + -5);
        velY = (r.nextInt(5 - -5) + -5);

        if (velX == 0 && velY == 0) {
            velX = 5;
            velY = 5;
        }
        
        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));

    }
    
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
    
    public void tick() {
        x += velX;
        y += velY;
        // screen limit
        if (x <= 0 || x >= game_.WIDTH) velX *= -1;
        if (y <= 0 || y >= game_.HEIGHT) velY *= -1;
        // trail
        if (!game_.ldm) handler.addObject(new trail_((int) x, (int) y, ID.Trail, color, width, height, 0.1f, handler));
    }

    public void render(Graphics g) {
//        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
    }


}
