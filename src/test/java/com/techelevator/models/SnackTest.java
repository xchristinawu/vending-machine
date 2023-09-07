package com.techelevator.models;

import com.techelevator.models.*;
import org.junit.Test;
import org.junit.internal.runners.model.EachTestNotifier;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SnackTest {
    @Test
    public void deductSnackQuantityByOne_Should_DeductOneFromSelectedSnackWhenItsQuantityIsOne() {
        // arrange
        Snack snack = new Candy("A1", "Candy", new BigDecimal("1"));
        snack.setQuantity(1);
        int expected = 0;

        // act
        snack.deductSnackQuantityByOne();
        int actual = snack.getQuantity();

        // assert
        assertEquals("Because if we deduct the last snack, the resulting quantity should be zero.", expected, actual);
    }

    @Test
    public void deductSnackQuantityByOne_Should_DeductOneFromSelectedSnackWhenItsQuantityIsSix() {
        // arrange
        Snack snack = new Candy("A1", "Candy", new BigDecimal("1"));
        snack.setQuantity(6);
        int expected = 5;

        // act
        snack.deductSnackQuantityByOne();
        int actual = snack.getQuantity();

        // assert
        assertEquals("Because if we deduct the sixth snack, the resulting quantity should be five.", expected, actual);
    }

    @Test
    public void getMessageFromGum_Should_ReturnChewChewYum() {
        // arrange
        String expected = "Chew Chew, Yum!";
        Snack gum = new Gum("A1", "Gum", new BigDecimal("1"));

        // act
        String actual = gum.getMessage();

        // assert
        assertEquals("Because gum should always make a 'Chew Chew, Yum!' sound.", expected, actual);
    }

    @Test
    public void getMessageFromChip_Should_ReturnCrunchCrunchYum() {
        // arrange
        String expected = "Crunch Crunch, Yum!";
        Snack chip = new Chip("A1", "Chip", new BigDecimal("1"));

        // act
        String actual = chip.getMessage();

        // assert
        assertEquals("Because chip should always make a 'Crunch Crunch, Yum!' sound.", expected, actual);
    }

    @Test
    public void getMessageFromCandy_Should_ReturnMunchMunchYum() {
        // arrange
        String expected = "Munch Munch, Yum!";
        Snack candy = new Candy("A1", "Candy", new BigDecimal("1"));

        // act
        String actual = candy.getMessage();

        // assert
        assertEquals("Because candy should always make a 'Munch Munch, Yum!' sound.", expected, actual);
    }

    @Test
    public void getMessageFromDrink_Should_ReturnGlugGlugYum() {
        // arrange
        String expected = "Glug Glug, Yum!";
        Snack drink = new Drink("A1", "Drink", new BigDecimal("1"));

        // act
        String actual = drink.getMessage();

        // assert
        assertEquals("Because drink should always make a 'Glug Glug, Yum!' sound.", expected, actual);
    }
}
