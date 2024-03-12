package Model;

import java.util.ArrayList;

public class Box {
    private ArrayList<Card> cards= new ArrayList<>();

    public Box(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}

