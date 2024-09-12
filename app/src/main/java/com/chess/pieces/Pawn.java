package com.chess.pieces;

import java.util.List;

import com.chess.ChessBoard;
import com.chess.Square;
import com.chess.pieces.utility.MoveOptions;
import com.chess.pieces.utility.MoveUtility;
import com.chess.pieces.utility.Piece;
import com.chess.pieces.utility.PieceColor;

public class Pawn extends Piece {

    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    protected List<MoveUtility> whiteTurnPiece(ChessBoard chessBoard) {
        Square bottomSquare, bottomLeftSquare, bottomRightSquare;
        bottomSquare = this.getBottomSquares(chessBoard, 1).get(0);
        bottomLeftSquare = this.getBottomLeftSquares(chessBoard, 1).get(0);
        bottomRightSquare = this.getBottomRightSquares(chessBoard, 1).get(0);
        if (bottomSquare.getPiece() == this) {
            getMoves().add(new MoveUtility(bottomSquare, MoveOptions.NotApplicable));
        }
        if (bottomLeftSquare.getPiece() == this) {
            getMoves().add(new MoveUtility(bottomLeftSquare, MoveOptions.NotApplicable));
        }
        if (bottomRightSquare.getPiece() == this) {
            getMoves().add(new MoveUtility(bottomRightSquare, MoveOptions.NotApplicable));
        }
        // Check for a possibility to move to these positions.
        // Only if this current piece is at the same position to move to.
        // Since there is a bound and can't move outside the board, solution,
        // -> Make sure it does not return negtative value.
        if (bottomSquare.getPiece() == null) {
            getMoves().add(new MoveUtility(bottomSquare, MoveOptions.Move));
        }
        /*
         * To destroy a piece using a pawn (Destroying the opposite color.)
         * Top-Left, Top-Right
         * Bottom-Left, Bottom-Right
         * Based on color.
         */

        if (bottomLeftSquare.getPiece() != null && bottomLeftSquare.getPiece().getColor() != this.getColor()) {
            if (bottomLeftSquare != bottomSquare) {
                getMoves().add(new MoveUtility(bottomLeftSquare, MoveOptions.Kill));
            }
        }
        if (bottomRightSquare.getPiece() != null && bottomRightSquare.getPiece().getColor() != this.getColor()) {
            if (bottomRightSquare != bottomSquare) {
                getMoves().add(new MoveUtility(bottomRightSquare, MoveOptions.Kill));
            }
        }
        return getMoves();
    }

    @Override
    protected List<MoveUtility> blackTurnPiece(ChessBoard chessBoard) {
        Square topSquare, topLeftSquare, topRightSquare;
        if (this.getColor() == PieceColor.Black) {
            topSquare = this.getTopSquares(chessBoard, 1).get(0);
            topLeftSquare = this.getTopLeftSquares(chessBoard, 1).get(0);
            topRightSquare = this.getTopRightSquares(chessBoard, 1).get(0);
            // Check if possible to move in a position
            // Prevent to move outside the board. I made sure that when ever encounters
            // negative numbers, it will return the preivous number.
            if (topSquare.getPiece() == this) {
                getMoves().add(new MoveUtility(topSquare, MoveOptions.NotApplicable));
            }
            if (topLeftSquare.getPiece() == this) {
                getMoves().add(new MoveUtility(topLeftSquare, MoveOptions.NotApplicable));
            }
            if (topRightSquare.getPiece() == this) {
                getMoves().add(new MoveUtility(topRightSquare, MoveOptions.NotApplicable));
            }
            // Check for a possibility to move to these positions.
            // Only if this current piece is at the same position to move to.
            // Since there is a bound and can't move outside the board, solution,
            // -> Make sure it does not return negtative value.
            if (topSquare.getPiece() == null) {
                getMoves().add(new MoveUtility(topSquare, MoveOptions.Move));
            }
            /*
             * To destroy a piece using a pawn (Destroying the opposite color.)
             * Top-Left, Top-Right
             * Bottom-Left, Bottom-Right
             * Based on color.
             */

            if (topLeftSquare.getPiece() != null && topLeftSquare.getPiece().getColor() != this.getColor()) {
                // This solve a problem where sometimes when a piece is at (0,1) it trys to
                // attack a piece at (0,0) which is infront of it. NOTE: A pawn cannot do this
                // kind of attack.
                if (topLeftSquare != topSquare) {
                    getMoves().add(new MoveUtility(topLeftSquare, MoveOptions.Kill));
                }
            }
            if (topRightSquare.getPiece() != null && topRightSquare.getPiece().getColor() != this.getColor()) {
                if (topRightSquare != topSquare) {
                    getMoves().add(new MoveUtility(topRightSquare, MoveOptions.Kill));
                }
            }
        }
        return getMoves();
    }
}
