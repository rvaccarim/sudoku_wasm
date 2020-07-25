package main.java;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        var longest = List.of(
                "0 0 0 0 0 0 0 0 0",
                "0 0 0 0 0 3 0 8 5",
                "0 0 1 0 2 0 0 0 0",
                "0 0 0 5 0 7 0 0 0",
                "0 0 4 0 0 0 1 0 0",
                "0 9 0 0 0 0 0 0 0",
                "5 0 0 0 0 0 0 7 3",
                "0 0 2 0 1 0 0 0 0",
                "0 0 0 0 4 0 0 0 9"
        );

        Board board = new Board(longest);
        Solver solver = new Solver();
        Board solved = solver.Solve(board);

        System.out.println(board.toString());
        System.out.println("--------------------------------");
        System.out.println(solved.toString());
        double elapsed = (System.currentTimeMillis() - startTime) / 1000F;
        System.out.printf("Solved in %.3f seconds", elapsed);

    }
}
