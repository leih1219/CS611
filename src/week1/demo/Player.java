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
        sc = new Scanner(System.in);
        int userInput = 0;
        while(true){

            try {
                userInput = sc.nextInt();
            }
            catch (InputMismatchException exception){
                System.out.println("Error - Enter a integer");
            }


            //check the number is valid.
            if (userInput<1 || userInput>9){
                System.out.println("Please input a number between 1 - 9");
                continue;
            }

            boolean isValid = Fuctions.validPosition(userInput,board); //judge whether current position is occupied
            if(isValid){
                board.getBoard()[(userInput-1)/3][(userInput-1)%3] = chessPiece;
                return;
            }
        }
    }

    public char getChessPiece() {
        return chessPiece;
    }

}
