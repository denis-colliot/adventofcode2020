package com.adventofcode.day9

import com.adventofcode.IsDayProgram
import com.adventofcode.toNumbersList
import java.io.File

@Suppress("unused")
class Program : IsDayProgram<Pair<Long, Long>> {
    override fun run(dayNumber: Int): Pair<Long, Long> {
        val inputs = File(javaClass.getResource("/day9/inputs").toURI()).readText()
        val preamble = 25
        return inputs.toNumbersList().let { numbers ->
            numbers.findFirstInvalidNumber(preamble) to numbers.findFirstInvalidNumberKey(preamble)
        }
    }
}

// region Part 1

fun List<Long>.findFirstInvalidNumber(preamble: Int): Long {
    return asSequence()
            .drop(preamble)
            .filterIndexed { index, number -> !number.isValidNumber(subList(index, preamble + index)) }
            .first()
}

fun Long.isValidNumber(previousNumbers: List<Long>): Boolean {
    val (firstPrevious, otherPrevious) = previousNumbers.firstOrNull() to previousNumbers.drop(1)
    return when {
        firstPrevious == null -> false
        firstPrevious > this -> isValidNumber(otherPrevious)
        else -> otherPrevious.any { it + firstPrevious == this } || isValidNumber(otherPrevious)
    }
}

// endregion

// region Part 2

fun List<Long>.findFirstInvalidNumberKey(preamble: Int): Long {
    val invalidNumber = findFirstInvalidNumber(preamble)
    return asSequence()
            .mapIndexedNotNull { index, _ -> subList(index, size).startRangeWhichSumEqualsTo(invalidNumber) }
            .firstOrNull()
            ?.let { range -> range.minOrNull()?.plus(range.maxOrNull() ?: 0) }
            ?: error("Cannot find range which sum equals to `$invalidNumber`")
}

fun List<Long>.startRangeWhichSumEqualsTo(searchedSum: Long, size: Int = 1): List<Long>? {
    val sum = subList(0, size).sum()
    return when {
        sum > searchedSum -> null
        sum == searchedSum -> subList(0, size)
        else -> startRangeWhichSumEqualsTo(searchedSum, size + 1)
    }
}

// endregion