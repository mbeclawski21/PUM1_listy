import java.time.LocalDate
import java.time.Month
import java.util.SortedMap
import kotlin.random.Random

enum class CostType(val costType: String) {
    REFUELING("Tankowanie"),
    SERVICE("Serwis"),
    PARKING("Parking"),
    INSURANCE("Ubezpieczenie"),
    TICKET("Mandat")
}

data class Cost(
    val type: CostType,
    val date: LocalDate,
    val amount: Int
)

object DataProvider {
    val generalCosts = List(5) {
        Cost(
            CostType.entries[Random.nextInt(CostType.entries.size)],
            LocalDate.of(
                2025,
                Random.nextInt(1, 13),
                Random.nextInt(1, 28)
            ),
            Random.nextInt(5000)
        )
    }
}

fun groupedCostMap(costs: List<Cost>): SortedMap<Month, List<Cost>> = costs
    .groupBy { it.date.month }
    .toSortedMap()

fun main() {
    println("--- Wygenerowana lista kosztów (nieposortowana) ---")
    DataProvider.generalCosts.forEach { println(it) }

    println("\n--- Wynik funkcji groupedCostMap (Zad 1) ---")
    val result = groupedCostMap(DataProvider.generalCosts)

    println(result)
}