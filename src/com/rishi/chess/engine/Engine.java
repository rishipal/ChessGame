package com.rishi.chess.engine;

import com.rishi.common.Tree;
import com.rishi.chess.ChessBoard;

public class Engine {
    private Mode mode;

    public Engine() {
        this.mode = Mode.RANDOM;
    }

    /** Mode of the engine represents the way the engine will choose the computer moves. */
    private enum Mode {
        RANDOM, // pick a legal move for the computer at random
        EASY, // pick a move, but prefer moves that capture any enemy piece when available, otherwise random
        MEDIUM, // capture enemy if possible, and also prefer capturing higher valued enemy
        HARD //
    }
}

