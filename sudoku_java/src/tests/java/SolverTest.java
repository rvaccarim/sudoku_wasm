package tests.java;

import main.java.Board;
import main.java.Solver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;


public class SolverTest {
    private final String testPath = "D:/Users/frozen/Documents/03_programming/online/sudoku/sudoku_java/src/tests/java/tests/";

    @Test
    public void TestEasy() throws IOException {
        var rows = Files.readAllLines(new File(testPath + "easy1_input.txt").toPath(), Charset.defaultCharset());
        Board board = new Board(rows);
        Solver solver = new Solver();
        Board solved = solver.Solve(board);

        String expected = Files.readString(new File(testPath + "easy1_solution.txt").toPath(), Charset.defaultCharset());
        assertEquals(expected, solved.toString());
    }

    @Test
    public void TestEvil() throws IOException {
        var rows = Files.readAllLines(new File(testPath + "evil1_input.txt").toPath(), Charset.defaultCharset());
        Board board = new Board(rows);
        Solver solver = new Solver();
        Board solved = solver.Solve(board);

        String expected = Files.readString(new File(testPath + "evil1_solution.txt").toPath(), Charset.defaultCharset());
        assertEquals(expected, solved.toString());
    }


    @Test
    public void TestHardest() throws IOException {
        var rows = Files.readAllLines(new File(testPath + "hardest_input.txt").toPath(), Charset.defaultCharset());
        Board board = new Board(rows);
        Solver solver = new Solver();
        Board solved = solver.Solve(board);

        String expected = Files.readString(new File(testPath + "hardest_solution.txt").toPath(), Charset.defaultCharset());
        assertEquals(expected, solved.toString());
    }


}

