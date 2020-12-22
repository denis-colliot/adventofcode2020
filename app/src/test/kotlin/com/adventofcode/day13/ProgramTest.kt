package com.adventofcode.day13

import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramTest {

    @Test
    fun `should return 295 as product of earliest bus id and waiting minutes`() {
        // Given
        val inputs = """
            939
            7,13,x,x,59,x,31,19
        """.trimIndent()

        // When
        val result = inputs.findEarliestBusIdMultipliedByWaitingMinutes()

        // Then
        assertEquals(295, result)
    }

    @Test
    fun `should return 1068781 as earliest timestamp for given input`() {
        // Given
        val inputs = "7,13,x,x,59,x,31,19"

        // When/Then
        assertEquals(1068781.toBigInteger(), inputs.findEarliestTimestamp())
    }

    @Test
    fun `should return 3417 as earliest timestamp for given input`() {
        // Given
        val inputs = "17,x,13,19"

        // When/Then
        assertEquals(3417.toBigInteger(), inputs.findEarliestTimestamp())
    }

    @Test
    fun `should return 754018 as earliest timestamp for given input`() {
        // Given
        val inputs = "67,7,59,61"

        // When/Then
        assertEquals(754018.toBigInteger(), inputs.findEarliestTimestamp())
    }

    @Test
    fun `should return 779210 as earliest timestamp for given input`() {
        // Given
        val inputs = "67,x,7,59,61"

        // When/Then
        assertEquals(779210.toBigInteger(), inputs.findEarliestTimestamp())
    }

    @Test
    fun `should return 1261476 as earliest timestamp for given input`() {
        // Given
        val inputs = "67,7,x,59,61"

        // When/Then
        assertEquals(1261476.toBigInteger(), inputs.findEarliestTimestamp())
    }

    @Test
    fun `should return 1202161486 as earliest timestamp for given input`() {
        // Given
        val inputs = "1789,37,47,1889"

        // When/Then
        assertEquals(1202161486.toBigInteger(), inputs.findEarliestTimestamp())
    }

}