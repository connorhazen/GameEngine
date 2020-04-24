package game.util;

public interface Action {

  String getCode();
  boolean equals(Object obj);
  String toString();
  int hashCode();
  Coordinates getCell();

}
