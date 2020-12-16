package com.adventofcode.day12

import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramTest {

    @Test
    fun `should return 25 as Manhattan distance for given input`() {
        // Given
        val inputs = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent()

        // When
        val result = inputs.getManhattanDistance()

        // Then
        assertEquals(25, result)
    }

    @Test
    fun `should return proper direction for given degrees`() {
        assertEquals('N', ShipPart1(degrees = 0).currentDirection)
        assertEquals('E', ShipPart1(degrees = 90).currentDirection)
        assertEquals('S', ShipPart1(degrees = 180).currentDirection)
        assertEquals('W', ShipPart1(degrees = 270).currentDirection)
        assertEquals('W', ShipPart1(degrees = -90).currentDirection) // 270
        assertEquals('S', ShipPart1(degrees = -180).currentDirection) // 180
    }

    // region Part 2


    @Test
    fun `should return 286 as Manhattan distance for given input with actual action meanings`() {
        // Given
        val inputs = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent()

        // When
        val result = inputs.getManhattanDistanceWithActualActionsMeanings()

        // Then
        assertEquals(286, result)
    }

    // endregion

}