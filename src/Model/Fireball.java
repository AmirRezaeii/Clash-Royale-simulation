package Model;

public class Fireball implements Card, Spell{
    @Override
    public int price() {
        return 100;
    }

    @Override
    public int power() {
        return 1600;
    }

    private User owner;

    @Override
    public String name() {
        return "Fireball";
    }

    @Override
    public User owner() {
        return owner;
    }

    public Fireball(User owner) {
        this.owner = owner;
    }
}
