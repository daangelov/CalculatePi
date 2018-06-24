package com.pi;

import java.math.BigDecimal;

class Logger {

    private String log;
    private boolean isQuiet;

    Logger(boolean isQuiet) {
        this.isQuiet = isQuiet;
    }

    public String getLog() {
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
     * @param totalExecutionTime long the thread execution time
     */
    void programEndedMessage(BigDecimal result, long totalExecutionTime) {
        String message = "Result: " + result.toString() +
                System.getProperty("line.separator") +
                "Total Execution time(millis): " +
                totalExecutionTime +
                System.getProperty("line.separator");

        sendMessage(message);
    }

    /**
     * Sends a message that a file was saved
     *
     * @param filePath String
     */
    public void fileSavedMessage(String filePath) {
        System.out.println("Saved Results to File: " + filePath);
    }

    /**
     * This method sends a string message to the log and console
     *
     * @param message String
     */
    private void sendMessage(String message) {

        this.log += message;
        System.out.print(message);
    }
}
