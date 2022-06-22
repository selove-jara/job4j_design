package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> mapOfPrevious = new HashMap<>();
        Map<Integer, String> mapOfCurrent = new HashMap<>();
        Info info = new Info(0, 0, 0);
        info = new Info(
                info.getAdded(),
                info.getChanged(),
                info.getDeleted());
        for (User user : previous) {
            mapOfPrevious.put(user.getId(), user.getName());
        }
        for (User user : current) {
            mapOfCurrent.put(user.getId(), user.getName());
        }
        for (Integer key : mapOfCurrent.keySet()) {
            if (!mapOfPrevious.containsKey(key)) {
                info.setAdded(info.getAdded() + 1);
            }
        }
        for (Integer key : mapOfPrevious.keySet()) {
            if (!mapOfCurrent.containsKey(key)) {
                info.setDeleted(info.getDeleted() + 1);
            } else if (!mapOfCurrent.get(key).equals(mapOfPrevious.get(key))) {
                info.setChanged(info.getChanged() + 1);
            }
        }
        return info;
    }
}