package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String result = values.get(key);
        if (result == null) {
            throw new IllegalArgumentException("The key does not exist");
        }
        return result;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The array is empty");
        }
        for (String element : args) {
            validate(element);
            String[] temp = element.replaceFirst("-", "").split("=", 2);
            if (temp.length != 2 || temp[0].isEmpty() || temp[1].isEmpty()) {
                throw new IllegalArgumentException("One of the arguments is missing");
            }
            values.put(temp[0], temp[1]);

        }
    }

    private void validate(String element) {
        if (!element.startsWith("-")) {
            throw new IllegalArgumentException("The template is broken, the \"-\" symbol is missing");
        }
        if (!element.contains("=")) {
            throw new IllegalArgumentException("The template is broken, the \"=\" symbol is missing");
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}