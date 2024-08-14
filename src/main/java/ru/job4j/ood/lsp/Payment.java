package ru.job4j.ood.lsp;

public class Payment {
    public void processPayment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Денежные средства отсутствуют");
        }
    }

    public class DiscountedPaymentProcessor extends Payment {
        private double discount;

        public DiscountedPaymentProcessor(double discount) {
            this.discount = discount;
        }

        @Override
        public void processPayment(double amount) {
            if (amount <= discount) {
                throw new IllegalArgumentException("Скидка должна быть меньше суммы");
            }
        }
    }
}
