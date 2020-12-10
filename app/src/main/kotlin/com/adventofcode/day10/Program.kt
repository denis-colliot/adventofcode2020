package com.adventofcode.day10

import com.adventofcode.IsDayProgram
import com.adventofcode.toNumbersList
import java.io.File

@Suppress("unused")
class Program : IsDayProgram<Long> {
    override fun run(dayNumber: Int): Long {
        val inputs = File(javaClass.getResource("/day10/inputs").toURI()).readText()
        return inputs.toNumbersList().findJoltageDistributionProduct()
    }
}

fun List<Long>.findJoltageDistributionProduct(): Long {
    return findJoltageDistribution().let { distribution ->
        distribution.getOrDefault(3, 0) * distribution.getOrDefault(1, 0)
    }
}

fun List<Long>.findJoltageDistribution(): Map<Long, Long> {
    val distribution = mutableMapOf<Long, Long>()
    toMutableList()
            .apply {
                add(0) // charging outlet
                maxOrNull()?.let { max -> add(max + 3) } // device's built-in adapter
            }
            .sorted()
            .reduce { accumulator, rating ->
                val difference = rating - accumulator
                distribution[difference] = distribution.getOrDefault(difference, 0) + 1
                rating
            }
    return distribution.also { println("Distribution → $distribution") }
}

fun List<Long>.countPossibleArrangements(): Long {
    return findJoltageDistribution().let { distribution ->
        distribution.getOrDefault(3, 0) * distribution.getOrDefault(1, 0)
    }
}