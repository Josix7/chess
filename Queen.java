class Queen extends Rook {
    public Queen(String color, char column, int row) {
        super(color, column, row);
        this.pieceName = "Queen";
    }
    
    @Override
    public boolean verifyMove(char newColumn, int newRow) {
        // Check for rook-like movement.
        boolean rookMove = ((newColumn == this.column && newRow != this.row) ||
                            (newRow == this.row && newColumn != this.column));
        // Check for bishop-like (diagonal) movement.
        int colDiff = Math.abs(newColumn - this.column);
        int rowDiff = Math.abs(newRow - this.row);
        boolean bishopMove = (colDiff == rowDiff && colDiff != 0);
        return rookMove || bishopMove;
    }
}