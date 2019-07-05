package com.shanmathuran.sritharan.sudokuvalidator.validator;

import com.shanmathuran.sritharan.sudokuvalidator.InvalidSolutionException;
import com.shanmathuran.sritharan.sudokuvalidator.model.SudokuBoard;

/**
 * Class which implements the Sudoku validation.
 */
public class SudokuValidatorImpl implements SudokuValidator {

    private static final int SUDOKU_SIZE = 9;

    @Override
    public boolean validate(SudokuBoard sudokuBoard) throws InvalidSolutionException {
        if(sudokuBoard == null) {
            return false;
        }

        return checkNumbers(sudokuBoard.getBoard()) && checkRows(sudokuBoard.getBoard()) && checkCols(sudokuBoard.getBoard()) && checkSquares(sudokuBoard.getBoard());
    }

    private boolean checkNumbers(int[][] board) throws InvalidSolutionException {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] < 1 || board[row][col] > 9) {
                    throw new InvalidSolutionException("Numbers out of range");
                }
            }
        }

        return true;
    }

    private boolean checkRows(int[][] board) throws InvalidSolutionException {
        for(int row = 0; row < SUDOKU_SIZE; row++) {
            boolean[] numberExists = new boolean[SUDOKU_SIZE];
            for (int col = 0; col < SUDOKU_SIZE; col++) {
                int index = board[row][col] - 1;
                if (!numberExists[index]) {
                    numberExists[index] = true;
                }
                else {
                    throw new InvalidSolutionException("Duplicate number at row " + (row + 1));
                }
            }
        }

        return true;
    }

    private boolean checkCols(int[][] board) throws InvalidSolutionException {
        for(int col = 0; col < SUDOKU_SIZE; col++) {
            boolean[] numberExists = new boolean[SUDOKU_SIZE];

            for(int row = 0; row < SUDOKU_SIZE; row++) {
                int index = board[row][col] -1;
                if (!numberExists[index]) {
                    numberExists[index] = true;
                }
                else {
                    throw new InvalidSolutionException("Duplicate number at column " + (col + 1));
                }
            }
        }

        return true;
    }

    private boolean checkSquares(int[][] board) throws InvalidSolutionException {
        for (int square = 0; square < 9; square++) {
            boolean[] numberExists = new boolean[9];
            for (int i = square / 3 * 3; i < square / 3 * 3 + 3; i++) {
                for (int j = square % 3 * 3; j < square % 3 * 3 + 3; j++) {
                    if (numberExists[board[i][j] - 1]) {
                        throw new InvalidSolutionException("Duplicate number at square " + (square + 1));
                    }
                    numberExists[board[i][j] - 1] = true;
                }
            }
        }
        return true;
    }
}
