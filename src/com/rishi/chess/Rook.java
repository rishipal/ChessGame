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

    private Set<Cell> getLegalDestinations(PieceDirection d) {
        Cordinate currCord = this.cordinate;
        Set<Cell> dests = new LinkedHashSet<>();
        Cordinate nextCordinate = currCord.getNextCordinate(d);
        while(nextCordinate.isWithinBounds(this.board.SIZE_BOARD)) {
            Cell nextCell = board.getCellFromCordinate(nextCordinate);
            if(nextCell.occupied) {
                if(nextCell.piece.pieceColor != this.pieceColor) {
                    dests.add(nextCell);
                }
                break;
            } else {
                dests.add(nextCell);
            }
            nextCordinate = nextCordinate.getNextCordinate(d);
        }
        return dests;
    }

    /**
     * Collects legal destinations vertical and horizontal for this Rook piece
     * @return
     */
    private Set<Cell> getLegalDestinations() {
        Set<Cell> destsUp = getLegalDestinations(PieceDirection.UP);
        Set<Cell> destsDown = getLegalDestinations(PieceDirection.DOWN);
        Set<Cell> destsLeft = getLegalDestinations(PieceDirection.LEFT);
        Set<Cell> destsRight = getLegalDestinations(PieceDirection.RIGHT);
        destsUp.addAll(destsDown);
        destsUp.addAll(destsLeft);
        destsUp.addAll(destsRight);
        return destsUp;
    }

    private ArrayList<Cell> generatePathForRookMove(Move move, PieceDirection d) {
        ArrayList<Cell> path = new ArrayList<>();
        Cell source = move.source;
        Cell destination = move.destination;
        path.add(source);
        Cordinate next = source.getCordinate().getNextCordinate(d);
        while(next.isWithinBounds(board.SIZE_BOARD) &&  next.isEqual(destination.getCordinate())) {
            Cell curr = board.getCellFromCordinate(next);
            path.add(curr);
            next = next.getNextCordinate(d);
        }
        path.add(destination);
        System.out.println("Direction is " + d);
        return path;
    }

    private ArrayList<Cell> generatePathForRookMove(Move move) {
        ArrayList<Cell> path;
        Cell source = move.source;
        Cell destination = move.destination;
        Piece.PieceDirection d = source.getCordinate().getDirection(destination.getCordinate());
        path = generatePathForRookMove(move, d);
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
            //System.out.println(" and path is " + m.getPathAsString());

            legalMoves.add(m);
        }
        return legalMoves;
    }
}