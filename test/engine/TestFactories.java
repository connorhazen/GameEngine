package engine;
import game.engine.gameHandlers.rules.CellNotOfType;
import game.engine.gameHandlers.rules.CellOfType;
import game.engine.gameHandlers.rules.NotOppositeDirection;
import game.engine.gameHandlers.rules.Rules;
import game.engine.gameHandlers.rules.ValueEqual;
import game.util.Coordinates;
import game.util.MutableCell;
import game.util.SimpleState;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class TestRules{

  @Test
  void testValueEqual(){
    Rules rule = new ValueEqual(Arrays.asList(new String[] {"-1"}));

    assertTrue(rule.canPerform(Arrays.asList(new MutableCell[] {new MutableCell(new SimpleState("", -1), new Coordinates(0,0))})));
    assertFalse(rule.canPerform(Arrays.asList(new MutableCell[] {new MutableCell(new SimpleState("", 0), new Coordinates(0,0))})));

  }
  @Test
  void testCellNotOfType(){
    Rules rule = new CellNotOfType(Arrays.asList(new String[] {"Hello"}));

    assertTrue(rule.canPerform(Arrays.asList(new MutableCell[] {new MutableCell(new SimpleState("", -1), new Coordinates(0,0))})));
    assertFalse(rule.canPerform(Arrays.asList(new MutableCell[] {new MutableCell(new SimpleState("Hello", 0), new Coordinates(0,0))})));

  }
  @Test
  void testCellOfType(){
    Rules rule = new CellOfType(Arrays.asList(new String[] {"Hello"}));

    assertTrue(rule.canPerform(Arrays.asList(new MutableCell[] {new MutableCell(new SimpleState("Hello", -1), new Coordinates(0,0))})));
    assertFalse(rule.canPerform(Arrays.asList(new MutableCell[] {new MutableCell(new SimpleState("", 0), new Coordinates(0,0))})));

    rule = new CellOfType(Arrays.asList(new String[] {"Hello", "Hello1"}));
    assertTrue(rule.canPerform(Arrays.asList(new MutableCell[] {new MutableCell(new SimpleState("Hello", -1), new Coordinates(0,0))})));
    assertTrue(rule.canPerform(Arrays.asList(new MutableCell[] {new MutableCell(new SimpleState("Hello1", -1), new Coordinates(0,0))})));
  }
  @Test
  void testNotOppositeDirection(){
    Rules rule = new NotOppositeDirection(Arrays.asList(new String[] {"LEFT"}));

    assertTrue(rule.canPerform(Arrays.asList(new MutableCell[] {new MutableCell(new SimpleState("", -1, "LEFT"), new Coordinates(0,0))})));
    assertFalse(rule.canPerform(Arrays.asList(new MutableCell[] {new MutableCell(new SimpleState("", 0, "RIGHT"), new Coordinates(0,0))})));

  }

}


