class ChessBoard {
    public static final int MIN_ROW = 1;
    public static final int MAX_ROW = 8;
    
    public static boolean withinChessboard(char column, int row) {
        return (column >= 'a' && column <= 'h') && (row >= MIN_ROW && row <= MAX_ROW);
    }
}