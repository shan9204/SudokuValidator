package com.shanmathuran.sritharan.sudokuvalidator.validator;

import com.shanmathuran.sritharan.sudokuvalidator.InvalidSolutionException;
import com.shanmathuran.sritharan.sudokuvalidator.model.SudokuBoard;

/**
 * Interface for the Sudoku validator
 */
public interface SudokuValidator {

    /**
     * Validates a given SudokuBoard whether or not it is a valid Sudoku result.
     *
     * @param sudokuBoard the sudokuBoard to validate
     * @return true, when sudokuBoard is valid
     * @throws InvalidSolutionException thrown, when any checks for valid Sudoku fails
     */
    boolean validate(SudokuBoard sudokuBoard) throws InvalidSolutionException;
}
