package gamemakerstudio_.entities;

import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.assets_;
import gamemakerstudio_.misc.gameobject_;
import gamemakerstudio_.misc.handler_;

import java.awt.*;
import java.util.ArrayList;

public class portalblue_ extends gameobject_ {
    handler_ handler;
    int frames = 0;
    ArrayList<gameobject_> holdEntity = new ArrayList<gameobject_>();
    public portalblue_(float x, float y, ID id,handler_ handler, int velX, int velY) {
        super(x, y, id);
        width = 64;
        height = 64;
        this.velX = velX;
        this.velY = velY;
        this.handler = handler;
    }

    public boolean ignoreList(gameobject_ tempObject){
        switch (tempObject.getId()) {
            case P1Range:
            case P2Range:
            case RocketRange:
            case ElecRangeP1:
            case ElecRangeP2:
            case Trail:
            case PortalBlue:
            case PortalRed:
                return false;
            default:
                return true;

        }
    }

    @Override
    public void tick() {
        // todo: add dvd and/or delete this if offscreen
        for (int i = 0; i < handler.object.size(); i++){
            gameobject_ tempObject = handler.object.get(i);
            if (ignoreList(tempObject)) {
                if (tempObject.getBounds() != null){
                    if (getBounds().contains(tempObject.getBounds()) && !tempObject.isTeleporting()){
                        tempObject.setTeleporting(true);
                        gameobject_ tp = handler.getObject(ID.PortalRed);
                        tempObject.setX((int)tp.getX() + (32 - tempObject.getWidth()/2));
                        tempObject.setY((int)tp.getY() + (32 - tempObject.getHeight()/2));
                    }
                    else if (getBounds().contains(tempObject.getBounds()) && tempObject.isTeleporting()){
                        holdEntity.add(tempObject);
                    }
                }
            }
        }
        for (int i = 0; i < holdEntity.size(); i++){
            if (holdEntity.get(i) != null){
                if (!getBounds().intersects(holdEntity.get(i).getBounds())){
//                System.out.println("blue: the playa escaped (" + holdEntity.get(i).getId() + ")");
                    holdEntity.get(i).setTeleporting(false);
                    holdEntity.remove(i);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        // box bounds
        /*g.setColor(Color.RED);
        g.drawRect((int)x, (int)y, width, height);*/
        // frames
        frames++;
        if (frames == 90)
            frames = 0;
        if (frames < 15)
            g.drawImage(assets_.maxBlue1, (int)x - 32, (int)y - 32, null);
        if (frames >= 15 && frames < 30)
            g.drawImage(assets_.maxBlue2, (int)x - 32, (int)y - 32, null);
        if (frames >= 30 && frames < 45)
            g.drawImage(assets_.maxBlue3, (int)x - 32, (int)y - 32, null);
        if (frames >= 45 && frames < 60)
            g.drawImage(assets_.maxBlue4, (int)x - 32, (int)y - 32, null);
        if (frames >= 60 && frames < 75)
            g.drawImage(assets_.maxBlue3, (int)x - 32, (int)y - 32, null);
        if (frames >= 75 && frames < 90)
            g.drawImage(assets_.maxBlue2, (int)x - 32, (int)y - 32, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}
