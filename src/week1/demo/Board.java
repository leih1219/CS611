package week1.demo;

import static week1.demo.PlayGame.gameBoard;

public class Board {
    private int row;
    private int column;
    private char[][] board;

    public Board() {
        row = 3;
        column = 3;
        board = new char[row][column];
    }

    //initialize the board, set the array empty
    public void initBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                board[i][j] = ' ';
            }
        }
    }

    //show board with assist of '+' '-' and '|'
    public void showBoard(){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++) {
                int m = i*2+1;
                int n = j*3+1;
                gameBoard[m][n] = board[i][j];  //find the corresponding position in the output for the board elements
            }
        }
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 10; y++) {
                System.out.print(gameBoard[x][y]);
            }
            System.out.print("\n");
        }
    }

    public char stepResult() {
        //are there three same pieces in a row
        for (int i = 0; i < row; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][1] != ' ') {
                return board[i][1];
            }
        }
        //are there three same pieces in a column
        for (int i = 0; i < row; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[1][i] != ' ') {
                return board[1][i];
            }
        }
        //are there three same pieces in the diagonal
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] != ' ' || board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] != ' ') {
            return board[1][1];
        }

        if (isFull()) {
            return '=';
        } else {
            return 'C';
        }
    }

    private boolean isFull() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char[][] getBoard() {
        return board;
    }

}
