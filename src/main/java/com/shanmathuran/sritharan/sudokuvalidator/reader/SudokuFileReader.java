package com.shanmathuran.sritharan.sudokuvalidator.reader;

import com.shanmathuran.sritharan.sudokuvalidator.InvalidSudokuBoardException;
import com.shanmathuran.sritharan.sudokuvalidator.model.SudokuBoard;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * Class to read Sudoku boards from a file.
 */
@Slf4j
public class SudokuFileReader implements SudokuReader {

    @Override
    public SudokuBoard getSudokuBoard(String input) throws InvalidSudokuBoardException {
        File file = new File(input);
        int[][] board = new int[9][9];

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String readLine;
            int lineCount = 0;
            log.debug("Reading file {}", file.getAbsolutePath());

            while(lineCount < 9 && (readLine = bufferedReader.readLine()) != null) {
                String[] splitChars = readLine.split(",");
                if(splitChars.length < 9) {
                    throw new InvalidSudokuBoardException("Incomplete Sudoku board");
                }

                int[] aLine = new int[9];
                for (int colCount = 0; colCount < splitChars.length; colCount++) {
                    aLine[colCount] = Integer.parseInt(splitChars[colCount]);
                }

                board[lineCount++] = aLine;
            }

            if(lineCount != 9) {
                throw new InvalidSudokuBoardException("Incomplete Sudoku board");
            }
        } catch (FileNotFoundException e) {
            throw new InvalidSudokuBoardException("File not found", e);
        } catch (IOException e) {
            throw new InvalidSudokuBoardException("Failed to read file", e);
        }

        return new SudokuBoard(board);
    }
}
