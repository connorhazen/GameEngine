package game.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public interface  Grid {


    Cell getCell(Coordinates coords);

    Cell getCell(int x, int y);

    int getWidth();
    int getHeight();

    List<MutableCell> getCellsOfState(State s);

    void loop(Consumer<Coordinates> c);

    List<MutableCell> getCellsOfValue(int val);

    List<MutableCell> getCellsOfType(String type);

    MutableCell getCellDirection(MutableCell c);

    List<MutableCell> getMarkedCells();

    @Override
    boolean equals(Object obj);
}
