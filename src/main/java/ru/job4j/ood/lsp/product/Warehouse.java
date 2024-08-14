package ru.job4j.ood.lsp.product;

public class Warehouse extends AbstractStore {

    @Override
    public boolean accept(Food food) {
        double timeSpent = food.getSpentPercentage();
        return timeSpent < 0.25;
    }
}
