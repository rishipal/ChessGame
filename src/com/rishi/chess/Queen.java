package com.rishi.chess;

import java.lang.reflect.Array;
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
    }

    private Set<Cell> getSpatiallyPossibleDests() {
        List<PieceDirection> directionList = this.pieceDirection.getAllDirectionsAsList();
        Set<Cell> legalDests = new LinkedHashSet<>();
        for(PieceDirection direction : directionList) {
            legalDests.addAll(getLegalDestinations(direction));
        }
        return legalDests;
    }

    // TODO(rishipal): Make this a common function inside Move or Piece - getStraightLinePath()
    //  as this functionality can be shared by all pieces except Knight.
    private ArrayList<Cell> generatePathForQueenMove(Move move, PieceDirection d) {
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

    @Override
    final protected ArrayList<Cell> generatePathForLegalMove(Move m) {
        ArrayList<Cell> path = new ArrayList<>();
        Piece.PieceDirection d = m.source.getCordinate().getDirection(m.destination.getCordinate());
        path = generatePathForQueenMove(m, d);
        return path;
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
