package com.rishi.chess;

import java.util.ArrayList;

public class Move {

    Cell source;
    Cell destination;
    Piece piece;
    public ArrayList<Cell> path;


    Move(Piece p, Cell dest) {
        piece = p;
        destination = dest;
        path = new ArrayList<>();
    }



    boolean isMoveLegal() {
        return piece.isMoveLegal(destination.getCordinate());
    }

    void generatePathsForThisMove() {
        path.add(piece.cell);
        path.add(destination);

    }


}