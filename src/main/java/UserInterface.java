import chess.ChessPiece;

public class UserInterface {

  public static void printBoard(ChessPiece[][] allPieces) {
    for (int head = 0; head < allPieces.length; head++) {
      System.out.print((8 - head) + " ");
      for (int body = 0; body < allPieces.length; body++) {
        printPiece(allPieces[head][body]);
      }
      System.out.println();
    }
    System.out.println("  a b c d e f g h");
  }

  private static void printPiece(ChessPiece onePiece) {
    if (onePiece == null) {
      System.out.print("-");
    } else {
      System.out.print(onePiece);
    }

    System.out.print(" ");
  }
}
