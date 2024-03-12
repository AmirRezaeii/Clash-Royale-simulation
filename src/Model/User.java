package Model;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private int gold;
    private int experience;
    private int level;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Card> deck = new ArrayList<>();

    public User(String username, String password, int gold, int experience, int level) {
        this.username = username;
        this.password = password;
        this.gold = gold;
        this.experience = experience;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getGold() {
        return gold;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public void addCart(Card card) {
        cards.add(card);
    }

    public void changePassword(String newPassword){
        password= newPassword;
    }

    public void removeFromDeck(Card card){
        deck.remove(card);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void addToDeck(Card card){
        deck.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void removeCard(Card card){
        cards.remove(card);
    }

    public void increaseGold(int amount){
        gold += amount;
    }

    public void decreaseGold(int amount){
        gold -= amount;
    }

    public void changeExperienceAndLevel(int amount){
        experience += amount;
        while(true){
            if(experience >= level * level * 160){
                experience -= level * level * 160;
                level ++;
            } else break;
        }
    }
}
