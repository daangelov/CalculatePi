package com.pi;

public class Main {

    public static void main(String[] args) {

        // Process the command line arguments
        CommandLineProcessor cmdProcessor = new CommandLineProcessor();
        cmdProcessor.buildOptions();
        cmdProcessor.parseOptions(args);
        cmdProcessor.validateOptions();

        // Get the values from command line
        int precision = cmdProcessor.getOptionPrecision();
        int threads = cmdProcessor.getOptionThreads();
        boolean isQuiet = cmdProcessor.getOptionQuiet();
        String outputFileName = cmdProcessor.getOptionOutput();

        CalculatePiApp calculatePi = new CalculatePiApp(precision, threads, isQuiet, outputFileName);

        calculatePi.run();
    }
}