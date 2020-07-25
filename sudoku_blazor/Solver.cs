using System.Collections.Generic;
using System.Linq;

namespace sudoku_blazor {
    public class Solver {

        public Solver() {

        }

        public Board Solve(Board board) {
            // optimize board
            // reorder so that we have the sections with more hints in the upper section (reduces the
            // search space)
            var sections = ScoreSections(board);

            Dictionary<int, int> swapInstructions = new Dictionary<int, int>();
            int s = 0;
            foreach (var sortedSection in sections.OrderByDescending(x => x.Score).ToList()) {
                // from ---> to
                swapInstructions.Add(sortedSection.ID, s);
                s++;
            }

            var swappedBoard = SwapSections(board, sections, swapInstructions);

            // solve using backtracking
            Backtrack(swappedBoard);

            // validate that the board is solved
            if (swappedBoard.IsSolved()) {
                // restore original section order
                var swapBackInstructions = new Dictionary<int, int>();
                foreach (var instruction in swapInstructions) {
                    swapBackInstructions.Add(instruction.Value, instruction.Key);
                    s++;
                }

                return SwapSections(swappedBoard, sections, swapBackInstructions);
            }
            else {
                return null;
            }
        }

        private List<Section> ScoreSections(Board board) {
            // calculate how many fixed values we have in the upper, middle and bottom sections
            int upperScore = 0;
            int middleScore = 0;
            int bottomScore = 0;

            for (int row = 0; row < 9; row++) {
                int score = 0;
                for (int col = 0; col < 9; col++) {
                    if (board.fixedCells[row, col]) {
                        score++;
                    }
                }

                if (row < 3) {
                    upperScore += score;
                }
                else {
                    if (row < 6) {
                        middleScore += score;
                    }
                    else {
                        bottomScore += score;
                    }
                }
            }

            var sections = new List<Section>
            {
                new Section(0, upperScore),
                new Section(1, middleScore),
                new Section(2, bottomScore)
            };

            return sections;
        }

        private void Backtrack(Board board) {
            //  backtracing iterative version
            int row = 0;
            int col = 0;
            int n;
            int num = 1;

            //  if row = 9 then we have found a solution (matrix range is from 0 to 8)
            start:
            while (row < 9) {
                if (!board.fixedCells[row, col]) {
                    for (n = num; n <= 9; n++) {
                        if (board.IsPlayValid(row, col, n)) {
                            //  move forward
                            //  increment poisition and start searching from 1 again for the next value
                            board.cells[row, col] = n;
                            col++;
                            if (col > 8) {
                                col = 0;
                                row++;
                            }
                            num = 1;
                            goto start;
                        }
                    }
                }
                else {
                    // increment poisition and start searching from 1 again for the next value
                    col++;
                    if (col > 8) {
                        col = 0;
                        row++;
                    }
                    num = 1;
                    goto start;
                }

                //  if it arrives here then no value is valid for the cell, so we need to go back
                //  clean the current cell
                board.cells[row, col] = 0;
                //  decrease position, we need to go back to the first non fixed cell
                while (true) {
                    col--;
                    if (col < 0) {
                        col = 8;
                        row--;
                    }

                    if (!board.fixedCells[row, col]) {
                        break;
                    }

                }

                // increase the value of the cell in order to keep searching for the solution
                num = board.cells[row, col] + 1;
            }
        }

        private Board SwapSections(Board board, List<Section> sections, Dictionary<int, int> swapInstructions) {
            Board tmpBoard = new Board();

            foreach (var section in sections) {

                int fromSection = section.ID;
                int toSection = swapInstructions[fromSection];
                int rowFrom = fromSection * 3;
                int rowTo = toSection * 3;

                for (int i = rowFrom; i < rowFrom + 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        tmpBoard.cells[rowTo, j] = board.cells[i, j];
                        tmpBoard.fixedCells[rowTo, j] = board.fixedCells[i, j];
                    }
                    rowTo++;
                }
            }

            return tmpBoard;
        }

    }
}
