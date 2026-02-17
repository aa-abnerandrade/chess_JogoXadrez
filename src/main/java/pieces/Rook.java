package pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

  public Rook(Board board, Color color) {
    super(board, color);
  }

  @Override
  public String toString() {
    return "R";
  }

  @Override
  public boolean[][] allPossibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position pos = new Position(getBoard().getRows(), getBoard().getColumns());

    // cima
    pos.setValues(position.getRow() - 1, position.getColumn());
    while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
      pos.setRow(pos.getRow() - 1);
    }
    if (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
    }
    // baixo
    pos.setValues(position.getRow() + 1, position.getColumn());
    while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
      pos.setRow(pos.getRow() + 1);
    }
    if (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
    }
    // esquerda
    pos.setValues(position.getRow(), position.getColumn() - 1);
    while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
      pos.setColumn(pos.getColumn() - 1);
    }
    if (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
    }
    // direita
    pos.setValues(position.getRow(), position.getColumn() + 1);
    while (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
      pos.setColumn(pos.getColumn() + 1);
    }
    if (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
      mat[pos.getRow()][pos.getColumn()] = true;
    }

    // finaliza
    return mat;
  }
}
