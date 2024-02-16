package chess;

import boardgame.Board;

public class ChessMatch {
  private Board board;

  public ChessMatch() {
    this.board = new Board(8, 8);
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
}
