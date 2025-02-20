class King extends Queen {
    public King(String color, char column, int row) {
        super(color, column, row);
        this.pieceName = "King";
    }
    
    @Override
    public boolean verifyMove(char newColumn, int newRow) {
        int colDiff = Math.abs(newColumn - this.column);
        int rowDiff = Math.abs(newRow - this.row);
        // King can move one square in any direction.
        return (colDiff <= 1 && rowDiff <= 1 && (colDiff != 0 || rowDiff != 0));
    }
}