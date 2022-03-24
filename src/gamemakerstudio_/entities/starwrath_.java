package gamemakerstudio_.entities;

import gamemakerstudio_.entities.particle.trail_;
import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;
import java.util.Random;

public class starwrath_ extends gameobject_ {
    private handler_ handler;
//    private Color col;
    private Random r = new Random();
    private gameobject_ ghost;
    float pathX, pathY, diffX, diffY, distance;
    public starwrath_(int x, int y, ID id, handler_ handler, float velX, float velY){
        super(x, y, id);
        this.handler = handler;
        this.width = 30;
        this.height = 30;
        this.velX = velX;
        this.velY = velY;
        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        // AI
        for (int i = 0; i < handler.object.size(); i++) {
            gameobject_ tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.GHOST) {
                ghost = tempObject;
            }
        }
        // perform AI
        diffX = x - ghost.getX();
        diffY = y - ghost.getY();
        distance = (float) Math.sqrt((x - ghost.getX()) * (x - ghost.getX()) + (y - ghost.getY()) * (y - ghost.getY()));
    }
    public void tick() {
        // default is 30

        // execute AI
        pathX = (float) ((-velX / distance) * diffX);
        pathY = (float) ((-velY / distance) * diffY);

        x += pathX;
        y += pathY;

        if (x <= 0 || x >= game_.WIDTH) {
            handler.removeObject(this);
        }
        if (y <= 0 || y >= game_.HEIGHT) {
            handler.removeObject(this);
        }

        if (!game_.ldm) handler.addObject(new trail_((int) x, (int) y, ID.Trail, color, width, height, 0.1f, handler));

    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public void health() {

    }
}
