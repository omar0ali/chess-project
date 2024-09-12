package com.chess;

import com.chess.pieces.utility.Piece;

public class Square {
    private final Point coordinates;
    private Piece piece;

    public Square(int x, int y) {
        this.coordinates = new Point(x, y);
    }

    public Square(Piece piece, int x, int y) {
        this.coordinates = new Point(x, y);
        this.piece = piece;
    }

    public Square(Piece piece, Point point) {
        this.piece = piece;
        this.coordinates = point;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "[" + coordinates + " " + (piece != null ? piece.getPieceAndColor() : "\s\s\s\s") + "]";
    }
}
