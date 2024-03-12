package Model;

public class Turn {
    private int hostPlay = 1;
    private int guestPlay = 1;
    private int hostMove = 3;
    private int guestMove = 3;

    public void changeHostPlay() {
        hostPlay -= 1;
    }

    public void changeGuestPlay() {
        guestPlay -= 1;
    }

    public void changeHostMove() {
        hostMove -= 1;
    }

    public void changeGuestMove() {
        guestMove -= 1;
    }

    public int getHostPlay() {
        return hostPlay;
    }

    public int getGuestPlay() {
        return guestPlay;
    }

    public int getHostMove() {
        return hostMove;
    }

    public int getGuestMove() {
        return guestMove;
    }
}
