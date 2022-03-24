package gamemakerstudio_.entities.guns;

import gamemakerstudio_.entities.particle.trail_;
import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;

public class ricochet_ extends gameobject_ {
    handler_ handler;
    public int bulletRange = 300;
    public ricochet_(float x, float y, ID id, handler_ handler, int velX, int velY) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        this.width = 10;
        this.height = 10;
        color = Color.ORANGE;
    }

    @Override
    public void tick() {
        // screen limit
        if (x <= 0 || x >= game_.WIDTH) velX *= -1;
        if (y <= 0 || y >= game_.HEIGHT) velY *= -1;
        collision();
        // life
        if (bulletRange != 0) {
            x += velX;
            y += velY;
            bulletRange--;
        } else handler.removeObject(this);
        if (!game_.ldm) handler.addObject(new trail_((int) x, (int) y, ID.Trail, color, width, height, 0.1f,
                handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            gameobject_ tempObject = handler.object.get(i);
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
                    if(getBounds().intersects(tempObject.getBounds())) {
                        handler.removeObject(tempObject);
                        handler.removeObject(this);
                    }
                    break;
            }
        }
    }
}
