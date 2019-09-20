package com.rishi.chess;

import java.util.ArrayList;

public class Move {

    Cell source;
    Cell destination;
    Piece piece;
    public ArrayList<Cell> path;


    Move(Piece p, Cell dest) {
        piece = p;
        source = p.getEnclosingCell();
        destination = dest;
        path = new ArrayList<>();
    }



    boolean isMoveLegal() {
        return piece.isMoveLegal(destination.getCordinate());
    }

    /**
     * This function is supposed to return a list of all Cells that form the path from source to destination for this
     * move.
     * Currently, this function only returns the source and destination cells and path.
     * This function needs to move to the respective piece classes to implement
     */
    void generatePathsForThisMove() {
        path.add(source);
        path.add(destination);

    }


}