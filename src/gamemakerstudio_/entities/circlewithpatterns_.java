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

/**
 *
 * @author ACER
 */
public class circlewithpatterns_ extends gameobject_ {

    private handler_ handler;
    // do i still need to use this in the future?
    public static boolean dvd = false;

    public circlewithpatterns_ (int x, int y, ID id, handler_ handler, float velX, float velY) {
        super(x, y, id);
        this.width = 10;
        this.height = 10;
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        color = Color.magenta;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public void tick() {
        x += velX;
        y += velY;
        // screen limit
        if (dvd) {
            if (x <= 0 || x >= game_.WIDTH) velX *= -1;
            if (y <= 0 || y >= game_.HEIGHT) velY *= -1;
        }
        else {
            if (x <= 0 || x >= game_.WIDTH) handler.removeObject(this);
            if (y <= 0 || y >= game_.HEIGHT) handler.removeObject(this);
        }
        // trail
        if (!game_.ldm) handler.addObject(new trail_((int) x, (int) y, ID.Trail, color, width, height, 0.1f, handler));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, width, height);
        /*g.setColor(Color.RED);
        g.drawRect((int)x, (int)y, width, height);*/
    }


}
