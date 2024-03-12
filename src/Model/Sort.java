package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Sort implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        int levelCompare = Integer.compare(user2.getLevel() , user1.getLevel());
        int experienceCompare = Integer.compare(user2.getExperience(),user1.getExperience());
        int usernameCompare = user1.getUsername().compareTo(user2.getUsername());

        if(levelCompare != 0) return levelCompare;
        else if (experienceCompare != 0) return experienceCompare;
        else return usernameCompare;
    }
}
