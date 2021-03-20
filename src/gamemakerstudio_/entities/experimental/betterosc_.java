package gamemakerstudio_.entities.experimental;

import com.xuggle.xuggler.ICodec;
import gamemakerstudio_.game_;
import gamemakerstudio_.misc.*;
import gamemakerstudio_.misc.audiostuff.xtaudio.XtAudio;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import static gamemakerstudio_.game_.*;
import static gamemakerstudio_.misc.audiostuff.xtaudio.XtAudio.BUFFER;

public class betterosc_ extends gameobject_ implements KeyListener {
    // TODO: better code, wip
    static float[] pLeftChannel = new float[2048];
    static float[] pRightChannel = new float[2048];

    private static final int DEFAULT_FPS = 70;
    private static final int DEFAULT_SAMPLE_SIZE = 2048;

    //Color oscilloscopeColor = Color.red;
    int colorIndex = 0;
    int colorSize = 360;

    int	halfCanvasHeight = HEIGHT/2;
    int canvasWidth = 1360/2;

    boolean stereo = true;

    game_ game;

    Random r = new Random();
    public betterosc_(float x, float y, ID id, game_ game) {
        super(x, y, id);
        this.game = game;
        game.addKeyListener(this);
    }

    @Override
    public void tick() {
        // TODO: WTF IS THIS SHIT?!

    }
    // new visualizer from active, flow, check this
    private ArrayList<flowparticle_> flowParticles = new ArrayList<>();

