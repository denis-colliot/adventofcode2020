package com.adventofcode.day15

import com.adventofcode.IsDayProgram
import java.io.File

@Suppress("unused")
class Program : IsDayProgram<Pair<Long, Long>> {
    override fun run(dayNumber: Int): Pair<Long, Long> {
        val inputs = File(javaClass.getResource("/day15/inputs").toURI()).readText()
        return inputs.getMemoryGameValueAtTurn(2020) to inputs.getMemoryGameValueAtTurn(30000000)
    }
}

fun String.getMemoryGameValueAtTurn(turnToStop: Int): Long {
    val startNumbers = split(",").map(String::toLong)
    var lastSpokenNumber = startNumbers.last()

    val gameMemory = startNumbers
        .mapIndexed { turnIndex, spokenNumber -> spokenNumber to mutableListOf(turnIndex + 1) }
        .toMap(mutableMapOf())

    for (turnNumber in (startNumbers.size + 1)..turnToStop) {
        lastSpokenNumber = gameMemory.getNextValue(lastSpokenNumber)
        gameMemory.getOrPut(lastSpokenNumber, ::mutableListOf).add(turnNumber)
    }
    return lastSpokenNumber
}

fun Map<Long, List<Int>>.getNextValue(lastSpokenNumber: Long): Long {
    val valueTurns = getOrDefault(lastSpokenNumber, emptyList())
    return when {
        valueTurns.size <= 1 -> 0 // Never spoken or spoken once
        else -> valueTurns.takeLast(2).let { it.last() - it.first() }.toLong()
    }
}