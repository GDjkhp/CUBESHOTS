package gamemakerstudio_.entities.guns;

import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;
import gamemakerstudio_.misc.graphicsstuff.assets_;

import java.awt.*;
import java.util.Random;

public class arkhalis_ extends gameobject_ {
    handler_ handler;
    int bulletRange = 50;
    public arkhalis_(float x, float y, ID id, handler_ handler) {
        super(x, y, id);
        this.handler = handler;
        width = 150;
        height = 150;
    }

    @Override
    public void tick() {
        if (bulletRange != 0){
            bulletRange--;
        } else handler.removeObject(this);

        collision(getBounds());
    }

    int frames = 0, frame = 0;
    Random r = new Random();
    int delay = 3;
    @Override
    public void render(Graphics g) {
        // bound
        g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        // frame codes
        frames++;
        if (frames == delay * 8)
            frames = 0;

        frame = frames / delay;

        switch (frame){
            case 0:
                g.drawImage(assets_.minSlash0, (int)x + 15 - (assets_.minSlash0.getWidth(null)/2 + (r.nextInt(30) - 15)),
                        (int)y + 15 - (assets_.minSlash0.getHeight(null)/2 + (r.nextInt(30) - 15)), null);
                break;
            case 1:
                g.drawImage(assets_.minSlash1, (int)x + 15 - (assets_.minSlash1.getWidth(null)/2 + (r.nextInt(30) - 15)),
                        (int)y + 15 - (assets_.minSlash0.getHeight(null)/2 + (r.nextInt(30) - 15)), null);
                break;
            case 2:
                g.drawImage(assets_.minSlash2, (int)x + 15 - (assets_.minSlash2.getWidth(null)/2 + (r.nextInt(30) - 15)),
                        (int)y + 15 - (assets_.minSlash0.getHeight(null)/2 + (r.nextInt(30) - 15)), null);
                break;
            case 3:
                g.drawImage(assets_.minSlash3, (int)x + 15 - (assets_.minSlash3.getWidth(null)/2 + (r.nextInt(30) - 15)),
                        (int)y + 15 - (assets_.minSlash0.getHeight(null)/2 + (r.nextInt(30) - 15)), null);
                break;
            case 4:
                g.drawImage(assets_.minSlash4, (int)x + 15 - (assets_.minSlash4.getWidth(null)/2 + (r.nextInt(30) - 15)),
                        (int)y + 15 - (assets_.minSlash0.getHeight(null)/2 + (r.nextInt(30) - 15)), null);
                break;
            case 5:
                g.drawImage(assets_.minSlash5, (int)x + 15 - (assets_.minSlash5.getWidth(null)/2 + (r.nextInt(30) - 15)),
                        (int)y + 15 - (assets_.minSlash0.getHeight(null)/2 + (r.nextInt(30) - 15)), null);
                break;
            case 6:
                g.drawImage(assets_.minSlash6, (int)x + 15 - (assets_.minSlash6.getWidth(null)/2 + (r.nextInt(30) - 15)),
                        (int)y + 15 - (assets_.minSlash0.getHeight(null)/2 + (r.nextInt(30) - 15)), null);
                break;
            case 7:
                g.drawImage(assets_.minSlash7, (int)x + 15 - (assets_.minSlash7.getWidth(null)/2 + (r.nextInt(30) - 15)),
                        (int)y + 15 - (assets_.minSlash0.getHeight(null)/2 + (r.nextInt(30) - 15)), null);
                break;
            default:
                g.drawImage(assets_.minSlash0, (int)x + 15 - (assets_.minSlash0.getWidth(null)/2 + (r.nextInt(30) - 15)),
                        (int)y + 15 - (assets_.minSlash0.getHeight(null)/2 + (r.nextInt(30) - 15)), null);
                break;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x - 60, (int)y - 60, width, height);
    }

    public void collision(Rectangle rect) {
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
                    if(rect.getBounds().intersects(tempObject.getBounds())) {
                        handler.removeObject(tempObject);
                    }
                    break;
                case Arkhalis:
                if(rect.getBounds().intersects(tempObject.getBounds()) && tempObject != this) {
                    handler.removeObject(tempObject);
                }
                break;
            }
        }
    }
}
