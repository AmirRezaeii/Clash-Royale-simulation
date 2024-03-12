package View;

import Model.*;
import Controller.Controller;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private Controller controller;

    public ProfileMenu(Controller controller) {
        this.controller = controller;
    }

    public void run(Scanner scanner) {
        String command = scanner.nextLine();
        while (!command.equals("back")) {
            //System.out.println(command);
            Matcher matcherChangePassword = Commands.getMatcher(command, Commands.REGEX6);
            Matcher matcherRemoveFromDeck = Commands.getMatcher(command, Commands.REGEX7);
            Matcher matcherAddToDeck = Commands.getMatcher(command, Commands.REGEX8);
            if (matcherChangePassword.find()) {
                String oldPassword = matcherChangePassword.group("oldPassword");
                String newPassword = matcherChangePassword.group("newPassword");
                System.out.println(controller.changePassword(oldPassword, newPassword));
            } else if (command.equals("Info")) {
                System.out.println(controller.info());
            } else if (matcherRemoveFromDeck.find()) {
                String name = matcherRemoveFromDeck.group("name");
                System.out.println(controller.removeFromDeck(name));
            } else if (matcherAddToDeck.find()) {
                String name = matcherAddToDeck.group("name");
                System.out.println(controller.addToDeck(name));
            } else if (command.equals("show battle deck")) {
                System.out.print(controller.showDeck());
            } else if (command.equals("show current menu")) {
                System.out.println("Profile Menu");
            } else System.out.println("Invalid command!");
            command = scanner.nextLine();
        }
        System.out.println("Entered main menu!");
    }
}
