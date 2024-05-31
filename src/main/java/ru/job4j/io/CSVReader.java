package ru.job4j.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        List<String> result = new ArrayList<>();
        String delimiter = argsName.get("delimiter");
        List<String> list = conversionLines(argsName);

        List<Integer> index = new ArrayList<>();
        String[] filter = argsName.get("filter").split(",");
        for (String line : list) {
            String[] strings = line.split(delimiter);
            for (int i = 0; i < filter.length; i++) {
                if (line.contains(filter[i])) {
                    index.add(Arrays.asList(strings).indexOf(filter[i]));
                }
            }

            StringBuilder builder = new StringBuilder();
            for (Integer i : index) {
                builder.append(strings[i]).append(delimiter);
            }
            result.add(builder.substring(0, builder.length() - 1));
        }
        output(result, argsName);
    }

    private static List<String> conversionLines(ArgsName argsName) {
        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(argsName.get("path"))).useDelimiter(System.lineSeparator())) {
        while (scanner.hasNext()) {
            list.add(scanner.next().replaceAll("\"", ""));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
        return list;
    }

    public static void output(List<String> result, ArgsName argsName) {
        if ("stdout".contains(argsName.get("out"))) {
            result.forEach(System.out::println);
        } else {
            try (FileWriter writer = new FileWriter(argsName.get("out"))) {
                for (String str : result) {
                    writer.write(str + System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        File file = new File(argsName.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException("Such file does not exist.");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("The specified path is not a file.");
        }
        handle(argsName);
    }
}
