package com.rishi.chess;

import java.util.ArrayList;
import java.util.Iterator;

public class Knight extends Piece {
    public Knight(int x, int y, PieceColor c, ChessBoard cb) {
        this.board = cb;
        this.x = x;
        this.y = y;
        this.pieceColor = c;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BN.gif" : "WN.gif");
    }

    public boolean isMoveLegal(int end_x, int end_y) {
        return true;
    }

    /**
     *  This function generates all the destinatins that are spatially (not semantically) possible for the Knight
     *  to move from its current position. From the current position (x,y), the Knight can move to the following places:
     *     x+2, y+1
     *     x+2, y-1
     *     x-2, y+1
     *     x-2, y-1
     *     x+1, y+2
     *     x+1, y-2
     *     x-1, y+2
     *     x-1, y-2
     *
     * However, some of these positions might also be out of bounds of the board (if the Knight is near the edges).
     * This function does not return those out of bounds destinations
     **/
    private ArrayList<Cordinate> getAllSpatiallyPossibleDestinations() {
        ArrayList<Cordinate> allDests = new ArrayList<>();
        final int one = 1, two = 2;
        allDests.add(new Cordinate(this.x + two, this.y + one));
        allDests.add(new Cordinate(this.x + two, this.y - one));
        allDests.add(new Cordinate(this.x - two, this.y + one));
        allDests.add(new Cordinate(this.x - two, this.y - one));
        allDests.add(new Cordinate(this.x + one, this.y + two));
        allDests.add(new Cordinate(this.x - one, this.y + two));
        allDests.add(new Cordinate(this.x - one, this.y - two));
        allDests.add(new Cordinate(this.x + one, this.y - two));


        Iterator itr = allDests.iterator();
        while (itr.hasNext())
        {
            Cordinate c = (Cordinate)itr.next();
            if(c != null && !c.isWithinBounds(this.board.SIZE_BOARD)) {
                itr.remove();
            }
        }
        return allDests;
    }

    /**
     * Knight's moves are independent from the direction on the player.
     * This function first collects all spatially possible destinations,
     * Out of those, legal destinations are the ones which are unoccupied by the current player
     * @return legal moves that can be done from the current position of a Knight piece
     */
    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
        ArrayList<Cell> legalDestinations = new ArrayList<>();

        ArrayList<Cordinate> destinationCordinates = getAllSpatiallyPossibleDestinations();
        for(Cordinate c : destinationCordinates) {
            Cell cell = board.getCellFromCordinate(c);
            if(!cell.occupied) {
                legalDestinations.add(cell);
            } else {
                if(cell.piece.pieceColor != this.pieceColor) {
                    legalDestinations.add(cell);
                }
            }
        }

        ArrayList<Move> legalMoves = new ArrayList<>();
        for(Cell c : legalDestinations) {
            Move m = new Move(this, c);
            m.generatePathsForThisMove();
            legalMoves.add(m);
        }
        return legalMoves;
    }
}