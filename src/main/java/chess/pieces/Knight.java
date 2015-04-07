package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.ArrayList;
import java.util.List;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner, Position currentPosition) {
        super(owner, currentPosition);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

    public List<Position> getPossibleMoves(Position currentPosition) {
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

}
