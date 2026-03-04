fun check_prime(n: Int): Boolean {
    if (n < 2) return false
    for (i in 2 until n) {
        if (n % i == 0) {
            return false
        }
    }
    return true
}

fun main(){
    val n = 17
    println(check_prime(n))
}