package ru.job4j.ood.ocp;

public class Employee {
    private static final String ENGINEER = "ENGINEER";
    private static final String MANAGER = "MANAGER";

    private String type;

    public Employee(String type) {
        this.type = type;
    }

    public double calculateBonus() {
        if (type.equals(ENGINEER)) {
            return 1000;
        } else if (type.equals(MANAGER)) {
            return 2000;
        } else {
            throw new IllegalArgumentException("Должность отсуствует");
        }
    }
}
