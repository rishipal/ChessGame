package com.rishi.chess;

import java.util.Stack;

public class MoveManager {

    private final ChessBoard chessBoard; // can not re-instantiate a board even if a bad move requested
    private Stack<Move> movesHistory;

    MoveManager(ChessBoard board) {
        this.movesHistory = new Stack<>();
        this.chessBoard = board;
    }

    public void resetMoveManager() {
        this.movesHistory.clear();
    }

    void storeMove(Move move) {
        movesHistory.push(move);;
    }

    void printLastNMoves(int n) {
        if(n < 0) {
            return;
        }
        Move m = movesHistory.pop();
        m.printMove();
        printLastNMoves(n-1);
        movesHistory.push(m);
    }
}