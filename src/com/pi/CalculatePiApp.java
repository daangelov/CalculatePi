package com.pi;

import com.pi.utils.LogFileWriter;

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

        Logger.setIsQuiet(isQuiet);

        // Measure Program Start Time
        long startTime = Calendar.getInstance().getTimeInMillis();

        // Create thread pool
        ThreadPool threadPool = new ThreadPool(this.precision, this.threads);
        // Start threads
        threadPool.startThreads();
        // Join threads
        threadPool.joinThreads();

        // Get result from static class result
        BigDecimal result = Results.getFinalResult();

        // Measure Program End Time
        long endTime = Calendar.getInstance().getTimeInMillis();
        long totalExecutionTime = endTime - startTime;

        Logger.programEndedMessage(result, totalExecutionTime);

        // Save log to file
        File outputFile = new File(outputFileName);
        try {
            LogFileWriter.saveLogInFile(Logger.getLog(), outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.fileSavedMessage(outputFileName);
    }
}