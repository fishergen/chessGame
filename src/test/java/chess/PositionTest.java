package chess;

import javafx.geometry.Pos;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Basic Unit Tests for the Position class
 */
public class PositionTest {

    @Test
    public void testStringParsingConstructor() {
        Position pos = new Position("d5");

        assertEquals("The column should be 'd'", 'd', pos.getColumn());
        assertEquals("The row should be 5", 5, pos.getRow());
    }

    @Test
    public void testPositionEquality() {
        Position one = new Position('a', 1);
        Position other = new Position('a', 1);

        assertEquals("The positions should equal each other", one, other);
    }
    @Test
    public void testColumnShift() {
        Position toShift = new Position('d', 2);
        char toShiftColumn = toShift.getColumn();
        char movedRightColumn = toShift.moveRight(toShiftColumn);
        char movedLeftColumn = toShift.moveLeft(toShiftColumn);

        assertEquals("The column should have shifted to the right", 'e', movedRightColumn);
        assertEquals("The column should have shifted to the left", 'c', movedLeftColumn);
    }

    @Test
    public void testAtBoardEdge(){
        Position edgePosition = new Position('i', 3);
        Position edgePosition2 = new Position('c', 0);
        Position notAtEdge = new Position('d', 4);

        assertTrue("This position is on an edge column", edgePosition.atBoardEdge());
        assertTrue("This position is on an edge row", edgePosition2.atBoardEdge());
        assertFalse("This position is not on the edge", notAtEdge.atBoardEdge());
    }

    /*@Test
    public void testFromString(){
        String position = "h7";
        Position stringPos = new Position("h7");
        Position newPos = stringPos.fromString(position);

        assertEquals("The strings of this position should be identical", "h7", newPos);
        assertNotSame("The strings of this position should be identical", "h1", newPos);

    }*/


}
