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

        int get() {
            return this.status;
        }

        void set(int s) {
            this.status = s;
        }
    }
    public static Status status;



    /** Mode of the engine represents the way the engine will choose the computer moves. */
    public enum Mode {
        RANDOM, // pick a legal move for the computer at random, no need to construct the MoveTree
        EASY, // pick a move, but prefer moves that capture any enemy piece when available, otherwise random
        MEDIUM, // capture enemy if possible, and also prefer capturing higher valued enemy
        HARD //
    }


    public Game(Mode mode) {
        chessBoard = new ChessBoard();
        moveManager = new MoveManager(chessBoard);
        human = new HumanPlayer(chessBoard, Piece.PieceColor.WHITE);
        computer = new ComputerPlayer(chessBoard, Piece.PieceColor.BLACK);
        chessBoard.assignPlayers(human, computer);
        activePlayer = human; // human player makes the first move
        engine = new Engine(chessBoard, Mode.RANDOM);
        status = Status.PLAYING;
    }

    public void switchMode(Mode m) {
        engine.switchMode(m);
    }

    public void makeHumanMove(Cell source, Cell destination) {
         assert (activePlayer == human);
         human.makeAMove(null, moveManager, source, destination);
       //  computer.calculateRemainingPieces(computer);
       // human.calculateRemainingPieces(human);
        chessBoard.resetMovesDataForEntireBoard();
        //chessBoard.setData();
         toggleActivePlayer();

         // Trigger for computer's move as soon as human move ends
        computer.makeAMove(engine, moveManager, null, null);
        // human.calculateRemainingPieces(human);
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

    private void toggleActivePlayer() {
         activePlayer = activePlayer == human? computer : human;
    }
}
