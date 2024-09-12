package com.chess.pieces;

import java.util.List;

import com.chess.ChessBoard;
import com.chess.Square;
import com.chess.pieces.utility.MoveOptions;
import com.chess.pieces.utility.MoveUtility;
import com.chess.pieces.utility.Piece;
import com.chess.pieces.utility.PieceColor;

public class King extends Piece {

    public King(PieceColor color) {
        super(color);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public List<MoveUtility> whiteTurnPiece(ChessBoard chessBoard) {
        Square topSquare = getTopSquares(chessBoard, 1).get(0);
        Square bottomSquare = getBottomSquares(chessBoard, 1).get(0);
        Square leftSquare = getLeftSquares(chessBoard, 1).get(0);
        Square rightSquare = getRightSquares(chessBoard, 1).get(0);
        // Side Movements
        Square topLeftSquare = getTopLeftSquares(chessBoard, 1).get(0);
        Square topRightSquare = getTopRightSquares(chessBoard, 1).get(0);
        Square bottomLeftSquare = getBottomLeftSquares(chessBoard, 1).get(0);
        Square bottomRightSquare = getBottomRightSquares(chessBoard, 1).get(0);

        Square squares[] = { topSquare, bottomSquare, leftSquare, rightSquare, topLeftSquare, topRightSquare,
                bottomLeftSquare, bottomRightSquare };

        // Check if possible to move in a position.
        // Sometimes its not possible to move outside the board. Read Pawn.java
        // comments.
        for (Square square : squares) {
            if (square.getPiece() == this) {
                this.getMoves().add(new MoveUtility(square, MoveOptions.NotApplicable));
            }
            if (square.getPiece() == null) {
                this.getMoves().add(new MoveUtility(square, MoveOptions.Move));
            }
            // Check if not possible to move and either to kill.
            if (square.getPiece() != null && square.getPiece().getColor() != this.getColor()) {
                this.getMoves().add(new MoveUtility(square, MoveOptions.Kill));
            } else if (square.getPiece() != null && square.getPiece().getColor() == this.getColor()) {
                this.getMoves().add(new MoveUtility(square, MoveOptions.NotApplicable));
            }
        }
        return this.getMoves();
    }

    @Override
    public List<MoveUtility> blackTurnPiece(ChessBoard chessBoard) {
        // Both players using the KING Piece will have the same movements.
        return whiteTurnPiece(chessBoard);
    }

}
