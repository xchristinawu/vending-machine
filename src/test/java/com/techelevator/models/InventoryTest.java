package com.techelevator.models;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class InventoryTest {

    @Test
    public void loadInventory_Should_AddSnacksToSnacksMap() {
        // arrange
        Inventory inventory = new Inventory();

        Map<String, Snack> expected = new LinkedHashMap<>();
        expected.put("A1", new Chip("A1", "Potato Crisps", new BigDecimal("3.05")));
        expected.put("A2", new Chip("A2", "Stackers", new BigDecimal("1.45")));
        expected.put("A3", new Chip("A3", "Grain Waves", new BigDecimal("2.75")));
        expected.put("A4", new Chip("A4", "Cloud Popcorn", new BigDecimal("3.65")));
        expected.put("B1", new Candy("B1", "Moonpie", new BigDecimal("1.8")));
        expected.put("B2", new Candy("B2", "Cowtales", new BigDecimal("1.5")));
        expected.put("B3", new Candy("B3", "Wonka Bar", new BigDecimal("1.5")));
        expected.put("B4", new Candy("B4", "Crunchie", new BigDecimal("1.75")));
        expected.put("C1", new Drink("C1", "Cola", new BigDecimal("1.25")));
        expected.put("C2", new Drink("C2", "Dr. Salt", new BigDecimal("1.5")));
        expected.put("C3", new Drink("C3", "Mountain Melter", new BigDecimal("1.5")));
        expected.put("C4", new Drink("C4", "Heavy", new BigDecimal("1.5")));
        expected.put("D1", new Gum("D1", "U-Chews", new BigDecimal("0.85")));
        expected.put("D2", new Gum("D2", "Little League Chew", new BigDecimal("0.95")));
        expected.put("D3", new Gum("D3", "Chiclets", new BigDecimal("0.75")));
        expected.put("D4", new Gum("D4", "Triplemint", new BigDecimal("0.75")));

        // act
        inventory.loadInventory("data/vendingmachine.csv");
        Map<String, Snack> actual = inventory.getSnacks();

        // assert
        assertEquals("Because keysets should match", expected.keySet(), actual.keySet());

        for (Map.Entry<String, Snack> snack : expected.entrySet()) {
            String key = snack.getKey();
            Snack expectedSnack = snack.getValue();
            Snack actualSnack = actual.get(key);

            assertEquals("Because names should match", expectedSnack.getName(), actualSnack.getName());
            assertEquals("Because productId should match", expectedSnack.getProductId(), actualSnack.getProductId());
            assertEquals("Because price should match", expectedSnack.getPrice(), actualSnack.getPrice());
            assertEquals("Because quantity should match", expectedSnack.getQuantity(), actualSnack.getQuantity());
            assertEquals("Because Class should match", expectedSnack.getClass(), actualSnack.getClass());
        }

    }
}
