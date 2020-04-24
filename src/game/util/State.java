package game.util;

import java.io.Serializable;

public interface State extends Serializable {

  String getType();

  int getValue();

  String getDirection();

  boolean ifMarked();

  State mark(boolean newStat);


}
