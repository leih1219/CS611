package week1.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private char chessPiece;

    public Player(char chess) {
        chessPiece = chess;
    }

    public void moveChess(Board board){
        Scanner sc;

        int userInput;
        while(true){

            try {
                sc = new Scanner(System.in);
                userInput = sc.nextInt();
            }
            catch (InputMismatchException exception){
                System.out.println("Error - Enter a integer");
                continue;
            }


            //check the number is valid.
            if (userInput<1 || userInput>9){
                System.out.println("Please input a number between 1 - 9");
                continue;
            }

            boolean isValid = Functions.validPosition(userInput,board); //judge whether current position is occupied
            if (isValid) {
                board.getBoard()[(userInput - 1) / 3][(userInput - 1) % 3] = chessPiece;
                return;
            } else {
                System.out.println("This position is occupied, please choose another one!");
            }
        }
    }

    public char getChessPiece() {
        return chessPiece;
    }

}
