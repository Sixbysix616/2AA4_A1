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
            logger.error("Error parsing command-line arguments: " + e.getMessage());
            System.exit(1);
        }

        // Check if the -i flag is provided
        if (cmd.hasOption("i")) {
            String mazeFile = cmd.getOptionValue("i");
            try {
                logger.info("**** Reading the maze from file " + mazeFile);
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
            } catch (Exception e) {
                logger.error("/!\\ An error has occurred while reading the maze file /!\\");
            }
        } else {
            logger.error("No input maze file specified. Please provide one using the -i flag.");
            System.exit(1);
        }

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
