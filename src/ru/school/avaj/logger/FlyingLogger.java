package ru.school.avaj.logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FlyingLogger {
    private static PrintWriter writer;
    private static File file;

    public FlyingLogger(String fileName) {
        try {
            file = new File(fileName);
            if (writer == null) {
                writer = new PrintWriter(fileName);
            }
        } catch (IOException e) {
            System.out.println("[ERROR]: Failed to create file.");
        }
    }

    public static void log(String message) {
            writer.println(message);
            writer.flush();
    }

    public static void close() {
        if (writer != null) {
            writer.close();
        }
    }
}
