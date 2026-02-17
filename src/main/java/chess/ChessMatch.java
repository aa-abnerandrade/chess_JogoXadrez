package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.exception.ChessException;
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

  public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
    Position source = sourcePosition.toPosition();
    Position target = targetPosition.toPosition();
    validateSourcePosition(source);
    Piece capturedPiece = makeMove(source, target);
    return (ChessPiece) capturedPiece;
  }

  private Piece makeMove(Position source, Position target) {
    Piece p = board.removePiece(source);
    Piece capturedPiece = board.removePiece(target);
    board.placePiece(p, target);
    return capturedPiece;
  }

  private void validateSourcePosition(Position source) {
    if (!board.thereIsAPiece(source)) {
      throw new ChessException("ChessMatch: There is no piece on source position");
    }
  }

  private void placeNewPiece(char column, int row, ChessPiece piece) {
    Position cp = new ChessPosition(column, row).toPosition();
    board.placePiece(piece, cp);
  }

  private void initialSetup() {
    placeNewPiece('c', 1, new Rook(board, Color.WHITE));
    placeNewPiece('c', 2, new Rook(board, Color.WHITE));
    placeNewPiece('d', 2, new Rook(board, Color.WHITE));
    placeNewPiece('e', 2, new Rook(board, Color.WHITE));
    placeNewPiece('e', 1, new Rook(board, Color.WHITE));
    placeNewPiece('d', 1, new King(board, Color.WHITE));

    placeNewPiece('c', 7, new Rook(board, Color.BLACK));
    placeNewPiece('c', 8, new Rook(board, Color.BLACK));
    placeNewPiece('d', 7, new Rook(board, Color.BLACK));
    placeNewPiece('e', 7, new Rook(board, Color.BLACK));
    placeNewPiece('e', 8, new Rook(board, Color.BLACK));
    placeNewPiece('d', 8, new King(board, Color.BLACK));
  }

}
