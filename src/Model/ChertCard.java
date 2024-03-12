package Model;

public class ChertCard implements Card{

    @Override
    public int price() {
        return 0;
    }

    @Override
    public String name() {
        return "chertCard";
    }

    @Override
    public User owner() {
        return null;
    }
}

