package com.rishi.chess.engine;

import com.rishi.chess.*;
import com.rishi.chess.utils.*;
import com.rishi.common.Node;

import java.util.*;

public class Engine {
    // This is a set containing all possible moves for each piece belonging to the player.
    // TODO: Make the engine use trees of depth > 1
    private Map<Piece, Set<Move>> movesSet;
    private Map<Piece, Node<Move>> movesTree;
    private Game.Mode mode;  //can be changed by the user on the fly

    public Engine(ChessBoard board, Game.Mode mode) {
        this.mode = mode;

    }


    public Move getComputerPlayerMove(Player computer) {
        this.calculateMovesSet(computer);
        this.calculateMovesTree(computer);
        Utils.print("Calculated Computer moves tree");
        Move m = this.pickNextMove();
        return m;
    }

    public void switchMode(Game.Mode mode) {
        this.mode = mode;
    }

    public void calculateMovesTree(Player computer) {
        movesTree = new LinkedHashMap<>();
        for(Piece piece : computer.getRemainingPieces()) {
            ArrayList<Move> legalMoves = piece.generateLegalMovesForPiece();
            if( legalMoves == null) {
                continue;
            }
            for(Move m : legalMoves) {
                if(movesTree.containsKey(piece)) {
                    movesTree.get(piece).addChild(m);
                } else {
                    movesTree.put(piece, new Node(m));
                }
            }
        }
    }

    public void calculateMovesSet(Player computer) {
        movesSet = new LinkedHashMap<>();
        for(Piece piece : computer.getRemainingPieces()) {
            movesSet.put(piece, new HashSet<>());
            ArrayList<Move> legalMoves = piece.generateLegalMovesForPiece();
            if( legalMoves == null) {
                continue;
            }
            for(Move m : legalMoves) {
                movesSet.get(piece).add(m);
            }
        }
    }

    /**
     * Picks the next move for the player based on the game's Mode
     * @param mode the difficulty mode of the game
     * @return selected move
     */
    public Move pickNextMove() {
        if(mode == Game.Mode.RANDOM) {
            return pickRandomMove();
        } else if (mode == Game.Mode.EASY) {
            return pickEasyMove();
        } else if (mode == Game.Mode.MEDIUM) {
            return pickMediumMove();
        } else {
            return null;
        }
    }

    private Move pickRandomMove() {
        if(!movesSet.isEmpty()) {
            Iterator<Map.Entry<Piece, Set<Move>>> moveTreeItr = movesSet.entrySet().iterator();
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

    /**
     * Finds the best move based on the killScore of a piece.
     * @return
     */
    private Move pickEasyMove() {
        Move bestMove = null;
        Integer bestProfitSoFar = -1;
        if(!movesSet.isEmpty()) {
            Iterator<Map.Entry<Piece, Set<Move>>> moveTreeItr = movesSet.entrySet().iterator();
            while(moveTreeItr.hasNext()) {
                Map.Entry<Piece, Set<Move>> pieceMovesMapping = moveTreeItr.next();
                Piece piece = pieceMovesMapping.getKey();
                Set<Move> moves = pieceMovesMapping.getValue();
                for(Move move : moves) {
                    Piece dest = move.destination.piece;
                    Integer currProfit = 0;
                    if(dest == null) {
                        if(currProfit > bestProfitSoFar) {
                            bestMove = move;
                            bestProfitSoFar = currProfit;
                        }
                    } else {
                        currProfit = dest.getKillScore();
                        if(currProfit > bestProfitSoFar) {
                            bestMove = move;
                            bestProfitSoFar = currProfit;
                        }
                    }
                }
            }
        }
        return bestMove;
    }

    private Move pickMediumMove() {
        return pickRandomMove();

    }
}
