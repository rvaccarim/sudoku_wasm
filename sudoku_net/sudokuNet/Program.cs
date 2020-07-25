using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;

namespace sudokuNet {
    class Program {
        static void Main(string[] args) {
            var watch = System.Diagnostics.Stopwatch.StartNew();

            // longest
            var longest = new List<String>
            {
                "0 0 0 0 0 0 0 0 0",
                "0 0 0 0 0 3 0 8 5",
                "0 0 1 0 2 0 0 0 0",
                "0 0 0 5 0 7 0 0 0",
                "0 0 4 0 0 0 1 0 0",
                "0 9 0 0 0 0 0 0 0",
                "5 0 0 0 0 0 0 7 3",
                "0 0 2 0 1 0 0 0 0",
                "0 0 0 0 4 0 0 0 9"
            };

            Board board = new Board(longest);
            Solver solver = new Solver();
            Board solved = solver.Solve(board);

            Console.WriteLine(board.ToString());
            Console.WriteLine("--------------------------------");
            Console.WriteLine(solved.ToString());

            watch.Stop();
            float elapsedMs = watch.ElapsedMilliseconds / 1000f;
            Console.WriteLine($"Solved {elapsedMs} in seconds");
        }
    }
}
