package com.rishi.chess;

import java.util.ArrayList;

class Pawn extends Piece {


    Pawn(int x, int y, PieceColor pieceColor, ChessBoard cb) {
        this.board = cb;
        this.x = x;
        this.y = y;
        this.pieceColor = pieceColor;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BP.gif" : "WP.gif");
        this.pieceDirection = this.pieceColor == PieceColor.BLACK? PieceDirection.UP : PieceDirection.DOWN;
    }


    boolean isMoveSpatiallyLegal(Cordinate destination) {
        if (!destination.isWithinBounds(board.SIZE_BOARD)) {
            return false;
        }

        //no move
        if (destination.row == this.x) {
            return false;
        }

        // diagonal movement restricted to neighboring cols
        if(Math.abs(destination.col - this.y) > 1) {
            return false;
        }
        return true;
    }

    boolean isMoveDirectionallyLegal(Cordinate destination) {
        //moves in only the facing direction
        if((this.pieceDirection == PieceDirection.UP && destination.row <= this.x) ||
                this.pieceDirection == PieceDirection.DOWN && destination.row >= this.x) {
            return false;
        }
        return true;

    }

    boolean isLegalConsideringVerticalObstructions(Cordinate destination) {
        boolean twoStepJumpAllowed = false;
        if((this.x == 1 && this.pieceDirection == PieceDirection.DOWN) ||
                (this.x == 7 && this.pieceDirection == PieceDirection.UP)) {
            twoStepJumpAllowed = true;
        }

        if(twoStepJumpAllowed){
            //vertical path to destination does not have an occupied cell
            int smallerRow = destination.row > this.x? this.x : destination.col;
            if(board.getChessBoard()[smallerRow+1][destination.col].occupied) {
                return false;
            }
        }
        return true;

    }

    boolean isKillingEnemyDiagonally(Cordinate destination) {
        if(destination.col == this.y) {
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
        //ATTN: todo
    }

    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
        ArrayList<Cell> legalDestinations = new ArrayList<>();
        ArrayList<Move> legalMoves = new ArrayList<>();
        System.out.println("Should come here");
        if(pieceDirection == PieceDirection.UP) {
            if(this.x == 0) {
                return null;
            }
            Cell firstCellAboveThis = board.getChessBoard()[this.x - 1][this.y];
            if(isDestionationPossible(firstCellAboveThis)) {
                legalDestinations.add(firstCellAboveThis);
            }
            if(this.x == 7) {
                Cell secondCellAboveThis = board.getChessBoard()[this.x - 2][this.y];
                if(isDestionationPossible(secondCellAboveThis)) {
                    legalDestinations.add(secondCellAboveThis);
                }
            }
        } else {
            //ATTN: Handle DOWN direction later
            //if(this.y )
        }


        // ATTN: handle diagonal moves later
        for (Cell c : legalDestinations) {
            Move m = new Move(this, c);
            m.generatePathsForThisMove();
            legalMoves.add(m);

        }
        return legalMoves;
    }

}