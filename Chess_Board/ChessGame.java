import java.util.ArrayList;
import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        
        while (true) {
            System.out.println("Choose an action: add / move / exit");
            String action = scanner.nextLine().trim().toLowerCase();
            
            if (action.equals("exit")) {
                System.out.println("Exiting the game.");
                break;
            } else if (action.equals("add")) {
                // Adding a new chess piece
                System.out.println("Enter details for the new chess piece:");
                System.out.print("Select chess piece (Pawn, Rook, Knight, Bishop, Queen, King): ");
                String type = scanner.nextLine().trim();
                
                // Validate type
                if (!type.equalsIgnoreCase("Pawn") && !type.equalsIgnoreCase("Rook") &&
                    !type.equalsIgnoreCase("Knight") && !type.equalsIgnoreCase("Bishop") &&
                    !type.equalsIgnoreCase("Queen") && !type.equalsIgnoreCase("King")) {
                    System.out.println("Invalid chess piece type. Please try again.\n");
                    continue;
                }
                
                System.out.print("Enter color (WHITE or BLACK): ");
                String color = scanner.nextLine().trim();
                if (!color.equalsIgnoreCase("WHITE") && !color.equalsIgnoreCase("BLACK")) {
                    System.out.println("Invalid color. Please try again.\n");
                    continue;
                }
                
                System.out.print("Enter column (a-h): ");
                String colInput = scanner.nextLine().trim().toLowerCase();
                if (colInput.length() != 1) {
                    System.out.println("Invalid column input. Please try again.\n");
                    continue;
                }
                char column = colInput.charAt(0);
                
                System.out.print("Enter row (1-8): ");
                int row;
                try {
                    row = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid row input. Please try again.\n");
                    continue;
                }
                
                // Validate coordinates
                if (!ChessBoard.withinChessboard(column, row)) {
                    System.out.println("Position is out of chessboard boundaries. Valid columns are a-h and rows 1-8.\n");
                    continue;
                }
                
                // Create the appropriate chess piece based on user input.
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
                
                pieces.add(piece);
                System.out.println("Chess piece added: " + piece.getPieceName() + " at " + piece.getColumn() + ", " + piece.getRow() + "\n");
                
            } else if (action.equals("move")) {
                // Make sure there is at least one piece to move.
                if (pieces.isEmpty()) {
                    System.out.println("No pieces available to move. Please add a piece first.\n");
                    continue;
                }
                
                // Display all pieces with their indices
                System.out.println("Current pieces on the board:");
                for (int i = 0; i < pieces.size(); i++) {
                    ChessPiece p = pieces.get(i);
                    System.out.println((i + 1) + ". " + p.getPieceName() + " at " + p.getColumn() + ", " + p.getRow());
                }
                
                System.out.print("Enter the piece number you want to move: ");
                int pieceIndex;
                try {
                    pieceIndex = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid piece number. Please try again.\n");
                    continue;
                }
                
                if (pieceIndex < 1 || pieceIndex > pieces.size()) {
                    System.out.println("Piece number out of range. Please try again.\n");
                    continue;
                }
                
                System.out.print("Enter target column (a-h): ");
                String colInput = scanner.nextLine().trim().toLowerCase();
                if (colInput.length() != 1) {
                    System.out.println("Invalid column input. Please try again.\n");
                    continue;
                }
                char targetColumn = colInput.charAt(0);
                
                System.out.print("Enter target row (1-8): ");
                int targetRow;
                try {
                    targetRow = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid row input. Please try again.\n");
                    continue;
                }
                
                if (!ChessBoard.withinChessboard(targetColumn, targetRow)) {
                    System.out.println("Target position is out of chessboard boundaries. Valid columns are a-h and rows 1-8.\n");
                    continue;
                }
                
                // Retrieve the selected piece and check if it can move
                ChessPiece piece = pieces.get(pieceIndex - 1);
                if (piece.verifyMove(targetColumn, targetRow)) {
                    piece.setColumn(targetColumn);
                    piece.setRow(targetRow);
                    System.out.println(piece.getPieceName() + " moved to " + targetColumn + ", " + targetRow + "\n");
                } else {
                    
                    System.out.println(piece.getPieceName() + " at " + piece.getColumn() + ", " + piece.getRow() +
                                       " cannot move to " + targetColumn + ", " + targetRow + "\n");

                }
            } else {

                System.out.println("Unknown command. Please enter 'add', 'move', or 'exit'.\n");
                
            }
        }
        
        scanner.close();
    }
}