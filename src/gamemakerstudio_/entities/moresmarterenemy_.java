package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.gui.hud2_;
import gamemakerstudio_.gui.hud_;
import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.gameobject_;
import gamemakerstudio_.misc.handler_;

import java.awt.*;

public class moresmarterenemy_ extends gameobject_ {
    handler_ handler;
    gameobject_ player;
    float pathX, pathY;
    boolean findPlayer = true;
    public moresmarterenemy_(float x, float y, ID id, handler_ handler, int velX, int velY) {
        super(x, y, id);
        width = 30;
        height = 30;
        this.velX = velX;
        this.velY = velY;
        this.handler = handler;
        color = Color.white;
        initPlayer();
        pathfindPlayer();
    }

    @Override
    public void tick() {
        // screen limit
        if (x <= 0 || x >= game_.WIDTH) pathfindPlayer();
        if (y <= 0 || y >= game_.HEIGHT) pathfindPlayer();
        x += pathX;
        y += pathY;
        if (!game_.ldm) handler.addObject(new trail_((int) x, (int) y, ID.Trail, color, width, height, 0.1f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public void pathfindPlayer(){
        // path find player
        float diffX = x - player.getX();
        float diffY = y - player.getY();
        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
        // smartenemy_ default is 3
        pathX = (float) ((-velX / distance) * diffX);
        pathY = (float) ((-velY / distance) * diffY);
    }

    public void initPlayer(){
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
    }
}
