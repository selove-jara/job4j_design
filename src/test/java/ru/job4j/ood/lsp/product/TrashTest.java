package ru.job4j.ood.lsp.product;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrashTest {
    @Test
    public void whenFoodIsExpiredThenAccepted() {
        Trash trash = new Trash();
        Food food = new Food("Expired Bread", LocalDate.now().minusDays(1), LocalDate.now().minusDays(10), 100, 0);
        assertTrue(trash.accept(food));
    }

    @Test
    public void whenFoodIsNotExpiredThenRejected() {
        Trash trash = new Trash();
        Food food = new Food("Fresh Milk", LocalDate.now().plusDays(10), LocalDate.now().minusDays(1), 100, 0);
        assertFalse(trash.accept(food));
    }
}