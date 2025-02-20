class Rook extends ChessPiece {
    public Rook(String color, char column, int row) {
        super("Rook", color, column, row);
    }
    
    @Override
    public boolean verifyMove(char newColumn, int newRow) {
        // Valid if moving in a straight line (same row or same column)
        return ((newColumn == this.column && newRow != this.row) ||
                (newRow == this.row && newColumn != this.column));
    }
}