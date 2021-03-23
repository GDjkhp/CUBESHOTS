package gamemakerstudio_.entities;

import gamemakerstudio_.misc.entitystuff.FACE;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.graphicsstuff.assets_;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class spike_ extends gameobject_ {
    FACE defaultFace;
    Polygon triangle, triangle2, triangle3;
    double radians; // TODO: move this to gameobject_
    public spike_(float x, float y, ID id, int velX, int velY, FACE face) {
        super(x, y, id);
        color = Color.MAGENTA;
        width = 50;
        height = 50;
        this.velX = velX;
        this.velY = velY;
        // detect face
        defaultFace = face;
        triangle = triangleAlgorithm(face, (int)x, (int)y, width, height);
        radians = Math.toRadians(radiansRotate(face));
    }
    
    public Polygon triangleAlgorithm(FACE face, int x, int y, int w, int h){
        // crappy codes
        Polygon triangle = new Polygon();
        // face 1
        if (face == FACE.NORTH) {
            triangle.xpoints = new int[]{x, x + w/2, x + w};
            triangle.ypoints = new int[]{y + h, y, y + h};
            triangle.npoints = 3;
        }
        // face 2
        if (face == FACE.EAST) {
            triangle.xpoints = new int[]{x, x + w, x};
            triangle.ypoints = new int[]{y, y + h/2, y + h};
            triangle.npoints = 3;
        }
        // face 3
        if (face == FACE.SOUTH) {
            triangle.xpoints = new int[]{x, x + w/2, x + w};
            triangle.ypoints = new int[]{y, y + h, y};
            triangle.npoints = 3;
        }
        // face 4
        if (face == FACE.WEST) {
            triangle.xpoints = new int[]{x + w, x, x + w};
            triangle.ypoints = new int[]{y, y + h/2, y + h};
            triangle.npoints = 3;
        }
        return triangle;
    }

    public double radiansRotate(FACE face){
        switch (face){
            case EAST:
                return 90;
            case SOUTH:
                return 180;
            case WEST:
                return 270;
            default:
                return 0;
        }
    }

    @Override
    public void tick() {
        // todo: add dvd and/or delete this if offscreen
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.drawPolygon(triangle);

        // use g2d
        Graphics2D g2d = (Graphics2D) g;
        // transform
        AffineTransform old = g2d.getTransform();
        // pre rotate
        g2d.rotate(radians, x + (width/2), y + (height/2));
        g.drawImage(assets_.spike, (int)x, (int)y, null);
        // post rotate
        g2d.setTransform(old);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}
