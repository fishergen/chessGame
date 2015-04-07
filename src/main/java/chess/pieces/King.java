package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

/**
 * The King class
 */
public class King extends Piece {
    public King(Player owner, Position currentPosition) {
        super(owner, currentPosition);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }


    @Override
    public List<Position> getPossibleMoves(Position currentPosition) {
        int currentRow = currentPosition.getRow();
        char currentCol = currentPosition.getColumn();
        //int[] travelDirectionList = {1, -1};
        Position potentialPos = currentPosition;
        List<Position> positionList = new ArrayList<Position>();

        //travel vertically
        int newRow = currentRow + 1;
        potentialPos.setRow(newRow);
        positionList.add(potentialPos);

        //travel horizontally

        //travel diagonnally
        /*for (i=0 ;i<travelDirectionList.length; i++){
            currentRow = currentRow +1*travelDirectionList[i];;
            potentialPos.setRow(currentRow);
            positionList.add(potentialPos);
            for (j=0; j<travelDirectionList.length; j++){
                currentCol = currentPosition.moveOver(currentCol,travelDirectionList[i]);
                potentialPos.setColumn(currentCol);
                positionList.add(potentialPos);
            }
        }*/
        return positionList;
    }


}
