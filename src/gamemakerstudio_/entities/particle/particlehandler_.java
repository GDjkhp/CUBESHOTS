package gamemakerstudio_.entities.particle;

import gamemakerstudio_.entities.spicymenu_;
import gamemakerstudio_.game_;
import gamemakerstudio_.misc.STATE;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static gamemakerstudio_.game_.*;

public class particlehandler_ extends gameobject_ implements KeyListener {
    handler_ handler;

    public enum PARTICLE{
        Spicy, Covid, Cube, Terrain
    }

    public particlehandler_(float x, float y, ID id, handler_ handler, game_ game) {
        super(x, y, id);
        this.handler = handler;
        game.addKeyListener(this);
        // init
        currentParticle = initParticle(currentParticle);
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {}

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (game_.gameState == STATE.Menu && key == KeyEvent.VK_SPACE){
            currentParticle = switchParticle(currentParticle);
        }
    }

    public PARTICLE switchParticle(PARTICLE current){
        switch (current){
            case Spicy:
                handler.removeAllSelectedObjects(ID.Spicy);
                handler.addObject(new covidsphere_(0, 0, ID.SpicyCovid));
                return PARTICLE.Covid;
            case Covid:
                handler.removeAllSelectedObjects(ID.SpicyCovid);
                handler.addObject(new cube3d_(0, 0, ID.Spicy3D));
                return PARTICLE.Cube;
            case Cube:
                handler.removeAllSelectedObjects(ID.Spicy3D);
                handler.addObject(new terrain_(0, 0, ID.SpicyTerrain));
                return PARTICLE.Terrain;
            default:
                handler.removeAllSelectedObjects(ID.SpicyTerrain);
                for (int i = 1; i <= 50; i++)
                    handler.addObject(new spicymenu_(r.nextInt(WIDTH - 20), r.nextInt(HEIGHT - 20), ID.Spicy, handler));
                return PARTICLE.Spicy;
        }
    }
    public PARTICLE initParticle(PARTICLE current){
        switch (current){
            case Covid:
                handler.addObject(new covidsphere_(0, 0, ID.SpicyCovid));
                return PARTICLE.Covid;
            case Cube:
                handler.addObject(new cube3d_(0, 0, ID.Spicy3D));
                return PARTICLE.Cube;
            default:
                for (int i = 1; i <= 50; i++)
                    handler.addObject(new spicymenu_(r.nextInt(WIDTH - 20), r.nextInt(HEIGHT - 20), ID.Spicy, handler));
                return PARTICLE.Spicy;
        }
    }


    Random r = new Random();
}
