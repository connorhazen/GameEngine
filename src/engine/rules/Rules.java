package engine.rules;

import util.MutableCell;

import java.util.List;

public interface Rules {

    boolean canPerform(List<MutableCell> currCells);
}
