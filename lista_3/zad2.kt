fun addToBoolean(): Map<Int, Boolean> {
    val resultMap = mutableMapOf<Int, Boolean>()

    for (i in 1..20) {
        resultMap[i] = (i % 2 == 0)
    }

    return resultMap
}

fun main() {
    println(addToBoolean())
}