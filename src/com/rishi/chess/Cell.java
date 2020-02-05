package com.rishi.chess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Cell {
    //TODO(rishipal): Remove board from Cell. Logically, cell should not hold a board.
    private final ChessBoard board;
    private Cordinate cordinate;
    public boolean occupied = false;
    public Piece piece = null;

    //TODO(rishipal): Move legalMoves and legalDestinations somewhere else (maybe Piece).
    public ArrayList<Move> legalMoves;
    private Set<Cell> legalDestinations;

    public boolean isActivePlayerCell(Player active) {
        return this.piece !=null && this.piece.pieceColor.equals(active.pieceColor);
    }

    public Cell( Cordinate cordinate, ChessBoard cb) {
        this.cordinate = cordinate;
        this.board = cb;
        this.legalMoves = new ArrayList<>();
        this.legalDestinations = getLegalDestinationsFromMoves();
    }

    // TODO(rishipal): Rename thsi to indicate that you are resetting the piece's data
    public void update() {
        this.legalMoves = new ArrayList<>();
        this.legalDestinations = getLegalDestinationsFromMoves();
    }

    public void setPiece(Piece p) {
        this.piece = p;
        this.occupied = true;
        this.piece.cell = this;
        this.piece.setNewCordinates(this.cordinate);
    }

    // Should be called only when all the pieces are set on the board. - board is static state.
    public void setPieceData() {
        this.legalMoves = this.piece.generateLegalMovesForPiece();
        this.legalDestinations = getLegalDestinationsFromMoves();
    }

    public void unsetPiece() {
        this.piece = null;
        this.occupied = false;
        this.legalMoves.clear();
        this.legalDestinations.clear();
    }

    public Cordinate getCordinate() {
        return this.cordinate;
    }

    // TODO(rishipal): Move this to TilePanel class
    public int getTileIDFromCell() {
        return this.cordinate.getTileIDFromCordinate(board.SIZE_BOARD);
    }

    //TODO(rishipal): Move this inside Piece
    public ArrayList<Move> getLegalMoves() {
        if(this.occupied && this.legalMoves.isEmpty()) {
            this.legalMoves = this.piece.generateLegalMovesForPiece();
            this.legalDestinations = getLegalDestinationsFromMoves();
            return this.legalMoves;
        }
        return this.legalMoves;
    }

    public Set<Cell> getLegalDestinations() {
        return this.legalDestinations;
    }

    private Set<Cell> getLegalDestinationsFromMoves() {
        Set<Cell> dests = new HashSet<>();
        for(Move m : legalMoves) {
            dests.add(m.destination);
        }
        return dests;
    }
}