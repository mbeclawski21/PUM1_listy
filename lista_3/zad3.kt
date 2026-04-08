fun suma(a: List<Int>): Int {
    return a.filter { it > 0 }.sum()
}

fun main() {
    println(suma(listOf(1, -4, 12, 0, -3, 29, -150)))
    // Wynik: 42
}