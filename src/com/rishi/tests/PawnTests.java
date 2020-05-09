package com.rishi.tests;

import com.rishi.chess.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PawnTests {
    Game game;
    @BeforeEach
    void setUp() {
        game = new Game(Game.Mode.RANDOM);
    }

    @Test
    public void testPawnPiecesMarkedOccupied() {
        for(int j = 0; j < game.chessBoard.SIZE_BOARD; j++) {
            assert(this.game.chessBoard.getChessBoard()[1][j].occupied);
            assert(this.game.chessBoard.getChessBoard()[6][j].occupied);
        }
    }
}