package com.rishi.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import static java.lang.Math.abs;

public class Knight extends Piece {
    public Knight(Cordinate cord, PieceColor color, ChessBoard cb) {
        super(color);
        this.board = cb;
        this.cordinate = cord;
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
        allDests.add(new Cordinate(this.cordinate.row + two, this.cordinate.col + one));
        allDests.add(new Cordinate(this.cordinate.row + two, this.cordinate.col - one));
        allDests.add(new Cordinate(this.cordinate.row - two, this.cordinate.col + one));
        allDests.add(new Cordinate(this.cordinate.row - two, this.cordinate.col - one));
        allDests.add(new Cordinate(this.cordinate.row + one, this.cordinate.col + two));
        allDests.add(new Cordinate(this.cordinate.row - one, this.cordinate.col + two));
        allDests.add(new Cordinate(this.cordinate.row - one, this.cordinate.col - two));
        allDests.add(new Cordinate(this.cordinate.row + one, this.cordinate.col - two));


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
     * Generates the path for a Knight's move
     * @param m move
     * @return list of cells in the path for this move
     */
    private ArrayList<Cell> generatePathForKnightMove(Move m) {
        Cordinate destCord = m.destination.getCordinate();
        assert(abs(destCord.row - this.cordinate.row) == 1 || abs(destCord.col - this.cordinate.col) == 1 );

        ArrayList<Cell> result = new ArrayList<>();
        result.add(m.destination);

        if(abs(destCord.row - this.cordinate.row) == 1) {
            if(destCord.col > this.cordinate.col) {
                Cordinate c1 = new Cordinate(this.cordinate.row, this.cordinate.col + 1);
                Cordinate c2 = new Cordinate(this.cordinate.row, this.cordinate.col + 2);
                result.add(board.getCellFromCordinate(c2));
                result.add(board.getCellFromCordinate(c1));
            } else {
                Cordinate c1 = new Cordinate(this.cordinate.row, this.cordinate.col - 1);
                Cordinate c2 = new Cordinate(this.cordinate.row, this.cordinate.col - 2);
                result.add(board.getCellFromCordinate(c2));
                result.add(board.getCellFromCordinate(c1));
            }
        } else if(abs(destCord.col - this.cordinate.col) == 1) {
            if(destCord.row > this.cordinate.row) {
                Cordinate c1 = new Cordinate(this.cordinate.row + 1, this.cordinate.col);
                Cordinate c2 = new Cordinate(this.cordinate.row + 2, this.cordinate.col);
                result.add(board.getCellFromCordinate(c2));
                result.add(board.getCellFromCordinate(c1));
            } else {
                Cordinate c1 = new Cordinate(this.cordinate.row - 1, this.cordinate.col);
                Cordinate c2 = new Cordinate(this.cordinate.row - 2, this.cordinate.col);
                result.add(board.getCellFromCordinate(c2));
                result.add(board.getCellFromCordinate(c1));
            }
        }
        result.add(board.getCellFromCordinate(this.cordinate));
        Collections.reverse(result);
        return result;
    }

    public ArrayList<Cell> getLegalDestinations() {
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
        return legalDestinations;
    }

    /**
     * Knight's moves are independent from the direction on the player.
     * This function first collects all spatially possible destinations,
     * Out of those, legal destinations are the ones which are unoccupied by the current player
     * @return legal moves that can be done from the current position of a Knight piece
     */
    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
        ArrayList<Cell> legalDestinations = getLegalDestinations();

        ArrayList<Move> legalMoves = new ArrayList<>();
        for(Cell dest : legalDestinations) {
            Move m = new Move(this, dest);
            ArrayList<Cell> path = this.generatePathForKnightMove(m);
            m.setPath(path);
            legalMoves.add(m);
        }
        return legalMoves;
    }
}