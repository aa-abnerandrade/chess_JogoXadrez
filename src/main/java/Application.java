import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exception.ChessException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

  public static void main(String[] args) {
    Scanner scannerIn = new Scanner(System.in);
    ChessMatch chessMatch = new ChessMatch();

    while (true) {
      try {
        UserInterface.clearScreen();
        UserInterface.printBoard(chessMatch.getPieces());
        System.out.println();
        System.out.print("Source: ");
        ChessPosition source = UserInterface.readChessPosition(scannerIn);
        System.out.println();
        System.out.print("Target: ");
        ChessPosition target = UserInterface.readChessPosition(scannerIn);
        ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
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

  }
}
