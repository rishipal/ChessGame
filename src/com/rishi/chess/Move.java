package com.rishi.chess;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Move {

    Cell source;
    public Cell destination;
    Piece piece;
    public ArrayList<Cell> path;
    // ATTN:
    LocalDateTime moveTime;


    Move(Piece p, Cell dest) {
        piece = p;
        source = p.getEnclosingCell();
        destination = dest;
        path = new ArrayList<>();
        moveTime = LocalDateTime.now();
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

    boolean isMoveLegal() {
        return piece.isMoveLegal(destination.getCordinate());
    }
}