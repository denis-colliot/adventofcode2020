package com.adventofcode.day14

import com.adventofcode.IsDayProgram
import java.io.File

@Suppress("unused")
class Program : IsDayProgram<Pair<Long, Long>> {
    override fun run(dayNumber: Int): Pair<Long, Long> {
        val inputs = File(javaClass.getResource("/day14/inputs").toURI()).readText()
        return inputs.sumAllMemoryValues(memoryHandlerPart1) to inputs.sumAllMemoryValues(memoryHandlerPart2)
    }
}

typealias MemoryHandler = (Long, Long, String) -> Map<Long, Long>

fun String.sumAllMemoryValues(memoryHandler: MemoryHandler = memoryHandlerPart2): Long {
    val memory = mutableMapOf<Long, Long>()
    var currentMask = ""
    lines().forEach { line ->
        when {
            line.startsWith("mask") -> "mask = (.*)".toRegex().matchEntire(line)?.let { matchResult ->
                currentMask = matchResult.groupValues[1]
            }
            line.startsWith("mem") -> "mem\\[(\\d+)] = (\\d+)".toRegex().matchEntire(line)?.let { matchResult ->
                val address = matchResult.groupValues[1].toLong()
                val value = matchResult.groupValues[2].toLong()
                memoryHandler(address, value, currentMask).forEach { (address, value) -> memory[address] = value }
            }
        }
    }
    return memory.values.sum()
}

val memoryHandlerPart1: MemoryHandler = { address, value, mask ->
    mapOf(address to value.applyMask(mask))
}

val memoryHandlerPart2: MemoryHandler = { address, value, mask ->
    address.toBytes()
        .decodeAddresses(mask)
        .map { decodedAddress -> decodedAddress to value }
        .toMap()
}

fun Long.applyMask(mask: String): Long =
    toBytes()
        .mapIndexed { index, bit -> if (mask[index] != 'X') mask[index] else bit }
        .joinToString(separator = "")
        .bytesToLong()

fun String.decodeAddresses(mask: String, index: Int = 0): List<Long> =
    when (mask.getOrNull(index)) {
        null -> listOf(bytesToLong())
        'X' -> replaceRange(index, index + 1, "0").decodeAddresses(mask, index + 1) +
                replaceRange(index, index + 1, "1").decodeAddresses(mask, index + 1)
        '1' -> replaceRange(index, index + 1, "1").decodeAddresses(mask, index + 1)
        else -> decodeAddresses(mask, index + 1)
    }

private fun Long.toBytes(): String = toString(radix = 2).padStart(length = 36, padChar = '0')

private fun String.bytesToLong(): Long = toLong(radix = 2)