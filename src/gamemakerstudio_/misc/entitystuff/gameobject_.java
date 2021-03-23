/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.misc.entitystuff;

import java.awt.*;

/**
 *
 * @author ACER
 */
public abstract class gameobject_ {

    protected float x, y;
    protected ID id;
    protected float velX = 0, velY = 0;
    protected int width, height;
    protected double spawnTimer = 0;
    protected boolean isTeleporting = false;
    protected Color color;
    
    public gameobject_(float x, float y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    // still unimplemented, might remove later
//    public abstract void health();
    
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setId(ID id) {
        this.id = id;
    }
    public ID getId() {
        return id;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }
    public void setVelY(float velY) {
        this.velY = velY;
    }
    public float getVelX() {
        return velX;
    }
    public float getVelY() {
        return velY;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public int getWidth() {return width;}
    public void setHeight(int height) {
        this.height = height;
    }
    public int getHeight() {
        return height;
    }

    public void setSpawnTimer(double spawnTimer) { this.spawnTimer = spawnTimer; }

    public boolean isTeleporting() {
        return isTeleporting;
    }

    public void setTeleporting(boolean teleporting) {
        isTeleporting = teleporting;
    }
}
