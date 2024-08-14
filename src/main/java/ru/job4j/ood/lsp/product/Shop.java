package ru.job4j.ood.lsp.product;

public class Shop extends AbstractStore {

    @Override
    public boolean accept(Food food) {
        double timeSpent = food.getSpentPercentage();
        return timeSpent >= 0.25;
    }

    @Override
    public void add(Food food) {
        double timeSpent = food.getSpentPercentage();
        if (timeSpent >= 0.75) {
            food.setDiscount(20);
        }
        super.add(food);
    }
}
