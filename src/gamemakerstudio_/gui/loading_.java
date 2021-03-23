/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.gui;

import gamemakerstudio_.entities.experimental.placeholdertext_;
import gamemakerstudio_.game_;
import gamemakerstudio_.misc.graphicsstuff.BufferedImageLoader;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.MathUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 *
 * @author ACER
 */
public class loading_ {
    private int loadValue = 255;
    // screen
    private BufferedImage[] loadScreen = new BufferedImage[9];
    private Image[] downscale = new Image[9];
    private BufferedImageLoader loader = new BufferedImageLoader();

    // placeholder for donut

    placeholdertext_ placeholder = new placeholdertext_(0, 0, ID.NULL, "AAAAAAAAAAAAAAAAAAAAAAAAAAA");

    public loading_() {
        for (int i = 1; i < loadScreen.length; i++){
            loadScreen[i-1] = loader.loadImage("resources_/image_/load_/000" + i +".png");
            downscale[i-1] = scale(loadScreen[i-1], 480, 360);
        }
    }
    public void tick() {
        loadValue = (int)game_.loadstate * 2;
        loadValue = MathUtil.clamp(loadValue, 0, 255);
    }
    int frame = 0;
    boolean donutAscii = false;
    public void render(Graphics gc) {
        if (donutAscii){
            // buffer
            gc.setColor(Color.BLACK);
            gc.fillRect(0, 0, game_.WIDTH, game_.HEIGHT);
            gc.setColor(Color.GREEN);
            // clear the screen here
            placeholder.text = "";
            if (game_.loadstate != 100) {
                placeholder.text += window_.title() + "\n";
                placeholder.text += "game_ Engine [Version 1.0] by KENNEDY\n" +
                        "(c) 2020 The Karakters Kompany, game_ Engine. All rights reserved.\n";
                placeholder.text += game_.stringsforloading;
            }
            // donut
            Arrays.fill(b, 0, 1760, ' ');
            Arrays.fill(z, 0, 1760, 0);
            for (j = 0; 6.28 > j; j += 0.07)
                for (i = 0; 6.28 > i; i += 0.02) {
                    double c = Math.sin(i),
                            d = Math.cos(j),
                            e = Math.sin(A),
                            f = Math.sin(j),
                            g = Math.cos(A),
                            h = d + 2,
                            D = 1 / (c * h * e + f * g + 5),
                            l = Math.cos(i),
                            m = Math.cos(B),
                            n = Math.sin(B),
                            t = c * h * g - f * e;
                    int x = (int) (40 + 30 * D * (l * h * m - t * n)),
                            y = (int) (12 + 15 * D * (l * h * n + t * m)),
                            o = x + 80 * y,
                            N = (int) (8 * ((f * e - c * d * g) * m - c * d * e - f * g - l * d * n));
                    if (22 > y && y > 0 && x > 0 && 80 > x && D > z[o]) {
                        z[o] = D;
                        b[o] = new char[]{'.', ',', '-', '~', ':', ';', '=', '!', '*', '#', '$', '@'}[Math.max(N, 0)];
                    }
                }
            for (k = 0; 1761 > k; k++)
                placeholder.text += k % 80 > 0 ? b[k] : 10;
            A += 0.04;
            B += 0.02;

            // render placeholder
            placeholder.render(gc);
        }
        else {
            // default y is 325, offset is 75
            int y = 325 + 75;
            // default y is game_.HEIGHT / 2 + 30
            int yText = game_.HEIGHT / 2 + 30 + 75;

            if (game_.loadstate != 100){
                gc.setColor(new Color(75, loadValue, 0));
                gc.fillRect(game_.WIDTH/2 - 100, y, (int) (200 * (game_.loadstate / 100)), 50);

                gc.setColor(Color.green);
                gc.drawRect(game_.WIDTH/2 - 100, y, 200, 50);

                gc.setFont(new Font("arial", 0, 15));
                gc.drawString(game_.stringsforloading, game_.WIDTH/2 - game_.getTextWidth(gc, game_.stringsforloading)/2,
                        yText);
            }
        }

        frame++;
//        g.drawImage(downscale[frame], 0, 0, null);
        if (frame == 8)
            frame = 0;
    }
    public static Image scale(BufferedImage img, int width, int height){
        return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);  // scale it the smooth way
    }

    // donut vars
    int k;
    double A = 0, B = 0, i, j;
    double[] z = new double[1760];
    char[] b = new char[1760];
}
