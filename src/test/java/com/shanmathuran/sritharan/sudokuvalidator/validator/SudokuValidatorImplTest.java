package com.shanmathuran.sritharan.sudokuvalidator.validator;

import com.shanmathuran.sritharan.sudokuvalidator.InvalidSolutionException;
import com.shanmathuran.sritharan.sudokuvalidator.InvalidSudokuBoardException;
import com.shanmathuran.sritharan.sudokuvalidator.model.SudokuBoard;
import com.shanmathuran.sritharan.sudokuvalidator.reader.SudokuFileReader;
import com.shanmathuran.sritharan.sudokuvalidator.reader.SudokuReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SudokuValidatorImplTest {

    @Test
    public void testInvalidSudoku() throws InvalidSudokuBoardException, InvalidSolutionException {
        SudokuReader sudokuReader = new SudokuFileReader();
        final SudokuBoard invalidSudokuBoard = sudokuReader.getSudokuBoard(getClass().getClassLoader().getResource("sudokuFileReaderFile.txt").getPath());
        SudokuValidator sudokuValidator = new SudokuValidatorImpl();
        assertThatThrownBy(() -> sudokuValidator.validate(invalidSudokuBoard)).isInstanceOf(InvalidSolutionException.class).hasMessageContaining("Duplicate number at column");

        final SudokuBoard invalidSudokuBoard2 = sudokuReader.getSudokuBoard(getClass().getClassLoader().getResource("invalidSudokuNumberRange.txt").getPath());
        assertThatThrownBy(() -> sudokuValidator.validate(invalidSudokuBoard2)).isInstanceOf(InvalidSolutionException.class).hasMessageContaining("Numbers out of range");

        final SudokuBoard invalidSudokuBoard3 = sudokuReader.getSudokuBoard(getClass().getClassLoader().getResource("invalidSudokuRow.txt").getPath());
        assertThatThrownBy(() -> sudokuValidator.validate(invalidSudokuBoard3)).isInstanceOf(InvalidSolutionException.class).hasMessageContaining("Duplicate number at row");

        final SudokuBoard invalidSudokuBoard4 = sudokuReader.getSudokuBoard(getClass().getClassLoader().getResource("invalidSudokuSquare.txt").getPath());
        assertThatThrownBy(() -> sudokuValidator.validate(invalidSudokuBoard4)).isInstanceOf(InvalidSolutionException.class).hasMessageContaining("Duplicate number at square");
    }

    @Test
    public void testValidSudoku() throws InvalidSudokuBoardException, InvalidSolutionException {
        SudokuReader sudokuReader = new SudokuFileReader();
        final SudokuBoard sudokuBoard = sudokuReader.getSudokuBoard(getClass().getClassLoader().getResource("validSudoku.txt").getPath());
        SudokuValidator sudokuValidator = new SudokuValidatorImpl();
        assertThat(sudokuValidator.validate(sudokuBoard)).isTrue();

        final SudokuBoard sudokuBoard2 = sudokuReader.getSudokuBoard(getClass().getClassLoader().getResource("validSudoku2.txt").getPath());
        assertThat(sudokuValidator.validate(sudokuBoard2)).isTrue();
    }
}