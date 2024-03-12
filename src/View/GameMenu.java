package View;
import Model.*;
import Controller.Controller;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private Controller controller;

    public GameMenu(Controller controller) {
        this.controller = controller;
    }
    public void run(Scanner scanner) {
        String command = scanner.nextLine();
        while (true) {
            //System.out.println(command);
            Matcher matcherShowLineInfo = Commands.getMatcher(command, Commands.REGEX11);
            Matcher matcherMoveTroop = Commands.getMatcher(command, Commands.REGEX12);
            Matcher matcherDeployTroop = Commands.getMatcher(command, Commands.REGEX13);
            Matcher matcherDeployHeal = Commands.getMatcher(command, Commands.REGEX14);
            Matcher matcherDeployFireball = Commands.getMatcher(command, Commands.REGEX15);
            if (command.equals("show the hitpoints left of my opponent")) {
                System.out.println(controller.showHitPointsOfOpponent());
            } else if (matcherShowLineInfo.find()) {
                String line = matcherShowLineInfo.group("line");
                System.out.print(controller.showLineInfo(line));
            } else if (command.equals("number of cards to play")) {
                if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                    System.out.println("You can play " + Data.getCurrentGame().getCurrentTurn().getHostPlay() + " cards more!");
                else
                    System.out.println("You can play " + Data.getCurrentGame().getCurrentTurn().getGuestPlay() + " cards more!");
            } else if (command.equals("number of moves left")) {
                if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                    System.out.println("You have " + Data.getCurrentGame().getCurrentTurn().getHostMove() + " moves left!");
                else
                    System.out.println("You have " + Data.getCurrentGame().getCurrentTurn().getGuestMove() + " moves left!");
            } else if (matcherMoveTroop.find()) {
                String lineDirection = matcherMoveTroop.group("lineDirection");
                int rowNumber = Integer.parseInt(matcherMoveTroop.group("rowNumber"));
                String direction = matcherMoveTroop.group("direction");
                System.out.println(controller.moveTroop(lineDirection, rowNumber, direction));
            } else if (matcherDeployTroop.find()) {
                String troopName = matcherDeployTroop.group("troopName");
                String lineDirection = matcherDeployTroop.group("lineDirection");
                int rowNumber = Integer.parseInt(matcherDeployTroop.group("rowNumber"));
                System.out.println(controller.deployTroop(troopName, lineDirection, rowNumber));
            } else if (matcherDeployHeal.find()) {
                String lineDirection = matcherDeployHeal.group("lineDirection");
                int rowNumber = Integer.parseInt(matcherDeployHeal.group("rowNumber"));
                System.out.println(controller.deployHeal(lineDirection, rowNumber));
            } else if (matcherDeployFireball.find()) {
                String lineDirection = matcherDeployFireball.group("lineDirection");
                System.out.println(controller.deployFireball(lineDirection));
            } else if (command.equals("next turn")) {
                String stringNextTurn = controller.nextTurn();
                System.out.println(stringNextTurn);
                if (stringNextTurn.startsWith("Game has ended")) break;
            } else if (command.equals("show current menu")) {
                System.out.println("Game Menu");
            } else System.out.println("Invalid command!");
            command = scanner.nextLine();
        }
    }
}
