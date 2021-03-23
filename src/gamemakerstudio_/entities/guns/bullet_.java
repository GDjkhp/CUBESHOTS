package gamemakerstudio_.entities.guns;

import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;

public class bullet_ extends gameobject_ {

    handler_ handler;
    public int bulletRange = 50;

    public bullet_(float x, float y, ID id, handler_ handler, int velX, int velY) {
        super(x, y, id);
        this.width = 10;
        this.height = 10;
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        color = Color.blue;
    }

    public void tick() {
        collision();
        if (bulletRange != 0) {
            x += velX;
            y += velY;
            bulletRange--;
        } else handler.removeObject(this);
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, width, height);
    }

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
