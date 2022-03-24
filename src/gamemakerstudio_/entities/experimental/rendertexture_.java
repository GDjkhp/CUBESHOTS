package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.game_;
import gamemakerstudio_.gui.window_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.graphicsstuff.ScreenImage;
import gamemakerstudio_.misc.graphicsstuff.assets_;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;
import java.awt.image.BufferedImage;

public class rendertexture_ extends gameobject_ {
    public static BufferedImage render;
    public rendertexture_(float x, float y, ID id, int width, int height, int velX, int velY) {
        super(x, y, id);
        this.velX = velX;
        this.velY = velY;
        this.width = width;
        this.height = height;
    }

    // software
    public static BufferedImage software(){
        return game_.image;
    }
    // hardware
    public static BufferedImage hardware(){
        try {
            return ScreenImage.createImage(window_.frame);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void tick() {
        // render = software();
    }

    @Override
    public void render(Graphics g) {
        try {
            // render = hardware();
            render = software();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image image = assets_.scaleImage(render, width, height);
        g.drawImage(image, (int)x, (int)y, null);
        g.drawRect((int)x, (int)y, image.getWidth(null), image.getHeight(null));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}