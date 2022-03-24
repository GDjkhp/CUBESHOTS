package gamemakerstudio_.entities.particle;

import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static gamemakerstudio_.game_.HEIGHT;
import static gamemakerstudio_.game_.WIDTH;

public class cube3d_ extends gameobject_ {
    // 3D stuff
    ArrayList<Cube> cubes = new ArrayList<Cube>();
    float FIELD_OF_VIEW = WIDTH * 0.8f; // float PERSPECTIVE
    float PROJECTION_CENTER_X = WIDTH / 2f;
    float PROJECTION_CENTER_Y = HEIGHT / 2f;

    // cube const
    int[][] CUBE_LINES = {{0, 1}, {1, 3}, {3, 2}, {2, 0}, {2, 6}, {3, 7}, {0, 4}, {1, 5}, {6, 7}, {6, 4}, {7, 5}, {4, 5}};
    int[][] CUBE_VERTICES = {{-1, -1, -1}, {1, -1, -1}, {-1, 1, -1}, {1, 1, -1}, {-1, -1, 1}, {1, -1, 1}, {-1, 1, 1}, {1, 1, 1}};

    // TODO: 3D cubes floating around, see How to Render 3D in 2D Canvas
    public cube3d_(float x, float y, ID id) {
        super(x, y, id);
        createCubes();
    }

    @Override
    public void tick() {
        for (Cube cube : cubes) cube.tick();
    }

