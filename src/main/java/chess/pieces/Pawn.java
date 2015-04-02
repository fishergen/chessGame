package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;
import javafx.geometry.Pos;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.ArrayList;
import java.util.List;

/**
 * The Pawn
 */
public class Pawn extends Piece {
    public Pawn(Player owner, Position currentPosition) {
        super(owner, currentPosition);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

    @Override
    public List<Position> getPossibleMoves() {
        int currentRow = getCurrentPosition().getRow();
        char currentCol = getCurrentPosition().getColumn();
        int potentialRow;
        int moveCount = getMoveCount();
        int direction = getMoveDirection();
        if (moveCount == 0)
            potentialRow = currentRow + 2*direction;
        else
            potentialRow = currentRow + 1*direction;
        Position potentialPos = new Position(currentCol, potentialRow);
        List<Position> positionList = new ArrayList<Position>();
        positionList.add(potentialPos);

        return positionList;
    }

    @Override
    public boolean isValidMove(GameState state){
        int currentRow = getCurrentPosition().getRow();
        char currentCol = getCurrentPosition().getColumn();
        int blockedRow = currentRow++;
        Position blockedPosition = new Position(currentCol,blockedRow);
        if (!state.isPieceAt(blockedPosition)){
            return true;
        }
        return false;
    }

}
