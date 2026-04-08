fun perm(list: List<Int>): List<List<Int>> {
    if (list.isEmpty()) return listOf(emptyList())

    return list.flatMap { element ->
        val rest = list - element
        perm(rest).map { permutation ->
            listOf(element) + permutation
        }
    }
}

fun main() {
    println(perm(listOf(1, 2, 3)))
    // Wynik: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
}