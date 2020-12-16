package com.adventofcode.day12

import com.adventofcode.IsDayProgram
import java.io.File
import kotlin.math.abs

@Suppress("unused")
class Program : IsDayProgram<Pair<Int, Int>> {
    override fun run(dayNumber: Int): Pair<Int, Int> {
        val inputs = File(javaClass.getResource("/day12/inputs").toURI()).readText()
        return inputs.getManhattanDistance() to inputs.getManhattanDistanceWithActualActionsMeanings()
    }
}

// region Part 1

data class ShipPart1(val degrees: Int, val north: Int = 0, val south: Int = 0, val east: Int = 0, val west: Int = 0)

fun String.getManhattanDistance(startDegrees: Int = 90): Int {
    var shipMove = ShipPart1(degrees = startDegrees)
    lines().forEach { instruction -> shipMove = shipMove.readInstruction(instruction) }
    return abs(shipMove.north - shipMove.south) + abs(shipMove.east - shipMove.west)
}

fun ShipPart1.readInstruction(instruction: String): ShipPart1 {
    val (action, value) = (instruction.first() to instruction.substring(1).toInt())
    return when (action) {
        'N' -> copy(north = north + value)
        'S' -> copy(south = south + value)
        'E' -> copy(east = east + value)
        'W' -> copy(west = west + value)
        'L' -> copy(degrees = degrees - value)
        'R' -> copy(degrees = degrees + value)
        else -> readInstruction("$currentDirection$value") // `F`
    }
}

val ShipPart1.currentDirection: Char
    get() = when (degrees.modulo360) {
        0 -> 'N'
        90 -> 'E'
        180 -> 'S'
        270 -> 'W'
        else -> error("Cannot convert angle `$this` into direction")
    }

val Int.modulo360: Int get() = (((this % 360) + 360) % 360)

// endregion

// region Part 2

data class Position(val north: Int = 0, val east: Int = 0, val south: Int = 0, val west: Int = 0)

data class ShipPart2(val position: Position, val wayPoint: Map<Int, Int>)

fun String.getManhattanDistanceWithActualActionsMeanings(): Int {
    var ship = ShipPart2(position = Position(), wayPoint = mapOf(90 to 10, 0 to 1))
    lines().forEach { instruction -> ship = ship.readInstructionWithActualActionMeaning(instruction) }
    return with(ship.position) { abs(north - south) + abs(east - west) }
}

fun ShipPart2.readInstructionWithActualActionMeaning(instruction: String): ShipPart2 {
    val (action, value) = (instruction.first() to instruction.substring(1).toInt())
    return when (action) {
        'N' -> copy(wayPoint = wayPoint.toMutableMap().also { it[0] = it.getOrDefault(0, 0) + value })
        'E' -> copy(wayPoint = wayPoint.toMutableMap().also { it[90] = it.getOrDefault(90, 0) + value })
        'S' -> copy(wayPoint = wayPoint.toMutableMap().also { it[180] = it.getOrDefault(180, 0) + value })
        'W' -> copy(wayPoint = wayPoint.toMutableMap().also { it[270] = it.getOrDefault(270, 0) + value })
        'L' -> copy(wayPoint = wayPoint.mapKeys { (degree, _) -> (degree - value).modulo360 })
        'R' -> copy(wayPoint = wayPoint.mapKeys { (degree, _) -> (degree + value).modulo360 })
        else -> copy(
            position = Position(
                north = position.north + wayPoint.getOrDefault(0, 0) * value,
                east = position.east + wayPoint.getOrDefault(90, 0) * value,
                south = position.south + wayPoint.getOrDefault(180, 0) * value,
                west = position.west + wayPoint.getOrDefault(270, 0) * value
            )
        )
    }
}

// endregion