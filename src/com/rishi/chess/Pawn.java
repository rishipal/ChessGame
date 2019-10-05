package com.rishi.chess;
import java.util.ArrayList;

public class Pawn extends Piece {
    Pawn(Cordinate cord, PieceColor pieceColor, ChessBoard cb) {
        this.board = cb;
        this.cordinate = cord;
        this.pieceColor = pieceColor;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BP.gif" : "WP.gif");
        this.pieceDirection = this.pieceColor == PieceColor.BLACK? PieceDirection.UP : PieceDirection.DOWN;
    }


    boolean isMoveSpatiallyLegal(Cordinate destination) {
        if (!destination.isWithinBounds(board.SIZE_BOARD)) {
            return false;
        }

        //no move
        if (destination.row == this.cordinate.row) {
            return false;
        }

        // diagonal movement restricted to neighboring cols
        if(Math.abs(destination.col - this.cordinate.col) > 1) {
            return false;
        }
        return true;
    }

    boolean isMoveDirectionallyLegal(Cordinate destination) {
        //moves in only the facing direction
        if((this.pieceDirection == PieceDirection.UP && destination.row <= this.cordinate.row) ||
                this.pieceDirection == PieceDirection.DOWN && destination.row >= this.cordinate.row) {
            return false;
        }
        return true;

    }

    boolean isLegalConsideringVerticalObstructions(Cordinate destination) {
        boolean twoStepJumpAllowed = false;
        if((this.cordinate.row == 1 && this.pieceDirection == PieceDirection.DOWN) ||
                (this.cordinate.row == 6 && this.pieceDirection == PieceDirection.UP)) {
            twoStepJumpAllowed = true;
        }

        if(twoStepJumpAllowed){
            //vertical path to destination does not have an occupied cell
            int smallerRow = destination.row > this.cordinate.row? this.cordinate.row : destination.col;
            if(board.getChessBoard()[smallerRow+1][destination.col].occupied) {
                return false;
            }
        }
        return true;

    }

    boolean isKillingEnemyDiagonally(Cordinate destination) {
        if(destination.col == this.cordinate.col) {
            return false;
        }

        //For diagonal movement, the destination cell must be occupied
        if(!board.getChessBoard()[destination.row][destination.col].occupied) {
            return false;
        }
        PieceColor expectedColorOfOpponent = this.pieceColor == PieceColor.BLACK? PieceColor.WHITE: PieceColor.BLACK;
        PieceColor destinationCellPieceColor = board.getChessBoard()[destination.row][destination.col].piece.pieceColor;

        if(destinationCellPieceColor != expectedColorOfOpponent) {
            return false;
        }
        return true;

    }

    @Override
    public boolean isMoveLegal(Cordinate destination) {
        if(isMoveSpatiallyLegal(destination) &&
                isMoveDirectionallyLegal(destination) &&
                isLegalConsideringVerticalObstructions(destination) &&
                isKillingEnemyDiagonally(destination)) {
            return true;
        }
        return false;
    }

    private boolean isDestionationPossible(Cell cell) {
        return true;
        //AI: todo check for obstruction from source to destination
    }

    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
        ArrayList<Cell> legalDestinations = new ArrayList<>();
        System.out.println("Should come here");
        if(pieceDirection == PieceDirection.UP) {
            if(this.cordinate.row == 0) {
                return null;
            }
            Cell firstCellAboveThis = board.getChessBoard()[this.cordinate.row - 1][this.cordinate.col];
            if(isDestionationPossible(firstCellAboveThis)) {
                legalDestinations.add(firstCellAboveThis);
            }
            if(this.cordinate.row == 6) {
                Cell secondCellAboveThis = board.getChessBoard()[this.cordinate.row - 2][this.cordinate.col];
                if(isDestionationPossible(secondCellAboveThis)) {
                    legalDestinations.add(secondCellAboveThis);
                }
            }
            Cordinate topLeft = new Cordinate(this.cordinate.row -1, this.cordinate.row -1);
            if(topLeft.isWithinBounds(board.SIZE_BOARD)) {
                Cell topLeftCell = board.getCellFromCordinate(topLeft);
                if(topLeftCell.occupied && topLeftCell.piece.pieceColor != this.pieceColor) {
                    legalDestinations.add(topLeftCell);
                }
            }
            Cordinate topRight = new Cordinate(this.cordinate.row -1, this.cordinate.col + 1);
            if(topRight.isWithinBounds(board.SIZE_BOARD)) {
                Cell topRightCell = board.getCellFromCordinate(topRight);
                if(topRightCell.occupied && topRightCell.piece.pieceColor != this.pieceColor) {
                    legalDestinations.add(topRightCell);
                }
            }
        } else {
            if(this.cordinate.row == 6) {
                return null;
            }
            Cell firstCellBelowThis = board.getChessBoard()[this.cordinate.row + 1][this.cordinate.col];
            if(isDestionationPossible(firstCellBelowThis)) {
                legalDestinations.add(firstCellBelowThis);
            }
            if(this.cordinate.row == 1) {
                Cell secondCellAboveThis = board.getChessBoard()[this.cordinate.row + 2][this.cordinate.col];
                if(isDestionationPossible(secondCellAboveThis)) {
                    legalDestinations.add(secondCellAboveThis);
                }
            }
            Cordinate downLeft = new Cordinate(this.cordinate.row +1, this.cordinate.col -1);
            if(downLeft.isWithinBounds(board.SIZE_BOARD)) {
                Cell downLeftCell = board.getCellFromCordinate(downLeft);
                if(downLeftCell.occupied && downLeftCell.piece.pieceColor != this.pieceColor) {
                    legalDestinations.add(downLeftCell);
                }
            }
            Cordinate downRight = new Cordinate(this.cordinate.row -1, this.cordinate.col + 1);
            if(downRight.isWithinBounds(board.SIZE_BOARD)) {
                Cell downRightCell = board.getCellFromCordinate(downRight);
                if(downRightCell.occupied && downRightCell.piece.pieceColor != this.pieceColor) {
                    legalDestinations.add(downRightCell);
                }
            }
        }

        ArrayList<Move> legalMoves = new ArrayList<>();
        for (Cell c : legalDestinations) {
            Move m = new Move(this, c);
            m.path.add(m.source);
            m.path.add(m.destination);
            legalMoves.add(m);
        }
        return legalMoves;
    }
}