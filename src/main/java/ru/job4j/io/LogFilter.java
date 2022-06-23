package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("404")) {
                    String[] division = line.split(" ");
                    String temp = division[division.length - 2];
                    if (temp.equals("404")) {
                        data.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out::println);
    }
}