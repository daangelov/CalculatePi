package com.pi;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

class CalculatePiApp {

    private int precision;
    private int threads;
    private boolean isQuiet;
    private String outputFileName;

    CalculatePiApp(int precision, int threads, boolean isQuiet, String outputFileName) {
        this.precision = precision;
        this.threads = threads;
        this.isQuiet = isQuiet;
        this.outputFileName = outputFileName;
    }

    void run() {
        // Set mode of program (quiet/non quiet)
        Logger logger = new Logger(isQuiet);

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

        long totalExecutionTime = endTime - startTime;
        logger.programEndedMessage(result, totalExecutionTime);

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
