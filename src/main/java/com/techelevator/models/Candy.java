package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends Snack {

    public Candy(String productId, String name, BigDecimal price) {
        super(productId, name, price);
    }

    public Candy() {
    }

    public final String getMessage() {
        return "Munch Munch, Yum!";
    }
}
