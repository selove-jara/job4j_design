package ru.job4j.ood.dip;

public class Payment {
    private PayPal payPal = new PayPal();

    public void processPayment() {
        payPal.process();
    }
}

