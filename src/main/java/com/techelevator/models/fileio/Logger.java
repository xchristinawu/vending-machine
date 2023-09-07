package com.techelevator.models.fileio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static String logMessage(String message) {

        File logDirectory = new File("./Log");
        if (!logDirectory.exists()) {
            logDirectory.mkdir();
        }

        File logFile = new File("./Log/", "Log.txt");

        String formatDateTime = "";
        try (FileWriter filewriter = new FileWriter(logFile, true);
                PrintWriter writer = new PrintWriter(filewriter)) {

            LocalDateTime timeStamp = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            formatDateTime = timeStamp.format(formatter);
            writer.println(formatDateTime + " " + message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return formatDateTime + " " + message;
    }
}
