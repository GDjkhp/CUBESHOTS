package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.gameobject_;
import gamemakerstudio_.misc.handler_;

import java.awt.*;
import java.util.Random;

public class starwrathenemy_ extends gameobject_ {

    handler_ handler;

    Random r = new Random();

    public starwrathenemy_(float x, float y, ID id, handler_ handler, float velX, float velY, int spawnTimer) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        this.spawnTimer = spawnTimer;
    }

    public void tick() {
        handler.addObject(new starwrath_(r.nextInt(game_.WIDTH), 0, ID.Star, handler, velX, velY));
    }

    public void render(Graphics g) {

    }

    public Rectangle getBounds() {
        return null;
    } // todo: bounds is null
}
