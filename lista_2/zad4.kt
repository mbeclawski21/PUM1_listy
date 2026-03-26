fun safeParseAndClassify(input: String?): String {
    return input
        ?.takeIf { it.isNotEmpty() }
        ?.toIntOrNull()
        ?.let {
            if (it % 2 == 0) "PARZYSTA" else "NIEPARZYSTA"
        }
        ?: "BRAK_DANYCH"
}
fun main(){
    println(safeParseAndClassify("10"))    // PARZYSTA
    println(safeParseAndClassify(null))    // BRAK_DANYCH
    println(safeParseAndClassify(""))      // BRAK_DANYCH
    println(safeParseAndClassify("13"))    // NIEPARZYSTA
}