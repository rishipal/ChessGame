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

    @Override
    final protected ArrayList<Cell> generatePathForLegalMove(Move move) {
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
            ArrayList<Cell> path = this.generatePathForLegalMove(m);
            m.setPath(path);
            //System.out.println(" and path is " + m.getPathAsString());

            legalMoves.add(m);
        }
        return legalMoves;
    }
}