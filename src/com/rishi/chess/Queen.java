package com.rishi.chess;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Queen extends Piece {

    public Queen(Cordinate cord, PieceColor c, ChessBoard cb) {
        super(c);
        this.board = cb;
        this.cordinate = cord;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BQ.gif" : "WQ.gif");
        this.pieceType = PieceType.QUEEN;
    }

    private Set<Cell> getSpatiallyPossibleDests() {
        List<PieceDirection> directionList = this.pieceDirection.getAllDirectionsAsList();
        Set<Cell> legalDests = new LinkedHashSet<>();
        for(PieceDirection direction : directionList) {
            legalDests.addAll(getLegalDestinations(direction));
        }
        return legalDests;
    }

    @Override
    final protected ArrayList<Cell> generatePathForLegalMove(Move m) {
        return generateStraightLinePath(m);
    }

    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
        Set<Cell> legalDests = getSpatiallyPossibleDests();
        ArrayList<Move> legalMoves = new ArrayList<>();

        for(Cell d : legalDests) {
            Move m = new Move(this, d);
            m.setPath(generatePathForLegalMove(m));
            legalMoves.add(m);
        }
        return legalMoves;
    }
}
