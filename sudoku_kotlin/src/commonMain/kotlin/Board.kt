class Board {
    var cells = Array(9) { IntArray(9) }
    var fixedCells = Array(9) { BooleanArray(9) }

    constructor() {}

    constructor(rows: List<String>) {
        for (row in 0..8) {
            val values = rows[row].split(" ").toTypedArray()
            for (col in 0..8) {
                val v = values[col].toInt()
                cells[row][col] = v
                fixedCells[row][col] = (v != 0)
            }
        }

//        println("Board created")
//        for (i in cells.indices) {
//            print(cells[i].contentToString())
//        }
//
//        for (i in fixedCells.indices) {
//            print(fixedCells[i].contentToString())
//        }

    }

    private fun isSummaryOk(summary: IntArray): Boolean {
        // we shouldn't have a zero on the board
        if (summary[0] != 0) {
            return false
        }

        // and we shouldn't have duplicates or missing values
        for (i in 1..9) {
            if (summary[i] != 1) {
                return false
            }
        }
        return true
    }

    fun isSolved(): Boolean {
        //  validate rows
        for (row in 0..8) {
            // ten elements
            val summary = intArrayOf(
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            )
            var col = 0
            while (col < 9) {
                val value = cells[row][col]
                summary[value]++
                col++
            }
            if (!isSummaryOk(summary)) {
                return false
            }
        }

        //  validate columns
        for (col in 0..8) {
            val summary = intArrayOf(
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            )
            var row = 0
            while (row < 9) {
                val value = cells[row][col]
                summary[value]++
                row++
            }
            if (!isSummaryOk(summary)) {
                return false
            }
        }

        // validate quadrant
        var i = 0
        while (i <= 6) {
            var j = 0
            while (j <= 6) {
                if (!isQuadrantValid(i, j)) {
                    return false
                }
                j += 3
            }
            i += 3
        }
        return true
    }

    private fun isQuadrantValid(startRow: Int, startCol: Int): Boolean {
        val summary = intArrayOf(
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        )
        for (i in startRow until startRow + 3) {
            for (j in startCol until startCol + 3) {
                val value = cells[i][j]
                summary[value]++
            }
        }
        return isSummaryOk(summary)
    }

    fun isPlayValid(row: Int, col: Int, n: Int): Boolean {
        //  validate rows
        for (r in 0..8) {
            if (cells[r][col] == n) {
                return false
            }
        }

        //  validate columns
        for (c in 0..8) {
            if (cells[row][c] == n) {
                return false
            }
        }

        //  validate 3x3 matrix
        val br = row / 3
        val bc = col / 3
        for (r in br * 3 until (br + 1) * 3) {
            for (c in bc * 3 until (bc + 1) * 3) {
                if (cells[r][c] == n) {
                    return false
                }
            }
        }
        return true
    }

    override fun toString(): String {
        var boardStr = StringBuilder()
        var cont = 0

        for (r in 0..8) {
            var contx = 0
            for (c in 0..8) {
                boardStr.append(cells[r][c]).append(" ")
                contx++
                if (contx == 3 || contx == 6) {
                    boardStr.append("| ")
                }
            }

            boardStr = StringBuilder(boardStr.toString().trim { it <= ' ' })
            boardStr.append("\n")
            cont++

            if (cont == 3 || cont == 6) {
                boardStr.append("---------------------")
                boardStr.append("\n")
            }
        }
        return boardStr.toString()
    }
}