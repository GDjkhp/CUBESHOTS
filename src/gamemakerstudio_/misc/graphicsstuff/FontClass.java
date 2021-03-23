/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.misc.graphicsstuff;

import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 *
 * @author ACER
 */
public class FontClass {
    public static void loadfont() {
        //create the font
        try {
        //create the font to use. Specify the size!
            Font andy = Font.createFont(Font.TRUETYPE_FONT, new File("resources_/fonts_/andy bold.ttf"));
            Font mojangles = Font.createFont(Font.TRUETYPE_FONT, new File("resources_/fonts_/mojangles.ttf"));
            Font pusab = Font.createFont(Font.TRUETYPE_FONT, new File("resources_/fonts_/PUSAB___.otf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
            ge.registerFont(andy);
            ge.registerFont(mojangles);
            ge.registerFont(pusab);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}
 
