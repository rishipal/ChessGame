package com.rishi.tests;

import com.rishi.chess.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessBoardTests {
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game(Game.Mode.RANDOM);
    }

    @Test
    public void testInitialSetup() {
        for(int j = 0; j < this.game.chessBoard.SIZE_BOARD; j++) {
            assert(this.game.chessBoard.getChessBoard()[1][j].occupied);
            assert(this.game.chessBoard.getChessBoard()[0][j].occupied);

            assert(this.game.chessBoard.getChessBoard()[7][j].occupied);
            assert(this.game.chessBoard.getChessBoard()[6][j].occupied);
        }
    }
}