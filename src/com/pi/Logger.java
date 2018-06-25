package com.pi;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

class Logger {

    private static StringBuffer log = new StringBuffer();
    private static boolean isQuiet;

    static void setIsQuiet(boolean isQuiet) {
        Logger.isQuiet = isQuiet;
    }

    static StringBuffer getLog() {
        return log;
    }

    /**
     * Sends message that thread started
     *
     * @param threadId String id of thread
     */
    static void threadStartedMessage(int threadId) {
        if (Logger.isQuiet) {
            return;
        }
        StringBuffer message = new StringBuffer()
                .append("Thread-")
                .append(threadId)
                .append(" started.")
                .append(System.getProperty("line.separator"));

        sendMessage(message);
    }

    /**
     * Sends message that thread ended and it's execution time
     *
     * @param threadId            String id of thread
     * @param threadExecutionTime long the thread execution time
     */
    static void threadEndedMessage(int threadId, long threadExecutionTime) {
        if (Logger.isQuiet) {
            return;
        }
        StringBuffer message = new StringBuffer()
                .append("Thread-")
                .append(threadId)
                .append(" ended.")
                .append(System.getProperty("line.separator"))
                .append("Thread-")
                .append(threadId)
                .append(" execution time was(millis): ")
                .append(threadExecutionTime)
                .append(System.getProperty("line.separator"));

        sendMessage(message);

    }

    /**
     * Sends a message that the program ended
     *
     * @param result             BigDecimal result for Pi
     * @param totalExecutionTime long the thread execution time
     */
    static void programEndedMessage(BigDecimal result, long totalExecutionTime) {
        StringBuffer message = new StringBuffer()
                .append(System.getProperty("line.separator"))
                .append("Result: ")
                .append(result)
                .append(System.getProperty("line.separator"))
                .append("Total Execution time(millis): ")
                .append(totalExecutionTime);

        sendMessage(message);
    }

    /**
     * Sends a message that a file was saved
     *
     * @param fileName String
     */
    static void fileSavedMessage(String fileName) {
        StringBuffer message = new StringBuffer()
                .append(System.getProperty("line.separator"))
                .append(System.getProperty("line.separator"))
                .append("Saved Results to File: ")
                .append(fileName)
                .append(System.getProperty("line.separator"));

        sendMessage(message);
    }

    /**
     * This method sends a string message to the log and console
     *
     * @param message StringBuffer
     */
    private static void sendMessage(StringBuffer message) {

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            Logger.log.append(message);
            System.out.print(message);
        } finally {
            lock.unlock();
        }
    }
}