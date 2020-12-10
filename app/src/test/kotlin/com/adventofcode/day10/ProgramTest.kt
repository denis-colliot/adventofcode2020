package com.adventofcode.day10

import com.adventofcode.toNumbersList
import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramTest {

    @Test
    fun `should return 7 differences of 1J and 5 differences of 3J`() {
        // Given
        val inputs = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent()

        // When
        val result = inputs.toNumbersList().findJoltageDistribution()

        // Then
        assertEquals(mapOf(1L to 7L, 3L to 5L), result)
    }

    @Test
    fun `should return 22 differences of 1J and 10 differences of 3J`() {
        // Given
        val inputs = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent()

        // When
        val result = inputs.toNumbersList().findJoltageDistribution()

        // Then
        assertEquals(mapOf(1L to 22L, 3L to 10L), result)
    }

    @Test
    fun `should count 8 possible arrangements for given numbers`() {
        // Given
        val inputs = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent()

        // When
        val result = inputs.toNumbersList().countPossibleArrangements()

        // Then
        assertEquals(8, result)
    }

    @Test
    fun `should count 19208 possible arrangements for given numbers`() {
        // Given
        val inputs = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent()

        // When
        val result = inputs.toNumbersList().countPossibleArrangements()

        // Then
        assertEquals(19208, result)
    }

}