package engine;
import game.engine.gameHandlers.operation.*;
import game.engine.gameHandlers.rules.*;
import game.util.Coordinates;
import game.util.MutableCell;
import game.util.SimpleState;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
public class TestOperations {

    @Test
    void testIncrementValue(){
        Operation op = new IncrementValue(List.of("1"));
        MutableCell cell = new MutableCell(new SimpleState("", 0), new Coordinates(0,0));
        assertTrue(cell.getValue()==0);
        op.execute(List.of(cell));
        assertTrue(cell.getValue()==1);
    }

    @Test
    void testMarkUnmarkCell(){
        Operation op1 = new MarkCell();
        Operation op2 = new UnMarkCell();
        MutableCell cell = new MutableCell(new SimpleState("", 0), new Coordinates(0,0));
        assertFalse(cell.isMarked());
        op1.execute(List.of(cell));
        assertTrue(cell.isMarked());
        op2.execute(List.of(cell));
        assertFalse(cell.isMarked());
    }

    @Test
    void testSetDirection(){
        Operation op = new SetDirection(List.of("DOWN"));
        MutableCell cell = new MutableCell(new SimpleState("", 0), new Coordinates(0,0));
        assertFalse(cell.getDirection().equals("DOWN"));
        op.execute(List.of(cell));
        assertTrue(cell.getDirection().equals("DOWN"));
    }

    @Test
    void testOperationCombine2048(){
        Operation op = new OperationCombine2048();
        MutableCell cell = new MutableCell(new SimpleState("", 2), new Coordinates(0,0));
        MutableCell cell2 = new MutableCell(new SimpleState("", 2), new Coordinates(0,0));
        assertTrue(cell.getValue()==2);
        op.execute(List.of(cell,cell2));
        assertTrue(cell.getValue()==4);
        assertTrue(cell2.getValue()==0);
    }

    @Test
    void testSetVal(){
        Operation op = new SetValue(List.of("2"));
        MutableCell cell = new MutableCell(new SimpleState("", 0), new Coordinates(0,0));
        assertFalse(cell.getValue() == 2);
        op.execute(List.of(cell));
        assertTrue(cell.getValue() == 2);
    }
}
