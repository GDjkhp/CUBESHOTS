package gamemakerstudio_.entities.guns;

import gamemakerstudio_.entities.particle.trail_;
import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.graphicsstuff.assets_;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;
import java.util.Random;

public class chlorophyte_ extends gameobject_ {

    handler_ handler;
    Random r = new Random();

    gameobject_ target, playerRange;
    public int bulletRange = 15;
    float diffX, diffY, pathX, pathY, distance, speed;


    public chlorophyte_(float x, float y, ID id, handler_ handler, float velX, float velY) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        this.width = 10;
        this.height = 10;
        color = Color.GREEN;
        // Init Player Range
        if (id == ID.ChlorophyteP1) {
            for (int i = 0; i < handler.object.size(); i++) {
                gameobject_ tempObject = handler.object.get(i);
                switch (tempObject.getId()) {
                    case P1Range:
                        playerRange = tempObject;
                        break;
                }
            }
        }
        if (id == ID.ChlorophyteP2) {
            for (int i = 0; i < handler.object.size(); i++) {
                gameobject_ tempObject = handler.object.get(i);
                switch (tempObject.getId()) {
                    case P2Range:
                        playerRange = tempObject;
                        break;
                }
            }
        }

        // Init Target
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
    }

    @Override
    public void tick() {
        if (bulletRange != 0) {
            if (target != null) {
                // remove this when finished
                if (target.getBounds().intersects(getBounds())) {
                    handler.removeObject(target);
                    handler.removeObject(this);
                }

                // perform AI
                diffX = x - target.getX();
                diffY = y - target.getY();
                distance = (float) Math.sqrt((x - target.getX()) * (x - target.getX()) + (y - target.getY()) * (y - target.getY()));

                pathX = ((-/*(speed)*/15 / distance) * diffX);
                pathY = ((-/*(speed)*/15 / distance) * diffY);

                x += pathX;
                y += pathY;
            } else {
                x += velX;
                y += velY;
                collision();
            }
            bulletRange--;
        } else handler.removeObject(this);
        if (!game_.ldm) handler.addObject(new trail_((int) x, (int) y, ID.Trail, color, width, height, 0.1f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
        if (target != null) g.drawImage(assets_.locktargetimage, (int)target.getX(),
                (int)target.getY(), null);
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
                    if (getBounds().intersects(tempObject.getBounds())) {
                        handler.removeObject(tempObject);
                        handler.removeObject(this);
                    }
            }
        }
    }

}
