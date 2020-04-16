package game.engine.interaction;

import game.util.Coordinates;
import game.util.Grid2dArray;
import game.util.MutableCell;

import java.util.ArrayList;
import java.util.List;

public class TopToBottomAll extends OrderedAll {

    public TopToBottomAll(){
        super();
    }

    @Override
    public void setGrid(Grid2dArray currentGrid) {
        cells.clear();
        for(int col = 0; col < currentGrid.getWidth(); col++){
            List<MutableCell> newGroup = new ArrayList<>();
            for(int row = 0; row < currentGrid.getHeight(); row++){
                newGroup.add(currentGrid.getMutableCell(new Coordinates(col,row)));
            }
            cells.add(newGroup);
        }
    }
}
