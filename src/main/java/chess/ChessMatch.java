package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.exception.ChessException;
import lombok.Getter;
import pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

  private final Board mainBoard;

  @Getter
  private int turn;
  @Getter
  private Color currentPlayer;
  @Getter
  private boolean check;
  @Getter
  private boolean checkMate;
  @Getter
  private ChessPiece enPassantVulnerable;
  @Getter
  private ChessPiece promoted;

  private final List<Piece> piecesOnTheBoard = new ArrayList<>();

  private final List<Piece> capturedPieces = new ArrayList<>();


  public ChessMatch() {
    this.mainBoard = new Board(8, 8);
    turn = 1;
    currentPlayer = Color.WHITE;
    initialSetup();
  }


  public ChessPiece[][] getPieces() {
    ChessPiece[][] mat = new ChessPiece[mainBoard.getRows()][mainBoard.getColumns()];
    for (int i = 0; i< mainBoard.getRows(); i++) {
      for (int j = 0; j< mainBoard.getColumns(); j++) {
        mat[i][j] = (ChessPiece) mainBoard.getSquareFromPieces(i, j);
      }
    }
    return mat;
  }

  public boolean[][] possibleMoves(ChessPosition sourcePosition) {
    Position position = sourcePosition.toPosition();
    validateSourcePosition(position);
    return mainBoard.getSquareFromPieces(position).possibleMoves();
  }

  public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
    Position source = sourcePosition.toPosition();
    Position target = targetPosition.toPosition();
    validateSourcePosition(source);
    validadeTargetPosition(source, target);
    Piece capturedPiece = makeMove(source, target);
    if (testCheck(currentPlayer)) {
      undoMove(source, target, capturedPiece);
      throw new ChessException("You can't put yourself in check");
    }
    ChessPiece movedPiece = (ChessPiece) mainBoard.getSquareFromPieces(target);

    // move promotion
    promoted = null;
    if (movedPiece instanceof Pawn) {
      if ((movedPiece.getColor() == Color.WHITE && target.getRow() == 0) || (movedPiece.getColor() == Color.BLACK && target.getRow() == 7)) {
        promoted = (ChessPiece) mainBoard.getSquareFromPieces(target);
        promoted = replacePromotedPiece("Q");
      }
    }

    check = testCheck(opponent(currentPlayer));
    if (testCheckMate(opponent(currentPlayer))) {
      checkMate = true;
    }
    nextTurn();

    // special move en passant
    if (movedPiece instanceof Pawn && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)) {
      enPassantVulnerable = (Pawn) movedPiece;
    } else {
      enPassantVulnerable = null;
    }

    return (ChessPiece)capturedPiece;
  }

  public ChessPiece replacePromotedPiece(String type) {
    if (promoted == null) {
      throw new IllegalStateException("There is no piece to be promoted");
    }
    if (!type.equals("B") && (!type.equals("N")) && (!type.equals("R")) && (!type.equals("Q"))) {
      return promoted;
    }
    Position pos = promoted.getChessPosition().toPosition();
    Piece p = mainBoard.removePiece(pos);
    piecesOnTheBoard.remove(p);

    ChessPiece newPiece = newPiece(type, promoted.getColor());
    mainBoard.placePiece(newPiece, pos);
    piecesOnTheBoard.add(newPiece);

    return newPiece;
  }

  private ChessPiece newPiece(String type, Color color) {
    if (type.equals("B")) return new Bishop(mainBoard, color);
    if (type.equals("N")) return new Knight(mainBoard, color);
    if (type.equals("R")) return new Rook(mainBoard, color);
    return new Queen(mainBoard, color);
  }

  private Piece makeMove(Position source, Position target) {
    ChessPiece p = (ChessPiece) mainBoard.removePiece(source);
    p.increaseMoveCount();
    Piece capturedPiece = mainBoard.removePiece(target);
    mainBoard.placePiece(p, target);
    if (capturedPiece != null) {
      piecesOnTheBoard.remove(capturedPiece);
      capturedPieces.add(capturedPiece);
    }

    // move castling roque pequeno
    if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
      Position sourceRook = new Position(source.getRow(), source.getColumn() + 3);
      Position targetRook = new Position(source.getRow(), source.getColumn() + 1);
      ChessPiece rook = (ChessPiece) mainBoard.removePiece(sourceRook);
      mainBoard.placePiece(rook, targetRook);
      rook.increaseMoveCount();
    }
    // move castling roque grande
    if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
      Position sourceRook = new Position(source.getRow(), source.getColumn() - 4);
      Position targetRook = new Position(source.getRow(), source.getColumn() - 1);
      ChessPiece rook = (ChessPiece) mainBoard.removePiece(sourceRook);
      mainBoard.placePiece(rook, targetRook);
      rook.increaseMoveCount();
    }

    // move en passant
    if (p instanceof Pawn) {
      if (source.getColumn() != target.getColumn() && capturedPiece == null) {
        Position pawnPosition;
        if (p.getColor() == Color.WHITE) {
          pawnPosition = new Position(target.getRow() + 1, target.getColumn());
        } else {
          pawnPosition = new Position(target.getRow() - 1, target.getColumn());
        }
        capturedPiece = mainBoard.removePiece(pawnPosition);
        capturedPieces.add(capturedPiece);
        piecesOnTheBoard.remove(capturedPiece);
      }
    }

    return capturedPiece;
  }

  private void undoMove(Position source, Position target, Piece capturedPiece) {
    ChessPiece p = (ChessPiece) mainBoard.removePiece(target);
    p.decreaseMoveCount();
    mainBoard.placePiece(p, source);
    if (capturedPiece != null) {
      mainBoard.placePiece(capturedPiece, target);
      capturedPieces.remove(capturedPiece);
      piecesOnTheBoard.add(capturedPiece);
    }

    // move castling roque pequeno
    if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
      Position sourceRook = new Position(source.getRow(), source.getColumn() + 3);
      Position targetRook = new Position(source.getRow(), source.getColumn() + 1);
      ChessPiece rook = (ChessPiece) mainBoard.removePiece(targetRook);
      mainBoard.placePiece(rook, sourceRook);
      rook.decreaseMoveCount();
    }
    // move castling roque grande
    if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
      Position sourceRook = new Position(source.getRow(), source.getColumn() - 4);
      Position targetRook = new Position(source.getRow(), source.getColumn() - 1);
      ChessPiece rook = (ChessPiece) mainBoard.removePiece(targetRook);
      mainBoard.placePiece(rook, sourceRook);
      rook.decreaseMoveCount();
    }

    // move en passant
    if (p instanceof Pawn) {
      if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {
        ChessPiece pawn = (ChessPiece) mainBoard.removePiece(target);
        Position pawnPosition;
        if (p.getColor() == Color.WHITE) {
          pawnPosition = new Position(3, target.getColumn());
        } else {
          pawnPosition = new Position(4, target.getColumn());
        }
        mainBoard.placePiece(pawn, pawnPosition);
      }
    }
  }

  private void validateSourcePosition(Position position) {
    if (!mainBoard.thereIsAPiece(position)) {
      throw new ChessException("ChessMatch: There is no piece on source position");
    }
    if (currentPlayer != ((ChessPiece) mainBoard.getSquareFromPieces(position)).getColor()) {
      throw new ChessException("ChessMatch: The chosen piece is not yours");
    }
    if (!mainBoard.getSquareFromPieces(position).isThereAnyPossibleMove()) {
      throw new ChessException("ChessMatch: There is no possible moves for the chosen piece");
    }
  }

  private void validadeTargetPosition(Position source, Position target) {
    if (!mainBoard.getSquareFromPieces(source).isPossibleMove(target)) {
      throw new ChessException("ChessMatch: The chosen piece can't move to target position");
    }
  }

  private void nextTurn() {
    turn++;
    currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
  }

  private Color opponent(Color color) {
    return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
  }

  private ChessPiece king(Color color) {
    List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).toList();
    for (Piece p : list) {
      if (p instanceof King) {
        return (ChessPiece) p;
      }
    }
    throw new IllegalStateException("There is no " + color + " king on the board");
  }

  private boolean testCheck(Color color) {
    Position kingPosition = king(color).getChessPosition().toPosition();
    List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).toList();
    for (Piece p : opponentPieces) {
      boolean[][] mat = p.possibleMoves();
      if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
        return true;
      }
    }
    return false;
  }

  private boolean testCheckMate(Color color) {
    if (!testCheck(color) ) {
      return false;
    }
    List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).toList();
    for (Piece p : list) {
      boolean[][] mat = p.possibleMoves();
      for (int i = 0; i < mainBoard.getRows(); i++) {
        for (int j = 0; j < mainBoard.getColumns(); j++) {
          if (mat[i][j]) {
            Position source = ((ChessPiece) p).getChessPosition().toPosition();
            Position target = new Position(i, j);
            Piece capturedPiece = makeMove(source, target);
            boolean testCheck = testCheck(color);
            undoMove(source, target, capturedPiece);
            if (!testCheck) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  private void placeNewPiece(char column, int row, ChessPiece piece) {
    Position cp = new ChessPosition(column, row).toPosition();
    mainBoard.placePiece(piece, cp);
    piecesOnTheBoard.add(piece);
  }

  private void initialSetup() {
    placeNewPiece('a', 1, new Rook(mainBoard, Color.WHITE));
    placeNewPiece('b', 1, new Knight(mainBoard, Color.WHITE));
    placeNewPiece('c', 1, new Bishop(mainBoard, Color.WHITE));
    placeNewPiece('d', 1, new Queen(mainBoard, Color.WHITE));
    placeNewPiece('e', 1, new King(mainBoard, Color.WHITE, this));
    placeNewPiece('f', 1, new Bishop(mainBoard, Color.WHITE));
    placeNewPiece('g', 1, new Knight(mainBoard, Color.WHITE));
    placeNewPiece('h', 1, new Rook(mainBoard, Color.WHITE));
    placeNewPiece('a', 2, new Pawn(mainBoard, Color.WHITE, this));
    placeNewPiece('b', 2, new Pawn(mainBoard, Color.WHITE, this));
    placeNewPiece('c', 2, new Pawn(mainBoard, Color.WHITE, this));
    placeNewPiece('d', 2, new Pawn(mainBoard, Color.WHITE, this));
    placeNewPiece('e', 2, new Pawn(mainBoard, Color.WHITE, this));
    placeNewPiece('f', 2, new Pawn(mainBoard, Color.WHITE, this));
    placeNewPiece('g', 2, new Pawn(mainBoard, Color.WHITE, this));
    placeNewPiece('h', 2, new Pawn(mainBoard, Color.WHITE, this));

    placeNewPiece('a', 8, new Rook(mainBoard, Color.BLACK));
    placeNewPiece('b', 8, new Knight(mainBoard, Color.BLACK));
    placeNewPiece('c', 8, new Bishop(mainBoard, Color.BLACK));
    placeNewPiece('d', 8, new Queen(mainBoard, Color.BLACK));
    placeNewPiece('e', 8, new King(mainBoard, Color.BLACK, this));
    placeNewPiece('f', 8, new Bishop(mainBoard, Color.BLACK));
    placeNewPiece('g', 8, new Knight(mainBoard, Color.BLACK));
    placeNewPiece('h', 8, new Rook(mainBoard, Color.BLACK));
    placeNewPiece('a', 7, new Pawn(mainBoard, Color.BLACK, this));
    placeNewPiece('b', 7, new Pawn(mainBoard, Color.BLACK, this));
    placeNewPiece('c', 7, new Pawn(mainBoard, Color.BLACK, this));
    placeNewPiece('d', 7, new Pawn(mainBoard, Color.BLACK, this));
    placeNewPiece('e', 7, new Pawn(mainBoard, Color.BLACK, this));
    placeNewPiece('f', 7, new Pawn(mainBoard, Color.BLACK, this));
    placeNewPiece('g', 7, new Pawn(mainBoard, Color.BLACK, this));
    placeNewPiece('h', 7, new Pawn(mainBoard, Color.BLACK, this));
  }

}
