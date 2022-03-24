package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class maze_ extends gameobject_ implements KeyListener {

    public static final int WIDTH = 500;
    public static final int HEIGHT = WIDTH; // best to keep these the same. variable is only created for readability.
    public static final int W = 20;

    public static int speed = 1; // 1 = fastest, 40 = slowest
    public static boolean generated, solved;

    private static final String[] GENERATION_METHODS = {"0. Aldous-Broder", "1. Binary Tree",
            "2. DFS", "3. Eller's", "4. Growing Forest", "5. Growing Tree", "6. Houston's",
            "7. Hunt & Kill", "8. Kruskal's", "9. Prim's", "10. Quad-directional DFS", "11. Sidewinder",
            "12. Spiral Backtracker", "13. Wilson's", "14. Zig-Zag"};
    private static final String[] SOLVING_METHODS = {"0. Bi-directional DFS", "1. BFS", "2. DFS", "3. Dijkstra's"};

    private int cols = Math.floorDiv(WIDTH, W), rows = cols;

    // createAndShowGui codes
    MazeGridPanel grid;

    // switch if running
    public static boolean running = false;

    // TODO: yow port this, you lazybones
    public maze_(float x, float y, ID id, game_ game) {
        super(x, y, id);

        generated = false;
        solved = false;

        grid = new MazeGridPanel(rows, cols);

        // one lines of code, FOR INPUT
        game.addKeyListener(this);
    }

    @Override
    public void tick() {
        if (running){
            if (!generated && !solved){
                // progress bar for generation
                int progress = 0;
                for (int i = 0; i < grid.grid.size(); i++){
                    if (grid.grid.get(i).visited)
                        progress++;
                }
                info = "Progress: " + Math.round((((float)progress/grid.grid.size()) * 100f)) + "%";
                if (((float)progress/grid.grid.size()) * 100f == 100f)
                    info = SOLVING_METHODS[solIndex];
            } else if (!solved) {
                info = "Solving...";
            }
        }
        if (solved) {
            info = "Press <SPACE> to reset...";
        }
    }

    String info = GENERATION_METHODS[0];

    @Override
    public void render(Graphics g) {
        g.drawString(info, 0, game_.HEIGHT);
        grid.paintComponent(g);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    int genIndex = 0, solIndex = 0;
    
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (!running) {
            // run
            if (key == KeyEvent.VK_SPACE){
                running = true;
                // generate
                if (!generated && !solved) {
                    grid.generate(genIndex);
                }
                // solve
                else {
                    if (generated && !solved) {
                        grid.solve(solIndex);
                    } else {
                        // reset
                        grid = new MazeGridPanel(rows, cols);
                        generated = false;
                        solved = false;
                        running = false;
                    }
                }
            }
            // gen
            if (!generated && !solved){
                if (key == KeyEvent.VK_RIGHT) {
                    if (genIndex == 14)
                        genIndex = 0;
                    else genIndex++;
                }
                if (key == KeyEvent.VK_LEFT) {
                    if (genIndex == 0)
                        genIndex = 14;
                    else genIndex--;
                }
                info = GENERATION_METHODS[genIndex];
            }
            // sol
            if (generated && !solved){
                if (key == KeyEvent.VK_RIGHT) {
                    if (solIndex == 3)
                        solIndex = 0;
                    else solIndex++;
                }
                if (key == KeyEvent.VK_LEFT) {
                    if (solIndex == 0)
                        solIndex = 3;
                    else solIndex--;
                }
                info = SOLVING_METHODS[solIndex];
            }
        }
    }

    // classes here
    public class MazeGridPanel {

        private final List<Cell> grid = new ArrayList<Cell>();
        private List<Cell> currentCells = new ArrayList<Cell>();

        public MazeGridPanel(int rows, int cols) {
            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < cols; y++) {
                    grid.add(new Cell(x, y));
                }
            }
        }

        public Dimension getPreferredSize() {
            // +1 pixel on width and height so bottom and right borders can be drawn.
            return new Dimension(maze_.WIDTH + 1, maze_.HEIGHT + 1);
        }

        public void generate(int index) {
            // switch statement for gen method read from combobox in Maze.java
            switch (index) {
                case 0:
                    new AldousBroderGen(grid, this);
                    break;
                case 1:
                    new BinaryTreeGen(grid, this);
                    break;
                case 2:
                    new DFSGen(grid, this);
                    break;
                case 3:
                    new EllersGen(grid, this);
                    break;
                case 4:
                    new GrowingForestGen(grid, this);
                    break;
                case 5:
                    new GrowingTreeGen(grid, this);
                    break;
                case 6:
                    new HoustonGen(grid, this);;
                    break;
                case 7:
                    new HuntAndKillGen(grid, this);
                    break;
                case 8:
                    new KruskalsGen(grid, this);;
                    break;
                case 9:
                    new PrimsGen(grid, this);
                    break;
                case 10:
                    new QuadDFSGen(grid, this);
                    break;
                case 11:
                    new SidewinderGen(grid, this);
                    break;
                case 12:
                    new SpiralBacktrackerGen(grid, this);
                    break;
                case 13:
                    new WilsonsGen(grid, this);
                    break;
                case 14:
                    new ZigZagGen(grid, this);
                    break;
                default:
                    new GrowingTreeGen(grid, this);
                    break;
            }
        }

        public void solve(int index) {
            switch (index) {
                case 0:
                    new BiDFSSolve(grid, this);
                    break;
                case 1:
                    new BFSSolve(grid, this);
                    break;
                case 2:
                    new DFSSolve(grid, this);
                    break;
                case 3:
                    new DijkstraSolve(grid, this);
                    break;
                default:
                    new DijkstraSolve(grid, this);
                    break;
            }
        }

        public void resetSolution() {
            for (Cell c : grid) {
                c.setDeadEnd(false);
                c.setPath(false);
                c.setDistance(-1);
                c.setParent(null);
            }
        }

        public void setCurrent(Cell current) {
            if(currentCells.size() == 0) {
                currentCells.add(current);
            } else {
                currentCells.set(0, current);
            }
        }

        public void setCurrentCells(List<Cell> currentCells) {
            this.currentCells = currentCells;
        }

        protected void paintComponent(Graphics g) {
            for (Cell c : grid) {
                c.draw(g);
            }
            for (Cell c : currentCells) {
                if(c != null) c.displayAsColor(g, Color.ORANGE);
            }
            grid.get(0).displayAsColor(g, Color.GREEN); // start cell
            grid.get(grid.size() - 1).displayAsColor(g, Color.YELLOW); // end or goal cell
        }
    }

    public class Cell {
        private int x, y, distance, id;

        private Cell parent;

        private boolean visited = false;
        private boolean path = false;
        private boolean deadEnd = false;

        private boolean[] walls = {true, true, true, true};

        public boolean[] getWalls() {
            return walls;
        }

        public void setWalls(boolean[] walls) {
            this.walls = walls;
        }

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
            this.distance = -1;
        }
        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public boolean isDeadEnd() {
            return deadEnd;
        }

        public void setDeadEnd(boolean deadEnd) {
            this.deadEnd = deadEnd;
        }

        public boolean isPath() {
            return path;
        }

        public void setPath(boolean path) {
            this.path = path;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public Cell getParent() {
            return parent;
        }

        public void setParent(Cell parent) {
            this.parent = parent;
        }

        public void draw(Graphics g) {
            int x2 = x * maze_.W;
            int y2 = y * maze_.W;

            if (visited) {
                g.setColor(Color.MAGENTA);
                g.fillRect(x2, y2, maze_.W, maze_.W);
            }

            if (path) {
                g.setColor(Color.BLUE);
                g.fillRect(x2, y2, maze_.W, maze_.W);
            } else if (deadEnd) {
                g.setColor(Color.RED);
                g.fillRect(x2, y2, maze_.W, maze_.W);
            }

            g.setColor(Color.WHITE);
            if (walls[0]) {
                g.drawLine(x2, y2, x2+maze_.W, y2);
            }
            if (walls[1]) {
                g.drawLine(x2+maze_.W, y2, x2+maze_.W, y2+maze_.W);
            }
            if (walls[2]) {
                g.drawLine(x2+maze_.W, y2+maze_.W, x2, y2+maze_.W);
            }
            if (walls[3]) {
                g.drawLine(x2, y2+maze_.W, x2, y2);
            }
        }

        public void displayAsColor(Graphics g, Color color) {
            int x2 = x * maze_.W;
            int y2 = y * maze_.W;
            g.setColor(color);
            g.fillRect(x2, y2, maze_.W, maze_.W);
        }

        public void removeWalls(Cell next) {
            int x = this.x - next.x;
            // top 0, right 1, bottom 2, left 3

            if(x == 1) {
                walls[3] = false;
                next.walls[1] = false;
            } else if (x == -1) {
                walls[1] = false;
                next.walls[3] = false;
            }

            int y = this.y - next.y;

            if(y == 1) {
                walls[0] = false;
                next.walls[2] = false;
            } else if (y == -1) {
                walls[2] = false;
                next.walls[0] = false;
            }
        }

        private Cell randomNeighbour(java.util.List<Cell> neighbours) {
            if (neighbours.size() > 0) {
                return neighbours.get(new Random().nextInt(neighbours.size()));
            } else {
                return null;
            }
        }

        private Cell checkNeighbourInGridBounds(java.util.List<Cell> grid, Cell neighbour) {
            if (grid.contains(neighbour)) {
                return grid.get(grid.indexOf(neighbour));
            } else {
                return null;
            }
        }

        // Used for Wilson's algorithm
        public Cell getNonPathNeighbour(java.util.List<Cell> grid) {

            java.util.List<Cell> neighbours = new ArrayList<Cell>(4);

            Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
            Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
            Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
            Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));

            if (top != null) if(!top.path) neighbours.add(top);
            if (right != null) if(!right.path) neighbours.add(right);
            if (bottom != null) if(!bottom.path) neighbours.add(bottom);
            if (left != null) if(!left.path) neighbours.add(left);

            if (neighbours.size() ==  1) {
                return neighbours.get(0);
            }
            return randomNeighbour(neighbours);
        }

        public Cell getUnvisitedNeighbour(java.util.List<Cell> grid) {

            java.util.List<Cell> neighbours = getUnvisitedNeighboursList(grid);

            if (neighbours.size() ==  1) {
                return neighbours.get(0);
            }
            return randomNeighbour(neighbours);
        }

        public java.util.List<Cell> getUnvisitedNeighboursList(java.util.List<Cell> grid) {

            java.util.List<Cell> neighbours = new ArrayList<Cell>(4);

            Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
            Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
            Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
            Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));

            if (top != null) if(!top.visited) neighbours.add(top);
            if (right != null) if(!right.visited) neighbours.add(right);
            if (bottom != null)if(!bottom.visited) neighbours.add(bottom);
            if (left != null) if(!left.visited)neighbours.add(left);

            return neighbours;
        }

        // no walls between
        public java.util.List<Cell> getValidMoveNeighbours(java.util.List<Cell> grid) {
            java.util.List<Cell> neighbours = new ArrayList<Cell>(4);

            Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
            Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
            Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
            Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));

            if (top != null) {
                if (!walls[0]) neighbours.add(top);
            }

            if (right != null) {
                if (!walls[1]) neighbours.add(right);
            }

            if (bottom != null) {
                if (!walls[2]) neighbours.add(bottom);
            }

            if (left != null) {
                if (!walls[3]) neighbours.add(left);
            }

            return neighbours;
        }

        // used for DFS solving, gets a neighbour that could potentially be part of the solution path.
        public Cell getPathNeighbour(java.util.List<Cell> grid) {
            java.util.List<Cell> neighbours = new ArrayList<Cell>();

            Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
            Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
            Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
            Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));

            if (top != null) {
                if (!top.deadEnd) {
                    if (!walls[0]) neighbours.add(top);
                }
            }

            if (right != null) {
                if (!right.deadEnd) {
                    if (!walls[1]) neighbours.add(right);
                }
            }

            if (bottom != null) {
                if (!bottom.deadEnd) {
                    if (!walls[2]) neighbours.add(bottom);
                }
            }

            if (left != null) {
                if (!left.deadEnd) {
                    if (!walls[3]) neighbours.add(left);
                }
            }

            if (neighbours.size() ==  1) {
                return neighbours.get(0);
            }

            return randomNeighbour(neighbours);
        }

        public java.util.List<Cell> getAllNeighbours(java.util.List<Cell> grid) {
            java.util.List<Cell> neighbours = new ArrayList<Cell>();

            Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
            Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
            Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
            Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));

            if (top != null) neighbours.add(top);
            if (right != null) neighbours.add(right);
            if (bottom != null) neighbours.add(bottom);
            if (left != null) neighbours.add(left);

            return neighbours;
        }

        public Cell getTopNeighbour(java.util.List<Cell> grid) {
            return checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
        }

        public Cell getRightNeighbour(java.util.List<Cell> grid) {
            return checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
        }

        public Cell getBottomNeighbour(java.util.List<Cell> grid) {
            return checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
        }

        public Cell getLeftNeighbour(List<Cell> grid) {
            return checkNeighbourInGridBounds(grid, new Cell(x - 1, y));
        }

        // i dunno what this do
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Cell other = (Cell) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }
    }

    public class DisjointSets {

        private List<Map<Integer, Set<Integer>>> disjointSet;

        public DisjointSets() {
            disjointSet = new ArrayList<Map<Integer, Set<Integer>>>();
        }

        public void create_set(int element) {
            Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
            Set<Integer> set = new HashSet<Integer>();

            set.add(element);
            map.put(element, set);

            disjointSet.add(map);
        }

        public void union(int first, int second) {

            int first_rep = find_set(first);
            int second_rep = find_set(second);

            Set<Integer> first_set = null;
            Set<Integer> second_set = null;

            for (int index = 0; index < disjointSet.size(); index++) {
                Map<Integer, Set<Integer>> map = disjointSet.get(index);

                if (map.containsKey(first_rep)) {
                    first_set = map.get(first_rep);
                } else if (map.containsKey(second_rep)) {
                    second_set = map.get(second_rep);
                }
            }

            if (first_set != null && second_set != null)
                first_set.addAll(second_set);

            for (int index = 0; index < disjointSet.size(); index++) {

                Map<Integer, Set<Integer>> map = disjointSet.get(index);

                if (map.containsKey(first_rep)) {
                    map.put(first_rep, first_set);
                } else if (map.containsKey(second_rep)) {
                    map.remove(second_rep);
                    disjointSet.remove(index);
                }
            }
            return;
        }

        public int find_set(int element) {

            for (int index = 0; index < disjointSet.size(); index++) {
                Map<Integer, Set<Integer>> map = disjointSet.get(index);
                Set<Integer> keySet = map.keySet();

                for (Integer key : keySet) {
                    Set<Integer> set = map.get(key);
                    if (set.contains(element)) {
                        return key;
                    }
                }
            }
            return -1;
        }

        public int getNumberofDisjointSets() {
            return disjointSet.size();
        }
    }

    // generator
    public class AldousBroderGen {

        private final List<Cell> grid;
        private Cell current;
        private Random r = new Random();

        public AldousBroderGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            current = grid.get(r.nextInt(grid.size() - 1));
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            current.setVisited(true);
            List<Cell> neighs = current.getAllNeighbours(grid);
            Cell next = neighs.get(r.nextInt(neighs.size()));
            if (!next.isVisited()) {
                current.removeWalls(next);
            }
            current = next;
        }
    }

    public class BinaryTreeGen {

        private final List<Cell> grid;
        private Cell current;
        private int index;
        private final Random r = new Random();

        public BinaryTreeGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            index = grid.size() - 1;
            current = grid.get(index);
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            boolean topNeigh = grid.contains(new Cell(current.getX(), current.getY() - 1));
            boolean leftNeigh = grid.contains(new Cell(current.getX() - 1, current.getY()));
            if (topNeigh && leftNeigh) {
                carveDirection(r.nextInt(2));
            } else if (topNeigh) {
                carveDirection(0);
            } else if (leftNeigh) {
                carveDirection(1);
            }

            current.setVisited(true);


            if (index - 1 >= 0) {
                current = grid.get(--index);
            }
            // else we break out of if statement in timer Action Listener and set maze to generated.

        }

        // 0 down
        // 1 right
        private void carveDirection(int dir) {
            if (dir == 0) {
                List<Cell> neighs = current.getAllNeighbours(grid);
                for (Cell c : neighs) {
                    if (c.getY() + 1 == current.getY()) current.removeWalls(c);
                }
            } else {
                List<Cell> neighs = current.getAllNeighbours(grid);
                for (Cell c : neighs) {
                    if (c.getX() + 1 == current.getX()) current.removeWalls(c);
                }
            }
        }
    }

    public class DFSGen {

        private final Stack<Cell> stack = new Stack<Cell>();
        private final List<Cell> grid;
        private Cell current;

        public DFSGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            current = grid.get(0);
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            current.setVisited(true);
            Cell next = current.getUnvisitedNeighbour(grid);
            if (next != null) {
                stack.push(current);
                current.removeWalls(next);
                current = next;
            } else if (!stack.isEmpty()) {
                try {
                    current = stack.pop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class EllersGen {

        private final List<Cell> grid;
        private List<Cell> currentCol;

        private final DisjointSets disjointSet = new DisjointSets();

        private final int COLS = Math.floorDiv(maze_.WIDTH, maze_.W);
        private int fromIndex, toIndex;

        private boolean genNextCol = true;

        public EllersGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;

            fromIndex = 0;
            toIndex = COLS;

            for (int i = 0; i < grid.size(); i++) {
                grid.get(i).setId(i);
                disjointSet.create_set(grid.get(i).getId());
            }

            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (genNextCol) {
                        currentCol = grid.subList(fromIndex, toIndex);
                        fromIndex = toIndex;
                        toIndex += COLS;
                        new ColumnGen(currentCol, panel);
                    } else if (grid.parallelStream().allMatch(c -> c.isVisited())) {
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                }
            });
            timer.start();
        }

        private class ColumnGen {

            private final Queue<Cell> carveDownQueue = new LinkedList<Cell>();
            private final Queue<Cell> carveRightQueue = new LinkedList<Cell>();
            private final List<Cell> col;
            private final Random r = new Random();
            private Cell current;

            private ColumnGen(List<Cell> col, MazeGridPanel panel) {
                genNextCol = false;
                this.col = col;
                carveDownQueue.addAll(col);
                carveRightQueue.addAll(col);

                final Timer timer = new Timer(maze_.speed, null);
                timer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!carveDownQueue.isEmpty()) {
                            carveDown();
                        } else if (!carveRightQueue.isEmpty()) {
                            carveRight();
                        } else {
                            current = null;
                            genNextCol = true;
                            timer.stop();
                        }
                        panel.setCurrent(current);
                        timer.setDelay(maze_.speed);
                    }
                });
                timer.start();
            }

            private void carveDown() {
                current = carveDownQueue.poll();
                current.setVisited(true);

                if (r.nextBoolean() || col.contains(grid.get(grid.size() - 1))) { // or last column
                    Cell bottom = current.getBottomNeighbour(grid);
                    if (bottom != null) {
                        if (disjointSet.find_set(current.getId()) != disjointSet.find_set(bottom.getId())) {
                            current.removeWalls(bottom);
                            disjointSet.union(current.getId(), bottom.getId());
                        }
                    }
                }
            }

            private void carveRight() {
                Cell c = carveRightQueue.poll();

                List<Cell> cells = new ArrayList<Cell>();
                for (Cell c2 : col) {
                    if (disjointSet.find_set(c.getId()) == disjointSet.find_set(c2.getId())) {
                        cells.add(c2);
                    }
                }
                Collections.shuffle(cells);
                Cell c3 = cells.get(0);
                Cell right = c3.getRightNeighbour(grid);
                if (right != null) {
                    current = right;
                    right.setVisited(true);
                    c3.removeWalls(right);
                    disjointSet.union(c3.getId(), right.getId());
                }
            }
        }
    }

    public class GrowingForestGen {

        private final List<Cell> grid;
        private final List<Cell> active = new ArrayList<Cell>();
        private final DisjointSets disjointSet = new DisjointSets();

        private Cell current;
        private final Random r = new Random();

        public GrowingForestGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            for (int i = 0; i < grid.size(); i++) {
                grid.get(i).setId(i);
                disjointSet.create_set(grid.get(i).getId());
            }

            current = grid.get(r.nextInt(grid.size()));
            active.add(current);

            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!active.isEmpty()) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            boolean done = true;
            current.setVisited(true);

            List<Cell> neighs = current.getAllNeighbours(grid);

            for (Cell n : neighs) {
                if (disjointSet.find_set(current.getId()) != disjointSet.find_set(n.getId())) {
                    current.removeWalls(n);
                    disjointSet.union(current.getId(), n.getId());
                    done = false;
                    active.add(n);
                    break;
                }
            }

            if (done) active.remove(current);

            List<Cell> unvisited = grid.parallelStream().filter(c -> !c.isVisited()).collect(Collectors.toList());

            // 2% chance to generate a new tree
            if (!unvisited.isEmpty() && r.nextInt(101) > 98) {
                active.add(unvisited.get(r.nextInt(unvisited.size())));
            }

            if (!active.isEmpty()) current = active.get(r.nextInt(active.size()));
        }
    }

    public class GrowingTreeGen {

        private final List<Cell> grid;
        private Cell current;
        private final List<Cell> cells = new ArrayList<Cell>();
        private final Random r = new Random();

        public GrowingTreeGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            current = grid.get(r.nextInt(grid.size()));
            cells.add(current);
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            current.setVisited(true);
            Cell next = current.getUnvisitedNeighbour(grid);
            if (next != null) {
                current.removeWalls(next);
                current = next;
                cells.add(current);
            } else {
                cells.remove(current);
                if (!cells.isEmpty()) {
                    current = cells.get(r.nextInt(cells.size())); // get random cell
                }
            }
        }
    }

    public class HoustonGen {

        private final Stack<Cell> stack = new Stack<Cell>();
        private final List<Cell> grid;
        private List<Cell> visited = new ArrayList<Cell>();
        private Cell current;
        private final Random r = new Random();

        public HoustonGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            current = grid.get(0);
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            if (visited.size() <= grid.size() / 3) {
                visited = grid.parallelStream().filter(c -> c.isVisited()).collect(Collectors.toList());
                aldousBroder();
            } else {
                wilson();
            }
        }

        private void aldousBroder() {
            current.setVisited(true);
            List<Cell> neighs = current.getAllNeighbours(grid);
            Cell next = neighs.get(r.nextInt(neighs.size()));
            if (!next.isVisited()) {
                current.removeWalls(next);
            }
            current = next;
        }

        private void wilson() {
            if (current.isVisited()) {
                addPathToMaze();
                List<Cell> notInMaze = grid.parallelStream().filter(c -> !c.isVisited()).collect(Collectors.toList());
                if (!notInMaze.isEmpty()) {
                    current = notInMaze.get(r.nextInt(notInMaze.size()));
                } else {
                    return;
                }
            }
            current.setPath(true);
            Cell next = current.getNonPathNeighbour(grid);
            if (next != null) {
                stack.push(current);
                current.removeWalls(next);
                current = next;
            } else if (!stack.isEmpty()) {
                try {
                    current = stack.pop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void addPathToMaze() {
            grid.parallelStream().filter(c -> c.isPath()).forEach(c -> {
                c.setVisited(true);
                c.setPath(false);
            });
            stack.clear();
        }
    }

    public class HuntAndKillGen {

        private final List<Cell> grid;
        private Cell current;

        public HuntAndKillGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            current = grid.get(0);
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            current.setVisited(true);
            Cell next = current.getUnvisitedNeighbour(grid);
            if (next != null) {
                current.removeWalls(next);
                current = next;
            } else {
                // hunt
                Optional<Cell> opt = grid.parallelStream().filter(c -> c.isVisited() && c.getUnvisitedNeighboursList(grid).size() > 0)
                        .findAny();
                if (opt.isPresent()) {
                    current = opt.get();
                }
            }
        }
    }

    public class KruskalsGen {

        private final Stack<Cell> stack = new Stack<Cell>();
        private final DisjointSets disjointSet = new DisjointSets();
        private final List<Cell> grid;
        private Cell current;

        public KruskalsGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            current = grid.get(0);

            for (int i = 0; i < grid.size(); i++) {
                grid.get(i).setId(i);
                disjointSet.create_set(grid.get(i).getId());
            }

            stack.addAll(grid);

            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            current = stack.pop();
            current.setVisited(true);

            List<Cell> neighs = current.getAllNeighbours(grid);

            for (Cell n : neighs) {
                if (disjointSet.find_set(current.getId()) != disjointSet.find_set(n.getId())) {
                    current.removeWalls(n);
                    disjointSet.union(current.getId(), n.getId());
                }
            }

            Collections.shuffle(stack);
        }
    }

    public class PrimsGen {

        private final List<Cell> grid;
        private final List<Cell> frontier = new ArrayList<Cell>();
        private Cell current;

        public PrimsGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            current = grid.get(0);
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            current.setVisited(true);

            List<Cell> neighs = current.getUnvisitedNeighboursList(grid);
            frontier.addAll(neighs);
            Collections.shuffle(frontier);

            current = frontier.get(0);

            List<Cell> inNeighs = current.getAllNeighbours(grid);
            inNeighs.removeIf(c -> !c.isVisited());

            if (!inNeighs.isEmpty()) {
                Collections.shuffle(inNeighs);
                current.removeWalls(inNeighs.get(0));
            }

            frontier.removeIf(c -> c.isVisited());
        }
    }

    public class QuadDFSGen {

        private final List<Cell> grid;

        private final List<Cell> currentCells = new ArrayList<Cell>(4);
        private final List<Stack<Cell>> stacks = new ArrayList<Stack<Cell>>(4);
        private final List<List<Cell>> grids = new ArrayList<List<Cell>>(4);
        private final Random r = new Random();

        public QuadDFSGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            currentCells.add(grid.get(0));
            currentCells.add(grid.get(grid.size() - 1));
            currentCells.add(grid.get(r.nextInt(grid.size())));
            currentCells.add(grid.get(r.nextInt(grid.size())));

            for (int i = 0; i < 4; i++) {
                stacks.add(new Stack<Cell>());
                grids.add(new ArrayList<Cell>());
            }

            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        createPath();
                        currentCells.clear();
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrentCells(currentCells);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            for(int i = 0; i < currentCells.size(); i++) {
                Cell current = currentCells.get(i);
                if (current != null) {
                    Stack<Cell> myStack = stacks.get(i);
                    List<Cell> myGrid = grids.get(i);
                    current.setVisited(true);
                    Cell next = current.getUnvisitedNeighbour(grid);
                    if (next != null) {
                        myStack.push(current);
                        myGrid.add(current);
                        current.removeWalls(next);
                        current = next;
                    } else if (!myStack.isEmpty()) {
                        try {
                            current = myStack.pop();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        current = null;
                    }
                    currentCells.set(i, current);
                }
            }
        }

        // one and two MUST be connected, otherwise no path from start to end.
        private void createPath() {
            DisjointSets disjointSet = new DisjointSets();

            for (int i = 0; i < grids.size(); i++) {
                final int id = i;
                grids.get(i).forEach(c -> c.setId(id));
                disjointSet.create_set(i);
            }

            for (Cell c : grid) {
                if (disjointSet.getNumberofDisjointSets() == 1) break; // break out if all cells in one set.
                List<Cell> neighs = c.getAllNeighbours(grid);
                for (Cell n : neighs) {
                    if (disjointSet.find_set(c.getId()) != disjointSet.find_set(n.getId())) {
                        c.removeWalls(n);
                        disjointSet.union(c.getId(), n.getId());
                    }
                }
            }

        }
    }

    public class SidewinderGen {

        private final List<Cell> grid;
        private final List<Cell> run = new ArrayList<Cell>();
        private Cell current;
        private int index;
        private final Random r = new Random();

        public SidewinderGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            index = 0;
            current = grid.get(index);
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            current.setVisited(true);

            Cell bottom = current.getBottomNeighbour(grid);
            Cell left = current.getLeftNeighbour(grid);

            if (left == null) {
                if (bottom != null) {
                    current.removeWalls(bottom);
                }
            } else {
                run.add(current);
                if (bottom != null && r.nextBoolean()) {
                    current.removeWalls(bottom);
                } else {
                    current = run.get(r.nextInt(run.size()));
                    left = current.getLeftNeighbour(grid);
                    if (left != null) {
                        current.removeWalls(left);
                    }
                    run.clear();
                }
            }

            if (grid.size() - 1 >= index + 1) {
                current = grid.get(++index);
            }
        }
    }

    public class SpiralBacktrackerGen {

        private static final int STEP_COUNT = 4; // change this to generate different mazes.

        private final Stack<Cell> stack = new Stack<Cell>();
        private final List<Cell> grid;
        private final Random r = new Random();

        private int direction;
        private Cell current;
        private int count = STEP_COUNT; // so we can pick direction in carve().

        public SpiralBacktrackerGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            current = grid.get(0);
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            current.setVisited(true);

            List<Cell> neighs = current.getUnvisitedNeighboursList(grid);

            if (neighs.isEmpty()) {
                current = stack.pop();
                return;
            }

            if (count < STEP_COUNT) {
                Cell next = getNeighbour();
                if (next != null) {
                    if (!next.isVisited()) {
                        stack.push(current);
                        current.removeWalls(next);
                        current = next;
                    }

                } else {
                    count = STEP_COUNT + 1;
                }

                count++;
            } else {
                count = 0; // reset count
                List<Integer> directions = new ArrayList<Integer>();
                // get valid directions
                if(neighs.contains(current.getTopNeighbour(grid))) {
                    directions.add(0);
                }

                if(neighs.contains(current.getRightNeighbour(grid))) {
                    directions.add(1);
                }

                if(neighs.contains(current.getBottomNeighbour(grid))) {
                    directions.add(2);
                }

                if(neighs.contains(current.getLeftNeighbour(grid))) {
                    directions.add(3);
                }


                int prevDir = direction;
                // optional, this prevents the algorithm going in the same direction multiple times.
                if (neighs.size() > 1) {
                    while (direction == prevDir) direction = directions.get(r.nextInt(directions.size()));
                } else {
                    // can just do this line and go in the same direction multiple times.
                    direction = directions.get(r.nextInt(directions.size()));
                }
            }
        }

        private Cell getNeighbour() {
            switch (direction) {
                case 0:
                    return current.getTopNeighbour(grid);
                case 1:
                    return current.getRightNeighbour(grid);
                case 2:
                    return current.getBottomNeighbour(grid);
                case 3:
                    return current.getLeftNeighbour(grid);
                default:
                    return null;
            }
        }
    }

    public class WilsonsGen {

        private final List<Cell> grid;
        private final Stack<Cell> stack = new Stack<Cell>();
        private Cell current;
        private final Random r = new Random();

        public WilsonsGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            current = grid.get(r.nextInt(grid.size()));
            current.setVisited(true);
            current = grid.get(r.nextInt(grid.size()));
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void carve() {
            if (current.isVisited()) {
                addPathToMaze();
                // TODO: Minor future refinement:
                /* Do not need to run through whole maze with stream filter.
                 * Could maintain a list of all cells not in the maze from beginning and remove them
                 * from the list as we pop them off the stack in addPathToMaze(). Algorithm should still work as
                 * current will never be in maze_. When this list is empty we have carved the maze_.
                 */
                List<Cell> notInMaze = grid.parallelStream().filter(c -> !c.isVisited()).collect(Collectors.toList());
                if (!notInMaze.isEmpty()) {
                    current = notInMaze.get(r.nextInt(notInMaze.size()));
                } else {
                    return;
                }
            }
            current.setPath(true);
            Cell next = current.getNonPathNeighbour(grid);
            if (next != null) {
                stack.push(current);
                current.removeWalls(next);
                current = next;
            } else if (!stack.isEmpty()) {
                try {
                    current = stack.pop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void addPathToMaze() {
            grid.parallelStream().filter(c -> c.isPath()).forEach(c -> {
                c.setVisited(true);
                c.setPath(false);
            });
            stack.clear();
        }
    }

    public class ZigZagGen {

        private final Queue<Cell> queue;
        private Cell current, goal;
        private final List<Cell> grid;
        private List<Cell> notInMaze = new ArrayList<Cell>();
        private final Random r = new Random();

        public ZigZagGen(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            goal = grid.get(r.nextInt(grid.size()));
            goal.setVisited(true);

            queue = new PriorityQueue<Cell>(new CellDistanceFromGoalComparator());
            current = grid.get(r.nextInt(grid.size()));
            queue.offer(current);

            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                        carve();
                    } else {
                        current = null;
                        maze_.generated = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }


        private void carve() {
            Cell next = queue.poll();
            Cell prev = current;
            current.removeWalls(next);
            if (next.isVisited()) {
                goal = current;
                notInMaze = grid.parallelStream().filter(c -> !c.isVisited()).collect(Collectors.toList());
                current = notInMaze.get(r.nextInt(notInMaze.size()));
                queue.clear();
            } else {
                current = next;
            }

            current.setVisited(true);
            // we never enter carve again after here, need to check if it's last cell before and remove random wall.
            // could either check unvisited (notInMaze) size == 1 or check for 4 walls with all visited neighbours - but this situation may
            // happen under different circumstances. I think check size == 1 is best.
            if(notInMaze.size() == 1) {
                // last cell to add
                current.removeWalls(current.getAllNeighbours(grid).get(0));
            }

            // force zig zag pattern.
            if (prev == current.getBottomNeighbour(grid) || prev == current.getTopNeighbour(grid)) {
                Cell left = current.getLeftNeighbour(grid);
                Cell right = current.getRightNeighbour(grid);

                if (left != null) queue.offer(left);
                if (right != null) queue.offer(right);
            } else {
                Cell top = current.getTopNeighbour(grid);
                Cell bottom = current.getBottomNeighbour(grid);

                if (top != null) queue.offer(top);
                if (bottom != null) queue.offer(bottom);
            }
        }

        private class CellDistanceFromGoalComparator implements Comparator<Cell> {
            @Override
            public int compare(Cell arg0, Cell arg1) {
                if (getDistanceFromGoal(arg0) > getDistanceFromGoal(arg1)) {
                    return 1;
                } else {
                    return getDistanceFromGoal(arg0) < getDistanceFromGoal(arg1) ? -1 : 0;
                }
            }

            private double getDistanceFromGoal(Cell c) {
                return Math.hypot(c.getX() - goal.getX(), c.getY() - goal.getY());
            }
        }
    }

    // solver
    public class BFSSolve {

        private final Queue<Cell> queue = new LinkedList<Cell>();
        private Cell current;
        private final List<Cell> grid;

        public BFSSolve(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            current = grid.get(0);
            current.setDistance(0);
            queue.offer(current);
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!current.equals(grid.get(grid.size() - 1))) {
                        flood();
                    } else {
                        drawPath();
                        maze_.solved = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void flood() {
            current.setDeadEnd(true);
            current = queue.poll();
            List<Cell> adjacentCells = current.getValidMoveNeighbours(grid);
            for (Cell c : adjacentCells) {
                if (c.getDistance() == -1) {
                    c.setDistance(current.getDistance() + 1);
                    c.setParent(current);
                    queue.offer(c);
                }
            }
        }

        private void drawPath() {
            while (current != grid.get(0)) {
                current.setPath(true);
                current = current.getParent();
            }
        }
    }

    public class BiDFSSolve {

        private final Stack<Cell> path1 = new Stack<Cell>();
        private final Stack<Cell> path2 = new Stack<Cell>();
        private Cell current1, current2;
        private final List<Cell> grid;

        public BiDFSSolve(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            current1 = grid.get(0);
            current2 = grid.get(grid.size() - 1);
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!pathFound()) {
                        pathFromEnd();
                        pathFromStart();
                    } else {
                        current1 = null;
                        current2 = null;
                        maze_.solved = true;
                        maze_.running = false;
                        drawPath();
                        timer.stop();
                    }
                    panel.setCurrentCells(Arrays.asList(current1, current2));
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void pathFromStart() {
            current1.setDeadEnd(true);
            Cell next = current1.getPathNeighbour(grid);
            if (next != null) {
                path1.push(current1);
                current1 = next;
            } else if (!path1.isEmpty()) {
                try {
                    current1 = path1.pop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void pathFromEnd() {
            current2.setDeadEnd(true);
            Cell next = current2.getPathNeighbour(grid);
            if (next != null) {
                path2.push(current2);
                current2 = next;
            } else if (!path2.isEmpty()) {
                try {
                    current2 = path2.pop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean pathFound() {
            List<Cell> neighs1 = current1.getValidMoveNeighbours(grid);
            List<Cell> neighs2 = current2.getValidMoveNeighbours(grid);
            for (Cell c : neighs1) {
                if (path2.contains(c)) {
                    // path from beginning.
                    path1.push(current1);
                    path1.push(c);
                    joinPaths(c, path2, current2);
                    return true;
                }
            }
            for (Cell c : neighs2) {
                if (path1.contains(c)) {
                    // path from end.
                    path2.push(current2);
                    path2.push(c);
                    joinPaths(c, path1, current1);
                    return true;
                }
            }
            return false;
        }

        private void joinPaths(Cell c, Stack<Cell> path, Cell current) {
            while (!path.isEmpty() && !current.equals(c)) {
                try {
                    current = path.pop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            path1.addAll(path2);
        }


        private void drawPath() {
            while (!path1.isEmpty()) {
                try {
                    path1.pop().setPath(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class DFSSolve {

        private final Stack<Cell> path = new Stack<Cell>();
        private Cell current;
        private final List<Cell> grid;

        public DFSSolve(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            current = grid.get(0);
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!current.equals(grid.get(grid.size() - 1))) {
                        path();
                    } else {
                        drawPath();
                        maze_.solved = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void path() {
            current.setDeadEnd(true);
            Cell next = current.getPathNeighbour(grid);
            if (next != null) {
                path.push(current);
                current = next;
            } else if (!path.isEmpty()) {
                try {
                    current = path.pop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void drawPath() {
            while (!path.isEmpty()) {
                try {
                    path.pop().setPath(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class DijkstraSolve {

        private final Queue<Cell> queue;
        private Cell current;
        private final List<Cell> grid;

        public DijkstraSolve(List<Cell> grid, MazeGridPanel panel) {
            this.grid = grid;
            queue = new PriorityQueue<Cell>(new CellDistanceFromGoalComparator());
            current = grid.get(0);
            current.setDistance(0);
            queue.offer(current);
            final Timer timer = new Timer(maze_.speed, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!current.equals(grid.get(grid.size() - 1))) {
                        flood();
                    } else {
                        drawPath();
                        maze_.solved = true;
                        maze_.running = false;
                        timer.stop();
                    }
                    panel.setCurrent(current);
                    timer.setDelay(maze_.speed);
                }
            });
            timer.start();
        }

        private void flood() {
            current.setDeadEnd(true);
            current = queue.poll();
            List<Cell> adjacentCells = current.getValidMoveNeighbours(grid);
            for (Cell c : adjacentCells) {
                if (c.getDistance() == -1) {
                    c.setDistance(current.getDistance() + 1);
                    c.setParent(current);
                    queue.offer(c);
                }
            }
        }

        private void drawPath() {
            while (current != grid.get(0)) {
                current.setPath(true);
                current = current.getParent();
            }
        }

        private class CellDistanceFromGoalComparator implements Comparator<Cell> {
            Cell goal = grid.get(grid.size() - 1);

            @Override
            public int compare(Cell arg0, Cell arg1) {
                if (getDistanceFromGoal(arg0) > getDistanceFromGoal(arg1)) {
                    return 1;
                } else {
                    return getDistanceFromGoal(arg0) < getDistanceFromGoal(arg1) ? -1 : 0;
                }
            }

            private double getDistanceFromGoal(Cell c) {
                return Math.hypot(c.getX() - goal.getX(), c.getY() - goal.getY());
            }
        }
    }
}
