package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class ChessPiece extends Piece {
  @Getter
  private Board board;

  @Getter
  private Color color;

  protected boolean isThereOpponentPiece(Position position) {
    ChessPiece p = (ChessPiece) getBoard().getSquareFromPieces(position);
    return p != null && p.getColor() != color;
  }

}
