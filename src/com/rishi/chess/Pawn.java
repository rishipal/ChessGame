package com.rishi.chess;

class Pawn extends Piece {
    Pawn(int x, int y, PieceColor pieceColor) {
        this.x = x;
        this.y = y;
        this.pieceColor = pieceColor;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BP.gif" : "WP.gif");
        this.pieceDirection = this.pieceColor == PieceColor.BLACK? PieceDirection.UP : PieceDirection.DOWN;
    }


    boolean isMoveSpatiallyLegal(int dest_x, int dest_y, ChessBoard board) {
        if (!board.isPieceLocationWithinBounds(dest_x, dest_y)) {
            return false;
        }

        //no move
        if (dest_x == this.x) {
            return false;
        }

        // digonal movement restricted to neighboaring cols
        if(Math.abs(dest_y - this.y) > 1) {
            return false;
        }
        return true;
    }

    boolean isMoveDirectionallyLegal(int dest_x, int dest_y, ChessBoard board) {
        //moves in only the facing direction
        if((this.pieceDirection == PieceDirection.UP && dest_x <= this.x) ||
                this.pieceDirection == PieceDirection.DOWN && dest_x >= this.x) {
            return false;
        }
        return true;

    }

    boolean isLegalConsideringVerticalObstructions(int dest_x, int dest_y, ChessBoard board) {
        boolean twoStepJumpAllowed = false;
        if((this.x == 1 && this.pieceDirection == PieceDirection.DOWN) ||
                (this.x == 7 && this.pieceDirection == PieceDirection.UP)) {
            twoStepJumpAllowed = true;
        }

        if(twoStepJumpAllowed){
            //vertical path to destination does not have an occupied cell
            int smallerRow = dest_x > this.x? this.x : dest_x;
            if(board.getChessBoard()[smallerRow+1][dest_y].occupied) {
                return false;
            }
        }
        return true;

    }

    boolean isKillingEnemyDiagonally(int dest_x, int dest_y, ChessBoard board) {
        if(dest_y == this.y) {
            return false;
        }

        //For diagonal movement, the destination cell must be occupied
        if(!board.getChessBoard()[dest_x][dest_y].occupied) {
            return false;
        }
        PieceColor expectedColorOfOpponent = this.pieceColor == PieceColor.BLACK? PieceColor.WHITE: PieceColor.BLACK;
        PieceColor destinationCellPieceColor = board.getChessBoard()[dest_x][dest_y].piece.pieceColor;

        if(destinationCellPieceColor != expectedColorOfOpponent) {
            return false;
        }
        return true;

    }

    boolean isMoveLegal(int dest_x, int dest_y, ChessBoard board) {
        if(isMoveSpatiallyLegal(dest_x, dest_y, board) &&
                isMoveDirectionallyLegal(dest_x, dest_y, board) &&
                isLegalConsideringVerticalObstructions(dest_x, dest_y, board) &&
                isKillingEnemyDiagonally(dest_x, dest_y, board)) {
            return true;
        }
        return false;

    }
}