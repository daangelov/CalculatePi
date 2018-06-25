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


    private Logger logger;
    private Thread threadPool[];
    private int chunkSize;

    CalculatePiApp(int precision, int threads, boolean isQuiet, String outputFileName) {
        this.precision = precision;
        this.threads = threads;
        this.isQuiet = isQuiet;
        this.outputFileName = outputFileName;

        initiate();
    }

    private void initiate() {
        this.logger = new Logger(this.isQuiet);
        this.threadPool = new Thread[this.threads];
        this.chunkSize = this.precision / this.threads;
    }

    void run() {

        // Measure Program Start Time
        long startTime = Calendar.getInstance().getTimeInMillis();

        // Start the threads
        int lastTo = 0;
        for (int threadId = 0; threadId < this.threads; threadId++) {

            int threadsLeft = this.threads - (threadId + 1);
            int from = lastTo;
            int to = from + this.chunkSize;
            lastTo = to;

            if (threadsLeft == 0) {
                to = this.precision;
            }

            // Create thread to sum from, to
            Runnable runnableThread = new RunnableThread(threadId, from, to, this.logger);

            // Save a ref to the thread and start it
            Thread currentThread = new Thread(runnableThread);
            this.threadPool[threadId] = currentThread;
            currentThread.start();
        }

        // Join all the threads and add up the calculation from each one after it finishes
        for (int i = 0; i < this.threads; i++) {
            try {
                this.threadPool[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        // Get result from static class result
        BigDecimal result = Results.getFinalResult();

        // Measure Program End Time
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