package com.pi;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

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
        boolean quietMode = cmdProcessor.getOptionQuiet();
        String outputFileName = cmdProcessor.getOptionOutput();

        // Set mode of program (quiet/non quiet)
        Logger logger = new Logger(quietMode);

        // Create thread pool and results pool
        Thread threadPool[] = new Thread[threads];

        // Division of tasks between threads
        int chunkSize = precision / threads;

        // Measure Program Start Time
        long startTime = Calendar.getInstance().getTimeInMillis();

        int lastTo = 0;
        for (int threadId = 0; threadId < threads; threadId++) {

            int threadsLeft = threads - (threadId + 1);
            int from = lastTo;
            int to = from + chunkSize;
            lastTo = to;

            if (threadsLeft == 0) {
                to = precision;
            }

            // Create thread to sum from, to
            Runnable runnableThread = new RunnableThread(threadId, from, to, logger);

            // Save a ref to the thread and start it
            Thread currentThread = new Thread(runnableThread);
            threadPool[threadId] = currentThread;
            currentThread.start();
        }

        // Join all the threads and add up the calculation from each one after it finishes
        for (int i = 0; i < threads; i++) {
            try {
                threadPool[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        BigDecimal result = Results.getFinalResult();

        long endTime = Calendar.getInstance().getTimeInMillis();

        logger.programEndedMessage(result);
        long totalExecutionTime = endTime - startTime;
        logger.programExecutionTimeMessage(totalExecutionTime);


        // Save log to file
        File outputFile = (outputFileName == null) ? new File(Constants.DEFAULT_OUTPUT_FILE) : new File(outputFileName);
        try {
            LogFileWriter.saveLogInFile(logger.getLog(), outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.fileSavedMessage(outputFileName);
    }
}