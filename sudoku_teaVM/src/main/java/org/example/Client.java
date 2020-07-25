package org.example;

import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<String> longest = new ArrayList<>();

        longest.add("0 0 0 0 0 0 0 0 0");

        longest.add("0 0 0 0 0 3 0 8 5");
        longest.add("0 0 1 0 2 0 0 0 0");
        longest.add("0 0 0 5 0 7 0 0 0");
        longest.add("0 0 4 0 0 0 1 0 0");
        longest.add("0 9 0 0 0 0 0 0 0");
        longest.add("5 0 0 0 0 0 0 7 3");
        longest.add("0 0 2 0 1 0 0 0 0");
        longest.add("0 0 0 0 4 0 0 0 9");

        Board board = new Board(longest);
        Solver solver = new Solver();
        Board solved = solver.Solve(board);

        System.out.println(board.toString());
        System.out.println("--------------------------------");
        System.out.println(solved.toString());
        double elapsed = (System.currentTimeMillis() - startTime) / 1000F;
        System.out.println("Solved in " + elapsed + " seconds");
    }
}
