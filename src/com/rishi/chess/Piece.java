package com.rishi.chess;
import java.util.*;

public class Piece {
    protected Cordinate cordinate;
    protected ChessBoard board;
    public Cell cell;
    //TODO(rishipal): Add a player here?

    protected enum PieceColor {
        WHITE,
        BLACK
    }

    protected PieceColor pieceColor;
    protected PieceDirection pieceDirection; // TODO(rishipal): rename this to player direction

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

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.pieceDirection = this.pieceColor == PieceColor.BLACK? PieceDirection.UP : PieceDirection.DOWN;

    }
    public String pieceIconPath = "art/";

    public void setNewCordinates(Cordinate c) {
        this.cordinate = c;
    }

    protected Set<Cell> getLegalDestinations(PieceDirection d) {
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

    public boolean isSamePlayer(Piece p) {
        if(this.pieceColor == p.pieceColor) {
            assert(this.pieceDirection == p.pieceDirection);
            return true;
        }
        return false;
    }

    // do nothing, will be overridden.
    public boolean isMoveLegal(Cordinate dest) {
        System.out.println("This should not be called");
        return true;
    }

    public ArrayList<Move> generateLegalMovesForPiece() {
        // do nothing, will be overridden.
        System.out.println("Should not come here in generateLegalMovesForPiece");
        return new ArrayList<>();
    }

    protected ArrayList<Cell> generatePathForLegalMove(Move m) {
        // do nothing, will be overridden.
        System.out.println("Should not come here in generatePathsForLegalMoves");
        return new ArrayList<>();
    }

    public Cell getEnclosingCell() {
        return board.getCellFromCordinate(cordinate);
    }
}

