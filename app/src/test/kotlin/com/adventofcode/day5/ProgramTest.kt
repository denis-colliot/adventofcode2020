/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.adventofcode.day5

import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramTest {

    @Test
    fun `should return proper seat row from input`() {
        // Given
        val plane = Plane(rowsRange = 0..127, columnsRange = 0..7)

        // Then
        assertEquals(44, plane.getRow("FBFBBFF"))
        assertEquals(70, plane.getRow("BFFFBBF"))
        assertEquals(14, plane.getRow("FFFBBBF"))
        assertEquals(102, plane.getRow("BBFFBBF"))
    }

    @Test
    fun `should return proper seat column from input`() {
        // Given
        val plane = Plane(rowsRange = 0..127, columnsRange = 0..7)

        // Then
        assertEquals(5, plane.getColumn("RLR"))
        assertEquals(7, plane.getColumn("RRR"))
        assertEquals(4, plane.getColumn("RLL"))
    }

    @Test
    fun `should return proper seat from input`() {
        // Given
        val plane = Plane(rowsRange = 0..127, columnsRange = 0..7)

        // Then
        assertEquals(Seat(44, 5), plane.getSeat("FBFBBFFRLR"))
        assertEquals(Seat(70, 5), plane.getSeat("BFFFBBFRLR"))
        assertEquals(Seat(70, 7), plane.getSeat("BFFFBBFRRR"))
        assertEquals(Seat(14, 7), plane.getSeat("FFFBBBFRRR"))
        assertEquals(Seat(102, 4), plane.getSeat("BBFFBBFRLL"))
    }

    @Test
    fun `should return proper seat id from input`() {
        // Given
        val plane = Plane(rowsRange = 0..127, columnsRange = 0..7)

        // Then
        assertEquals(357, plane.getSeat("FBFBBFFRLR").id)
        assertEquals(565, plane.getSeat("BFFFBBFRLR").id)
        assertEquals(567, plane.getSeat("BFFFBBFRRR").id)
        assertEquals(119, plane.getSeat("FFFBBBFRRR").id)
        assertEquals(820, plane.getSeat("BBFFBBFRLL").id)
    }

    @Test
    fun `should return seat with highest id from input`() {
        // Given
        val plane = Plane(rowsRange = 0..127, columnsRange = 0..7)
        val boardingPasses = """
            FBFBBFFRLR
            BFFFBBFRLR
            BBFFBBFRLL
            BFFFBBFRRR
            FFFBBBFRRR
        """.trimIndent()

        // Then
        assertEquals(820, plane.findHighestSeatId(boardingPasses))
    }

}