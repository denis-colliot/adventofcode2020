package com.adventofcode.day11

import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramTest {

    @Test
    fun `should return 37 empty seats after part 1 rules application`() {
        // Given
        val inputs = """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """.trimIndent()

        // When
        val result = inputs.countEmptySeatsAfterRulesApplication(seatRulesPart1, neighborsProviderPart1)

        // Then
        assertEquals(37, result)
    }


    @Test
    fun `should return 26 empty seats after part 2 rules application`() {
        // Given
        val inputs = """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """.trimIndent()

        // When
        val result = inputs.countEmptySeatsAfterRulesApplication(seatRulesPart2, neighborsProviderPart2)

        // Then
        assertEquals(26, result)
    }

    @Test
    fun `should return neighbor in direction`() {
        // Given
        val inputs = """
            .............
            .L.L.#.#.#.#.
            .............
        """.trimIndent().lines()

        // When/Then
        assertEquals('L', inputs.getNeighborInDirection(Coordinates(x = 1, y = 1), Direction.East))
        assertEquals('#', inputs.getNeighborInDirection(Coordinates(x = 3, y = 1), Direction.East))
        assertEquals('L', inputs.getNeighborInDirection(Coordinates(x = 3, y = 1), Direction.West))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 3, y = 1), Direction.NorthWest))

        // (12,1) → last `.` on second row
        assertEquals('#', inputs.getNeighborInDirection(Coordinates(x = 12, y = 1), Direction.West))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 12, y = 1), Direction.North))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 12, y = 1), Direction.South))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 12, y = 1), Direction.East))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 12, y = 1), Direction.NorthWest))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 12, y = 1), Direction.NorthEast))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 12, y = 1), Direction.SouthEast))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 12, y = 1), Direction.SouthWest))
    }

    @Test
    fun `should return null when finding neighbors in all directions for seat (3,3)`() {
        // Given
        val inputs = """
            .##.##.
            #.#.#.#
            ##...##
            ...L...
            ##...##
            #.#.#.#
            .##.##.
        """.trimIndent().lines()

        // When/Then
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 3, y = 3), Direction.West))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 3, y = 3), Direction.North))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 3, y = 3), Direction.South))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 3, y = 3), Direction.East))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 3, y = 3), Direction.NorthWest))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 3, y = 3), Direction.NorthEast))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 3, y = 3), Direction.SouthEast))
        assertEquals(null, inputs.getNeighborInDirection(Coordinates(x = 3, y = 3), Direction.SouthWest))
    }

    @Test
    fun `should find 8 occupied seats as neighbors for empty seat with coordinates (3,4)`() {
        // Given
        val inputs = """
            .......#.
            ...#.....
            .#.......
            .........
            ..#L....#
            ....#....
            .........
            #........
            ...#.....
        """.trimIndent().lines()

        // When
        val result = neighborsProviderPart2.invoke(inputs, Coordinates(x = 3, y = 4))

        // Then
        assertEquals(listOf('#', '#', '#', '#', '#', '#', '#', '#'), result)
    }

}