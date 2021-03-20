package gamemakerstudio_.world;

import gamemakerstudio_.tiles.FirstTile;
import gamemakerstudio_.tiles.SecondTile;
import gamemakerstudio_.tiles.ThirdTile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class tiles_ {

    public static tiles_[] tilesarray = new tiles_[256];
    public static tiles_ tileone = new FirstTile(1);
    public static tiles_ tiletwo = new SecondTile(2);
    public static tiles_ tilethree = new ThirdTile(3);

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    protected BufferedImage texture;
    protected final int id;

    public tiles_(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        tilesarray[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public int getId() {
        return id;
    }
}
