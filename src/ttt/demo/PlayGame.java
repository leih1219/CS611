package ttt.demo;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class PlayGame {
    static char[][] gameBoard = new char[7][10];
    static char[][] oInput = new char[3][3];
    static char[][] xInput = new char[3][3];
    static char[][] allInput = new char[3][3];
// clear above variables when restart
    public static void main(String[] args){
        int step = 1;
        String firstPlayer;
        boolean isOTheFirst = false;
        int oWinTimes = 0, xWinTimes =0;

        while (true){
            System.out.println("Welcome to Tic Tac Toe!");

            showInitialBoard();


            //restrict user input 'O' or 'X'
            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("You can choose which player comes first, 'O' or 'X': ");
                firstPlayer = sc.next();
                firstPlayer = firstPlayer.toUpperCase();
            } while (!firstPlayer.equals("O") && !firstPlayer.equals("X"));

            System.out.println("The first player is Player " + firstPlayer + "!");
            if (firstPlayer.equals("O")){
                isOTheFirst = true;
            }
            //System.out.println("Is O Player the first one? " + isOTheFirst);
            while (true){
                Map<String , String> userInput = requireInput(step, isOTheFirst);
                drawCurrentBoard(userInput.get("player"), userInput.get("playerInput"));

                //figure out whether one of them is win or to announce stalemate
                char result = rule(step);
                if (result == ' '){
                    step++;
                } else if (result == 'X'){
                    System.out.println("Congrats Player X, YOU WIN!");
                    xWinTimes++;
                    break;
                } else if (result == 'O'){
                    System.out.println("Congrats Player O, YOU WIN!");
                    oWinTimes++;
                    break;
                } else if (result == '='){
                    System.out.println("STALEMATE!");
                    break;
                }
            }
            //wanna play again?
            System.out.println("Play again? Y/N:");
            Scanner userInput = new Scanner(System.in);
            String decision = userInput.next();
            decision = decision.toUpperCase();
            if (decision.equals("Y")){
                allInput = new char[3][3];
                xInput = new char[3][3];
                oInput = new char[3][3];
                step = 1;
                isOTheFirst = false;
            } else {
                System.out.println("Player O wins: " + oWinTimes + "!\n" + "Player X wins: " + xWinTimes + "!");
                return;
            }

        }


    }

    public static void showInitialBoard() {
        char[][] initialBoard = new char[7][10];
        char identifyNum = '1';
        //initialize the board
        for(int i=0; i < 7; i+=2){
            initialBoard[i] = new char[]{'+', '-', '-', '+', '-', '-', '+', '-', '-', '+'};
            gameBoard[i] = new char[]{'+', '-', '-', '+', '-', '-', '+', '-', '-', '+'};
        }
        for (int j=1; j < 7; j+=2){
            initialBoard[j] = new char[]{'|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|'};
            gameBoard[j] = new char[]{'|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|'};
            //add the number for user-friendly
            for (int l=1; l < 10; l+=3){
                initialBoard[j][l] = identifyNum;
                identifyNum++;
            }
        }

        //print the board
        for (int k=0; k<7; k++){
            for (int g=0; g<10; g++){
                System.out.print(initialBoard[k][g]);
            }
            System.out.print("\n");
        }

    }

    public static Map<String, String> requireInput(int gameStep, boolean isOTheFirstPlayer){
        int userInput = 0;
        String currentPlayer;
        if ((gameStep % 2 == 1 && isOTheFirstPlayer) || (gameStep % 2 == 0 && !isOTheFirstPlayer)){
            currentPlayer = "O";
        }
        else {
            currentPlayer = "X";
        }
        System.out.println("Now, Player " + currentPlayer + " please input the number from 1-9 where you want to move to:");

        //record and check the user's input
        boolean flag = true;
        while (flag){
            Scanner sc = new Scanner(System.in);
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

            //check whether the position is occupied
            int m = (userInput-1)/3;
            int n = (userInput-1)%3;
            if (allInput[m][n] != '\0'){
                System.out.println("This space is occupied, please choose a valid one!");
                continue;
            } else { //record the input in the arrays
                allInput[m][n] = (char) userInput;
                if (currentPlayer.equals("O")){
                    oInput[m][n] = (char) userInput;
                } else {
                    xInput[m][n] = (char) userInput;
                }
            }

            flag = false;

        }
        Map<String, String> userInputInfo = new HashMap<>();
        userInputInfo.put("player", currentPlayer);
        userInputInfo.put("playerInput", String.valueOf(userInput));
        return userInputInfo;
    }

    public static char rule(int totalStep){
        boolean oWins = false;
        boolean xWins = false;
        //there are three in the line
        //flag to record whether lines up in row
        boolean oLinesUpInRow;
        boolean xLinesUpInRow;
        //flag to record whether lines up in column
        boolean oLinesUpInCol;
        boolean xLinesUpInCol;
        //flag to record whether lines up in diagonal
        boolean oLinesUpInDia1 = true;
        boolean xLinesUpInDia1 = true;
        boolean oLinesUpInDia2 = true;
        boolean xLinesUpInDia2 = true;
        if(totalStep<5){ //Not possible to win the game
            return ' ';
        } else if (totalStep<10){
            //check the rows first
            for (int row=0; row < 3; row++){
                xLinesUpInRow = oLinesUpInRow = xLinesUpInCol = oLinesUpInCol =  true;
                for(int column=0; column<3; column++){
                    if (oInput[row][column] == '\0'){
                        oLinesUpInRow = false;
                    }
                    //check the columns then
                    if (oInput[column][row] == '\0'){
                        oLinesUpInCol = false;
                    }

                    //the same for player X
                    if (xInput[row][column] == '\0'){
                        xLinesUpInRow = false;
                    }
                    //check the columns then
                    if (xInput[column][row] == '\0'){
                        xLinesUpInCol = false;
                    }
                }
                if (oLinesUpInRow || oLinesUpInCol){
                    oWins = true;
                    break;
                }
                if (xLinesUpInRow || xLinesUpInCol){
                    xWins = true;
                    break;
                }
            }
            if (oWins){
                return 'O';
            } else if (xWins){
                return 'X';
            } else {//No winners yet, check the diagonal
                for (int i=0; i<3;i++){
                    if (oInput[i][i] == '\0'){
                        oLinesUpInDia1 = false;
                    }
                    if (xInput[i][i] == '\0'){
                        xLinesUpInDia1 = false;
                    }
                    for (int j = 2; j>=0; j--){
                        if (i+j == 2){
                            if (oInput[i][j] == '\0'){
                                oLinesUpInDia2 = false;
                            }
                            if (xInput[i][j] == '\0'){
                                xLinesUpInDia2 = false;
                            }
                        }
                    }
                }
                if (oLinesUpInDia1 || oLinesUpInDia2){
                    oWins = true;
                }
                if (xLinesUpInDia1 || xLinesUpInDia2){
                    xWins = true;
                }
            }
            if (oWins){
                return 'O';
            } else if (xWins) {
                return 'X';
            }
            //no winners yet
            //continue then
            return ' ';
        } else { //over 9 steps, the game is stalemate
            return '=';
        }
    }

    public static void drawCurrentBoard(@NotNull String player, @NotNull String playerInput){
        //position in 3x3
        int playerInputNum = Integer.parseInt(playerInput);
        int i = (playerInputNum-1)/3;
        int j = (playerInputNum-1)%3;
        //position in 10x7
        int m = i*2+1;
        int n = j*3+1;
        if (player.equals("O")){
            gameBoard[m][n] = 'O';
        } else {
            gameBoard[m][n] = 'X';
        }

        //print the game board
        for (int k=0; k<7; k++){
            for (int g=0; g<10; g++){
                System.out.print(gameBoard[k][g]);
            }
            System.out.print("\n");
        }
    }

}
