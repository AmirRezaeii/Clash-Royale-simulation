package Model;

public class BabyDragon implements Card, Troop{
    private int hitPoint= 3300;

    public void changeHitPoint(int amount){
        hitPoint -= amount;
        if(hitPoint <= 0) hitPoint= 0;
        if(hitPoint >= 3300) hitPoint= 3300;
    }
    @Override
    public int price() {
        return 200;
    }

    @Override
    public int power() {
        return 1200;
    }

    @Override
    public int hitPoint() {
        return hitPoint;
    }

    private User owner;

    @Override
    public String name() {
        return "Baby Dragon";
    }

    @Override
    public User owner() {
        return owner;
    }

    public BabyDragon(User owner) {
        this.owner = owner;
    }
}