    @Override
    public void render(Graphics g) {
        for (Cube cube : cubes) cube.render(g);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    class Cube {
        public float x, y, z, radius;

        public Cube(){
            x = (float)((Math.random() - 0.5) * WIDTH);
            y = (float)((Math.random() - 0.5) * WIDTH);
            z = (float)((Math.random() - 0.5) * WIDTH);
            radius = (float)(Math.floor(Math.random() * 12 + 10));
        }

        class v {
            public float x;
            public float y;
            public float z;

            public v(float x, float y, float z){
                this.x = x;
                this.y = y;
                this.z = z;
            }
        }

        // movement
        float xTemp = (float)((Math.random() - 0.5) * (WIDTH * 0.5) / WIDTH);
        float yTemp = (float)((Math.random() - 0.5) * (WIDTH * 0.5) / WIDTH);
        float zTemp = (float)((Math.random() - 0.5) * WIDTH / WIDTH);

        int defaultDelta = (int)(Math.random() * 20 + 15) * 100;
        int delta = defaultDelta;

        public void tick(){
            if (delta != 0){
                delta--;

                /*x += (Math.random() - 0.5) * (WIDTH * 0.5);
                y += (Math.random() - 0.5) * (WIDTH * 0.5);
                z += (Math.random() - 0.5) * WIDTH;*/

                x += xTemp;
                y += yTemp;
                z += zTemp;
            }
            else {
                delta = defaultDelta;
                xTemp = -xTemp;
                yTemp = -yTemp;
                zTemp = -zTemp;
            }

            fillPolygon();
        }

        public v project(float x, float y, float z){
            float scaleProjected = FIELD_OF_VIEW / (FIELD_OF_VIEW + z); // change + to * for weird effect
            float xProjected = (x * scaleProjected) + PROJECTION_CENTER_X;
            float yProjected = (y * scaleProjected) + PROJECTION_CENTER_Y;

            return new Cube.v(xProjected, yProjected, scaleProjected);
        }

        public void render(Graphics g){
            if (z < -FIELD_OF_VIEW + radius)
                return;

            if (dump3DInfo) System.out.println("Cube: x = " + x + ", y = " + y + ", z = " + z + "\n");

            g.setColor(color);
            if (randBool[0])
                g.fillPolygon(frontFace);
            if (randBool[1])
                g.fillPolygon(backFace);
            if (randBool[2])
                g.fillPolygon(upperFace);
            if (randBool[3])
                g.fillPolygon(bottomFace);
            if (randBool[4])
                g.fillPolygon(leftFace);
            if (randBool[5])
                g.fillPolygon(rightFace);

            for (int i = 0; i < CUBE_LINES.length; i++){

                v v1 = new v(x + (radius * CUBE_VERTICES[CUBE_LINES[i][0]][0]),
                        y + (radius * CUBE_VERTICES[CUBE_LINES[i][0]][1]),
                        z + (radius * CUBE_VERTICES[CUBE_LINES[i][0]][2]));

                v v2 = new v(x + (radius * CUBE_VERTICES[CUBE_LINES[i][1]][0]),
                        y + (radius * CUBE_VERTICES[CUBE_LINES[i][1]][1]),
                        z + (radius * CUBE_VERTICES[CUBE_LINES[i][1]][2]));

                if (dump3DInfo) {
                    System.out.println("1: x = " + v1.x + ", y = " + v1.y + ", z = " + v1.z);
                    System.out.println("2: x = " + v2.x + ", y = " + v2.y + ", z = " + v2.z + "\n");
                }

                v v1Project = project(v1.x, v1.y, v1.z);
                v v2Project = project(v2.x, v2.y, v2.z);

                if (dump3DInfo) {
                    System.out.println("1 Project: x = " + v1Project.x + ", y = " + v1Project.y);
                    System.out.println("2 Project: x = " + v2Project.x + ", y = " + v2Project.y + "\n");
                }

                g.setColor(colorLine);
                g.drawLine((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y);
            }

            // switch lock
            dump3DInfo = false;
        }

        boolean dump3DInfo = false;
        private Random r = new Random();
        private Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        private Color colorLine = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        // polygons
        Polygon frontFace = new Polygon();
        Polygon upperFace = new Polygon();
        Polygon bottomFace = new Polygon();
        Polygon backFace = new Polygon();
        Polygon leftFace = new Polygon();
        Polygon rightFace = new Polygon();
        // random boolean
        boolean[] randBool = new boolean[]{r.nextBoolean(), r.nextBoolean(), r.nextBoolean(), r.nextBoolean(), r.nextBoolean(), r.nextBoolean()};

        public Polygon createPolygon(ArrayList<Line2D> lines){
            Polygon p = new Polygon();
            if (lines.size() < 2) return p;

            Line2D line = lines.get(0);
            addPoint(p, line.getP1());

            for (int i = 1; i < lines.size(); i++) {
                addPoint(p, line.getP2());
                line = lines.get(i);
            }
            addPoint(p, line.getP2());
            lines.clear();
            return p;
        }
        public void addPoint(Polygon p, Point2D point){
            int x = (int) point.getX();
            int y = (int) point.getY();
            p.addPoint(x, y);
        }
        public void fillPolygon(){
            ArrayList<Line2D> lines = new ArrayList<>();
            ArrayList<Line2D> leftFaceLines = new ArrayList<>();
            ArrayList<Line2D> rightFaceLines = new ArrayList<>();

            if (dump3DInfo) System.out.println("Cube: x = " + x + ", y = " + y + ", z = " + z + "\n");

            for (int i = 0; i < CUBE_LINES.length; i++){

                v v1 = new v(x + (radius * CUBE_VERTICES[CUBE_LINES[i][0]][0]),
                             y + (radius * CUBE_VERTICES[CUBE_LINES[i][0]][1]),
                             z + (radius * CUBE_VERTICES[CUBE_LINES[i][0]][2]));

                v v2 = new v(x + (radius * CUBE_VERTICES[CUBE_LINES[i][1]][0]),
                             y + (radius * CUBE_VERTICES[CUBE_LINES[i][1]][1]),
                             z + (radius * CUBE_VERTICES[CUBE_LINES[i][1]][2]));

                if (dump3DInfo) {
                    System.out.println("1: x = " + v1.x + ", y = " + v1.y + ", z = " + v1.z);
                    System.out.println("2: x = " + v2.x + ", y = " + v2.y + ", z = " + v2.z + "\n");
                }

                v v1Project = project(v1.x, v1.y, v1.z);
                v v2Project = project(v2.x, v2.y, v2.z);

                if (dump3DInfo) {
                    System.out.println("1 Project: x = " + v1Project.x + ", y = " + v1Project.y);
                    System.out.println("2 Project: x = " + v2Project.x + ", y = " + v2Project.y + "\n");
                }

                // front face
                if (i < 4) lines.add(new Line2D.Float((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y));
                if (i == 4) {
                    frontFace = createPolygon(lines);
                    lines.add(new Line2D.Float((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y));
                    leftFaceLines.add(lines.get(lines.size() - 1));
                }
                // bottom face
                if (i == 5) {
                    lines.add(new Line2D.Float((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y));
                    rightFaceLines.add(lines.get(lines.size() - 1));
                }
                if (i == 6) {
                    // FIXME: implement crappy algorithm
                    lines.add(new Line2D.Float(lines.get(lines.size() - 2).getP1(), lines.get(lines.size() - 1).getP1()));
                    lines.add(new Line2D.Float(lines.get(lines.size() - 2).getP2(), lines.get(lines.size() - 1).getP2()));

                    bottomFace = createPolygon(lines);

                    lines.add(new Line2D.Float((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y));
                    leftFaceLines.add(lines.get(lines.size() - 1));
                }
                // upper face
                if (i == 7) {
                    lines.add(new Line2D.Float((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y));
                    rightFaceLines.add(lines.get(lines.size() - 1));
                }
                if (i == 8) {
                    // FIXME: implement crappy algorithm
                    lines.add(new Line2D.Float(lines.get(lines.size() - 2).getP1(), lines.get(lines.size() - 1).getP1()));
                    lines.add(new Line2D.Float(lines.get(lines.size() - 2).getP2(), lines.get(lines.size() - 1).getP2()));

                    upperFace = createPolygon(lines);
                }
                // back face
                if (i == 9) lines.add(new Line2D.Float((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y));
                if (i == 10) {
                    lines.add(new Line2D.Float((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y));
                    // FIXME: implement crappy algorithm
                    lines.add(new Line2D.Float(lines.get(lines.size() - 2).getP1(), lines.get(lines.size() - 1).getP1()));
                    lines.add(new Line2D.Float(lines.get(lines.size() - 2).getP2(), lines.get(lines.size() - 1).getP2()));

                    backFace = createPolygon(lines);
                }
            }

            // left face
            leftFaceLines.add(new Line2D.Float(leftFaceLines.get(leftFaceLines.size() - 2).getP1(), leftFaceLines.get(leftFaceLines.size() - 1).getP1()));
            leftFaceLines.add(new Line2D.Float(leftFaceLines.get(leftFaceLines.size() - 2).getP2(), leftFaceLines.get(leftFaceLines.size() - 1).getP2()));
            leftFace = createPolygon(leftFaceLines);

            // right face
            rightFaceLines.add(new Line2D.Float(rightFaceLines.get(rightFaceLines.size() - 2).getP1(), rightFaceLines.get(rightFaceLines.size() - 1).getP1()));
            rightFaceLines.add(new Line2D.Float(rightFaceLines.get(rightFaceLines.size() - 2).getP2(), rightFaceLines.get(rightFaceLines.size() - 1).getP2()));
            rightFace = createPolygon(rightFaceLines);

            if (dump3DInfo) {
                System.out.println("Front Face: x: " + Arrays.toString(frontFace.xpoints) + ", y: " + Arrays.toString(frontFace.ypoints) + ", npoints: " + frontFace.npoints);
                System.out.println("Back Face: x: " + Arrays.toString(backFace.xpoints) + ", y: " + Arrays.toString(backFace.ypoints) + ", npoints: " + backFace.npoints);
                System.out.println("Upper Face: x: " + Arrays.toString(upperFace.xpoints) + ", y: " + Arrays.toString(upperFace.ypoints) + ", npoints: " + upperFace.npoints);
                System.out.println("Bottom Face: x: " + Arrays.toString(bottomFace.xpoints) + ", y: " + Arrays.toString(bottomFace.ypoints) + ", npoints: " + bottomFace.npoints);
                System.out.println("Left Face: x: " + Arrays.toString(leftFace.xpoints) + ", y: " + Arrays.toString(leftFace.ypoints) + ", npoints: " + leftFace.npoints);
                System.out.println("Right Face: x: " + Arrays.toString(rightFace.xpoints) + ", y: " + Arrays.toString(rightFace.ypoints) + ", npoints: " + rightFace.npoints + "\n");
            }

            // switch lock
            dump3DInfo = false;
        }
    }

    public void createCubes(){
        cubes.clear();
        // Create a new dot based on the amount needed
        for (int i = 0; i < 100; i++) {
            cubes.add(new Cube());
        }
    }
}
