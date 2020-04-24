package game.engine.gameHandlers.interaction;

import game.util.Action;
import game.util.Coordinates;
import game.util.MutableCell;

import game.util.MutableGrid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RightToLeftAll extends OrderedAll {

    public RightToLeftAll(){
        super();
    }

    @Override
    public void setGrid(MutableGrid currentGrid, Action a) {
        cells.clear();
        for(int row = 0; row < currentGrid.getHeight(); row++){
            List<List<MutableCell>> allRowCombos = new ArrayList<>();
            for(int col = currentGrid.getWidth()-2; col >= 0; col--) {
                List<MutableCell> newGroup = new ArrayList<>();
                newGroup.add(currentGrid.getMutableCell(new Coordinates(col + 1, row)));
                newGroup.add(currentGrid.getMutableCell(new Coordinates(col, row)));
                newGroup.get(0).mark(false);
                newGroup.get(1).mark(false);
                allRowCombos.add(newGroup);
            }
            for(int index = 1; index <= allRowCombos.size();index++){
                List<List<MutableCell>> sub = new ArrayList<>(allRowCombos.subList(0,index));
                Collections.reverse(sub);
                cells.addAll(sub);
            }
        }
    }
}
