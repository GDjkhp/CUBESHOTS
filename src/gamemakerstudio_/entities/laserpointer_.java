package gamemakerstudio_.entities;

import gamemakerstudio_.entities.particle.trail_;
import gamemakerstudio_.game_;
import gamemakerstudio_.gui.hud2_;
import gamemakerstudio_.gui.hud_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;

public class laserpointer_ extends gameobject_ {
    handler_ handler;
    private gameobject_ player;
    float pathX, pathY, diffX, diffY, distance;

    public laserpointer_ (float x, float y, ID id, handler_ handler, float velX, float velY, int spawnTimer) {
        super(x, y, id);
        this.handler = handler;
        this.width = 30;
        this.height = 30;
        this.spawnTimer = spawnTimer;
        this.velX = velX;
        this.velY = velY;
        color = Color.red;
        // AI
        for (int i = 0; i < handler.object.size(); i++) {
            if (game_.multiplayer) {
                if (hud_.HEALTH < hud2_.HEALTH) {
                    if (hud_.HEALTH != 0) {
                        if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
                    } else if (handler.object.get(i).getId() == ID.Player2) player = handler.object.get(i);
                } else if (hud2_.HEALTH < hud_.HEALTH) {
                    if (hud2_.HEALTH != 0) {
                        if (handler.object.get(i).getId() == ID.Player2) player = handler.object.get(i);
                    } else if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
                }
                else if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
                else if (handler.object.get(i).getId() == ID.Player2) player = handler.object.get(i);
            }
            else if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }
        // perform AI
        diffX = x - player.getX();
        diffY = y - player.getY();
        distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
    }

    public void tick() {
        if (spawnTimer == 0) {
            if (x <= 0 || x >= game_.WIDTH) handler.removeObject(this);
            if (y <= 0 || y >= game_.HEIGHT) handler.removeObject(this);
            // execute AI

            // default is 30
            pathX = (float) ((-velX / distance) * diffX);
            pathY = (float) ((-velY / distance) * diffY);

            x += pathX;
            y += pathY;

            if (!game_.ldm) handler.addObject(new trail_((int) x, (int) y, ID.Trail, color, width, height, 0.1f, handler));

        } else {
            // perform AI
            diffX = x - player.getX();
            diffY = y - player.getY();
            distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
            spawnTimer--;
        }
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }


}
