package gamemakerstudio_.misc;

import gamemakerstudio_.game_;

import javax.swing.*;
import java.awt.*;

public abstract class GameEngine extends Canvas implements Runnable {

    // game loop variables
    public Thread thread;
    public boolean running = false;
    public static boolean paused = false;
    public static int throwFrames, throwTick;
    public static long throwWait, throwErrorWait;
    String loops = "pro";
    // main gameloop vars fix
    public long lastTime = System.nanoTime();
    public double delta = 0;
    static double amountOfTicks = 100.0;
    public static double ns = 1000000000 / amountOfTicks;
    public void run() {
        this.requestFocus();
        // main loop
        if (loops == "pro"){
            // frame test
            long timer = System.currentTimeMillis();
            int frames = 0;
            // tick test
            int tick = 0;
            long timerTick = System.currentTimeMillis();
            while(running) {
                // loop this
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                try {
                    // pro tick
                    if (!game_.smoothFix) {
                        // loop this tick
                        while (delta >= 1) {
                            tick();
                            delta--;
                            // tick test
                            tick++;
                        }
                        // render this
                        if (running) render();
                        frames++;
                        // reset frame count
                        if (System.currentTimeMillis() - timer > 1000) {
                            timer += 1000;
                            // System.out.println("FPS: " + frames);
                            // System.out.println("Objects: " + handler.object.size());
                            throwFrames = frames;
                            // render();
                            frames = 0;
                        }
                        // test tick
                        if (System.currentTimeMillis() - timerTick > 1000) {
                            timerTick += 1000;
                            // System.out.println(tick);
                            throwTick = tick;
                            tick = 0;
                        }
                    }
                    // smooth fix, aka noob
                    else {
                        // now = System.nanoTime();

                        // tick
                        tick();

                        // render
                        render();

                        // removing this code bcz it's redundant
                        /*updateTime = System.nanoTime() - now;
                        wait = (OPTIMAL_TIME - updateTime) / 1000000000;*/

                        // test tick
                        tick++;
                        if (System.currentTimeMillis() - timerTick > 1000) {
                            timerTick += 1000;
                            // System.out.println(tick);
                            throwTick = tick;
                            tick = 0;
                        }
                        // frame count
                        frames++;
                        if (System.currentTimeMillis() - timer > 1000) {
                            timer += 1000;

                            // System.out.println("FPS: " + frames);
                            // System.out.println("Objects: " + handler.object.size());
                            throwFrames = frames;
                            // render();
                            frames = 0;
                        }

                        // yow wtf removing this codes fixes everything!

                        /*try {
                            throwWait = wait;
                            Thread.sleep(wait);
                        } catch (Exception e) {
                            throwErrorWait = wait;
                            e.printStackTrace();
                        }*/
                    }
                } catch (Exception e) {
                    handleError(e);
                }
            }
            stop();
        }
        if (loops == "noob") {
            // redundant
            /*long now;
            long updateTime;
            long wait;*/

            /*final int TARGET_FPS = 100; // also tick
            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; // also ns*/
        }
    }
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public abstract void tick();
    public abstract void render();
    // TODO: MERGE EVERY ERROR HANDLER HERE
    public void handleError(Exception e) {
        e.printStackTrace();
        int a = JOptionPane.showConfirmDialog(null, "An error occurred: " + e + ", " +
                "\ndo you still wish to continue?", "weird shit happened!", JOptionPane.INFORMATION_MESSAGE);
        if (a == JOptionPane.NO_OPTION) System.exit(0);
        // FIXME: if error caught but continued, still work in progress
    }
}
