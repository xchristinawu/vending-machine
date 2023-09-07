package com.techelevator.models;

import java.math.BigDecimal;

public class Chip extends Snack {

    public Chip(String productId, String name, BigDecimal price) {
        super(productId, name, price);
    }

    public Chip() {
    }

    public final String getMessage() {
        return "Crunch Crunch, Yum!";
    }
}
