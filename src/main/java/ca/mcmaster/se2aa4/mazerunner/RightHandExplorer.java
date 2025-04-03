package ca.mcmaster.se2aa4.mazerunner;

public class RightHandExplorer extends Explorer {

    public RightHandExplorer(Maze maze) {
        super(maze);
        int stepsTaken = 0;
        this.currentDirection = 1; // Start facing right (East)
    }

    @Override
    public void explore() {
        int currentX = maze.getStartX();
        int currentY = maze.getStartY();

        // Loop until the explorer has arrived at the exit
        while (!isAtExit(currentX, currentY )) {
            // Check if we can move forward
            if (checkRightWall(currentX, currentY)) {//if right side is a wall
                // If explorer can move, then move
                if (isValidMove(currentX + DX[currentDirection], currentY + DY[currentDirection])) {
                    currentX += DX[currentDirection];
                    currentY += DY[currentDirection];
                    path.add("F"); // Add "F" for forward
                    stepsTaken++;
                } else {
                    // If forward is a wall
                    path.add("L"); // Add "R" for turn right
                    currentDirection = (currentDirection +3) % 4; // Turn left
                    stepsTaken++;
                }
            } else {
                // Turn right and move forward
                path.add("R"); // Add "R" for turn right
                currentDirection = (currentDirection + 1) % 4; // Turn right
                stepsTaken++;
                currentX += DX[currentDirection];
                currentY += DY[currentDirection];
                path.add("F");
                stepsTaken++;

            }
        }
    }

    public boolean checkRightWall(int currentX, int currentY) {
        // Determine the direction to the right of the current direction
        int rightDirection = (currentDirection + 1) % 4; // Right side direction
        int rightX = currentX + DX[rightDirection];
        int rightY = currentY + DY[rightDirection];

        // Check bounds
        if (rightX < 0 || rightY < 0 || rightX >= maze.getMaze().length || rightY >= maze.getMaze()[0].length) {
            return true; // Out of bounds is treated as a wall
        }

        // Check if the right-hand side is a wall
        return maze.getMaze()[rightX][rightY] == '#';
    }
}
