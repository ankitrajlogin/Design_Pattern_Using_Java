package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L05_Prototype_Pattern_Design._ChessBoard_CheckPoint;

/*
🟢 Problem Statement
We are building a board game (like Chess, Tic-Tac-Toe, or N-Queens puzzle).

In this game, we often need to save the current state of the board for reasons such as:
1. Undo functionality – If a player wants to undo a move, we must restore the board to its previous state.
2. Checkpointing – Save the progress of the game at different moments.
3. Cloning for new players or branches – Create a new copy of the board for experiments.

➡️ To achieve this, we need a way to create a copy (clone) of the board with all its pieces.
 */


import java.util.ArrayList;
import java.util.List;// Prototype Interface
interface Prototype<T>{
    T clone() ;
}

//GamePiece
class GamePiece implements Prototype<GamePiece>{
    private String color ;
    private  int position ;

    public GamePiece(String color , int position){
        this.color = color ;
        this.position = position ;
    }

    @Override
    public GamePiece clone(){
        return new GamePiece(this.color , this.position) ;
    }

    @Override
    public String toString() {
        return "GamePiece{color='" + color + "', position=" + position + "}";
    }

}

// GameBoard
class GameBoard implements Prototype<GameBoard>{
    private List<GamePiece> pieces = new ArrayList<>() ;

    public void addPiece(GamePiece piece){
        pieces.add(piece) ;
    }

    @Override
    public GameBoard clone(){
        GameBoard newBoard = new GameBoard() ;
        for(GamePiece piece : pieces){
            newBoard.addPiece(piece.clone()); // deep copy
        }
        return newBoard ;
    }

    public void showBoardState() {
        System.out.println("Board State:");
        for (GamePiece piece : pieces) {
            System.out.println(piece);
        }
    }
}

public class CheckBoardRuntime {
    public static void main(String[] args){
        GameBoard board = new GameBoard() ;
        board.addPiece(new GamePiece("Red" , 1));
        board.addPiece(new GamePiece("Blue" , 5));

        System.out.println("Original Board: ") ;
        board.showBoardState();

        // clone using Prototype pattern
        GameBoard copiedBoard = board.clone();
        copiedBoard.addPiece(new GamePiece("Yellow" , 2));
        System.out.println("\nCopied Board");
        copiedBoard.showBoardState();
    }
}


/*
🔑 Related Terms
Shallow Copy – Copies only outer object but shares references of nested objects.

Deep Copy – Creates independent copies of nested objects (used in this example).
 */