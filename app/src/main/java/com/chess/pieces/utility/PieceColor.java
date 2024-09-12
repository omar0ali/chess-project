package com.chess.pieces.utility;

public enum PieceColor {
    Black("B"),
    White("W");

    private final String displayName;

    PieceColor(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return displayName;
    }
}