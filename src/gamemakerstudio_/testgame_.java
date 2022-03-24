package gamemakerstudio_;

import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import gamemakerstudio_.misc.GameEngine;
import gamemakerstudio_.misc.MathUtil;
import gamemakerstudio_.misc.audiostuff.xtaudio.XtAudio;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.graphicsstuff.BufferedImageLoader;
import gamemakerstudio_.misc.graphicsstuff.ImageProcessing;
import gamemakerstudio_.misc.graphicsstuff.assets_;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static gamemakerstudio_.entities.experimental.betterosc_.bytesToFloats;
import static gamemakerstudio_.entities.experimental.betterosc_.stereoMerge;
import static gamemakerstudio_.game_.*;
import static gamemakerstudio_.misc.audiostuff.xtaudio.XtAudio.BUFFER;

public class testgame_ extends GameEngine implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
    // dimension
    // public static int WIDTH = 720, HEIGHT = 1360/2;

    // public static int WIDTH = 720, HEIGHT = 480;
    public static int WIDTH = 1280, HEIGHT = 720;

    float PROJECTION_CENTER_X = WIDTH / 2f;
    float PROJECTION_CENTER_Y = HEIGHT / 2f;

    // texture asset
    public BufferedImageLoader loader = new BufferedImageLoader();
    public static BufferedImage spritesheet;

    // classes
    public ScreenCapture recorder = new ScreenCapture("mydesktop", "C:/Users/ACER/Desktop/record/");

    public testgame_(){
        // texture
        spritesheet = loader.loadImage("resources_/image_/gamespritesheet.png");
        assets_.init(spritesheet);

        // window and start
        new testwindow_(WIDTH, HEIGHT, this);
        start();

        // camera
        new camera(0, 0, ID.Camera, this);

        // for (int i = 0; i < 800; i++) dots.add(new Dot());

        // createDots();
        // createDotsV2();

        // input
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);

        // xt audio
        new XtAudio();
    }

    float a = 0f;
    @Override
    public void tick() {
        FPS = "FPS: " + throwFrames;
        currentRotation = "Rotation: " + rotation + ", a: " + a;

        // for (int i = 0; i < dots.size(); i++) dots.get(i).tick();

        // Increase the globe rotation
        a++;
        rotation = a * 0.03f;

        for (Dot dot : dots) {
            // dot.tick();

            float sineRotation = (float)Math.sin(rotation);
            float cosineRotation = (float)Math.cos(rotation);
            dot.project(sineRotation, cosineRotation);
        }

        for (Cube cube : cubes) cube.tick();

        // rotate stuff for new 3D
        tetra.rotate(false, 0, 1, 1);
        diamond.rotate(true, 0, 0, 1);

        // for 3D cube
        // FIXME: user rotate update, issue: mouse unresponsive
        // controls using ctrl
        // FIXME: add x axis rotation
        if (shift){
            int xDif = dispX - prevX;
            int yDif = dispY - prevY;

            tetra.rotate(true, 0, -yDif, -xDif);
            diamond.rotate(true, 0, -yDif, -xDif);
        }
        else if (ctrl){
            int xDif = dispX - prevX;

            tetra.rotate(true, -xDif, 0, 0);
            diamond.rotate(true, -xDif, 0, 0);
        }

        // for terrain
        // FIXME: user rotate update, issue: mouse unresponsive
        // controls using ctrl
        // FIXME: add x axis rotation
        if (shift){
            yDif += dispY - prevY;
        }
        if (ctrl) {
            xDif += dispX - prevX;
        }
        if (z) {
            zDif += dispX - prevX;
        }

        prevX = dispX;
        prevY = dispY;

        // scale
        if (scroll == -1)
            PointConverter.zoomIn();
        else if (scroll == 1)
            PointConverter.zoomOut();
        scroll = 0;
    }

    public void MainRender(Graphics g) {
        // main render
        /*g.setColor(Color.green);
        g.fillRect(0, 0, 100, 100);*/

        // bg
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // for (int i = 0; i < dots.size(); i++) dots.get(i).render(g);

        for (Dot dot : dots) dot.render(g);

        for (Cube cube : cubes) cube.render(g);

        // boogie beats
        // beatReact();
        // spectrum(g);

        draw(g);

        // poly.render(g);

        PointConverter.centerPoint = new Point(WIDTH / 2, HEIGHT / 2);
        // tetra.render(g, true);
        // diamond.render(g);

        g.drawString(FPS,
                WIDTH - getTextWidth(g, FPS) - 10, HEIGHT - 50);
        /*g.drawString(currentRotation,
                WIDTH - getTextWidth(g, currentRotation) - 10, HEIGHT - 75);*/
    }

    // field test here

    // random test codes
    void randomTestCodes() {
        float[] pSample2 = bytesToFloats(BUFFER, 2);
        float[] pSample1 = bytesToFloats(BUFFER, 1);

        System.out.println(stereoMerge(pSample1, pSample2).length);
    }

    // class for recording
    class ScreenCapture {
        public boolean isRecording = false;
        public String outputFilename, outputDir;
        public Dimension screenBounds;
        public IMediaWriter writer;
        public long startTime;

        public ScreenCapture(String name, String path) {
            outputFilename = name;
            outputDir = path;

            System.out.println("Xuggler initialized...");
        }

        public void startRecording() {
            if (!isRecording) {
                // xuggler shit, run this first and once
                screenBounds = new Dimension(WIDTH, HEIGHT);

                String out = outputDir + getName(outputFilename);
                writer = ToolFactory.makeWriter(out);
                writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4,
                        screenBounds.width, screenBounds.height);

                startTime = System.nanoTime();
                isRecording = true;
                System.out.println("Recording started...");
            }
        }

        public void stopRecording() {
            if (isRecording) {
                isRecording = false;
                writer.close(); // stop
                System.out.println("Recording stopped...");
            }
        }

        public void captureGraphics(BufferedImage image) {
            // xuggler shit, run this everytime
            if (isRecording && writer.isOpen()){
                BufferedImage bgrScreen = convertToType(image, BufferedImage.TYPE_3BYTE_BGR);
                writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
                // TODO: add video duration
                duration = tellTime((int)((System.nanoTime() - startTime) / 10000000L));
            }
        }

        public String getName(String filename) {
            Date now = new Date();
            String name = filename + "-" + now + ".mp4";
            name = name.replaceAll("[\\\\/:*?\"<>| ]", "-");
            return name;
        }

        public String duration;

        public String tellTime(int var) {
            int centi = var % 100, sec = (var / 100) % 60, min = ((var / 100) / 60) % 60;

            String tempCenti, tempSec, tempMin;

            if (sec < 10) tempSec = "0" + sec;
            else tempSec = String.valueOf(sec);

            if (min < 10) tempMin = "0" + min;
            else tempMin = String.valueOf(min);

            if (centi < 10) tempCenti = "0" + centi;
            else tempCenti = String.valueOf(centi);

            return tempMin + ":" + tempSec + ":" + tempCenti;
        }

        private BufferedImage convertToType(BufferedImage sourceImage, int targetType) {
            BufferedImage image;
            // if the source image is already the target type, return the source image
            if (sourceImage.getType() == targetType) {
                image = sourceImage;
            }
            // otherwise create a new image of the target type and draw the new image
            else {
                image = new BufferedImage(sourceImage.getWidth(),
                        sourceImage.getHeight(), targetType);
                image.getGraphics().drawImage(sourceImage, 0, 0, null);
            }
            return image;
        }
    }

    // deprecated triangle strip, might fix later, fixed: dec 4, 2021

    int scl = 20;
    int w = 1000; // default is 2000
    int h = 1000; // default is 1600
    int cols = w / scl, rows = h / scl;

    float flying = 0;

    float[][] terrain = new float[cols][rows];

    public void draw(Graphics g){
        float[] pSample2 = bytesToFloats(BUFFER, 2);
        float[] pSample1 = bytesToFloats(BUFFER, 1);

        flying -= 0.1;

        float yoff = flying;
        for (int y = 0; y < rows; y++) {
            float xoff = 0;
            for (int x = 0; x < cols; x++) {
                terrain[x][y] = (float)MathUtil.map(MathUtil.ImprovedNoise.noise(xoff, yoff), 0, 1, -100, 100);
                // terrain[x][y] = (float)MathUtil.map(MathUtil.ImprovedNoise.noise(pSample1[x], pSample2[y]), -1, 1, -100, 100);
                xoff += 0.2;
            }
            yoff += 0.2;
        }

        ArrayList<Polygon3D> triangles = new ArrayList<>();

        //PointConverter.centerPoint = new Point(WIDTH, 0);
        PointConverter.centerPoint = new Point(WIDTH / 2, HEIGHT / 2);

        Color col = smoothColor();

        for (int y = 0; y < rows-1; y++) {
            // beginShape(TRIANGLE_STRIP);
            for (int x = 0; x < cols; x++) {
                Point3D v1, v2, v3;
                v1 = new Point3D(x*scl, y*scl, terrain[x][y]);
                v2 = new Point3D(x*scl, (y+1)*scl, terrain[x][y+1]);

                if (x + 1 < cols) {
                    v3 = new Point3D((x + 1) * scl, y * scl, terrain[x + 1][y]);
                } else {
                    v3 = new Point3D(x * scl, y * scl, terrain[x][y]); // FIXME: end triangles are unclosed
                }

                // center test
                v1 = new Point3D(v1.x - w / 2, v1.y - h / 2, v1.z);
                v2 = new Point3D(v2.x - w / 2, v2.y - h / 2, v2.z);
                v3 = new Point3D(v3.x - w / 2, v3.y - h / 2, v3.z);

                triangles.add(new Polygon3D(col, v1, v2, v3));
            }
            // endShape();
        }
        Tetrahedron triangleStrip = new Tetrahedron(listToArray(triangles));

        // TODO: add transform codes
        triangleStrip.rotate(true, -xDif, -yDif, -zDif);

        triangleStrip.render(g, false);
        counter++;

        // info debug
        g.setFont(new Font("Arial", 0, 12));
        g.setColor(Color.WHITE);
        g.drawString("Position: " + xDif + ", " + yDif + ", " + zDif, 0, 12);
        g.drawString("Scale: " + PointConverter.scale, 0, 24);
        g.drawString("Duration: " + recorder.duration, 0, 36);
    }
    int counter = 0;
    // int xDif = 30, yDif, zDif = -84;
    // int xDif = 10, yDif, zDif = -84;
    int xDif = 45, yDif, zDif = -90;

    Polygon3D[] listToArray(ArrayList<Polygon3D> data) {
        Polygon3D[] bruh = new Polygon3D[data.size()];
        for (int i = 0; i < data.size(); i++) {
            bruh[i] = data.get(i);
        }
        return bruh;
    }

    public void beatReact() {
        float size = 2;
        float[] values = stereoMerge(bytesToFloats(BUFFER, 1), bytesToFloats(BUFFER, 2));
        float mean = 0;
        for (float i = 0.0f; i < 480.0f; i++) {
            mean += values[(int) i];
        }
        mean /= 480.0f;
        PointConverter.scale = size + size * 2 * Math.abs(mean);
    }

    int colorIndex = 0;
    int colorSize = 360;

    int offset = 3; // default is 2

    public void spectrum(Graphics gc) {
        int halfCanvasHeight = HEIGHT/2;
        float[] pSample1 = bytesToFloats(BUFFER, 1);

        // use this if problem above was fixed
        gc.setColor(Color.WHITE);

        int yLast1 = (int) (pSample1[0] * (float) halfCanvasHeight)
                + halfCanvasHeight;
        int samIncrement1 = 1;
        for (int a = samIncrement1, c = 0; /*c < canvasWidth && */a < pSample1.length; a += samIncrement1, c+=offset) {
            int yNow = (int) (pSample1[a] * (float) halfCanvasHeight)
                    + halfCanvasHeight;
            gc.drawLine(c, yLast1, c + 1, yNow);
            yLast1 = yNow;
        }

        colorIndex = (colorIndex == colorSize - 1) ? 0 : colorIndex + 1;
        gc.setColor(Color.getHSBColor((float) colorIndex / 360f, 1.0f, 1.0f));
//			System.out.println(Color.getHSBColor((float) colorIndex / 360f, 1.0f, 1.0f) + ", colorIndex: " + colorIndex);

        float[] pSample2 = bytesToFloats(BUFFER, 2);

        int yLast2 = (int) (pSample2[0] * (float) halfCanvasHeight)
                + halfCanvasHeight;
        int samIncrement2 = 1;
        for (int a = samIncrement2, c = 0; /*c < canvasWidth && */a < pSample2.length; a += samIncrement2, c+=offset) {
            int yNow = (int) (pSample2[a] * (float) halfCanvasHeight)
                    + halfCanvasHeight;
            gc.drawLine(c, yLast2, c + 1, yNow);
            yLast2 = yNow;
        }
    }

    public Color smoothColor() {
        colorIndex = (colorIndex == colorSize - 1) ? 0 : colorIndex + 1;
        return Color.getHSBColor((float) colorIndex / 360f, 1.0f, 1.0f);
    }

    Polygon3D poly = new Polygon3D(Color.GREEN,
            new Point3D(0, 100, 0),
            new Point3D(0, 0, 0),
            new Point3D(0, 50, 50)
    );

    // TODO: cube data, compile it as class

    float s = 100;

    Point3D p1 = new Point3D(s/2, -s/2, -s/2);
    Point3D p2 = new Point3D(s/2, s/2, -s/2);
    Point3D p3 = new Point3D(s/2, s/2, s/2);
    Point3D p4 = new Point3D(s/2, -s/2, s/2);
    Point3D p5 = new Point3D(-s/2, -s/2, -s/2);
    Point3D p6 = new Point3D(-s/2, s/2, -s/2);
    Point3D p7 = new Point3D(-s/2, s/2, s/2);
    Point3D p8 = new Point3D(-s/2, -s/2, s/2);

    // cube
    Tetrahedron tetra = new Tetrahedron(
            new Polygon3D(Color.red, p1, p2, p3, p4),
            new Polygon3D(Color.orange, p5, p6, p7, p8),
            new Polygon3D(Color.yellow, p1, p2, p6, p5),
            new Polygon3D(Color.green, p1, p5, p8, p4),
            new Polygon3D(Color.blue, p2, p6, p7, p3),
            new Polygon3D(Color.magenta, p4, p3, p7, p8)
    );

    // TODO: diamond data, compile it as class

    Tetrahedron diamond = createDiamond(randomColor(), 100, 0, 0, 0);

    public Tetrahedron createDiamond(Color color, float size, float centerX, float centerY, float centerZ){
        int edges = 10;
        float inFactor = 0.8f;
        Point3D bottom = new Point3D(centerX, centerY, centerZ - size/2f);
        Point3D[] outerPoints = new Point3D[edges];
        Point3D[] innerPoints = new Point3D[edges];

        for (int i = 0; i < edges; i++){
            float theta = (float)(2 * Math.PI / edges * i);
            float xPos = (float)(-Math.sin(theta) * size / 2);
            float yPos = (float)(Math.cos(theta) * size / 2);
            float zPos = size / 2;
            outerPoints[i] = new Point3D(centerX + xPos, centerY + yPos, centerZ + zPos);
            innerPoints[i] = new Point3D(centerX + xPos * inFactor, centerY + yPos * inFactor, centerZ + zPos / inFactor);
        }
        Polygon3D[] polygons = new Polygon3D[2 * edges + 1];
        for (int i = 0; i < edges; i++){
            polygons[i] = new Polygon3D(randomColor(), outerPoints[i], bottom, outerPoints[(i + 1) % edges]);
        }
        for (int i = 0; i < edges; i++){
            polygons[i + edges] = new Polygon3D(randomColor(), outerPoints[i], outerPoints[(i + 1) % edges], innerPoints[(i + 1) % edges], innerPoints[i]);
        }
        polygons[edges * 2] = new Polygon3D(randomColor(), innerPoints);

        return new Tetrahedron(polygons);
    }

    class Point3D {
        public float x, y, z;

        public Point3D(float x, float y, float z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class PointConverter {
        public static float scale = 1;
        public static float zoomFactor = 1.2f;
        public static Point centerPoint = new Point(WIDTH / 2, HEIGHT / 2);

        public static Point convertPoint(Point3D point3D){
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

        public static Point scale(float x3d, float y3d, float depth){
            float dist = (float)Math.sqrt(x3d * x3d + y3d * y3d);
            float theta = (float)(Math.atan2(y3d, x3d));
            float depth2 = 15 - depth;
            float localScale = Math.abs(1400/(depth2+1400));
            dist *= localScale;
            return new Point((int)(dist * Math.cos(theta)), (int)(dist * Math.sin(theta)));
        }

        public static void rotateAxisX(Point3D p, boolean CW, float degrees){
            float radius = (float) Math.sqrt(p.y * p.y + p.z * p.z);
            float theta = (float) Math.atan2(p.y, p.z);
            theta += 2 * Math.PI / 360 * degrees * (CW ? -1 : 1);
            p.y = (float)(radius * Math.sin(theta));
            p.z = (float)(radius * Math.cos((theta)));
        }

        public static void rotateAxisY(Point3D p, boolean CW, float degrees){
            float radius = (float) Math.sqrt(p.x * p.x + p.z * p.z);
            float theta = (float) Math.atan2(p.x, p.z);
            theta += 2 * Math.PI / 360 * degrees * (CW ? -1 : 1);
            p.x = (float)(radius * Math.sin(theta));
            p.z = (float)(radius * Math.cos((theta)));
        }

        public static void rotateAxisZ(Point3D p, boolean CW, float degrees){
            float radius = (float) Math.sqrt(p.y * p.y + p.x * p.x);
            float theta = (float) Math.atan2(p.y, p.x);
            theta += 2 * Math.PI / 360 * degrees * (CW ? -1 : 1);
            p.y = (float)(radius * Math.sin(theta));
            p.x = (float)(radius * Math.cos((theta)));
        }

        public static void zoomIn(){
            scale *= zoomFactor;
        }

        public static void zoomOut(){
            scale /= zoomFactor;
        }
    }

    class Polygon3D {
        Point3D[] points;
        Color color = Color.WHITE;

        public Polygon3D(Color col, Point3D... points){
            color = col;
            this.points = new Point3D[points.length];
            for (int i = 0; i < points.length; i++){
                Point3D p = points[i];
                this.points[i] = new Point3D(p.x, p.y, p.z);
            }
        }

        public void render(Graphics g, boolean fill){
            Polygon poly = new Polygon();
            for (int i = 0; i < points.length; i++){
                Point p = PointConverter.convertPoint(points[i]);
                poly.addPoint(p.x, p.y);
            }
            g.setColor(color);
            if (fill)
                g.fillPolygon(poly);
            else g.drawPolygon(poly);
            // TODO: draw some img here, hint: affine transform
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
                PointConverter.rotateAxisX(p, CW, xDegrees);
                PointConverter.rotateAxisY(p, CW, yDegrees);
                PointConverter.rotateAxisZ(p, CW, zDegrees);
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

    class Tetrahedron {
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
            List<Polygon3D> polyList = new ArrayList<>(Arrays.asList(p));

            // FIXME: Comparison method violates its general contract!
            // polyList.sort(((o1, o2) -> o2.getAverageX() - o1.getAverageX() < 0 ? 1 : -1));

            for (int i = 0; i < p.length; i++){
                p[i] = polyList.get(i);
            }
        }
    }

    // input
    boolean leftMouse = false, rightMouse = false, ctrl = false, shift = false, z = false;
    int prevX, prevY, scroll;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // FIXME: not working
        switch (e.getButton()){
            case MouseEvent.BUTTON1:
                leftMouse = true;
                break;
            case MouseEvent.BUTTON3:
                rightMouse = true;
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // FIXME: not working
        if (e.getButton() == MouseEvent.BUTTON3)
            mouseCursor = !mouseCursor;

        switch (e.getButton()){
            case MouseEvent.BUTTON1:
                leftMouse = false;
                break;
            case MouseEvent.BUTTON3:
                rightMouse = false;
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // codes for mouse debug
        int mx = e.getX();
        int my = e.getY();
        mouseX = mx - 15;
        mouseY = my - 15;
        dispX = mx;
        dispY = my;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int event = e.getWheelRotation();

        if (event == -1)
            scroll = -1;
        if (event == 1)
            scroll = 1;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_CONTROL)
            ctrl = true;
        if (key == KeyEvent.VK_SHIFT)
            shift = true;
        if (key == KeyEvent.VK_Z)
            z = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_CONTROL)
            ctrl = false;
        if (key == KeyEvent.VK_SHIFT)
            shift = false;
        if (key == KeyEvent.VK_Z)
            z = false;

        if (key == KeyEvent.VK_SPACE) {
            if (recorder.isRecording) recorder.stopRecording();
            else recorder.startRecording();
        }
    }

    // drop everything here

    public v project(float x, float y, float z){
        float scaleProjected = FIELD_OF_VIEW / (FIELD_OF_VIEW + z); // old bug, change + to * for weird effect
        float xProjected = (x * scaleProjected) + PROJECTION_CENTER_X;
        float yProjected = (y * scaleProjected) + PROJECTION_CENTER_Y;

        return new v(xProjected, yProjected, scaleProjected);
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

    // 3D stuff
    float PERSPECTIVE = WIDTH * 0.8f;
    ArrayList<Dot> dots = new ArrayList<Dot>();

    float rotation = 0;
    int DOTS_AMOUNT = 1000;
    float DOT_RADIUS = 16; // default is 4
    float GLOBE_RADIUS = WIDTH * 0.7f;
    float GLOBE_CENTER_Z = -GLOBE_RADIUS;

    ArrayList<Cube> cubes = new ArrayList<Cube>();
    float FIELD_OF_VIEW = WIDTH * 0.8f; // float PERSPECTIVE

    // cube
    int[][] CUBE_LINES = {{0, 1}, {1, 3}, {3, 2}, {2, 0}, {2, 6}, {3, 7}, {0, 4}, {1, 5}, {6, 7}, {6, 4}, {7, 5}, {4, 5}};
    int[][] CUBE_VERTICES = {{-1, -1, -1}, {1, -1, -1}, {-1, 1, -1}, {1, 1, -1}, {-1, -1, 1}, {1, -1, 1}, {-1, 1, 1}, {1, 1, 1}};

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

        /*float xTemp = (float)((Math.random() - 0.5) * (WIDTH * 0.5));
        float yTemp = (float)((Math.random() - 0.5) * (WIDTH * 0.5));
        float zTemp = (float)((Math.random() - 0.5) * WIDTH);*/

        // this some megumin type shit
        /*float xTemp = (float)((Math.random() - 0.5));
        float yTemp = (float)((Math.random() - 0.5));
        float zTemp = (float)((Math.random() - 0.5));*/

        /*float xTemp = (float)((Math.random() - 0.5) * (WIDTH / 256f));
        float yTemp = (float)((Math.random() - 0.5) * (WIDTH / 256f));
        float zTemp = (float)((Math.random() - 0.5) * (WIDTH / 128f));*/

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

            return new v(xProjected, yProjected, scaleProjected);
        }

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
                System.out.println("Right Face: x: " + Arrays.toString(rightFace.xpoints) + ", y: " + Arrays.toString(rightFace.ypoints) + ", npoints: " + rightFace.npoints);
            }

            // switch lock
            dump3DInfo = false;
        }

        public void render(Graphics g){
            if (z < -FIELD_OF_VIEW + radius)
                return;

            if (dump3DInfo) System.out.println("Cube: x = " + x + ", y = " + y + ", z = " + z + "\n");

            int[][] polygonXData = new int[CUBE_LINES.length][4];
            int[][] polygonYData = new int[CUBE_LINES.length][4];
            int[] polygonXData2 = new int[CUBE_LINES.length];
            int[] polygonYData2 = new int[CUBE_LINES.length];

            int[] every4thProjectionX = new int[4];
            int[] every4thProjectionY = new int[4];

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

                v v1Project = project(v1.x, v1.y, v1.z);
                v v2Project = project(v2.x, v2.y, v2.z);

                // add line2d
                // lines.add(new Line2D.Float((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y));

                g.setColor(colorLine);
                g.drawLine((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y);

                // optimize every 4th projection
                /*polygon = new Polygon();
                if (i < 4) {
                    polygonXData[i] = new int[]{(int)v1Project.x, (int)v1Project.y, (int)v2Project.x};
                    polygonYData[i] = new int[]{(int)v2Project.x, (int)v2Project.y, (int)v1Project.x};
                } else if (i == 4) {
                    for (int temp = 0; temp < 4; temp++){
                        polygon.xpoints = polygonXData[temp];
                        polygon.ypoints = polygonXData[temp];
                        polygon.npoints = 3;
                        g.fillPolygon(polygon);
                    }
                }*/

                // every 4th projection, laggy
                /*if (i < 4) {
                    lines.add(new Line2D.Float((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y));
                } else if (i == 4) {
                    polygon = createPolygon();
                }
                g.fillPolygon(polygon);*/

                // buggy
                /*polygon.xpoints = new int[]{(int)v1Project.x, (int)v1Project.y, (int)v1Project.z};
                polygon.ypoints = new int[]{(int)v2Project.x, (int)v2Project.y, (int)v2Project.z};
                polygon.npoints = 3;
                color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
                g.setColor(color);
                g.fillPolygon(polygon);*/

                // crappy algorithm
                /*boolean addX = true;
                for (int polygonXDatum : polygonXData) {
                    if (polygonXDatum == v1Project.x) {
                        addX = false;
                        break;
                    }
                }
                if (addX)
                    polygonXData[i] = (int)v1Project.x;

                addX = true;
                for (int polygonXDatum : polygonXData) {
                    if (polygonXDatum == v1Project.y) {
                        addX = false;
                        break;
                    }
                }
                if (addX)
                    polygonXData[i] = (int)v1Project.y;

                boolean addY = true;
                for (int polygonYDatum : polygonYData) {
                    if (polygonYDatum == v2Project.x) {
                        addY = false;
                        break;
                    }
                }
                if (addY)
                    polygonYData[i] = (int)v2Project.x;

                addY = true;
                for (int polygonYDatum : polygonYData) {
                    if (polygonYDatum == v2Project.y) {
                        addY = false;
                        break;
                    }
                }
                if (addY)
                    polygonYData[i] = (int)v2Project.y;*/

                // test
                /*polygonXData[i] = (int)v1Project.x;
                polygonYData[i] = (int)v1Project.y;
                polygonXData2[i] = (int)v2Project.x;
                polygonYData2[i] = (int)v2Project.y;*/

                // shit test
                /*if (i % 2 == 1){
                    polygon.xpoints = new int[]{polygonXData[i - 1], polygonXData2[i - 1], (int)v1Project.x, (int)v1Project.y};
                    polygon.ypoints = new int[]{polygonYData[i - 1], polygonYData2[i - 1], (int)v2Project.x, (int)v2Project.y};
                    polygon.npoints = polygon.xpoints.length;
                    g.setColor(color);
                    g.fillPolygon(polygon);
                }

                polygonXData[i] = (int)v1Project.x;
                polygonXData2[i] = (int)v1Project.y;
                polygonYData[i] = (int)v2Project.x;
                polygonYData2[i] = (int)v2Project.y;*/

                // fuck this shit
                /*polygon.addPoint((int)v1Project.x, (int)v1Project.y);
                polygon.addPoint((int)v2Project.x, (int)v2Project.y);*/

                // this was unoptimized, part 1
                // if (i < 4) lines.add(new Line2D.Float((int)v1Project.x, (int)v1Project.y, (int)v2Project.x, (int)v2Project.y));

            }

            // this was unoptimized, part 2
            // polygon = createPolygon();

            /*polygon.xpoints = polygonXData;
            polygon.ypoints = polygonYData;
            polygon.npoints = polygonXData.length;*/

            // polygon = createPolygon();
            // color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));

            // g.fillPolygon(testFace);
            // g.fillPolygon(polygon);
            // g.fillPolygon(polygon2);
        }

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

        private Polygon polygon = new Polygon();
        private Polygon polygon2 = new Polygon();

        // random boolean
        boolean[] randBool = new boolean[]{r.nextBoolean(), r.nextBoolean(), r.nextBoolean(), r.nextBoolean(), r.nextBoolean(), r.nextBoolean()};
    }

    boolean dump3DInfo = false;

    public void createDotsV2(){
        cubes.clear();
        // Create a new dot based on the amount needed
        for (int i = 0; i < 100; i++) {
            cubes.add(new Cube());
        }
    }

    class Dot {
        public float x, y, z, radius, xProjected, yProjected, scaleProjected;
        private float alpha = 1;
        private float life;
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
            g.setColor(Color.GREEN);

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

    // camera vars
    double tx = 0, ty = 0, sx = 1, sy = 1;
    public void cameraControls(double tx, double ty, double sx, double sy){
        this.tx = tx;
        this.sx = sx;
        this.ty = ty;
        this.sy = sy;
    }

    class camera extends gameobject_ implements KeyListener {
        testgame_ game;
        public camera(float x, float y, ID id, testgame_ game) {
            super(x, y, id);
            this.game = game;
            game.addKeyListener(this);
        }

        @Override
        public void tick() {}

        @Override
        public void render(Graphics g) {}

        @Override
        public Rectangle getBounds() {
            return new Rectangle((int)x, (int)y, width, height);
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        public int xTemp = 0, yTemp = 0;
        int pixels = 50;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            // move
            if (key == KeyEvent.VK_LEFT)
                xTemp += -pixels;
            if (key == KeyEvent.VK_RIGHT)
                xTemp += pixels;
            if (key == KeyEvent.VK_UP)
                yTemp += -pixels;
            if (key == KeyEvent.VK_DOWN)
                yTemp += pixels;
            // reset
            if (key == KeyEvent.VK_ENTER) {
                xTemp = 0;
                yTemp = 0;
                PROJECTION_CENTER_X = WIDTH/2f;
                PROJECTION_CENTER_Y = HEIGHT/2f;

                // some random test codes
                randomTestCodes();
            }
            // apply
            game.cameraControls(xTemp, yTemp, 1, 1);

            // exclusive
            if (key == KeyEvent.VK_SPACE) {
                // createDotsV2();
            }
            if (key == KeyEvent.VK_W)
                PROJECTION_CENTER_Y += pixels;
            if (key == KeyEvent.VK_S)
                PROJECTION_CENTER_Y += -pixels;
            if (key == KeyEvent.VK_D)
                PROJECTION_CENTER_X += -pixels;
            if (key == KeyEvent.VK_A)
                PROJECTION_CENTER_X += pixels;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            // dump info
            if (key == KeyEvent.VK_ENTER){
                dump3DInfo = true;
            }
        }
    }

    String FPS, currentRotation;

    // the actual image to be rendered
    public static BufferedImage image;

    public static void main(String[] args){
        new testgame_();
    }

    @Override
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        // init image
        image = ImageProcessing.processHandlerInit(WIDTH, HEIGHT);

        Graphics g = image.createGraphics();

        // camera codes, i like this part
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform at = g2d.getTransform();

        // just white canvas, to clean excess graphics
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // pre camera codes
        at.scale(sx, sy);
        at.translate(tx, ty);

        // post camera codes
        g2d.setTransform(at);

        MainRender(g);

        // mouse render
        if (mouseCursor){
            g.drawImage(assets_.targetimage, (int)mouseX, (int)mouseY, null);
            g.setColor(Color.CYAN);

            // trash codes
            if(dispX >= WIDTH - 60){
                if (dispY >= HEIGHT - 40) {
                    // bottom right, dispX = 620, dispY = 680
                    g.drawString("x = " + dispX, (int)mouseX - 35, (int)mouseY - 15);
                    g.drawString("y = " + dispY, (int)mouseX - 35, (int)mouseY - 0);
                } else {
                    // upper right, dispX = 620, dispY = 40
                    g.drawString("x = " + dispX, (int)mouseX - 35, (int)mouseY + 35);
                    g.drawString("y = " + dispY, (int)mouseX - 35, (int)mouseY + 50);
                }
            }
            else if (dispY >= HEIGHT - 40){
                // lower left, dispX = 50, dispY = 680
                g.drawString("x = " + dispX, (int)mouseX + 35, (int)mouseY - 15);
                g.drawString("y = " + dispY, (int)mouseX + 35, (int)mouseY - 0);
            } else {
                // neutral
                g.drawString("x = " + dispX, (int)mouseX + 35, (int)mouseY + 35);
                g.drawString("y = " + dispY, (int)mouseX + 35, (int)mouseY + 50);
            }
        }

        // get every graphics to be rendered at outer layer
        g = bs.getDrawGraphics();

        // image processing codes
        image = ImageProcessing.postProcessing(image);

        // draw everything
        g.drawImage(image, 0, 0, null);
        bs.show();

        // xuggler shit
        if (recorder.isRecording) {
            recorder.captureGraphics(image);
        }

        // dispose properly
        g.dispose();

    }
    // mouse thing
    int dispX, dispY, mouseX, mouseY;
    // options
    public static boolean gridLines = false;
    public static boolean mouseCursor = false;
    // useful utils
    static Random r = new Random();
    public static Color randomColor(){
        return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }
}
class testwindow_ extends WindowAdapter {
    public static JFrame frame = new JFrame();
    public testwindow_(int width, int height, testgame_ game) {
        frame.addWindowListener(this);

        frame.setPreferredSize(new Dimension(width + 16, height + 39));
        frame.setMaximumSize(new Dimension(width + 16, height + 39));
        frame.setMinimumSize(new Dimension(width + 16, height + 39));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
    }
}
