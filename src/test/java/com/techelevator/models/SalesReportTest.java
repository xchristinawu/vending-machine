package com.techelevator.models;

import com.techelevator.models.fileio.SalesReport;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;


import static org.junit.Assert.*;

public class SalesReportTest
{
    Map<Snack, Integer> expected;
    Snack chip;
    Inventory inventory;

    @Before
    public void setUp()
    {

        inventory = new Inventory();
        inventory.loadInventory("data/vendingmachine.csv");
        SalesReport.initializeSalesReport(inventory.getSnacks());

        expected = new LinkedHashMap<>();
        chip = new Chip("A1", "Potato Crisps", new BigDecimal("3.05"));
        expected.put(chip, 0);
        expected.put(new Chip("A2", "Stackers", new BigDecimal("1.45")), 0);
        expected.put(new Chip("A3", "Grain Waves", new BigDecimal("2.75")), 0);
        expected.put(new Chip("A4", "Cloud Popcorn", new BigDecimal("3.65")), 0);
        expected.put(new Candy("B1", "Moonpie", new BigDecimal("1.8")), 0);
        expected.put(new Candy("B2", "Cowtales", new BigDecimal("1.5")), 0);
        expected.put(new Candy("B3", "Wonka Bar", new BigDecimal("1.5")), 0);
        expected.put(new Candy("B4", "Crunchie", new BigDecimal("1.75")), 0);
        expected.put(new Drink("C1", "Cola", new BigDecimal("1.25")), 0);
        expected.put(new Drink("C2", "Dr. Salt", new BigDecimal("1.5")), 0);
        expected.put(new Drink("C3", "Mountain Melter", new BigDecimal("1.5")), 0);
        expected.put(new Drink("C4", "Heavy", new BigDecimal("1.5")), 0);
        expected.put(new Gum("D1", "U-Chews", new BigDecimal("0.85")), 0);
        expected.put(new Gum("D2", "Little League Chew", new BigDecimal("0.95")), 0);
        expected.put(new Gum("D3", "Chiclets", new BigDecimal("0.75")), 0);
        expected.put(new Gum("D4", "Triplemint", new BigDecimal("0.75")), 0);

    }
    @Test
    public void initializeSalesReport()
    {

        //Arrange
        Set<Snack> expectedKeySet = expected.keySet();
        List<Snack> expectedKeys = new ArrayList<>(expectedKeySet);
        List<Integer> expectedValues = new ArrayList<>(expected.values());

        // act
        Map<Snack, Integer> actual = SalesReport.getSalesReport();

        Set<Snack> actualKeySet = actual.keySet();
        List<Snack> actualKeys = new ArrayList<>(actualKeySet);
        List<Integer> actualValues = new ArrayList<>(actual.values());


        // assert
        assertEquals("Because values should all be zero", expectedValues, actualValues);

        for (int i = 0; i < expectedKeys.size(); i++) {

            Snack expectedSnack = expectedKeys.get(i);
            Snack actualSnack = actualKeys.get(i);

            assertEquals("Because names should match", expectedSnack.getName(), actualSnack.getName());
            assertEquals("Because productId should match", expectedSnack.getProductId(), actualSnack.getProductId());
            assertEquals("Because price should match", expectedSnack.getPrice(), actualSnack.getPrice());
            assertEquals("Because quantity should match", expectedSnack.getQuantity(), actualSnack.getQuantity());
            assertEquals("Because Class should match", expectedSnack.getClass(), actualSnack.getClass());
        }

    }

    @Test
    public void addSoldItemToSalesReport()
    {
        //Arrange
        expected.put(chip, 1);
        List<Integer> expectedValues = new ArrayList<>(expected.values());

        // act
        Map<Snack, Integer> actual = SalesReport.getSalesReport();

        for (Map.Entry<Snack, Integer> entry : actual.entrySet())
        {
            if(entry.getKey().getProductId().equals("A1"))
            {
                SalesReport.addSoldItemToSalesReport(entry.getKey());
            }
        }

        List<Integer> actualValues = new ArrayList<>(actual.values());

        // assert
        assertEquals("Because values should all be zero except for A1, which should be 1 in both maps.", expectedValues, actualValues);
    }

    @Test
    public void generateSalesReport_Should_CreateAnAccurateSalesReportFileOfItsReturnedName()
    {
        String expected = "Potato Crisps|1\nStackers|0\nGrain Waves|0\nCloud Popcorn|0\nMoonpie|0\nCowtales|0\n" +
                "Wonka Bar|0\nCrunchie|0\nCola|0\nDr. Salt|0\nMountain Melter|0\nHeavy|0\nU-Chews|0\n" +
                "Little League Chew|0\nChiclets|0\nTriplemint|0\n\n**TOTAL SALES** $3.05";
        Map<Snack, Integer> salesReport = SalesReport.getSalesReport();

        for (Map.Entry<Snack, Integer> entry : salesReport.entrySet())
        {
            if(entry.getKey().getProductId().equals("A1"))
            {
                SalesReport.addSoldItemToSalesReport(entry.getKey());
            }
        }

        String fileName = SalesReport.generateSalesReport();
        File file = new File(fileName);
        String actual = "";
        try (Scanner fileScanner = new Scanner(file)) {
            actual += fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                actual += "\n" + line;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }

        assertEquals("Because the file should exist and look like we expect it to.", expected, actual);
    }
}
