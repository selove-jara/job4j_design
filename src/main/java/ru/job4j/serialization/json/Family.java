package ru.job4j.serialization.json;

import java.util.Arrays;

public class Family {
    private final String surname;
    private final Person person;
    private final Person person1;
    private final boolean marriage;
    private final int termMarriage;
    private final String[] children;

    public Family(String surname, Person person, Person person1, boolean marriage, int termMarriage, String[] children) {
        this.surname = surname;
        this.person = person;
        this.person1 = person1;
        this.marriage = marriage;
        this.termMarriage = termMarriage;
        this.children = children;
    }

    @Override
    public String toString() {
        return "Family{"
                + "surname='" + surname + '\''
                + ", person=" + person
                + ", marriage=" + marriage
                + ", termMarriage=" + termMarriage
                + ", children=" + Arrays.toString(children)
                + '}';
    }
}
