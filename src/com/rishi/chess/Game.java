package com.rishi.chess;

public class Game {
    public ChessBoard chessBoard;
    private MoveManager moveManager;
    private Player player1;
    private Player player2;

     public Game() {
        chessBoard = new ChessBoard();
        moveManager = new MoveManager(chessBoard);
        player1 = new Player();
        player2 = new Player();
    }

    public void makeMove(Cell source, Cell destination) {
        moveManager.makeMove(source, destination);
    }

}
