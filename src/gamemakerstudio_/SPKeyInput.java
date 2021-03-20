package gamemakerstudio_;

import gamemakerstudio_.entities.player2_;
import gamemakerstudio_.entities.player_;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// i dunno why is this still here, maybe for debug?
public class SPKeyInput extends KeyAdapter {
    private boolean[] keyDownP1 = new boolean[2];
    private boolean[] keyDownP2 = new boolean[2];
    public SPKeyInput () {
        keyDownP1[0] = false;
        keyDownP1[1] = false;
        keyDownP2[0] = false;
        keyDownP2[1] = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // p1 misc
        if (key == KeyEvent.VK_E) {
            player_.isShooting = true; keyDownP1[0] = true;}
        if (key == KeyEvent.VK_Q) {player_.isDashing = true; keyDownP1[1] = true;}

        // p2 misc
        if (key == KeyEvent.VK_CONTROL) {
            player2_.isShooting = true; keyDownP2[0] = true;}
        if (key == KeyEvent.VK_SHIFT) {player2_.isDashing = true; keyDownP2[1] = true;}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_E) keyDownP1[0] = false;
        if (key == KeyEvent.VK_Q) keyDownP1[1] = false;

        if (key == KeyEvent.VK_CONTROL) keyDownP2[0] = false;
        if (key == KeyEvent.VK_SHIFT) keyDownP2[1] = false;

        // p1 reset
        if (!keyDownP1[0]) {player_.isShooting = false; player_.cooldownp1 = 0;}
        if (!keyDownP1[1]) {player_.dashcooldown = 15;}

        // p2 reset
        if (!keyDownP2[0]) {player2_.isShooting = false; player2_.cooldownp2 = 0;}
        if (!keyDownP2[1]) {player2_.dashcooldown = 15;}
    }
}
