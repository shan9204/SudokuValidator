package com.shanmathuran.sritharan.sudokuvalidator;

import com.shanmathuran.sritharan.sudokuvalidator.model.SudokuBoard;
import com.shanmathuran.sritharan.sudokuvalidator.reader.SudokuFileReader;
import com.shanmathuran.sritharan.sudokuvalidator.reader.SudokuReader;
import com.shanmathuran.sritharan.sudokuvalidator.validator.SudokuValidator;
import com.shanmathuran.sritharan.sudokuvalidator.validator.SudokuValidatorImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        if(args.length == 0) {
            log.error("Failed to start application, missing filename argument");
            System.exit(-1);
        }

        SudokuReader sudokuReader = new SudokuFileReader();
        SudokuBoard sudokuBoard = null;
        try {
            sudokuBoard = sudokuReader.getSudokuBoard(args[0]);
        } catch (InvalidSudokuBoardException e) {
            log.error("Failed to read Sudoku file", e);
            System.exit(-1);
        }

        SudokuValidator sudokuValidator = new SudokuValidatorImpl();
        try {
            sudokuValidator.validate(sudokuBoard);
        } catch (InvalidSolutionException e) {
            log.error("Solution provided is invalid:", e);
            System.exit(1);
        }

        log.info("0");
    }
}
