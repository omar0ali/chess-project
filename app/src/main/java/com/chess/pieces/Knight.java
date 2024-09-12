package com.chess.pieces;

import java.util.*;

import com.chess.ChessBoard;
import com.chess.Square;
import com.chess.pieces.utility.MoveOptions;
import com.chess.pieces.utility.MoveUtility;
import com.chess.pieces.utility.Piece;
import com.chess.pieces.utility.PieceColor;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super(color);
    }

    // TODO Refactor if possible.
    public List<Square> knightListOfMoves(ChessBoard chessBoard) {
        // Check location.
        List<Square> listOfSquares = new ArrayList<>();
        Square topRight = chessBoard.getSquare(this.getCurrentPosition().getX() + 1, getCurrentPosition().getY() + 2);
        if (this.getCurrentPosition().getX() + 1 == topRight.getCoordinates().getX()
                && this.getCurrentPosition().getY() + 2 == topRight.getCoordinates().getY()) {
            listOfSquares.add(topRight);
        }
        Square topLeft = chessBoard.getSquare(this.getCurrentPosition().getX() - 1, getCurrentPosition().getY() + 2);
        if (this.getCurrentPosition().getX() - 1 == topLeft.getCoordinates().getX()
                && this.getCurrentPosition().getY() + 2 == topLeft.getCoordinates().getY()) {
            listOfSquares.add(topLeft);
        }
        Square bottomRight = chessBoard.getSquare(this.getCurrentPosition().getX() + 1,
                getCurrentPosition().getY() - 2);
        if (this.getCurrentPosition().getX() + 1 == bottomRight.getCoordinates().getX()
                && this.getCurrentPosition().getY() - 2 == bottomRight.getCoordinates().getY()) {
            listOfSquares.add(bottomRight);
        }
        Square bottomLeft = chessBoard.getSquare(this.getCurrentPosition().getX() - 1, getCurrentPosition().getY() - 2);
        if (this.getCurrentPosition().getX() - 1 == bottomLeft.getCoordinates().getX()
                && this.getCurrentPosition().getY() - 2 == bottomLeft.getCoordinates().getY()) {
            listOfSquares.add(bottomLeft);
        }
        return listOfSquares;
    }

    @Override
    protected List<MoveUtility> whiteTurnPiece(ChessBoard chessBoard) {
        List<Square> listOfMoves = knightListOfMoves(chessBoard);
        System.out.println(listOfMoves);
        for (Square square : listOfMoves) {
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
        return getMoves();
    }

    @Override
    protected List<MoveUtility> blackTurnPiece(ChessBoard chessBoard) {
        return whiteTurnPiece(chessBoard);
    }

    @Override
    public String toString() {
        return "K";
    }

}
