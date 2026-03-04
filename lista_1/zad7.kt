fun sum_even(n: Int): Int {
    var sum = 0
    for (i in 1..n) {
        if (i % 2 == 0) sum += i
    }
    return sum
}

fun main(){
    println(sum_even(100))
}