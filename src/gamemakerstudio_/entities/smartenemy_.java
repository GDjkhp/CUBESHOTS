/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.gui.hud2_;
import gamemakerstudio_.gui.hud_;
import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.gameobject_;
import gamemakerstudio_.misc.handler_;

import java.awt.*;

/**
 *
 * @author ACER
 */
public class smartenemy_ extends gameobject_ {
    
    private handler_ handler;
    private gameobject_ player;
    
    public smartenemy_ (int x, int y, ID id, handler_ handler, float velX, float velY) {
        super(x, y, id);
        
        this.handler = handler;
        this.width = 30;
        this.height = 30;
        this.velX = velX;
        this.velY = velY;
        color = Color.YELLOW;
        // init player, this was dumb
        for (int i = 0; i < handler.object.size(); i++) {
            if (game_.multiplayer) {
                if (hud_.HEALTH < hud2_.HEALTH) {
                    if (hud_.HEALTH != 0) {
                        if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
                    } else if (handler.object.get(i).getId() == ID.Player2) player = handler.object.get(i);
                }
                else if (hud2_.HEALTH < hud_.HEALTH) {
                    if (hud2_.HEALTH != 0) {
                        if (handler.object.get(i).getId() == ID.Player2) player = handler.object.get(i);
                    } else if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
                }
                else if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
                else if (handler.object.get(i).getId() == ID.Player2) player = handler.object.get(i);
            } else if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }
//        velX = 5;
//        velY = 5;
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
    
    public void tick() {
        // path find player
        float diffX = x - player.getX();
        float diffY = y - player.getY();
        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
        // default is 3
        float pathX = (float) ((-velX / distance) * diffX);
        float pathY = (float) ((-velY / distance) * diffY);
        // screen limit
//        if (x <= 0 || x >= game_.WIDTH) velX *= -1;
//        if (y <= 0 || y >= game_.HEIGHT) velY *= -1;

        x += pathX;
        y += pathY;
        // trail
        if (!game_.ldm) handler.addObject(new trail_((int) x, (int) y, ID.Trail, color, width, height, 0.1f, handler));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
    }


}
