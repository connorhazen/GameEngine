package engine.interaction;

import util.Coordinates;
import util.Grid2dArray;

public class RightToLeftAll extends OrderedAll {

    public RightToLeftAll(){
        super();
    }

    @Override
    public void setGrid(Grid2dArray currentGrid) {
        cells.clear();
        for(int row = 0; row < currentGrid.getHeight(); row++){
            for(int col = currentGrid.getWidth()-1; col > 0; col--){
                cells.add(currentGrid.getMutableCell(new Coordinates(row,col)));
            }
        }
    }
}
