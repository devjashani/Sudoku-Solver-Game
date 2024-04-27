import java.util.*;

public class Game {

    // Checks if a value can be placed safely at a specific position
    public static boolean isSafe(char[][] board, int row, int col, int number) {
        // Check row and column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char) (number + '0') && i != row) {
                return false;
            }
            if (board[row][i] == (char) (number + '0') && i != col) {
                return false;
            }
        }

        // Check for same number in the 3x3 subgrid
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == (char) (number + '0') && (i != row || j != col)) {
                    return false;
                }
            }
        }

        return true;
    }

    // Solves the Sudoku using backtracking
    public static boolean solveSudoku(char[][] board) {
        return helper(board, 0, 0);
    }

    // Recursive helper function to solve Sudoku
    private static boolean helper(char[][] board, int row, int col) {
        if (row == board.length) {
            return true;  // Base case: All cells filled, Sudoku solved
        }

        int nextRow = (col == board.length - 1) ? row + 1 : row; // Move to next cell
        int nextCol = (col == board.length - 1) ? 0 : col + 1;

        // If the current cell is already filled (not '.')
        if (board[row][col] != '.') {
            return helper(board, nextRow, nextCol);  // Move to next cell
        }

        // Try all possible numbers for the current cell
        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = (char) (num + '0');
                if (helper(board, nextRow, nextCol)) { // Solve recursively with the placed number
                    return true;
                } else {
                    board[row][col] = '.';  // Backtrack if placement doesn't lead to a solution
                }
            }
        }

        return false;  // No valid placement found for the current cell
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'.', '.', '.', '6', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };

        if (solveSudoku(board)) {
            System.out.println("Sudoku solved successfully!");
            for (int i = 0; i < board.length; i++) {
                System.out.println(Arrays.toString(board[i]));
            }
        } else {
            System.out.println("No solution found for the given Sudoku.");
        }
    }
}
