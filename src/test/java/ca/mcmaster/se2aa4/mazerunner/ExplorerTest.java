package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ExplorerTest {

    // Mock Explorer for testing abstract class
    private class MockExplorer extends Explorer {
        public MockExplorer(Maze maze) {
            super(maze);
        }

        @Override
        public void explore() {
            // Do nothing for mock implementation
        }
    }


    @Test
    public void testValidMove() throws IOException {
        Maze maze = new Maze("src/test/resources/straight.maz.txt");
        Explorer explorer = new MockExplorer(maze);

        List<String> path = new ArrayList<>();
        path.add("4F");

        boolean result = explorer.move(path);
        assertTrue(result, "Should be able to move to the exit");
    }

    @Test
    public void testInvalidMove() throws IOException {
        Maze maze = new Maze("src/test/resources/straight.maz.txt");
        Explorer explorer = new MockExplorer(maze);

        List<String> path = new ArrayList<>();
        path.add("F"); 
        path.add("R");
        path.add("F");// Move into a wall

        boolean result = explorer.move(path);
        assertFalse(result, "Should not be able to move through a wall");
    }

    @Test
    public void testIsAtExit() throws IOException{
        Maze maze = new Maze("src/test/resources/straight.maz.txt");
        Explorer explorer = new MockExplorer(maze);
        int validX = maze.getEndX();
        int validY = maze.getEndY();
        int invalidX = validX - 1;  
        int invalidY = validY - 1;
        assertTrue(explorer.isAtExit(validX, validY), "Should return true when at the exit");
        assertFalse(explorer.isAtExit(invalidX, invalidY), "Should return false when not at the exit");
    }

    @Test
    public void testPathCompression() throws IOException {
        Maze maze = new Maze("src/test/resources/straight.maz.txt");
        Explorer explorer = new MockExplorer(maze);

        explorer.path.add("F");
        explorer.path.add("F");
        explorer.path.add("R");
        explorer.path.add("R");
        explorer.path.add("L");

        List<String> compressedPath = explorer.getPath();
        assertEquals(3, compressedPath.size(), "Compressed path should contain 3 elements");
        assertEquals("2F", compressedPath.get(0), "First compressed element should be 2F");
        assertEquals("2R", compressedPath.get(1), "Second compressed element should be 2R");
        assertEquals("L", compressedPath.get(2), "Third compressed element should be L");
    }



}
