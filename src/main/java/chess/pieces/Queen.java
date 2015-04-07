package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.ArrayList;
import java.util.List;

/**
 * The Queen
 */
public class Queen extends Piece{
    public Queen(Player owner, Position currentPosition) {
        super(owner, currentPosition);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }
    @Override
    public List<Position> getPossibleMoves(Position currentPosition) {
        int currentRow = currentPosition.getRow();
        char currentCol = currentPosition.getColumn();
        int[] travelDirectionList = {1, -1};
        Position potentialPos = currentPosition;
        List<Position> positionList = new ArrayList<Position>();
        int i, j;
        //move straight
        if (currentRow == Position.MAX_ROW){
            while (currentRow != Position.MIN_ROW){
                currentRow = currentRow - 1;
                potentialPos.setRow(currentRow);
                positionList.add(potentialPos);
            }
        }else if(currentRow == Position.MIN_ROW){
            while (currentRow != Position.MAX_ROW){
                currentRow = currentRow + 1;
                potentialPos.setRow(currentRow);
                positionList.add(potentialPos);
            }
        }else if (currentRow < Position.MAX_ROW && currentRow < Position.MIN_ROW){
            for (i = 0; i < travelDirectionList.length; i++) {
                currentRow = currentRow + 1 * i;
                potentialPos.setRow(currentRow);
                positionList.add(potentialPos);
                for (j = 0; j < travelDirectionList.length; j++) {
                    currentCol = currentPosition.moveOver(currentCol, i);
                    potentialPos.setColumn(currentCol);
                    positionList.add(potentialPos);
                }
            }
        }else{
            System.out.println("Your piece appears to be off of the board.");
        }
        //move sideways
        //move diagonally
        return positionList;
    }


}
