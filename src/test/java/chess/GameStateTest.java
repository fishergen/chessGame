package chess;

import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * Basic unit tests for the GameState class
 */
public class GameStateTest {

    private GameState state;

    @Before
     public void setUp() {
        state = new GameState();
    }

    @Test
    public void testStartsEmpty() {
        // Make sure all the positions are empty
        for (char col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
                assertNull("All pieces should be empty", state.getPieceAt(String.valueOf(col) + row));
            }
        }
    }

    @Test
    public void testInitialGame() {
        // Start the game
        state.reset();

        // White should be the first player
        Player current = state.getCurrentPlayer();
        assertEquals("The initial player should be White", Player.White, current);

        // Spot check a few pieces
        Piece whiteRook = state.getPieceAt("a1");
        assertTrue("A rook should be at a1", whiteRook instanceof Rook);
        assertEquals("The rook at a1 should be owned by White", Player.White, whiteRook.getOwner());


        Piece blackQueen = state.getPieceAt("d8");
        assertTrue("A queen should be at d8", blackQueen instanceof Queen);
        assertEquals("The queen at d8 should be owned by Black", Player.Black, blackQueen.getOwner());

        Position queenPos = new Position('d',8);
        Position d8 = state.getPositionFor(blackQueen);
        assertEquals("A queen should be at d8", queenPos, d8);

        List<Piece> allPieces = state.getAllPieces();
        int allPiecesCount = allPieces.size();
        assertNotNull("The list of pieces should not be null",allPieces);
        assertEquals("The list of all pieces should be 32", allPiecesCount,32);

        List<Piece> whitePlayerPieces = state.getPlayerPieces(Player.White);
        assertNotNull("The list of white player pieces should not be null", whitePlayerPieces);
        assertEquals("The list of white player pieces should contain 16 pieces", whitePlayerPieces.size(),16);

        List<Position> possibleMoves = state.getPossibleMoves();
        int possibleMovesCount = possibleMoves.size();
        assertNotNull("The list of possible moves should not be null");
        assertEquals("The list of possible moves should contain positions of Pawns", possibleMovesCount,16);//this amount will change when piece logic is implemented

        //test isPieceAt
        assertTrue("A piece should be at d8", state.isPieceAt(queenPos));

    }
}
