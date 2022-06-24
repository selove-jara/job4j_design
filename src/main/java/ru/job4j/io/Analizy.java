package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            int count = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] division = line.split(" ");
                    if (status(division) && count == 0) {
                        data.add(division[1]);
                        count++;
                    } else if (!status(division) && count > 0) {
                        data.add(division[1]);
                        count = 0;
                    }
                }
            }
            save(data, target);
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

    public static void save(List<String> data, String target) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            int count = 0;
            for (String element : data) {
                if (count < 1) {
                    out.print(element + "; ");
                    count++;
                } else {
                    out.println(element + "; ");
                    count = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("unavailable.csv", "unavailable_result");
        analizy.unavailable("unavailable2.csv", "unavailable_rsl");
    }
}
