package com.rishi.chess;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Rook extends Piece {

    public Rook(Cordinate cord, PieceColor c, ChessBoard cb) {
        super(c);
        this.cordinate = cord;
        this.board = cb;
        this.pieceColor = c;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BR.gif" : "WR.gif");
    }

    private Set<Cell> getLegalDestinationsLeft() {
        Cordinate currCord = this.cordinate;
        Set<Cell> dests = new LinkedHashSet<>();

        Cordinate cordinateLeft = currCord.getCordinateLeft();
        while(cordinateLeft.isWithinBounds(this.board.SIZE_BOARD)) {
            Cell cellLeft = board.getCellFromCordinate(cordinateLeft);
            if(cellLeft.occupied) {
                break;
            } else {
                dests.add(cellLeft);
            }
            cordinateLeft = cordinateLeft.getCordinateLeft();
        }
        return dests;
    }

    private Set<Cell> getLegalDestinationsRight() {
        Cordinate currCord = this.cordinate;
        Set<Cell> dests = new LinkedHashSet<>();

        Cordinate cordinateRight = currCord.getCordinateRight();
        while(cordinateRight.isWithinBounds(this.board.SIZE_BOARD)) {
            Cell cellRight = board.getCellFromCordinate(cordinateRight);
            if(cellRight.occupied) {
                break;
            } else {
                dests.add(cellRight);
            }
            cordinateRight = cordinateRight.getCordinateRight();
        }
        return dests;
    }

    private Set<Cell> getLegalDestinationsAbove() {
        Cordinate currCord = this.cordinate;
        Set<Cell> dests = new LinkedHashSet<>();

        Cordinate cordAbove = currCord.getCordinateAbove();
        while(cordAbove.isWithinBounds(this.board.SIZE_BOARD)) {
            Cell cellAbove = board.getCellFromCordinate(cordAbove);
            if(cellAbove.occupied) {
                break;
            } else {
                dests.add(cellAbove);
            }
            cordAbove = cordAbove.getCordinateAbove();
        }
        return dests;
    }

    private Set<Cell> getLegalDestinationsBelow() {
        Cordinate currCord = this.cordinate;
        Set<Cell> dests = new LinkedHashSet<>();

        Cordinate cordBelow = currCord.getCordinateBelow();
        while(cordBelow.isWithinBounds(this.board.SIZE_BOARD)) {
            Cell cellBelow = board.getCellFromCordinate(cordBelow);
            if(cellBelow.occupied) {
                break;
            } else {
                dests.add(cellBelow);
            }
            cordBelow = cordBelow.getCordinateBelow();
        }
        return dests;
    }

    /**
     * Collects legal destinations above and below this cell for Rook piece
     * @return
     */
    private Set<Cell> getLegalVerticalDestinations() {
        Set<Cell> destsAbove = getLegalDestinationsAbove();
        Set<Cell> destsBelow = getLegalDestinationsBelow();
        destsAbove.addAll(destsBelow);
        return destsAbove;
    }

    private Set<Cell> getLegalHorizontalDestinations() {
        Set<Cell> destsLeft = getLegalDestinationsLeft();
        Set<Cell> destsRight = getLegalDestinationsRight();
        destsLeft.addAll(destsRight);
        return destsLeft;
    }

    /**
     * Collects legal destinations vertical and horizontal for this Rook piece
     * @return
     */
    private Set<Cell> getLegalDestinations() {
        Set<Cell> verticalDests = getLegalVerticalDestinations();
        Set<Cell> horizonralDests = getLegalHorizontalDestinations();
        verticalDests.addAll(horizonralDests);
        return verticalDests;
    }

    private ArrayList<Cell> generatePathForRookMove(Move move) {
        ArrayList<Cell> path = new ArrayList<>();
        Cell destination = move.destination;
        path.add(move.source);
        int currCol = move.source.getCordinate().col;
        int currRow =  move.source.getCordinate().row;
        if(this.cordinate.isSameColumn(destination.getCordinate())) {
            if(this.cordinate.row < destination.getCordinate().row) {
                while(currRow < destination.getCordinate().row) {
                    path.add(board.getChessBoard()[currRow][currCol]);
                    currRow++;
                }
            } else {
                while(currRow > destination.getCordinate().row) {
                    path.add(board.getChessBoard()[currRow][currCol]);
                    currRow--;
                }
            }
        } else if(this.cordinate.isSameRow(destination.getCordinate())) {
            if(this.cordinate.col <= destination.getCordinate().col) {
                while(currCol < destination.getCordinate().col) {
                    path.add(board.getChessBoard()[currRow][currCol]);
                    currCol++;
                }
            } else {
                while(currCol > destination.getCordinate().col) {
                    path.add(board.getChessBoard()[currRow][currCol]);
                    currCol--;
                }
            }
        } else {
            System.out.print("Rook move calculated incorrectly - destination is niether vertical nor horizontal");
        }
        path.add(destination);
        return path;
    }

    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
        System.out.print("Coming inside Rook generateLegalMovesForPiece");
        Set<Cell> destinations = getLegalDestinations();
        ArrayList<Move> legalMoves = new ArrayList<>();
        for(Cell dest : destinations) {
            Move m = new Move(this, dest);
            ArrayList<Cell> path = this.generatePathForRookMove(m);
            m.setPath(path);
            legalMoves.add(m);
        }
        return legalMoves;
    }

    public boolean isMoveLegal(int end_x, int end_y) {
        return true;
    }

}