package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MVPExplorerTest{

    @Test
    public void testStepsTaken() throws IOException {
        Maze maze = new Maze("src/test/resources/test.maz.txt");
        MVPExplorer explorer = new MVPExplorer(maze);
        explorer.explore();
        assertTrue(explorer.getStepsTaken() == 10, "Steps should equal to 10");
    }


}
