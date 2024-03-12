package Controller;

import Model.*;
import View.*;

import java.util.ArrayList;

public class Controller {

    private LoginMenu loginMenu;
    private MainMenu mainMenu;
    private GameMenu gameMenu;
    private ProfileMenu profileMenu;
    private ShopMenu shopMenu;

    public Controller() {
        loginMenu = new LoginMenu(this);
        mainMenu = new MainMenu(this);
        gameMenu = new GameMenu(this);
        profileMenu = new ProfileMenu(this);
        shopMenu = new ShopMenu(this);
    }

    public String showHitPointsOfOpponent() {
        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser())) {
            return "middle castle: " + Data.getCurrentGame().getGuestPlayerMiddleCastle().getHeatPoint() + "\n" +
                    "left castle: " + Data.getCurrentGame().getGuestPlayerLeftCastle().getHeatPoint() + "\n" +
                    "right castle: " + Data.getCurrentGame().getGuestPlayerRightCastle().getHeatPoint();
        } else {
            return "middle castle: " + Data.getCurrentGame().getHostPlayerMiddleCastle().getHeatPoint() + "\n" +
                    "left castle: " + Data.getCurrentGame().getHostPlayerLeftCastle().getHeatPoint() + "\n" +
                    "right castle: " + Data.getCurrentGame().getHostPlayerRightCastle().getHeatPoint();
        }
    }

    public String showLineInfo(String line) {
        String stingShowLineInfo = line + " line:\n";
        int index = 1;
        if (line.equals("left")) {
            return showLineInfoLeft(stingShowLineInfo);
        } else if (line.equals("middle")) {
            return showLineInfoMiddle(stingShowLineInfo);
        } else if (line.equals("right")) {
            return showLineInfoRight(stingShowLineInfo);
        } else return "Incorrect line direction!\n";
    }

    private String showLineInfoLeft(String stingShowLineInfo) {
        int index = 1;
        for (Box box : Data.getCurrentGame().getLeftLine().getBoxes()) {
            if (box != null) {
                for (Card card : box.getCards()) {
                    if (!card.name().equals("chertCard"))
                        stingShowLineInfo = stingShowLineInfo + "row " + index + ": " + card.name() + ": " + card.owner().getUsername() + "\n";
                }
                index++;
            }
        }
        return stingShowLineInfo;
    }

    private String showLineInfoMiddle(String stingShowLineInfo) {
        int index = 1;
        for (Box box : Data.getCurrentGame().getMiddleLine().getBoxes()) {
            if (box != null) {
                for (Card card : box.getCards()) {
                    if (!card.name().equals("chertCard"))
                        stingShowLineInfo = stingShowLineInfo + "row " + index + ": " + card.name() + ": " + card.owner().getUsername() + "\n";
                }
                index++;
            }
        }
        return stingShowLineInfo;
    }

    private String showLineInfoRight(String stingShowLineInfo) {
        int index = 1;
        for (Box box : Data.getCurrentGame().getRightLine().getBoxes()) {
            if (box != null) {
                for (Card card : box.getCards()) {
                    if (!card.name().equals("chertCard"))
                        stingShowLineInfo = stingShowLineInfo + "row " + index + ": " + card.name() + ": " + card.owner().getUsername() + "\n";
                }
                index++;
            }
        }
        return stingShowLineInfo;
    }

    public String moveTroop(String lineDirection, int rowNumber, String direction) {
        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser())) {
            if (lineDirection.equals("left")) {
                return moveTroopForHostLeftLine(lineDirection, rowNumber, direction);
            } else if (lineDirection.equals("middle")) {
                return moveTroopForHostMiddleLine(lineDirection, rowNumber, direction);
            } else if (lineDirection.equals("right")) {
                return moveTroopForHostRightLine(lineDirection, rowNumber, direction);
            } else return "Incorrect line direction!";
        } else {
            if (lineDirection.equals("left")) {
                return moveTroopForGuestLeftLine(lineDirection, rowNumber, direction);
            } else if (lineDirection.equals("middle")) {
                return moveTroopForGuestMiddleLine(lineDirection, rowNumber, direction);
            } else if (lineDirection.equals("right")) {
                return moveTroopForGuestRightLine(lineDirection, rowNumber, direction);
            } else return "Incorrect line direction!";
        }
    }

    private String moveTroopForHostLeftLine(String lineDirection, int rowNumber, String direction) {
        if (rowNumber > 15 || rowNumber < 1) return "Invalid row number!";
        if (direction.equals("upward")) {
            if (Data.getCurrentGame().getCurrentTurn().getHostMove() == 0) return "You are out of moves!";
            for (Card card : Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 1).getCards()) {
                if (!card.name().equals("chertCard") && !card.getClass().equals(Heal.class)) {
                    if (card.owner().equals(Data.getCurrentUser())) {
                        if (rowNumber == 15) return "Invalid move!";
                        Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber).getCards().add(card);
                        Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 1).getCards().remove(card);
                        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                            Data.getCurrentGame().getCurrentTurn().changeHostMove();
                        else Data.getCurrentGame().getCurrentTurn().changeGuestMove();
                        return card.name() + " moved successfully to row " + (rowNumber + 1) + " in line " + lineDirection;
                    }
                }
            }
            return "You don't have any troops in this place!";
        } else if (direction.equals("downward")) {
            if (Data.getCurrentGame().getCurrentTurn().getHostMove() == 0) return "You are out of moves!";
            for (Card card : Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 1).getCards()) {
                if (!card.name().equals("chertCard") && !card.getClass().equals(Heal.class)) {
                    if (card.owner().equals(Data.getCurrentUser())) {
                        if (rowNumber == 1) return "Invalid move!";
                        Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 2).getCards().add(card);
                        Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 1).getCards().remove(card);
                        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                            Data.getCurrentGame().getCurrentTurn().changeHostMove();
                        else Data.getCurrentGame().getCurrentTurn().changeGuestMove();
                        return card.name() + " moved successfully to row " + (rowNumber - 1) + " in line " + lineDirection;
                    }
                }
            }
            return "You don't have any troops in this place!";
        } else return "you can only move troops upward or downward!";
    }

    private String moveTroopForHostMiddleLine(String lineDirection, int rowNumber, String direction) {
        if (rowNumber > 15 || rowNumber < 1) return "Invalid row number!";
        if (direction.equals("upward")) {
            if (Data.getCurrentGame().getCurrentTurn().getHostMove() == 0) return "You are out of moves!";
            for (Card card : Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 1).getCards()) {
                if (!card.name().equals("chertCard") && !card.getClass().equals(Heal.class)) {
                    if (card.owner().equals(Data.getCurrentUser())) {
                        if (rowNumber == 15) return "Invalid move!";
                        Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber).getCards().add(card);
                        Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 1).getCards().remove(card);
                        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                            Data.getCurrentGame().getCurrentTurn().changeHostMove();
                        else Data.getCurrentGame().getCurrentTurn().changeGuestMove();
                        return card.name() + " moved successfully to row " + (rowNumber + 1) + " in line " + lineDirection;
                    }
                }
            }
            return "You don't have any troops in this place!";
        } else if (direction.equals("downward")) {
            if (Data.getCurrentGame().getCurrentTurn().getHostMove() == 0) return "You are out of moves!";
            for (Card card : Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 1).getCards()) {
                if (!card.name().equals("chertCard") && !card.getClass().equals(Heal.class)) {
                    if (card.owner().equals(Data.getCurrentUser())) {
                        if (rowNumber == 1) return "Invalid move!";
                        Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 2).getCards().add(card);
                        Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 1).getCards().remove(card);
                        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                            Data.getCurrentGame().getCurrentTurn().changeHostMove();
                        else Data.getCurrentGame().getCurrentTurn().changeGuestMove();
                        return card.name() + " moved successfully to row " + (rowNumber - 1) + " in line " + lineDirection;
                    }
                }
            }
            return "You don't have any troops in this place!";
        } else return "you can only move troops upward or downward!";
    }

    private String moveTroopForHostRightLine(String lineDirection, int rowNumber, String direction) {
        if (rowNumber > 15 || rowNumber < 1) return "Invalid row number!";
        if (direction.equals("upward")) {
            if (Data.getCurrentGame().getCurrentTurn().getHostMove() == 0) return "You are out of moves!";
            for (Card card : Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 1).getCards()) {
                if (!card.name().equals("chertCard") && !card.getClass().equals(Heal.class)) {
                    if (card.owner().equals(Data.getCurrentUser())) {
                        if (rowNumber == 15) return "Invalid move!";
                        Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber).getCards().add(card);
                        Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 1).getCards().remove(card);
                        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                            Data.getCurrentGame().getCurrentTurn().changeHostMove();
                        else Data.getCurrentGame().getCurrentTurn().changeGuestMove();
                        return card.name() + " moved successfully to row " + (rowNumber + 1) + " in line " + lineDirection;
                    }
                }
            }
            return "You don't have any troops in this place!";
        } else if (direction.equals("downward")) {
            if (Data.getCurrentGame().getCurrentTurn().getHostMove() == 0) return "You are out of moves!";
            for (Card card : Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 1).getCards()) {
                if (!card.name().equals("chertCard") && !card.getClass().equals(Heal.class)) {
                    if (card.owner().equals(Data.getCurrentUser())) {
                        if (rowNumber == 1) return "Invalid move!";
                        Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 2).getCards().add(card);
                        Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 1).getCards().remove(card);
                        Data.getCurrentGame().getCurrentTurn().changeHostMove();
                        return card.name() + " moved successfully to row " + (rowNumber - 1) + " in line " + lineDirection;
                    }
                }
            }
            return "You don't have any troops in this place!";
        } else return "you can only move troops upward or downward!";
    }

    private String moveTroopForGuestLeftLine(String lineDirection, int rowNumber, String direction) {
        if (rowNumber > 15 || rowNumber < 1) return "Invalid row number!";
        if (direction.equals("upward")) {
            if (Data.getCurrentGame().getCurrentTurn().getGuestMove() == 0) return "You are out of moves!";
            for (Card card : Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 1).getCards()) {
                if (!card.name().equals("chertCard") && !card.getClass().equals(Heal.class)) {
                    if (card.owner().equals(Data.getCurrentUserInGame())) {
                        if (rowNumber == 15) return "Invalid move!";
                        Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber).getCards().add(card);
                        Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 1).getCards().remove(card);
                        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                            Data.getCurrentGame().getCurrentTurn().changeHostMove();
                        else Data.getCurrentGame().getCurrentTurn().changeGuestMove();
                        return card.name() + " moved successfully to row " + (rowNumber + 1) + " in line " + lineDirection;
                    }
                }
            }
            return "You don't have any troops in this place!";
        } else if (direction.equals("downward")) {
            if (Data.getCurrentGame().getCurrentTurn().getGuestMove() == 0) return "You are out of moves!";
            for (Card card : Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 1).getCards()) {
                if (!card.name().equals("chertCard") && !card.getClass().equals(Heal.class)) {
                    if (card.owner().equals(Data.getCurrentUserInGame())) {
                        if (rowNumber == 1) return "Invalid move!";
                        Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 2).getCards().add(card);
                        Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 1).getCards().remove(card);
                        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                            Data.getCurrentGame().getCurrentTurn().changeHostMove();
                        else Data.getCurrentGame().getCurrentTurn().changeGuestMove();
                        return card.name() + " moved successfully to row " + (rowNumber - 1) + " in line " + lineDirection;
                    }
                }
            }
            return "You don't have any troops in this place!";
        } else return "you can only move troops upward or downward!";
    }

    private String moveTroopForGuestMiddleLine(String lineDirection, int rowNumber, String direction) {
        if (rowNumber > 15 || rowNumber < 1) return "Invalid row number!";
        if (direction.equals("upward")) {
            if (Data.getCurrentGame().getCurrentTurn().getGuestMove() == 0) return "You are out of moves!";
            for (Card card : Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 1).getCards()) {
                if (!card.name().equals("chertCard") && !card.getClass().equals(Heal.class)) {
                    if (card.owner().equals(Data.getCurrentUserInGame())) {
                        if (rowNumber == 15) return "Invalid move!";
                        Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber).getCards().add(card);
                        Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 1).getCards().remove(card);
                        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                            Data.getCurrentGame().getCurrentTurn().changeHostMove();
                        else Data.getCurrentGame().getCurrentTurn().changeGuestMove();
                        return card.name() + " moved successfully to row " + (rowNumber + 1) + " in line " + lineDirection;
                    }
                }
            }
            return "You don't have any troops in this place!";
        } else if (direction.equals("downward")) {
            if (Data.getCurrentGame().getCurrentTurn().getGuestMove() == 0) return "You are out of moves!";
            for (Card card : Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 1).getCards()) {
                if (!card.name().equals("chertCard") && !card.getClass().equals(Heal.class)) {
                    if (card.owner().equals(Data.getCurrentUserInGame())) {
                        if (rowNumber == 1) return "Invalid move!";
                        Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 2).getCards().add(card);
                        Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 1).getCards().remove(card);
                        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                            Data.getCurrentGame().getCurrentTurn().changeHostMove();
                        else Data.getCurrentGame().getCurrentTurn().changeGuestMove();
                        return card.name() + " moved successfully to row " + (rowNumber - 1) + " in line " + lineDirection;
                    }
                }
            }
            return "You don't have any troops in this place!";
        } else return "you can only move troops upward or downward!";
    }

    private String moveTroopForGuestRightLine(String lineDirection, int rowNumber, String direction) {
        if (rowNumber > 15 || rowNumber < 1) return "Invalid row number!";
        if (direction.equals("upward")) {
            if (Data.getCurrentGame().getCurrentTurn().getGuestMove() == 0) return "You are out of moves!";
            for (Card card : Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 1).getCards()) {
                if (!card.name().equals("chertCard") && !card.getClass().equals(Heal.class)) {
                    if (card.owner().equals(Data.getCurrentUserInGame())) {
                        if (rowNumber == 15) return "Invalid move!";
                        Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber).getCards().add(card);
                        Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 1).getCards().remove(card);
                        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                            Data.getCurrentGame().getCurrentTurn().changeHostMove();
                        else Data.getCurrentGame().getCurrentTurn().changeGuestMove();
                        return card.name() + " moved successfully to row " + (rowNumber + 1) + " in line " + lineDirection;
                    }
                }
            }
            return "You don't have any troops in this place!";
        } else if (direction.equals("downward")) {
            if (Data.getCurrentGame().getCurrentTurn().getGuestMove() == 0) return "You are out of moves!";
            for (Card card : Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 1).getCards()) {
                if (!card.name().equals("chertCard") && !card.getClass().equals(Heal.class)) {
                    if (card.owner().equals(Data.getCurrentUserInGame())) {
                        if (rowNumber == 1) return "Invalid move!";
                        Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 2).getCards().add(card);
                        Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 1).getCards().remove(card);
                        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                            Data.getCurrentGame().getCurrentTurn().changeHostMove();
                        else Data.getCurrentGame().getCurrentTurn().changeGuestMove();
                        return card.name() + " moved successfully to row " + (rowNumber - 1) + " in line " + lineDirection;
                    }
                }
            }
            return "You don't have any troops in this place!";
        } else return "you can only move troops upward or downward!";
    }

    public String deployTroop(String troopName, String lineDirection, int rowNumber) {
        if (!troopName.equals("Barbarian") && !troopName.equals("Baby Dragon") && !troopName.equals("Ice Wizard"))
            return "Invalid troop name!";
        for (Card card : Data.getCurrentUserInGame().getDeck()) {
            if (card.name().equals(troopName)) {
                if (!lineDirection.equals("left") && !lineDirection.equals("middle") && !lineDirection.equals("right"))
                    return "Incorrect line direction!";
                if (rowNumber > 15 || rowNumber < 1) return "Invalid row number!";
                if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()) && rowNumber > 4)
                    return "Deploy your troops near your castles!";
                if (!Data.getCurrentUserInGame().equals(Data.getCurrentUser()) && rowNumber < 12)
                    return "Deploy your troops near your castles!";
                if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()) && Data.getCurrentGame().getCurrentTurn().getHostPlay() == 0)
                    return "You have deployed a troop or spell this turn!";
                if (!Data.getCurrentUserInGame().equals(Data.getCurrentUser()) && Data.getCurrentGame().getCurrentTurn().getGuestPlay() == 0)
                    return "You have deployed a troop or spell this turn!";
                if (card.getClass().equals(Barbarian.class)) {
                    return deployTroopBarbarian(troopName, lineDirection, rowNumber);
                } else if (card.getClass().equals(BabyDragon.class)) {
                    return deployTroopBabyDragon(troopName, lineDirection, rowNumber);
                } else if (card.getClass().equals(IceWizard.class)) {
                    return deployTroopIceWizard(troopName, lineDirection, rowNumber);
                }
            }
        }
        return "You don't have " + troopName + " card in your battle deck!";
    }

    private String deployTroopBarbarian(String troopName, String lineDirection, int rowNumber) {
        Barbarian currentCard = new Barbarian(Data.getCurrentUserInGame());
        if (lineDirection.equals("left")) {
            Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 1).getCards().add(currentCard);
            if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                Data.getCurrentGame().getCurrentTurn().changeHostPlay();
            else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
            return "You have deployed " + troopName + " successfully!";
        } else if (lineDirection.equals("middle")) {
            Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 1).getCards().add(currentCard);
            if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                Data.getCurrentGame().getCurrentTurn().changeHostPlay();
            else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
            return "You have deployed " + troopName + " successfully!";
        } else {
            Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 1).getCards().add(currentCard);
            if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                Data.getCurrentGame().getCurrentTurn().changeHostPlay();
            else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
            return "You have deployed " + troopName + " successfully!";
        }
    }

    private String deployTroopBabyDragon(String troopName, String lineDirection, int rowNumber) {
        BabyDragon currentCard = new BabyDragon(Data.getCurrentUserInGame());
        if (lineDirection.equals("left")) {
            Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 1).getCards().add(currentCard);
            if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                Data.getCurrentGame().getCurrentTurn().changeHostPlay();
            else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
            return "You have deployed " + troopName + " successfully!";
        } else if (lineDirection.equals("middle")) {
            Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 1).getCards().add(currentCard);
            if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                Data.getCurrentGame().getCurrentTurn().changeHostPlay();
            else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
            return "You have deployed " + troopName + " successfully!";
        } else {
            Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 1).getCards().add(currentCard);
            if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                Data.getCurrentGame().getCurrentTurn().changeHostPlay();
            else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
            return "You have deployed " + troopName + " successfully!";
        }
    }

    private String deployTroopIceWizard(String troopName, String lineDirection, int rowNumber) {
        IceWizard currentCard = new IceWizard(Data.getCurrentUserInGame());
        if (lineDirection.equals("left")) {
            Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 1).getCards().add(currentCard);
            if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                Data.getCurrentGame().getCurrentTurn().changeHostPlay();
            else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
            return "You have deployed " + troopName + " successfully!";
        } else if (lineDirection.equals("middle")) {
            Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 1).getCards().add(currentCard);
            if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                Data.getCurrentGame().getCurrentTurn().changeHostPlay();
            else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
            return "You have deployed " + troopName + " successfully!";
        } else {
            Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 1).getCards().add(currentCard);
            if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                Data.getCurrentGame().getCurrentTurn().changeHostPlay();
            else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
            return "You have deployed " + troopName + " successfully!";
        }
    }

    public String deployHeal(String lineDirection, int rowNumber) {
        if (!lineDirection.equals("left") && !lineDirection.equals("middle") && !lineDirection.equals("right"))
            return "Incorrect line direction!";
        for (Card card : Data.getCurrentUserInGame().getDeck()) {
            if (card.name().equals("Heal")) {
                Heal heal = new Heal(Data.getCurrentUserInGame());
                if (rowNumber > 15 || rowNumber < 1) return "Invalid row number!";
                if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()) && Data.getCurrentGame().getCurrentTurn().getHostPlay() == 0)
                    return "You have deployed a troop or spell this turn!";
                if (!Data.getCurrentUserInGame().equals(Data.getCurrentUser()) && Data.getCurrentGame().getCurrentTurn().getGuestPlay() == 0)
                    return "You have deployed a troop or spell this turn!";
                if (lineDirection.equals("left")) {
                    Data.getCurrentGame().getLeftLine().getBoxes().get(rowNumber - 1).getCards().add(heal);
                    if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                        Data.getCurrentGame().getCurrentTurn().changeHostPlay();
                    else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
                    return "You have deployed Heal successfully!";
                } else if (lineDirection.equals("middle")) {
                    Data.getCurrentGame().getMiddleLine().getBoxes().get(rowNumber - 1).getCards().add(heal);
                    if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                        Data.getCurrentGame().getCurrentTurn().changeHostPlay();
                    else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
                    return "You have deployed Heal successfully!";
                } else {
                    Data.getCurrentGame().getRightLine().getBoxes().get(rowNumber - 1).getCards().add(heal);
                    if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
                        Data.getCurrentGame().getCurrentTurn().changeHostPlay();
                    else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
                    return "You have deployed Heal successfully!";
                }
            }
        }
        return "You don't have Heal card in your battle deck!";
    }

    public String deployFireball(String lineDirection) {
        if (!lineDirection.equals("left") && !lineDirection.equals("middle") && !lineDirection.equals("right"))
            return "Incorrect line direction!";
        for (Card card : Data.getCurrentUserInGame().getDeck()) {
            if (card.name().equals("Fireball")) {
                if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()) && Data.getCurrentGame().getCurrentTurn().getHostPlay() == 0)
                    return "You have deployed a troop or spell this turn!";
                if (!Data.getCurrentUserInGame().equals(Data.getCurrentUser()) && Data.getCurrentGame().getCurrentTurn().getGuestPlay() == 0)
                    return "You have deployed a troop or spell this turn!";
                Fireball fireball = new Fireball(Data.getCurrentUserInGame());
                if (lineDirection.equals("left")) {
                    return deployFireballInLeft(fireball);
                } else if (lineDirection.equals("middle")) {
                    return deployFireballInMiddle(fireball);
                } else {
                    return deployFireballInRight(fireball);
                }
            }
        }
        return "You don't have Heal card in your battle deck!";
    }

    private String deployFireballInLeft(Fireball fireball) {
        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser())) {
            if (Data.getCurrentGame().getGuestPlayerLeftCastle().getHeatPoint() == -1)
                return "This castle is already destroyed!";
            Data.getCurrentGame().getGuestPlayerLeftCastle().changeHeatPoint(fireball.power());
        } else {
            if (Data.getCurrentGame().getHostPlayerLeftCastle().getHeatPoint() == -1)
                return "This castle is already destroyed!";
            Data.getCurrentGame().getHostPlayerLeftCastle().changeHeatPoint(fireball.power());
        }
        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
            Data.getCurrentGame().getCurrentTurn().changeHostPlay();
        else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
        return "You have deployed Fireball successfully!";
    }

    private String deployFireballInMiddle(Fireball fireball) {
        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser())) {
            if (Data.getCurrentGame().getGuestPlayerMiddleCastle().getHeatPoint() == -1)
                return "This castle is already destroyed!";
            Data.getCurrentGame().getGuestPlayerMiddleCastle().changeHeatPoint(fireball.power());
        } else {
            if (Data.getCurrentGame().getHostPlayerMiddleCastle().getHeatPoint() == -1)
                return "This castle is already destroyed!";
            Data.getCurrentGame().getHostPlayerMiddleCastle().changeHeatPoint(fireball.power());
        }
        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
            Data.getCurrentGame().getCurrentTurn().changeHostPlay();
        else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
        return "You have deployed Fireball successfully!";
    }

    private String deployFireballInRight(Fireball fireball) {
        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser())) {
            if (Data.getCurrentGame().getGuestPlayerRightCastle().getHeatPoint() == -1)
                return "This castle is already destroyed!";
            Data.getCurrentGame().getGuestPlayerRightCastle().changeHeatPoint(fireball.power());
        } else {
            if (Data.getCurrentGame().getHostPlayerRightCastle().getHeatPoint() == -1)
                return "This castle is already destroyed!";
            Data.getCurrentGame().getHostPlayerRightCastle().changeHeatPoint(fireball.power());
        }
        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser()))
            Data.getCurrentGame().getCurrentTurn().changeHostPlay();
        else Data.getCurrentGame().getCurrentTurn().changeGuestPlay();
        return "You have deployed Fireball successfully!";
    }

    public String nextTurn() {
        if (Data.getCurrentUserInGame().equals(Data.getCurrentUser())) {
            Data.setCurrentUserInGame(Data.getCurrentGame().getGuest());
        } else {
            Data.setCurrentUserInGame(Data.getCurrentGame().getHost());
            Data.getCurrentGame().nextTurn();
            changesInLeftCastle();
            changesInMiddleCastle();
            changesInRightCastle();
            changesInLeftCard();
            changesInMiddleCard();
            changesInRightCard();
        }
        return checkForEndGame();
    }

    private String checkForEndGame(){
        int numberOfNoRemainForHost = 0;
        int numberOfNoRemainForGuest = 0;
        int numberOfHitPointsForHost = 0;
        int numberOfHitPointsForGuest = 0;

        if (Data.getCurrentGame().getGuestPlayerLeftCastle().getHeatPoint() == -1) numberOfNoRemainForGuest += 1;
        else numberOfHitPointsForGuest += Data.getCurrentGame().getGuestPlayerLeftCastle().getHeatPoint();
        if (Data.getCurrentGame().getGuestPlayerMiddleCastle().getHeatPoint() == -1) numberOfNoRemainForGuest += 1;
        else numberOfHitPointsForGuest += Data.getCurrentGame().getGuestPlayerMiddleCastle().getHeatPoint();
        if (Data.getCurrentGame().getGuestPlayerRightCastle().getHeatPoint() == -1) numberOfNoRemainForGuest += 1;
        else numberOfHitPointsForGuest += Data.getCurrentGame().getGuestPlayerRightCastle().getHeatPoint();

        if (Data.getCurrentGame().getHostPlayerLeftCastle().getHeatPoint() == -1) numberOfNoRemainForHost += 1;
        else numberOfHitPointsForHost += Data.getCurrentGame().getHostPlayerLeftCastle().getHeatPoint();
        if (Data.getCurrentGame().getHostPlayerMiddleCastle().getHeatPoint() == -1) numberOfNoRemainForGuest += 1;
        else numberOfHitPointsForHost += Data.getCurrentGame().getHostPlayerMiddleCastle().getHeatPoint();
        if (Data.getCurrentGame().getHostPlayerRightCastle().getHeatPoint() == -1) numberOfNoRemainForGuest += 1;
        else numberOfHitPointsForHost += Data.getCurrentGame().getHostPlayerRightCastle().getHeatPoint();

        if (Data.getCurrentGame().getNumberOfTurns() == 0 ||
                (Data.getCurrentUserInGame().equals(Data.getCurrentUser()) && (numberOfNoRemainForHost == 3 || numberOfNoRemainForGuest == 3))) {
            afterGame(numberOfNoRemainForHost, numberOfNoRemainForGuest, numberOfHitPointsForHost, numberOfHitPointsForGuest);
            if (numberOfNoRemainForHost < numberOfNoRemainForGuest)
                return "Game has ended. Winner: " + Data.getCurrentGame().getHost().getUsername();
            else if (numberOfNoRemainForHost > numberOfNoRemainForGuest)
                return "Game has ended. Winner: " + Data.getCurrentGame().getGuest().getUsername();
            else {
                if (numberOfHitPointsForHost > numberOfHitPointsForGuest)
                    return "Game has ended. Winner: " + Data.getCurrentGame().getHost().getUsername();
                else if (numberOfHitPointsForHost < numberOfHitPointsForGuest)
                    return "Game has ended. Winner: " + Data.getCurrentGame().getGuest().getUsername();
                else return "Game has ended. Result: Tie";
            }
        }
        return "Player " + Data.getCurrentUserInGame().getUsername() + " is now playing!";
    }

    private void changesInLeftCastle(){
        for (Card card : Data.getCurrentGame().getLeftLine().getBoxes().get(0).getCards()) {
            if (!card.name().equals("chertCard")) {
                if (card.owner().equals(Data.getCurrentGame().getGuest())) {
                    if (card.getClass().equals(Barbarian.class)) {
                        Data.getCurrentGame().getHostPlayerLeftCastle().changeHeatPoint(((Barbarian) card).power());
                        ((Barbarian) card).changeHitPoint(Data.getCurrentGame().getHostPlayerLeftCastle().getPower());
                    } else if (card.getClass().equals(BabyDragon.class)) {
                        Data.getCurrentGame().getHostPlayerLeftCastle().changeHeatPoint(((BabyDragon) card).power());
                        ((BabyDragon) card).changeHitPoint(Data.getCurrentGame().getHostPlayerLeftCastle().getPower());
                    } else if (card.getClass().equals(IceWizard.class)) {
                        Data.getCurrentGame().getHostPlayerLeftCastle().changeHeatPoint(((IceWizard) card).power());
                        ((IceWizard) card).changeHitPoint(Data.getCurrentGame().getHostPlayerLeftCastle().getPower());
                    }
                }
            }
        }
        for (Card card : Data.getCurrentGame().getLeftLine().getBoxes().get(14).getCards()) {
            if (!card.name().equals("chertCard")) {
                if (card.owner().equals(Data.getCurrentGame().getHost())) {
                    if (card.getClass().equals(Barbarian.class)) {
                        Data.getCurrentGame().getGuestPlayerLeftCastle().changeHeatPoint(((Barbarian) card).power());
                        ((Barbarian) card).changeHitPoint(Data.getCurrentGame().getGuestPlayerLeftCastle().getPower());
                    } else if (card.getClass().equals(BabyDragon.class)) {
                        Data.getCurrentGame().getGuestPlayerLeftCastle().changeHeatPoint(((BabyDragon) card).power());
                        ((BabyDragon) card).changeHitPoint(Data.getCurrentGame().getGuestPlayerLeftCastle().getPower());
                    } else if (card.getClass().equals(IceWizard.class)) {
                        Data.getCurrentGame().getGuestPlayerLeftCastle().changeHeatPoint(((IceWizard) card).power());
                        ((IceWizard) card).changeHitPoint(Data.getCurrentGame().getGuestPlayerLeftCastle().getPower());
                    }
                }
            }
        }

    }

    private void changesInLeftCard(){
        for (Box box : Data.getCurrentGame().getLeftLine().getBoxes()) {
            if (box != null) {
                for (Card card1 : box.getCards()) {
                    if (!card1.name().equals("chertCard")) {
                        if (card1.getClass().equals(Heal.class)) ((Heal) card1).changePassTurn();
                        for (Card card2 : box.getCards()) {
                            if (!card2.name().equals("chertCard")) {
                                if (!card1.owner().equals(card2.owner())) {
                                    if (card1.getClass().equals(Barbarian.class)) {
                                        if (card2.getClass().equals(BabyDragon.class))
                                            ((Barbarian) card1).changeHitPoint(((BabyDragon) card2).power() - ((Barbarian) card1).power());
                                        else if (card2.getClass().equals(IceWizard.class))
                                            ((Barbarian) card1).changeHitPoint(((IceWizard) card2).power() - ((Barbarian) card1).power());
                                    } else if (card1.getClass().equals(BabyDragon.class)) {
                                        if (card2.getClass().equals(IceWizard.class))
                                            ((BabyDragon) card1).changeHitPoint(((IceWizard) card2).power() - ((BabyDragon) card1).power());
                                    } else if (card1.getClass().equals(Heal.class)) {
                                        if (card2.getClass().equals(Barbarian.class))
                                            ((Barbarian) card2).changeHitPoint(-(((Heal) card1).power()));
                                        else if (card2.getClass().equals(BabyDragon.class))
                                            ((BabyDragon) card2).changeHitPoint(-(((Heal) card1).power()));
                                        else if (card2.getClass().equals(IceWizard.class))
                                            ((IceWizard) card2).changeHitPoint(-(((Heal) card1).power()));
                                    }
                                }
                            }
                        }
                    }
                }
                ArrayList<Card> cpy = new ArrayList<>(box.getCards());
                for (Card card : cpy) {
                    if (card.getClass().equals(Heal.class)) {
                        if (((Heal) card).getPassTurn() == 2) box.getCards().remove(card);
                    } else if (card.getClass().equals(Barbarian.class)) {
                        if (((Barbarian) card).hitPoint() == 0) box.getCards().remove(card);
                    } else if (card.getClass().equals(BabyDragon.class)) {
                        if (((BabyDragon) card).hitPoint() == 0) box.getCards().remove(card);
                    } else if (card.getClass().equals(IceWizard.class)) {
                        if (((IceWizard) card).hitPoint() == 0) box.getCards().remove(card);
                    }
                }
            }
        }
    }

    private void changesInMiddleCastle(){
        for (Card card : Data.getCurrentGame().getMiddleLine().getBoxes().get(0).getCards()) {
            if (!card.name().equals("chertCard")) {
                if (card.owner().equals(Data.getCurrentGame().getGuest())) {
                    if (card.getClass().equals(Barbarian.class)) {
                        Data.getCurrentGame().getHostPlayerMiddleCastle().changeHeatPoint(((Barbarian) card).power());
                        ((Barbarian) card).changeHitPoint(Data.getCurrentGame().getHostPlayerMiddleCastle().getPower());
                    } else if (card.getClass().equals(BabyDragon.class)) {
                        Data.getCurrentGame().getHostPlayerMiddleCastle().changeHeatPoint(((BabyDragon) card).power());
                        ((BabyDragon) card).changeHitPoint(Data.getCurrentGame().getHostPlayerMiddleCastle().getPower());
                    } else if (card.getClass().equals(IceWizard.class)) {
                        Data.getCurrentGame().getHostPlayerMiddleCastle().changeHeatPoint(((IceWizard) card).power());
                        ((IceWizard) card).changeHitPoint(Data.getCurrentGame().getHostPlayerMiddleCastle().getPower());
                    }
                }
            }
        }
        for (Card card : Data.getCurrentGame().getMiddleLine().getBoxes().get(14).getCards()) {
            if (!card.name().equals("chertCard")) {
                if (card.owner().equals(Data.getCurrentGame().getHost())) {
                    if (card.getClass().equals(Barbarian.class)) {
                        Data.getCurrentGame().getGuestPlayerMiddleCastle().changeHeatPoint(((Barbarian) card).power());
                        ((Barbarian) card).changeHitPoint(Data.getCurrentGame().getGuestPlayerMiddleCastle().getPower());
                    } else if (card.getClass().equals(BabyDragon.class)) {
                        Data.getCurrentGame().getGuestPlayerMiddleCastle().changeHeatPoint(((BabyDragon) card).power());
                        ((BabyDragon) card).changeHitPoint(Data.getCurrentGame().getGuestPlayerMiddleCastle().getPower());
                    } else if (card.getClass().equals(IceWizard.class)) {
                        Data.getCurrentGame().getGuestPlayerMiddleCastle().changeHeatPoint(((IceWizard) card).power());
                        ((IceWizard) card).changeHitPoint(Data.getCurrentGame().getGuestPlayerMiddleCastle().getPower());
                    }
                }
            }
        }

    }

    private void changesInMiddleCard(){
        for (Box box : Data.getCurrentGame().getMiddleLine().getBoxes()) {
            if (box != null) {
                for (Card card1 : box.getCards()) {
                    if (!card1.name().equals("chertCard")) {
                        if (card1.getClass().equals(Heal.class)) ((Heal) card1).changePassTurn();
                        for (Card card2 : box.getCards()) {
                            if (!card2.name().equals("chertCard")) {
                                if (!card1.owner().equals(card2.owner())) {
                                    if (card1.getClass().equals(Barbarian.class)) {
                                        if (card2.getClass().equals(BabyDragon.class))
                                            ((Barbarian) card1).changeHitPoint(((BabyDragon) card2).power() - ((Barbarian) card1).power());
                                        else if (card2.getClass().equals(IceWizard.class))
                                            ((Barbarian) card1).changeHitPoint(((IceWizard) card2).power() - ((Barbarian) card1).power());
                                    } else if (card1.getClass().equals(BabyDragon.class)) {
                                        if (card2.getClass().equals(IceWizard.class))
                                            ((BabyDragon) card1).changeHitPoint(((IceWizard) card2).power() - ((BabyDragon) card1).power());
                                    } else if (card1.getClass().equals(Heal.class)) {
                                        if (card2.getClass().equals(Barbarian.class))
                                            ((Barbarian) card2).changeHitPoint(-(((Heal) card1).power()));
                                        else if (card2.getClass().equals(BabyDragon.class))
                                            ((BabyDragon) card2).changeHitPoint(-(((Heal) card1).power()));
                                        else if (card2.getClass().equals(IceWizard.class))
                                            ((IceWizard) card2).changeHitPoint(-(((Heal) card1).power()));
                                    }
                                }
                            }
                        }
                    }
                }
                ArrayList<Card> cpy = new ArrayList<>(box.getCards());
                for (Card card : cpy) {
                    if (card.getClass().equals(Heal.class)) {
                        if (((Heal) card).getPassTurn() == 2) box.getCards().remove(card);
                    } else if (card.getClass().equals(Barbarian.class)) {
                        if (((Barbarian) card).hitPoint() == 0) box.getCards().remove(card);
                    } else if (card.getClass().equals(BabyDragon.class)) {
                        if (((BabyDragon) card).hitPoint() == 0) box.getCards().remove(card);
                    } else if (card.getClass().equals(IceWizard.class)) {
                        if (((IceWizard) card).hitPoint() == 0) box.getCards().remove(card);
                    }
                }
            }
        }
    }

    private void changesInRightCastle(){
        for (Card card : Data.getCurrentGame().getRightLine().getBoxes().get(0).getCards()) {
            if (!card.name().equals("chertCard")) {
                if (card.owner().equals(Data.getCurrentGame().getGuest())) {
                    if (card.getClass().equals(Barbarian.class)) {
                        Data.getCurrentGame().getHostPlayerRightCastle().changeHeatPoint(((Barbarian) card).power());
                        ((Barbarian) card).changeHitPoint(Data.getCurrentGame().getHostPlayerRightCastle().getPower());
                    } else if (card.getClass().equals(BabyDragon.class)) {
                        Data.getCurrentGame().getHostPlayerRightCastle().changeHeatPoint(((BabyDragon) card).power());
                        ((BabyDragon) card).changeHitPoint(Data.getCurrentGame().getHostPlayerRightCastle().getPower());
                    } else if (card.getClass().equals(IceWizard.class)) {
                        Data.getCurrentGame().getHostPlayerRightCastle().changeHeatPoint(((IceWizard) card).power());
                        ((IceWizard) card).changeHitPoint(Data.getCurrentGame().getHostPlayerRightCastle().getPower());
                    }
                }
            }
        }
        for (Card card : Data.getCurrentGame().getRightLine().getBoxes().get(14).getCards()) {
            if (!card.name().equals("chertCard")) {
                if (card.owner().equals(Data.getCurrentGame().getHost())) {
                    if (card.getClass().equals(Barbarian.class)) {
                        Data.getCurrentGame().getGuestPlayerRightCastle().changeHeatPoint(((Barbarian) card).power());
                        ((Barbarian) card).changeHitPoint(Data.getCurrentGame().getGuestPlayerRightCastle().getPower());
                    } else if (card.getClass().equals(BabyDragon.class)) {
                        Data.getCurrentGame().getGuestPlayerRightCastle().changeHeatPoint(((BabyDragon) card).power());
                        ((BabyDragon) card).changeHitPoint(Data.getCurrentGame().getGuestPlayerRightCastle().getPower());
                    } else if (card.getClass().equals(IceWizard.class)) {
                        Data.getCurrentGame().getGuestPlayerRightCastle().changeHeatPoint(((IceWizard) card).power());
                        ((IceWizard) card).changeHitPoint(Data.getCurrentGame().getGuestPlayerRightCastle().getPower());
                    }
                }
            }
        }
    }

    private void changesInRightCard(){
        for (Box box : Data.getCurrentGame().getRightLine().getBoxes()) {
            if (box != null) {
                for (Card card1 : box.getCards()) {
                    if (!card1.name().equals("chertCard")) {
                        if (card1.getClass().equals(Heal.class)) ((Heal) card1).changePassTurn();
                        for (Card card2 : box.getCards()) {
                            if (!card2.name().equals("chertCard")) {
                                if (!card1.owner().equals(card2.owner())) {
                                    if (card1.getClass().equals(Barbarian.class)) {
                                        if (card2.getClass().equals(BabyDragon.class))
                                            ((Barbarian) card1).changeHitPoint(((BabyDragon) card2).power() - ((Barbarian) card1).power());
                                        else if (card2.getClass().equals(IceWizard.class))
                                            ((Barbarian) card1).changeHitPoint(((IceWizard) card2).power() - ((Barbarian) card1).power());
                                    } else if (card1.getClass().equals(BabyDragon.class)) {
                                        if (card2.getClass().equals(IceWizard.class))
                                            ((BabyDragon) card1).changeHitPoint(((IceWizard) card2).power() - ((BabyDragon) card1).power());
                                    } else if (card1.getClass().equals(Heal.class)) {
                                        if (card2.getClass().equals(Barbarian.class))
                                            ((Barbarian) card2).changeHitPoint(-(((Heal) card1).power()));
                                        else if (card2.getClass().equals(BabyDragon.class))
                                            ((BabyDragon) card2).changeHitPoint(-(((Heal) card1).power()));
                                        else if (card2.getClass().equals(IceWizard.class))
                                            ((IceWizard) card2).changeHitPoint(-(((Heal) card1).power()));
                                    }
                                }
                            }
                        }
                    }
                }
                ArrayList<Card> cpy = new ArrayList<>(box.getCards());
                for (Card card : cpy) {
                    if (card.getClass().equals(Heal.class)) {
                        if (((Heal) card).getPassTurn() == 2) box.getCards().remove(card);
                    } else if (card.getClass().equals(Barbarian.class)) {
                        if (((Barbarian) card).hitPoint() == 0) box.getCards().remove(card);
                    } else if (card.getClass().equals(BabyDragon.class)) {
                        if (((BabyDragon) card).hitPoint() == 0) box.getCards().remove(card);
                    } else if (card.getClass().equals(IceWizard.class)) {
                        if (((IceWizard) card).hitPoint() == 0) box.getCards().remove(card);
                    }
                }
            }
        }
    }

    private void afterGame(int numberOfNoRemainForHost, int numberOfNoRemainForGuest, int numberOfHitPointsForHost, int numberOfHitPointsForGuest) {
        Data.getCurrentGame().getHost().changeExperienceAndLevel(numberOfHitPointsForHost);
        Data.getCurrentGame().getGuest().changeExperienceAndLevel(numberOfHitPointsForGuest);
        Data.getCurrentGame().getHost().increaseGold(numberOfNoRemainForGuest * 25);
        Data.getCurrentGame().getGuest().increaseGold(numberOfNoRemainForHost * 25);
    }

    public String register(String username, String password) {
        if (!Commands.getMatcher(username, Commands.REGEX2).find()) return "Incorrect format for username!";
        if (!Commands.getMatcher(password, Commands.REGEX3).find()) return "Incorrect format for password!";
        if (Data.getUserByUsername(username) != null) return "Username already exists!";
        User user = new User(username, password, 100, 0, 1);
        Barbarian barbarian = new Barbarian(user);
        Fireball fireball = new Fireball(user);
        user.addCart(barbarian);
        user.addCart(fireball);
        user.addToDeck(barbarian);
        user.addToDeck(fireball);
        Data.addUser(user);
        Data.setCurrentUser(user);
        return "User " + username + " created successfully!";
    }

    public String login(String username, String password) {
        if (!Commands.getMatcher(username, Commands.REGEX2).find()) return "Incorrect format for username!";
        if (!Commands.getMatcher(password, Commands.REGEX3).find()) return "Incorrect format for password!";
        if (Data.getUserByUsername(username) == null) return "Username doesn't exist!";
        if (!Data.getUserByUsername(username).getPassword().equals(password)) return "Password is incorrect!";
        return "User " + username + " logged in!";
    }

    public String listOfUsers() {
        String listOfUsers = new String();
        int index = 1;
        for (User user : Data.getUsers()) {
            listOfUsers = listOfUsers + "user " + index + ": " + user.getUsername() + "\n";
            index++;
        }
        return listOfUsers;
    }

    public String scoreboard() {
        String stringScoreBoard = new String();
        int index = 1;
        for (User user : Data.getRanks()) {
            if (index < 6) {
                stringScoreBoard = stringScoreBoard + index + "- username: " + user.getUsername() + " level: " + user.getLevel() + " experience: " + user.getExperience() + "\n";
                index++;
            }
        }
        return stringScoreBoard;
    }

    public String startGame(int turnsCount, String username) {
        if (turnsCount < 5 || turnsCount > 30) return "Invalid turns count!";
        if (!Commands.getMatcher(username, Commands.REGEX2).find()) return "Incorrect format for username!";
        if (Data.getUserByUsername(username) == null) return "Username doesn't exist!";
        GameBoard gameBoard = new GameBoard(Data.getCurrentUser(), Data.getUserByUsername(username), turnsCount);
        Data.setCurrentGame(gameBoard);
        return "Battle started with user " + username;
    }

    public String changePassword(String oldPassword, String newPassword) {
        if (!Data.getCurrentUser().getPassword().equals(oldPassword)) return "Incorrect password!";
        if (!Commands.getMatcher(newPassword, Commands.REGEX3).find()) return "Incorrect format for new password!";
        Data.getCurrentUser().changePassword(newPassword);
        return "Password changed successfully!";
    }

    public String info() {
        User user = Data.getCurrentUser();
        int rank = 1;
        for (User userForFindRank : Data.getRanks()) {
            if (userForFindRank.equals(user)) break;
            rank++;
        }
        return "username: " + user.getUsername() + "\n" +
                "password: " + user.getPassword() + "\n" +
                "level: " + user.getLevel() + "\n" +
                "experience: " + user.getExperience() + "\n" +
                "gold: " + user.getGold() + "\n" +
                "rank: " + rank;
    }

    public String removeFromDeck(String name) {
        if (name.equals("Fireball")) {
            return removeFromDeckFireball(name);
        } else if (name.equals("Heal")) {
            return removeFromDeckHeal(name);
        } else if (name.equals("Barbarian")) {
            return removeFromDeckBarbarian(name);
        } else if (name.equals("Baby Dragon")) {
            return removeFromDeckBabyDragon(name);
        } else if (name.equals("Ice Wizard")) {
            return removeFromDeckIceWizard(name);
        }
        return "Invalid card name!";
    }

    private String removeFromDeckFireball(String name) {
        for (Card card : Data.getCurrentUser().getDeck()) {
            if (card.getClass().equals(Fireball.class)) {
                if (Data.getCurrentUser().getDeck().size() < 2)
                    return "Invalid action: your battle deck will be empty!";
                Data.getCurrentUser().removeFromDeck(card);
                return "Card " + name + " removed successfully!";
            }
        }
        return "This card isn't in your battle deck!";
    }

    private String removeFromDeckHeal(String name) {
        for (Card card : Data.getCurrentUser().getDeck()) {
            if (card.getClass().equals(Heal.class)) {
                if (Data.getCurrentUser().getDeck().size() < 2)
                    return "Invalid action: your battle deck will be empty!";
                Data.getCurrentUser().removeFromDeck(card);
                return "Card " + name + " removed successfully!";
            }
        }
        return "This card isn't in your battle deck!";
    }

    private String removeFromDeckBarbarian(String name) {
        for (Card card : Data.getCurrentUser().getDeck()) {
            if (card.getClass().equals(Barbarian.class)) {
                if (Data.getCurrentUser().getDeck().size() < 2)
                    return "Invalid action: your battle deck will be empty!";
                Data.getCurrentUser().removeFromDeck(card);
                return "Card " + name + " removed successfully!";
            }
        }
        return "This card isn't in your battle deck!";
    }

    private String removeFromDeckBabyDragon(String name) {
        for (Card card : Data.getCurrentUser().getDeck()) {
            if (card.getClass().equals(BabyDragon.class)) {
                if (Data.getCurrentUser().getDeck().size() < 2)
                    return "Invalid action: your battle deck will be empty!";
                Data.getCurrentUser().removeFromDeck(card);
                return "Card " + name + " removed successfully!";
            }
        }
        return "This card isn't in your battle deck!";
    }

    private String removeFromDeckIceWizard(String name) {
        for (Card card : Data.getCurrentUser().getDeck()) {
            if (card.getClass().equals(IceWizard.class)) {
                if (Data.getCurrentUser().getDeck().size() < 2)
                    return "Invalid action: your battle deck will be empty!";
                Data.getCurrentUser().removeFromDeck(card);
                return "Card " + name + " removed successfully!";
            }
        }
        return "This card isn't in your battle deck!";
    }

    public String addToDeck(String name) {
        if (name.equals("Fireball")) {
            return addToDeckFireball(name);
        } else if (name.equals("Heal")) {
            return addToDeckHeal(name);
        } else if (name.equals("Barbarian")) {
            return addToDeckBarbarian(name);
        } else if (name.equals("Baby Dragon")) {
            return addToDeckBabyDragon(name);
        } else if (name.equals("Ice Wizard")) {
            return addToDeckIceWizard(name);
        }
        return "Invalid card name!";
    }

    private String addToDeckFireball(String name) {
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(Fireball.class)) {
                for (Card cardInDeck : Data.getCurrentUser().getDeck()) {
                    if (cardInDeck.getClass().equals(Fireball.class))
                        return "This card is already in your battle deck!";
                }
                if (Data.getCurrentUser().getDeck().size() == 4)
                    return "Invalid action: your battle deck is full!";
                Data.getCurrentUser().addToDeck(card);
                return "Card " + name + " added successfully!";
            }
        }
        return "You don't have this card!";
    }

    private String addToDeckHeal(String name) {
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(Heal.class)) {
                for (Card cardInDeck : Data.getCurrentUser().getDeck()) {
                    if (cardInDeck.getClass().equals(Heal.class))
                        return "This card is already in your battle deck!";
                }
                if (Data.getCurrentUser().getDeck().size() == 4)
                    return "Invalid action: your battle deck is full!";
                Data.getCurrentUser().addToDeck(card);
                return "Card " + name + " added successfully!";
            }
        }
        return "You don't have this card!";
    }

    private String addToDeckBarbarian(String name) {
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(Barbarian.class)) {
                for (Card cardInDeck : Data.getCurrentUser().getDeck()) {
                    if (cardInDeck.getClass().equals(Barbarian.class))
                        return "This card is already in your battle deck!";
                }
                if (Data.getCurrentUser().getDeck().size() == 4)
                    return "Invalid action: your battle deck is full!";
                Data.getCurrentUser().addToDeck(card);
                return "Card " + name + " added successfully!";
            }
        }
        return "You don't have this card!";
    }

    private String addToDeckBabyDragon(String name) {
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(BabyDragon.class)) {
                for (Card cardInDeck : Data.getCurrentUser().getDeck()) {
                    if (cardInDeck.getClass().equals(BabyDragon.class))
                        return "This card is already in your battle deck!";
                }
                if (Data.getCurrentUser().getDeck().size() == 4)
                    return "Invalid action: your battle deck is full!";
                Data.getCurrentUser().addToDeck(card);
                return "Card " + name + " added successfully!";
            }
        }
        return "You don't have this card!";
    }

    private String addToDeckIceWizard(String name) {
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(IceWizard.class)) {
                for (Card cardInDeck : Data.getCurrentUser().getDeck()) {
                    if (cardInDeck.getClass().equals(IceWizard.class))
                        return "This card is already in your battle deck!";
                }
                if (Data.getCurrentUser().getDeck().size() == 4)
                    return "Invalid action: your battle deck is full!";
                Data.getCurrentUser().addToDeck(card);
                return "Card " + name + " added successfully!";
            }
        }
        return "You don't have this card!";
    }

    public String showDeck() {
        String stringShowDeck = new String();
        for (Card card : Data.getCurrentUser().getDeck()) {
            if (card.getClass().equals(BabyDragon.class)) stringShowDeck = stringShowDeck + "Baby Dragon\n";
        }
        for (Card card : Data.getCurrentUser().getDeck()) {
            if (card.getClass().equals(Barbarian.class)) stringShowDeck = stringShowDeck + "Barbarian\n";
        }
        for (Card card : Data.getCurrentUser().getDeck()) {
            if (card.getClass().equals(Fireball.class)) stringShowDeck = stringShowDeck + "Fireball\n";
        }
        for (Card card : Data.getCurrentUser().getDeck()) {
            if (card.getClass().equals(Heal.class)) stringShowDeck = stringShowDeck + "Heal\n";
        }
        for (Card card : Data.getCurrentUser().getDeck()) {
            if (card.getClass().equals(IceWizard.class)) stringShowDeck = stringShowDeck + "Ice Wizard\n";
        }
        return stringShowDeck;
    }

    public String byCard(String name) {
        if (name.equals("Fireball")) {
            return byCardFireball(name);
        } else if (name.equals("Heal")) {
            return byCardHeal(name);
        } else if (name.equals("Barbarian")) {
            return byCardBarbarian(name);
        } else if (name.equals("Baby Dragon")) {
            return byCardBabyDragon(name);
        } else if (name.equals("Ice Wizard")) {
            return byCardIceWizard(name);
        }
        return "Invalid card name!";
    }

    private String byCardFireball(String name){
        Fireball fireball = new Fireball(Data.getCurrentUser());
        if (Data.getCurrentUser().getGold() < fireball.price())
            return "Not enough gold to buy " + name + "!";
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(Fireball.class)) return "You have this card!";
        }
        Data.getCurrentUser().decreaseGold(fireball.price());
        Data.getCurrentUser().addCart(fireball);
        return "Card " + name + " bought successfully!";
    }

    private String byCardHeal(String name){
        Heal heal = new Heal(Data.getCurrentUser());
        if (Data.getCurrentUser().getGold() < heal.price()) return "Not enough gold to buy " + name + "!";
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(Heal.class)) return "You have this card!";
        }
        Data.getCurrentUser().decreaseGold(heal.price());
        Data.getCurrentUser().addCart(heal);
        return "Card " + name + " bought successfully!";
    }

    private String byCardBarbarian(String name){
        Barbarian barbarian = new Barbarian(Data.getCurrentUser());
        if (Data.getCurrentUser().getGold() < barbarian.price())
            return "Not enough gold to buy " + name + "!";
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(Barbarian.class)) return "You have this card!";
        }
        Data.getCurrentUser().decreaseGold(barbarian.price());
        Data.getCurrentUser().addCart(barbarian);
        return "Card " + name + " bought successfully!";
    }

    private String byCardBabyDragon(String name){
        BabyDragon babyDragon = new BabyDragon(Data.getCurrentUser());
        if (Data.getCurrentUser().getGold() < babyDragon.price())
            return "Not enough gold to buy " + name + "!";
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(BabyDragon.class)) return "You have this card!";
        }
        Data.getCurrentUser().decreaseGold(babyDragon.price());
        Data.getCurrentUser().addCart(babyDragon);
        return "Card " + name + " bought successfully!";
    }

    private String byCardIceWizard(String name){
        IceWizard iceWizard = new IceWizard(Data.getCurrentUser());
        if (Data.getCurrentUser().getGold() < iceWizard.price())
            return "Not enough gold to buy " + name + "!";
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(IceWizard.class)) return "You have this card!";
        }
        Data.getCurrentUser().decreaseGold(iceWizard.price());
        Data.getCurrentUser().addCart(iceWizard);
        return "Card " + name + " bought successfully!";
    }

    public String sellCard(String name) {
        if (name.equals("Fireball")) {
            return sellCardFireball(name);
        } else if (name.equals("Heal")) {
            return sellCardHeal(name);
        } else if (name.equals("Barbarian")) {
            return sellCardBarbarian(name);
        } else if (name.equals("Baby Dragon")) {
            return sellCardBabyDragon(name);
        } else if (name.equals("Ice Wizard")) {
            return sellCardIceWizard(name);
        }
        return "Invalid card name!";
    }

    private String sellCardFireball(String name){
        Fireball fireball = new Fireball(Data.getCurrentUser());
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(Fireball.class)) {
                for (Card cardInDeck : Data.getCurrentUser().getDeck()) {
                    if (cardInDeck.getClass().equals(Fireball.class))
                        return "You cannot sell a card from your battle deck!";
                }
                Data.getCurrentUser().increaseGold(fireball.price() * 4 / 5);
                Data.getCurrentUser().removeCard(card);
                return "Card " + name + " sold successfully!";
            }
        }
        return "You don't have this card!";
    }

    private String sellCardHeal(String name){
        Heal heal = new Heal(Data.getCurrentUser());
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(Heal.class)) {
                for (Card cardInDeck : Data.getCurrentUser().getDeck()) {
                    if (cardInDeck.getClass().equals(Heal.class))
                        return "You cannot sell a card from your battle deck!";
                }
                Data.getCurrentUser().increaseGold(heal.price() * 4 / 5);
                Data.getCurrentUser().removeCard(card);
                return "Card " + name + " sold successfully!";
            }
        }
        return "You don't have this card!";
    }

    private String sellCardBarbarian(String name){
        Barbarian barbarian = new Barbarian(Data.getCurrentUser());
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(Barbarian.class)) {
                for (Card cardInDeck : Data.getCurrentUser().getDeck()) {
                    if (cardInDeck.getClass().equals(Barbarian.class))
                        return "You cannot sell a card from your battle deck!";
                }
                Data.getCurrentUser().increaseGold(barbarian.price() * 4 / 5);
                Data.getCurrentUser().removeCard(card);
                return "Card " + name + " sold successfully!";
            }
        }
        return "You don't have this card!";
    }

    private String sellCardBabyDragon(String name){
        BabyDragon babyDragon = new BabyDragon(Data.getCurrentUser());
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(BabyDragon.class)) {
                for (Card cardInDeck : Data.getCurrentUser().getDeck()) {
                    if (cardInDeck.getClass().equals(BabyDragon.class))
                        return "You cannot sell a card from your battle deck!";
                }
                Data.getCurrentUser().increaseGold(babyDragon.price() * 4 / 5);
                Data.getCurrentUser().removeCard(card);
                return "Card " + name + " sold successfully!";
            }
        }
        return "You don't have this card!";
    }

    private String sellCardIceWizard(String name){
        IceWizard iceWizard = new IceWizard(Data.getCurrentUser());
        for (Card card : Data.getCurrentUser().getCards()) {
            if (card.getClass().equals(IceWizard.class)) {
                for (Card cardInDeck : Data.getCurrentUser().getDeck()) {
                    if (cardInDeck.getClass().equals(IceWizard.class))
                        return "You cannot sell a card from your battle deck!";
                }
                Data.getCurrentUser().increaseGold(iceWizard.price() * 4 / 5);
                Data.getCurrentUser().removeCard(card);
                return "Card " + name + " sold successfully!";
            }
        }
        return "You don't have this card!";
    }
}
