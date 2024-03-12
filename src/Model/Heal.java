package Model;

public class Heal implements Card, Spell{
    private int passTurn= 0;

    public int getPassTurn() {
        return passTurn;
    }

    public void changePassTurn(){
        passTurn +=1;
    }

    @Override
    public int price() {
        return 150;
    }

    @Override
    public int power() {
        return 1000;
    }

    private User owner;

    @Override
    public String name() {
        return "Heal";
    }

    @Override
    public User owner() {
        return owner;
    }

    public Heal(User owner) {
        this.owner = owner;
    }
}
