package ru.job4j.io;

import java.io.*;

public class Analysis {

    private static boolean status = true;

    public static void unavailable(String source, String target) {

        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     ))) {
            reader.lines()
                    .forEach(s -> {
                        String[] strings = s.split(" ");
                        if (("500".equals(strings[0]) || "400".equals(strings[0])) && status) {
                            status = false;
                            output.append(strings[1]).append(";");
                        } else if (("200".equals(strings[0]) || "300".equals(strings[0])) && !status) {
                            status = true;
                            output.append(strings[1]).append(";").append(System.lineSeparator());
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        unavailable("data/server.log", "data/target.csv");
    }
}