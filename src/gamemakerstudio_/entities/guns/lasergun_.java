package gamemakerstudio_.entities.guns;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.Random;

public class lasergun_ extends gameobject_ {
    handler_ handler;
    gameobject_ playerRange, target;
    Random r = new Random();
    int bulletRange = 50;
    // alpha
    private float life = 0.01f;
    private float alpha = 1;
    boolean opaque = false;
    public lasergun_(float x, float y, ID id, handler_ handler) {
        super(x, y, id);
        width = 10;
        height = 10;
        color = Color.red;
        this.handler = handler;
        // init player range
        if (id == ID.LasergunP1)
            playerRange = handler.getObject(ID.P1Range);
        if (id == ID.LasergunP2)
            playerRange = handler.getObject(ID.P2Range);
        // init target
        for (int i = 0; i < handler.object.size() && target == null; i++) {
            int randTarget = r.nextInt(handler.object.size());
            gameobject_ tempObject = handler.object.get(randTarget);
            switch (tempObject.getId()) {
                case BasicEnemy:
                case FastEnemy:
                case SmartEnemy:
                case HardEnemy:
                case BaseCircle:
                case Laser:
                case Star:
                case TNT:
                case CircleWithPatterns:
                    if (tempObject.getBounds().intersects(playerRange.getBounds())) {
                        target = tempObject;
                    }
                    break;
            }
        }
        if (game_.sfx) audioplayer_.getSound("laser").play();

        // destroy automagically
        if (target != null){
            handler.removeObject(target);
        }
    }

    @Override
    public void tick() {
        // TODO: tick codes
        if (!opaque){
            bulletRange--;
            if (bulletRange == 0)
                handler.removeObject(this);
        } else {
            // opaque
            if(alpha > life) {
                alpha -= (life - 0.0001f);
            } else handler.removeObject(this);
        }
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }

    @Override
    public void render(Graphics g) {
        // use g2d
        Graphics2D g2d = (Graphics2D) g;
        // transform
        AffineTransform old = g2d.getTransform();
        // graphics codes
        if (target != null) {

            // pre alpha codes
            g2d.setComposite(makeTransparent(alpha));

            // rotate
            Line2D line = new Line2D.Double((int)x, (int)y, (int)target.getX(), (int)target.getY());

            double distance = Math.sqrt((line.getX1() - line.getX2()) * (line.getX1() - line.getX2()) +
                    (line.getY1() - line.getY2()) * (line.getY1() - line.getY2()));

            double slopeInRadians = Math.atan2(line.getY1() - line.getY2(), line.getX1() - line.getX2()) + Math.toRadians(90);
            double slopeInDegrees = (slopeInRadians * 180) / Math.PI;

            // pre rotate
            g2d.rotate(slopeInRadians, x, y);

            if (!game_.ldm){
                Image laserStretch = assets_.scaleImage(assets_.laser, 256, (int)distance);
                g.drawImage(laserStretch, (int)x - 116, (int)y, null);
            }

            // post alpha codes
            g2d.setComposite(makeTransparent(1));

            // box
            if (game_.ldm){
                g.setColor(Color.GREEN);
                g.drawRect((int)x, (int)y, 30, (int)distance);
            }

            // post rotate
            g2d.setTransform(old);

            // line
            if (game_.ldm){
                g.setColor(color);
                g.drawLine((int) x, (int) y, (int) target.getX(), (int) target.getY());
            }

            g.drawImage(assets_.targetimage, (int)target.getX(), (int)target.getY(), null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}
