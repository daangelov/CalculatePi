package com.pi;

public class Main {

    public static void main(String[] args) {

        // Process the command line arguments
        CommandLineProcessor cmdProcessor = new CommandLineProcessor();
        cmdProcessor.buildOptions();
        cmdProcessor.parseOptions(args);
        cmdProcessor.validateOptions();


        // Test
        System.out.println(cmdProcessor.getOptionPrecision());
        System.out.println(cmdProcessor.getOptionThreads());
        System.out.println(cmdProcessor.getOptionQuiet());
        System.out.println(cmdProcessor.getOptionOutput());
    }
}