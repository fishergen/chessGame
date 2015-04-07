package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;
import javafx.geometry.Pos;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.ArrayList;
import java.util.List;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner, Position currentPosition) {
        super(owner, currentPosition);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }

    @Override
    public List<Position> getPossibleMoves(Position currentPosition) {
        int[] travelDirectionList = {1,-1};
        int currentRow = currentPosition.getRow();
        char currentCol = currentPosition.getColumn();
        List<Position> positionList = new ArrayList<Position>();
        int newRow;
        char rightCol,leftCol;
        int i;
        //while not on edge of board
        //while (currentPosition.atBoardEdge() == false) {
        if (currentPosition.getRow() == Position.MAX_ROW){
            newRow = currentRow + 1;
            Position forwardPos = new Position(currentCol, newRow);
        }
            for (i = 0; i < travelDirectionList.length; i++) {
                newRow = currentRow + 1 * i;
                Position forwardPos = new Position(currentCol, newRow);
                positionList.add(forwardPos);
            }
            rightCol = currentPosition.moveRight(currentCol);
            Position rightPos = new Position(rightCol, currentRow);
            positionList.add(rightPos);
            leftCol = currentPosition.moveLeft(currentCol);
            Position leftPos = new Position(leftCol, currentRow);
            positionList.add(leftPos);
        //}
        return positionList;
    }


}
