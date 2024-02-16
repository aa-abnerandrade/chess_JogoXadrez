package boardgame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class Piece {

  protected Position position;

  @Getter
  private Board board;

  public Piece(Board board) {
    this.board = board;
    this.position = null;

  }

  public boolean allPossibleMoves() {
    return true;
  }

  public boolean isPossibleMove() {
    return true;
  }
}
