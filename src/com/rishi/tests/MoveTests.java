package com.rishi.tests;

import com.rishi.chess.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO(rishipal): Add tests for killing move (reduces player count, etc)
class MoveTests {
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void testSimpleMove() {
        Cell source = game.chessBoard.getCellFromCordinate(new Cordinate(1,1));
        Piece pieceBeforeMove = source.piece;

        Move m = source.getLegalMoves().get(0);
        Cell dest = m.destination;
        m.execute();

        validatePostMoveState(source, dest, pieceBeforeMove);
    }

    // TODO(rishipal): This should report a warning and be a no-op
    @Test
    void testSameSourceAndDest() {
        Cell source = game.chessBoard.getCellFromCordinate(new Cordinate(1,1));
        Cell dest = game.chessBoard.getCellFromCordinate(new Cordinate(1,1));
        Piece pieceBeforeMove = source.piece;
        try{
            Move m = new Move(source.piece, dest);
            m.execute();
            throw new IllegalStateException();
        } catch (UnsupportedOperationException e) {
           assert(e.getMessage().contains("Performing an invalid move"));
        }

    }

    @Test
    void testValidKnightMove() {
        Cell source = game.chessBoard.getCellFromCordinate(new Cordinate(0,1));
        Piece pieceBeforeMove = source.piece;

        assert (pieceBeforeMove.getPieceType() == Piece.PieceType.KNIGHT);

        Move m = source.getLegalMoves().get(0);
        Cell dest = m.destination;
        m.execute();

        validatePostMoveState(source, dest, pieceBeforeMove);

    }

    private void validatePostMoveState(Cell source, Cell dest, Piece piece) {
        assert(source.piece == null);
        assert(dest.piece != null);
        assert(dest.piece == piece); // same piece, no new memory
        assert(dest.piece.getEnclosingCell() == dest);
        assert(dest.piece.getCordinate() == dest.getCordinate());
        assert(dest.piece.getCordinate() != source.getCordinate());
    }
}