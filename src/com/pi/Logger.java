package com.pi;

import java.math.BigDecimal;

class Logger {

    private String log;
    private boolean isQuiet;

    Logger(boolean isQuiet) {
        this.isQuiet = isQuiet;
    }

    String getLog() {
        return log;
    }

    /**
     * Sends message that thread started
     *
     * @param threadId String id of thread
     */
    void threadStartedMessage(String threadId) {
        if (this.isQuiet) {
            return;
        }

        String message = "Thread-" +
                threadId +
                " started." +
                System.getProperty("line.separator");

        sendMessage(message);
    }

    /**
     * Sends message that thread ended
     *
     * @param threadId String id of thread
     */
    void threadEndedMessage(String threadId) {
        if (this.isQuiet) {
            return;
        }

        String message = "Thread-" +
                threadId +
                " ended." +
                System.getProperty("line.separator");

        sendMessage(message);
    }

    /**
     * Send information for thread execution time
     *
     * @param threadId            String id of thread
     * @param threadExecutionTime long the thread execution time
     */
    void threadExecutionTimeMessage(String threadId, long threadExecutionTime) {
        if (this.isQuiet) {
            return;
        }

        String message = "Thread-" +
                threadId +
                " execution time was(millis): " +
                threadExecutionTime +
                System.getProperty("line.separator");

        sendMessage(message);
    }

    /**
     * Sends a message that the program ended
     *
     * @param result BigDecimal result for Pi
     */
    void programEndedMessage(BigDecimal result) {
        String message = System.getProperty("line.separator") +
                "Result: " +
                result.toString() +
                System.getProperty("line.separator");

        sendMessage(message);
    }

    /**
     * Send information for program execution time
     *
     * @param totalExecutionTime long the thread execution time
     */
    void programExecutionTimeMessage(long totalExecutionTime) {
        String message = "Total Execution time(millis): " +
                totalExecutionTime +
                System.getProperty("line.separator");

        sendMessage(message);
    }

    /**
     * Sends a message that a file was saved
     *
     * @param fileName String
     */
    void fileSavedMessage(String fileName) {
        String message = System.getProperty("line.separator") +
                "Saved Results to File: " +
                fileName +
                System.getProperty("line.separator");

        sendMessage(message);
    }

    /**
     * This method sends a string message to the log and console
     *
     * @param message String
     */
    private void sendMessage(String message) {

        this.log = (this.log == null) ? message : this.log + message;
        System.out.print(message);
    }
}
