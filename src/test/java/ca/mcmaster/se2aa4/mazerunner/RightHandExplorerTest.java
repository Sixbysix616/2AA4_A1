package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RightHandExplorerTest {
    private Maze maze;
    private RightHandExplorer explorer;

    @BeforeEach
    public void setUp() throws IOException {
        maze = new Maze("src/test/resources/test.maz.txt"); 
        explorer = new RightHandExplorer(maze);
    }

    @Test
    public void testCheckRightWall() {

        int startX = maze.getStartX();
        int startY = maze.getStartY();
        
 
        assertTrue(explorer.checkRightWall(startX, startY), "Right side should be a wall");
    }

    @Test
    public void testExplore() {
        explorer.explore();
        
        assertEquals(maze.getEndX(), explorer.maze.getEndX(), "Explorer should reach the exit X coordinate");
        assertEquals(maze.getEndY(), explorer.maze.getEndY(), "Explorer should reach the exit Y coordinate");
        
 
        assertFalse(explorer.getPath().isEmpty(), "Path should not be empty after exploration");
    }
}
