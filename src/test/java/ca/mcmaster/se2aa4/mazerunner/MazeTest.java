package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class MazeTest {

    @Test
    public void testMazeConstruction() {
        try {
            Maze maze = new Maze("src/test/resources/test.maz.txt");
            assertNotNull(maze.getMaze(), "Maze should not be null");
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

    @Test
    public void testStartAndEndPoints() {
        try {
            Maze maze = new Maze("src/test/resources/test.maz.txt");
            assertEquals(0, maze.getStartY(), "Start Y should be 0");
            assertEquals(10, maze.getEndY(), "End Y should be 10");
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

    @Test
    public void testGetters() {
        try {
            Maze maze = new Maze("src/test/resources/test.maz.txt");
            assertTrue(maze.getStartX() >= 0, "Start X should be non-negative");
            assertTrue(maze.getEndX() >= 0, "End X should be non-negative");
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }
    
}
