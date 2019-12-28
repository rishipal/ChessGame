package com.rishi.chess;

import java.util.ArrayList;

public class Cell {
    private final ChessBoard board;
    private Cordinate cordinate;
    public boolean occupied = false;
    public Piece piece = null;
    ArrayList<Move> legalMoves;
    public Cell( Cordinate cordinate, ChessBoard cb) {
        this.cordinate = cordinate;
        this.board = cb;
        this.legalMoves = new ArrayList<>();
    }

    public void setPiece(Piece p) {
        this.piece = p;
        this.occupied = true;
        this.piece.cell = this;
        this.piece.setNewCordinates(this.cordinate);
        this.legalMoves = this.piece.generateLegalMovesForPiece();
    }

    public void unsetPiece() {
        this.piece = null;
        this.occupied = false;
        this.legalMoves.clear();
    }

    public Cordinate getCordinate() {
        return this.cordinate;
    }

    public int getTileIDFromCell() {
        return this.cordinate.getTileIDFromCordinate(board.SIZE_BOARD);
    }

    public ArrayList<Move> getLegalMoves() {
        return this.legalMoves;
    }
}