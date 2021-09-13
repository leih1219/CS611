package week1.demo;

public class Functions {
    public static boolean validPosition(int userInput, Board board) {

        return board.getBoard()[(userInput - 1) / 3][(userInput - 1) % 3] == ' ';
    }
}
