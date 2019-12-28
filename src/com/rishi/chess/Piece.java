package com.rishi.chess;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Piece {
    protected Cordinate cordinate;
    protected ChessBoard board;
    public Cell cell;


    protected enum PieceColor {
        WHITE,
        BLACK
    }

    protected PieceColor pieceColor;
    protected PieceDirection pieceDirection;
    protected enum PieceDirection {
        UP,
        DOWN
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
        return new ArrayList<Move>();
    }

    public Cell getEnclosingCell() {
        return board.getCellFromCordinate(cordinate);
    }
}

