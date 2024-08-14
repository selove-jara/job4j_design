package ru.job4j.ood.lsp.product;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    @Test
    public void whenFoodIsBetweenQuarterAndThreeQuartersThenAccepted() {
        Shop shop = new Shop();
        Food food = new Food("Bread", LocalDate.now().plusDays(20), LocalDate.now()
                .minusDays(10), 100, 0);
        assertTrue(shop.accept(food));
    }

    @Test
    public void whenFoodIsLessThanQuarterThenRejected() {
        Shop shop = new Shop();
        Food food = new Food("Cheese", LocalDate.now().plusDays(30), LocalDate.now()
                .minusDays(5), 100, 0);
        assertFalse(shop.accept(food));
    }

    @Test
    public void whenFoodIsMoreThanThreeQuartersThenAcceptedAndDiscountApplied() {
        Shop shop = new Shop();
        Food food = new Food("Milk", LocalDate.now().plusDays(10), LocalDate.now()
                .minusDays(30), 100, 20);
        assertTrue(shop.accept(food));
        shop.add(food);
        Food storedFood = shop.getAll().get(0);
        assertEquals(80, storedFood.getDiscountedPrice());
    }
}