package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private char[][] maze; 
    private int startX, startY;  
    private int endX, endY; 

    public Maze(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        
        
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        
     
        maze = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            maze[i] = lines.get(i).toCharArray();
        }
        
        findStartAndEnd();
    }

    private void findStartAndEnd() {

        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == ' ') {  // 起点
                startX = i;
                startY = 0;
            }
            if (maze[i][maze[0].length - 1] == ' ') {  
                endX = i;
                endY = maze[0].length - 1;
            }
        }
    }

    public char[][] getMaze() {
        return maze;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
}
