package gamemakerstudio_.misc.graphicsstuff;

import gamemakerstudio_.game_;

import java.awt.*;
import java.awt.image.BufferedImage;

public class assets_ {

    public static BufferedImage tile1, tile2, tile3, xgamer, heartimage,
            soundiconon, soundiconoff, crazyboss, targetimage, explode, 
            portalBlue1, portalBlue2, portalBlue3, portalBlue4,
            portalRed1, portalRed2, portalRed3, portalRed4, laser, spike,
            noob, hacker, corrupted, serponge, alien, btpou, coolio, arrow,
            octo, partition, monster, glow, zig, sharp, vamp, demon, rocky, juni, lava,
            slash0, slash1, slash2, slash3, slash4, slash5, slash6, slash7;

    public static Image upscaledxgamer, upscaledcrazyboss, locktargetimage, maxExplode,
            maxBlue1, maxBlue2, maxBlue3, maxBlue4,
            maxRed1, maxRed2, maxRed3, maxRed4,
            minSlash0, minSlash1, minSlash2, minSlash3, minSlash4, minSlash5, minSlash6, minSlash7;

    public static void init(BufferedImage spritesheet) {
        SpriteSheet ss = new SpriteSheet(spritesheet);

        tile1 = ss.grabImage(1, 4, 32, 32);
        tile2 = ss.grabImage(2, 4, 32, 32);
        tile3 = ss.grabImage(3,4,32,32);
        heartimage = ss.grabImage(1, 1, 16, 16);
        soundiconon = ss.grabImage(2, 1, 32, 32);
        soundiconoff = ss.grabImage(3, 1, 32, 32);
        laser = ss.grabImage(9, 5, 32, 32);
        spike = ss.grabImage(9, 7, 64, 64);
        noob = ss.grabImage(13, 7, 32, 32);
        hacker = ss.grabImage(14, 7, 32, 32);
        corrupted = ss.grabImage(15, 7, 32, 32);
        serponge = ss.grabImage(16, 7, 32, 32);
        alien = ss.grabImage(11, 8, 32, 32);
        btpou = ss.grabImage(12, 8, 32, 32);
        coolio = ss.grabImage(13, 8, 32, 32);
        arrow = ss.grabImage(1, 9, 128,192);
        octo = ss.grabImage(15, 8, 32, 32);
        partition = ss.grabImage(14, 8, 32, 32);
        monster = ss.grabImage(16, 8,32, 32);
        glow = ss.grabImage(5, 9, 32, 32);
        zig = ss.grabImage(6, 9, 32, 32);
        vamp = ss.grabImage(7, 9, 32, 32);
        sharp = ss.grabImage(8, 9, 32, 32);
        demon = ss.grabImage(9, 9 ,32, 32);
        rocky = ss.grabImage(10, 9, 32, 32);
        juni = ss.grabImage(11, 9, 32, 32);
        lava = ss.grabImage(12, 9, 32, 32);

        // test for upscale
        xgamer = ss.grabImage(2, 2, 32, 32);
        crazyboss = ss.grabImage(1, 2, 32, 32);
        targetimage = ss.grabImage(4, 2, 32, 32);
        explode = ss.grabImage(5, 5, 128, 128);

        upscaledxgamer = scaleImage(xgamer, 256, 256);
        upscaledcrazyboss = scaleImage(crazyboss, 256, 256);
        locktargetimage = scaleImage(targetimage, 30, 30);
        maxExplode = scaleImage(explode, 512, 512);

        portalBlue1 = ss.grabImage(9, 1, 64, 64);
        portalBlue2 = ss.grabImage(11, 1, 64, 64);
        portalBlue3 = ss.grabImage(13, 1, 64, 64);
        portalBlue4 = ss.grabImage(15, 1, 64, 64);
        portalRed1 = ss.grabImage(9, 3, 64, 64);
        portalRed2 = ss.grabImage(11, 3, 64, 64);
        portalRed3 = ss.grabImage(13, 3, 64, 64);
        portalRed4 = ss.grabImage(15, 3, 64, 64);
        
        maxBlue1 = scaleImage(portalBlue1, 128, 128);
        maxBlue2 = scaleImage(portalBlue2, 128, 128);
        maxBlue3 = scaleImage(portalBlue3, 128, 128);
        maxBlue4 = scaleImage(portalBlue4, 128, 128);
        maxRed1 = scaleImage(portalRed1, 128, 128);
        maxRed2 = scaleImage(portalRed2, 128, 128);
        maxRed3 = scaleImage(portalRed3, 128, 128);
        maxRed4 = scaleImage(portalRed4, 128, 128);

        slash0 = ss.grabImage(17, 1, 256, 256);
        slash1 = ss.grabImage(25, 1, 256, 256);
        slash2 = ss.grabImage(17, 9, 256, 256);
        slash3 = ss.grabImage(25, 9,256, 256);
        slash4 = ss.grabImage(17, 17, 256, 256);
        slash5 = ss.grabImage(25, 17, 256, 256);
        slash6 = ss.grabImage(17, 25, 256, 256);
        slash7 = ss.grabImage(25, 25,256, 256);

        minSlash0 = scaleImageByPercent(slash0, 50, 50);
        minSlash1 = scaleImageByPercent(slash1, 50, 50);
        minSlash2 = scaleImageByPercent(slash2, 50, 50);
        minSlash3 = scaleImageByPercent(slash3, 50, 50);
        minSlash4 = scaleImageByPercent(slash4, 50, 50);
        minSlash5 = scaleImageByPercent(slash5, 50, 50);
        minSlash6 = scaleImageByPercent(slash6, 50, 50);
        minSlash7 = scaleImageByPercent(slash7, 50, 50);
    }
    public static Image scaleImage(Image img, float width, float height){
//        return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);  // scale it the smooth way
        return img.getScaledInstance((int)width, (int)height, Image.SCALE_SMOOTH);
    }

    public static Image scaleImageByPercent(Image img, float widthPercent, float heightPercent){
        float width = img.getWidth(null) * (widthPercent * 0.01f);
        float height = img.getHeight(null) * (heightPercent * 0.01f);
        return img.getScaledInstance((int)width, (int)height, Image.SCALE_SMOOTH);
    }

}
