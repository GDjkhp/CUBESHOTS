package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;
import java.awt.image.BufferedImage;

public class mandelbrot_ extends gameobject_ {
    int canvasWidth = game_.WIDTH, canvasHeight = game_.HEIGHT;

    // Color pixels[] = new Color[canvasWidth * canvasHeight];
    float pixels[] = new float[canvasWidth * canvasHeight];

    public BufferedImage image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);

    public mandelbrot_(float x, float y, ID id) {
        super(x, y, id);
        initSet();
        // picture picture picture
        Graphics g = image.createGraphics();
        for (int j = 0; j < canvasHeight; j++) {
            for (int i = 0; i < canvasWidth; i++) {
                // draw test
                g.setColor(Color.getHSBColor(pixels[i+j*canvasWidth], 1f, 1f));
                g.fillRect(i, j, 1, 1);
            }
        }
    }

    public void initSet(){
        // Establish a range of values on the complex plane
        // A different range will allow us to "zoom" in or out on the fractal

        // It all starts with the canvasWidth, try higher or lower values
        float w = 5;
        float h = (w * canvasHeight) / canvasWidth;

        // Start at negative half the canvasWidth and canvasHeight
        float xmin = -w/2;
        float ymin = -h/2;

        // Make sure we can write to the pixels[] array.
        // Only need to do this once since we don't do any other drawing.
        // loadPixels();

        // Maximum number of iterations for each point on the complex plane
        int maxiterations = 100;

        // x goes from xmin to xmax
        float xmax = xmin + w;
        // y goes from ymin to ymax
        float ymax = ymin + h;

        // Calculate amount we increment x,y for each pixel
        float dx = (xmax - xmin) / (canvasWidth);
        float dy = (ymax - ymin) / (canvasHeight);

        // Start y
        float y = ymin;
        for (int j = 0; j < canvasHeight; j++) {
            // Start x
            float x = xmin;
            for (int i = 0; i < canvasWidth; i++) {

                // Now we test, as we iterate z = z^2 + cm does z tend towards infinity?
                float a = x;
                float b = y;
                int n = 0;
                while (n < maxiterations) {
                    float aa = a * a;
                    float bb = b * b;
                    float twoab = (float)(2.0 * a * b);
                    a = aa - bb + x;
                    b = twoab + y;
                    // Infinty in our finite world is simple, let's just consider it 16
                    if (a*a + b*b > 16.0) {
                        break;  // Bail
                    }
                    n++;
                }

                // We color each pixel based on how long it takes to get to infinity
                // If we never got there, let's pick the color black
                if (n == maxiterations) {
                    // pixels[i+j*canvasWidth] = Color.getHSBColor(0, 0, 0);
                    pixels[i+j*canvasWidth] = 0f;
                } else {
                    // Gosh, we could make fancy colors here if we wanted
                    // pixels[i+j*canvasWidth] = Color.getHSBColor(((float)n / maxiterations), 0, 0);
                    pixels[i+j*canvasWidth] = (float)n / maxiterations;
                }
                x += dx;
            }
            y += dy;
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        // TODO: draw pixels
        /*updatePixels();
        println(frameRate);*/

        g.drawImage(image, 0, 0, null);

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
