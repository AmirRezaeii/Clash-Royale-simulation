package Model;

import java.util.ArrayList;

public class Line {
    private ArrayList<Box> boxes= new ArrayList<>();
    private  ChertCard chertCard= new ChertCard();
    public Line() {
        for (int i = 0; i < 15 ; i++){
            Box box= new Box(chertCard);
            boxes.add(box);
        }
    }
    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void changeLine(int index, Box box){
        boxes.set(index, box);
    }
}
