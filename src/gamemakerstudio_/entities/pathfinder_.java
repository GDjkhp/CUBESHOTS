package gamemakerstudio_.entities;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.gameobject_;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class pathfinder_ extends gameobject_ {
    Controller controller;
    game_ game;
    public static final int NODE_SIZE = 20; // default is 25
    int delay = 10;
    public pathfinder_(float x, float y, ID id, game_ game) {
        super(x, y, id);
        this.game = game;
        controller = new Controller();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        controller.paint(g);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    public class PathFinder {

        // variables used for Astar

//        private static final int NODE_SIZE = 25;
//        private static final int DIAGONAL_MOVE = (int) (Math.sqrt(1250));

        private Controller control;
        private Node start, end;
        private boolean deleteWalls, complete, isPause, run, isDijkstra;

        //data structures for A* pathfinding
        private PriorityQueue<Node> open;

        //list closed poitns and of all walls on the grid
        private Set<Point> closed, wall;

        // final path leading to the list
        private ArrayList<Node> finalPath;

        /**
         * inner class used for Dijkstra's Algorithm
         */
        class DFSFinder {

            //list for finalPath creation and keeping track of walls
            private ArrayList<Node> finalPath;

            //data structures for DFS search
            private Stack<Node> open;

            private Node start, end;

            private Set<Point> wall, closed;

            private Controller control;

            public DFSFinder(Controller control) {
                this.control = control;

                finalPath = new ArrayList<Node>();
                wall = new HashSet<Point>();
                open = new Stack<Node>();
                closed = new HashSet<Point>();
            }
        }

        /**
         * inner class used for comaparing Nodes
         */

        class NodeComparator implements Comparator<Node> {
            public int compare(Node xCoord, Node yCoord) {
                if(xCoord.getF() > yCoord.getF()) {
                    return 1;

                }else if(xCoord.getF() < yCoord.getF()) {
                    return -1;

                }else{

                    if(xCoord.getG() > yCoord.getG()) {
                        return 1;

                    }else if(xCoord.getG() < yCoord.getG()) {
                        return -1;

                    }

                }
                return 0;

            }
        }

        public PathFinder(Controller control) {
            this.control = control;

            run = false;
            isPause = true;

            finalPath = new ArrayList<Node>();
            wall = new HashSet<Point>();
            open = new PriorityQueue<Node>(new NodeComparator());
            closed = new HashSet<Point>();
        }

        /*
         * Checks to see if the list of walls contains a certain node.
         */
        public boolean isWall(Point point) {
            return wall.contains(point);
        }

        /*
         * Contains method to see if nodes are in the closed list.
         */
        public boolean closedContains(Point point) {
            return closed.contains(point);
        }

        public boolean closedRemove(Point point) {
            return closed.remove(point);
        }

        /*
         * Contains method to see if nodes are in the open list.
         */
        public boolean openContains(Node n) {
            return open.contains(n);
        }

        public boolean openRemove(Node n) {
            return open.remove(n);
        }

        public Node openFind(Node n) {
            for(Node x : open) {
                if(x.equals(n)) {
                    return x;
                }
            }

            return null;
        }

        /*
         * Adds a wall to the wall list if a wall at the same location is not
         * already present.
         */
        public boolean addWall(Point point) {
            return wall.add(point);
        }

        /*
         * Removes a wall node from the list of walls.
         */
        public boolean removeWall(Point point) {
            return wall.remove(point);
        }

        public void deleteWalls(boolean check) {
            deleteWalls = check;
        }

        public void setisDijkstra(boolean check) {
            isDijkstra = check;
        }

        public void reset() {
            run = false;
            isPause = true;
            complete = false;

            if(deleteWalls) {
                wall.clear();
                deleteWalls = false;
            }

            closed.clear();
            open.clear();
            finalPath.clear();
        }

        /*
         * Various getter methods to get the various lists containing the nodes.
         */
        public Set<Point> getWall() {
            return wall;
        }

        public PriorityQueue<Node> getOpen() {
            return open;
        }

        public Set<Point> getClosed() {
            return closed;
        }

        public ArrayList<Node> getFinal() {
            return finalPath;
        }

        /*
         * Various setter methods to set the various lists containing the nodes.
         */
        public void setStart(Node start) {
            this.start = new Node(start.getX(), start.getY());
            open.add(this.start);
        }

        public void setEnd(Node end) {
            this.end = new Node(end.getX(), end.getY());
        }

        public void setisPause(boolean isPause) {
            this.isPause = isPause;
        }

        public void setisRun(boolean run) {
            this.run = run;
        }

        public boolean isRun() {
            return run;
        }

        public boolean isPause() {
            return isPause;
        }

        public boolean isComplete() {
            return complete;
        }

        /*
         * Constructs the final path from start to end node. Only called once a
         * valid path is found.
         */
        public void constructPath() {
            Node current = end;
            while(!(current.getParent().equals(start))) {
                finalPath.add(0, current.getParent());
                current = current.getParent();
            }

            finalPath.add(0, current);
        }

        /*
         * Method finds the cost associated with moving from the current node to
         * the neighbor node. Uses the formula for the distance between two points.
         */
        public double gCostMovement(Node parent, Node neighbor) {
            //distance from point to point in a grid
            int xCoord = neighbor.getX() - parent.getX();
            int yCoord = neighbor.getY() - parent.getY();

            return (int) (Math.sqrt(Math.pow(xCoord, 2) + Math.pow(yCoord, 2)));
        }

        /*
         * Method finds the heuristic cost from the neighbor node to the end node.
         * From the Stanford page: "Here we compute the number of steps you take if
         * you can’t take a diagonal, then subtract the steps you save by using the
         * diagonal. There are min(dx, dy) diagonal steps, and each one costs D2 but
         * saves you 2⨉D non-diagonal steps."
         *
         * The heuristic used is octile distance where the cost of an orthogonal move
         * is one and the cost of a diagonal is sqrt(2).
         */
        public double hCostMovement(Node neighbor) {
            int hXCost = Math.abs(end.getX() - neighbor.getX());
            int hYCost = Math.abs(end.getY() - neighbor.getY());
            double hCost = hXCost + hYCost;

            if(control.isOctile()) {
                if(hXCost > hYCost) {
                    hCost = ((hXCost - hYCost) + Math.sqrt(2) * hYCost);
                } else {
                    hCost = ((hYCost - hXCost) + Math.sqrt(2) * hXCost);
                }
            }

            return hCost;
        }

        /*
         * A* pathfinding algorithm. Tries to explore the fewest number of nodes to
         * reach the end node. Self corrects the path to the end node using the
         * heuristic cost function h.
         */
        public void aStarPath() {
            //get node with lowest F cost off PQ
            Node current = open.poll();

            //if no min node, then no path
            if(current == null) {
                System.out.println("No path");
                run = false;
                isPause = true;
                return;
            }

            //if min node is the end, then stop algorithm and build final path
            if(!isDijkstra && current.equals(end)) {
                end.setParent(current.getParent());
                run = false;
                isPause = true;
                complete = true;
                constructPath();
                System.out.println("Total Cost of Path: " + end.getParent().getG());
                System.out.println("Size of Open: " + open.size());
                System.out.println("Size of Closed: " + closed.size());
                System.out.println("Size of Path: " + finalPath.size() + "\n");
                return;
            }

            closed.add(new Point(current.getX(), current.getY()));

            //calculate costs for the 8 possible adjacent nodes to current
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {

                    //skip the current node we are exploring
                    if(i == 1 && j == 1) {
                        continue;
                    }

                    int xCoord = (current.getX() - NODE_SIZE) + (NODE_SIZE * i);
                    int yCoord = (current.getY() - NODE_SIZE) + (NODE_SIZE * j);
                    Node neighbor = new Node(xCoord, yCoord);

                    //for Dijkstra, once we encounter end node we have the shortest path
                    if(isDijkstra && neighbor.equals(end)) {
                        end.setParent(current);
                        run = false;
                        isPause = true;
                        complete = true;
                        constructPath();
                        System.out.println("Total Cost of Path: " + end.getParent().getG());
                        System.out.println("Size of Open: " + open.size());
                        System.out.println("Size of Closed: " + closed.size());
                        System.out.println("Size of Path: " + finalPath.size() + "\n");
                        return;
                    }

                    //checks if node is within canvas boundary
                    if(xCoord < 0 || yCoord < 0 || xCoord >= game_.WIDTH || yCoord >=
                            game_.HEIGHT) {
                        continue;
                    }

                    //checks to see if the neighbor node is a wall, in the open/closed list
                    if(isWall(new Point(neighbor.getX(), neighbor.getY()))) {
                        continue;
                    }

                    int wallJumpX = current.getX() + (xCoord - current.getX());
                    int wallJumpY = current.getY() + (yCoord - current.getY());

                    //checks for border in adjacent pos, does not allow for a diagonal
                    //jump across a border
                    if(isWall(new Point(wallJumpX, current.getY())) || isWall(new
                            Point(current.getX(), wallJumpY)) && ((j == 0 | j == 2) && i != 1)) {
                        continue;
                    }

                    //calculate f, g, and h costs for this node
                    double gCost = current.getG() + gCostMovement(current, neighbor);
                    double hCost = hCostMovement(neighbor);
                    double fCost = gCost + hCost;

                    boolean inOpen = openContains(neighbor);
                    boolean inClosed = closedContains(new Point(neighbor.getX(),
                            neighbor.getY()));
                    Node found = openFind(neighbor);

                    //if inOpen and inClosed cases just in case, should not happen
                    //if node in open and we found lower gCost, no need to search neighbor
                    if(inOpen && (gCost < found.getG())) {
                        openRemove(found);
                        neighbor.setG(gCost);
                        neighbor.setF(gCost + found.getH());
                        neighbor.setParent(current);
                        open.add(neighbor);
                        continue;
                    }

                    //if neighbor in closed and found lower gCost, visit again
                    if(inClosed && (gCost < neighbor.getG())) {
                        System.out.println("HEYCLOSED");
                        continue;
                    }

                    //if neighbor not visited, then add to open list
                    if(!inOpen && !inClosed) {

                        if(isDijkstra) {
                            neighbor.setG(gCost);
                            neighbor.setF(gCost);
                        } else {
                            neighbor.setG(gCost);
                            neighbor.setH(hCost);
                            neighbor.setF(fCost);
                        }

                        neighbor.setParent(current);

                        open.add(neighbor);
                    }
                }
            }

        }

    }

    public class Controller implements ActionListener,
            MouseListener, KeyListener, MouseMotionListener {
        private PathFinder path;

        private Timer timer;

        private Node start;
        private Node end;

        private char keyPress;
        private boolean isOctile;
        private boolean isManhattan;

        private static final int WIDTH = 750;
        private static final int HEIGHT = 750;

        public Controller(){
            isOctile = true;
            isManhattan = false;
            // add input here
            game.addMouseListener(this);
            game.addKeyListener(this);
            game.addMouseMotionListener(this);
            timer = new Timer(delay, this);
            path = new PathFinder(this);
            timer.start();
        }

        public void paint(Graphics g) {
            //Draw grid for pathfind
            g.setColor(Color.lightGray);
            for(int j = 0; j < game_.HEIGHT; j += NODE_SIZE) {
                for(int i = 0; i < game_.WIDTH; i += NODE_SIZE) {

                    g.setColor(new Color(40, 42, 54));
                    g.fillRect(i, j, NODE_SIZE, NODE_SIZE);
                    g.setColor(Color.black);
                    g.drawRect(i, j, NODE_SIZE, NODE_SIZE);
                }
            }

            //draw the wall nodes
            Set<Point> wallList = path.getWall();
            g.setColor(new Color(68, 71, 90));
            for(Point pt : wallList) {
                int xCoord = (int) pt.getX();
                int yCoord = (int) pt.getY();

                g.fillRect(xCoord + 1, yCoord + 1, NODE_SIZE - 2, NODE_SIZE - 2);
            }

            //draw closed list
            Set<Point> closedList = path.getClosed();
            g.setColor(new Color(253, 90, 90));
            for(Point pt : closedList) {
                int xCoord = (int) pt.getX();
                int yCoord = (int) pt.getY();

                g.fillRect(xCoord + 1, yCoord + 1, NODE_SIZE - 2, NODE_SIZE - 2);
            }

            //draw open list
            PriorityQueue<Node> openList = path.getOpen();
            g.setColor(new Color(80, 250, 123));
            for(Node e : openList) {
                g.fillRect(e.getX() + 1, e.getY() + 1, NODE_SIZE - 2, NODE_SIZE - 2);
            }

            //draw final path
            ArrayList<Node> finalPath = path.getFinal();
            g.setColor(new Color(189, 147, 249));
            for(int i = 0; i < finalPath.size(); i++) {
                g.fillRect(finalPath.get(i).getX() + 1, finalPath.get(i).getY() + 1,
                        NODE_SIZE - 2, NODE_SIZE - 2);
            }

            //draw the start node
            if(start != null) {
                g.setColor(new Color(139, 233, 253));
                g.fillRect(start.getX() + 1, start.getY() + 1, NODE_SIZE - 2, NODE_SIZE -
                        2);
            }

            //draw the end node
            if(end != null) {
                g.setColor(new Color(255, 121, 198));
                g.fillRect(end.getX() + 1, end.getY() + 1, NODE_SIZE - 2, NODE_SIZE - 2);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // set a timer delay of 50
            timer.setDelay(delay);

            if(path.isRun() && !path.isComplete() && !path.isPause()) {
                path.aStarPath();
            }
        }

        public boolean isOctile() {
            return isOctile;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        boolean holdWall = false;
        @Override
        public void keyPressed(KeyEvent e) {
            //get keyPress to know if we should make start, end, or delete
            keyPress = e.getKeyChar();

            switch(keyPress){

                case KeyEvent.VK_SPACE:
                    //start algorithm or stop it
                    //set start and end node for path
                    //on first run, set start and end; do again when finish algorithm
                    if(!path.isComplete() && !path.isRun() && start != null && end != null){
                        path.setisRun(true);
                        path.setStart(start);
                        path.setEnd(end);

                    }

                    //press space to pause the algorithm
                    if(!path.isPause()) {
                        path.setisPause(true);

                    }else if(path.isPause()) {
                        path.setisPause(false);

                    }
                    break;

                // backspace to grid delete
                case KeyEvent.VK_BACK_SPACE:
                    if(!path.isRun()) {
                        path.deleteWalls(true);
                        path.reset();

                        System.out.println("Grid Deletion Complete.\n");
                    }
                    break;

                case '1':
                    if(!path.isRun()) {
                        path.setisDijkstra(true);
                        System.out.println("Begin Dijkstra.\n");

                    }
                    break;

                case '2':
                    if(!path.isRun()) {
                        path.setisDijkstra(false);
                        System.out.println("Begin A-Star\n");
                    }
                    break;

                case 'c':
                    //command to clear and reset
                    path.reset();
                    break;

                case 'm':
                    if(!path.isRun()) {
                        isManhattan = true;
                        isOctile = false;

                        System.out.println("Use MANHATTAN\n");
                    }
                    break;

                case 'o':
                    if(!path.isRun()) {
                        isOctile = true;
                        isManhattan = false;

                        System.out.println("Use OCTILE\n");
                    }
                    break;

                case 'w':
                    holdWall = true;
                    break;

                default:
            }
        }

        public void gridWork(MouseEvent e) {

            //if mouse click was left click
            if(e.getButton() == MouseEvent.BUTTON1) {

                //mouse clicks not exactly at node point of creation, so find remainder
                int xOver = e.getX() % NODE_SIZE;
                int yOver = e.getY() % NODE_SIZE;

                //s key and left mouse makes start node
                if(keyPress == 's') {

                    int xTmp = e.getX() - xOver;
                    int yTmp = e.getY() - yOver;

                    //if start null, create start on nodes where end not already
                    if(start == null) {

                        if(!path.isWall(new Point(xTmp, yTmp))) {
                            if(end == null) {
                                start = new Node(xTmp, yTmp);
                            } else {
                                if(!end.equals(new Node(xTmp, yTmp))) {
                                    start = new Node(xTmp, yTmp);
                                }
                            }
                        }

                        //otherwise, do not move start to where end is
                    } else {

                        if(!path.isWall(new Point(xTmp, yTmp))) {
                            if(end == null) {
                                start.setXY(xTmp, yTmp);
                            } else {
                                if(!end.equals(new Node(xTmp, yTmp))) {
                                    start.setXY(xTmp, yTmp);
                                }
                            }
                        }

                    }

                    //e key and left mouse makes end node
                }
                else if(keyPress == 'e') {

                    int xTmp = e.getX() - xOver;
                    int yTmp = e.getY() - yOver;

                    //if end null, create end on nodes where start not already
                    if(end == null) {

                        if(!path.isWall(new Point(xTmp, yTmp))) {
                            if(start == null) {
                                end = new Node(xTmp, yTmp);
                            } else {
                                if(!start.equals(new Node(xTmp, yTmp))) {
                                    end = new Node(xTmp, yTmp);
                                }
                            }
                        }

                        //otherwise, do not move end to where start is
                    } else {

                        if(!path.isWall(new Point(xTmp, yTmp))) {
                            if(start == null) {
                                end.setXY(xTmp, yTmp);
                            } else {
                                if(!start.equals(new Node(xTmp, yTmp))) {
                                    end.setXY(xTmp, yTmp);
                                }
                            }
                        }

                    }

                    //d key and left mouse deletes nodes
                }
                else if(keyPress == 'd') {
                    //delete walls with this function if no right click
                    int nodeX = e.getX() - xOver;
                    int nodeY = e.getY() - yOver;

                    if(start != null && start.equals(new Node(nodeX, nodeY))) {
                        start = null;
                    } else if(end != null && end.equals(new Node(nodeX, nodeY))) {
                        end = null;
                    } else {
                        path.removeWall(new Point(nodeX, nodeY));
                    }

                    //just mouse click makes walls
                }
                else {
                    //create walls and add to wall list
                    Node tmpWall = new Node(e.getX() - xOver, e.getY() - yOver);

                    if(start == null && end == null) {
                        path.addWall(new Point(tmpWall.getX(), tmpWall.getY()));
                    }

                    if(!(tmpWall.equals(start)) && !(tmpWall.equals(end))) {
                        path.addWall(new Point(tmpWall.getX(), tmpWall.getY()));
                    }
                }

                //if mouse click was right click
            } else if(e.getButton() == MouseEvent.BUTTON1) {
                //delete nodes with right click
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keyPress = 0;
            if (e.getKeyChar() == 'w')
                holdWall = false;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //all mouse clicks to change grid somehow
            if(!path.isRun()) {
                gridWork(e);
            }
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
            if(!path.isRun()) {
                gridWork(e);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (holdWall) {
                int xOver = e.getX() % NODE_SIZE;
                int yOver = e.getY() % NODE_SIZE;
                //create walls and add to wall list
                Node tmpWall = new Node(e.getX() - xOver, e.getY() - yOver);

                if (start == null && end == null) {
                    path.addWall(new Point(tmpWall.getX(), tmpWall.getY()));
                }

                if (!(tmpWall.equals(start)) && !(tmpWall.equals(end))) {
                    path.addWall(new Point(tmpWall.getX(), tmpWall.getY()));
                }
            }
        }
    }

    public class Node {

        private int x;
        // varaibles used for x
        private int y;
        private double f, g, h;
        private Node parent;

        /**
         * The node constructor that creates the initial positions of the nodes.
         */
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Setter Functions to set the cost calculations, parents of the node, and
         * more.
         */

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setF(double f) {
            this.f = f;
        }

        public void setG(double g) {
            this.g = g;
        }

        public void setH(double h) {
            this.h = h;
        }

        /**
         * Various getter functions to get the positions of the node, get the
         * heuristic/cost calculations, and the parent of this node.
         */

        public Node getParent() {
            return parent;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public double getG() {
            return g;
        }

        public double getF() {
            return f;
        }

        public double getH() {
            return h;
        }

        /**
         * Extra methods that is used to compare Nodes and print the location
         * of the nodes.
         */

        @Override
        public String toString() {
            return "F Cost: " + f + " G Cost: " + g + " H Cost: " + h + "\n";
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null) {
                return false;
            }

            Node tmp = (Node) obj;
            if(this.x == tmp.getX() && this.y == tmp.getY()) {
                return true;
            }

            return false;
        }
    }

}
