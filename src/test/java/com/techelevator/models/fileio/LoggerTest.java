package com.techelevator.models.fileio;

import org.junit.Test;
import java.io.File;
import java.util.Scanner;
import static org.junit.Assert.*;

public class LoggerTest {
    @Test
    public void logger_Should_LogAppropriately() {
    String expected = Logger.logMessage("Hello");
    String fileName = "Log.txt";
    File file = new File(fileName);
    String actual = "";
    try (Scanner fileScanner = new Scanner(file)) {
        while (fileScanner.hasNextLine()) {
            actual = fileScanner.nextLine();
        }
     } catch (Exception e) {
        System.out.println(e.getMessage());
        fail();
    }

    assertEquals("Because the file should exist and look like we expect it to.", expected, actual);
}

}
