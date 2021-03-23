package gamemakerstudio_.entities;

import gamemakerstudio_.misc.entitystuff.FACE;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;

public class triangleshooter_ extends gameobject_ {
    handler_ handler;
    Polygon triangle = new Polygon();
    int defaultSpawnTimer;
    FACE defaultFace;
    public triangleshooter_(float x, float y, ID id, handler_ handler, float velX, float velY, int spawnTimer, FACE face) {
        super(x, y, id);
        this.handler = handler;
        this.width = 50;
        this.height = 50;
        this.velX = velX;
        this.velY = velY;
        defaultSpawnTimer = spawnTimer;
        this.spawnTimer = spawnTimer;
        color = Color.red;
        // detect face, crappy codes
        defaultFace = face;
        // face 1
        if (face == FACE.SOUTH) {
            triangle.xpoints = new int[]{(int) x, (int) x + 25, (int) x + 50};
            triangle.ypoints = new int[]{(int) y + 50, (int) y, (int) y + 50};
            triangle.npoints = 3;
        }
        // face 2
        if (face == FACE.WEST) {
            triangle.xpoints = new int[]{(int) x, (int) x + 50, (int) x};
            triangle.ypoints = new int[]{(int) y, (int) y + 25, (int) y + 50};
            triangle.npoints = 3;
        }
        // face 3
        if (face == FACE.NORTH) {
            triangle.xpoints = new int[]{(int) x, (int) x + 25, (int) x + 50};
            triangle.ypoints = new int[]{(int) y, (int) y + 50, (int) y};
            triangle.npoints = 3;
        }
        // face 4
        if (face == FACE.EAST) {
            triangle.xpoints = new int[]{(int) x + 50, (int) x, (int) x + 50};
            triangle.ypoints = new int[]{(int) y, (int) y + 25, (int) y + 50};
            triangle.npoints = 3;
        }
        // new kompany logo confirmed?
        /*triangle.xpoints = new int[] {(int) x + 50, (int) x, (int) x + 50};
        triangle.ypoints = new int[] {(int) y + 25, (int) y, (int) y + 50};
        triangle.npoints = 3;*/
    }

    @Override
    public void tick() {
        // todo: add dvd and/or delete this if offscreen
        if (spawnTimer == 0) {
            spawnTimer = defaultSpawnTimer;
            if (defaultFace == FACE.SOUTH) {
                handler.addObject(new circlewithpatterns_((int) x, (int) y + 50, ID.CircleWithPatterns, handler, 0, 5));
                handler.addObject(new circlewithpatterns_((int) x + 20, (int) y + 50, ID.CircleWithPatterns, handler, 0, 5));
                handler.addObject(new circlewithpatterns_((int) x + 40, (int) y + 50, ID.CircleWithPatterns, handler, 0, 5));
            }
            if (defaultFace == FACE.WEST) {
                handler.addObject(new circlewithpatterns_((int) x - 10, (int) y, ID.CircleWithPatterns, handler, -5, 0));
                handler.addObject(new circlewithpatterns_((int) x - 10, (int) y + 20, ID.CircleWithPatterns, handler, -5, 0));
                handler.addObject(new circlewithpatterns_((int) x - 10, (int) y + 40, ID.CircleWithPatterns, handler, -5, 0));
            }
            if (defaultFace == FACE.NORTH) {
                handler.addObject(new circlewithpatterns_((int) x, (int) y - 10, ID.CircleWithPatterns, handler, 0, -5));
                handler.addObject(new circlewithpatterns_((int) x + 20, (int) y - 10, ID.CircleWithPatterns, handler, 0, -5));
                handler.addObject(new circlewithpatterns_((int) x + 40, (int) y - 10, ID.CircleWithPatterns, handler, 0, -5));
            }
            if (defaultFace == FACE.EAST) {
                handler.addObject(new circlewithpatterns_((int) x + 50, (int) y, ID.CircleWithPatterns, handler, 5, 0));
                handler.addObject(new circlewithpatterns_((int) x + 50, (int) y + 20, ID.CircleWithPatterns, handler, 5, 0));
                handler.addObject(new circlewithpatterns_((int) x + 50, (int) y + 40, ID.CircleWithPatterns, handler, 5, 0));
            }
        } else spawnTimer--;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillPolygon(triangle);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
