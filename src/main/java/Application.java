import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exception.ChessException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {

  public static void main(String[] args) {
    Scanner scannerIn = new Scanner(System.in);
    ChessMatch chessMatch = new ChessMatch();
    List<ChessPiece> captured = new ArrayList<>();

    while (!chessMatch.getCheckMate()) {
      try {
        UserInterface.clearScreen();
        UserInterface.printMatch(chessMatch, captured);
        System.out.println();
        System.out.print("Source: ");
        ChessPosition source = UserInterface.readChessPosition(scannerIn);
        boolean[][] possibleMoves = chessMatch.possibleMoves(source);
        UserInterface.clearScreen();
        UserInterface.printBoard(chessMatch.getPieces(), possibleMoves);
        System.out.println();
        System.out.print("Target: ");
        ChessPosition target = UserInterface.readChessPosition(scannerIn);
        ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

        if (capturedPiece != null) {
          captured.add(capturedPiece);
        }

        if (chessMatch.getPromoted() != null) {
          System.out.print("Enter piece for promotion (B/N/R/Q): ");
          String type = scannerIn.nextLine().toUpperCase();
          while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
            System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
            type = scannerIn.nextLine().toUpperCase();
          }
          chessMatch.replacePromotedPiece(type);
        }

      }
      catch (ChessException chessEx) {
        System.out.println(chessEx.getMessage());
        System.out.println("Press Enter to continue...");
        scannerIn.nextLine();
      }
      catch (InputMismatchException imEx) {
        System.out.println(imEx.getMessage());
        System.out.println("Press Enter to continue...");
        scannerIn.nextLine();
      }
    }
    UserInterface.clearScreen();
    UserInterface.printMatch(chessMatch, captured);

  }
}
