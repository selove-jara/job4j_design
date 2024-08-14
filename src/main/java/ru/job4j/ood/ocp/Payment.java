package ru.job4j.ood.ocp;

public class Payment {
    private double amount;
    private String currency;

    public Payment(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double convertAmountToUSD() {
        if (currency.equals("USD")) {
            return amount;
        } else if (currency.equals("EUR")) {
            return amount * 1.1;
        } else {
            throw new IllegalArgumentException("Данную валюту не принимаем.");
        }
    }
}
