package chess;

import chess.pieces.*;
import org.junit.Before;
import org.junit.Test;
import chess.GameState;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Basic Unit Test for the Pieces Class
 */
public class PiecesTest {
    //setup
    //private GameState state;


    //@Before


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
        Position whitePos = white.getCurrentPosition();
        int possibleMoveCount = white.getPossibleMoves(whitePos).size();
        assertNotNull ("Pawn should return a list of possible moves", white.getPossibleMoves(white.getCurrentPosition()));
        assertEquals("White has not moved yet so move count should be zero", white.getMoveCount(), 0);
        assertEquals("Pawn should have 2 possible moves at start of game",possibleMoveCount,2);

        white.setMoveCount(1);
        int possibleMoveCount2 = white.getPossibleMoves(white.getCurrentPosition()).size();
        assertEquals("Pawn should have 1 possible move only since this isn't the first move",possibleMoveCount2,1);
        //assertTrue("Pawn is on the board",white.notOnEdge(white.getCurrentPosition()));

        //Pawn offWhite = new Pawn(Player.White, new Position("h9"));
        //assertFalse("Pawn is off the board", offWhite.notOnEdge(offWhite.getCurrentPosition()));
    }

   //Test if a move is valid


    @Test
    public void getValidMovesForPawn() {
        GameState state = new GameState();
        state.reset();
        Piece white = state.getPieceAt("h2");
        Position goodPosition = new Position('h', 3);
        Position badPosition = new Position('h', 1);
        assertTrue("Pawn has a valid move",white.isValidMove(state, goodPosition));
        assertFalse("Pawn does not have a valid move", white.isValidMove(state, badPosition));
    }

    @Test
     public void getPossibleMovesForBishop() {
        Bishop black = new Bishop(Player.Black, new Position("f1"));
        Position testPos1 = new Position('g', 2);
        Position testPos2 = new Position('e', 2);

        Position blackPos = black.getCurrentPosition();
        int possibleMoveCount = black.getPossibleMoves(blackPos).size();
        assertNotNull("Bishop should return a list of possible moves", black.getPossibleMoves(blackPos));

        assertEquals("Bishop should have 2 possible moves at start of game",possibleMoveCount,4);//change to 2 when boardedge works, to 0 when validmove works

        List<Position> possibleMoves = black.getPossibleMoves(blackPos);

        assertTrue("Bishop should have g2 as a possible move", possibleMoves.contains(testPos1));
        assertTrue("Bishop should have e2 as a possible move", possibleMoves.contains(testPos2));
    }

    @Test
    public void getPossibleMovesForRook() {
        Rook black = new Rook(Player.Black, new Position("a1"));
        Position testPos1 = new Position('a', 2);
        Position testPos2 = new Position('b', 1);

        Position blackPos = black.getCurrentPosition();
        int possibleMoveCount = black.getPossibleMoves(blackPos).size();
        assertNotNull("Rook should return a list of possible moves", black.getPossibleMoves(blackPos));

        assertEquals("Rook should have 2 possible moves at start of game",possibleMoveCount,4);//change to 2 when boardedge works, to 0 when validmove works

        List<Position> possibleMoves = black.getPossibleMoves(blackPos);

        assertTrue("Rook should have a2 as a possible move", possibleMoves.contains(testPos1));
        assertTrue("Rook should have b1 as a possible move", possibleMoves.contains(testPos2));
    }

    @Test
    public void getPossibleMovesForKing(){
        King white = new King(Player.White, new Position('e', 1));
        Position whitePos = white.getCurrentPosition();
        List<Position> possibleMoves = white.getPossibleMoves(whitePos);
        Position testPos1 = new Position('e', 2);
        assertTrue("King should have e2 as a possible move", possibleMoves.contains(testPos1));
    }
}