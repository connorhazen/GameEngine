package engine.interaction;

import util.Coordinates;
import util.Grid2dArray;

public class TopToBottomAll extends OrderedAll {

    public TopToBottomAll(){
        super();
    }

    @Override
    public void setGrid(Grid2dArray currentGrid) {
        cells.clear();
        for(int col = 0; col < currentGrid.getWidth(); col++){
            for(int row = 0; row < currentGrid.getHeight(); row++){
                cells.add(currentGrid.getMutableCell(new Coordinates(row,col)));
            }
        }
    }
}
