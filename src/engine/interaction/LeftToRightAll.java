package engine.interaction;

import util.Coordinates;
import util.Grid2dArray;
import util.MutableCell;

import java.util.ArrayList;
import java.util.List;


public class LeftToRightAll extends OrderedAll{

    public LeftToRightAll(){
        super();
    }

    @Override
    public void setGrid(Grid2dArray currentGrid) {
        cells.clear();
        for(int row = 0; row < currentGrid.getHeight(); row++){
            List<MutableCell> newGroup = new ArrayList<>();
            for(int col = 0; col < currentGrid.getWidth(); col++){
                newGroup.add(currentGrid.getMutableCell(new Coordinates(col,row)));
            }
            cells.add(newGroup);
        }
    }
}
