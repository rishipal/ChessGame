package com.rishi.chess;
import java.util.ArrayList;

public class Piece {
    protected Cordinate cordinate;
    protected ChessBoard board;
    public Cell cell;
    //TODO(rishipal): Add a player here?

    protected enum PieceColor {
        WHITE,
        BLACK
    }

    protected PieceColor pieceColor;
    protected PieceDirection pieceDirection; // TODO(rishipal): rename this to player direction

    //TODO(rishipal): This should be defined elsewhere. Make it used by path calculator for all pieces.
    protected enum PieceDirection {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        NORTHEAST,
        NORTHWEST,
        SOUTHEAST,
        SOUTHWEST,
        UNKNOWN
    }

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.pieceDirection = this.pieceColor == PieceColor.BLACK? PieceDirection.UP : PieceDirection.DOWN;

    }
    public String pieceIconPath = "art/";

    public void setNewCordinates(Cordinate c) {
        this.cordinate = c;
    }

    // do nothing, will be overridden.
    public boolean isMoveLegal(Cordinate dest) {
        System.out.println("This should not be called");
        return true;
    }

    public ArrayList<Move> generateLegalMovesForPiece() {
        // do nothing, will be overridden.
        System.out.println("Should not come here in generateLegalMovesForPiece");
        return new ArrayList<>();
    }

    public ArrayList<Move> generatePathsForLegalMoves() {
        // do nothing, will be overridden.
        System.out.println("Should not come here in generatePathsForLegalMoves");
        return new ArrayList<>();
    }

    public Cell getEnclosingCell() {
        return board.getCellFromCordinate(cordinate);
    }
}

