package pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

  public Queen(Board board, Color color) {
    super(board, color);
  }

  @Override
  public String toString() {
    return "Q";
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    // start with a neutral position object
    Position pos = new Position(0, 0);

    // cima (up)
    pos.setValues(position.getRow() - 1, position.getColumn());
    while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
      pos.setRow(pos.getRow() - 1);
    }
    if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
    }

    // baixo (down)
    pos.setValues(position.getRow() + 1, position.getColumn());
    while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
      pos.setRow(pos.getRow() + 1);
    }
    if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
    }

    // esquerda (left)
    pos.setValues(position.getRow(), position.getColumn() - 1);
    while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
      pos.setColumn(pos.getColumn() - 1);
    }
    if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
    }

    // direita (right)
    pos.setValues(position.getRow(), position.getColumn() + 1);
    while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
      pos.setColumn(pos.getColumn() + 1);
    }
    if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
    }

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
