package week1.demo;

import java.util.Scanner;

public class PlayGame {

    static char[][] gameBoard = new char[7][10];

    public static void main(String[] args){
        int gameStep = 1;
        int oWinTimes = 0, xWinTimes =0;
        while (true) {
            showInitialBoard(); //show the empty board with numbers
            boolean isOTheFirst = userChooseFirst(); //get user's input to decide who comes the first

            Board board = new Board();      //create the object of board
            Player playerO = new Player('O');  //create the object of Player O
            Player playerX = new Player('X');  //create the object of Player X
            board.initBoard();

            char result = ' ';  //calculate result after each step

            //play the game
            while (true){

                board.showBoard();

                String currentPlayer;
                if ((gameStep % 2 == 1 && isOTheFirst) || (gameStep % 2 == 0 && !isOTheFirst)){
                    currentPlayer = "O";
                }
                else {
                    currentPlayer = "X";
                }
                System.out.println("Now, Player " + currentPlayer + " please input the number from 1-9 where you want to move to:");

                if (currentPlayer.equals("O")) {
                    playerO.moveChess(board);
                } else {
                    playerX.moveChess(board);
                }
                result = board.stepResult();
                if (result != 'C') {
                    break;  //if result shows "continue" then continue
                }
                gameStep++;

            }
            if (result == playerO.getChessPiece()) {        //Player O wins!
                System.out.println("Congrats Player O, YOU WIN!");
                oWinTimes++;
            } else if (result == playerX.getChessPiece()) { //Player X wins!
                System.out.println("Congrats Player X, YOU WIN!");
                xWinTimes++;
            } else {                                        //Stalemate
                System.out.println("Stalemate!!");
            }

            //wanna play again?
            Scanner userInput = new Scanner(System.in);
            String decision;
            do {
                System.out.println("Play again? Y/N:");
                decision = userInput.next().toUpperCase();
            } while (!decision.equals("Y") && !decision.equals("N"));
            if (decision.equals("Y")){
                gameStep = 1;
            } else {
                System.out.println("Player O wins: " + oWinTimes + "!\n" + "Player X wins: " + xWinTimes + "!");
                return;
            }

        }
    }

    private static boolean userChooseFirst() {
        boolean isOTheFirst = false;
        //restrict user input 'O' or 'X'
        Scanner sc = new Scanner(System.in);
        String firstPlayer;
        do {
            System.out.println("You can choose which player comes first, 'O' or 'X': ");
            firstPlayer = sc.next().toUpperCase();
        } while (!firstPlayer.equals("O") && !firstPlayer.equals("X"));
        //record the first player for later use
        System.out.println("The first player is Player " + firstPlayer + "!");
        if (firstPlayer.equals("O")){
            isOTheFirst = true;
        }
        return isOTheFirst;
    }

    public static void showInitialBoard() {
        char[][] initialBoard = new char[7][10];
        char identifyNum = '1';
        //initialize the board
        for (int i = 0; i < 7; i += 2) {
            initialBoard[i] = new char[]{'+', '-', '-', '+', '-', '-', '+', '-', '-', '+'};
            gameBoard[i] = new char[]{'+', '-', '-', '+', '-', '-', '+', '-', '-', '+'};
        }
        for (int j = 1; j < 7; j += 2) {
            initialBoard[j] = new char[]{'|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|'};
            gameBoard[j] = new char[]{'|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|'};
            //add the number for user-friendly
            for (int l = 1; l < 10; l += 3) {
                initialBoard[j][l] = identifyNum;
                identifyNum++;
            }
        }

        //print the board
        System.out.println("Welcome to Tic Tac Toe!");
        for (int k = 0; k < 7; k++) {
            for (int g = 0; g < 10; g++) {
                System.out.print(initialBoard[k][g]);
            }
            System.out.print("\n");
        }
    }
}
