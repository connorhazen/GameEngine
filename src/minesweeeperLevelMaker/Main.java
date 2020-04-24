package minesweeeperLevelMaker;

import game.controller.GameStorageHandler;
import game.engine.UpdateStatus;
import game.util.Coordinates;
import game.util.Grid2dArray;
import game.util.SimpleState;
import java.util.function.Consumer;

public class Main {
  public static void main(String[]args){
    makeLevel(10, 10);
  }

  private static void makeLevel(int width, int height) {
    Grid2dArray g = new Grid2dArray(width, height);
    Consumer<Coordinates> c = (e) -> g.setCell(e.x, e.y, new SimpleState("COVERED", 0));

    g.loop(c);

    g.setCell(0,0, new SimpleState("COVERED", -1));
    g.setCell(1,0, new SimpleState("COVERED", 1));
    g.setCell(0,1, new SimpleState("COVERED", 1));
    g.setCell(1,1, new SimpleState("COVERED", 1));


    GameStorageHandler.storeGame(new UpdateStatus(g), "data/Games/Minesweeper/level1.sav");
  }

}
