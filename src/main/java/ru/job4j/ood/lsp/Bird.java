package ru.job4j.ood.lsp;

public class Bird {
    public void fly() {
    }

    public class Penguin extends Bird {
        @Override
        public void fly() {
            throw new UnsupportedOperationException("Я слишком пухлый и не смогу взлететь.");
        }
    }
}