    @Override
    public void render(Graphics gc){
        float[] values = stereoMerge(bytesToFloats(BUFFER, 1), bytesToFloats(BUFFER, 2)); // 0
        float[] bassValues = stereoMerge(bytesToFloats(BUFFER, 1), bytesToFloats(BUFFER, 2)); // 20

        float mean = 0;
        float bassMean = 0;
        for (float i = 0.0f; i < 480.0f; i++) {
            mean += values[(int) i];
            bassMean += bassValues[(int) i];
        }
        mean /= 480.0f;
        bassMean /= 480.0f;

        Random random = new Random();

        while (flowParticles.size() < 400) {
            float randomiser = random.nextFloat();
            float randomiser2 = random.nextFloat();
            float multiplier = (random.nextFloat() + 1.0f) * 0.5f;
            flowParticles.add(new flowparticle_(-20.0f * (1 - multiplier) * 2, HEIGHT * randomiser, ID.Particle, 20.0f * (1 - multiplier), 20.0f * multiplier, 50.0f * multiplier, 2 * (float) Math.PI * randomiser2));
        }

        for (flowparticle_ flowParticle : flowParticles) {
            if (flowParticle.getPosX() > WIDTH + flowParticle.getRadius() * 2) {
                flowParticle.setX(0);
            }
            if (flowParticle.getPosX() < 0 - flowParticle.getRadius() * 2) {
                flowParticle.setX(WIDTH);
            }
            flowParticle.render(gc, mean, bassMean);
            if (fastForward)
                flowParticle.incrementX(20.0f * (random.nextFloat() + 1.0f) * 0.5f);
        }

        /*System.out.println("\nBYTES (" + BYTES.length + "): " + Arrays.toString(BYTES));
        System.out.println("bytesToFloats (" + bytesToFloats(BYTES).length + "): " + Arrays.toString(bytesToFloats(BYTES)));*/

        if (stereo) {
            float[] pSample1 = bytesToFloats(BUFFER, 1);

            // use this if problem above was fixed
            gc.setColor(Color.WHITE);

            int yLast1 = (int) (pSample1[0] * (float) halfCanvasHeight)
                    + halfCanvasHeight;
            int samIncrement1 = 1;
            for (int a = samIncrement1, c = 0; c < canvasWidth && a < pSample1.length; a += samIncrement1, c+=2) {
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
            for (int a = samIncrement2, c = 0; c < canvasWidth && a < pSample2.length; a += samIncrement2, c+=2) {
                int yNow = (int) (pSample2[a] * (float) halfCanvasHeight)
                        + halfCanvasHeight;
                gc.drawLine(c, yLast2, c + 1, yNow);
                yLast2 = yNow;
            }

            // channel 1
            gc.setColor(Color.WHITE);

            int yLast1R = (int) (pSample1[0] * (float) halfCanvasHeight)
                    + halfCanvasHeight;
            int samIncrement1R = 1;
            int lastC1 = 0;
            int lastA1 = 0;
            for (int a = samIncrement1R, c = canvasWidth*2; c > canvasWidth && a < pSample1.length; a += samIncrement1R, c-=2) {
                int yNow = (int) (pSample1[a] * (float) halfCanvasHeight)
                        + halfCanvasHeight;
                gc.drawLine(c, yLast1R, c - 1, yNow);
                yLast1R = yNow;
                lastC1 = c;
                lastA1 = a;
            }
            // join
            int yNow1 = (int) (pSample2[lastA1 + 1] * (float) halfCanvasHeight)
                    + halfCanvasHeight;
            gc.drawLine(lastC1-2, yLast1, (lastC1-2) - 1, yNow1);

            // channel 2
            colorIndex = (colorIndex == colorSize - 1) ? 0 : colorIndex + 1;
            gc.setColor(Color.getHSBColor((float) colorIndex / 360f, 1.0f, 1.0f));

            int yLast2R = (int) (pSample2[0] * (float) halfCanvasHeight)
                    + halfCanvasHeight;
            int samIncrement2R = 1;
            int lastC2 = 0;
            int lastA2 = 0;
            for (int a = samIncrement2R, c = canvasWidth*2; c > canvasWidth && a < pSample2.length; a += samIncrement2R, c-=2) {
                int yNow = (int) (pSample2[a] * (float) halfCanvasHeight)
                        + halfCanvasHeight;
                gc.drawLine(c, yLast2R, c - 1, yNow);
                yLast2R = yNow;
                lastC2 = c;
                lastA2 = a;
            }
            // join
            int yNow2 = (int) (pSample2[lastA2 + 1] * (float) halfCanvasHeight)
                    + halfCanvasHeight;
            gc.drawLine(lastC2-2, yLast2, (lastC2-2) - 1, yNow2);

        } else {
            float[] pSample1 = stereoMerge(bytesToFloats(BUFFER, 1), bytesToFloats(BUFFER, 2));
            /*colorIndex = (colorIndex == colorSize - 1) ? 0 : colorIndex + 1;
            gc.setColor(Color.getHSBColor((float) colorIndex / 360f, 1.0f, 1.0f));
            int yLast1 = (int) (pSample1[0] * (float) halfCanvasHeight)
                    + halfCanvasHeight;
            int samIncrement1 = 1;
            for (int a = samIncrement1, c = 0; c < canvasWidth && a < pSample1.length; a += samIncrement1, c+=2) {
                int yNow = (int) (pSample1[a] * (float) halfCanvasHeight)
                        + halfCanvasHeight;
                gc.drawLine(c, yLast1, c + 1, yNow);
                yLast1 = yNow;
            }*/

            // the coding train codes
            colorIndex = (colorIndex == colorSize - 1) ? 0 : colorIndex + 1;
            gc.setColor(Color.getHSBColor((float) colorIndex / 360f, 1.0f, 1.0f));
            for (int i = 0, offset = 0; i < pSample1.length; i++, offset += 1){
                float amp = Math.abs(pSample1[i]);
                double yLine = MathUtil.map(amp, 0f, 1f, (float) HEIGHT, 0f);
                gc.drawLine(i + offset, HEIGHT, i + offset, (int)yLine);
            }
//            System.out.println(Arrays.toString(pSample1));
            // TODO: jtransforms
        }

        // codes for image

        int radius = 256;
        float value = bassMean;

        Image imageReact = assets_.scaleImage(assets_.coolio, (int)(radius + radius * 2 * Math.abs(value)),
                (int)(radius + radius * 2 * Math.abs(value)));
        /*Image imageReact = assets_.scaleImage(imageReactMain, (int)(radius + radius * 2 * Math.abs(value)),
                (int)(radius + radius * 2 * Math.abs(value)));*/
        // gc.fillRect(0, 0, (int)(radius + radius * 2 * Math.abs(value)),
        // (int)(radius + radius * 2 * Math.abs(value)));

        int imageWidth = imageReact.getWidth(null);
        int imageHeight = imageReact.getHeight(null);

        /*gc.drawImage(imageReact, (WIDTH - WIDTH/2) - (imageWidth - imageWidth/2),
                (HEIGHT - HEIGHT/2) - (imageHeight - imageHeight/2), null);*/
    }

    /*BufferedImage imageReactMain;

    {
        try {
            imageReactMain = ImageIO.read(new File("C:\\Users\\ACER\\Downloads\\" +
                    "test for channel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public Rectangle getBounds() {
        return null;
    }

    public static float[] bytesToFloats(byte[] bytes, int channel){
        float[] floats = new float[bytes.length / 2];
        for (int i = 0; i < bytes.length; i += 2){
            floats[i/2] = (float)(bytes[i] | (bytes[i+1] << 8)) / 32767.0f;
        }

        int limit = 480;
        float[] betterFloats = new float[floats.length / 2];
        float[] clampToLimit = new float[limit];
        if (channel == 1){
            for (int i = 0, j = 0; i < floats.length; i+=2, j++){
                betterFloats[j] = floats[i];
            }
        } else if (channel == 2){
            for (int i = 1, j = 0; i < floats.length; i+=2, j++){
                betterFloats[j] = floats[i];
            }
        } else if (channel == 0)
            return floats;
        for (int i = 0; i < limit; i++){
            clampToLimit[i] = betterFloats[i];
        }

        // find two zero
        /*for (int i = 0; i < betterFloats.length; i++){
            if (i != 0 && betterFloats[i - 1] != 0 && betterFloats[i] == 0 && betterFloats[i + 1] == 0){
                System.out.println("index " + i + " afterwards fucks up the array");
            }
        }*/

        return clampToLimit; // TODO: must return channel
    }

    static float rangeFloat(byte bytes){
        return (float)bytes / 128;
    }

    public static float[] stereoMerge(float[] pLeft, float[] pRight) {
        for (int a = 0; a < pLeft.length; a++)
            pLeft[a] = (pLeft[a] + pRight[a]) / 2.0f;

        return pLeft;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    boolean fastForward = false;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT)
            fastForward = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        // he he boi visualizer codes
        if (key == KeyEvent.VK_F10) {
            if (stereo)
                stereo = false;
            else stereo = true;
        }
        // record switch
        if (recordSession){
            if (key == KeyEvent.VK_SPACE){
                if (game_.recordVideo) {
                    game_.recordVideo = false;
                    game_.writer.close(); // stop
                    System.out.println("Recording stopped...");
                }
                else {
                    // xuggler shit, run this first and once
                    Dimension screenBounds = new Dimension(WIDTH, HEIGHT);
                    game_.writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4,
                            screenBounds.width/2, screenBounds.height/2);
                    game_.startTime = System.nanoTime();
                    game_.recordVideo = true;
                    System.out.println("Recording started...");
                }

            }
            // play test music
            if (key == KeyEvent.VK_ENTER)
                audioplayer_.getMusic("record").play();
        }

        if (key == KeyEvent.VK_RIGHT)
            fastForward = false;
    }
}
