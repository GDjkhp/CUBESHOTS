package gamemakerstudio_;

import java.awt.*;
import java.util.ArrayList;

public class JavaTestClassForStackoverflow {
    // dimension
    public static int WIDTH = 720, HEIGHT = 1360/2;
    // 3D stuff
    float PROJECTION_CENTER_X = WIDTH / 2f;
    float PROJECTION_CENTER_Y = HEIGHT / 2f;
    float FIELD_OF_VIEW = WIDTH * 0.8f;
    // cube const
    int[][] CUBE_LINES = {{0, 1}, {1, 3}, {3, 2}, {2, 0}, {2, 6}, {3, 7}, {0, 4}, {1, 5}, {6, 7}, {6, 4}, {7, 5}, {4, 5}};
    int[][] CUBE_VERTICES = {{-1, -1, -1}, {1, -1, -1}, {-1, 1, -1}, {1, 1, -1}, {-1, -1, 1}, {1, -1, 1}, {-1, 1, 1}, {1, 1, 1}};

    class Cube {
        public float x, y, z, radius;

        public Cube(){
            x = (float)((Math.random() - 0.5) * WIDTH);
            y = (float)((Math.random() - 0.5) * WIDTH);
            z = (float)((Math.random() - 0.5) * WIDTH);
            radius = (float)(Math.floor(Math.random() * 100 + 10));
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

        public void tick(){}

        public v project(float x, float y, float z){
            float scaleProjected = FIELD_OF_VIEW / (FIELD_OF_VIEW * z);
            float xProjected = (x * scaleProjected) + PROJECTION_CENTER_X;
            float yProjected = (y * scaleProjected) + PROJECTION_CENTER_Y;

            return new v(xProjected, yProjected, scaleProjected);
        }
        public void render(Graphics g){
            if (z < - FIELD_OF_VIEW + radius)
                return;

            System.out.println("Cube: x = " + x + ", y = " + y + ", z = " + z + "\n");

            for (int i = 0; i < CUBE_LINES.length; i++){

                v v1 = new v(x + (radius * CUBE_VERTICES[CUBE_LINES[i][0]][0]),
                             y + (radius * CUBE_VERTICES[CUBE_LINES[i][0]][1]),
                             z + (radius * CUBE_VERTICES[CUBE_LINES[i][0]][2]));

                v v2 = new v(x + (radius * CUBE_VERTICES[CUBE_LINES[i][1]][0]),
                             y + (radius * CUBE_VERTICES[CUBE_LINES[i][1]][1]),
                             z + (radius * CUBE_VERTICES[CUBE_LINES[i][1]][2]));

                System.out.println("1: x = " + v1.x + ", y = " + v1.y + ", z = " + v1.z);
                System.out.println("2: x = " + v2.x + ", y = " + v2.y + ", z = " + v2.z + "\n");

                v v1Project = project(v1.x, v1.y, v1.z);
                v v2Project = project(v2.x, v2.y, v2.z);

                System.out.println("1 Project: x = " + v1Project.x + ", y = " + v1Project.y);
                System.out.println("2 Project: x = " + v2Project.x + ", y = " + v2Project.y + "\n");

                g.setColor(Color.GREEN);
                g.drawLine((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y);
            }
        }
    }
}
