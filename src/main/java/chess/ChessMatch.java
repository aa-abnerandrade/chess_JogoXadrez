package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.exception.ChessException;
import lombok.Getter;
import pieces.King;
import pieces.Rook;

public class ChessMatch {

  @Getter
  private int turn;
  @Getter
  private Color currentPlayer;

  private Board board;

  public ChessMatch() {
    this.board = new Board(8, 8);
    turn = 1;
    currentPlayer = Color.WHITE;
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

  public boolean[][] possibleMoves(ChessPosition sourcePosition) {
    Position position = sourcePosition.toPosition();
    validateSourcePosition(position);
    return board.getSquareFromPieces(position).allPossibleMoves();
  }

  public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
    Position source = sourcePosition.toPosition();
    Position target = targetPosition.toPosition();
    validateSourcePosition(source);
    validadeTargetPosition(source, target);
    Piece capturedPiece = makeMove(source, target);
    nextTurn();
    return (ChessPiece) capturedPiece;
  }

  private Piece makeMove(Position source, Position target) {
    Piece p = board.removePiece(source);
    Piece capturedPiece = board.removePiece(target);
    board.placePiece(p, target);
    return capturedPiece;
  }

  private void validateSourcePosition(Position position) {
    if (!board.thereIsAPiece(position)) {
      throw new ChessException("ChessMatch: There is no piece on source position");
    }
    if (currentPlayer != ((ChessPiece) board.getSquareFromPieces(position)).getColor()) {
      throw new ChessException("ChessMatch: The chosen piece is not yours");
    }
    if (!board.getSquareFromPieces(position).isThereAnyPossibleMove()) {
      throw new ChessException("ChessMatch: There is no possible moves for the chosen piece");
    }
  }

  private void validadeTargetPosition(Position source, Position target) {
    if (!board.getSquareFromPieces(source).isPossibleMove(target)) {
      throw new ChessException("ChessMatch: The chosen piece can't move to target position");
    }
  }

  private void nextTurn() {
    turn++;
    currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
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
