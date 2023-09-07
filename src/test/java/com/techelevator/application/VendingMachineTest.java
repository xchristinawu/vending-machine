package com.techelevator.application;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingMachineTest {

    @Test
    public void getChange_Should_Return_CorrectChange() {

        // arrange
        VendingMachine vendingMachine = new VendingMachine();
        String expected = "You have been dispensed 9 quarter(s), 1 dime(s), and 1 nickel(s).";

        // act
        String actual = vendingMachine.getChange(new BigDecimal("2.40"));

        // assert
        assertEquals("Because $2.40 in change should be 9 quarters, 1 dime, and 1 nickel", expected, actual);
    }
}
