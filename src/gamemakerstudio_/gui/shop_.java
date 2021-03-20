/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.gui;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.STATE;
import gamemakerstudio_.misc.audioplayer_;
import gamemakerstudio_.misc.handler_;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author ACER
 */
public class shop_ extends MouseAdapter {
    
    handler_ handler;
    hud_ hud;
    hud2_ hud2;
    game_ game;
    
    public static int B1 = 0;
    public static int B2 = 0;
    public static int B3 = 0;
    public static int B4 = 0;
    public static int B5 = 0;
    public static int B6 = 0;
    
    public shop_(handler_ handler, hud_ hud, hud2_ hud2, game_ game) {
        this.handler = handler;
        this.hud = hud;
        this.hud2 = hud2;
        this.game = game;
        game.addMouseListener(this);
    }
    public void render (Graphics g) {
        //use the font
        g.setFont(new Font("andy", 0, 48));
//        g.setFont(new Font("supercell-magic", 0, 48));

        g.setColor(Color.red);
        g.drawString("shop_", game_.WIDTH / 2 - 100, 40);

        g.setFont(new Font("andy", 0, 20));

        // p1 xp
        g.setColor(Color.cyan);
        g.drawString("Player 1 XP: " + hud.getXp(), game_.WIDTH / 2 - 50, 75);

        // box 1
        if (B1 > hud.getXp()) g.setColor(Color.red);
        else g.setColor(Color.cyan);
        g.drawRect(100, 100, 150, 80);
        g.drawString("Upgrade Health", 110, 120);
        g.drawString("Cost: " + B1, 110, 140);
        g.drawString("Total Health: " + (100 + (hud.bounds / 2)), 110, 160);
        // box 2
        if (B2 > hud.getXp()) g.setColor(Color.red);
        else g.setColor(Color.cyan);
        g.drawRect(250, 100, 150, 80);
        g.drawString("Upgrade Speed", 260, 120);
        g.drawString("Cost: " + B2, 260, 140);
        g.drawString("Speed: " + handler.spdp1, 260, 160);
        // box 3
        if (B3 > hud.getXp()) g.setColor(Color.red);
        else g.setColor(Color.cyan);
        g.drawRect(400, 100, 150, 80);
        g.drawString("Refill Health", 410, 120);
        g.drawString("Cost: " + B3, 410, 140);
        g.drawString("Health: " + hud.HEALTH, 410, 160);

        if (game_.multiplayer) {
            // p2 xp
            g.setColor(Color.green);
            g.drawString("Player 2 XP: " + hud2.getXp(), game_.WIDTH / 2 - 50, 315);

            // box 4
            if (B4 > hud2.getXp()) g.setColor(Color.red);
            else g.setColor(Color.green);
            g.drawRect(100, 200, 150, 80);
            g.drawString("Upgrade Health", 110, 220);
            g.drawString("Cost: " + B4, 110, 240);
            g.drawString("Total Health: " + (100 + (hud2.bounds / 2)), 110, 260);
            // box 5
            if (B5 > hud2.getXp()) g.setColor(Color.red);
            else g.setColor(Color.green);
            g.drawRect(250, 200, 150, 80);
            g.drawString("Upgrade Speed", 260, 220);
            g.drawString("Cost: " + B5, 260, 240);
            g.drawString("Speed: " + handler.spdp2, 260, 260);
            // box 6
            if (B6 > hud2.getXp()) g.setColor(Color.red);
            else g.setColor(Color.green);
            g.drawRect(400, 200, 150, 80);
            g.drawString("Refill Health", 410, 220);
            g.drawString("Cost: " + B6, 410, 240);
            g.drawString("Health: " + hud2.HEALTH, 410, 260);
        }
    }
    public void mouseReleased(MouseEvent e) {
        if (game.gameState == STATE.Shop) {
            int mx = e.getX();
            int my = e.getY();
            // box 1
            if (mouseOver(mx, my, 100, 100, 150, 80)) {
                if (hud.getXp() >= B1) {
                    hud.setXp(hud.getXp() - B1);
                    B1 += B1;
                    hud_.bounds += 20;
                    if (game_.sfx) audioplayer_.getSound("click_sound").play();
                }
            }
            // box 2
            if (mouseOver(mx, my, 250, 100, 150, 80)) {
                if (hud.getXp() >= B2) {
                    hud.setXp(hud.getXp() - B2);
                    B2 += B2;
                    handler_.spdp1 += 5;
                    if (game_.sfx) audioplayer_.getSound("click_sound").play();
                }
            }
            // box 3
            if (mouseOver(mx, my, 400, 100, 150, 80)) {
                if (hud.getXp() >= B3) {
                    if (hud.HEALTH != (100 + (hud.bounds / 2))) {
                        hud.setXp(hud.getXp() - B3);
                        hud.HEALTH = (100 + (hud.bounds / 2));
                        if (game_.sfx) audioplayer_.getSound("click_sound").play();
                    }
                }
            }
            if (game_.multiplayer) {
                // box 4
                if (mouseOver(mx, my, 100, 200, 150, 80)) {
                    if (hud2.getXp() >= B4) {
                        hud2.setXp(hud2.getXp() - B4);
                        B4 += B4;
                        hud2_.bounds += 20;
                        if (game_.sfx) audioplayer_.getSound("click_sound").play();
                    }
                }
                // box 5
                if (mouseOver(mx, my, 250, 200, 150, 80)) {
                    if (hud2.getXp() >= B5) {
                        hud2.setXp(hud2.getXp() - B5);
                        B5 += B5;
                        handler_.spdp2 += 5;
                        if (game_.sfx) audioplayer_.getSound("click_sound").play();
                    }
                }
                // box 6
                if (mouseOver(mx, my, 400, 200, 150, 80)) {
                    if (hud2.getXp() >= B6) {
                        if (hud2.HEALTH != (100 + (hud2.bounds / 2))) {
                            hud2.setXp(hud2.getXp() - B6);
                            hud2.HEALTH = (100 + (hud2.bounds / 2));
                            if (game_.sfx) audioplayer_.getSound("click_sound").play();
                        }
                    }
                }
            }
        }
    }
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }
    public void tick() {

    }
}
