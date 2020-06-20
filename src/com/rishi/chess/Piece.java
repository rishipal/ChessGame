package com.rishi.chess;
import java.util.*;

import com.rishi.chess.utils.Utils.Direction;

public abstract class Piece {
    protected Cordinate cordinate;
    protected ChessBoard board;
    public Cell cell;
    private Player player;
    protected Integer killScore = 0;

    protected enum PieceColor {
        WHITE,
        BLACK
    }

    public enum PieceType {
        PAWN,
        KNIGHT,
        BISHOP,
        KING,
        QUEEN
    }

    protected PieceColor pieceColor;
    protected PieceType pieceType;
    protected Direction direction; // TODO(rishipal): rename this to player direction
    public String pieceIconPath = "art/";



    public Integer getKillScore() {
        return this.killScore;
    }

    // TODO(rishipal): Do this in the same way we assignPlayers - from Game class
    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        // TODO: Fix the direction here. Human must face up and computer must face down.
        this.direction = this.pieceColor == PieceColor.BLACK? Direction.UP : Direction.DOWN;
        // TODO: Try to give user the choice of color
    }

    public void killPiece() {
        this.player.getRemainingPieces().remove(this);
        this.cell.piece = null;
    }

    public void setPlayer(Player p) {
        this.player = p;
    }

    public void setNewCordinates(Cordinate c) {
        this.cordinate = c;
    }

    public Cordinate getCordinate() {
        return this.cordinate;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    final protected Set<Cell> getLegalDestinations(Direction d) {
        Cordinate currCord = this.cordinate;
        Set<Cell> dests = new LinkedHashSet<>();
        Cordinate nextCordinate = currCord.getNextCordinate(d);
        while(nextCordinate.isWithinBounds(this.board.SIZE_BOARD)) {
            Cell nextCell = board.getCellFromCordinate(nextCordinate);
            if(nextCell.occupied) {
                if(nextCell.piece.pieceColor != this.pieceColor) {
                    dests.add(nextCell);
                }
                break;
            } else {
                dests.add(nextCell);
            }
            nextCordinate = nextCordinate.getNextCordinate(d);
        }
        return dests;
    }

    final public boolean isSamePlayer(Piece p) {
        if(this.pieceColor == p.pieceColor) {
            assert(this.direction == p.direction);
            return true;
        }
        return false;
    }

    final protected ArrayList<Cell> generateStraightLinePath(Move m) {
        assert m.isStraightLineMove() : "Non-straight move calculated for" + m.piece.toString();
        Cell source = m.source;
        Cell dest = m.destination;
        Direction d = m.source.getCordinate().getDirection(m.destination.getCordinate());
        Cordinate next = source.getCordinate().getNextCordinate(d);
        ArrayList<Cell> path = new ArrayList<>();
        path.add(source);
        while(next.isWithinBounds(board.SIZE_BOARD) && !next.isEqual(dest.getCordinate())) {
            path.add(board.getCellFromCordinate(next));
            next = next.getNextCordinate(d);
        }
        path.add(dest);
        return path;
    }

    //final ArrayList<Cell> protected getCanBeKilledDests() {

    //}

    final public Cell getEnclosingCell() {
        return board.getCellFromCordinate(cordinate);
    }

    abstract public ArrayList<Move> generateLegalMovesForPiece();

    abstract protected ArrayList<Cell> generatePathForLegalMove(Move m);
}

