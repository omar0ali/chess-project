package com.chess.pieces.utility;

import java.util.ArrayList;
import java.util.List;

import com.chess.ChessBoard;
import com.chess.Point;
import com.chess.Square;

//TODO: There are some functions needs to be refactored.
public abstract class Piece {
    private PieceColor color;
    private Point currentPosition = null;
    private static int CounterID = 0;
    private final int ID;

    private List<MoveUtility> availableMoves;

    public Piece(PieceColor color) {
        this.color = color;
        this.ID = Piece.CounterID;
        Piece.CounterID++;
        this.availableMoves = new ArrayList<>();
    }

    protected abstract List<MoveUtility> whiteTurnPiece(ChessBoard chessBoard);

    protected abstract List<MoveUtility> blackTurnPiece(ChessBoard chessBoard);

    public List<MoveUtility> getPossibleMoves(ChessBoard chessboard) {
        clearAvailableMoves();
        if (this.getColor() == PieceColor.Black) {
            return blackTurnPiece(chessboard);
        } else { // White Turn
            return whiteTurnPiece(chessboard);
        }
    }

    public void clearAvailableMoves() {
        if (!this.availableMoves.isEmpty()) {
            this.availableMoves.clear();
        }
    }

    public List<MoveUtility> getMoves() {
        return this.availableMoves;
    }

