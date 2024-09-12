package com.chess;

import java.util.ArrayList;
import java.util.List;

import com.chess.pieces.Bishop;
import com.chess.pieces.King;
import com.chess.pieces.Knight;
import com.chess.pieces.Pawn;
import com.chess.pieces.Queen;
import com.chess.pieces.Rook;
import com.chess.pieces.utility.Piece;
import com.chess.pieces.utility.PieceColor;

public class App {
    ChessBoard chessBoard;

    public App() {
        // Setup board
        chessBoard = new ChessBoard();
        // White Pieces
        List<Pawn> whitePawns = setPawns(PieceColor.White);
        Queen whiteQueen = setQueen(PieceColor.White);
        King whiteKing = setKing(PieceColor.White);
        Knight whiteKnights[] = setKnight(PieceColor.White); // Return two pieces
        Bishop whiteBishops[] = setBishop(PieceColor.White);
        Rook whiteRooks[] = setRook(PieceColor.White);

        // Black Pieces
        List<Pawn> blackPawns = setPawns(PieceColor.Black);
        Queen blackQueen = setQueen(PieceColor.Black);
        King blackKing = setKing(PieceColor.Black);
        Knight blackKnights[] = setKnight(PieceColor.Black); // Return two pieces
        Bishop blackBishops[] = setBishop(PieceColor.Black);
        Rook blackRooks[] = setRook(PieceColor.Black);
        try {
            placePiecesOnBoard(
                    PieceColor.White,
                    whitePawns,
                    whiteKing,
                    whiteQueen,
                    whiteBishops,
                    whiteKnights,
                    whiteRooks);

            placePiecesOnBoard(
                    PieceColor.Black,
                    blackPawns,
                    blackKing,
                    blackQueen,
                    blackBishops,
                    blackKnights,
                    blackRooks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chessBoard.printBoard();
    }

    private Queen setQueen(PieceColor color) {
        return new Queen(color);
    }

    private King setKing(PieceColor color) {
        return new King(color);
    }

    private Knight[] setKnight(PieceColor color) {
        Knight knights[] = { new Knight(color), new Knight(color) };
        return knights;
    }

    private Rook[] setRook(PieceColor color) {
        Rook rooks[] = { new Rook(color), new Rook(color) };
        return rooks;
    }

    private Bishop[] setBishop(PieceColor color) {
        Bishop bishops[] = { new Bishop(color), new Bishop(color) };
        return bishops;
    }

    private List<Pawn> setPawns(PieceColor color) {
        List<Pawn> pawns = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            pawns.add(new Pawn(color));
        }
        return pawns;
    }

    public void placePiecesOnBoard(PieceColor color, List<Pawn> pawns, King king, Queen queen, Bishop[] bishops,
            Knight[] knights, Rook[] rooks) throws Exception {
        int x = 0;
        if (color == PieceColor.White) {
            for (Pawn p : pawns) {
                chessBoard.placePiece(p, x, 1);
                x++;
            }
        } else {
            for (Pawn p : pawns) {
                chessBoard.placePiece(p, x, 6);
                x++;
            }
        }
        if (color == PieceColor.White) {
            chessBoard.placePiece(rooks[0], 0, 0);
            chessBoard.placePiece(rooks[1], 7, 0);
            chessBoard.placePiece(knights[0], 6, 0);
            chessBoard.placePiece(knights[1], 1, 0);
            chessBoard.placePiece(bishops[0], 2, 0);
            chessBoard.placePiece(bishops[1], 5, 0);
            chessBoard.placePiece(king, 3, 0);
            chessBoard.placePiece(queen, 4, 0);
            // Piece.printMoves(whiteKing.getPossibleMoves(chessBoard));
        } else {
            chessBoard.placePiece(rooks[0], 0, 7);
            chessBoard.placePiece(rooks[1], 7, 7);
            chessBoard.placePiece(knights[0], 6, 7);
            chessBoard.placePiece(knights[1], 1, 7);
            chessBoard.placePiece(bishops[0], 2, 7);
            chessBoard.placePiece(bishops[1], 5, 7);
            chessBoard.placePiece(king, 3, 7);
            chessBoard.placePiece(queen, 4, 7);
        }
    }

    // Top is white, bottom is black.
    public static void main(String[] args) {
        App app = new App();

        // Example how to access a pieces from the board
        Piece piece = app.chessBoard.getSquare(3, 1).getPiece(); // Select a specific coordinate, get piece.
        System.out.println(piece); // Pawn
        System.out.println(piece.getColor()); // White
        Piece.printMoves(piece.getPossibleMoves(app.chessBoard)); // Print available moves of the Pawn
    }
}
