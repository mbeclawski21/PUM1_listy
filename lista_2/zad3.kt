fun <A> isSorted(lst: List<A>, order: (A, A) -> Boolean): Boolean {
    if (lst.size <= 1) return true

    for (i in 0 until lst.size - 1) {
        if (!order(lst[i], lst[i + 1])) {
            return false
        }
    }

    return true
}

fun main() {
    // Przykład 1: Liczby rosnąco
    val test1 = isSorted(listOf(1, 2, 3, 4), { i: Int, j: Int -> i < j })
    println(test1) // true

    // Przykład 2: Same jedynki
    val test2 = isSorted(listOf(1, 1, 1, 1), { i: Int, j: Int -> i == j })
    println(test2) // true

    // Przykład 3: Napisy po pierwszej literze
    val test3 = isSorted(listOf("ahyyhh", "bkjn", "cnn", "duu"), { i: String, j: String -> i.first() < j.first() })
    println(test3) // true

    // Dodatkowy test: Przypadek false (nieposortowane)
    val test4 = isSorted(listOf(5, 2, 8), { i, j -> i < j })
    println(test4) // false
}