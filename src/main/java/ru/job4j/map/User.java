package ru.job4j.map;

import java.util.*;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2017, 0, 25);
        User one = new User("Andrew", 1, calendar);
        User two = new User("Andrew", 1, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(one, new Object());
        map.put(two, new Object());
        System.out.println(map);
        System.out.println(map.get(one));
        System.out.println(map.get(two));
    }

    @Override
    public String toString() {
        return "User{"
                +  "name='" + name + '\''
                +  ", children=" + children
                +  ", birthday=" + birthday
                +  '}';
    }
}
