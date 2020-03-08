package com.rishi.chess.engine;

import com.rishi.chess.Move;
import com.rishi.chess.Piece;
import com.rishi.chess.Player;
import com.rishi.common.Node;
import com.rishi.common.Tree;
import com.rishi.chess.ChessBoard;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Engine {
    private Mode mode;
    private ChessBoard chessBoard;

    // This is a set containing all possible moves for each piece belonging to the player.
    // TODO: Make the engine use trees of depth > 1
    private Set<Tree> movesTree;

    public Engine(ChessBoard board) {
        this.mode = Mode.RANDOM;
        this.chessBoard = board;
    }

    /** Mode of the engine represents the way the engine will choose the computer moves. */
    private enum Mode {
        RANDOM, // pick a legal move for the computer at random
        EASY, // pick a move, but prefer moves that capture any enemy piece when available, otherwise random
        MEDIUM, // capture enemy if possible, and also prefer capturing higher valued enemy
        HARD //
    }

    public Move getComputerPlayerMove(Player computer) {
        calculateMovesTree(computer);
        Move m = pickRandomMove();
        return m;
    }

    private void calculateMovesTree(Player computer) {
        movesTree = new LinkedHashSet<>();
        for(Piece piece : computer.getRemainingPieces()) {
            Tree tree = new Tree(piece);
            ArrayList<Move> legalMoves = piece.generateLegalMovesForPiece();
            if(!legalMoves.isEmpty()) {
                tree.addChildrenAfter(tree.getRoot(), legalMoves);
            }
            movesTree.add(tree);
        }
    }

    private Move pickRandomMove() {
        if(!movesTree.isEmpty()) {
            Tree<Move> t = movesTree.iterator().next();
            Node<Move> root = t.getRoot();
            List<Node<Move>> children = root.getChildren();
            Node<Move> randomMoveNode = children.get(children.size() -1 );
            return randomMoveNode.getData();
        }
        return null;
    }
}

