package com.rishi.chess;

import java.util.ArrayList;

public class Piece {
    protected int x;
    protected int y;
    protected ChessBoard board;
    public Cell cell;


    protected enum PieceColor {
        WHITE,
        BLACK
    }
    protected PieceColor pieceColor;

    protected enum PieceDirection {
        UP,
        DOWN
    }
    protected PieceDirection pieceDirection;

    public Piece() {}
    public String pieceIconPath = "art/";


    public boolean isMoveLegal(Cordinate dest) {
        System.out.println("This should not be called");
        return true;
    }

    public ArrayList<Move> generateLegalMovesForPiece() {
        // do nothing, will be overridden.
        System.out.println("Should not come here in genearteLegalMovesForPiece");
        return null;
    }
}

