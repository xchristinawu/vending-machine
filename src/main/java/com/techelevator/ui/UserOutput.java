package com.techelevator.ui;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.Snack;
import com.techelevator.view.Colors;
import com.techelevator.view.Console;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class UserOutput {

    public static final String VENDO_MATIC = " __   _____ _  _ ___   ___      __  __   _ _____ ___ ___   ___  __   __  \n" +
            " \\ \\ / / __| \\| |   \\ / _ \\ ___|  \\/  | /_\\_   _|_ _/ __| ( _ )/  \\ /  \\ \n" +
            "  \\ V /| _|| .` | |) | (_) |___| |\\/| |/ _ \\| |  | | (__  / _ \\ () | () |\n" +
            "   \\_/ |___|_|\\_|___/ \\___/    |_|  |_/_/ \\_\\_| |___\\___| \\___/\\__/ \\__/ \n";

    private static final String HOME = "  _  _  ___  __  __ ___  \n" +
            " | || |/ _ \\|  \\/  | __| \n" +
            " | __ | (_) | |\\/| | _|  \n" +
            " |_||_|\\___/|_|  |_|___| \n";

    private static final String ITEMS = "  ___ _____ ___ __  __ ___ \n" +
            " |_ _|_   _| __|  \\/  / __|\n" +
            "  | |  | | | _|| |\\/| \\__ \\\n" +
            " |___| |_| |___|_|  |_|___/ \n";

    private static final String PURCHASE = "  ___ _   _ ___  ___ _  _   _   ___ ___ \n" +
            " | _ \\ | | | _ \\/ __| || | /_\\ / __| __|\n" +
            " |  _/ |_| |   / (__| __ |/ _ \\\\__ \\ _| \n" +
            " |_|  \\___/|_|_\\\\___|_||_/_/ \\_\\___/___|\n";

    public static final String FEED_MONEY = "  ___ ___ ___ ___    __  __  ___  _  _ _____   __\n" +
            " | __| __| __|   \\  |  \\/  |/ _ \\| \\| | __\\ \\ / /\n" +
            " | _|| _|| _|| |) | | |\\/| | (_) | .` | _| \\ V / \n" +
            " |_| |___|___|___/  |_|  |_|\\___/|_|\\_|___| |_|  \n";

    public static void displayHomeScreen() {
        System.out.println();
//        System.out.println(Colors.BLUE + "*" + Colors.RED + "*" + Colors.BLUE + "*" + Colors.YELLOW+ "Home" +
//                Colors.BLUE + "*" + Colors.RED + "*" + Colors.BLUE + "*" + Colors.RESET);
        System.out.println(Colors.CYAN + HOME + Colors.RESET);
        System.out.println();
        System.out.println(Colors.RED+ "1) "+ Colors.BLUE+"Display Vending Machine Items" + Colors.RESET);
        System.out.println(Colors.RED+"2) " + Colors.BLUE + "Purchase" + Colors.RESET);
        System.out.println(Colors.RED+"3) " + Colors.BLUE+ "Exit" + Colors.RESET);
        System.out.println();
        System.out.print(Colors.YELLOW +"Choose an option: " + Colors.RESET);
    }

    public static void displayPurchaseScreen(BigDecimal currentMoneyProvided) {
        System.out.println(Console.CLEAR_SCREEN);
        System.out.println();
//        System.out.println(Colors.BLUE + "*" + Colors.RED + "*" + Colors.BLUE + "*" + Colors.YELLOW +" Purchase " +
//                Colors.BLUE + "*" + Colors.RED + "*" + Colors.BLUE + "*" + Colors.RESET);
        System.out.println(Colors.CYAN + PURCHASE + Colors.RESET);
        System.out.println();
        System.out.println(Colors.YELLOW + "Current Money Provided:" + Colors.GREEN +" $" +
                VendingMachine.DECIMAL_FORMAT.format(currentMoneyProvided)+ Colors.RESET);
        System.out.println();
        System.out.println(Colors.RED + "1)" + Colors.BLUE + " Feed Money" + Colors.RESET);
        System.out.println(Colors.RED + "2)" +Colors.BLUE + " Select Product" + Colors.RESET);
        System.out.println(Colors.RED + "3)" +Colors.BLUE + " Finish Transaction" + Colors.RESET);
        System.out.println();
        System.out.print(Colors.YELLOW + "Choose an option: " + Colors.RESET);
    }

    public static void displayInventory(Map<String, Snack> snacks) {
        System.out.println(Console.CLEAR_SCREEN);
        System.out.println();
        System.out.println(Colors.CYAN + ITEMS + Colors.RESET);
        for (Map.Entry<String, Snack> snack : snacks.entrySet()) {
            String quantityString = Colors.YELLOW + "Quantity: " + snack.getValue().getQuantity();
            boolean snackIsSoldOut = snack.getValue().getQuantity() == 0;
            if (snackIsSoldOut) {
                quantityString = Colors.RED + "SOLD OUT" + Colors.RESET;
            }
            System.out.println(Colors.RED + snack.getKey() + ": " + Colors.BLUE+ snack.getValue().getName() + Colors.GREEN + " - $" +
                    VendingMachine.DECIMAL_FORMAT.format(snack.getValue().getPrice()) + ", " + Colors.YELLOW +
                    quantityString + Colors.RESET);
        }
        System.out.println();
    }

    public static void selectProductDisplay(Map<String, Snack> snacks) {
        displayInventory(snacks);
        System.out.print(Colors.YELLOW + "Choose an option: " + Colors.RESET);
    }

    public static void displaySelectedSnackMessage(Snack selectedSnack, BigDecimal inputMoney) {
        System.out.println(Colors.YELLOW+ "You have purchased " + Colors.BLUE+ selectedSnack.getName() + Colors.YELLOW +
                " for" + Colors.GREEN+" $" + VendingMachine.DECIMAL_FORMAT.format(selectedSnack.getPrice()) +
                Colors.YELLOW + ". You now have" + Colors.GREEN+ " $" + VendingMachine.DECIMAL_FORMAT.format(inputMoney)
                + "." + Colors.RESET);
        System.out.println(Colors.RED + selectedSnack.getMessage() + Colors.RESET);
    }

    public static void displayIcon(List<String> icon) {
        for (String s : icon) {
            System.out.println(s);
        }
    }

    public static void displayAllIconsSideBySide(List<List<String>> allIcons) {
        System.out.println();
        int numberOfRowsPerIcon = 10;
        for (int i = 0; i < numberOfRowsPerIcon; i++) {
            System.out.print("    ");
            for (List<String> iconList : allIcons) {
                String line = iconList.get(i);
                System.out.print("    ");
                System.out.print(line);
                System.out.print("    ");
            }
            System.out.println();
        }
    }

    public static void displayMessage(String message, boolean goToNextLine) {
        if (goToNextLine) {
            System.out.println(message);
        } else {
            System.out.print(message);
        }
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            UserOutput.displayMessage(e.getMessage(), false);
        }
    }
}
