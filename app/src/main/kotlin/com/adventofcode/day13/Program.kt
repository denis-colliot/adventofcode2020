package com.adventofcode.day13

import com.adventofcode.IsDayProgram
import java.io.File
import java.math.BigInteger

@Suppress("unused")
class Program : IsDayProgram<Pair<Int, BigInteger>> {
    override fun run(dayNumber: Int): Pair<Int, BigInteger> {
        val inputs = File(javaClass.getResource("/day13/inputs").toURI()).readText()
        return inputs.findEarliestBusIdMultipliedByWaitingMinutes() to inputs.lines().last().findEarliestTimestamp()
    }
}

// region Part 1

fun String.findEarliestBusIdMultipliedByWaitingMinutes(): Int {
    val (timestamp, busIds) = lines().let { (firstLine, secondLine) ->
        firstLine.toInt() to secondLine.split(",").mapNotNull { it.toIntOrNull() }
    }
    return busIds.findEarliestBus(timestamp).let { earliestBus ->
        println("Earliest bus is: $earliestBus")
        earliestBus.busId * (earliestBus.timestamp - timestamp)
    }
}

data class EarliestBus(val busId: Int, val timestamp: Int)

fun List<Int>.findEarliestBus(timestamp: Int): EarliestBus =
    when (val result = find { busId -> timestamp % busId == 0 }) {
        null -> findEarliestBus(timestamp + 1)
        else -> EarliestBus(result, timestamp)
    }

// endregion

// region Part 2

/**
 * [http://mathafou.free.fr/themes/kcongr.html]
 */
fun String.findEarliestTimestamp(): BigInteger {
    val map = split(",")
        .mapIndexedNotNull { index, busIdOrX -> busIdOrX.toIntOrNull()?.let { it.toBigInteger() to index } }
        .toMap()

    return map
        .map { (busId, delta) ->
            val otherIdsProduct = map.keys.toMutableList()
                .apply { remove(busId) }
                .reduce { acc, i -> i.multiply(acc) }
            Triple(busId, busId.minus(delta.toBigInteger()).mod(busId), otherIdsProduct)
        }
        .map { (busId, congruence, otherIdsProduct) ->
            val oppositeModulo = (otherIdsProduct.mod(busId)).findOppositeModulo(busId)
            otherIdsProduct.multiply(oppositeModulo.toBigInteger()).multiply(congruence)
        }
        .reduce { acc, bigInteger -> acc.add(bigInteger) }
        .let {
            val products = map.keys.toMutableList().reduce { acc, i -> acc * i }
            it.mod(products)
        }
}

fun BigInteger.findOppositeModulo(modulo: BigInteger, value: Int = 1): Int =
    if (multiply(value.toBigInteger()).mod(modulo) == 1.toBigInteger()) value
    else findOppositeModulo(modulo, value + 1)

// endregion