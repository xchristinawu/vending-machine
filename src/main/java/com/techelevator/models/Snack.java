package com.techelevator.models;

import java.math.BigDecimal;

public abstract class Snack {

    private String productId;
    private String name;
    private BigDecimal price;
    private int quantity = 5;

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Snack(String productId, String name, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public Snack() {

    }

    public final void deductSnackQuantityByOne() {
        quantity--;
    }

    public abstract String getMessage();

}
