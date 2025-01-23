package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public abstract class Explorer {
    protected Maze maze;  
    protected List<String> path; 

    public Explorer(Maze maze) {
        this.maze = maze;
        this.path = new ArrayList<>();
    }

    public abstract void explore();

    public List<String> getPath() {
        return path;
    }
}
