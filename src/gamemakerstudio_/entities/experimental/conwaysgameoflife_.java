package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class conwaysgameoflife_ extends gameobject_ implements MouseMotionListener, KeyListener, MouseListener {
    private static final int BLOCK_SIZE = 5; // default is 10
    private ArrayList<Point> point = new ArrayList<Point>(0);
    Dimension d_gameBoardSize = new Dimension(game_.WIDTH/BLOCK_SIZE-2, game_.HEIGHT/BLOCK_SIZE-2);

    public conwaysgameoflife_(float x, float y, ID id, game_ game) {
        super(x, y, id);
        randomlyFillBoard(50);
        game.addKeyListener(this);
        game.addMouseMotionListener(this);
        game.addMouseListener(this);
    }

    @Override
    public void tick() {
        if (!isPaused){
            boolean[][] gameBoard = new boolean[d_gameBoardSize.width+2][d_gameBoardSize.height+2];
            for (Point current : point) {
                gameBoard[current.x+1][current.y+1] = true;
            }
            ArrayList<Point> survivingCells = new ArrayList<Point>(0);
            // Iterate through the array, follow game of life rules
            for (int i=1; i<gameBoard.length-1; i++) {
                for (int j=1; j<gameBoard[0].length-1; j++) {
                    int surrounding = 0;
                    if (gameBoard[i-1][j-1]) { surrounding++; }
                    if (gameBoard[i-1][j])   { surrounding++; }
                    if (gameBoard[i-1][j+1]) { surrounding++; }
                    if (gameBoard[i][j-1])   { surrounding++; }
                    if (gameBoard[i][j+1])   { surrounding++; }
                    if (gameBoard[i+1][j-1]) { surrounding++; }
                    if (gameBoard[i+1][j])   { surrounding++; }
                    if (gameBoard[i+1][j+1]) { surrounding++; }
                    if (gameBoard[i][j]) {
                        // Cell is alive, Can the cell live? (2-3)
                        if ((surrounding == 2) || (surrounding == 3)) {
                            survivingCells.add(new Point(i-1,j-1));
                        }
                    } else {
                        // Cell is dead, will the cell be given birth? (3)
                        if (surrounding == 3) {
                            survivingCells.add(new Point(i-1,j-1));
                        }
                    }
                }
            }
            resetBoard();
            point.addAll(survivingCells);
        }
    }

    public void resetBoard() {
        point.clear();
    }

    public void randomlyFillBoard(int percent) {
        for (int i=0; i<d_gameBoardSize.width; i++) {
            for (int j=0; j<d_gameBoardSize.height; j++) {
                if (Math.random()*100 < percent) {
                    addPoint(i,j);
                }
            }
        }
    }

    public void addPoint(int x, int y) {
        if (!point.contains(new Point(x,y))) {
            point.add(new Point(x,y));
        }
    }

    public void removePoint(int x, int y) {
        if (point.contains(new Point(x,y)))
            point.remove(new Point(x,y));
    }

    public void addPoint(MouseEvent me) {
        int x = me.getPoint().x/BLOCK_SIZE-1;
        int y = me.getPoint().y/BLOCK_SIZE-1;
        if ((x >= 0) && (x < d_gameBoardSize.width) && (y >= 0) && (y < d_gameBoardSize.height)) {
            addPoint(x,y);
        }
    }

    public void removePoint(MouseEvent me) {
        int x = me.getPoint().x/BLOCK_SIZE-1;
        int y = me.getPoint().y/BLOCK_SIZE-1;
        if ((x >= 0) && (x < d_gameBoardSize.width) && (y >= 0) && (y < d_gameBoardSize.height)) {
            removePoint(x,y);
        }
    }

    @Override
    public void render(Graphics g) {
        try {
            for (Point newPoint : point) {
                // Draw new point
                g.setColor(Color.blue);
                g.fillRect(BLOCK_SIZE + (BLOCK_SIZE*newPoint.x), BLOCK_SIZE + (BLOCK_SIZE*newPoint.y), BLOCK_SIZE, BLOCK_SIZE);
            }
        } catch (ConcurrentModificationException cme) {}
        // Setup grid
        g.setColor(Color.BLACK);
        for (int i=0; i<=d_gameBoardSize.width; i++) {
            g.drawLine(((i*BLOCK_SIZE)+BLOCK_SIZE), BLOCK_SIZE, (i*BLOCK_SIZE)+BLOCK_SIZE, BLOCK_SIZE + (BLOCK_SIZE*d_gameBoardSize.height));
        }
        for (int i=0; i<=d_gameBoardSize.height; i++) {
            g.drawLine(BLOCK_SIZE, ((i*BLOCK_SIZE)+BLOCK_SIZE), BLOCK_SIZE*(d_gameBoardSize.width+1), ((i*BLOCK_SIZE)+BLOCK_SIZE));
        }
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    boolean isPaused = false;
    boolean add = false;
    boolean remove = false;

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            add = true;
        if (e.getKeyCode() == KeyEvent.VK_DELETE)
            remove = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (isPaused) isPaused = false;
            else isPaused = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            resetBoard();
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            add = false;
        if (e.getKeyCode() == KeyEvent.VK_DELETE)
            remove = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (add)
            addPoint(e);
        if (remove)
            removePoint(e);
    }

}
