package ru.company;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final String DIRECTORY_PATH = "/Users/Korsa/Desktop/Doczilla";
    private static List<String> preSortedFiles = new ArrayList<>();
    //TODO: вернуть формат в листе - File

    public static void preSortFiles(File folder) {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                preSortFiles(entry);
                continue;
            } else if (entry.getAbsolutePath().endsWith(".txt")) {
                preSortedFiles.add(entry.getAbsolutePath());
            }
        }
    }



    public static void processFilesFromFolder(File folder) {

        preSortFiles(folder);
           for (int i = 0; i < preSortedFiles.size(); i++) {
            try {
                System.out.println(preSortedFiles.get(i));
                    List<String> lines = Files.readAllLines(Path.of(preSortedFiles.get(i)), Charset.defaultCharset());
                    for (String content : lines) {
                        if (content.contains("require")) {

                           String folderFile = content
                                   .substring(content.indexOf('‘') + 1, content.lastIndexOf('’'));

//                            StringBuilder stringBuilder = new StringBuilder();
//                            stringBuilder.append(folderFile[0]);
//                            stringBuilder.append("/");
//                            stringBuilder.append(folderFile[1]);
//                            stringBuilder.append(".txt");
//
//                            System.out.println(stringBuilder);

                            for (int j =0; j < preSortedFiles.size(); j++) {
                                if (preSortedFiles.get(j).contains(folderFile)) {
                                    System.out.println("Yes");
                                    preSortedFiles.set()
                                            //TODO: на стадии когда нужно поменять местами индексы/приоритеты
                                }
                            }

                           // System.out.println(preSortedFiles.contains(stringBuilder.toString()));






                            ///Users/Korsa/Desktop/Doczilla/Folder2/File2-1.txt
                            ///Users/Korsa/Desktop/Doczilla/Folder2/File2-2.txt
                            //Folder Folder 1
                            //File File 1-1
                        }

                    }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        File folder = new File(DIRECTORY_PATH);
        processFilesFromFolder(folder);

    }
}
