package gamemakerstudio_.entities;

import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.gameobject_;
import gamemakerstudio_.misc.handler_;

import java.awt.*;

public class RangeArea extends gameobject_ {

    handler_ handler;
    gameobject_ player;
    boolean showBoundingBox = false;

    public RangeArea(float x, float y, ID id, handler_ handler) {
        super(x, y, id);
        this.handler = handler;
        this.width = 450;
        this.height = 450;
        // locate player one
        if (id == ID.P1Range) {
            for (int i = 0; i < handler.object.size(); i++) {
                gameobject_ tempObject = handler.object.get(i);
                if (tempObject.getId() == ID.Player) player = tempObject;
            }
        }
        // locate player two
        if (id == ID.P2Range) {
            for (int i = 0; i < handler.object.size(); i++) {
                gameobject_ tempObject = handler.object.get(i);
                if (tempObject.getId() == ID.Player2) player = tempObject;
            }
        }
        // locate entity, for electrocute
        if (id == ID.ElecRangeP1) {
            for (int i = 0; i < handler.object.size(); i++) {
                gameobject_ tempObject = handler.object.get(i);
                if (tempObject.getId() == ID.ElectrocuteP1) player = tempObject;
            }
        }
        if (id == ID.ElecRangeP2) {
            for (int i = 0; i < handler.object.size(); i++) {
                gameobject_ tempObject = handler.object.get(i);
                if (tempObject.getId() == ID.ElectrocuteP2) player = tempObject;
            }
        }
        // locate entity, for rocket
        if (id == ID.RocketRange) {
            for (int i = 0; i < handler.object.size(); i++) {
                gameobject_ tempObject = handler.object.get(i);
                if (tempObject.getId() == ID.Rocket) player = tempObject;
            }
        }
    }

    @Override
    public void tick() {
        x = player.getX() - 210;
        y = player.getY() - 210;
        if (player == null)
            handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        if (showBoundingBox) {
            if (id == ID.ElecRangeP1 || id == ID.ElecRangeP2 || id == ID.RocketRange)
                g.setColor(Color.RED);
            if (id == ID.P1Range)
                g.setColor(Color.CYAN);
            if (id == ID.P2Range)
                g.setColor(Color.GREEN);
            g.drawRect((int) x, (int) y, width, height);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }


}
