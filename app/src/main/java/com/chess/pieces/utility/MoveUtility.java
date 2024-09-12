package com.chess.pieces.utility;

import com.chess.Point;
import com.chess.Square;

public class MoveUtility {
    private Square squareDetials = null;
    private MoveOptions availableOption = null;

    public MoveUtility(Square squareDetials, MoveOptions option) {
        this.squareDetials = squareDetials;
        this.availableOption = option;
    }

    public Point getSquareCoordinates() {
        return this.squareDetials.getCoordinates();
    }

    public Piece getSquarePiece() {
        return this.squareDetials.getPiece();
    }

    @Override
    public String toString() {
        return (this.getSquarePiece() != null)
                ? this.getSquarePiece().toString() + " : " + this.getSquareCoordinates() + " -> "
                        + this.availableOption.toString()
                        + "\n"
                : "Empty" + " : " + this.getSquareCoordinates() + " -> " + this.availableOption.toString()
                        + "\n";
    }
}
