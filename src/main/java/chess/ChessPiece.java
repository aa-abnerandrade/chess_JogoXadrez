package chess;

import boardgame.Board;
import boardgame.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class ChessPiece extends Piece {
  @Getter
  private Board board;

  @Getter
  private Color color;

}
