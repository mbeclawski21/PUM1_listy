fun isPalindrome(word: String): Boolean{
    return word.lowercase() == word.lowercase().reversed()
}
fun main() {
    val word = "bbbc"
    if (isPalindrome(word)){
        println("to słowo jest palindromem")
    } else {
        println("to słowo nie jest palindromem")
    }
}