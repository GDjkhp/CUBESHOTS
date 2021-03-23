package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;

public class basecircle_ extends gameobject_ {

    // do i still need to use this in the future?
    public static boolean dvd = true;

    private handler_ handler;
    public static int patternVel = 5;
    // north
    private static int northVelY = -patternVel;
    // south
    private static int southVelY = patternVel;
    // east
    private static int eastVelX = patternVel;
    // west
    private static int westVelX = -patternVel;

    // default
    int defaultTimer;

    public basecircle_(float x, float y, ID id, handler_ handler, float velX, float velY, int spawnTimer) {
        super(x, y, id);
        this.width = 30;
        this.height = 30;
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        defaultTimer = spawnTimer;
        this.spawnTimer = spawnTimer;
        color = Color.magenta;
    }

    public void tick() {
        // screen limit
        if (dvd) {
            if (x <= 0 || x >= game_.WIDTH) velX *= -1;
            if (y <= 0 || y >= game_.HEIGHT) velY *= -1;
        }
        if (spawnTimer == 0) {
            if (this.id == ID.BaseCircle) {
                handler.addObject(new circlewithpatterns_((int) x + 10, (int) y + 10, ID.CircleWithPatterns, handler, 0, northVelY));
                handler.addObject(new circlewithpatterns_((int) x + 10, (int) y + 10, ID.CircleWithPatterns, handler, 0, southVelY));
                handler.addObject(new circlewithpatterns_((int) x + 10, (int) y + 10, ID.CircleWithPatterns, handler, eastVelX, 0));
                handler.addObject(new circlewithpatterns_((int) x + 10, (int) y + 10, ID.CircleWithPatterns, handler, westVelX, 0));
                handler.addObject(new circlewithpatterns_((int) x + 10, (int) y + 10, ID.CircleWithPatterns, handler, eastVelX, northVelY));
                handler.addObject(new circlewithpatterns_((int) x + 10, (int) y + 10, ID.CircleWithPatterns, handler, westVelX, northVelY));
                handler.addObject(new circlewithpatterns_((int) x + 10, (int) y + 10, ID.CircleWithPatterns, handler, eastVelX, southVelY));
                handler.addObject(new circlewithpatterns_((int) x + 10, (int) y + 10, ID.CircleWithPatterns, handler, westVelX, southVelY));
                if (velX == 0 && velY == 0)
                handler.removeObject(this);
                else spawnTimer = defaultTimer;
            }
        } else {
            x += velX;
            y += velY;
            if (this.id == ID.BaseCircle) {
                // TODO: delete this if offscreen
                spawnTimer--;
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(color);
        if (this.id == ID.BaseCircle)
            g.fillOval((int) x, (int) y, width, height);
        if (this.id == ID.BASECIRCLEGHOST)
            g.drawOval((int) x, (int) y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }


}
