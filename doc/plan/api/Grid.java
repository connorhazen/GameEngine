package ooga;

import java.util.Iterator;

public interface Grid extends Iterable<Cell> {

  String getIdentifier(Cell x);

  String getCell(String identifier);

  @Override
  Iterator<Cell> iterator();

}
