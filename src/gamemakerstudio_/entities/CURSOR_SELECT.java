package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.assets_;
import gamemakerstudio_.misc.gameobject_;

import java.awt.*;

public class CURSOR_SELECT extends gameobject_ {
    public CURSOR_SELECT(float x, float y, ID id) {
        super(x, y, id);
        width = 32;
        height = 32;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(game_.isSelecting) g.drawImage(assets_.targetimage, (int)x, (int)y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}
