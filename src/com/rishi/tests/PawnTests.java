package com.rishi.tests;

import com.rishi.chess.ChessBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PawnTests {
    ChessBoard board;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
    }

    @Test
    public void testPawnPiecesMarkedOccupied() {
        for(int j = 0; j < board.SIZE_BOARD; j++) {
            assert(this.board.getChessBoard()[1][j].occupied);
            assert(this.board.getChessBoard()[7][j].occupied);
        }
    }

    @Test
    public void testPawnMoves() {

    }
}