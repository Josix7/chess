// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// public class ChessFileReader {

//     /**
//      * Reads the file at 'filename' and returns a List of ChessPiece objects.
//      */
//     public List<ChessPiece> readPiecesFromFile(String filename) {
//         List<ChessPiece> pieces = new ArrayList<>();

//         try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//             String line;
//             while ((line = br.readLine()) != null) {
//                 // Example line: "Pawn,white,A,2"
//                 String[] parts = line.split(",");
//                 if (parts.length < 4) {
//                     // Not a valid line, skip or handle error
//                     continue;
//                 }
//                 String pieceName = parts[0];
//                 String color = parts[1];
//                 char posX = parts[2].charAt(0);  // e.g., 'A'
//                 int posY = Integer.parseInt(parts[3]);

//                 ChessPiece piece = createPiece(pieceName, color, posX, posY);
//                 if (piece != null) {
//                     pieces.add(piece);
//                 }
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }

//         return pieces;
//     }

//     /**
//      * Creates the correct ChessPiece subclass
//      * based on the pieceName read from file.
//      */
//     private ChessPiece createPiece(String pieceName, String color, char posX, int posY) {
//         pieceName = pieceName.toLowerCase(); // normalize to lowercase
//         switch (pieceName) {
//             case "pawn":
//                 return new Pawn(color, posX, posY);
//             case "rook":
//                 return new Rook(color, posX, posY);
//             case "knight":
//                 return new Knight(color, posX, posY);
//             case "bishop":
//                 return new Bishop(color, posX, posY);
//             case "queen":
//                 return new Queen(color, posX, posY);
//             case "king":
//                 return new King(color, posX, posY);
//             default:
//                 System.err.println("Unknown piece type: " + pieceName);
//                 return null;
//         }
//     }
// }