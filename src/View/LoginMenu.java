package View;
import Model.*;
import Controller.Controller;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private Controller controller;

    public LoginMenu(Controller controller) {
        this.controller = controller;
    }

    public void run(Scanner scanner){
        String command= scanner.nextLine();
        while (!command.equals("Exit")){
            //System.out.println(command);
            Matcher matcherRegister = Commands.getMatcher(command, Commands.REGEX1);
            Matcher matcherLogin = Commands.getMatcher(command, Commands.REGEX4);
            if(matcherRegister.find()){
                String username= matcherRegister.group("username");
                String password= matcherRegister.group("password");
                System.out.println(controller.register(username, password));
            }else if(matcherLogin.find()){
                String username= matcherLogin.group("username");
                String password= matcherLogin.group("password");
                String stringLogin= controller.login(username, password);
                if(stringLogin.equals("User " + username +" logged in!")){
                    System.out.println(stringLogin);
                    Data.setCurrentUser(Data.getUserByUsername(username));
                    MainMenu mainMenu= new MainMenu(controller);
                    mainMenu.run(scanner);
                }else System.out.println(stringLogin);
            }else if(command.equals("show current menu")){
                System.out.println("Register/Login Menu");
            }else System.out.println("Invalid command!");
            command= scanner.nextLine();
        }
    }
}
