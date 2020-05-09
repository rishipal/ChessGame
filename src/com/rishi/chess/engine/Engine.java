package com.rishi.chess.engine;

import com.rishi.chess.*;
import com.rishi.chess.utils.*;

import java.util.*;

public class Engine {
    // This is a set containing all possible moves for each piece belonging to the player.
    // TODO: Make the engine use trees of depth > 1
    private Map<Piece, Set<Move>> movesTree;

    public Engine(ChessBoard board) {
    }

    /** Mode of the engine represents the way the engine will choose the computer moves. */
    private enum Mode {
        RANDOM, // pick a legal move for the computer at random, no need to construct the MoveTree
        EASY, // pick a move, but prefer moves that capture any enemy piece when available, otherwise random
        MEDIUM, // capture enemy if possible, and also prefer capturing higher valued enemy
        HARD //
    }


    public Move getComputerPlayerMove(Player computer) {
        calculateMovesTree(computer);
        Utils.print("Calculated Computer moves tree");

        Move m = pickRandomMove();
        // Move m = pickEasyMove();
        Utils.print("Picked random computer move");
        //m.printMove();
        return m;
    }

    private void calculateMovesTree(Player computer) {
        movesTree = new LinkedHashMap<>();
        for(Piece piece : computer.getRemainingPieces()) {
            movesTree.put(piece, new HashSet<>());
            ArrayList<Move> legalMoves = piece.generateLegalMovesForPiece();
            if( legalMoves == null) {
                continue;
            }
            for(Move m : legalMoves) {
                movesTree.get(piece).add(m);
            }
        }
    }

    private Move pickRandomMove() {
        if(!movesTree.isEmpty()) {
            Iterator<Map.Entry<Piece, Set<Move>>> moveTreeItr = movesTree.entrySet().iterator();
            while(moveTreeItr.hasNext()) {
                Map.Entry<Piece, Set<Move>> pieceMovesMapping = moveTreeItr.next();
                Set<Move> moves = pieceMovesMapping.getValue();
                if(moves.isEmpty()) {
                    continue;
                }
                return moves.iterator().next();
            }
        }
        Utils.print("N more remaining moves !!! Game over!");
        //assert(false);
        Game.status = Game.Status.OVER;
        return null;
    }

    private Move pickEasyMove() {

        if(!movesTree.isEmpty()) {
            Iterator<Map.Entry<Piece, Set<Move>>> moveTreeItr = movesTree.entrySet().iterator();
            while(moveTreeItr.hasNext()) {

            }
        }
    }
}
