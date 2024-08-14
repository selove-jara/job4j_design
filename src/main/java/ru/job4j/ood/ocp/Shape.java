package ru.job4j.ood.ocp;

public class Shape {
    private static final String CIRCLE = "CIRCLE";
    private static final String RECTANGLE = "RECTANGLE";

    private String type;

    public Shape(String type) {
        this.type = type;
    }

    public double calculateArea() {
        switch (type) {
            case CIRCLE:
                return 0;
            case RECTANGLE:
                return 0;
            default:
                throw new IllegalArgumentException("Неизвестная фигура");
        }
    }
}
