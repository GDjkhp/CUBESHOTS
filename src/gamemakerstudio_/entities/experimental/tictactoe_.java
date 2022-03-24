package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class tictactoe_ extends gameobject_ implements MouseListener {
    Random r = new Random();
    String[][] board;
    boolean[][] isAvailable;
    String playerOne = "X";
    String playerTwo = "O";
    String currentPlayer = playerOne;
    // canvas
    int w = 300 / 3;
    int h = 300 / 3;

    public enum gameState {
        P1WON, P2WON, TIE
    }

    public gameState STATE;

    // FIXME: killing and then making a new instance makes a false conclusion
    public tictactoe_(float x, float y, ID id, game_ game) {
        super(x, y, id);
        board = new String[3][3];
        isAvailable = new boolean[3][3];
        game.addMouseListener(this);
        clearScreen();
    }

    void clearScreen(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = "";
                isAvailable[i][j] = true;
            }
        }
        STATE = null;
    }

    void nextTurn(int mx, int my){
        if (currentPlayer == playerOne){
            decide(mx, my, playerOne);
        }
        else if (currentPlayer == playerTwo){
            decide(mx, my, playerTwo);
        }
    }
    
    void decide(int mx, int my, String player){
        int i = 0, j = 0;
        // first row
        if (mx < 100 && my < 100){
            i = 0;
            j = 0;
        }
        if (mx > 100 && mx < 200 && my < 100){
            i = 1;
            j = 0;
        }
        if (mx > 200 && mx < 300 && my < 100){
            i = 2;
            j = 0;
        }
        // second row
        if (mx < 100 && my > 100 && my < 200){
            i = 0;
            j = 1;
        }
        if (mx > 100 && mx < 200 && my > 100 && my < 200){
            i = 1;
            j = 1;
        }
        if (mx > 200 && mx < 300 && my > 100 && my < 200){
            i = 2;
            j = 1;
        }
        // third row
        if (mx < 100 && my > 200 && my < 300){
            i = 0;
            j = 2;
        }
        if (mx > 100 && mx < 200 && my > 200 && my < 300){
            i = 1;
            j = 2;
        }
        if (mx > 200 && mx < 300 && my > 200 && my < 300){
            i = 2;
            j = 2;
        }
//        System.out.println("i: " + i + ", j: " + j);
        if (isAvailable[i][j]){
            board[i][j] = player;
            isAvailable[i][j] = false;
            conclusion(currentPlayer);
//            switchPlayer();
            decideAI();
//            dumbAI();
        }
    }

    void conclusion(String currentPlayer){
        // rewrite this
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean surrounding = false;
                // the following method is bullshit
                /*try {
                    if (board[i - 1][j] == currentPlayer) {
                        if (board[i + 1][j] == currentPlayer) {
                            surrounding = true;
                        }
                    }
                }catch (Exception e){

                }
                try {
                    if (board[i][j - 1] == currentPlayer) {
                        if (board[i][j + 1] == currentPlayer) {
                            surrounding = true;
                        }
                    }
                }catch (Exception e){

                }
                try {
                    if (board[i + 1][j + 1] == currentPlayer) {
                        if (board[i - 1][j - 1] == currentPlayer) {
                            surrounding = true;
                        }
                    }
                } catch (Exception e){

                }
                try {
                    if (board[i + 1][j - 1] == currentPlayer) {
                        if (board[i - 1][j + 1] == currentPlayer) {
                            surrounding = true;
                        }
                    }
                } catch (Exception e){

                }*/

                // another stolen code cuz the method before this was bullshit
                int whoWon = evaluate(board);
                if (whoWon != 0) surrounding = true;
                // check
                System.out.println("==========================================");
                System.out.println("score: " + evaluate(board));
                System.out.println("==========================================");
                for (int iTemp = 0; iTemp < 3; iTemp++) {
                    for (int jTemp = 0; jTemp < 3; jTemp++) {
                        System.out.print(isAvailable[jTemp][iTemp] + " ");
                    }
                    System.out.println("");
                }
                if (/*board[i][j] == currentPlayer && */surrounding){
                    if (currentPlayer == playerOne){
                        STATE = gameState.P1WON;
                    }
                    else if (currentPlayer == playerTwo){
                        STATE = gameState.P2WON;
                    }
                    // time
                    Date now = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(now);

                    System.out.println(now + ": " + currentPlayer + " won!");
                    JOptionPane.showMessageDialog(null, currentPlayer + " won!");
                    clearScreen();
                }
            }
        }
        // draw
        int openSpots = 0;
        for (int iTemp = 0; iTemp < 3; iTemp++) {
            for (int jTemp = 0; jTemp < 3; jTemp++) {
                if (board[iTemp][jTemp] == "") {
                    openSpots++;
                }
            }
        }
        if (STATE == null && openSpots == 0) {
            // time
            Date now = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);

            STATE = gameState.TIE;
            System.out.println(now + ": Tie!");
            JOptionPane.showMessageDialog(null, "Tie!");
            clearScreen();
        }
    }

    // dumb ai
    void dumbAI(){
        int i, j;
        i = r.nextInt(3);
        j = r.nextInt(3);

        if (isAvailable[i][j]){
            board[i][j] = playerTwo;
            isAvailable[i][j] = false;
            conclusion(playerTwo);
        } else {
            dumbAI();
        }
    }

    class Move{
        int row, col;
    }

    // smart ai
    void decideAI(){
        Move bestMove = findBestMove(board);
        board[bestMove.row][bestMove.col] = playerTwo;
        isAvailable[bestMove.row][bestMove.col] = false;
        conclusion(playerTwo);
    }

    Boolean isMovesLeft(String board[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == "")
                    return true;
        return false;
    }

    int evaluate(String b[][]) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++){
            if (b[row][0] == b[row][1] && b[row][1] == b[row][2]){
                if (b[row][0] == playerTwo)
                    return +10;
                else if (b[row][0] == playerOne)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++){
            if (b[0][col] == b[1][col] && b[1][col] == b[2][col]){
                if (b[0][col] == playerTwo)
                    return +10;

                else if (b[0][col] == playerOne)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]){
            if (b[0][0] == playerTwo)
                return +10;
            else if (b[0][0] == playerOne)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]){
            if (b[0][2] == playerTwo)
                return +10;
            else if (b[0][2] == playerOne)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    int minimax(String board[][], int depth, Boolean isMax) {
        int score = evaluate(board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (isMovesLeft(board) == false)
            return 0;

        // If this maximizer's move
        if (isMax){
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    // Check if cell is empty
                    if (board[i][j] == ""){
                        // Make the move
                        board[i][j] = playerTwo;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = "";
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else{
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    // Check if cell is empty
                    if (board[i][j] == ""){
                        // Make the move
                        board[i][j] = playerOne;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = "";
                    }
                }
            }
            return best;
        }
    }

    Move findBestMove(String board[][]){
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                // Check if cell is empty
                if (board[i][j] == ""){
                    // Make the move
                    board[i][j] = playerTwo;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = "";

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal){
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        /*System.out.printf("The value of the best Move " +
                "is : %d\n\n", bestVal);*/

        return bestMove;
    }

    void switchPlayer(){
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        }
        else if (currentPlayer == playerTwo) {
            currentPlayer = playerOne;
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        for (int xTemp = 0; xTemp < 3; xTemp++) {
            for (int yTemp = 0; yTemp < 3; yTemp++) {
//                g.drawString(board[xTemp][yTemp], xTemp * 100, (yTemp + 1) * 100);
                g.drawRect(xTemp*100, yTemp*100, 100, 100);
            }
        }
        // stolen code
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                int xTemp = w * i + w/2;
                int yTemp = h * j + h/2;
                String spot = board[i][j];
                if (spot == playerTwo){
                    g.drawOval(xTemp - 25, yTemp - 25, w/2, h/2);
                } else if (spot == playerOne){
                    int xr = w/4;
                    g.drawLine(xTemp - xr, yTemp - xr, xTemp + xr, yTemp + xr);
                    g.drawLine(xTemp + xr, yTemp - xr, xTemp - xr, yTemp + xr);
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, 0, 300, 300);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mx = e.getX(), my = e.getY();
        if (STATE == null && getBounds().intersects(mx, my, 1, 1)) {
            nextTurn(mx, my);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
