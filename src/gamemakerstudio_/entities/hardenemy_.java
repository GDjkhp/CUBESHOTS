/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.entities;

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
public class hardenemy_ extends gameobject_ {
    
    private handler_ handler;
    
    private Random r = new Random();
    
    public hardenemy_ (int x, int y, ID id, handler_ handler) {
        super(x, y, id);
        
        this.handler = handler;
        this.width = 10;
        this.height = 10;
        this.velX = 5;
        this.velY = 5;
        color = Color.orange;
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
    
    public void tick() {
        x += velX;
        y += velY;
        // screen limit
        if (x <= 0 || x >= game_.WIDTH) {
            if (x <= 0) velX = -(r.nextInt(10) + 1) * -1;
            else velX = (r.nextInt(10) + 1) * -1;
        }
        else if (y <= 0 || y >= game_.HEIGHT) {
            if (y <= 0) velY = -(r.nextInt(10) + 1) * -1;
            else velY = (r.nextInt(10) + 1) * -1;
        }
        // trail
        if (!game_.ldm) handler.addObject(new trail_((int) x, (int) y, ID.Trail, color, width, height, 0.1f, handler));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
    }


}
