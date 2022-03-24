package gamemakerstudio_.entities.particle;

import gamemakerstudio_.misc.Math3D;
import gamemakerstudio_.misc.MathUtil;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;
import java.util.ArrayList;

public class terrain_ extends gameobject_ {
    int scl = 20;
    int w = 1000; // default is 2000
    int h = 1000; // default is 1600
    int cols = w / scl, rows = h / scl;

    float flying = 0;

    float[][] terrain = new float[cols][rows];

    int colorIndex = 0;
    int colorSize = 360;

    int xDif = 45, yDif, zDif = -90;

    public terrain_(float x, float y, ID id) {
        super(x, y, id);
    }

    public Color smoothColor() {
        colorIndex = (colorIndex == colorSize - 1) ? 0 : colorIndex + 1;
        return Color.getHSBColor((float) colorIndex / 360f, 1.0f, 1.0f);
    }

    @Override
    public void tick() {

    }

    public void render(Graphics g) {
        flying -= 0.1;

        float yoff = flying;
        for (int y = 0; y < rows; y++) {
            float xoff = 0;
            for (int x = 0; x < cols; x++) {
                terrain[x][y] = (float) MathUtil.map(MathUtil.ImprovedNoise.noise(xoff, yoff), 0, 1, -100, 100);
                // terrain[x][y] = (float)MathUtil.map(MathUtil.ImprovedNoise.noise(pSample1[x], pSample2[y]), -1, 1, -100, 100);
                xoff += 0.2;
            }
            yoff += 0.2;
        }

        ArrayList<Math3D.Polygon3D> triangles = new ArrayList<>();

        // PointConverter.centerPoint = new Point(WIDTH, 0);
        // Math3D.PointConverter.centerPoint = new Point(WIDTH / 2, HEIGHT / 2);

        Color col = smoothColor();

        for (int y = 0; y < rows-1; y++) {
            // beginShape(TRIANGLE_STRIP);
            for (int x = 0; x < cols; x++) {
                Math3D.Point3D v1, v2, v3;
                v1 = new Math3D.Point3D(x*scl, y*scl, terrain[x][y]);
                v2 = new Math3D.Point3D(x*scl, (y+1)*scl, terrain[x][y+1]);

                if (x + 1 < cols) {
                    v3 = new Math3D.Point3D((x + 1) * scl, y * scl, terrain[x + 1][y]);
                } else {
                    v3 = new Math3D.Point3D(x * scl, y * scl, terrain[x][y]); // FIXME: end triangles are unclosed
                }

                // center test
                v1 = new Math3D.Point3D(v1.x - w / 2, v1.y - h / 2, v1.z);
                v2 = new Math3D.Point3D(v2.x - w / 2, v2.y - h / 2, v2.z);
                v3 = new Math3D.Point3D(v3.x - w / 2, v3.y - h / 2, v3.z);

                triangles.add(new Math3D.Polygon3D(col, v1, v2, v3));
            }
            // endShape();
        }
        Math3D.Tetrahedron triangleStrip = new Math3D.Tetrahedron(Math3D.listToArray(triangles));

        // TODO: add transform codes
        triangleStrip.rotate(true, -xDif, -yDif, -zDif);

        triangleStrip.render(g, false);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
