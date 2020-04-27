package view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import game.controller.Factory;
import game.parse.XMLException;
import game.util.Grid2dArray;
import game.util.SimpleState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ViewParseTest {

  @Test
  void testGrid() {
    Grid2dArray gridTest = new Grid2dArray(100, 100);
    gridTest.loop((e) -> {
      gridTest.setCell(e.x, e.y, new SimpleState("", 1));
    });

    for (int i = 0; i < 100; i++) {
      assertEquals(1, gridTest.getCell(i, 0).getValue(), "wtf");
    }
  }

  @Test
  void testViewFactoryWrongFile() {
    Factory f = new Factory("testingXmlsWrongView/");
    assertThrows(XMLException.class, () -> f.makeView());
  }
}