    public void setCurrentPiece(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public PieceColor getColor() {
        return color;
    }

    public int getTotalIDs() {
        return Piece.CounterID;
    }

    public String getIDAsString() {
        return this.getIDAsInteger() + ":" + toString() + "(" + color.getName() + ")";
    }

    public String getPieceAndColor() {
        return toString() + "(" + color.getName() + ")";
    }

    public int getIDAsInteger() {
        return this.ID;
    }

    // Top Left is ngative for Y and negative for X
    public List<Square> getTopLeftSquares(ChessBoard chessBoard, int steps) {
        List<Square> squares = new ArrayList<>();
        int stepsToTop = getCurrentPosition().getY();
        int stepsToLeft = getCurrentPosition().getX();
        int maxSteps = 7 - Math.min(stepsToTop, stepsToLeft);
        if (steps > maxSteps) {
            steps = maxSteps;
        }
        int index = 1;
        while ((steps == 0) ? (index <= maxSteps) : index <= steps) {
            Square newPositionSquare = chessBoard.getSquare(getCurrentPosition().getX() - index,
                    getCurrentPosition().getY() - index);
            squares.add(newPositionSquare);
            if (chessBoard.getSquare(getCurrentPosition().getX() - index, getCurrentPosition().getY() - index)
                    .getPiece() != null) {
                break;
            }
            index++;
        }
        return squares;
    }

    // Negative for Y and Positive for X
    public List<Square> getTopRightSquares(ChessBoard chessBoard, int steps) {
        List<Square> squares = new ArrayList<>();
        int stepsToTop = getCurrentPosition().getY();
        int stepsToRight = getCurrentPosition().getX();
        int maxSteps = 7 - Math.min(stepsToRight, stepsToTop);
        if (steps > maxSteps) {
            steps = maxSteps;
        }
        int index = 1;
        while ((steps == 0) ? (index <= maxSteps) : index <= steps) {
            Square newPositionSquare = chessBoard.getSquare(getCurrentPosition().getX() + index,
                    getCurrentPosition().getY() - index);
            squares.add(newPositionSquare);
            if (chessBoard.getSquare(getCurrentPosition().getX() + index, getCurrentPosition().getY() - index)
                    .getPiece() != null) {
                break;
            }
            index++;
        }
        return squares;
    }

    // Positive for Y and Negative for X
    public List<Square> getBottomLeftSquares(ChessBoard chessBoard, int steps) {
        List<Square> squares = new ArrayList<>();
        int stepsToBottom = getCurrentPosition().getY();
        int stepsToLeft = getCurrentPosition().getX();
        int maxSteps = 7 - Math.min(stepsToLeft, stepsToBottom);
        if (steps > maxSteps) {
            steps = maxSteps;
        }
        int index = 1;
        while ((steps == 0) ? (index <= maxSteps) : index <= steps) {
            Square newPositionSquare = chessBoard.getSquare(getCurrentPosition().getX() - index,
                    getCurrentPosition().getY() + index);
            squares.add(newPositionSquare);
            if (chessBoard.getSquare(getCurrentPosition().getX() - index, getCurrentPosition().getY() + index)
                    .getPiece() != null) {
                break;
            }
            index++;
        }
        return squares;
    }

    // Positive for Y and Positive for X
    public List<Square> getBottomRightSquares(ChessBoard chessBoard, int steps) {
        List<Square> squares = new ArrayList<>();
        int stepsToTop = getCurrentPosition().getY() - 1;
        int stepsToBottom = getCurrentPosition().getX() - 1;
        int maxSteps = 7 - Math.min(stepsToTop, stepsToBottom);
        if (steps > maxSteps) {
            steps = maxSteps;
        }
        int index = 1;
        while ((steps == 0) ? (index <= maxSteps) : index <= steps) {
            Square newPositionSquare = chessBoard.getSquare(getCurrentPosition().getX() + index,
                    getCurrentPosition().getY() + index);
            squares.add(newPositionSquare);
            if (chessBoard.getSquare(getCurrentPosition().getX() + index, getCurrentPosition().getY() + index)
                    .getPiece() != null) {
                break;
            }
            index++;
        }
        return squares;
    }

    public List<Square> getRightSquares(ChessBoard chessBoard, int steps) {
        List<Square> squares = new ArrayList<>();
        int maxSteps = 7 - getCurrentPosition().getX(); // Steps taken until reach the end of the board of the X Axis.
        if (steps > maxSteps) {
            steps = maxSteps;
        }
        int index = 1;
        while ((steps == 0) ? (index <= maxSteps) : index <= steps) {
            squares.add(chessBoard.getSquare(getCurrentPosition().getX() + index,
                    getCurrentPosition().getY()));
            if (chessBoard.getSquare(getCurrentPosition().getX() + index, getCurrentPosition().getY())
                    .getPiece() != null) {
                // If found a piece at this current position, we need to stop and that will be
                // the last square or place the piece can go to.
                // Either Destory the current piece or can't move to that square.
                break;
            }
            index++;
        }
        if (squares.isEmpty()) {
            squares.add(chessBoard.getSquare(getCurrentPosition().getX(), getCurrentPosition().getY()));
        }
        return squares;
    }

    public List<Square> getLeftSquares(ChessBoard chessBoard, int steps) {
        List<Square> squares = new ArrayList<>();
        int maxSteps = getCurrentPosition().getX();

        if (steps > maxSteps) {
            steps = maxSteps;
        }

        int index = 1;
        // Here we make sure we go either to a piece or to the end of the wall.
        while ((steps == 0) ? (index <= maxSteps) : index <= steps) {
            squares.add(chessBoard.getSquare(getCurrentPosition().getX() - index,
                    this.getCurrentPosition().getY()));
            if (chessBoard.getSquare(getCurrentPosition().getX() - index, getCurrentPosition().getY())
                    .getPiece() != null) {
                // If found a piece at this current position, we need to stop and that will be
                // the last square or place the piece can go to.
                // Either Destory the current piece or can't move to that square.
                break;
            }
            index++;
        }
        if (squares.isEmpty()) {
            squares.add(chessBoard.getSquare(getCurrentPosition().getX(), getCurrentPosition().getY()));
        }
        return squares;
    }

    /*
     * - Get all the squares from the current position to the top.
     * - The number of steps, is how many step on each square a piece can go to.
     * - That depends on the piece itself, the developer need to make sure to use
     * this function with a specific piece and their rule accordingly.
     * - If number of steps wasn't set, meaning 0 was given, then the function
     * should go to the top of the board where y = 0 or if faced a friendly or enemy
     * piece.
     * NOTE: A Square contains (Piece and Point(x, y))
     */
    public List<Square> getTopSquares(ChessBoard chessBoard, int steps) {
        List<Square> squares = new ArrayList<>();
        // This answers, where is the position or the last Square on the YAxis.
        int maxSteps = getCurrentPosition().getY();

        // This fixes the maximum number of steps.
        if (steps > maxSteps) {
            steps = maxSteps;
        }

        int index = 1;
        // Here we make sure we go either to a piece or to the end of the wall.
        while ((steps == 0) ? (index <= maxSteps) : index <= steps) {
            squares.add(chessBoard.getSquare(getCurrentPosition().getX(),
                    this.getCurrentPosition().getY() - index));
            if (chessBoard.getSquare(getCurrentPosition().getX(), getCurrentPosition().getY() - index)
                    .getPiece() != null) {
                // If found a piece at this current position, we need to stop and that will be
                // the last square or place the piece can go to.
                // Either Destory the current piece or can't move to that square.
                break;
            }
            index++;
        }
        if (squares.isEmpty()) {
            squares.add(chessBoard.getSquare(getCurrentPosition().getX(), getCurrentPosition().getY()));
        }
        return squares;
    }

    /*
     * Similar to the function getTopSquares, please read the comment.
     */
    public List<Square> getBottomSquares(ChessBoard chessBoard, int steps) {
        List<Square> squares = new ArrayList<>();
        int maxSteps = 7 - getCurrentPosition().getY();

        // This fixes the maximum number of steps.
        if (steps > maxSteps) {
            steps = maxSteps;
        }

        int index = 1;
        while ((steps == 0) ? (index <= maxSteps) : index <= steps) {
            squares.add(chessBoard.getSquare(getCurrentPosition().getX(),
                    this.getCurrentPosition().getY() + index));
            if (chessBoard.getSquare(getCurrentPosition().getX(), getCurrentPosition().getY() + index)
                    .getPiece() != null) {
                // If found a piece at this current position, we need to stop and that will be
                // the last square or place the piece can go to.
                // Either Destory the current piece or can't move to that square.
                break;
            }
            index++;
        }
        if (squares.isEmpty()) {
            squares.add(chessBoard.getSquare(getCurrentPosition().getX(), getCurrentPosition().getY()));
        }
        return squares;
    }

    public static void printMoves(List<MoveUtility> moves) {
        for (MoveUtility move : moves) {
            System.out.print(move);
        }
    }

    @Override
    public abstract String toString();
}