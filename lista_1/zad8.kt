fun countVowels(word: String): Int {
    var count = 0
    val vowels = "aeiouy"
    for (char in word.lowercase()){
        if (vowels.contains(char)){
            count++
        }
    }
    return count
}

fun main(){
    val word = "Programowanie"
    println(countVowels(word))
}