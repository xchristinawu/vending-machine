package com.techelevator.application;

import com.techelevator.models.Inventory;
import com.techelevator.models.fileio.SalesReport;
import com.techelevator.models.Snack;
import com.techelevator.models.fileio.Logger;
import com.techelevator.ui.Icons;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;
import com.techelevator.view.Colors;
import com.techelevator.view.Console;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class VendingMachine {
    private final Inventory inventory = new Inventory();
    private BigDecimal inputMoney = new BigDecimal("0");

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    private static final String RETURN_TO_HOME_SCREEN_MESSAGE = Colors.YELLOW + "Returning to home screen." +
            Colors.RESET;
    private static final String INSUFFICIENT_FUNDS_MESSAGE = Colors.RED + "Insufficient funds." + Colors.RESET;
    private static final String SOLD_OUT_MESSAGE = Colors.RED + "SOLD OUT." + Colors.YELLOW +
            "\nReturning to purchase screen." + Colors.RESET;
    private static final String INVALID_INPUT_MESSAGE = Colors.RED + "Input invalid." + Colors.RESET;
    private static final String THANK_YOU_MESSAGE = Colors.YELLOW + "Thank you for using " + Colors.RED +
            "Vendo-Matic" + Colors.BLUE + " 800." + Colors.YELLOW + " Have a good day!" + Colors.RESET;
    private static final String HOW_TO_RETURN_TO_HOME_SCREEN_MESSAGE = Colors.YELLOW +
            "Press Enter to return to Home screen." + Colors.RESET;

    public VendingMachine() {
        inventory.loadInventory("data/vendingmachine.csv");
        SalesReport.initializeSalesReport(inventory.getSnacks());
    }

    public void run() {

        while (true) {
            UserOutput.displayMessage(Console.CLEAR_SCREEN, false);
            UserOutput.displayMessage(Colors.CYAN + UserOutput.VENDO_MATIC + Colors.RESET, true);
            Icons.printIconsSideBySIde();
            UserOutput.displayHomeScreen();

            int homeScreenChoice = UserInput.getChoice();
            boolean displayVendingMachineItemsOptionIsSelected = homeScreenChoice == 1;
            boolean purchaseOptionSelected = homeScreenChoice == 2;
            boolean exitOptionSelected = homeScreenChoice == 3;
            boolean topSecretSalesReportOptionSelected = homeScreenChoice == 4;

            if (displayVendingMachineItemsOptionIsSelected) {
                handleDisplayVendingMachineItems();
            } else if (purchaseOptionSelected) {
                handlePurchase();
            } else if (exitOptionSelected) {
                SalesReport.generateBothSalesReports();
                UserOutput.displayMessage(THANK_YOU_MESSAGE, true);
                break;
            } else if (topSecretSalesReportOptionSelected) {
                SalesReport.generateSalesReport();
            } else {
                UserOutput.displayMessage(INVALID_INPUT_MESSAGE, true);
            }
        }
    }

    public void handleDisplayVendingMachineItems() {
        UserOutput.displayInventory(inventory.getSnacks());
        UserOutput.displayMessage(HOW_TO_RETURN_TO_HOME_SCREEN_MESSAGE, false);
        UserInput.getNextLine();
    }

    public void handlePurchase() {

        while (true) {

            UserOutput.displayPurchaseScreen(inputMoney);

            int purchaseScreenChoice = UserInput.getChoice();
            boolean feedMoneyOptionIsSelected = purchaseScreenChoice == 1;
            boolean selectProductOptionIsSelected = purchaseScreenChoice == 2;
            boolean finishTransactionOptionIsSelected = purchaseScreenChoice == 3;

            if (feedMoneyOptionIsSelected) {
                inputMoney = UserInput.feedMoney(inputMoney);
            } else if (selectProductOptionIsSelected) {
                UserOutput.selectProductDisplay(inventory.getSnacks());
                String productId = UserInput.purchaseChoice();

                boolean isInvalidProductId = productId.equals("");
                if (isInvalidProductId) {
                    continue;
                }

                purchaseSelectedSnack(productId);
            } else if (finishTransactionOptionIsSelected) {
                finishTransaction();
                break;
            } else {
                UserOutput.displayMessage(INVALID_INPUT_MESSAGE, true);
            }
        }
    }

    public void purchaseSelectedSnack(String productId) {
        Snack selectedSnack = inventory.getSnacks().get(productId);
        BigDecimal selectedSnackPrice = selectedSnack.getPrice();

        boolean selectedSnackIsSoldOut = selectedSnack.getQuantity() == 0;
        boolean inputMoneyIsNotEnoughToPurchaseSelectedSnack = selectedSnackPrice.compareTo(inputMoney) > 0;

        if (selectedSnackIsSoldOut) {
            UserOutput.displayMessage(SOLD_OUT_MESSAGE, true);
            UserOutput.sleep(1500);
        } else if (inputMoneyIsNotEnoughToPurchaseSelectedSnack) {
            UserOutput.displayMessage(INSUFFICIENT_FUNDS_MESSAGE, true);
            UserOutput.sleep(1500);
        } else {
            selectedSnack.deductSnackQuantityByOne();
            inputMoney = inputMoney.subtract(selectedSnackPrice);
            SalesReport.addSoldItemToSalesReport(selectedSnack);
            UserOutput.displayIcon(Icons.getIconAsList(selectedSnack.getClass().toString()));
            UserOutput.displaySelectedSnackMessage(selectedSnack, inputMoney);
            Logger.logMessage(selectedSnack.getName() + " " + productId + " $" +
                    DECIMAL_FORMAT.format(selectedSnackPrice) + " $" + DECIMAL_FORMAT.format(inputMoney));

            UserOutput.sleep(2000);
        }
    }

    public void finishTransaction() {
        UserOutput.displayMessage(getChange(inputMoney), true);
        UserOutput.displayMessage(RETURN_TO_HOME_SCREEN_MESSAGE, true);
        UserOutput.sleep(2500);

        boolean changeIsGiven = inputMoney.compareTo(new BigDecimal("0")) > 0;

        if (changeIsGiven) {
            Logger.logMessage("GIVE CHANGE: $" + DECIMAL_FORMAT.format(inputMoney) + " $0.00");
        }

        inputMoney = new BigDecimal("0");
    }

    public String getChange(BigDecimal inputMoney) {
        int cents = inputMoney.multiply(new BigDecimal("100")).intValue();
        int quarters = cents / 25;
        int leftoverAfterQuarters = cents % 25;
        int dimes = leftoverAfterQuarters / 10;
        int leftoverAfterDimes = leftoverAfterQuarters % 10;
        int nickels = leftoverAfterDimes / 5;

        return Colors.GREEN + "You have been dispensed " + quarters + " quarters, " + dimes + " dimes, " + nickels +
                " nickels." + Colors.RESET;
    }
}
