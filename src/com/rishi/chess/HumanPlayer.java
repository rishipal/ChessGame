package com.rishi.chess;

class HumanPlayer extends Player {
    HumanPlayer(Piece.PieceColor color) {
        this.pieceColor = color;
    }

    /** Simply make the move from source to destination */
    public final void makeMove(MoveManager moveManager, Cell source, Cell destination) {
        Move m = new Move(source.piece, destination);
        if(!m.isMoveLegal()) {
            System.out.print("Illegal Move");
        }
        Piece pieceMoved = source.piece;
        source.unsetPiece();
        destination.setPiece(pieceMoved);
        moveManager.storeMove(m);
    }
}