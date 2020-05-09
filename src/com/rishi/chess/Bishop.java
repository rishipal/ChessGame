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
        this.pieceType = PieceType.BISHOP;
        this.killScore = 30;
    }

    final protected Set<Cell> getLegalDestinations() {
        Set<Cell> destsNE = getLegalDestinations(PieceDirection.NORTHEAST);
        Set<Cell> destsNW = getLegalDestinations(PieceDirection.NORTHWEST);
        Set<Cell> destsSE = getLegalDestinations(PieceDirection.SOUTHEAST);
        Set<Cell> destsSW = getLegalDestinations(PieceDirection.SOUTHWEST);
        destsNE.addAll(destsNW);
        destsNE.addAll(destsSE);
        destsNE.addAll(destsSW);
        return destsNE;
    }

    @Override
    final protected ArrayList<Cell> generatePathForLegalMove(Move move) {
        assert(move.destination.getCordinate().isDiagonal(move.source.getCordinate()));
        return generateStraightLinePath(move);
    }

    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
        System.out.print("Coming inside Bishop generateLegalMovesForPiece\n");
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
