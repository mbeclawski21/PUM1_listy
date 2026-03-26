fun check(N: Int, list: List<Int>): Int {
    for (i in N until list.size) {
        val current = list[i]
        val preamble = list.subList(i - N, i)

        val canBeFormed = preamble.any { a ->
            preamble.any { b ->
                a != b && (a + b == current)
            }
        }

        if (!canBeFormed) {
            return current
        }
    }
    return -1
}

fun main() {
    println(check(2, listOf(1, 2, 3, 4, 5, 6)))

    val longList = listOf(
        35, 25, 15, 25, 47, 40, 62, 55, 65, 95,
        102, 117, 150, 182, 127, 219, 299, 277, 309, 576
    )
    println(check(5, longList))
}