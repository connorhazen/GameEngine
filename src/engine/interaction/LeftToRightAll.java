package engine.interaction;

import util.Coordinates;
import util.Grid2dArray;


public class LeftToRightAll extends OrderedAll{

    public LeftToRightAll(){
        super();
    }

    @Override
    public void setGrid(Grid2dArray currentGrid) {
        cells.clear();
        for(int row = 0; row < currentGrid.getHeight(); row++){
            for(int col = 0; col < currentGrid.getWidth(); col++){
                cells.add(currentGrid.getMutableCell(new Coordinates(row,col)));
            }
        }
    }
}
