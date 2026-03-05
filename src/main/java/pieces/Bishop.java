package pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

  public Bishop(Board board, Color color) {
    super(board, color);
  }

  @Override
  public String toString() {
    return "B";
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    // start with a neutral position object
    Position pos = new Position(0, 0);

    // noroeste (nw)
    pos.setValues(position.getRow() - 1, position.getColumn() - 1);
    while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
      pos.setValues(pos.getRow() - 1, pos.getColumn() - 1);
    }
    if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
    }

    // sudoeste (sw)
    pos.setValues(position.getRow() + 1, position.getColumn() - 1);
    while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
      pos.setValues(pos.getRow() + 1, pos.getColumn() - 1);
    }
    if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
    }

    // nordeste (ne)
    pos.setValues(position.getRow() - 1, position.getColumn() + 1);
    while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
      pos.setValues(pos.getRow() - 1, pos.getColumn() + 1);
    }
    if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
    }

    // sudeste (se)
    pos.setValues(position.getRow() + 1, position.getColumn() + 1);
    while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
      pos.setValues(pos.getRow() + 1, pos.getColumn() + 1);
    }
    if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
    }

    // finaliza
    return mat;
  }
}
