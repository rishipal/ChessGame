package com.rishi.chess;

public class Game {
    public final ChessBoard chessBoard;
    private final MoveManager moveManager;
    private final Player player1;
    private final Player player2;
    private Player activePlayer;

     public Game() {
        chessBoard = new ChessBoard();
        moveManager = new MoveManager(chessBoard);
        player1 = new Player(Piece.PieceColor.WHITE );
        player2 = new Player(Piece.PieceColor.BLACK);
        activePlayer = player1;
    }

    public void makeMove(Cell source, Cell destination) {
         moveManager.makeMove(source, destination);
         toggleActivePlayer();
    }

    public Player getActivePlayer() {
         return this.activePlayer;
    }

    private void toggleActivePlayer() {
         activePlayer = activePlayer == player1? player2 : player1;
    }

}
