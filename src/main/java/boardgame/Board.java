package boardgame;

import lombok.Getter;
import lombok.Setter;

public class Board {

  @Getter
  @Setter
  private int rows;

  @Getter
  @Setter
  private int columns;

  private Piece[][] pieces;


  public Board(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    this.pieces = new Piece[rows][columns];
  }

  public Piece getSquareFromPieces(int row, int column) {
    return this.pieces[row][column];
  }

  public Piece getSquareFromPieces(Position position) {
    return this.pieces[position.getRow()][position.getColumn()];
  }
}
