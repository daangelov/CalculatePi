package com.pi;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

class LogFileWriter {

    /**
     * This method saves the log in fileName
     *
     * @param log      String the logged information
     * @param fileName File the file to be written in
     * @throws IOException Throws exception on error
     */
    static void saveLogInFile(String log, File fileName) throws IOException {

        if (!fileName.exists()) {
            if (!fileName.createNewFile()) {
                throw new IOException();
            }
        } else {
            if (!fileName.delete() || !fileName.createNewFile()) {
                throw new IOException();
            }
        }

        PrintWriter out = new PrintWriter(fileName.getAbsoluteFile());
        out.println(log);
        out.close();
    }
}
