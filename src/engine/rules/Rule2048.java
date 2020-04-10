package engine.rules;

import util.MutableCell;

import java.util.List;

public class Rule2048 implements Rules {

    @Override
    public boolean canPerform(List<MutableCell> currCells) {

        return (currCells.get(0).getValue() == currCells.get(1).getValue()) || (currCells.get(0).getValue() == 0 || currCells.get(1).getValue() == 0) && (currCells.get(0).getValue() !=0 || currCells.get(1).getValue() !=0) ;
    }
}
