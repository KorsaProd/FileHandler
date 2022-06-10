package ru.company;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.charset.Charset.defaultCharset;


public class Main {

    private static String DIRECTORY_PATH = "resources";
    private static final List<File> preSortedFiles = new ArrayList<>();
    private static final List<File> sortedFiles = new ArrayList<>();

    public static void preSortFiles(File folder) {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                preSortFiles(entry);
                continue;
            } else if (entry.getAbsolutePath().endsWith(".txt")) {
                preSortedFiles.add(entry);
            }
        }
    }

    public static void swapIndex(int i, String folderFile) {

        for (int j = 0; j < preSortedFiles.size(); j++) {
            if (preSortedFiles.get(j).getAbsolutePath().contains(folderFile)) {
                File buffer = preSortedFiles.get(i);

                preSortedFiles.set(i, preSortedFiles.get(j));
                preSortedFiles.set(j, buffer);

            }
        }
    }

    public static void reversPriority() {
        for (int i = preSortedFiles.size() -1; i != -1; i--) {
           sortedFiles.add(preSortedFiles.get(i));
        }

    }


    public static void processFilesFromFolder(File folder) {

        preSortFiles(folder);
           for (int i = 0; i < preSortedFiles.size(); i++) {
            try {
                    List<String> lines = Files.readAllLines(Path.of(String.valueOf(preSortedFiles.get(i))),
                            defaultCharset());
                    for (String content : lines) {
                        if (content.contains("require")) {

                           String folderFile = content
                                   .substring(content.indexOf('‘') + 1, content.lastIndexOf('’'));

                           swapIndex(i, folderFile);
                        }
                    }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
           reversPriority();
    }

    public static void concatenateContent(List<File> fileList) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (File file : fileList) {
                List<String> lines = Files.readAllLines(Path.of(file.getAbsolutePath()), Charset.defaultCharset());
                for (String content : lines) {
                    if (content.isEmpty() || content.startsWith("\trequire")) {
                        continue;
                    } else {
                        stringBuilder.append(content).append("\n");
                    }
                }
            }
           PrintWriter printWriter = new PrintWriter("output/output.txt");
            printWriter.write(stringBuilder.toString());
            printWriter.flush();
            printWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File folder = new File(DIRECTORY_PATH);
        processFilesFromFolder(folder);
        System.out.println("Список файлов согласно зависимостям: " + "\n" + sortedFiles);
        System.out.println("Содержание файлов находится в файле output/output.txt");
        concatenateContent(sortedFiles);
    }
}
