package ru.job4j.ood.lsp.product;

import java.util.ArrayList;
import java.util.List;
public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void redistribute(Food food) {
        for (Store store : stores) {
            if (store.accept(food)) {
                store.add(food);
                return;
            }
        }
    }

    public void resort() {
        List<Food> allFoods = new ArrayList<>();
        for (Store store : stores) {
            allFoods.addAll(store.getAll());
            store.clear();
        }
        allFoods.forEach(this::redistribute);
    }
}