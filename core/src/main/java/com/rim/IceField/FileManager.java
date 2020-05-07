package com.rim.IceField;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    public static void writeToFile(String fileName, String output) throws IOException {
        Path outputPath = Paths.get(System.getProperty("user.dir"), fileName);
        FileWriter outputFile = new FileWriter(outputPath.toString(), true);
        outputFile.write(output + "\n");
        outputFile.close();
    }
}
