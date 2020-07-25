using sudokuNet;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using Xunit;

// example taken from https://www.websudoku.com/
namespace sudokuTester {
    public class SolverTest {
        private readonly string testPath = @"D:\Users\frozen\Documents\03_programming\online\sudokuNet\sudokuTester\tests\";

        [Fact]
        public void TestEasy() {

            var rows = File.ReadLines(testPath + "easy1_input.txt").ToList();
            Board board = new Board(rows);
            Solver solver = new Solver();
            Board solved = solver.Solve(board);

            string expected = File.ReadAllText(testPath + "easy1_solution.txt");
            Assert.Equal(expected, solved.ToString());
        }

        [Fact]
        public void TestEvil() {
            var rows = File.ReadLines(testPath + "evil1_input.txt").ToList();
            Board board = new Board(rows);
            Solver solver = new Solver();
            Board solved = solver.Solve(board);
            Debug.Write(solved.ToString());

            string expected = File.ReadAllText(testPath + "evil1_solution.txt");
            Assert.Equal(expected, solved.ToString());
        }


        [Fact]
        public void TestHardest() {
            var rows = File.ReadLines(testPath + "hardest_input.txt").ToList();
            Board board = new Board(rows);
            Solver solver = new Solver();
            Board solved = solver.Solve(board);

            string expected = File.ReadAllText(testPath + "hardest_solution.txt");
            Assert.Equal(expected, solved.ToString());
        }



    }
}
