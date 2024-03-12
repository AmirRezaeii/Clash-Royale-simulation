package Model;

public class IceWizard implements Card, Troop{
    private int hitPoint= 3500;

    public void changeHitPoint(int amount){
        hitPoint -= amount;
        if(hitPoint <= 0) hitPoint= 0;
        if(hitPoint >= 3500) hitPoint= 3500;
    }
    @Override
    public int price() {
        return 180;
    }

    @Override
    public int power() {
        return 1500;
    }

    @Override
    public int hitPoint() {
        return hitPoint;
    }

    private User owner;

    @Override
    public String name() {
        return "Ice Wizard";
    }

    @Override
    public User owner() {
        return owner;
    }

    public IceWizard(User owner) {
        this.owner = owner;
    }
}

