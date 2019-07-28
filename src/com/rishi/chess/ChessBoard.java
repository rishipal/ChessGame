package com.rishi.chess;

import java.awt.*;


public class ChessBoard {
    public final int SIZE_BOARD = 9;
    private final Cell[][] board;

    public ChessBoard() {
        this.board = new Cell[SIZE_BOARD][SIZE_BOARD];
        createInitialBoard();
    }

    public Piece getPiece(int tileId) {
        int row = tileId/SIZE_BOARD;
        int col = tileId % SIZE_BOARD;
        return board[row][col].piece;
    }

    private void createInitialBoard() {
        createAllCells();
        fillPawns();
    }

    private void createAllCells() {
        for(int i = 0; i < this.SIZE_BOARD; i++) {
            for(int j = 0; j < this.SIZE_BOARD; j++) {
                this.board[i][j] = new Cell(i, j);
            }
        }
    }

    private void fillPawns() {
            for(int j = 0; j < this.SIZE_BOARD; j++) {
                this.board[1][j].setPiece(new Pawn(1,j, Piece.PieceColor.WHITE));
                this.board[7][j].setPiece(new Pawn(7,j, Piece.PieceColor.BLACK));
            }
    }

    public void printBoard() {
        for(int i = 0; i < SIZE_BOARD; i++) {
            for(int j = 0; j < SIZE_BOARD; j++) {
                System.out.print(board[i][j].occupied);
            }
        }
    }
}