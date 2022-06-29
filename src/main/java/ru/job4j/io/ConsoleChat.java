package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
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
    private List<String> chat = new ArrayList<>();


    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws FileNotFoundException {
        List<String> answers = readPhrases();
        Scanner scanner = new Scanner(System.in);
        String message = null;
        boolean stoped = false;
        do {
            message = scanner.nextLine();
            chat.add(message);
            if (!stoped) {
                stoped = STOP.equals(message) || OUT.equals(message);
            }
            if (CONTINUE.equals(message)) {
                stoped = false;
            }
            if (!stoped) {
                String answer = answers.get(new Random().nextInt(answers.size() - 1));
                chat.add(answer);
                System.out.println(answer);
            }
        } while (!OUT.equals(message));
        saveLog(path, chat);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            phrases = br.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(String path, List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        ConsoleChat cc = new ConsoleChat("chat.log", "answer.txt");
        cc.run();
    }
}