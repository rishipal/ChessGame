package com.rishi.chess;

public class ChessBoard {
    public final int SIZE_BOARD = 8;
    private final Cell[][] board;

    public Cell[][] getChessBoard() {
        return board;
    }

    public ChessBoard() {
        this.board = new Cell[SIZE_BOARD][SIZE_BOARD];
        initializeBoard();
    }

    public void updateAll() {
        for (Cell[] row : board) {
            for(Cell c : row) {
                c.resetData();
            }
        }
    }

    private void initializeBoard() {
        setAllCells();
        fillPawns();
        fillRook();
        fillKnights();
        fillBishops();
        fillQueens();
        fillKings();
        setData();
    }

    private void setData() {
        for(int i = 0; i < this.SIZE_BOARD; i++) {
            for(int j = 0; j < this.SIZE_BOARD; j++) {
                Cell c = this.board[i][j];
                if(c.occupied) {
                    c.setPieceData();
                }
            }
        }
    }

    private void setAllCells() {
        for(int i = 0; i < this.SIZE_BOARD; i++) {
            for(int j = 0; j < this.SIZE_BOARD; j++) {
                this.board[i][j] = new Cell(new Cordinate(i,j), this);
            }
        }
    }

    private void fillKings() {
        this.board[0][4].setPiece( new King( new Cordinate(0, 4) , Piece.PieceColor.WHITE, this));
        this.board[7][3].setPiece( new King( new Cordinate(7, 3) , Piece.PieceColor.BLACK, this));
    }

    private void fillQueens() {
        this.board[0][3].setPiece( new Queen( new Cordinate(0, 3) , Piece.PieceColor.WHITE, this));
        this.board[7][4].setPiece( new Queen( new Cordinate(7, 4) , Piece.PieceColor.BLACK, this));
    }

    private void fillBishops() {
        this.board[0][2].setPiece( new Bishop( new Cordinate(0, 2) , Piece.PieceColor.WHITE, this));
        this.board[0][5].setPiece( new Bishop( new Cordinate(0, 5) , Piece.PieceColor.WHITE, this));
        this.board[7][2].setPiece( new Bishop( new Cordinate(7, 2) , Piece.PieceColor.BLACK, this));
        this.board[7][5].setPiece( new Bishop( new Cordinate(7, 5) , Piece.PieceColor.BLACK, this));
    }

    private void fillKnights() {
        this.board[0][1].setPiece( new Knight( new Cordinate(0, 1) , Piece.PieceColor.WHITE, this));
        this.board[0][6].setPiece( new Knight( new Cordinate(0, 6) , Piece.PieceColor.WHITE, this));
        this.board[7][1].setPiece( new Knight( new Cordinate(7, 1) , Piece.PieceColor.BLACK, this));
        this.board[7][6].setPiece( new Knight( new Cordinate(7, 6) , Piece.PieceColor.BLACK, this));
    }

    private void fillRook() {
        this.board[0][0].setPiece( new Rook(new Cordinate(0, 0) , Piece.PieceColor.WHITE, this));
        this.board[0][7].setPiece( new Rook(new Cordinate(0, 7) , Piece.PieceColor.WHITE, this));
        this.board[7][7].setPiece( new Rook(new Cordinate(7, 7) , Piece.PieceColor.BLACK, this));
        this.board[7][0].setPiece( new Rook(new Cordinate(7, 0) , Piece.PieceColor.BLACK, this));
    }

    private void fillPawns() {
        for(int j = 0; j < this.SIZE_BOARD; j++) {
            this.board[1][j].setPiece( new Pawn( new Cordinate(1,j), Piece.PieceColor.WHITE, this));
            this.board[6][j].setPiece( new Pawn( new Cordinate(6,j), Piece.PieceColor.BLACK, this));
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