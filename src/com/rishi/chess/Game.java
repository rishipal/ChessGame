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
        human = new HumanPlayer(chessBoard, Piece.PieceColor.WHITE);
        computer = new ComputerPlayer(chessBoard, Piece.PieceColor.BLACK);
        activePlayer = human; // human player makes the first move
    }

    public void makeHumanMove(Cell source, Cell destination) {
         assert (activePlayer == human);
         human.makeMove(moveManager, source, destination);
         chessBoard.updateAll();
         toggleActivePlayer();

         // Trigger for computer's move as soon as human move ends
        computer.makeMove(moveManager, null, null);
        toggleActivePlayer();
    }

    public Player getActivePlayer() {
         return this.activePlayer;
    }

    private void toggleActivePlayer() {
         activePlayer = activePlayer == human? computer : human;
    }
}
