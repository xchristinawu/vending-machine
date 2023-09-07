package com.techelevator.ui;

import java.util.ArrayList;
import java.util.List;

public class Icons {
    private static List<String> constructDrinkIcon() {
        List<String> drinkList = new ArrayList<>();
        drinkList.add("  .=.  ");
        drinkList.add("  } {  ");
        drinkList.add(" .' '. ");
        drinkList.add("/     \\");
        drinkList.add(";'---';");
        drinkList.add("|DRINK|");
        drinkList.add("| .-. |");
        drinkList.add("| '-' |");
        drinkList.add("|'---'|");
        drinkList.add("'._._.'");
        return drinkList;
    }

    private static List<String> constructGumIcon() {
        List<String> gumList = new ArrayList<>();
        gumList.add("                  ");
        gumList.add("                  ");
        gumList.add("                  ");
        gumList.add("  _______________ ");
        gumList.add(" /_____GUM______/|");
        gumList.add("/______________/|/");
        gumList.add("|______________|/ ");
        gumList.add("                  ");
        gumList.add("                  ");
        gumList.add("                  ");
        return gumList;
    }

    private static List<String> constructCandyIcon() {
        List<String> candyList = new ArrayList<>();
        candyList.add("                          ");
        candyList.add("           ____           ");
        candyList.add("___      .' /:::.      ___");
        candyList.add("\\  \"-.  /  (:::-'\\  .-\"  /");
        candyList.add(" >_-=.\\/:\\__\\/__  \\/.=-_< ");
        candyList.add(" > -='/\\::::/\\:::\\/\\'=- < ");
        candyList.add("/__.-'  \\::'  )::/  '-.__\\");
        candyList.add("         '.__/::'         ");
        candyList.add("                          ");
        candyList.add("                          ");
        return candyList;
    }

    private static List<String> constructChipIcon() {
        List<String> chipList = new ArrayList<>();
        chipList.add(" _______________ ");
        chipList.add("[_______________]");
        chipList.add("|===============|");
        chipList.add("|               |");
        chipList.add("|     CHIPS     |");
        chipList.add("|               |");
        chipList.add("|               |");
        chipList.add("|               |");
        chipList.add("|               |");
        chipList.add("|===============|");
        return chipList;
    }

    public static List<String> getIconAsList(String iconType) {
        List<String> icon = null;
        switch (iconType) {
            case "class com.techelevator.models.Drink":
                icon = constructDrinkIcon();
                break;
            case "class com.techelevator.models.Gum":
                icon = constructGumIcon();
                break;
            case "class com.techelevator.models.Candy":
                icon = constructCandyIcon();
                break;
            case "class com.techelevator.models.Chip":
                icon = constructChipIcon();
                break;
        }
        return icon;
    }

    public static void printIconsSideBySIde() {
        List<List<String>> allIcons = new ArrayList<>();
        allIcons.add(constructDrinkIcon());
        allIcons.add(constructGumIcon());
        allIcons.add(constructCandyIcon());
        allIcons.add(constructChipIcon());

        UserOutput.displayAllIconsSideBySide(allIcons);
    }

}
