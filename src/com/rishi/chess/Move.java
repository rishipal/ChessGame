package com.rishi.chess;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Move {

    // Make all these fields private and guard against mutation
    Cell source;
    public Cell destination;
    Piece piece;
    public ArrayList<Cell> path;
    // TODO: Store and use the move's time
    LocalDateTime moveTime;

    public Move(Piece p, Cell dest) {
        piece = p;
        source = p.getEnclosingCell();
        destination = dest;
        path = new ArrayList<>();
        moveTime = LocalDateTime.now();
    }

    private boolean isMoveValid() {
        return source.getLegalDestinations().contains(destination);
        //return source.getLegalMoves().contains(this);
    }

    public boolean execute() {
        if(!isMoveValid()) {
            return false;
        }
        Piece pieceMoved = source.piece;
        source.unsetPiece();
        if(destination.occupied) {
            destination.piece.killPiece();
        }
        destination.setPiece(pieceMoved);
        return true;
    }

    public boolean isStraightLineMove() {
        Cordinate src = source.getCordinate();
        Cordinate dest = destination.getCordinate();
        return src.isSameRow(dest) || src.isSameColumn(dest) || src.isDiagonal(dest);
    }

    void setPath(ArrayList<Cell> p) {
        this.path = p;
    }

    public String getPathAsString() {
        String p = new String();
        for ( Cell c : path) {
            p = p + "[" + c.getCordinate().row + "," + c.getCordinate().col + "] --> " ;
        }
        return p;
    }

    public void printMove() {
        String result = new String();
        result = "Source:[" + this.source.getCordinate().row + "," + this.source.getCordinate().col + "] -->";
        result += "Destination:[" + this.destination.getCordinate().row + "," + this.destination.getCordinate().col + "]\n";
        System.out.print(result);
    }
}