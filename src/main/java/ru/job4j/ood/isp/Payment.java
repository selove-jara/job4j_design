package ru.job4j.ood.isp;

public interface Payment {
    void processCreditCardPayment();

    void processPayPalPayment();

    void processBankTransfer();
}