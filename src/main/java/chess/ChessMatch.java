package chess;

import boardgame.Board;
import boardgame.Position;
import pieces.King;
import pieces.Rook;

public class ChessMatch {
  private Board board;

  public ChessMatch() {
    this.board = new Board(8, 8);
    initialSetup();
  }

  public ChessPiece[][] getPieces() {
    ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
    for (int r = 0; r < board.getRows(); r++) {
      for (int c = 0; c < board.getColumns(); c++) {
        mat[r][c] = (ChessPiece) board.getSquareFromPieces(r, c);
      }
    }
    return mat;
  }

  private void initialSetup() {
    board.placePiece(new Rook(board, Color.WHITE), new Position(2, 0));
    board.placePiece(new Rook(board, Color.BLACK), new Position(7, 0));
    board.placePiece(new King(board, Color.WHITE), new Position(0, 4));
    board.placePiece(new King(board, Color.BLACK), new Position(7, 4));
  }

}
