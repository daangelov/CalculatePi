package com.pi;

import org.apache.commons.cli.*;

class CommandLineProcessor {

    private Options options = new Options();

    private CommandLine cmd;

    private CommandLineParser parser = new DefaultParser();

    /**
     * Build the structure for the command line options
     */
    void buildOptions() {
        Option precision = Option.builder("p")
                .argName("precision")
                .desc("Indicates the number of members for the calculation.")
                .hasArg()
                .required()
                .build();

        Option threads = Option.builder("t")
                .argName("threads")
                .desc("Indicates the number of threads to use for the calculation.")
                .hasArg()
                .required()
                .build();

        Option output = Option.builder("o")
                .argName("output")
                .desc("Indicates where the calculation will be output")
                .hasArg()
                .build();

        Option quiet = Option.builder("q")
                .argName("quiet")
                .hasArg()
                .optionalArg(true)
                .desc("Run program in \"quiet\" mode")
                .build();

        this.options.addOption(precision);
        this.options.addOption(threads);
        this.options.addOption(output);
        this.options.addOption(quiet);
    }

    /**
     * Parse the command line arguments and return object with values
     */
    void parseOptions(String[] args) {

        try {
            this.cmd = this.parser.parse(this.options, args);
        } catch (ParseException e) {
            printError(e.getMessage());
            printHelp();
            System.exit(-1);
        }
    }

    /**
     * Check if option is valid
     *
     * @param option option name
     * @return boolean
     */
    private boolean isInteger(String option) {
        try {
            Integer.parseInt(this.cmd.getOptionValue(option));
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    void validateOptions() {
        if (!isInteger("p") || !isInteger("t")) {

            if (!isInteger("p") && !isInteger("t")) {
                printError("Wrong format for arguments p and t. (must be integers)");
            } else if (!isInteger("p")) {
                printError("Wrong format for argument p. (must be integer)");
            } else if (!isInteger("t")) {
                printError("Wrong format for argument t. (must be integer)");
            }

            printHelp();
            System.exit(-1);
        }
    }

    /**
     * Get the value of option precision
     *
     * @return string value
     */
    int getOptionPrecision() {
        int precision = Integer.parseInt(this.cmd.getOptionValue("p"));
        if (precision <= 0) {
            return 1;
        }
        return precision;
    }

    int getOptionThreads() {
        int precision = Integer.parseInt(this.cmd.getOptionValue("p"));
        int threads = Integer.parseInt(this.cmd.getOptionValue("t"));

        if (precision < threads) {
            return precision;
        }
        if (threads <= 0) {
            return 1;
        }
        return threads;
    }

    boolean getOptionQuiet() {
        return this.cmd.hasOption("q");
    }

    String getOptionOutput() {
        String outputFileName = this.cmd.getOptionValue("o");

        if (outputFileName == null) {
            return Constants.DEFAULT_OUTPUT_FILE;
        }
        return outputFileName;
    }

    /**
     * @param message error text
     */
    private void printError(String message) {
        System.err.println(message);
    }

    /**
     * Print message with usage
     */
    private void printHelp() {

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("CalculatePi", this.options, true);
    }
}