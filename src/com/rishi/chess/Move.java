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

    boolean isMoveLegal() {
        return piece.isMoveLegal(destination.getCordinate());
    }
}