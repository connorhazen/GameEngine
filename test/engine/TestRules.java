package engine;
import game.engine.gameHandlers.rules.*;
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

public class TestRules{

  @Test
  void testValueEqual(){
    Rules rule = new ValueEqual(Arrays.asList("-1"));

    assertTrue(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("", -1), new Coordinates(0,0)))));
    assertFalse(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("", 0), new Coordinates(0,0)))));

  }

  @Test
  void testValueGreaterThanEqual(){
    Rules rule = new ValueGreaterThanEqual(Arrays.asList("1"));

    assertFalse(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("", -1), new Coordinates(0,0)))));
    assertTrue(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("", 1), new Coordinates(0,0)))));
    assertTrue(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("", 2), new Coordinates(0,0)))));


  }
  @Test
  void testCellNotOfType(){
    Rules rule = new CellNotOfType(Arrays.asList("Hello"));

    assertTrue(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("", -1), new Coordinates(0,0)))));
    assertFalse(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("Hello", 0), new Coordinates(0,0)))));

    rule = new CellNotOfType(Arrays.asList("Hello", "Hello1"));
    assertFalse(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("Hello", -1), new Coordinates(0,0)))));
    assertFalse(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("Hello1", -1), new Coordinates(0,0)))));
  }
  @Test
  void testCellOfType(){
    Rules rule = new CellOfType(Arrays.asList("Hello"));

    assertTrue(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("Hello", -1), new Coordinates(0,0)))));
    assertFalse(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("", 0), new Coordinates(0,0)))));

    rule = new CellOfType(Arrays.asList("Hello", "Hello1"));
    assertTrue(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("Hello", -1), new Coordinates(0,0)))));
    assertTrue(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("Hello1", -1), new Coordinates(0,0)))));
  }
  @Test
  void testNotOppositeDirection(){
    Rules rule = new NotOppositeDirection(Arrays.asList("LEFT"));

    assertTrue(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("", -1, "LEFT"), new Coordinates(0,0)))));
    assertFalse(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("", 0, "RIGHT"), new Coordinates(0,0)))));

  }

  @Test
  void testRuleTrue(){
    Rules rule = new RuleTrue();

    assertTrue(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("", -1, "LEFT"), new Coordinates(0,0)))));
    assertTrue(rule.canPerform(Arrays.asList(
        new MutableCell(new SimpleState("Yes", 0, "RIGHT"), new Coordinates(0,0)))));

  }

  @Test
  void testRule2048(){
    Rules rule = new Rule2048();

    assertTrue(rule.canPerform(Arrays.asList(
            new MutableCell(new SimpleState("",2),new Coordinates(0,0)),
            new MutableCell(new SimpleState("",2),new Coordinates(0,0)))));
    assertFalse(rule.canPerform(Arrays.asList(
            new MutableCell(new SimpleState("",1),new Coordinates(0,0)),
            new MutableCell(new SimpleState("",2),new Coordinates(0,0)))));
    assertTrue(rule.canPerform(Arrays.asList(
            new MutableCell(new SimpleState("",0),new Coordinates(0,0)),
            new MutableCell(new SimpleState("",2),new Coordinates(0,0)))));

  }



}


