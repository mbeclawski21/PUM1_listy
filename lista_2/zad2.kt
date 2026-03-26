val <T> List<T>.head: T
    get() = this.first()

val <T> List<T>.tail: List<T>
    get() = this.drop(1)

fun main() {
    val liczby = listOf(1, 2, 3, 4, 5)
    println(liczby.head) // 1
    println(liczby.tail) // [2, 3, 4, 5]

    // Test na liście napisów
    val napisy = listOf("kot", "pies", "chomik")
    println(napisy.head) // kot
    println(napisy.tail) // [pies, chomik]

    // Test na liście jednoelementowej
    val jeden = listOf(100)
    println(jeden.head) // 100
    println(jeden.tail) // []
}