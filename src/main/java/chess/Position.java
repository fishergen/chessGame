package chess;

import chess.pieces.Piece;

/**
 * Describes a position on the Chess Board
 */
public class Position {
    public static final int MIN_ROW = 1;
    public static final int MAX_ROW = 8;
    public static final char MIN_COLUMN = 'a';
    public static final char MAX_COLUMN = 'h';
    private int row;
    private char column;

    private Piece taken;

    /**
     * Create a new position object
     *
     * @param column The column
     * @param row The row
     */
    public Position(char column, int row) {
        this.row = row;
        this.column = column;
    }

    /**
     * Create a new Position object by parsing the string
     * @param colrow The column and row to use.  I.e. "a1", "h7", etc.
     */
    public Position(String colrow) {
        this(colrow.toCharArray()[0], Character.digit(colrow.toCharArray()[1], 10));
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row){
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public void setColumn(char column) { this.column = column; }

    /*
    public boolean isAvailable(Position potentialPos){
        if (potentialPos.getRow()==row && potentialPos.getColumn()==column){
            return false;
        }
        return true;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (column != position.column) return false;

        //noinspection RedundantIfStatement
        if (row != position.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + (int) column;
        return result;
    }

    @Override
    public String toString() {
        return "" + column + row;
    }

    public char moveRight(char column){
        int columnAscii = (int)column + 1;
        char rightColumn = (char)columnAscii;
        return rightColumn;
    }

    public char moveOver (char column, int direction){
        int columnAscii = (int)column + 1*direction;
        char overColumn = (char)columnAscii;
        return overColumn;
    }

    public char moveLeft(char column){
        int columnAscii = (int)column - 1;
        char leftColumn = (char)columnAscii;
        return leftColumn;
    }

    public boolean atBoardEdge() {
        char currentColumn = this.getColumn();
        int currentRow = this.getRow();
        while (currentColumn <= Position.MAX_COLUMN &&
                currentColumn >= Position.MIN_COLUMN &&
                currentRow <= Position.MAX_ROW &&
                currentRow >= Position.MIN_ROW) {
            return false;
        }
        return true;
    }
    

}
