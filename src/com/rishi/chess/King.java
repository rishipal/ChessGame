package com.rishi.chess;
import java.util.ArrayList;

public class King extends Piece {
    public King(Cordinate cord, PieceColor c, ChessBoard cb) {
        super(c);
        this.board = cb;
        this.cordinate = cord;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BK.gif" : "WK.gif");
    }

    private ArrayList<Cell> getSpatiallyPossibleDests() {
        ArrayList<Cordinate> cords = this.cordinate.getAllValidNeighboringCords(board.SIZE_BOARD);
        ArrayList<Cell> legalDests = new ArrayList<>();
        for(Cordinate c : cords) {
            Cell cell = board.getCellFromCordinate(c);
            if(!cell.occupied || !cell.piece.isSamePlayer(this)) {
                legalDests.add(cell);
            }
        }
        return legalDests;
    }

    @Override
    final protected ArrayList<Cell> generatePathForLegalMove(Move m) {
        ArrayList<Cell> path = new ArrayList<>();
        path.add(this.cell);
        path.add(m.destination);
        return path;
    }

    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
        ArrayList<Cell> legalDests = getSpatiallyPossibleDests();
        ArrayList<Move> legalMoves = new ArrayList<>();

        for(Cell d : legalDests) {
            Move m = new Move(this, d);
            m.setPath(generatePathForLegalMove(m));
            legalMoves.add(m);
        }
        return legalMoves;
    }
}
