package game.controller;

import game.util.UpdateObject;
import game.util.UpdateStatus;
import game.parse.XMLException;
import game.util.Grid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameStorageHandler {

  private final static String LOC = "data/";

  public static void storeGame(UpdateObject gameState, String filename) {
    try {
      FileOutputStream fil = new FileOutputStream(new File(LOC + filename));
      ObjectOutputStream oStream = new ObjectOutputStream(fil);
      oStream.writeObject(gameState.getGrid());
      oStream.close();
      fil.close();
    } catch (Exception e) {
      throw new XMLException(e.getMessage());
    }

  }

  public static UpdateObject loadGame(String sourceLocation) {
    try {

      FileInputStream fil = new FileInputStream(new File(LOC + sourceLocation));
      ObjectInputStream iStream = new ObjectInputStream(fil);
      Grid g = (Grid) iStream.readObject();
      iStream.close();
      fil.close();
      UpdateObject gameState = new UpdateStatus(g);
      return gameState;
    } catch (Exception e) {
      throw new XMLException(e.getMessage());
    }
  }

}
