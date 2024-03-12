package View;
import Model.*;
import Controller.Controller;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu {
    private Controller controller;

    public ShopMenu(Controller controller) {
        this.controller = controller;
    }

    public void run(Scanner scanner) {
        String command = scanner.nextLine();
        while (!command.equals("back")) {
            //System.out.println(command);
            Matcher matcherByCard = Commands.getMatcher(command, Commands.REGEX9);
            Matcher matcherSellCard = Commands.getMatcher(command, Commands.REGEX10);
            if (matcherByCard.find()) {
                String name = matcherByCard.group("name");
                System.out.println(controller.byCard(name));
            } else if (matcherSellCard.find()) {
                String name = matcherSellCard.group("name");
                System.out.println(controller.sellCard(name));
            }else if(command.equals("show current menu")){
                System.out.println("Shop Menu");
            } else System.out.println("Invalid command!");
            command = scanner.nextLine();
        }
        System.out.println("Entered main menu!");
    }
}
