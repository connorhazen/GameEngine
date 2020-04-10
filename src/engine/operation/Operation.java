package engine.operation;

import util.MutableCell;

import java.util.List;

public interface Operation {
    void execute(List<MutableCell> currCells);
}
