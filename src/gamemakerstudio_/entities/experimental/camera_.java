package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class camera_ extends gameobject_ implements KeyListener {
    game_ game;
    public camera_(float x, float y, ID id, game_ game) {
        super(x, y, id);
        this.game = game;
        game.addKeyListener(this);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public int xTemp = 0, yTemp = 0;
    int pixels = 1;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT)
            xTemp += -pixels;
        if (key == KeyEvent.VK_RIGHT)
            xTemp += pixels;
        if (key == KeyEvent.VK_UP)
            yTemp += -pixels;
        if (key == KeyEvent.VK_DOWN)
            yTemp += pixels;
        // no
//        game.getGameCamera().move(x, y);
        game.cameraControls(xTemp, yTemp, 1, 1);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
