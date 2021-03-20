package gamemakerstudio_.gui;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.handler_;

import java.awt.*;

public class leveleditor_ {
    game_ game;
    handler_ handler;
    public leveleditor_(game_ game, handler_ handler){
        this.game = game;
        this.handler = handler;
    }
    public void tick(){

    }
    public void render(Graphics g){
        /*g.drawLine(100, 100, 600, 600);
        g.drawLine(600, 100, 100, 600);
        g.drawRect(100, 100, 500, 500);*/

        /*g.fillRect(100, 100, 500, 20);
        g.fillRect(100, 100, 20, 500);
        g.fillRect(580, 100, 20, 500);
        g.fillRect(100, 580, 500, 20);

        Font fnt = new Font("mojangles", 0, 20);
        g.setFont(fnt);

        g.drawString("sound", 140, 160);
        g.drawString("ldm", 140, 180);
        g.drawString("multiplayer", 140, 200);
        g.drawString("god", 140, 220);
        g.drawString("auto-restart", 140, 240);
        g.drawString("ai", 140, 260);
        g.drawString("esp", 140, 280);
        g.drawString("hide hud", 140, 300);
        g.drawString("mouse pos", 140, 320);
        g.drawString("grid lines", 140, 340);*/
    }
}
