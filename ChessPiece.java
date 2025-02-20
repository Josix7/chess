abstract class ChessPiece {
    protected String pieceName;
    protected String color;
    protected char column;
    protected int row;
    
    public ChessPiece() { }
    
    public ChessPiece(String pieceName, String color, char column, int row) {
        this.pieceName = pieceName;
        this.color = color;
        this.column = column;
        this.row = row;
    }
    
    public String getPieceName() {
        return pieceName;
    }
    
    public String getColor() {
        return color;
    }
    
    public char getColumn() {
        return column;
    }
    
    public int getRow() {
        return row;
    }
    
    public void setColumn(char column) {
        this.column = column;
    }
    
    public void setRow(int row) {
        this.row = row;
    }
    
    // Abstract method to be implemented by each chess piece.
    public abstract boolean verifyMove(char newColumn, int newRow);
}