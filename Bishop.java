class Bishop extends ChessPiece {
    public Bishop(String color, char column, int row) {
        super("Bishop", color, column, row);
    }
    
    @Override
    public boolean verifyMove(char newColumn, int newRow) {
        int colDiff = Math.abs(newColumn - this.column);
        int rowDiff = Math.abs(newRow - this.row);
        // Valid if moving diagonally (equal column and row difference)
        return (colDiff == rowDiff && colDiff != 0);
    }
}