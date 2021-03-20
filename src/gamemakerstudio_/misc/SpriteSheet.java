/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.misc;

import java.awt.image.BufferedImage;

/**
 *
 * @author ACER
 */
public class SpriteSheet {
    private BufferedImage sprite;
    public SpriteSheet (BufferedImage ss) {
        this.sprite = ss;
    }
    public BufferedImage grabImage (int col, int row, int width, int height) {
        BufferedImage img = sprite.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
        return img;
    }
}
