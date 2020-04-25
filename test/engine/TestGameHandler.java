package engine;

import game.engine.gameHandlers.BaseGameHandler;
import game.engine.gameHandlers.GameHandler;
import game.util.Grid2dArray;
import game.util.MutableGrid;
import game.util.SimpleAction;
import game.util.SimpleState;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameHandler {

  private GameHandler gh;
  private MutableGrid grid;


  @BeforeEach
  void setUp(){
    gh = new BaseGameHandler();
    grid = new Grid2dArray(10,10);
    grid.loop(e -> grid.setCell(e.x,e.y,new SimpleState("", 0)));
  }

  @Test
  void testNextEvent(){
    gh.addEvent(new SimpleAction("trial"), Arrays.asList("Nothing"),
        Arrays.asList("All"), Arrays.asList("RuleTrue"), new SimpleAction("next"), new SimpleAction("last"));

    gh.generateUpdatedGrid(grid, new SimpleAction("trial"));
    assertEquals(gh.getNextEvent().getCode(), new SimpleAction("next").getCode());

    gh.generateUpdatedGrid(new Grid2dArray(0,0), new SimpleAction("trial"));
    assertEquals(gh.getNextEvent().getCode(), new SimpleAction("last").getCode());

  }

}
