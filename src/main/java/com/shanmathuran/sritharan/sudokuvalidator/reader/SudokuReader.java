package com.shanmathuran.sritharan.sudokuvalidator.reader;

import com.shanmathuran.sritharan.sudokuvalidator.InvalidSudokuBoardException;
import com.shanmathuran.sritharan.sudokuvalidator.model.SudokuBoard;

/**
 * Interface to retrieve a SudokuBoard from any given source
 */
public interface SudokuReader {

    /**
     * Retrieves a Sudoku board of a given input
     * @param input the input (e.g filename, url...)
     * @return SudokuBoard of the given input
     */
    SudokuBoard getSudokuBoard(String input) throws InvalidSudokuBoardException;
}
