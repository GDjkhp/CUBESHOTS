package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.MathUtil;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;

public class tvstaticnoise_ extends gameobject_ {
    public tvstaticnoise_(float x, float y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        for (int yTemp = 0; yTemp < game_.HEIGHT; yTemp++){
            for (int xTemp = 0; xTemp < game_.WIDTH; xTemp++){
                int col = (int) (Math.round(Math.random()*100)%50);
                if (Math.random() > 0.5){
                    col = 255 - col;
                }
                g.setColor(new Color(col, col, col));
                g.fillRect(xTemp, yTemp, 1, 1);
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
