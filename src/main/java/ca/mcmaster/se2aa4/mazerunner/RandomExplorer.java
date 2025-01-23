package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomExplorer extends Explorer {

    // Direction constants
    private static final int[] DX = {-1, 0, 1, 0}; // UP, RIGHT, DOWN, LEFT
    private static final int[] DY = {0, 1, 0, -1};

    private int currentDirection; // Current facing direction (0 = UP, 1 = RIGHT, 2 = DOWN, 3 = LEFT)
    private Set<String> visited;  // Set to track visited positions

    public RandomExplorer(Maze maze) {
        super(maze);
        this.currentDirection = 1; // Start facing right (East)
        this.visited = new HashSet<>(); // Initialize the visited set
    }

    @Override
    public void explore() {
        Random rand = new Random();
        int currentX = maze.getStartX(), currentY = maze.getStartY();
        
        // Add the starting point to visited set
        visited.add(currentX + "," + currentY);

        // Random exploration until the explorer reaches the end
        while (currentX != maze.getEndX() || currentY != maze.getEndY()) {
            List<int[]> directions = new ArrayList<>();
            
            // Check possible directions based on current facing direction
            if (isValidMove(currentX + DX[currentDirection], currentY + DY[currentDirection])) {
                directions.add(new int[]{currentX + DX[currentDirection], currentY + DY[currentDirection]});
            }

            // Try to turn right or left in place (without moving)
            boolean moved = false;
            for (int i = 0; i < 4; i++) {
                if (i != currentDirection) {  // Skip the current direction
                    int newDirection = (currentDirection + i) % 4; // Turning left or right
                    // Check if turning in this direction leads to a valid move
                    int[] nextMove = {currentX + DX[newDirection], currentY + DY[newDirection]};
                    if (isValidMove(nextMove[0], nextMove[1]) && !visited.contains(nextMove[0] + "," + nextMove[1])) {
                        // Add the appropriate turn command (L or R)
                        if (i == 1) { // Right turn
                            path.add("R");
                        } else if (i == 3) { // Left turn
                            path.add("L");
                        }
                        currentDirection = newDirection; // Update the current direction
                        path.add("F"); // Move forward after turning (even though no actual movement occurs)
                        visited.add(currentX + "," + currentY); // Mark this cell as visited
                        moved = true;
                        break;
                    }
                }
            }

            // If no valid direction found (stuck), break the loop (this can be improved for backtracking)
            if (!moved) {
                break;
            }
        }
    }
}
