package game.engine.gameHandlers.rules;

import game.util.MutableCell;

import java.util.List;

public class Rule2048 implements Rules {



    @Override
    public boolean canPerform(List<MutableCell> currCells) {
        boolean neitherMarked = !(currCells.get(0).getState().ifMarked() || currCells.get(1).getState().ifMarked());
        boolean cellsEqualButNotZero = (currCells.get(0).getValue() == currCells.get(1).getValue()) && currCells.get(0).getValue() != 0;
        boolean oneZero = (currCells.get(0).getValue() == 0 && currCells.get(1).getValue() != 0);
        return neitherMarked && (cellsEqualButNotZero || oneZero);
    }
}
