import java.time.LocalDate
import java.time.Month
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

sealed class MonthlyCostStatus {
    data object NoCosts : MonthlyCostStatus()
    data class WithinLimit(val total: Int) : MonthlyCostStatus()
    data class OverLimit(val total: Int, val exceededBy: Int) : MonthlyCostStatus()
}

fun classifyMonthlyCosts(costs: List<Cost>, month: Month, limit: Int): MonthlyCostStatus {
    val filteredCosts = costs.filter { it.date.month == month } // tylko te ktorych miesiac sie zgadza

    if (filteredCosts.isEmpty()) {
        return MonthlyCostStatus.NoCosts
    }

    val total = filteredCosts.sumOf { it.amount }

    return if (total <= limit) { // porownanie sumy z limitem
        MonthlyCostStatus.WithinLimit(total)
    } else {
        MonthlyCostStatus.OverLimit(total, total - limit)
    }
}

fun main() {
    val costs = listOf(
        Cost(CostType.REFUELING, LocalDate.of(2025, 1, 10), 300),
        Cost(CostType.PARKING, LocalDate.of(2025, 1, 12), 50),
        Cost(CostType.SERVICE, LocalDate.of(2025, 2, 4), 1200)
    )

    println(classifyMonthlyCosts(costs, Month.JANUARY, 400))
    println(classifyMonthlyCosts(costs, Month.FEBRUARY, 1000))
    println(classifyMonthlyCosts(costs, Month.MARCH, 500))
}
