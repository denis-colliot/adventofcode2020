package com.adventofcode.day5

import com.adventofcode.IsDayProgram
import java.io.File
import kotlin.math.ceil

@Suppress("unused")
class Program : IsDayProgram<Pair<Int, Int>> {
    override fun run(dayNumber: Int): Pair<Int, Int> {
        val inputs = File(javaClass.getResource("/day5/inputs").toURI()).readText()
        val plane = Plane(rowsRange = 0..127, columnsRange = 0..7)
        return plane.findHighestSeatId(inputs) to plane.findMissingSeatId(inputs)
    }
}

data class Plane(val rowsRange: IntRange, val columnsRange: IntRange)
data class Seat(val row: Int, val column: Int, val id: Int = row * 8 + column)

fun Plane.findHighestSeatId(boardingPasses: String): Int =
        boardingPasses.lines().maxOf { boardingPass -> getSeat(boardingPass).id }

fun Plane.getRow(boardingPass: String): Int = boardingPass.getRowOrColumn(range = rowsRange)

fun Plane.getColumn(boardingPass: String): Int = boardingPass.getRowOrColumn(range = columnsRange)

private fun String.getRowOrColumn(index: Int = 0, range: IntRange): Int =
        when (index == length - 1) {
            true -> when (get(index)) {
                'F', 'L' -> range.first
                else -> range.last
            }
            else -> when (get(index)) {
                'F', 'L' -> getRowOrColumn(index + 1, range.first..(range.last - range.middle))
                else -> getRowOrColumn(index + 1, (range.first + range.middle)..range.last)
            }
        }

private val IntRange.middle: Int get() = ceil((last - first) / 2.0).toInt()

fun Plane.getSeat(boardingPass: String): Seat = Seat(
        row = getRow(boardingPass.substring(0..6)),
        column = getColumn(boardingPass.substring(7))
)

fun Plane.findMissingSeatId(boardingPasses: String): Int {
    val expectedColumnSeatsCount = columnsRange.last + 1

    val missingSeatRowColumnsWithSeatIdMap: Map<Int, Int> = boardingPasses.lines()
            .map(::getSeat)
            .groupBy(Seat::row)
            .filter { (_, rowSeats) -> rowSeats.size < expectedColumnSeatsCount }
            .toSortedMap()
            .map { (_, rowSeats) -> rowSeats }
            .drop(1) // Drop very front row with missing seats
            .dropLast(1) // Drop very back row with missing seats
            .flatten()
            .map { it.column to it.id } // Map column index to seat id
            .toMap()

    return when (val missingColumn = columnsRange.sum() - missingSeatRowColumnsWithSeatIdMap.keys.sum()) {
        columnsRange.first -> missingSeatRowColumnsWithSeatIdMap.getValue(columnsRange.first + 1) - 1
        columnsRange.last -> missingSeatRowColumnsWithSeatIdMap.getValue(columnsRange.last - 1) + 1
        else -> missingSeatRowColumnsWithSeatIdMap.getValue(missingColumn - 1) + 1
    }
}