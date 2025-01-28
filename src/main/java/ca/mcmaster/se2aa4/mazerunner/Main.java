package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

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
        logger.info("** Starting Maze Runner");

        // Set up the command line options
        Options options = new Options();
        options.addOption("i", true, "Input maze file");

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
        if (cmd.hasOption("i")) {
            String mazeFile = cmd.getOptionValue("i");
            try {
                logger.info("**** Reading the maze from file " + mazeFile);
                logger.info("**** right hand explorer test v0.5");
                BufferedReader reader = new BufferedReader(new FileReader(mazeFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }

                // Create Maze instance from the maze file string
                Maze maze = new Maze(mazeFile);

                // Create an MVPExplorer to explore the maze
                Explorer explorer = new RightHandExplorer(maze);
                explorer.explore();  // Start the exploration
                logger.info("**** Computing path");
                logger.info("Path: " + String.join(" ", explorer.getPath()));
                logger.info("Steps:"+ explorer.getSteps());
        //logger.info("PATH NOT COMPUTED");
            } catch (Exception e) {
                Throwable cause = e.getCause();
                cause.printStackTrace();
                logger.error("An error has occurred while reading the maze file: " + e.getMessage());
            }
        } else {
            // Log an error if the -i flag is not provided
            logger.error("No input maze file specified. Please provide one using the -i flag.");
            System.exit(1);
        }


        logger.info("** End of MazeRunner");
    }
}
