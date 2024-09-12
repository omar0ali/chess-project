package com.chess.pieces;

import java.util.*;

import com.chess.ChessBoard;
import com.chess.Square;
import com.chess.pieces.utility.MoveOptions;
import com.chess.pieces.utility.MoveUtility;
import com.chess.pieces.utility.Piece;
import com.chess.pieces.utility.PieceColor;

public class Rook extends Piece {

    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    protected List<MoveUtility> whiteTurnPiece(ChessBoard chessBoard) {
        // Rock can move either vertically or horizontally
        List<List<Square>> listOfSquares = new ArrayList<>();
        List<Square> topSquares = getTopSquares(chessBoard, 0);
        List<Square> leftSquares = getLeftSquares(chessBoard, 0);
        List<Square> rightSquares = getRightSquares(chessBoard, 0);
        List<Square> bottomSquares = getBottomSquares(chessBoard, 0);
        listOfSquares.add(topSquares);
        listOfSquares.add(leftSquares);
        listOfSquares.add(rightSquares);
        listOfSquares.add(bottomSquares);

        for (List<Square> listOfLists : listOfSquares) {
            for (Square square : listOfLists) {
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
        return whiteTurnPiece(chessBoard);
    }

    @Override
    public String toString() {
        return "R";
    }

}
