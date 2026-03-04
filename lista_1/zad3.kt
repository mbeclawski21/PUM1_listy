fun printPascal(num_rows: Int): String {
    require(num_rows > 0)
    val triangle = mutableListOf<List<Int>>()
    triangle.add(listOf(1))

    for (i in 1 until num_rows) {
        val currentRow = mutableListOf<Int>()
        for (j in 0..i) {
            if (j == 0 || j == i) {
                currentRow.add(1)
            } else {
                val prevRow = triangle[i - 1]
                val sum = prevRow[j - 1] + prevRow[j]
                currentRow.add(sum)
            }
        }
        triangle.add(currentRow)
    }

    return triangle.mapIndexed { index, row ->
        val padding = " ".repeat(num_rows - index - 1)
        padding + row.joinToString(" ")
    }.joinToString("\n")
}

fun main() {
    val rows = 5
    println(printPascal(rows))
}