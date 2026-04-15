import java.time.LocalDate
import java.time.Month
import java.util.SortedMap
import kotlin.random.Random

// enum class CostType(val costType: String) {
//     REFUELING("Tankowanie"),
//     SERVICE("Serwis"),
//     PARKING("Parking"),
//     INSURANCE("Ubezpieczenie"),
//     TICKET("Mandat")
// }
//
// data class Cost(
//     val type: CostType,
//     val date: LocalDate,
//     val amount: Int
// )
//
// object DataProvider {
//     val generalCosts = List(5) {
//         Cost(
//             CostType.entries[Random.nextInt(CostType.entries.size)],
//             LocalDate.of(
//                 2025,
//                 Random.nextInt(1, 13),
//                 Random.nextInt(1, 28)
//             ),
//             Random.nextInt(5000)
//         )
//     }
// }

fun printCosts(costs: List<Cost>) {
    costs.groupBy { it.date.month }
        .toSortedMap()
        .forEach { (month, monthCosts) ->
            println(month.name)
            monthCosts.sortedBy { it.date }
                .forEach { cost ->
                    val day = cost.date.dayOfMonth.toString().padStart(2, '0')
                    println("$day ${cost.type.name} ${cost.amount} zł")
                }
        }
}

fun main() {
    println("--- Wynik funkcji printCosts (Zad 2) ---")
    printCosts(DataProvider.generalCosts)
}