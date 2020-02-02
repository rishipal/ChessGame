package com.rishi.chess;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Bishop extends Piece {

    public Bishop(Cordinate cord, PieceColor c, ChessBoard cb) {
        super(c);
        this.board = cb;
        this.cordinate = cord;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BB.gif" : "WB.gif");
    }

    private Set<Cell> getLegalDestinations(Piece.PieceDirection d) {
        Set<Cell> dests = new LinkedHashSet<>();
        Cordinate next = this.cordinate.getNextCordinate(d);
        while(next.isWithinBounds(this.board.SIZE_BOARD)) {
            Cell currCell = board.getCellFromCordinate(next);
            if(currCell.occupied) {
                if(currCell.piece.pieceColor != this.pieceColor) {
                    dests.add(currCell);
                }
                break;
            } else {
                dests.add(currCell);
            }
            next = next.getNextCordinate(d);
        }
        return dests;
    }

    private Set<Cell> getLegalDestinations() {
        Set<Cell> destsNE = getLegalDestinations(PieceDirection.NORTHEAST);
        Set<Cell> destsNW = getLegalDestinations(PieceDirection.NORTHWEST);
        Set<Cell> destsSE = getLegalDestinations(PieceDirection.SOUTHEAST);
        Set<Cell> destsSW = getLegalDestinations(PieceDirection.SOUTHWEST);
        destsNE.addAll(destsNW);
        destsNE.addAll(destsSE);
        destsNE.addAll(destsSW);
        return destsNE;
    }

    private ArrayList<Cell> generatePathForBishopMove(Move move, Piece.PieceDirection d) {
        ArrayList<Cell> path = new ArrayList<>();
        Cell source = move.source;
        Cell destination = move.destination;
        path.add(source);
        Cordinate next = source.getCordinate().getNextCordinate(d);
        while(next.isWithinBounds(board.SIZE_BOARD) && next.isEqual(destination.getCordinate())) {
            Cell curr = board.getCellFromCordinate(next);
            path.add(curr);
            next = next.getNextCordinate(d);
        }
        path.add(destination);
        return path;
    }

    @Override
    final protected ArrayList<Cell> generatePathForLegalMove(Move move) {
        ArrayList<Cell> path = new ArrayList<>();
        Cell source = move.source;
        Cell destination = move.destination;
        assert(destination.getCordinate().isDiagonal(source.getCordinate()));
        Piece.PieceDirection d = source.getCordinate().getDirection(destination.getCordinate());
        path = generatePathForBishopMove(move, d );
        return path;
    }

    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
        System.out.print("Coming inside Bishop generateLegalMovesForPiece");
        Set<Cell> destinations = getLegalDestinations();
        ArrayList<Move> legalMoves = new ArrayList<>();
        for(Cell dest : destinations) {
            Move m = new Move(this, dest);
            ArrayList<Cell> path = this.generatePathForLegalMove(m);
            m.setPath(path);
            legalMoves.add(m);
        }
        return legalMoves;
    }
}
