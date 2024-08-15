package ru.job4j.ood.dip;

public class DeliveryService {
    private Bike bike = new Bike();

    public void deliverPackage() {
        bike.deliver();
    }
}
