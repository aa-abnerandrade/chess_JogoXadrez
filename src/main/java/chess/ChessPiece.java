package chess;

import boardgame.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ChessPiece extends Piece {

  @Getter
  private Color color;
}
