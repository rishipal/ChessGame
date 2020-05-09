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

    protected void calculateRemainingPieces() {
        this.remainingPieces = new LinkedHashSet<>();
        for (Cell[] row : chessBoard.getChessBoard()) {
            for(Cell cell : row) {
                if( cell.occupied && cell.piece.pieceColor == this.pieceColor) {
                    cell.piece.setPlayer(this);
                    this.remainingPieces.add(cell.piece);
                }
            }
        }
        System.out.println("finished calculateRemainingPieces");
    }

    public Set<Piece> getRemainingPieces() {
        if (this.remainingPieces == null) {
            calculateRemainingPieces();
        }
        return this.remainingPieces;
    }

    public boolean isPlayerDead() {
        return remainingPieces.isEmpty() || isKingDead();
    }

    private boolean isKingDead() {
        for(Piece p : remainingPieces) {
            if (p.getPieceType() == Piece.PieceType.KING) {
                return false;
            }
        }
        return true;
    }
}