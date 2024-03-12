package Model;

public class Castle {
    private int heatPoint;
    private int power;

    public void setHeatPoint(int heatPoint) {
        this.heatPoint = heatPoint;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public int getHeatPoint() {
        return heatPoint;
    }

    public void changeHeatPoint(int amount) {
        heatPoint -= amount;
        if (heatPoint <= 0) heatPoint = -1;
    }
}

