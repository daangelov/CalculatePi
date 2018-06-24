package com.pi;

import java.math.BigDecimal;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {

        // Process the command line arguments
        CommandLineProcessor cmdProcessor = new CommandLineProcessor();
        cmdProcessor.buildOptions();
        cmdProcessor.parseOptions(args);
        cmdProcessor.validateOptions();

        // Set mode of program (quiet/non quiet)
        boolean quietMode = cmdProcessor.getOptionQuiet() != null;
        Logger logger = new Logger(quietMode);

        int precision = Integer.parseInt(cmdProcessor.getOptionPrecision());
        int threads = Integer.parseInt(cmdProcessor.getOptionThreads());

        // if more threads than tasks
        if (precision < threads) {
            threads = precision;
        }

        // Negative/Zero fix
        if (threads <= 0) {
            threads = 1;
        }
        if (precision <= 0) {
            precision = 1;
        }

        // Division of tasks between threads
        int chunkSize = precision / threads;

        // Create thread pool and results pool
        Thread threadPool[] = new Thread[threads];

        // Measure Program Start Time
        long startTime = Calendar.getInstance().getTimeInMillis();

        int lastTo = 0;
        for (int n = 0; n < threads; n++) {
            int threadsLeft = threads - (n + 1);
            int sumFrom = lastTo;
            int sumTo = sumFrom + chunkSize;
            lastTo = sumTo;
            if (threadsLeft == 0)
                sumTo = precision;

            // Create Thread Task to sum(from,to)
            Runnable runnableTask = new RunnableThread(n, sumFrom, sumTo, logger);

            // Save a ref to the thread and start it
            Thread currentThread = new Thread(runnableTask);
            threadPool[n] = currentThread;
            currentThread.start();
        }
        long endTime = Calendar.getInstance().getTimeInMillis();
        // Join all the threads and add up the calculation from each one after it finishes
        for (int i = 0; i < threads; i++) {
            try {
                threadPool[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        BigDecimal result = Results.getResult();



        logger.programEndedMessage(result, endTime - startTime);

    }
}