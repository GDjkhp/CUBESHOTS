package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.gameobject_;

import java.awt.*;

public class flowparticle_ extends gameobject_ {
    float displacementY;
    float radius;
    float speed;
    float curvature;
    float phase;

    // variable stuff

    public flowparticle_(float x, float y, ID id, float radius, float speed, float curvature, float phase) {
        super(x, y, id);
        this.displacementY = 0;
        this.radius = radius;
        this.speed = speed;
        this.curvature = curvature;
        this.phase = phase;
        width = (int)radius;
        height = (int)radius;
        color = Color.WHITE;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void render(Graphics g, float mean, float value) {
        x += speed * 0.25 + speed * 0.75 * Math.abs(mean) - speed * 5 * Math.abs(value);

        displacementY = (float) Math.sin(x / 100.0f + phase) * curvature;

        g.setColor(color);
        g.fillRect((int)x, (int)(y + displacementY), (int)(radius + radius * 2 * Math.abs(value)), (int)(radius + radius * 2 * Math.abs(value)));

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
    public float getPosX() {
        return x;
    }

    public float getRadius() {
        return radius;
    }

    public void incrementX(float speed){
        x += (speed * 0.25 + speed * 0.75 * 0 - speed * 5 * 0)*32f;
    }
}
