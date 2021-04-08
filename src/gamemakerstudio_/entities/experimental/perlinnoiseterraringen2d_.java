package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;
import java.util.Random;

import static gamemakerstudio_.misc.MathUtil.ImprovedNoise.noise;

public class perlinnoiseterraringen2d_ extends gameobject_ {
    long seed = System.currentTimeMillis();
    Random rand = new Random(seed);

    private static final int DEFAULT_HEIGHT = game_.HEIGHT/16;

    private static final int DEFAULT_WIDTH = game_.WIDTH/16;

    double z = rand.nextDouble();

    // TODO: maek this 3d
    public perlinnoiseterraringen2d_(float x, float y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < DEFAULT_WIDTH; ++i)
        { // y
            for (int j = 0; j < DEFAULT_HEIGHT; ++j)
            { // x
                double x = (double)j / ((double)DEFAULT_WIDTH);
                double y = (double)i / ((double)DEFAULT_HEIGHT);
                double n = noise(10 * x, 10 * y, z);

                // Watter (or a Lakes)
                if (n < 0.35)
                {
                    g.setColor(Color.CYAN);
                }
                // Floors (or Planes)
                else if (n >= 0.35 && n < 0.7)
                {
                    g.setColor(Color.GREEN);
                }
                // Walls (or Mountains)
                else if (n >= 0.7 && n < 0.85)
                {
                    g.setColor(Color.GRAY);
                }
                // Ice (or Snow)
                else
                {
                    g.setColor(Color.white);
                }
                g.fillRect(i * 10, j * 10, 10, 10);
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
