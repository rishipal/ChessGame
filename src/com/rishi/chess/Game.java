package com.rishi.chess;

import com.rishi.chess.engine.Engine;

public class Game {
    public final ChessBoard chessBoard;
    private final MoveManager moveManager;

    private final Player human;
    private final Player computer;
    private Engine engine;

    private Player activePlayer;

    public enum Status {
        PLAYING(0),
        OVER(1);

        private int status;
        Status(int i) {
            this.status = i;
        }
    }
    public static Status status;



    /** Mode of the engine represents the way the engine will choose the computer moves. */
    public enum Mode {
        RANDOM, // pick a legal move for the computer at random, no need to construct the MoveTree
        EASY,
        MEDIUM,
        HARD //
    }


    public Game(Mode mode) {
        chessBoard = new ChessBoard();
        moveManager = new MoveManager(chessBoard);
        engine = new Engine(chessBoard, mode);
        human = new HumanPlayer(chessBoard, Piece.PieceColor.WHITE);
        computer = new ComputerPlayer(chessBoard, Piece.PieceColor.BLACK, engine); // computer needs an engine
        chessBoard.assignPlayers(human, computer);
        activePlayer = human; // human player makes the first move

        status = Status.PLAYING;
    }

    public void switchMode(Mode m) {
        engine.switchMode(m);
    }

    public void makeActivePlayerMove(Cell source, Cell destination) {
        this.activePlayer.makeAMove(engine, moveManager, source, destination);
        chessBoard.resetMovesDataForEntireBoard();
        toggleActivePlayer();
        if(isGameOver()) {
            this.status = Status.OVER;
        }
    }

    private boolean isGameOver() {
        human.calculateRemainingPieces();
        computer.calculateRemainingPieces();
        return human.isPlayerDead() || computer.isPlayerDead();
    }

    public Player getActivePlayer() {
         return this.activePlayer;
    }

    public boolean isHumanActivePlayer() {
        return this.activePlayer == this.human;
    }

    private void toggleActivePlayer() {
         activePlayer = activePlayer == human? computer : human;
    }
}
