package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;
import javafx.geometry.Pos;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.List;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;
    private int moveDirection;
    private Position currentPosition;
    private int moveCount;

    protected Piece(Player owner, Position currentPosition) {
        this.owner = owner;
        this.currentPosition = currentPosition;
    }

    protected Piece(Player owner){
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public int getMoveDirection() {
        moveDirection = 1;
        if (owner.equals(Player.Black)) {
            moveDirection *= -1;
        }
        return moveDirection;
    }

    public boolean notOnEdge(Position currentPosition) {
        while (currentPosition.getColumn() < Position.MAX_COLUMN &&
                currentPosition.getColumn() > Position.MIN_COLUMN &&
                currentPosition.getRow() < Position.MAX_ROW &&
                currentPosition.getRow() > Position.MIN_ROW) {
            return true;
        }
        return false;
    }

    public boolean isValidMove(GameState state, Position potentialPosition){
        if (!state.isPieceAt(potentialPosition)){
            return true;
        }
        return false;
    }

    public Position getCurrentPosition(){
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition){
        this.currentPosition = currentPosition;
    }

    public int getMoveCount(){
        return moveCount;
    }

    public void setMoveCount(int moveCount){
        this.moveCount = moveCount;
    }

    public Player getOwner() {
        return owner;
    }

    protected abstract char getIdentifyingCharacter();

    public abstract List<Position> getPossibleMoves(Position currentPosition);


}
