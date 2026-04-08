fun evenPositiveSquare(list: List<Int>): List<Int> {
    return list
        .filterIndexed { index, value -> index % 2 != 0 && value > 0 }
        .map { it * it }
}

fun main() {
    println(evenPositiveSquare(listOf(1, 2, 3, 5, -6, -1, -1, 2, 3)))
    // Wynik: [4, 25, 4]
}