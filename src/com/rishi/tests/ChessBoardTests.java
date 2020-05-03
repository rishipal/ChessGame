package com.rishi.tests;

import com.rishi.chess.ChessBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessBoardTests {
    ChessBoard board;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
    }

    @Test
    public void testInitialSetup() {
        for(int j = 0; j < board.SIZE_BOARD; j++) {
            assert(this.board.getChessBoard()[1][j].occupied);
            assert(this.board.getChessBoard()[0][j].occupied);

            assert(this.board.getChessBoard()[7][j].occupied);
            assert(this.board.getChessBoard()[6][j].occupied);
        }
    }

}