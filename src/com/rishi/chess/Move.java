package com.rishi.chess;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Move {

    // Make all these fields private and guard against mutation
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

    public void printMove() {
        String result = new String();
        result = "Source:[" + this.source.getCordinate().row + "," + this.source.getCordinate().col + "] -->";
        result += "Destination:[" + this.destination.getCordinate().row + "," + this.destination.getCordinate().col + "]\n";
        System.out.print(result);
    }

    boolean isMoveLegal() {
        return piece.isMoveLegal(destination.getCordinate());
    }
}