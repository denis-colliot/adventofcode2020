package com.adventofcode.day15

import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramTest {

    // region Part 1

    @Test
    fun `should return 436 as 2020th value at memory game`() {
        // Given
        val startNumbers = "0,3,6"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(2020)

        // Then
        assertEquals(436, result)
    }

    @Test
    fun `should return 1 as 2020th value at memory game`() {
        // Given
        val startNumbers = "1,3,2"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(2020)

        // Then
        assertEquals(1, result)
    }

    @Test
    fun `should return 10 as 2020th value at memory game`() {
        // Given
        val startNumbers = "2,1,3"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(2020)

        // Then
        assertEquals(10, result)
    }

    @Test
    fun `should return 27 as 2020th value at memory game`() {
        // Given
        val startNumbers = "1,2,3"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(2020)

        // Then
        assertEquals(27, result)
    }

    @Test
    fun `should return 78 as 2020th value at memory game`() {
        // Given
        val startNumbers = "2,3,1"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(2020)

        // Then
        assertEquals(78, result)
    }

    @Test
    fun `should return 438 as 2020th value at memory game`() {
        // Given
        val startNumbers = "3,2,1"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(2020)

        // Then
        assertEquals(438, result)
    }

    @Test
    fun `should return 1836 as 2020th value at memory game`() {
        // Given
        val startNumbers = "3,1,2"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(2020)

        // Then
        assertEquals(1836, result)
    }

    // endregion

    // region Part 2

    @Test
    fun `should return 175594 as 30000000th value at memory game`() {
        // Given
        val startNumbers = "0,3,6"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(30000000)

        // Then
        assertEquals(175594, result)
    }

    @Test
    fun `should return 2578 as 30000000th value at memory game`() {
        // Given
        val startNumbers = "1,3,2"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(30000000)

        // Then
        assertEquals(2578, result)
    }

    @Test
    fun `should return 3544142 as 30000000th value at memory game`() {
        // Given
        val startNumbers = "2,1,3"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(30000000)

        // Then
        assertEquals(3544142, result)
    }

    @Test
    fun `should return 261214 as 30000000th value at memory game`() {
        // Given
        val startNumbers = "1,2,3"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(30000000)

        // Then
        assertEquals(261214, result)
    }

    @Test
    fun `should return 6895259 as 30000000th value at memory game`() {
        // Given
        val startNumbers = "2,3,1"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(30000000)

        // Then
        assertEquals(6895259, result)
    }

    @Test
    fun `should return 18 as 30000000th value at memory game`() {
        // Given
        val startNumbers = "3,2,1"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(30000000)

        // Then
        assertEquals(18, result)
    }

    @Test
    fun `should return 362 as 30000000th value at memory game`() {
        // Given
        val startNumbers = "3,1,2"

        // When
        val result = startNumbers.getMemoryGameValueAtTurn(30000000)

        // Then
        assertEquals(362, result)
    }

    // endregion

}