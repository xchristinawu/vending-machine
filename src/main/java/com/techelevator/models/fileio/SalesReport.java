package com.techelevator.models.fileio;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.Snack;
import com.techelevator.ui.UserOutput;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesReport {

    private static Map<Snack, Integer> salesReport = new LinkedHashMap<>();

    public static Map<Snack, Integer> getSalesReport()
    {
        return salesReport;
    }

    public static void initializeSalesReport(Map<String, Snack> snacks) {
        salesReport = new LinkedHashMap<>();
        for (Snack snack : snacks.values()) {
            salesReport.put(snack, 0);
        }
    }

    public static void addSoldItemToSalesReport(Snack selectedSnack) {
        Integer initialSnackQuantity = salesReport.get(selectedSnack);
        salesReport.put(selectedSnack, initialSnackQuantity + 1);
    }

    public static String generateSalesReport() {

        File salesDirectory = new File("./SalesReports");
        if (!salesDirectory.exists()) {
            salesDirectory.mkdir();
        }

        LocalDateTime timeStamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM_dd_yy_hh_mm_ss_a");
        String formatDateTime = "SalesReports/" + timeStamp.format(formatter);

        try (PrintWriter writer = new PrintWriter(formatDateTime)) {

            for (Map.Entry<Snack, Integer> snack : salesReport.entrySet()) {
                writer.println(snack.getKey().getName() + "|" + snack.getValue());
            }

            writer.println("");
            writer.println("**TOTAL SALES** $" + VendingMachine.DECIMAL_FORMAT.format(getTotalSales()));

        } catch (IOException e) {
            UserOutput.displayMessage(e.getMessage(), true);
        }

        return formatDateTime;
    }

    private static BigDecimal getTotalSales() {

        BigDecimal totalSales = new BigDecimal("0");

        for (Map.Entry<Snack, Integer> entry : salesReport.entrySet()) {
            BigDecimal entryPrice = entry.getKey().getPrice();
            BigDecimal entrySales = entryPrice.multiply(new BigDecimal("" + entry.getValue()));
            totalSales = totalSales.add(entrySales);
        }

        return totalSales;
    }

    public static void generateCumulativeSalesReport() {

        File salesDirectory = new File("./SalesReports");
        if (!salesDirectory.exists()) {
            salesDirectory.mkdir();
        }

        String filePath = "SalesReports/CumulativeSalesReport";
        File file = new File(filePath);

        Map<String, Integer> pastPurchases = new HashMap<>();
        BigDecimal pastSpentMoney = null;
        if (file.exists()) {
            try (Scanner fileReader = new Scanner(file)) {
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    if (line.length() > 0) {
                        String[] snackAndQuantity = line.split("\\|");
                        String snackName = snackAndQuantity[0];
                        Integer quantity = Integer.parseInt(snackAndQuantity[1]);
                        pastPurchases.put(snackName, quantity);
                    } else {
                        break;
                    }
                }
                String line = fileReader.nextLine();
                String[] splitLine = line.split("\\$");
                pastSpentMoney = new BigDecimal(splitLine[1]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            try (PrintWriter writer = new PrintWriter(filePath)) {
                for (Map.Entry<Snack, Integer> snack : salesReport.entrySet()) {
                    writer.println(snack.getKey().getName() + "|" + (snack.getValue() + pastPurchases.getOrDefault(snack.getKey().getName(), 0)));
                }
                writer.println("");
                BigDecimal totalSales = getTotalSales();
                writer.println("**TOTAL SALES** $" + VendingMachine.DECIMAL_FORMAT.format(totalSales.add(pastSpentMoney)));
            } catch (IOException e) {
                UserOutput.displayMessage(e.getMessage(), true);
            }
            
        } else {
            String fileName = generateSalesReport();
            File newFile = new File(fileName);
            newFile.renameTo(file);
        }
    }

    public static void generateBothSalesReports() {
        generateCumulativeSalesReport();
        generateSalesReport();
    }
}
