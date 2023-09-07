package com.techelevator.ui;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.fileio.Logger;
import com.techelevator.view.Colors;
import com.techelevator.view.Console;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserInput {
    static Scanner input = new Scanner(System.in);
    private static final String FEED_MONEY_INITIAL_MESSAGE = Colors.YELLOW +
            "Input money (only whole dollars accepted; do not input more than $100; press enter to exit): " +
            Colors.RESET;
    private static final String INVALID_PRODUCT_CODE_MESSAGE =
           Colors.RED + "Invalid product code. Returning to purchase screen." + Colors.RESET;
    private static final String INVALID_INPUT_MESSAGE = Colors.RED + "Invalid input, please try again." + Colors.RESET;
    private static final String BLANK_LINE = "";

    public static int getChoice() {

        while (true) {
            String userInput = input.nextLine();

            try {
                return Integer.parseInt(userInput);
            } catch (Exception e) {
                UserOutput.displayMessage(INVALID_INPUT_MESSAGE, true);
            }
        }
    }

    public static BigDecimal feedMoney(BigDecimal inputMoney) {

        BigDecimal moneyAdded = new BigDecimal("0");
        UserOutput.displayMessage(Console.CLEAR_SCREEN, true);
        UserOutput.displayMessage(Colors.CYAN + UserOutput.FEED_MONEY + Colors.RESET, true);

        while (true) {
            UserOutput.displayMessage(FEED_MONEY_INITIAL_MESSAGE, false);
            String userInput = input.nextLine();
            boolean isUserInputEmpty = userInput.length() == 0;

            String displayMoneyAdded = Colors.YELLOW + "You have added" + Colors.GREEN +" $" +
                    VendingMachine.DECIMAL_FORMAT.format(moneyAdded) + Colors.YELLOW +  ". Returning to purchase screen."
                    + Colors.RESET;

            if (isUserInputEmpty) {
                UserOutput.displayMessage(displayMoneyAdded, true);
                UserOutput.sleep(1500);
                return inputMoney;
            }

            boolean inputIsNotInWholeDollars = userInput.contains(".") && !userInput.endsWith(".00");
            String invalidInputDisplayMoneyAdded = Colors.YELLOW + "Invalid input. " + displayMoneyAdded;

            if (inputIsNotInWholeDollars) {
                UserOutput.displayMessage(invalidInputDisplayMoneyAdded, true);
                UserOutput.sleep(1500);
                return inputMoney;
            }

            try {
                BigDecimal userInputNumeric = new BigDecimal(userInput);
                boolean inputIsNotPositiveNum = userInputNumeric.compareTo(new BigDecimal("0")) < 1;
                boolean inputMoneyWouldBeGreaterThan100 = inputMoney.add(userInputNumeric).compareTo(new BigDecimal("100")) > 0;

                if (inputIsNotPositiveNum || inputMoneyWouldBeGreaterThan100) {
                    UserOutput.displayMessage(invalidInputDisplayMoneyAdded, true);
                    UserOutput.sleep(1500);
                    return inputMoney;
                }

                moneyAdded = moneyAdded.add(userInputNumeric);
                inputMoney = inputMoney.add(userInputNumeric);

                Logger.logMessage("FEED MONEY: $" + VendingMachine.DECIMAL_FORMAT.format(userInputNumeric) + " $" +
                        VendingMachine.DECIMAL_FORMAT.format(inputMoney));

            } catch (Exception e) {
                UserOutput.displayMessage(invalidInputDisplayMoneyAdded, true);
                UserOutput.sleep(1500);
                return inputMoney;
            }
        }
    }

    public static String purchaseChoice() {

        String userInput = input.nextLine().toUpperCase();
        boolean userInputIsNotValidLength = userInput.length() != 2;

        if (userInputIsNotValidLength) {
            UserOutput.displayMessage(INVALID_PRODUCT_CODE_MESSAGE, true);
            UserOutput.displayMessage(BLANK_LINE, true);
            UserOutput.sleep(1500);
        } else {
            String firstChar = userInput.substring(0, 1);
            String secondChar = userInput.substring(1, 2);

            boolean firstCharIsValid = (firstChar.equals("A") ||
                    firstChar.equals("B") || firstChar.equals("C") ||
                    firstChar.equals("D"));
            boolean secondCharIsValid = secondChar.equals("1") || secondChar.equals("2") ||
                    secondChar.equals("3") || secondChar.equals("4");

            if (!(firstCharIsValid && secondCharIsValid)) {
                UserOutput.displayMessage(INVALID_PRODUCT_CODE_MESSAGE, true);
                UserOutput.displayMessage(BLANK_LINE, true);
                UserOutput.sleep(1500);
            } else {
                UserOutput.displayMessage(BLANK_LINE, true);
                return userInput;
            }

        }
        UserOutput.displayMessage(BLANK_LINE, true);
        return "";
    }

    public static void getNextLine() {
        input.nextLine();
    }
}
