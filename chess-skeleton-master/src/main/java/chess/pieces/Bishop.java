package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    public Bishop(Player owner, Position currentPosition) {
        super(owner, currentPosition);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }


    @Override
    public List<Position> getPossibleMoves() {
        Position currentPos = getCurrentPosition();
        int currentRow = currentPos.getRow();
        int moveCount = getMoveCount();
        if (moveCount == 0)
            currentRow = currentRow + 2;
        else
            currentRow = currentRow + 1;
        Position potentialPos = currentPos;
        potentialPos.setRow(currentRow);
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
