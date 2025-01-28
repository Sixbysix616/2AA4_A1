package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        // Replaced System.out.println with logger at the appropriate level
        logger.info("## Starting Maze Runner");

        // Set up the command line options
        Options options = new Options();
        options.addOption("i", true, "Input maze file");
        options.addOption("p",true,"Input path");


        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        
        try {
            // Parse the command-line arguments
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            // Log the error if argument parsing fails
            logger.error("Error parsing command-line arguments: " + e.getMessage());
            System.exit(1);
        }

        // Check if the -i flag is provided
        if (cmd.hasOption("i") && cmd.getOptions().length == 1) {
            String mazeFile = cmd.getOptionValue("i");
            try {
                logger.info("**** Reading the maze from file " + mazeFile);
                logger.info("**** right hand explorer test v0.17");
                BufferedReader reader = new BufferedReader(new FileReader(mazeFile));
                StringBuilder modifiedContent = new StringBuilder();
                
                String line = reader.readLine();
                int width = line.length();  
                
                for (int j = 0; j < width; j++){
                    char currentChar = line.charAt(j);
                        if (currentChar == '#') {   
                            System.out.print("WALL ");
                        }else if (currentChar == ' ') {
                          System.out.print("PASS ");
                        }

                }
                System.out.print(System.lineSeparator());
                
                modifiedContent.append(line).append(System.lineSeparator());


                while ((line = reader.readLine()) != null){
                    if (line.length()< width){
                        for (int i = line.length(); i< width; i++){
                            line += " ";
                        }
                    }
                    for (int idx = 0; idx<line.length(); idx++) {
                        char currentChar = line.charAt(idx);
                        if (currentChar == '#') {   
                            System.out.print("WALL ");
                        }else if (currentChar == ' ') {
                          System.out.print("PASS ");
                        }

                    } 
                    System.out.print(System.lineSeparator());
                    modifiedContent.append(line).append(System.lineSeparator());
               
            }   
            
                BufferedWriter writer = new BufferedWriter(new FileWriter(mazeFile));
                writer.write(modifiedContent.toString());
                writer.close();

                // Create Maze instance from the maze file string
                Maze maze = new Maze(mazeFile);

                // Create an MVPExplorer to explore the maze
                Explorer explorer = new RightHandExplorer(maze);
                explorer.explore();  // Start the exploration
                logger.info("**** Computing path");
                logger.info("Path: " + String.join(" ", explorer.getPath()));
                logger.info("Steps:"+ explorer.getSteps());
        //logger.info("PATH NOT COMPUTED");
            }catch (Exception e) {
                logger.error("An error has occurred while reading the maze file: " + e.getMessage());
            }
        } else if (!cmd.hasOption("i")){
            // Log an error if the -i flag is not provided
            logger.error("No input maze file specified. Please provide one using the -i flag.");
            System.exit(1);
        }

        if (cmd.hasOption("i") && cmd.hasOption("p")){
            String mazeFile = cmd.getOptionValue("i");
            String path = cmd.getOptionValue("p");
            String[] pathArray = path.split(" ");
            List<String> pathList = new ArrayList<>(Arrays.asList(pathArray));

            logger.info("Path checking mode");
            try{
                logger.info("**** Reading the maze from file " + mazeFile);
                logger.info("**** check path test v0.4");               
                Maze maze = new Maze(mazeFile);
                Explorer explorer = new RightHandExplorer(maze);
                if(explorer.move(pathList)){
                    logger.info("correct path");
                }else{
                    logger.info("incorrect path");
                }

            }catch(Exception e){
                logger.error("An error has occurred while reading the maze file: " + e.getMessage());
            }



        }


        logger.info("** End of MazeRunner");
    }
}
