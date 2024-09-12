package com.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.ChessBoard;
import com.chess.Square;
import com.chess.pieces.utility.MoveOptions;
import com.chess.pieces.utility.MoveUtility;
import com.chess.pieces.utility.Piece;
import com.chess.pieces.utility.PieceColor;

public class Bishop extends Piece {

    public Bishop(PieceColor color) {
        super(color);
    }

    @Override
    protected List<MoveUtility> whiteTurnPiece(ChessBoard chessBoard) {
        // Going Top to Bottom of Chess Board
        List<List<Square>> listOfListOfSquares = new ArrayList<>();
        List<Square> bottomLeftSquares = getBottomLeftSquares(chessBoard, 0);
        List<Square> bottomRightSquares = getBottomRightSquares(chessBoard, 0);
        listOfListOfSquares.add(bottomLeftSquares);
        listOfListOfSquares.add(bottomRightSquares);
        for (List<Square> listOflists : listOfListOfSquares) {
            for (Square square : listOflists) {
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
        }
        return getMoves();
    }

    @Override
    protected List<MoveUtility> blackTurnPiece(ChessBoard chessBoard) {
        // Going Bottom to Top of Chess Board
        List<List<Square>> listOfListOfSquares = new ArrayList<>();
        List<Square> topLeftSquares = getTopLeftSquares(chessBoard, 0);
        List<Square> topRightSquares = getTopRightSquares(chessBoard, 0);
        listOfListOfSquares.add(topLeftSquares);
        listOfListOfSquares.add(topRightSquares);
        for (List<Square> listOflists : listOfListOfSquares) {
            for (Square square : listOflists) {
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
        }
        return getMoves();
    }

    @Override
    public String toString() {
        return "B";
    }

}
