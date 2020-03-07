package com.rishi.chess;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Rook extends Piece {
    public Rook(Cordinate cord, PieceColor c, ChessBoard cb) {
        super(c);
        this.cordinate = cord;
        this.board = cb;
        this.pieceColor = c;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BR.gif" : "WR.gif");
    }

    /**
     * Collects legal destinations vertical and horizontal for this Rook piece
     * @return
     */
    private Set<Cell> getLegalDestinations() {
        List<PieceDirection> directions = this.pieceDirection.get4WayDirectionsAsList();
        Set<Cell> legalDests = new LinkedHashSet<>();
        for(PieceDirection direction : directions) {
            legalDests.addAll(getLegalDestinations(direction));
        }
        return legalDests;
    }

    @Override
    final protected ArrayList<Cell> generatePathForLegalMove(Move move) {
        return generateStraightLinePath(move);
    }

    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
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