package com.rishi.chess;
import java.util.*;

public abstract class Piece {
    protected Cordinate cordinate;
    protected ChessBoard board;
    public Cell cell;
    private Player player;
    //TODO(rishipal): Add a player here?

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
    protected PieceDirection pieceDirection; // TODO(rishipal): rename this to player direction
    public String pieceIconPath = "art/";

    //TODO(rishipal): This should be defined elsewhere. Make it used by path calculator for all pieces.
    protected enum PieceDirection {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        NORTHEAST,
        NORTHWEST,
        SOUTHEAST,
        SOUTHWEST;

        public List<PieceDirection> getAllDirectionsAsList() {
            PieceDirection[] arr = this.values();
            return Arrays.asList(arr);
        }

        public List<PieceDirection> get4WayDirectionsAsList() {
            PieceDirection[] arr = new PieceDirection[]{UP, DOWN, LEFT, RIGHT};
            return Arrays.asList(arr);
        }
    }

    // TODO(rishipal): Do this in the same way we assignPlayers - from Game class
    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        // TODO: Fix the direction here. Human must face up and computer must face down.
        this.pieceDirection = this.pieceColor == PieceColor.BLACK? PieceDirection.UP : PieceDirection.DOWN;
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

    final protected Set<Cell> getLegalDestinations(PieceDirection d) {
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
            assert(this.pieceDirection == p.pieceDirection);
            return true;
        }
        return false;
    }

    final protected ArrayList<Cell> generateStraightLinePath(Move m) {
        assert m.isStraightLineMove() : "Non-straight move calculated for" + m.piece.toString();
        Cell source = m.source;
        Cell dest = m.destination;
        Piece.PieceDirection d = m.source.getCordinate().getDirection(m.destination.getCordinate());
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

    final public Cell getEnclosingCell() {
        return board.getCellFromCordinate(cordinate);
    }

    abstract public ArrayList<Move> generateLegalMovesForPiece();

    abstract protected ArrayList<Cell> generatePathForLegalMove(Move m);
}

