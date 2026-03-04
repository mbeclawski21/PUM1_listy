import kotlin.math.pow

fun is_armstrong(n: Int): Boolean {
    val str = n.toString()
    val power = str.length
    var sum = 0

    for (char in str) {
        val digit = char.digitToInt()
        sum += digit.toDouble().pow(power).toInt()
    }
    if (sum == n) {
        return true
    }
    else {
        return false
    }
}

fun main() {
    val n = 153
    println(is_armstrong(n))
}