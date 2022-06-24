package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            int count = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] division = line.split(" ");
                    if (status(division) && count == 0) {
                        count++;
                        out.print(division[1] + "; ");
                    } else if (!status(division) && count > 0) {
                        out.println(division[1] + "; ");
                        count = 0;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean status(String[] division) {
        boolean rsl = false;
        if ("400".equals(division[0]) || "500".equals(division[0])) {
            rsl = true;
        } else if ("200".equals(division[0]) || "300".equals(division[0])) {
            rsl = false;
        }
        return rsl;
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("unavailable.csv", "unavailable_result");
        analizy.unavailable("unavailable2.csv", "unavailable_rsl");
    }
}
