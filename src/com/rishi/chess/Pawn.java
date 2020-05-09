package com.rishi.chess;
import java.util.ArrayList;

public class Pawn extends Piece {
    Pawn(Cordinate cord, PieceColor pieceColor, ChessBoard cb) {
        super(pieceColor);
        this.board = cb;
        this.cordinate = cord;
        this.pieceIconPath = this.pieceIconPath + (this.pieceColor == PieceColor.BLACK? "BP.gif" : "WP.gif");
        this.pieceType = PieceType.PAWN;
        this.killScore = 10;
    }

    @Override
    public ArrayList<Cell> generatePathForLegalMove(Move m) {
        return generateStraightLinePath(m);
    }

    @Override
    public ArrayList<Move> generateLegalMovesForPiece() {
        ArrayList<Cell> legalDestinations = new ArrayList<>();
        ArrayList<Move> legalMoves = new ArrayList<>();
        if(pieceDirection == PieceDirection.UP) {
            if(this.cordinate.row == 0) {
                return legalMoves;
            }
            Cell firstCellAboveThis = board.getChessBoard()[this.cordinate.row - 1][this.cordinate.col];
            if(!firstCellAboveThis.occupied) {
                legalDestinations.add(firstCellAboveThis);
                if(this.cordinate.row == 6) {
                    Cell secondCellAboveThis = board.getChessBoard()[this.cordinate.row - 2][this.cordinate.col];
                    if(!secondCellAboveThis.occupied) {
                        legalDestinations.add(secondCellAboveThis);
                    }
                }
            }
            Cordinate topLeft = new Cordinate(this.cordinate.row -1, this.cordinate.col -1);
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
            if(this.cordinate.row == 7) {
                return legalMoves;
            }
            Cell firstCellBelowThis = board.getChessBoard()[this.cordinate.row + 1][this.cordinate.col];
            if(!firstCellBelowThis.occupied) {
                legalDestinations.add(firstCellBelowThis);
                if(this.cordinate.row == 1) {
                    Cell secondCellBelowThis = board.getChessBoard()[this.cordinate.row + 2][this.cordinate.col];
                    if(!secondCellBelowThis.occupied) {
                        legalDestinations.add(secondCellBelowThis);
                    }
                }
            }
            Cordinate downLeft = new Cordinate(this.cordinate.row +1, this.cordinate.col -1);
            if(downLeft.isWithinBounds(board.SIZE_BOARD)) {
                Cell downLeftCell = board.getCellFromCordinate(downLeft);
                if(downLeftCell.occupied && downLeftCell.piece.pieceColor != this.pieceColor) {
                    legalDestinations.add(downLeftCell);
                }
            }
            Cordinate downRight = new Cordinate(this.cordinate.row +1, this.cordinate.col + 1);
            if(downRight.isWithinBounds(board.SIZE_BOARD)) {
                Cell downRightCell = board.getCellFromCordinate(downRight);
                if(downRightCell.occupied && downRightCell.piece.pieceColor != this.pieceColor) {
                    legalDestinations.add(downRightCell);
                }
            }
        }
        for (Cell c : legalDestinations) {
            Move m = new Move(this, c);
            ArrayList<Cell> path = this.generatePathForLegalMove(m);
            m.setPath(path);
            legalMoves.add(m);
        }
        return legalMoves;
    }
}