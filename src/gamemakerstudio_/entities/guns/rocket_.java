package gamemakerstudio_.entities.guns;

import gamemakerstudio_.entities.RangeArea;
import gamemakerstudio_.game_;
import gamemakerstudio_.misc.*;

import java.awt.*;

public class rocket_ extends gameobject_ {
    handler_ handler;
//    public int bulletRange = 300;
    gameobject_ blastRange;
    boolean renderAnimation = false;
    int animationTick;
    Rectangle blastBounds;
    public rocket_(float x, float y, ID id, handler_ handler, int velX, int velY) {
        super(x, y, id);
        this.width = 30;
        this.height = 30;
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        animationTick = 50;
        color = Color.RED;
    }

    @Override
    public void tick() {
        // collide logic
        if (detectCollision(this)){
            handler.addObject(new RangeArea(x - 210, y - 210, ID.RocketRange, handler));
            blastRange = handler.getObject(ID.RocketRange);
            collision(blastRange);
            blastBounds = blastRange.getBounds();
            handler.removeObject(blastRange);
            renderAnimation = true;
            if (game_.sfx) audioplayer_.getSound("boom").play();
        }
        // weird codes
        if (renderAnimation){
            if (animationTick != 0){
                animationTick--;
            } else {
                handler.removeObject(this);
            }
        }
        // life
        else if (new Rectangle(0, 0, game_.WIDTH, game_.HEIGHT).contains(getBounds())) {
            x += velX;
            y += velY;
        } else {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        if (!renderAnimation)
            g.fillOval((int) x, (int) y, width, height);
        else {
            if (blastBounds != null)
                g.drawRect(blastBounds.x, blastBounds.y, blastBounds.width, blastBounds.height);
            g.drawImage(assets_.maxExplode, (int)x - 256, (int)y - 256, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public void collision(gameobject_ current) {
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
                    if(current.getBounds().intersects(tempObject.getBounds())) {
                        handler.removeObject(tempObject);
                    }
            }
        }
    }

    public boolean detectCollision(gameobject_ current) {
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
                    if(current.getBounds().intersects(tempObject.getBounds())) {
                        handler.removeObject(tempObject);
                        return true;
                    }

            }
        }
        return false;
    }
}
