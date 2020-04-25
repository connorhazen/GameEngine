package engine;

import game.engine.gameHandlers.BaseGameHandler;
import game.engine.gameHandlers.GameHandler;
import game.engine.levelHandlers.BaseLevelHandler;
import game.engine.levelHandlers.LevelHandler;
import game.engine.levelHandlers.endConditions.AllCellOfTypes;
import game.engine.levelHandlers.endConditions.CellOfType;
import game.engine.levelHandlers.endConditions.Condition;
import game.engine.levelHandlers.endConditions.NoNextMove;
import game.util.Grid2dArray;
import game.util.MutableGrid;
import game.util.SimpleAction;
import game.util.SimpleState;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestLevelHandler {

  private LevelHandler lh;
  private MutableGrid grid;

  @BeforeEach
  void setUp(){
    lh = new BaseLevelHandler();
    grid = new Grid2dArray(10,10);
    grid.loop(e -> grid.setCell(e.x,e.y,new SimpleState("", 0)));
  }

  @Nested
  @DisplayName("EndConditionTests")
  class EndCondition {

    @Test
    void CellOfType(){
      grid.setCell(0,0, new SimpleState("s1", 5));
      Condition c = new CellOfType(Arrays.asList("s1"));
      assertTrue(c.checkStatus(grid, new BaseGameHandler()));
      c = new CellOfType(Arrays.asList("s2"));
      assertFalse(c.checkStatus(grid, new BaseGameHandler()));

      grid.setCell(1,0, new SimpleState("s2", 5));
      assertTrue(c.checkStatus(grid, new BaseGameHandler()));

    }

    @Test
    void AllCellOfTypes(){
      grid.setCell(0,0, new SimpleState("s1", 5));
      Condition c = new AllCellOfTypes(new ArrayList<>(Arrays.asList("s1", "5", "", "0")));
      assertTrue(c.checkStatus(grid, new BaseGameHandler()));

      grid.setCell(0,1, new SimpleState("s2", 5));
      assertFalse(c.checkStatus(grid, new BaseGameHandler()));

      c = new AllCellOfTypes(new ArrayList<>(Arrays.asList("s1", "5", "", "0", "s2", "5")));
      assertTrue(c.checkStatus(grid, new BaseGameHandler()));
    }

    @Test
    void NoNextMove(){

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      // Change System.out to point out to our stream
      System.setOut(new PrintStream(baos));

      GameHandler gh = new BaseGameHandler();
      gh.addEvent(new SimpleAction("trial"), Arrays.asList("Nothing"),
          Arrays.asList("CellsOfType", "NOHERE"), Arrays.asList("RuleTrue"), new SimpleAction("next"), new SimpleAction("last"));
      gh.addEvent(new SimpleAction("trial1"), Arrays.asList("Nothing"),
          Arrays.asList("All"), Arrays.asList("RuleTrue"), new SimpleAction("next"), new SimpleAction("last"));
      gh.addEvent(new SimpleAction("trial2"), Arrays.asList("SetType", "newType"),
          Arrays.asList("All"), Arrays.asList("RuleTrue"), new SimpleAction("next"), new SimpleAction("last"));

      Condition c = new NoNextMove(Arrays.asList("trial"));
      assertTrue(c.checkStatus(grid, gh));

      c = new NoNextMove(Arrays.asList("trial", "trial1"));
      assertTrue(c.checkStatus(grid, gh));



      assertEquals(gh.generateUpdatedGrid(grid, new SimpleAction("trial2")).getCell(0,0).getType(), "newType");
      assertFalse(grid.equals(gh.generateUpdatedGrid(grid, new SimpleAction("trial2"))));

      c = new NoNextMove(Arrays.asList("trial", "trial1", "trial2"));
      assertFalse(c.checkStatus(grid, gh));
    }
  }

}
