data class UserInput(val name: String?, val email: String?, val age: String?)

data class UserProfile(
    var name: String = "",
    var email: String = "",
    var age: Int = 0,
    var isAdult: Boolean = false
)

fun buildProfile(input: UserInput?, logs: MutableList<String>): UserProfile? {
    val verifiedInput = input ?: return run {
        logs.add("Input is null")
        null
    }

    val verifiedName = verifiedInput.name?.trim()?.let {
        if (it.length < 3) {
            logs.add("Name too short")
            return null
        }
        it
    } ?: return run {
        logs.add("Name is null")
        null
    }

    val verifiedEmail = verifiedInput.email?.trim()?.lowercase()?.let {
        if (!it.contains("@")) {
            logs.add("Invalid email")
            return null
        }
        it
    } ?: return run {
        logs.add("Email is null")
        null
    }

    val verifiedAge = verifiedInput.age?.let {
        it.toIntOrNull() ?: run {
            logs.add("Age is not a number")
            return null
        }
    } ?: return run {
        logs.add("Age is null")
        null
    }

    return UserProfile().apply {
        name = verifiedName
        email = verifiedEmail
        age = verifiedAge
        isAdult = age >= 18
    }.also {
        logs.add("Profile created for ${it.email}")
    }
}

fun main() {
    val logs = mutableListOf<String>()

    // 1. Przypadek poprawny
    println("--- Test 1: Poprawne dane ---")
    val user1 = UserInput("Jan", "JAN@example.com", "25")
    val profile1 = buildProfile(user1, logs)
    println("Wynik: $profile1")
    println("Logi: $logs")

    logs.clear()

    // 2. Błędny wiek (nie jest liczbą)
    println("\n--- Test 2: Błędny wiek ---")
    val user2 = UserInput("Anna", "anna@poczta.pl", "dwadzieścia")
    val profile2 = buildProfile(user2, logs)
    println("Wynik: $profile2")
    println("Logi: $logs")

    logs.clear()

    // 3. Zbyt krótkie imię
    println("\n--- Test 3: Krótkie imię ---")
    val user3 = UserInput("Al", "al@test.pl", "30")
    val profile3 = buildProfile(user3, logs)
    println("Wynik: $profile3")
    println("Logi: $logs")

    logs.clear()

    // 4. Wejście jako null
    println("\n--- Test 4: Input null ---")
    val profile4 = buildProfile(null, logs)
    println("Wynik: $profile4")
    println("Logi: $logs")
}