package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Family family = new Family("Morozov", new Person("Andrey", 26), new Person("Неизвестность", 26),
                true, 3, new String[]{"Петя", "Вася"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(family));

        final String personJson =
                "{"
                        + "\"surname\":\"Morozov\","
                        + "\"person\":"
                        + "{"
                        + "\"name\":\"Андрей\","
                        + "\"age\":\"20\""
                        + "},"
                        + "\"person\":"
                        + "{"
                        + "\"name\":\"Андрей\","
                        + "\"age\":\"20\""
                        + "},"
                        + "\"marriage\":false,"
                        + "\"termMarriage\":4,"
                        + "\"children\":"
                        + "[\"Name\",\"Name1\"]"
                        + "}";

        final Family personMod = gson.fromJson(personJson, Family.class);
        System.out.println(personMod);
    }
}
