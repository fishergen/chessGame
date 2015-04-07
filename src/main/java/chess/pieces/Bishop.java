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
    public List<Position> getPossibleMoves(Position currentPosition) {
        int[] travelDirectionList = {1,-1};
        int currentRow = currentPosition.getRow();
        char currentCol = currentPosition.getColumn();
        List<Position> positionList = new ArrayList<Position>();
        int newRow;
        char rightCol,leftCol;
        int i;
        //while not on edge of board
        //while (currentPosition.atBoardEdge() == false){
            for (i=0; i<travelDirectionList.length; i++) {
                newRow = currentRow + 1 * i;
                rightCol = currentPosition.moveRight(currentCol);
                Position rightPos = new Position(rightCol, newRow);
                positionList.add(rightPos);
                leftCol = currentPosition.moveLeft(currentCol);
                Position leftPos = new Position(leftCol, newRow);
                positionList.add(leftPos);
            }
        //}
        return positionList;
    }


}
