package util;

import java.util.Iterator;

public abstract class Grid implements Iterable<Cell> {

  abstract String getIdentifier(Cell x);

  abstract String getCell(String identifier);
}
