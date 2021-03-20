/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.entities;

import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.gameobject_;
import gamemakerstudio_.misc.handler_;

import java.awt.*;

/**
 *
 * @author ACER
 */
public class trail_ extends gameobject_ {
    
    private float alpha = 1;
    private handler_ handler;
//    private Color color;
    private float life;
    
    public trail_(int x, int y, ID id, Color color, int width, int height, float life, handler_ handler) {
        super (x, y, id);
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
        this.handler = handler;
    }
    
    public void tick() {
        if(alpha > life) {
            alpha -= (life - 0.0001f);
        } else handler.removeObject(this);
    }


    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
        g2d.setComposite(makeTransparent(1));
    }
    
    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }


    
}
