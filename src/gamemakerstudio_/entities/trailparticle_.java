package gamemakerstudio_.entities;

import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;
import java.util.Random;

public class trailparticle_ extends gameobject_ {
    private float alpha = 1;
    private handler_ handler;
//    private Color color;
    private float life;
    Random r = new Random();
    int limitWidth, limitHeight;
    public trailparticle_(float x, float y, ID id, handler_ handler,
                          Color color, float life, int limitWidth, int limitHeight) {
        super(x, y, id);
        this.handler = handler;
        width = limitWidth/2; height = limitHeight/2;
        this.color = color;
        this.life = life;
        this.limitWidth = limitWidth - width;
        this.limitHeight = limitHeight - height;
    }

    @Override
    public void tick() {
        if(alpha > life) {
            alpha -= (life - 0.0001f);
        } else handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect((int)x + r.nextInt(limitWidth), (int)y + r.nextInt(limitHeight), width , height);
        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    } // todo: bounds is null
}
