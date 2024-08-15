package ru.job4j.ood.isp;

public class CreditCardPayment implements Payment {
    @Override
    public void processCreditCardPayment() {
    }

    @Override
    public void processPayPalPayment() {
        throw new UnsupportedOperationException("PayPal не поддерживает");
    }

    @Override
    public void processBankTransfer() {
    }
}

