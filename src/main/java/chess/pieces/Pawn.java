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
    public List<Position> getPossibleMoves(Position currentPosition) {
        int currentRow = currentPosition.getRow();
        char currentCol = currentPosition.getColumn();
        int potentialRow, potentialRow2;
        int moveCount = getMoveCount();
        int direction = getMoveDirection();
        Position potentialPos = new Position(currentCol, currentRow);
        Position potentialPos2= new Position(currentCol, currentRow);
        List<Position> positionList = new ArrayList<Position>();
        if (moveCount == 0) {
            potentialRow = currentRow + 2 * direction;
            potentialRow2 = currentRow + 1 * direction;
            potentialPos.setRow(potentialRow);
            potentialPos2.setRow(potentialRow2);

            positionList.add(potentialPos);
            positionList.add(potentialPos2);
        }
        else {
            potentialRow = currentRow + 1 * direction;
            potentialPos.setRow(potentialRow);
            positionList.add(potentialPos);
        }
        return positionList;
    }



}
