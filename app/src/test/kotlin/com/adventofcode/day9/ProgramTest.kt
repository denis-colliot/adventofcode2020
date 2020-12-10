package com.adventofcode.day9

import com.adventofcode.toNumbersList
import kotlin.test.*

class ProgramTest {

    @Test
    fun `should return if given number is valid regarding its previous numbers`() {
        assertTrue(40L.isValidNumber(listOf(35, 20, 15, 25, 47)))
        assertFalse(40L.isValidNumber(listOf(35, 20, 16, 25, 47))) // `20+20` should not be invoked
        assertTrue(182L.isValidNumber(listOf(150, 117, 102, 95, 65)))
        assertTrue(182L.isValidNumber(listOf(65, 95, 102, 117, 150)))
        assertFalse(127L.isValidNumber(listOf(95, 102, 117, 150, 182)))
    }

    @Test
    fun `should return 127 as first invalid number after preamble of 5`() {
        // Given
        val inputs = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent()

        // When
        val result = inputs.toNumbersList().findFirstInvalidNumber(preamble = 5)

        // Then
        assertEquals(127, result)
    }

    @Test
    fun `should return sublist of numbers which sum equal to given number`() {
        assertNotNull(listOf<Long>(15, 25, 47, 40).startRangeWhichSumEqualsTo(127))
        assertNull(listOf<Long>(15, 25, 48, 40).startRangeWhichSumEqualsTo(127))
        assertNotNull(listOf<Long>(2, 4, 6).startRangeWhichSumEqualsTo(6))
    }

    @Test
    fun `should return 62 as first invalid number corresponding key with preamble of 5`() {
        // Given
        val inputs = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent()

        // When
        val result = inputs.toNumbersList().findFirstInvalidNumberKey(preamble = 5)

        // Then
        assertEquals(62, result)
    }

}