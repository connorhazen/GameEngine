package game.engine.gameHandlers.operation;

import game.util.MutableCell;
import game.util.SimpleState;
import game.util.State;

import java.util.List;

public class OperationCombine2048 implements Operation{
    @Override
    public void execute(List<MutableCell> currCells) {
        MutableCell cell1 = currCells.get(0);
        MutableCell cell2 = currCells.get(1);
        State combinedState2 = new SimpleState("",cell1.getValue() + cell2.getValue());
        State combinedState1 = new SimpleState("",0);
        cell1.setState(combinedState1);
        cell2.setState(combinedState2);
    }
}
