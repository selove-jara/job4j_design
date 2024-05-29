package ru.job4j.regex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean workingBot = true;
        boolean notWorkingBot = true;
        List<String> dialogue = new ArrayList<>();
        List<String> answers = readPhrases();
        Random random = new Random();
        while (workingBot) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if (OUT.equals(str)) {
                workingBot = false;
                dialogue.add(str);
            } else if (STOP.equals(str)) {
                notWorkingBot = false;
            }
            if (notWorkingBot) {
                System.out.println(answers.get(random.nextInt(answers.size())));
                dialogue.add(str);
            } else if (CONTINUE.equals(str)) {
                notWorkingBot = true;
            }
        }
        saveLog(dialogue);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(botAnswers))) {
            input.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/dialogue.txt", "./data/textBot.txt");
        consoleChat.run();
    }
}
