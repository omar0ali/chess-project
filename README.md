# Chess-Project Java CLI

> [!NOTE]
> This project was created for learning purposes. The code may not be perfect, as I am exploring different approaches to solving various problems related to the mechanics of the game. My goal is to develop a game that functions in the terminal for now.

## Instructions

### 1. Clone the Repository:

```
git clone github.com/omar0ali/chess-project
cd chess-project
```

### 2. Ensure Java and Gradle are Installed:

-   Make sure you have Java installed. You can check by running:

```
java -version
```

-   Verify that Gradle is installed by running:

```
gradle -v
```

### 2. Run the Project:

Navigate to the project directory and execute the following command to run the game:

```
gradle run
```

Edit the Code (Optional):

You can modify the App.java file. Feel free to experiment.

### Example:

1. First setup the chessboard and the pieces.

```java
...
ChessBoard chessBoard;
public App() {
    // Setup board
    chessBoard = new ChessBoard();
    // White Pieces
    List<Pawn> whitePawns = setPawns(PieceColor.White);
    Queen whiteQueen = setQueen(PieceColor.White);
    King whiteKing = setKing(PieceColor.White);
    Knight whiteKnights[] = setKnight(PieceColor.White); // Return two pieces
    Bishop whiteBishops[] = setBishop(PieceColor.White);// Return two pieces
    Rook whiteRooks[] = setRook(PieceColor.White);// Return two pieces

    // Black Pieces
    List<Pawn> blackPawns = setPawns(PieceColor.Black);
    Queen blackQueen = setQueen(PieceColor.Black);
    King blackKing = setKing(PieceColor.Black);
    Knight blackKnights[] = setKnight(PieceColor.Black); // Return two pieces
    Bishop blackBishops[] = setBishop(PieceColor.Black);// Return two pieces
    Rook blackRooks[] = setRook(PieceColor.Black);// Return two pieces
    try {
        placePiecesOnBoard(
                PieceColor.White, // By providing the color, it will automatically know where to place the pieces.
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
```

2. Here shows how to access the chessboard, and update the board.

```java
public static void main(String[] args) {
    App app = new App(); //First Create the App Object, since it has everything required for the game

    // Example how to access a pieces from the board
    Piece piece = app.chessBoard.getSquare(3, 1).getPiece(); // Select a specific coordinate, get piece.
    System.out.println(piece); // Pawn
    System.out.println(piece.getColor()); // White
    Piece.printMoves(piece.getPossibleMoves(app.chessBoard)); // Print available moves of the Pawn
}
```
