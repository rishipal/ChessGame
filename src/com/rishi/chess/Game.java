package com.rishi.chess;

public class Game {
    public final ChessBoard chessBoard;
    private final MoveManager moveManager;

    private final Player human;
    private final Player computer;

    private Player activePlayer;

     public Game() {
        chessBoard = new ChessBoard();
        moveManager = new MoveManager(chessBoard);
        human = new Player(Piece.PieceColor.WHITE);
        computer = new ComputerPlayer(Piece.PieceColor.BLACK);
        activePlayer = human; // human player makes the first move
    }

    public void makeMove(Cell source, Cell destination) {
         moveManager.makeMove(source, destination);
         toggleActivePlayer();
    }

    public Player getActivePlayer() {
         return this.activePlayer;
    }

    private void toggleActivePlayer() {
         activePlayer = activePlayer == human? computer : human;
    }
}
