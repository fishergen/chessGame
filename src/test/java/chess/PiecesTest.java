package chess;

import chess.pieces.Pawn;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Basic Unit Test for the Pieces Class
 */
public class PiecesTest {
    //setup


    //Test getMoveDirection (white & black piece
    @Test
    public void getMoveDirection() {
        Pawn white = new Pawn(Player.White, new Position("h2"));
        Pawn black = new Pawn(Player.Black, new Position("h7"));

        assertEquals("White player should move in a positive direction", white.getMoveDirection(),1);
        assertEquals("Black player should move in a negative direction", black.getMoveDirection(),-1);
    }

    @Test
    public void getCurrentPositionForPawn() {
        Pawn white = new Pawn(Player.Black, new Position("h2"));

        assertNotNull("White player should have a current Position that is not null", white.getCurrentPosition());
    }

    @Test
    public void getPossibleMovesForPawn() {
        Pawn white = new Pawn(Player.White, new Position("h2"));
        int possibleMoveCount = white.getPossibleMoves().size();
        assertNotNull ("Pawn should return a list of possible moves", white.getPossibleMoves());
        assertEquals("White has not moved yet so move count should be zero", white.getMoveCount(), 0);
        assertEquals("Pawn should have 1 possible move at start of game? (or 2)",possibleMoveCount,1);
    }

   //Test if a move is valid

    /*
    @Test
    public void getValidMovesForPawn() {
        Pawn white = new Pawn(Player.White, new Position("h2"));
        assertTrue("Pawn has a valid move",white.isValidMove(state);
    }*/
}