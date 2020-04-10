package engine.interaction;

import util.Coordinates;
import util.Grid2dArray;

public class BottomToTopAll extends OrderedAll{

    public BottomToTopAll(){
        super();
    }

    @Override
    public void setGrid(Grid2dArray currentGrid) {
        cells.clear();
        for(int col = 0; col < currentGrid.getWidth(); col++){
            for(int row = currentGrid.getHeight()-1; row > 0; row--){
                cells.add(currentGrid.getMutableCell(new Coordinates(row,col)));
            }
        }
    }
}
