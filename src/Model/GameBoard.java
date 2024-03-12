package Model;

public class GameBoard {
    private User host;
    private User guest;
    private Castle hostPlayerLeftCastle= new Castle();
    private Castle hostPlayerMiddleCastle= new Castle();
    private Castle hostPlayerRightCastle= new Castle();
    private Castle guestPlayerLeftCastle= new Castle();
    private Castle guestPlayerMiddleCastle= new Castle();
    private Castle guestPlayerRightCastle= new Castle();
    private int numberOfTurns;
    private Turn currentTurn = new Turn();
    private Line leftLine = new Line();
    private Line middleLine = new Line();
    private Line rightLine = new Line();

    public GameBoard(User host, User guest, int numberOfTurns) {
        this.host = host;
        this.guest = guest;
        this.numberOfTurns = numberOfTurns;
        hostPlayerLeftCastle.setHeatPoint(2500 * host.getLevel());
        hostPlayerMiddleCastle.setHeatPoint(3600 * host.getLevel());
        hostPlayerRightCastle.setHeatPoint(2500 * host.getLevel());
        guestPlayerLeftCastle.setHeatPoint(2500 * guest.getLevel());
        guestPlayerMiddleCastle.setHeatPoint(3600 * guest.getLevel());
        guestPlayerRightCastle.setHeatPoint(2500 * guest.getLevel());
        hostPlayerLeftCastle.setPower(500 * host.getLevel());
        hostPlayerMiddleCastle.setPower(500 * host.getLevel());
        hostPlayerRightCastle.setPower(500 * host.getLevel());
        guestPlayerLeftCastle.setPower(500 * guest.getLevel());
        guestPlayerMiddleCastle.setPower(500 * guest.getLevel());
        guestPlayerRightCastle.setPower(500 * guest.getLevel());

    }

    public User getHost() {
        return host;
    }

    public User getGuest() {
        return guest;
    }

    public Castle getHostPlayerLeftCastle() {
        return hostPlayerLeftCastle;
    }

    public Castle getHostPlayerMiddleCastle() {
        return hostPlayerMiddleCastle;
    }

    public Castle getHostPlayerRightCastle() {
        return hostPlayerRightCastle;
    }

    public Castle getGuestPlayerLeftCastle() {
        return guestPlayerLeftCastle;
    }

    public Castle getGuestPlayerMiddleCastle() {
        return guestPlayerMiddleCastle;
    }

    public Castle getGuestPlayerRightCastle() {
        return guestPlayerRightCastle;
    }

    public Line getLeftLine() {
        return leftLine;
    }

    public Line getMiddleLine() {
        return middleLine;
    }

    public Line getRightLine() {
        return rightLine;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void nextTurn() {
        numberOfTurns -= 1;
        currentTurn= new Turn();
    }

    public Turn getCurrentTurn() {
        return currentTurn;
    }
}

