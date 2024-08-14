package ru.job4j.ood.lsp;

public class Rectangle {
    private double width;
    private double height;

    public void setDimensions(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public class Square extends Rectangle {
        @Override
        public void setDimensions(double width, double height) {
            if (width != height) {
                throw new IllegalArgumentException("Размеры должны быть одинаковы");
            }
        }
    }
}