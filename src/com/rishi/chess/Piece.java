package com.rishi.chess;

public class Piece {
    protected int x;
    protected int y;

    protected enum PieceColor {
        WHITE,
        BLACK
    }
    protected PieceColor pieceColor;

    public Piece() {}
    public String pieceIconPath = "art/";



   /* public boolean isMoveLegal(int end_x, int end_y) {
        System.out.println("This should not be called");
        return true;
    }*/
}

