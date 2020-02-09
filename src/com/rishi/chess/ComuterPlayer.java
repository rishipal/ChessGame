package com.rishi.chess;
import com.rishi.chess.engine.Engine;

class ComputerPlayer extends Player {
    private Engine engine;

    ComputerPlayer(Piece.PieceColor color) {
        this.pieceColor = color;
        this.engine = new Engine();
    }
}