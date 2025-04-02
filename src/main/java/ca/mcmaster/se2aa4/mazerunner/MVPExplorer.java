package ca.mcmaster.se2aa4.mazerunner;

public class MVPExplorer extends Explorer {
    private int stepsTaken = 0;

    public MVPExplorer(Maze maze) {
        super(maze);
        this.currentDirection = 1; // Start facing right (East)
    }
    
    public int getStepsTaken() {
        return stepsTaken;
      }
    

    @Override
    public void explore() {
        int currentX = maze.getStartX();
        int currentY = maze.getStartY();

        // Loop until the explorer has taken 10 steps
        while (stepsTaken < 10 && !isAtExit(currentX, currentY)) {
            if (isValidMove(currentX + DX[currentDirection], currentY + DY[currentDirection])) {
                // Move forward
                currentX += DX[currentDirection];
                currentY += DY[currentDirection];
                path.add("F"); // Add "F" for forward
                stepsTaken++;
            } else {
                // If it's a wall, turn right (i.e., change direction clockwise)
                path.add("R"); // Add "R" for turn right
                currentDirection = (currentDirection + 1) % 4; // Turn right (clockwise)
            }
        }
    }
}
