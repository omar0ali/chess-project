package com.chess;

import com.chess.pieces.utility.Piece;

public class ChessBoard {
    private Square[][] board;

    public ChessBoard() {
        board = new Square[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = new Square(x, y);
            }
        }
    }

    public Square getSquare(int x, int y) {
        int maxX = Math.min(Math.max(x, 0), board.length - 1);
        int maxY = Math.min(Math.max(y, 0), board[0].length - 1);
        return board[maxX][maxY];
    }

    public void movePiece(Piece piece, Point newPos) throws Exception {
        // Check new position if there is any pawn there.
        Piece currentPiece = board[newPos.getX()][newPos.getY()].getPiece();
        if (currentPiece != null) {
            throw new Exception(
                    "We can't move this piece (" + currentPiece.getIDAsString()
                            + ") to this location, because its not empty.");
        }
        removePiece(piece.getCurrentPosition().getX(), piece.getCurrentPosition().getY());
        board[newPos.getX()][newPos.getY()].setPiece(piece);
        piece.setCurrentPiece(newPos);
    }

    public void placePiece(Piece piece, int x, int y) throws Exception {
        if (piece.getCurrentPosition() != null) {
            throw new Exception("Piece is already in the board.");
        }
        board[x][y].setPiece(piece);
        piece.setCurrentPiece(new Point(x, y));
    }

    public void removePiece(int x, int y) {
        Square currentSquare = board[x][y];
        Piece currentPiece = currentSquare.getPiece();
        currentPiece.setCurrentPiece(null);
        currentSquare.setPiece(null);
    }

    public void printBoard() {
        for (int y = 0; y <= 7; y++) {
            for (int x = 0; x < 8; x++) {
                System.out.print(board[x][y] + "");
            }
            System.out.println();
        }
        System.out.println();
    }

}
