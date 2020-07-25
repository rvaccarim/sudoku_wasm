import kotlin.system.getTimeMillis

fun main(args: Array<String>) {
    val startTime = getTimeMillis()

    val longest: List<String> = listOf(
        "0 0 0 0 0 0 0 0 0",
        "0 0 0 0 0 3 0 8 5",
        "0 0 1 0 2 0 0 0 0",
        "0 0 0 5 0 7 0 0 0",
        "0 0 4 0 0 0 1 0 0",
        "0 9 0 0 0 0 0 0 0",
        "5 0 0 0 0 0 0 7 3",
        "0 0 2 0 1 0 0 0 0",
        "0 0 0 0 4 0 0 0 9"
    )

    val board = Board(longest)
    println(board.toString())

    val solver = Solver()
    val solved: Board? = solver.solve(board)

    println("--------------------------------")
    println(solved.toString())

    val endTime = (getTimeMillis() - startTime) / 1000F
    println("Solved in $endTime seconds");

}
