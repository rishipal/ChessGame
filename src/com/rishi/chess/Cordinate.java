package com.rishi.chess;

import java.util.ArrayList;

public class Cordinate {
    public final int row;
    public final int col;

    Cordinate(int i, int j) {
        row = i;
        col = j;
    }

    public boolean isSameColumn(Cordinate another) {
        if(this.col == another.col) {
            return true;
        }
        return false;
    }

    public boolean isSameRow(Cordinate another) {
        if(this.row == another.row) {
            return true;
        }
        return false;
    }

    public boolean isDiagonal(Cordinate another) {
        int rowDiff = Math.abs(this.row - another.row);
        int colDiff = Math.abs(this.col - another.col);
        if(rowDiff == colDiff) {
            return true;
        }
        return false;
    }

    // Number of diagonal steps is same as rowDiff or colDiff when diagonally moving
    public int getDiagonalDistance(Cordinate another) {
        return (Math.abs(this.row - another.row));
    }

    // TODO(rishipal); Move this to TIlePanel class
    public int getTileIDFromCordinate( int boardSize) {
        return this.row * boardSize + this.col;
    }

    public Cordinate getCordinateAbove() {
        return new Cordinate(this.row-1, this.col);
    }

    public Cordinate getCordinateLeft() {
        return new Cordinate(this.row, this.col-1);
    }

    public Cordinate getCordinateRight() {
        return new Cordinate(this.row, this.col+1);
    }

    public Cordinate getCordinateBelow() {
        return new Cordinate(this.row+1, this.col);
    }

    private Cordinate getCordinateNorthEast() {
        return new Cordinate(this.row-1, this.col+1);
    }

    private Cordinate getCordinateNorthWest() {
        return new Cordinate(this.row-1, this.col-1);
    }

    private Cordinate getCordinateSouthEast() {
        return new Cordinate(this.row+1, this.col+1);
    }

    private Cordinate getCordinateSouthWest() {
        return new Cordinate(this.row+1, this.col -1);
    }

    public Cordinate getNextCordinate(Piece.PieceDirection d) {
        switch(d) {
            case UP: return getCordinateAbove();
            case DOWN: return getCordinateBelow();
            case LEFT: return getCordinateLeft();
            case RIGHT: return getCordinateRight();
            case NORTHEAST: return getCordinateNorthEast();
            case NORTHWEST: return getCordinateNorthWest();
            case SOUTHEAST: return getCordinateSouthEast();
            case SOUTHWEST: return getCordinateSouthWest();
        }
        return null;
    }

    private ArrayList<Cordinate> getUncheckedNeighboringCords() {
        ArrayList<Cordinate> cords = new ArrayList<>();
        Cordinate left = this.getCordinateLeft();
        Cordinate right = this.getCordinateRight();
        Cordinate top = this.getCordinateAbove();
        Cordinate bottom = this.getCordinateBelow();
        Cordinate northwest = this.getNextCordinate(Piece.PieceDirection.NORTHWEST);
        Cordinate northeast = this.getNextCordinate(Piece.PieceDirection.NORTHEAST);
        Cordinate southwest = this.getNextCordinate(Piece.PieceDirection.SOUTHWEST);
        Cordinate southeast = this.getNextCordinate(Piece.PieceDirection.SOUTHEAST);

        cords.add(left);
        cords.add(right);
        cords.add(top);
        cords.add(bottom);
        cords.add(northwest);
        cords.add(northeast);
        cords.add(southwest);
        cords.add(southeast);
        return cords;
    }

    public ArrayList<Cordinate> getAllValidNeighboringCords(int boardSize) {
        ArrayList<Cordinate> uncheckedCords = getUncheckedNeighboringCords();
        ArrayList<Cordinate> validCords = new ArrayList<>();

        for(Cordinate c : uncheckedCords) {
            if(c.isWithinBounds(boardSize)) {
                validCords.add(c);
            }
        }
        return validCords;
    }

    public Piece.PieceDirection getDirection(Cordinate destination) {
        if(this.isSameColumn(destination)) {
            if(this.row < destination.row) {
                return Piece.PieceDirection.DOWN;
            } else {
                return Piece.PieceDirection.UP;
            }
        } else if (this.isSameRow(destination)) {
            if(this.col < destination.col) {
                return Piece.PieceDirection.RIGHT;
            } else {
                return Piece.PieceDirection.LEFT;
            }
        } else if(this.isDiagonal(destination)) {
            if(this.row > destination.row) {
                if(this.col < destination.col) {
                    return Piece.PieceDirection.NORTHEAST;
                } else {
                    return Piece.PieceDirection.NORTHWEST;
                }
            } else {
                    if(this.col < destination.col) {
                        return Piece.PieceDirection.SOUTHEAST;
                    } else {
                        return Piece.PieceDirection.SOUTHWEST;
                }
            }
        }
        throw new RuntimeException();
    }

    public boolean isEqual(Cordinate another) {
        return (this.row == another.row) && (this.col == another.col);
    }

    public boolean isWithinBounds( int boardSize) {
        if (this.row >= 0 && this.col >= 0 && this.row < boardSize && this.col < boardSize) {
            return true;
        }
        return false;
    }
}