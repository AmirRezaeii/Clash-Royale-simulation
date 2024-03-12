package Model;

public class Barbarian implements Card, Troop{
    private int hitPoint= 2000;

    public void changeHitPoint(int amount){
        hitPoint -= amount;
        if(hitPoint <= 0) hitPoint= 0;
        if(hitPoint >= 2000) hitPoint= 2000;
    }
    @Override
    public int price() {
        return 100;
    }

    @Override
    public int power() {
        return 900;
    }

    @Override
    public int hitPoint() {
        return hitPoint;
    }

    private User owner;

    @Override
    public String name() {
        return "Barbarian";
    }

    @Override
    public User owner() {
        return owner;
    }

    public Barbarian(User owner) {
        this.owner = owner;
    }
}

