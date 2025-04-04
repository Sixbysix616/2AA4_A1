package ca.mcmaster.se2aa4.mazerunner;

public class ExplorerFactory {
    public static Explorer createExplorer(String type, Maze maze) {
        if ("RightHand".equals(type)) {
            return new RightHandExplorer(maze);
        } else if ("MVP".equals(type)) {
            return new MVPExplorer(maze);
        } else {
            throw new IllegalArgumentException("Unknown explorer type: " + type);
        }
    }
}
