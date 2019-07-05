package com.shanmathuran.sritharan.sudokuvalidator.reader;

import com.shanmathuran.sritharan.sudokuvalidator.InvalidSudokuBoardException;
import com.shanmathuran.sritharan.sudokuvalidator.model.SudokuBoard;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SudokuFileReaderTest {

    @Test
    public void testValidFile() throws Exception {
        SudokuReader sudokuReader = new SudokuFileReader();
        SudokuBoard sudokuBoard = sudokuReader.getSudokuBoard(getClass().getClassLoader().getResource("sudokuFileReaderFile.txt").getPath());
        assertThat(sudokuBoard)
                .isNotNull();
        int[] row = IntStream.rangeClosed(1, 9).toArray();
        assertThat(sudokuBoard.getBoard()).containsSequence(row, row, row, row, row, row, row, row, row);
    }

    @Test
    public void testInvalidFile() {
        SudokuReader sudokuReader = new SudokuFileReader();
        assertThatThrownBy(() -> sudokuReader.getSudokuBoard(getClass().getClassLoader().getResource("sudokuFileReaderInvalidFile.txt").getPath()))
                .isInstanceOf(InvalidSudokuBoardException.class)
                .hasMessageContaining("Incomplete Sudoku board");
    }

    @Test
    public void testInvalidFileRow() {
        SudokuReader sudokuReader = new SudokuFileReader();
        assertThatThrownBy(() -> sudokuReader.getSudokuBoard(getClass().getClassLoader().getResource("sudokuFileReaderInvalidFileRow.txt").getPath()))
                .isInstanceOf(InvalidSudokuBoardException.class)
                .hasMessageContaining("Incomplete Sudoku board");
    }

    @Test
    public void testNonExistentFile() {
        SudokuReader sudokuReader = new SudokuFileReader();
        assertThatThrownBy(() -> sudokuReader.getSudokuBoard("randomfilenamewhichisnonexistent.txt"))
                .isInstanceOf(InvalidSudokuBoardException.class)
                .hasCauseInstanceOf(FileNotFoundException.class);
    }

    @Test
    public void testInvalidSeperator() throws InvalidSudokuBoardException {
        SudokuReader sudokuReader = new SudokuFileReader();
        assertThatThrownBy(() -> sudokuReader.getSudokuBoard(getClass().getClassLoader().getResource("sudokuFileReaderInvalidSeperatorFile.txt").getPath()))
                .isInstanceOf(InvalidSudokuBoardException.class);
    }
}