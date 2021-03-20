package gamemakerstudio_.world;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.util_;

import java.awt.*;

public class world_ {

    private int width, height;
    private int[][] grid;
    private game_ game;

    public world_ (game_ game, String path) {
        this.game = game;
        loadWorld(path);
    }

    private void loadWorld(String path) {
        /*width = 5;
        height = 5;
        grid = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = 0;
            }
        }*/
        String file = util_.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = util_.parseInt(tokens[0]);
        height = util_.parseInt(tokens[1]);
        grid = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = util_.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }
    public void tick() {

    }
    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).render(g,
                        (int) (x * tiles_.TILEWIDTH - game.getGameCamera().getxOffset()),
                        (int) (y * tiles_.TILEHEIGHT - game.getGameCamera().getyOffset())
                );
            }
        }
    }
    public tiles_ getTile(int x, int y) {
        tiles_ t = tiles_.tilesarray[grid[x][y]];
        if (t == null) return tiles_.tileone;
        return t;
    }

}
