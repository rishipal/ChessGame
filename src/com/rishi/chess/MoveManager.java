package com.rishi.chess;

import java.util.ArrayList;

public class MoveManager {

    private final ChessBoard chessBoard; // can not re-instantiate a board even if a bad move requested
    private ArrayList<Move> movesHistory;
    MoveManager(ChessBoard board) {
        this.movesHistory = new ArrayList<>();
        this.chessBoard = board;
    }

    private boolean isValidMove(Cell source, Cell destination) {
        // TODO(rishipal): Complete this
        return true;
    }

    public void makeMove(Cell source, Cell destination) {
        // TODO(rishipal): Complete this
        if(!isValidMove(source, destination)) {
            return;
        }
        //saveMove(move);
    }

    void saveMove(Move move) {
        movesHistory.add(move);
    }

    ArrayList<Move> getLastNMoves(int n) {
        ArrayList<Move> result = new ArrayList<>();
        int reverseIndex = 0;
        while(reverseIndex < n) {
            result.add(movesHistory.get(movesHistory.size() - reverseIndex));
            reverseIndex++;
        }
        return result;
    }
}