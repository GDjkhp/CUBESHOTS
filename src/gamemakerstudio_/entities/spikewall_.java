package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.FACE;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;
import gamemakerstudio_.misc.entitystuff.handler_;

import java.awt.*;

public class spikewall_ extends gameobject_ {
    handler_ handler;
    public spikewall_(float x, float y, ID id, handler_ handler) {
        super(x, y, id);
        this.handler = handler;
        // TODO: animate this
        // top
        for (int i = 0; i < game_.WIDTH - 100; i += 50){
            handler.addObject(new spike_(i, 0, ID.Spikes, 0, 0, FACE.SOUTH));
        }
        // right
        for (int i = 0; i < game_.HEIGHT - 100; i += 50){
            handler.addObject(new spike_(game_.WIDTH - 50, i, ID.Spikes, 0, 0, FACE.WEST));
        }
        // bottom
        for (int i = game_.WIDTH - 50; i >= 50; i -= 50){
            handler.addObject(new spike_(i, game_.HEIGHT - 50, ID.Spikes, 0, 0, FACE.NORTH));
        }
        // left
        for (int i = game_.HEIGHT - 50; i >= 50; i -= 50){
            handler.addObject(new spike_(0, i, ID.Spikes, 0, 0, FACE.EAST));
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
