class Knight extends ChessPiece {
    public Knight(String color, char column, int row) {
        super("Knight", color, column, row);
    }
    
    @Override
    public boolean verifyMove(char newColumn, int newRow) {
        int colDiff = Math.abs(newColumn - this.column);
        int rowDiff = Math.abs(newRow - this.row);
        // L-shaped moves: 2 by 1 or 1 by 2.
        return (colDiff == 1 && rowDiff == 2) || (colDiff == 2 && rowDiff == 1);
    }
}