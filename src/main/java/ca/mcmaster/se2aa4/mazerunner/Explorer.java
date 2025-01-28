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
