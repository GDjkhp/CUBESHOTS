package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.gameobject_;
import gamemakerstudio_.misc.handler_;

import java.awt.*;

public class testSpawnScreenLimit extends gameobject_ {
    handler_ handler;
    int count;
    public testSpawnScreenLimit(float x, float y, ID id, handler_ handler) {
        super(x, y, id);
        this.handler = handler;
        for (int xSpawn = 0; xSpawn <= game_.WIDTH - 10; xSpawn+=32){
            for (int ySpawn = 0; ySpawn <= game_.HEIGHT - 10; ySpawn+=32){
                handler.addObject(new heart_(xSpawn, ySpawn, ID.HeartFriend, handler, 0, 0));
                count++;
                System.out.println("count: " + count + " (" + xSpawn + ", " + ySpawn + ")");
            }
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
