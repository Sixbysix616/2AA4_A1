package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;
import java.util.List;

public abstract class Explorer {
    protected Maze maze;
    protected int currentDirection; // Current facing direction (0 = Up, 1 = Right, 2 = Down, 3 = Left)
    protected int stepsTaken; // Count of steps taken
    
    // Directions: 0 = Up, 1 = Right, 2 = Down, 3 = Left
    protected static final int[] DX = {-1, 0, 1, 0}; // Y-axis movement (Up, Right, Down, Left)
    protected static final int[] DY = {0, 1, 0, -1}; // X-axis movement (Up, Right, Down, Left)

    // The path taken by the explorer (for printing purposes)
    protected List<String> path;

//This part is added Observer design pattern
    private final List<ExplorerObserver> observers = new ArrayList<>();


    public void addObserver(ExplorerObserver observer) {
        observers.add(observer);
    }


    protected void notifyObservers(String action, int x, int y) {
    for (ExplorerObserver observer : observers) {
        observer.onStep(action, x, y);
        }
    }

    // Constructor
    public Explorer(Maze maze) {
        this.maze = maze;
        this.currentDirection = 1; // Start facing Right (East)
        this.stepsTaken = 0;
        this.path = new ArrayList<>();
    }

    // Abstract method to explore the maze
    public abstract void explore();

    // Utility method to check if a move is valid
    protected boolean isValidMove(int x, int y) {
        return x >= 0 && y >= 0 && x < maze.getMaze().length && y < maze.getMaze()[0].length && maze.getMaze()[x][y] == ' ';
    }

    protected boolean isAtExit(int x, int y) {
        return x == maze.getEndX() && y == maze.getEndY();
    }

    public boolean move(List<String> path){
        int currentX = maze.getStartX();
        int currentY = maze.getStartY();
        int currentDirection = 1;// Start facing right (East)

            for (String instruction : path) {
                
              // Check if the instruction involves a number (e.g., "5F", "2F")
                if (instruction.matches("\\d+[FLR]")) {
                    int repeatCount = Integer.parseInt(instruction.replaceAll("[^0-9]", ""));
                    char direction = instruction.charAt(instruction.length() - 1);
            
            // Repeat the movement based on the number
                    for (int i = 0; i < repeatCount; i++) {
                        if (direction == 'F') {
                             int nextX = currentX + DX[currentDirection];
                             int nextY = currentY + DY[currentDirection];
                              // Move forward
                            if (isValidMove(nextX, nextY)) {
                                currentX = nextX;
                                currentY = nextY;  
                                notifyObservers("F", currentX, currentY);
                            }else { 
                                return false; // Invalid move (blocked path)
                            }
                }       else if (direction == 'R') {
                    // Turn right
                           currentDirection = (currentDirection + 1) % 4;
                           notifyObservers("R", currentX, currentY);
                }       else if (direction == 'L') {
                    // Turn left
                            currentDirection = (currentDirection + 3) % 4;
                            notifyObservers("L", currentX, currentY);
                }
                
                // After each movement, check if at exit
                if (isAtExit(currentX, currentY)) {
                    return true;
                }
            }
        } else {
            // Single step instruction like "R", "L", or "F"
            if (instruction.equals("F")) {
                if (isValidMove(currentX + DX[currentDirection], currentY + DY[currentDirection])) {
                    currentX += DX[currentDirection];
                    currentY += DY[currentDirection];


                } else {
                    return false; // Invalid move (blocked path)
                }
            } else if (instruction.equals("R")) {
                // Turn right
                currentDirection = (currentDirection + 1) % 4;
            } else if (instruction.equals("L")) {
                // Turn left
                currentDirection = (currentDirection +3) % 4;
            }

            // After each movement, check if at exit
            if (isAtExit(currentX, currentY)) {
                return true;
            }
        }
    }
        return isAtExit(currentX, currentY);

    }

    // Get the path taken by the explorer
    public List<String> getPath() {
        List<String> compressedPath = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < path.size(); i++) {
            // If the current step is the same as the previous one
            if (path.get(i).equals(path.get(i - 1))) {
                count++;
            } else {
                // If the direction changes, add the previous count and direction
                if (count > 1) {
                    compressedPath.add(count + path.get(i - 1));  // Add count + direction
                } else {
                    compressedPath.add(path.get(i - 1));  // Add just the direction
                }
                count = 1;  // Reset the count for the new direction
            }
        }

        if (count > 1) {
            compressedPath.add(count + path.get(path.size() - 1));
        } else {
            compressedPath.add(path.get(path.size() - 1));
        }

        return compressedPath;
    }

    public int getSteps(){
        return stepsTaken;
    }
}
