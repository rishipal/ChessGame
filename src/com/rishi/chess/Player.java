package com.rishi.chess;

import com.rishi.chess.engine.Engine;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class Player {
    private String name;
    protected ChessBoard chessBoard;
    Piece.PieceColor pieceColor;
    List<Move> moveList;
    private Set<Piece> remainingPieces;

    Player() {}
    public abstract void makeAMove(Engine engine, MoveManager moveManager, Cell source, Cell destination);

    protected void calculateRemainingPieces(Player p) {
        p.remainingPieces = new LinkedHashSet<>();
        for (Cell[] row : chessBoard.getChessBoard()) {
            for(Cell cell : row) {
                if( cell.occupied && cell.piece.pieceColor == p.pieceColor) {
                    cell.piece.setPlayer(p);
                    p.remainingPieces.add(cell.piece);
                }
            }
        }
        System.out.println("finished calculateRemainingPieces");
    }

    public Set<Piece> getRemainingPieces() {
        if (this.remainingPieces == null) {
            calculateRemainingPieces(this);
        }
        return this.remainingPieces;
    }
}