package com.shanmathuran.sritharan.sudokuvalidator;

public class InvalidSudokuBoardException extends Exception {

    public InvalidSudokuBoardException(String message) {
        super(message);
    }

    public InvalidSudokuBoardException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
