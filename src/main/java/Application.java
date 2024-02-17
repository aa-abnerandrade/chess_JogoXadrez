import chess.ChessMatch;

public class Application {

  public static void main(String[] args) {

    ChessMatch chessMatch = new ChessMatch();
    UserInterface.printBoard(chessMatch.getPieces());
  }
}
