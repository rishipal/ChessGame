package com.rishi.chess;

import java.util.Random;

class Cell {
    public boolean occupied = false;
    public Piece piece = null;
    public Cell() {}
    public Cell(Piece p) {
        occupied = true;
        piece = p;
    }
}

public class ChessBoard {
    private final int SIZE_BOARD = 9;
    private final Cell[][] board;

    public ChessBoard() {
        board = new Cell[SIZE_BOARD][SIZE_BOARD];
        createAndPopulateRandomBoard();
    }

    void createAndPopulateRandomBoard() {
        for(int i = 0; i < SIZE_BOARD; i++) {
            for(int j = 0; j < SIZE_BOARD; j++) {
                board[i][j] = new Cell();
            }
        }
        Random random = new Random(23);
        for(int i = 0; i < 36; i++) {
            int randomRow = random.nextInt(9);
            int randomColumn = random.nextInt(9);

            //TODO: Randomize the type of piece that this cell should hold
            Piece piece = new Piece();
            Cell cell = new Cell(piece);
            board[randomRow][randomColumn] = cell;
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