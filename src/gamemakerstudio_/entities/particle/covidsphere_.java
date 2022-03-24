package gamemakerstudio_.entities.particle;

import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.testgame_;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static gamemakerstudio_.game_.HEIGHT;
import static gamemakerstudio_.game_.WIDTH;

public class covidsphere_ extends gameobject_ {
    // 3D stuff
    float PROJECTION_CENTER_X = WIDTH / 2f;
    float PROJECTION_CENTER_Y = HEIGHT / 2f;
    float rotation = 0;
    int DOTS_AMOUNT = 1000;
    float DOT_RADIUS = 16; // default is 4
    float GLOBE_RADIUS = WIDTH * 0.7f;
    float GLOBE_CENTER_Z = -GLOBE_RADIUS;
    float FIELD_OF_VIEW = WIDTH * 0.8f;

    ArrayList<Dot> dots = new ArrayList<Dot>();
    public covidsphere_(float x, float y, ID id) {
        super(x, y, id);
        createDots();
    }

    float a = 0f;
    @Override
    public void tick() {
        // Increase the globe rotation
        a++;
        rotation = a * 0.03f;

        for (Dot dot : dots) {
            // dot.tick();

            float sineRotation = (float)Math.sin(rotation);
            float cosineRotation = (float)Math.cos(rotation);
            dot.project(sineRotation, cosineRotation);
        }

    }

    @Override
    public void render(Graphics g) {
        for (Dot dot : dots) dot.render(g);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    class Dot {
        public float x, y, z, radius, xProjected, yProjected, scaleProjected;
        private float alpha = 1;
        private float life;
        Random r = new Random();
        private Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));;

        public Dot(float x, float y, float z){

            /*x = (float)(Math.random() - 0.5f) * WIDTH;
            y = (float)(Math.random() - 0.5f) * HEIGHT;
            z = (float)(Math.random() * WIDTH);*/

            this.x = x;
            this.y = y;
            this.z = z;

            // radius = 10f;
            xProjected = 0;
            yProjected = 0;
            scaleProjected = 0;
        }

        public void tick(){
            /*scaleProjected = PERSPECTIVE / (PERSPECTIVE + z);
            xProjected = (x * scaleProjected) + PROJECTION_CENTER_X;
            yProjected = (y * scaleProjected) + PROJECTION_CENTER_Y;

            alpha = Math.abs(1 - z / WIDTH);*/
        }

        public void project(float sin, float cos){
            float rotX = cos * x + sin * (z - GLOBE_CENTER_Z);
            float rotZ = -sin * x + cos * (z - GLOBE_CENTER_Z) + GLOBE_CENTER_Z;

            scaleProjected = FIELD_OF_VIEW / (FIELD_OF_VIEW - rotZ);
            xProjected = (rotX * scaleProjected) + PROJECTION_CENTER_X;
            yProjected = (y * scaleProjected) + PROJECTION_CENTER_Y;

            // alpha = scaleProjected;

            /*System.out.println("x: " + x + ", y: " + y + ", z: " + z + ", xProjected: " + xProjected + ", yProjected: " + yProjected + ", scaleProjected: " + scaleProjected);
            System.out.println("alpha: " + alpha);*/
        }

        public void render(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(makeTransparent(alpha));
            g.setColor(color);

            // g.fillRect((int)(xProjected - radius), (int)(yProjected - radius), (int)(radius * 2 * scaleProjected), (int)(radius * 2 * scaleProjected));
            g.fillOval((int)xProjected, (int)yProjected, (int)(DOT_RADIUS * scaleProjected), (int)(DOT_RADIUS * scaleProjected));

            g2d.setComposite(makeTransparent(1));
        }

        private AlphaComposite makeTransparent(float alpha) {
            int type = AlphaComposite.SRC_OVER;
            return(AlphaComposite.getInstance(type, alpha));
        }
    }

    public void createDots(){
        dots.clear();
        for (int i = 0; i < DOTS_AMOUNT; i++){
            float theta = (float)(Math.random() * 2 * Math.PI);
            float phi = (float)(Math.acos((Math.random() * 2) - 1));

            float x = (float)(GLOBE_RADIUS * Math.sin(phi) * Math.cos(theta));
            float y = (float)(GLOBE_RADIUS * Math.sin(phi) * Math.sin(theta));
            float z = (float)((GLOBE_RADIUS * Math.cos(phi)) + GLOBE_CENTER_Z);

            dots.add(new Dot(x, y, z));
        }
    }
}
