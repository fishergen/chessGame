package chess;


import chess.pieces.*;
import javafx.geometry.Pos;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;
    private int turnCount = 0;

    /**
     * A map of board positions to pieces at that position
     */
    private DualHashBidiMap<Position, Piece> gameState;//positionToPieceMap;

   // Iterator entries = gameState.entrySet().iterator();
    /**
     * Create the game state.
     */
    public GameState() {
        gameState = new DualHashBidiMap<Position, Piece>();
    }

    //call to return game state
    public DualHashBidiMap<Position, Piece> getGameState(){
        return gameState;
    }

    public void setGameState(DualHashBidiMap gameState){
        this.gameState = gameState;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer){
        this.currentPlayer = currentPlayer;
    }

    public void setTurn(int turnCount) {
        if ((turnCount & 1) == 0 ) {
            setCurrentPlayer(Player.White);
        }else{
            setCurrentPlayer(Player.Black);
        }
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White,  new Position("a1")), new Position("a1"));
        placePiece(new Knight(Player.White,  new Position("b1")), new Position("b1"));
        placePiece(new Bishop(Player.White,  new Position("c1")), new Position("c1"));
        placePiece(new Queen(Player.White,  new Position("d1")), new Position("d1"));
        placePiece(new King(Player.White,  new Position("e1")), new Position("e1"));
        placePiece(new Bishop(Player.White,  new Position("f1")), new Position("f1"));
        placePiece(new Knight(Player.White,  new Position("g1")), new Position("g1"));
        placePiece(new Rook(Player.White,  new Position("h1")), new Position("h1"));
        placePiece(new Pawn(Player.White, new Position("a2")), new Position("a2"));
        placePiece(new Pawn(Player.White, new Position("b2")), new Position("b2"));
        placePiece(new Pawn(Player.White, new Position("c2")), new Position("c2"));
        placePiece(new Pawn(Player.White, new Position("d2")), new Position("d2"));
        placePiece(new Pawn(Player.White, new Position("e2")), new Position("e2"));
        placePiece(new Pawn(Player.White, new Position("f2")), new Position("f2"));
        placePiece(new Pawn(Player.White, new Position("g2")), new Position("g2"));
        placePiece(new Pawn(Player.White,new Position("h2")), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black,  new Position("a8")), new Position("a8"));
        placePiece(new Knight(Player.Black,  new Position("b8")), new Position("b8"));
        placePiece(new Bishop(Player.Black,  new Position("c8")), new Position("c8"));
        placePiece(new Queen(Player.Black,  new Position("d8")), new Position("d8"));
        placePiece(new King(Player.Black,  new Position("e8")), new Position("e8"));
        placePiece(new Bishop(Player.Black,  new Position("f8")), new Position("f8"));
        placePiece(new Knight(Player.Black,  new Position("g8")), new Position("g8"));
        placePiece(new Rook(Player.Black,  new Position("h8")), new Position("h8"));
        placePiece(new Pawn(Player.Black,  new Position("a7")), new Position("a7"));
        placePiece(new Pawn(Player.Black,  new Position("b7")), new Position("b7"));
        placePiece(new Pawn(Player.Black, new Position("c7")), new Position("c7"));
        placePiece(new Pawn(Player.Black,  new Position("d7")), new Position("d7"));
        placePiece(new Pawn(Player.Black,  new Position("e7")), new Position("e7"));
        placePiece(new Pawn(Player.Black, new Position("f7")), new Position("f7"));
        placePiece(new Pawn(Player.Black, new Position("g7")), new Position("g7"));
        placePiece(new Pawn(Player.Black, new Position("h7")), new Position("h7"));
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return gameState.get(position);
    }

    public Boolean isPieceAt(Position position) {
        if (gameState.get(position)!=null){
            return true;
        }
        return false;
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    private void placePiece(Piece piece, Position position) {
        gameState.put(position, piece);
    }

    public Position getPositionFor(Piece piece) { return gameState.getKey(piece); }

    public List<Piece> getAllPieces(){
        List<Piece> somePieces = new ArrayList<Piece>();
        try {
            for (Position key : gameState.keySet()) {
                somePieces.add(gameState.get(key));
            }
        } catch(NullPointerException e) {
            throw new RuntimeException("If there are no pieces, then the game is over.", e);
        }
        return somePieces;
    }

    public List<Piece> getPlayerPieces(Player player){
        List<Piece> playerPieces = new ArrayList<Piece>();
        try{
            for (Piece value : gameState.values()){
                if (value.getOwner().equals(player))
                    playerPieces.add(value);
            }
        } catch(NullPointerException e){
            throw new RuntimeException("If the player has no pieces, the game is over.");
        }
        return playerPieces;
    }

    public CopyOnWriteArrayList<Position> getPossibleMoves(){
        List<Piece> playerPieces = getPlayerPieces(currentPlayer);
        CopyOnWriteArrayList<Position> allPossibleMoves = new CopyOnWriteArrayList<Position>();
        //List<Position> validMoves;
        try{
            for (Piece piece : playerPieces) {
                for (Position move : piece.getPossibleMoves(piece.getCurrentPosition())) {
                    allPossibleMoves.add(move);
                    }
                }
        } catch(NullPointerException e){
            throw new RuntimeException("There must be moves, or else the game is over.", e);
        }
        arePossibleMovesValid(allPossibleMoves);
        //return validMoves;
        return allPossibleMoves;
    }

    public List<Position> arePossibleMovesValid (List<Position> possibleMoves){
        for (Position move : possibleMoves) {
            if (isPieceAt(move)){
                possibleMoves.remove(move);
            }
        }
        return possibleMoves;
    }

    public void move (Position oldPosition, Position newPosition){
        Piece toMove = getPieceAt(oldPosition);
        List<Position> possibleMoves = toMove.getPossibleMoves(oldPosition);
        try {
            if (possibleMoves.contains(newPosition)){
                toMove.setCurrentPosition(newPosition);
                placePiece(toMove, newPosition);
                turnCount = turnCount + 1;
                setTurn(turnCount);
            }else{
                System.out.println("This move is not valid. Please select a valid move for your piece.");
            }
        } catch(NullPointerException e){
            throw new RuntimeException("You have entered an invalid move. Please enter a valid move.", e);
        }
    }

}
