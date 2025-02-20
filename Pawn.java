class Pawn extends ChessPiece {
    public Pawn(String color, char column, int row) {
        super("Pawn", color, column, row);
    }
    
    @Override
    public boolean verifyMove(char newColumn, int newRow) {
        // For simplicity, assume:
        // WHITE pawn moves up (row + 1); BLACK pawn moves down (row - 1).
        if (this.color.equalsIgnoreCase("WHITE")) {
            return (newColumn == this.column) && (newRow == this.row + 1);
        } else if (this.color.equalsIgnoreCase("BLACK")) {
            return (newColumn == this.column) && (newRow == this.row - 1);
        }
        return false;
    }
}