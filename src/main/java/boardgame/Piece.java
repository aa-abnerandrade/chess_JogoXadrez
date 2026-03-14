package boardgame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public abstract class Piece {

  protected Position position;

  @Getter
  protected Board board;

  public Piece(Board board) {
    this.board = board;
    this.position = null;

  }

  public abstract boolean[][] possibleMoves();

  public boolean isPossibleMove(Position position) {
    return possibleMoves()[position.getRow()][position.getColumn()];
  }

  public boolean isThereAnyPossibleMove() {
    boolean[][] mat = possibleMoves();
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {
        if (mat[i][j]) {
          return true;
        }
      }
    }
    return false;
  }

}
