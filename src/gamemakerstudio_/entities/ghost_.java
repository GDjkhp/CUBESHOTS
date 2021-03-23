package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.STATE;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ghost_ extends gameobject_ {
    public static boolean isControlled = false;
    int rotateTick = 0;
    public ghost_ (int x, int y, ID id, float velX, float velY) {
        super(x, y, id);
        this.width = 30;
        this.height = 30;
        this.velX = velX;
        this.velY = velY;
    }
    public void tick() {
        if (!isControlled || game_.gameState == STATE.Game || game_.gameState == STATE.GameBeta) {
            x += velX;
            y += velY;
        }

        if (rotateTick == 360) rotateTick = 0;
        rotateTick++;

        // screen limit
        if (x <= 0 || x >= game_.WIDTH - 30) velX *= -1;
        if (y <= 0 || y >= game_.HEIGHT - 30) velY *= -1;
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        if (isControlled) g2d.rotate(Math.toDegrees(rotateTick), x + 15, y + 15);
        g.setColor(Color.RED);
        g.drawRect((int) x, (int) y, width, height);
        g2d.setTransform(old);
    }

    public Rectangle getBounds() {
        return null;
    } // todo: bounds is null



}
