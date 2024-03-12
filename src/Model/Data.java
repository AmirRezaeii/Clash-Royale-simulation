package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Data {
    private static ArrayList<User> users= new ArrayList<>();
    private static User currentUser;
    private static User currentUserInGame;
    private static GameBoard currentGame;
    private static Sort sort= new Sort();
    public static void addUser(User user){
        users.add(user);
    }

    public static User getUserByUsername(String username){
        for (User user : users){
            if(user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public static void setCurrentUser(User currentUser) {
        Data.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static GameBoard getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(GameBoard currentGame) {
        Data.currentGame = currentGame;
    }

    public static User getCurrentUserInGame() {
        return currentUserInGame;
    }

    public static void setCurrentUserInGame(User currentUserInGame) {
        Data.currentUserInGame = currentUserInGame;
    }

    public static ArrayList<User> getRanks (){
        ArrayList<User> cpy= new ArrayList<>(users);
        Collections.sort(cpy, sort);
        return cpy;
    }
}

