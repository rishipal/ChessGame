package com.rishi.chess;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(int x, int y, PieceColor c) {
        this.x = x;
        this.y = y;
        this.pieceColor = c;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BN.gif" : "WN.gif");
    }

    public boolean isMoveLegal(int end_x, int end_y) {
        return true;
    }

    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
        ArrayList<Cell> legalDestinations = new ArrayList<>();

        // Knight's moves are independent from the direction on the player.
        // Collect all spatially possible destinations,
        // Out of those, legal destinations are the ones which are unoccupied by the current player


        ArrayList<Move> legalMoves = new ArrayList<>();
        return legalMoves;
    }


}