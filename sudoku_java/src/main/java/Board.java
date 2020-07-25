package main.java;

import java.util.List;

public class Board {

    public int[][] cells = new int[9][9];
    public boolean[][] fixedCells = new boolean[9][9];

    public Board() {

    }

    public Board(List<String> rows) {
        for (int row = 0; row < rows.size(); row++) {
            var values = rows.get(row).split(" ");

            for (int col = 0; col < values.length; col++) {
                var v = Integer.parseInt(values[col]);
                cells[row][col] = v;

                if (v != 0) {
                    fixedCells[row][col] = true;
                } else {
                    fixedCells[row][col] = false;
                }
            }
        }
    }

    private boolean IsSummaryOk(int[] summary) {
        // we shouldn't have a zero on the board
        if (summary[0] != 0) {
            return false;
        }

        // and we shouldn't have duplicates or missing values
        for (int i = 1; i < 10; i++) {
            if (summary[i] != 1) {
                return false;
            }
        }

        return true;
    }


    public boolean IsSolved() {
        //  validate rows
        for (int row = 0; row < 9; row++) {
            // ten elements
            int[] summary = {
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            };

            for (int col = 0; (col < 9); col++) {
                int value = this.cells[row][col];
                summary[value]++;
            }

            if (!IsSummaryOk(summary)) {
                return false;
            }
        }

        //  validate columns
        for (int col = 0; col < 9; col++) {
            int[] summary = {
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            };

            for (int row = 0; (row < 9); row++) {
                int value = this.cells[row][col];
                summary[value]++;
            }

            if (!IsSummaryOk(summary)) {
                return false;
            }
        }

        // validate quadrant
        for (int i = 0; i <= 6; i += 3) {
            for (int j = 0; j <= 6; j += 3) {
                if (!IsQuadrantValid(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean IsQuadrantValid(int startRow, int startCol) {
        int[] summary = {
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                int value = cells[i][j];
                summary[value]++;
            }
        }

        return IsSummaryOk(summary);
    }


    public boolean IsPlayValid(int row, int col, int n) {
        //  validate rows
        for (int r = 0; r < 9; r++) {
            if (cells[r][col] == n) {
                return false;
            }
        }

        //  validate columns
        for (int c = 0; c < 9; c++) {
            if (cells[row][c] == n) {
                return false;
            }
        }

        //  validate 3x3 matrix
        int br = (row / 3);
        int bc = (col / 3);
        for (int r = br * 3; r < (br + 1) * 3; r++) {
            for (int c = bc * 3; c < (bc + 1) * 3; c++) {
                if (cells[r][c] == n) {
                    return false;
                }
            }
        }

        return true;
    }


    @Override
    public String toString() {
        StringBuilder boardStr = new StringBuilder();

        int cont = 0;
        for (int r = 0; r < 9; r++) {
            int contx = 0;

            for (int c = 0; c < 9; c++) {
                boardStr.append(cells[r][c]).append(" ");
                contx++;
                if ((contx == 3) || (contx == 6)) {
                    boardStr.append("| ");
                }
            }

            boardStr = new StringBuilder(boardStr.toString().trim());
            boardStr.append(System.lineSeparator());

            cont++;
            if ((cont == 3) || (cont == 6)) {
                boardStr.append("---------------------");
                boardStr.append(System.lineSeparator());
            }

        }

        return boardStr.toString();
    }
}