import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Array to hold exactly six chess pieces
        ChessPiece[] pieces = new ChessPiece[6];
        int count = 0;
        
        // Loop until six pieces have been added
        while (count < 6) {
            System.out.println("Enter details for chess piece " + (count + 1) + ":");
            
            // Prompt for chess piece type.
            System.out.print("Select chess piece (Pawn, Rook, Knight, Bishop, Queen, King): ");
            String type = scanner.nextLine().trim();
            if (type.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game.");
                scanner.close();
                return;
            }
            // Validate the piece type
            if (!type.equalsIgnoreCase("Pawn") && !type.equalsIgnoreCase("Rook") &&
                !type.equalsIgnoreCase("Knight") && !type.equalsIgnoreCase("Bishop") &&
                !type.equalsIgnoreCase("Queen") && !type.equalsIgnoreCase("King")) {
                System.out.println("Invalid chess piece type. Please try again.\n");
                continue;
            }
            
            // (Extra Credit) Check for duplicate piece type (assumes user should not add a duplicate)
            boolean duplicate = false;
            for (int i = 0; i < count; i++) {
                if (pieces[i].getPieceName().equalsIgnoreCase(type)) {
                    duplicate = true;
                    break;
                }
            }
            if (duplicate) {
                System.out.println("This chess piece type has already been added. Please choose a different type.\n");
                continue;
            }
            
            // Prompt for color.
            System.out.print("Enter color (WHITE or BLACK): ");
            String color = scanner.nextLine().trim();
            if (color.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game.");
                scanner.close();
                return;
            }
            if (!color.equalsIgnoreCase("WHITE") && !color.equalsIgnoreCase("BLACK")) {
                System.out.println("Invalid color. Please try again.\n");
                continue;
            }
            
            // Prompt for starting column.
            System.out.print("Enter column (a-h): ");
            String colInput = scanner.nextLine().trim().toLowerCase();
            if (colInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game.");
                scanner.close();
                return;
            }
            if (colInput.length() != 1) {
                System.out.println("Invalid column input. Please try again.\n");
                continue;
            }
            char column = colInput.charAt(0);
            if (column < 'a' || column > 'h') {
                System.out.println("Invalid column. Valid columns are a-h.\n");
                continue;
            }
            
            // Prompt for starting row.
            System.out.print("Enter row (1-8): ");
            String rowInput = scanner.nextLine().trim();
            if (rowInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game.");
                scanner.close();
                return;
            }
            int row;
            try {
                row = Integer.parseInt(rowInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid row input. Please try again.\n");
                continue;
            }
            if (!ChessBoard.withinChessboard(column, row)) {
                System.out.println("Position is out of chessboard boundaries. Valid columns are a-h and rows 1-8.\n");
                continue;
            }
            
            // Create the appropriate chess piece based on the provided type.
            ChessPiece piece = null;
            if (type.equalsIgnoreCase("Pawn")) {
                piece = new Pawn(color, column, row);
            } else if (type.equalsIgnoreCase("Rook")) {
                piece = new Rook(color, column, row);
            } else if (type.equalsIgnoreCase("Knight")) {
                piece = new Knight(color, column, row);
            } else if (type.equalsIgnoreCase("Bishop")) {
                piece = new Bishop(color, column, row);
            } else if (type.equalsIgnoreCase("Queen")) {
                piece = new Queen(color, column, row);
            } else if (type.equalsIgnoreCase("King")) {
                piece = new King(color, column, row);
            }
            
            pieces[count] = piece;
            count++;
            System.out.println(piece.getPieceName() + " added at " + piece.getColumn() + ", " + piece.getRow() + "\n");
        }
        
        // Now prompt for moving each individual piece.
        System.out.println("All pieces have been added. Now, move each piece individually.");
        for (ChessPiece piece : pieces) {
            System.out.println("Moving " + piece.getPieceName() + " originally at " + piece.getColumn() + ", " + piece.getRow());
            
            // Prompt for target column for the current piece.
            System.out.print("Enter target column (a-h) for " + piece.getPieceName() + ": ");
            String targetColInput = scanner.nextLine().trim().toLowerCase();
            if (targetColInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game.");
                scanner.close();
                return;
            }
            if (targetColInput.length() != 1) {
                System.out.println("Invalid target column. Skipping move for " + piece.getPieceName() + ".\n");
                continue;
            }
            char targetColumn = targetColInput.charAt(0);
            if (targetColumn < 'a' || targetColumn > 'h') {
                System.out.println("Invalid target column. Valid columns are a-h. Skipping move for " + piece.getPieceName() + ".\n");
                continue;
            }
            
            // Prompt for target row for the current piece.
            System.out.print("Enter target row (1-8) for " + piece.getPieceName() + ": ");
            String targetRowInput = scanner.nextLine().trim();
            if (targetRowInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game.");
                scanner.close();
                return;
            }
            int targetRow;
            try {
                targetRow = Integer.parseInt(targetRowInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid target row. Skipping move for " + piece.getPieceName() + ".\n");
                continue;
            }
            if (!ChessBoard.withinChessboard(targetColumn, targetRow)) {
                System.out.println("Target position is out of chessboard boundaries. Skipping move for " + piece.getPieceName() + ".\n");
                continue;
            }
            
            // Verify move and optionally update piece's position.
            String message = piece.verifyMove(targetColumn, targetRow)
                ? piece.getPieceName() + " moved from " + piece.getColumn() + ", " + piece.getRow() + " to " + targetColumn + ", " + targetRow
                : piece.getPieceName() + " at " + piece.getColumn() + ", " + piece.getRow() + " can NOT move to " + targetColumn + ", " + targetRow;
            System.out.println(message + "\n");
            
            // If the move is valid, update the piece's location.
            if (piece.verifyMove(targetColumn, targetRow)) {
                piece.setColumn(targetColumn);
                piece.setRow(targetRow);
            }
        }
        
        scanner.close();
    }
}