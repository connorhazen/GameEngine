package game.engine.gameHandlers.operation;

import game.util.MutableCell;

import java.util.List;

public interface Operation {
    void execute(List<MutableCell> currCells);
}
