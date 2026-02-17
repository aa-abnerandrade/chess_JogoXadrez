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

  public abstract boolean[][] allPossibleMoves();

  public boolean isPossibleMove(Position position) {
    return allPossibleMoves()[position.getRow()][position.getColumn()];
  }

  public boolean isThereAnyPossibleMove() {
    boolean[][] mat = allPossibleMoves();
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat.length; j++) {
        if (mat[i][j]) {
          return true;
        }
      }
    }
    return false;
  }

}
