package View;
import Model.*;
import Controller.*;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    private Controller controller;

    public MainMenu(Controller controller) {
        this.controller = controller;
    }

    public void run(Scanner scanner) {
        String command = scanner.nextLine();
        while (!command.equals("logout")) {
            //System.out.println(command);
            Matcher matcherStartGame = Commands.getMatcher(command, Commands.REGEX5);
            if (command.equals("list of users")) {
                System.out.print(controller.listOfUsers());
            } else if (command.equals("scoreboard")) {
                System.out.print(controller.scoreboard());
            } else if (command.equals("profile menu")) {
                System.out.println("Entered profile menu!");
                ProfileMenu profileMenu = new ProfileMenu(controller);
                profileMenu.run(scanner);
            } else if (command.equals("shop menu")) {
                System.out.println("Entered shop menu!");
                ShopMenu shopMenu = new ShopMenu(controller);
                shopMenu.run(scanner);
            } else if (matcherStartGame.find()) {
                int turnsCount = Integer.parseInt(matcherStartGame.group("turnsCount"));
                String username = matcherStartGame.group("username");
                String stringStartGame = controller.startGame(turnsCount, username);
                if (stringStartGame.startsWith("Battle started with user ")) {
                    System.out.println(stringStartGame);
                    Data.setCurrentUserInGame(Data.getCurrentUser());
                    GameMenu gameMenu = new GameMenu(controller);
                    gameMenu.run(scanner);
                } else System.out.println(stringStartGame);
            }else if(command.equals("show current menu")){
                System.out.println("Main Menu");
            } else System.out.println("Invalid command!");
            command = scanner.nextLine();
        }
        System.out.println("User " + Data.getCurrentUser().getUsername() + " logged out successfully!");
    }
}
