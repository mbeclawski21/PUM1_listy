fun check_perfect_num(n: Int): String {
    var sum = 0
    for (i in 1..n - 1) {
        if (n % i == 0)
            sum += i
    }
    return when {
        sum == n -> "perfect"
        sum < n -> "deficient"
        else -> "abundant"
    }
}

fun main() {
    val n = 28
    println(check_perfect_num(n))
}