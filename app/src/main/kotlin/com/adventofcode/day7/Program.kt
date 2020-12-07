package com.adventofcode.day7

import com.adventofcode.IsDayProgram
import java.io.File

@Suppress("unused")
class Program : IsDayProgram<Pair<Int, Int>> {
    override fun run(dayNumber: Int): Pair<Int, Int> {
        val inputs = File(javaClass.getResource("/day7/inputs").toURI()).readText()
        return inputs.countNumberOfBagColorsThatMayContain("shiny gold") to
                inputs.countNumberOfIndividualsBagsRequiredInside("shiny gold")
    }
}

// region Part 1

typealias BagColorContainers = Map<String, List<String>>

fun String.countNumberOfBagColorsThatMayContain(color: String): Int =
        toBagColorContainersMap().findDistinctBagColorContainers(color, emptySet()).let { colors ->
            println("Distinct colors that may contain `$color` → $colors")
            colors.size
        }

fun String.toBagColorContainersMap(): BagColorContainers = lines()
        .mapNotNull {
            "^([a-z ]+?)\\sbags\\scontain\\s(.+)\$".toRegex().matchEntire(it)
        }
        .flatMap { mainMatcher ->
            mainMatcher.groupValues[2].let { bags ->
                "(?:(?:\\d+?)\\s([a-z ]+?)\\sbag)".toRegex().findAll(bags)
                        .map { it.groupValues[1] to mainMatcher.groupValues[1] }
            }
        }
        .groupBy(keySelector = { it.first }, valueTransform = { it.second })

private fun BagColorContainers.findDistinctBagColorContainers(color: String, colors: Set<String>): Set<String> =
        when (val colorContainers = get(color)) {
            null -> colors + color
            else -> colorContainers.flatMap { findDistinctBagColorContainers(it, colors + it) }.toSet()
        }

// endregion

// region Part 2

typealias BagColorQuantities = Map<String, Map<String, Int>>

fun String.countNumberOfIndividualsBagsRequiredInside(color: String): Int =
        toBagColorQuantityContainersMap().countBagColorInnerBags(color)

fun String.toBagColorQuantityContainersMap(): BagColorQuantities = lines()
        .mapNotNull {
            "^([a-z ]+?)\\sbags\\scontain\\s(.+)\$".toRegex().matchEntire(it)
        }
        .map { mainMatcher ->
            mainMatcher.groupValues[1] to mainMatcher.groupValues[2].let { bags ->
                "(?:(\\d+?)\\s([a-z ]+?)\\sbag)".toRegex().findAll(bags)
                        .map { it.groupValues[2] to it.groupValues[1].toInt() }
                        .toMap()
            }
        }
        .filter { (_, quantitiesMap) -> quantitiesMap.isNotEmpty() }
        .toMap()

private fun BagColorQuantities.countBagColorInnerBags(color: String): Int =
        when (val bagColorContents = get(color)) {
            null -> 0
            else -> bagColorContents.map { (childColor, quantity) ->
                quantity + quantity * countBagColorInnerBags(childColor)
            }.sum()
        }

// endregion