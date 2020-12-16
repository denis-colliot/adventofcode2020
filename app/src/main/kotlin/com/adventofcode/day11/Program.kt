package com.adventofcode.day11

import com.adventofcode.IsDayProgram
import java.io.File

@Suppress("unused")
class Program : IsDayProgram<Pair<Int, Int>> {
    override fun run(dayNumber: Int): Pair<Int, Int> {
        val inputs = File(javaClass.getResource("/day11/inputs").toURI()).readText()
        return inputs.countEmptySeatsAfterRulesApplication(seatRulesPart1, neighborsProviderPart1) to
                inputs.countEmptySeatsAfterRulesApplication(seatRulesPart2, neighborsProviderPart2)
    }
}

typealias Seat = Char
typealias Seats = List<String>
typealias SeatRules = (Seat, List<Seat>) -> Seat
typealias SeatNeighborsProvider = (Seats, Coordinates) -> List<Seat>

data class Coordinates(val x: Int, val y: Int)

sealed class Direction(val x: Int, val y: Int) {
    object NorthWest : Direction(-1, -1)
    object North : Direction(0, -1)
    object NorthEast : Direction(+1, -1)
    object West : Direction(-1, 0)
    object East : Direction(+1, 0)
    object SouthWest : Direction(-1, +1)
    object South : Direction(0, +1)
    object SouthEast : Direction(+1, +1)
}

fun String.countEmptySeatsAfterRulesApplication(rules: SeatRules, neighborsProvider: SeatNeighborsProvider): Int =
    lines().applyRules(rules, neighborsProvider).flatMap(String::toList).count { it == '#' }

fun Seats.applyRules(rules: SeatRules, neighborsProvider: SeatNeighborsProvider): Seats {
    val newSeats = mapIndexed { yIndex, line ->
        line.mapIndexed { xIndex, seat ->
            rules.invoke(seat, neighborsProvider.invoke(this, Coordinates(xIndex, yIndex)))
        }.joinToString(separator = "")
    }
    return if (this == newSeats) {
        newSeats
    } else {
        newSeats.applyRules(rules, neighborsProvider)
    }
}

fun Seats.getNeighborInDirection(coordinates: Coordinates, direction: Direction): Seat? {
    val neighborCoordinates = Coordinates(x = coordinates.x + direction.x, y = coordinates.y + direction.y)
    return when (val neighbor = getOrNull(neighborCoordinates.y)?.getOrNull(neighborCoordinates.x)) {
        null -> null
        '#', 'L' -> neighbor
        else -> getNeighborInDirection(neighborCoordinates, direction)
    }
}

val neighborsProviderPart1: SeatNeighborsProvider = { seats, seatCoordinates ->
    arrayOf(
        seats.getOrNull(seatCoordinates.y - 1)?.getOrNull(seatCoordinates.x - 1),
        seats.getOrNull(seatCoordinates.y - 1)?.getOrNull(seatCoordinates.x),
        seats.getOrNull(seatCoordinates.y - 1)?.getOrNull(seatCoordinates.x + 1),
        seats[seatCoordinates.y].getOrNull(seatCoordinates.x - 1),
        seats[seatCoordinates.y].getOrNull(seatCoordinates.x + 1),
        seats.getOrNull(seatCoordinates.y + 1)?.getOrNull(seatCoordinates.x - 1),
        seats.getOrNull(seatCoordinates.y + 1)?.getOrNull(seatCoordinates.x),
        seats.getOrNull(seatCoordinates.y + 1)?.getOrNull(seatCoordinates.x + 1),
    ).filterNotNull()
}

val neighborsProviderPart2: SeatNeighborsProvider = { seats, seatCoordinates ->
    arrayOf(
        seats.getNeighborInDirection(seatCoordinates, Direction.NorthWest),
        seats.getNeighborInDirection(seatCoordinates, Direction.North),
        seats.getNeighborInDirection(seatCoordinates, Direction.NorthEast),
        seats.getNeighborInDirection(seatCoordinates, Direction.West),
        seats.getNeighborInDirection(seatCoordinates, Direction.East),
        seats.getNeighborInDirection(seatCoordinates, Direction.SouthWest),
        seats.getNeighborInDirection(seatCoordinates, Direction.South),
        seats.getNeighborInDirection(seatCoordinates, Direction.SouthEast)
    ).filterNotNull()
}

val seatRulesPart1: SeatRules = { seat, neighbors ->
    when {
        seat == 'L' && neighbors.none { it == '#' } -> '#' // Seats becomes occupied
        seat == '#' && neighbors.count { it == '#' } >= 4 -> 'L' // Seats becomes empty
        else -> seat // Seat remains unchanged
    }
}

val seatRulesPart2: SeatRules = { seat, neighbors ->
    when {
        seat == 'L' && neighbors.none { it == '#' } -> '#' // Seats becomes occupied
        seat == '#' && neighbors.count { it == '#' } >= 5 -> 'L' // Seats becomes empty
        else -> seat // Seat remains unchanged
    }
}