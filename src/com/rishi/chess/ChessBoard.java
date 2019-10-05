package com.rishi.chess;

public class ChessBoard {

    public final int SIZE_BOARD = 9;
    private final Cell[][] board;
    private final MoveManager moveManager;

    public Cell[][] getChessBoard() {
        return board;
    }

    public ChessBoard() {
        this.board = new Cell[SIZE_BOARD][SIZE_BOARD];
        moveManager = new MoveManager();
        createInitialBoard();
    }

    private void createInitialBoard() {
        setAllCells();
        fillPawns();
        fillRook();
        fillKnights();
        fillBishops();
    }

    private void setAllCells() {
        for(int i = 0; i < this.SIZE_BOARD; i++) {
            for(int j = 0; j < this.SIZE_BOARD; j++) {
                this.board[i][j] = new Cell(i,j, this);
            }
        }
    }

    private void fillBishops() {
        this.board[0][2].setPiece(new Bishop( new Cordinate(0, 2) , Piece.PieceColor.WHITE, this));
        this.board[0][6].setPiece(new Bishop(new Cordinate(0, 6) , Piece.PieceColor.WHITE, this));
        this.board[8][2].setPiece(new Bishop( new Cordinate(8, 2) , Piece.PieceColor.BLACK, this));
        this.board[8][6].setPiece(new Bishop(new Cordinate(8, 6 ), Piece.PieceColor.BLACK, this));
    }

    private void fillKnights() {
        this.board[0][1].setPiece(new Knight( new Cordinate(0, 1) , Piece.PieceColor.WHITE, this));
        this.board[0][7].setPiece(new Knight(new Cordinate(0, 7) , Piece.PieceColor.WHITE, this));
        this.board[8][1].setPiece(new Knight( new Cordinate(8, 1) , Piece.PieceColor.BLACK, this));
        this.board[8][7].setPiece(new Knight(new Cordinate(8, 7 ), Piece.PieceColor.BLACK, this));
    }

    private void fillRook() {
        this.board[0][0].setPiece(new Rook(new Cordinate(0, 0) , Piece.PieceColor.WHITE));
        this.board[0][8].setPiece(new Rook(new Cordinate(0, 8) , Piece.PieceColor.WHITE));
        this.board[8][8].setPiece(new Rook(new Cordinate(8, 8), Piece.PieceColor.BLACK));
        this.board[8][0].setPiece(new Rook(new Cordinate(8, 0) , Piece.PieceColor.BLACK));
    }

    private void fillPawns() {
        for(int j = 0; j < this.SIZE_BOARD; j++) {
            this.board[1][j].setPiece(new Pawn(new Cordinate(1,j), Piece.PieceColor.WHITE, this));
            this.board[7][j].setPiece(new Pawn(new Cordinate(7,j), Piece.PieceColor.BLACK, this));
        }
    }


    public Cell getCellFromCordinate(Cordinate c) {
        return this.board[c.row][c.col];
    }

    public Piece getPiece(int tileId) {
        int row = tileId/SIZE_BOARD;
        int col = tileId % SIZE_BOARD;
        return board[row][col].piece;
    }
}