  package pieces;

  import boardgame.Board;
  import boardgame.Position;
  import chess.ChessMatch;
  import chess.ChessPiece;
  import chess.Color;

  public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
      super(board, color);
      this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
      return "K";
    }

    private boolean canMove(Position position) {
      ChessPiece p = (ChessPiece) getBoard().getSquareFromPieces(position);
      return p == null || p.getColor() != getColor();
    }

    private boolean testRookCastling(Position position) {
      ChessPiece p = (ChessPiece) getBoard().getSquareFromPieces(position);
      return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {
      boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
      Position pos = new Position(0, 0);
      // acima
      pos.setValues(position.getRow() - 1, position.getColumn());
      if (getBoard().positionExists(pos) && canMove(pos)) {
        mat[pos.getRow()][pos.getColumn()] = true;
      }
      // abaixo
      pos.setValues(position.getRow() + 1, position.getColumn());
      if (getBoard().positionExists(pos) && canMove(pos)) {
        mat[pos.getRow()][pos.getColumn()] = true;
      }
      // esquerda
      pos.setValues(position.getRow(), position.getColumn() - 1);
      if (getBoard().positionExists(pos) && canMove(pos)) {
        mat[pos.getRow()][pos.getColumn()] = true;
      }
      // direita
      pos.setValues(position.getRow(), position.getColumn() + 1);
      if (getBoard().positionExists(pos) && canMove(pos)) {
        mat[pos.getRow()][pos.getColumn()] = true;
      }
      // noroeste
      pos.setValues(position.getRow() - 1, position.getColumn() - 1);
      if (getBoard().positionExists(pos) && canMove(pos)) {
        mat[pos.getRow()][pos.getColumn()] = true;
      }
      // nordeste
      pos.setValues(position.getRow() - 1, position.getColumn() + 1);
      if (getBoard().positionExists(pos) && canMove(pos)) {
        mat[pos.getRow()][pos.getColumn()] = true;
      }
      // sudoeste
      pos.setValues(position.getRow() + 1, position.getColumn() - 1);
      if (getBoard().positionExists(pos) && canMove(pos)) {
        mat[pos.getRow()][pos.getColumn()] = true;
      }
      // sudeste
      pos.setValues(position.getRow() + 1, position.getColumn() + 1);
      if (getBoard().positionExists(pos) && canMove(pos)) {
        mat[pos.getRow()][pos.getColumn()] = true;
      }

      // roque (castling)
      if (getMoveCount() == 0 && !chessMatch.getCheck()) {
        // roque pequeno
        Position posRook1 = new Position(position.getRow(), position.getColumn() + 3);
        if (testRookCastling(posRook1)) {
          Position p1 = new Position(position.getRow(), position.getColumn() + 1);
          Position p2 = new Position(position.getRow(), position.getColumn() + 2);
          if (getBoard().getSquareFromPieces(p1) == null && getBoard().getSquareFromPieces(p2) == null) {
            mat[position.getRow()][position.getColumn() + 2] = true;
          }
        }
        // roque grande
        Position posRook2 = new Position(position.getRow(), position.getColumn() - 4);
        if (testRookCastling(posRook2)) {
          Position p1 = new Position(position.getRow(), position.getColumn() - 1);
          Position p2 = new Position(position.getRow(), position.getColumn() - 2);
          Position p3 = new Position(position.getRow(), position.getColumn() - 3);
          if (getBoard().getSquareFromPieces(p1) == null && getBoard().getSquareFromPieces(p2) == null && getBoard().getSquareFromPieces(p3) == null) {
            mat[position.getRow()][position.getColumn() - 2] = true;
          }
        }
      }

      // finaliza
      return mat;
    }
  }
