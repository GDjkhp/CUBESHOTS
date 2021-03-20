package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.PVector;
import gamemakerstudio_.misc.gameobject_;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static gamemakerstudio_.misc.MathUtil.ImprovedNoise.noise;

public class raycast_ extends gameobject_ implements KeyListener {
    Boundary[] walls;
    Ray ray;
    Particle particle;
    float xoff = 0;
    float yoff = 10000;

    int random(int bounds){
        Random r = new Random();
        return r.nextInt(bounds);
    }

    game_ game;

    public raycast_(float x, float y, ID id, game_ game) {
        super(x, y, id);
        walls = new Boundary[5+4];
        for (int i = 0; i < walls.length; i++) {
            float x1 = random(game_.WIDTH);
            float x2 = random(game_.WIDTH);
            float y1 = random(game_.HEIGHT);
            float y2 = random(game_.HEIGHT);
            walls[i] = new Boundary(x1, y1, x2, y2);
        }
        walls[walls.length-4] = (new Boundary(0, 0, game_.WIDTH, 0));
        walls[walls.length-3] = (new Boundary(game_.WIDTH, 0, game_.WIDTH, game_.HEIGHT));
        walls[walls.length-2] = (new Boundary(game_.WIDTH, game_.HEIGHT, 0, game_.HEIGHT));
        walls[walls.length-1] = (new Boundary(0, game_.HEIGHT, 0, 0));
        particle = new Particle();
        this.game = game;
        game.addKeyListener(this);
    }

    @Override
    public void tick() {
        if (up)
            particle.pos.y += -5;
        if (down)
            particle.pos.y += 5;
        if (left)
            particle.pos.x += -5;
        if (right)
            particle.pos.x += 5;
    }

    @Override
    public void render(Graphics g) {
        for (Boundary wall : walls) {
            wall.show(g);
        }
        // particle.update((float)noise(xoff) * game_.WIDTH, (float)noise(yoff) * game_.HEIGHT);
        particle.show(g);
        particle.look(walls, g);

        xoff += 0.01;
        yoff += 0.01;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
    
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP)
            up = true;
        if (key == KeyEvent.VK_DOWN)
            down = true;
        if (key == KeyEvent.VK_LEFT)
            left = true;
        if (key == KeyEvent.VK_RIGHT)
            right = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP)
            up = false;
        if (key == KeyEvent.VK_DOWN)
            down = false;
        if (key == KeyEvent.VK_LEFT)
            left = false;
        if (key == KeyEvent.VK_RIGHT)
            right = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    class Ray {
        PVector pos, dir;
        Ray(PVector _pos, float angle) {
            this.pos = _pos;
            this.dir = PVector.fromAngle(angle);
        }

        void lookAt(float x, float y) {
            this.dir.x = x - this.pos.x;
            this.dir.y = y - this.pos.y;
            this.dir.normalize();
        }

        void show(Graphics g) {
            g.drawLine((int)this.pos.x, (int)this.pos.y, (int)this.dir.x * 10, (int)this.dir.y * 10);
        }

        PVector cast(Boundary wall) {
            float x1 = wall.a.x;
            float y1 = wall.a.y;
            float x2 = wall.b.x;
            float y2 = wall.b.y;

            float x3 = this.pos.x;
            float y3 = this.pos.y;
            float x4 = this.pos.x + this.dir.x;
            float y4 = this.pos.y + this.dir.y;

            float den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
            if (den == 0) {
                return null;
            }

            float t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
            float u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;
            if (t > 0 && t < 1 && u > 0) {
                PVector pt = new PVector();
                pt.x = x1 + t * (x2 - x1);
                pt.y = y1 + t * (y2 - y1);
                return pt;
            } else {
                return null;
            }
        }
    }
    class Boundary {
        PVector a, b;
        Boundary(float x1, float y1, float x2, float  y2) {
            this.a = new PVector(x1, y1);
            this.b = new PVector(x2, y2);
        }

        void show(Graphics g) {
            g.drawLine((int)this.a.x, (int)this.a.y, (int)this.b.x, (int)this.b.y);
        }
    }
    class Particle {
        public PVector pos;
        Ray[] rays;
        Particle() {
            this.pos = new PVector(game_.WIDTH / 2, game_.HEIGHT / 2);
            this.rays = new Ray[360];
            for (int a = 0; a < this.rays.length; a += 1) {
                this.rays[a] = new Ray(this.pos, (float) Math.toRadians(a));
            }
        }

        void update(float x, float y) {
            this.pos.set(x, y);
        }

        void look(Boundary[] walls, Graphics g) {
            for (int i = 0; i < this.rays.length; i++) {
                Ray ray = this.rays[i];
                PVector closest = null;
                float record = 500000000;
                for (Boundary wall : walls) {
                    PVector pt = ray.cast(wall);
                    if (pt != null) {
                        float d = PVector.dist(this.pos, pt);
                        if (d < record) {
                            record = d;
                            closest = pt;
                        }
                    }
                }
                if (closest != null) {
                    // colorMode(HSB);
                    // stroke((i + frameCount * 2) % 360, 255, 255, 50);
                    g.drawLine((int)this.pos.x, (int)this.pos.y, (int)closest.x, (int)closest.y);
                }
            }
        }

        void show(Graphics g) {
            g.drawOval((int)this.pos.x, (int)this.pos.y, 4, 4);
            for (Ray ray : this.rays) {
                ray.show(g);
            }
        }
    }

}
