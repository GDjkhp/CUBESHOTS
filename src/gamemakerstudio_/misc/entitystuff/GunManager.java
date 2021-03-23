package gamemakerstudio_.misc.entitystuff;

import gamemakerstudio_.entities.guns.*;

public class GunManager {
    // guns
    public enum GUN {
        Default,
        Chlorophyte,
        Electrocute,
        Ricochet,
        Whip,
        Rocket,
        Arkhalis,
        Lasergun,
        Terraprisma,
        Boomerang
    }

    // player gun load out
    public static GUN playerOneGunLoadOut = GUN.Default;
    public static GUN playerTwoGunLoadOut = GUN.Default;

    public static GUN switchWeapon(GUN current){
        switch (current){
            case Default:
                return GUN.Chlorophyte;
            case Chlorophyte:
                return GUN.Electrocute;
            case Electrocute:
                return GUN.Ricochet;
            case Ricochet:
                return GUN.Rocket;
            case Rocket:
                return GUN.Lasergun;
            default:
                return GUN.Default;
        }
    }

    public static void shootCodes(float x, float y, handler_ handler, GUN current, ID player){
        // default bullets
        if (current == GunManager.GUN.Default) {
            handler.addObject(new bullet_(x + 10, y + 10, ID.BulletHell,
                    handler, 0, -5));
            handler.addObject(new bullet_(x + 10, y + 10, ID.BulletHell,
                    handler, 5, -5));
            handler.addObject(new bullet_(x + 10, y + 10, ID.BulletHell,
                    handler, -5, -5));
        }
        // chlorophyte bullets
        if (current == GunManager.GUN.Chlorophyte) {
            if (player == ID.Player){
                handler.addObject(new chlorophyte_(x + 10, y + 10, ID.ChlorophyteP1,
                        handler, 0, -15));
                handler.addObject(new chlorophyte_(x + 20, y + 20, ID.ChlorophyteP1,
                        handler, 15, -15));
                handler.addObject(new chlorophyte_(x, y + 20, ID.ChlorophyteP1, handler,
                        -15, -15));
            }
            if (player == ID.Player2){
                handler.addObject(new chlorophyte_(x + 10, y + 10, ID.ChlorophyteP2,
                        handler, 0, -15));
                handler.addObject(new chlorophyte_(x + 20, y + 20, ID.ChlorophyteP2,
                        handler, 15, -15));
                handler.addObject(new chlorophyte_(x, y + 20, ID.ChlorophyteP2, handler,
                        -15, -15));
            }
        }
        // electrocute bullets
        if (current == GunManager.GUN.Electrocute) {
            if (player == ID.Player){
                handler.addObject(new electrocutebullet_(x + 10, y + 10, ID.ElectrocuteP1,
                        handler, 0, -15));
            }
            if (player == ID.Player2){
                handler.addObject(new electrocutebullet_(x + 10, y + 10, ID.ElectrocuteP2,
                        handler, 0, -15));
            }
        }
        // ricochet
        if (current == GunManager.GUN.Ricochet){
            handler.addObject(new ricochet_(x + 10, y + 10, ID.Ricochet, handler, 0, -10));
            handler.addObject(new ricochet_(x + 10, y + 10, ID.Ricochet, handler, 0, 10));
            handler.addObject(new ricochet_(x + 10, y + 10, ID.Ricochet, handler, 10, 0));
            handler.addObject(new ricochet_(x + 10, y + 10, ID.Ricochet, handler, -10, 0));
            handler.addObject(new ricochet_(x + 10, y + 10, ID.Ricochet, handler, 10, -10));
            handler.addObject(new ricochet_(x + 10, y + 10, ID.Ricochet, handler, -10, -10));
            handler.addObject(new ricochet_(x + 10, y + 10, ID.Ricochet, handler, 10, 10));
            handler.addObject(new ricochet_(x + 10, y + 10, ID.Ricochet, handler, -10, 10));
        }
        // rocket
        if (current == GUN.Rocket)
            handler.addObject(new rocket_(x, y, ID.Rocket, handler, 0, -3));
        // laser
        if (current == GUN.Lasergun) {
            if (player == ID.Player)
                handler.addObject(new lasergun_(x + 15, y + 15, ID.LasergunP1, handler));
            if (player == ID.Player2) {
                handler.addObject(new lasergun_(x + 15, y + 15, ID.LasergunP2, handler));
            }
        }

    }
}
