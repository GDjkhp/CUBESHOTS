package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.graphicsstuff.assets_;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CURSOR_POINTER extends gameobject_ implements MouseMotionListener {
    game_ game;
    int dispX, dispY;
    Rectangle boundLimit = new Rectangle(50, 40, 570, 660);
    public CURSOR_POINTER(float x, float y, ID id, game_ game) {
        super(x, y, id);
        this.game = game;
        width = 32;
        height = 32;
        game.addMouseMotionListener(this);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(assets_.targetimage, (int)x, (int)y, null);
        g.setColor(Color.CYAN);

        // trash codes
        if(dispX >= 620){
            if (dispY >= 680) {
                // bottom right, dispX = 620, dispY = 680
                g.drawString("x = " + dispX, (int)x - 35, (int)y - 15);
                g.drawString("y = " + dispY, (int)x - 35, (int)y - 0);
            } else {
                // upper right, dispX = 620, dispY = 40
                g.drawString("x = " + dispX, (int)x - 35, (int)y + 35);
                g.drawString("y = " + dispY, (int)x - 35, (int)y + 50);
            }
        }
        else if (dispY >= 680){
            // lower left, dispX = 50, dispY = 680
            g.drawString("x = " + dispX, (int)x + 35, (int)y - 15);
            g.drawString("y = " + dispY, (int)x + 35, (int)y - 0);
        } else {
            // neutral
            g.drawString("x = " + dispX, (int)x + 35, (int)y + 35);
            g.drawString("y = " + dispY, (int)x + 35, (int)y + 50);
        }
//        g.drawRect(50, 40, 570, 660);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    } // todo: bounds is null

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        x = mx - 15;
        y = my - 15;
        dispX = mx;
        dispY = my;
    }
}
