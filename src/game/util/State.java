package game.util;

public interface State {

  String getType();

  int getValue();

  String getDirection();

  boolean ifMarked();

  State mark(boolean newStat);


}
