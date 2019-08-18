package com.rishi.chess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;

public class MoveManager {

    ArrayList<Move> movesHistory;

    MoveManager() {
        movesHistory = new ArrayList<>();
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