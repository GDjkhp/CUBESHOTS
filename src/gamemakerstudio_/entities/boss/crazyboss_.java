package gamemakerstudio_.entities.boss;

import gamemakerstudio_.entities.tnt_;
import gamemakerstudio_.misc.*;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;
import gamemakerstudio_.misc.graphicsstuff.assets_;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class crazyboss_ extends gameobject_ {

    handler_ handler;
    Random r = new Random();

    public static boolean isRotating = true;
    public static boolean rotateByTick = false;
    public static boolean customRotateByTick = true;
    public static boolean increment;
    public static int rotateTick = 0;
    public static int rotateThisTick;
    public static int minRotate, maxRotate;

    public crazyboss_(float x, float y, ID id, handler_ handler, float velXCustom, float velYCustom, int spawnTimer) {
        super(x, y, id);
        this.handler = handler;
        this.width = 256;
        this.height = 256;
        this.velX = velXCustom;
        this.velY = velYCustom;
        this.spawnTimer = spawnTimer;
    }

    public void tick() {

        x += velX;
        y += velY;

        if (rotateByTick) rotateTick++;
        if (customRotateByTick) {
            if (increment) rotateTick += rotateThisTick;
            if (!increment) rotateTick -= rotateThisTick;
            rotateTick = MathUtil.clamp(rotateTick, minRotate, maxRotate);
        } else rotateTick = 0;
        handler.addObject(new tnt_((int) x + 128, (int) y + 128, ID.TNT, handler, (r.nextInt(5 - -5) + -5), 5));
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        if (isRotating) g2d.rotate(Math.toRadians(rotateTick), x + 128, y + 128);
        g.drawImage(assets_.upscaledcrazyboss, (int) x, (int) y, null);
        g2d.setTransform(old);
    }

    public Rectangle getBounds() {
        return new Rectangle ((int) x, (int) y, width, height);
    }


}
