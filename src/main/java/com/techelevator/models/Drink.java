package com.techelevator.models;

import java.math.BigDecimal;

public class Drink extends Snack {

    public Drink(String productId, String name, BigDecimal price) {
        super(productId, name, price);
    }

    public Drink() {
    }

    public final String getMessage() {
        return "Glug Glug, Yum!";
    }
}
