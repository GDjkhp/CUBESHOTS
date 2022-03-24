package gamemakerstudio_.misc;

import gamemakerstudio_.testgame_;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static gamemakerstudio_.game_.HEIGHT;
import static gamemakerstudio_.game_.WIDTH;

public class Math3D {
    public static class Point3D {
        public float x, y, z;

        public Point3D(float x, float y, float z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static class Polygon3D {
        Point3D[] points;
        Color color = Color.WHITE;
        PointConverter pc;
        float scale = 1;

        public Polygon3D(Color col, Point3D... points){
            color = col;
            this.points = new Point3D[points.length];
            for (int i = 0; i < points.length; i++){
                Point3D p = points[i];
                this.points[i] = new Point3D(p.x, p.y, p.z);
            }
            pc = new PointConverter(scale, new Point(WIDTH / 2, HEIGHT / 2));
        }

        public void render(Graphics g, boolean fill){
            Polygon poly = new Polygon();
            for (int i = 0; i < points.length; i++){
                Point p = pc.convertPoint(points[i]);
                poly.addPoint(p.x, p.y);
            }
            g.setColor(color);
            if (fill)
                g.fillPolygon(poly);
            else g.drawPolygon(poly);
            // TODO: draw some img here, hint: affine transform/warp
            /*g.drawImage(assets_.juni,
                    poly.xpoints[0], poly.ypoints[0], poly.xpoints[2], poly.ypoints[2],
                    0, 0, 32, 32, null);*/

            /*// more test
            Graphics2D g2d = (Graphics2D)g;
            AffineTransform at = g2d.getTransform();

            // image mapping
            double[] imgSrc = {0, 0, 0, 32, 32, 32, 32, 0};
            double[] destSrc = {poly.xpoints[0], poly.ypoints[0],
                    poly.xpoints[1], poly.ypoints[1],
                    poly.xpoints[2], poly.ypoints[2],
                    poly.xpoints[3], poly.ypoints[3]};

            // transform
            at.deltaTransform(PointConverter.convertPoint(points[0]), PointConverter.convertPoint(points[2]));
            g.drawImage(assets_.juni, poly.xpoints[0], poly.ypoints[0], null);
            at.deltaTransform(imgSrc, 0, destSrc, 0, 4);
            g2d.setTransform(at);*/
        }

        public void rotate(boolean CW, float xDegrees, float yDegrees, float zDegrees){
            for (Point3D p : points){
                pc.rotateAxisX(p, CW, xDegrees);
                pc.rotateAxisY(p, CW, yDegrees);
                pc.rotateAxisZ(p, CW, zDegrees);
            }
        }

        public float getAverageX(){
            float sum = 0;
            for(Point3D p : points){
                sum += p.x;
            }
            return sum / points.length;
        }

        public void setColor(Color col){
            color = col;
        }
    }

    public static class Tetrahedron {
        Polygon3D[] poly;

        public Tetrahedron(Polygon3D... polygons){
            poly = polygons;
            sortPolygons(poly);
        }

        public void render(Graphics g, boolean fill){
            for (Polygon3D polygon : poly)
                polygon.render(g, fill);
        }

        public void rotate(boolean CW, float xDegrees, float yDegrees, float zDegrees){
            for(Polygon3D p : poly)
                p.rotate(CW, xDegrees, yDegrees, zDegrees);
            sortPolygons(poly);
        }

        public void sortPolygons(Polygon3D[] p){
            ArrayList<Polygon3D> polyList = new ArrayList<>(Arrays.asList(p));

            // FIXME: Comparison method violates its general contract!
            // polyList.sort(((o1, o2) -> o2.getAverageX() - o1.getAverageX() < 0 ? 1 : -1));

            for (int i = 0; i < p.length; i++){
                p[i] = polyList.get(i);
            }
        }
    }

    public static class PointConverter {
        public float scale = 1;
        public float zoomFactor = 1.2f;
        public Point centerPoint = new Point();

        public PointConverter(float scale, Point centerPoint) {
            this.scale = scale;
            this.centerPoint = centerPoint;
        }

        public Point convertPoint(Point3D point3D){
            float x3d = point3D.y * scale;
            float y3d = point3D.z * scale;
            float depth = point3D.x * scale;
            Point newVal = scale(x3d, y3d, depth);

            // vanishing point, where (0, 0) was mapped, pass this as params
            // (WIDTH / 2 + x, HEIGHT / 2 - y) == CENTER
            int x2d = centerPoint.x + newVal.x;
            int y2d = centerPoint.y - newVal.y;

            return new Point(x2d, y2d);
        }

        public Point scale(float x3d, float y3d, float depth){
            float dist = (float)Math.sqrt(x3d * x3d + y3d * y3d);
            float theta = (float)(Math.atan2(y3d, x3d));
            float depth2 = 15 - depth;
            float localScale = Math.abs(1400/(depth2+1400));
            dist *= localScale;
            return new Point((int)(dist * Math.cos(theta)), (int)(dist * Math.sin(theta)));
        }

        public void rotateAxisX(Point3D p, boolean CW, float degrees){
            float radius = (float) Math.sqrt(p.y * p.y + p.z * p.z);
            float theta = (float) Math.atan2(p.y, p.z);
            theta += 2 * Math.PI / 360 * degrees * (CW ? -1 : 1);
            p.y = (float)(radius * Math.sin(theta));
            p.z = (float)(radius * Math.cos((theta)));
        }

        public void rotateAxisY(Point3D p, boolean CW, float degrees){
            float radius = (float) Math.sqrt(p.x * p.x + p.z * p.z);
            float theta = (float) Math.atan2(p.x, p.z);
            theta += 2 * Math.PI / 360 * degrees * (CW ? -1 : 1);
            p.x = (float)(radius * Math.sin(theta));
            p.z = (float)(radius * Math.cos((theta)));
        }

        public void rotateAxisZ(Point3D p, boolean CW, float degrees){
            float radius = (float) Math.sqrt(p.y * p.y + p.x * p.x);
            float theta = (float) Math.atan2(p.y, p.x);
            theta += 2 * Math.PI / 360 * degrees * (CW ? -1 : 1);
            p.y = (float)(radius * Math.sin(theta));
            p.x = (float)(radius * Math.cos((theta)));
        }

        public void zoomIn(){
            scale *= zoomFactor;
        }

        public void zoomOut(){
            scale /= zoomFactor;
        }
    }

    public static Polygon3D[] listToArray(ArrayList<Polygon3D> data) {
        Polygon3D[] bruh = new Polygon3D[data.size()];
        for (int i = 0; i < data.size(); i++) {
            bruh[i] = data.get(i);
        }
        return bruh;
    }
}
