package com.pi;

import java.math.BigDecimal;

class Logger {

    private StringBuffer log;
    private boolean isQuiet;

    Logger(boolean isQuiet) {
        log = new StringBuffer();
        this.isQuiet = isQuiet;
    }

    StringBuffer getLog() {
        return log;
    }

    /**
     * Sends message that thread started
     *
     * @param threadId String id of thread
     */
    void threadStartedMessage(int threadId) {
        if (this.isQuiet) {
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
    void threadEndedMessage(int threadId, long threadExecutionTime) {
        if (this.isQuiet) {
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
    void programEndedMessage(BigDecimal result, long totalExecutionTime) {
        StringBuffer message = new StringBuffer()
                .append(System.getProperty("line.separator"))
                .append("Result: ")
                .append(result)
                .append(System.getProperty("line.separator"))
                .append("Total Execution time(millis): ")
                .append(totalExecutionTime)
                .append(System.getProperty("line.separator"));

        sendMessage(message);
    }

    /**
     * Sends a message that a file was saved
     *
     * @param fileName String
     */
    void fileSavedMessage(String fileName) {
        StringBuffer message = new StringBuffer()
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
    private void sendMessage(StringBuffer message) {

        this.log.append(message);
        System.out.print(message);
    }
}
