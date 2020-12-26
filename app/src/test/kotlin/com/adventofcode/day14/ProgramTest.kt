package com.adventofcode.day14

import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramTest {

    @Test
    fun `should apply mask to int`() {
        assertEquals(73, 11L.applyMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"))
        assertEquals(101, 101L.applyMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"))
        assertEquals(64, 0L.applyMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"))
    }

    @Test
    fun `should return 165 as sum of all memory values`() {
        // Given
        val inputs = """
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent()

        // When
        val result = inputs.sumAllMemoryValues(memoryHandlerPart1)

        // Then
        assertEquals(165, result)
    }

    @Test
    fun `should return 4 addresses when applying mask to address 42`() {
        // Given
        val address = "000000000000000000000000000000101010"
        val mask = "000000000000000000000000000000X1001X"

        // When
        val result = address.decodeAddresses(mask = mask)

        // Then
        assertEquals(
            listOf<Long>(
                26, // 000000000000000000000000000000011010
                27, // 000000000000000000000000000000011011
                58, // 000000000000000000000000000000111010
                59, // 000000000000000000000000000000111011
            ), result
        )
    }

    @Test
    fun `should return 8 addresses when applying mask to address 26`() {
        // Given
        val address = "000000000000000000000000000000011010"
        val mask = "00000000000000000000000000000000X0XX"

        // When
        val result = address.decodeAddresses(mask = mask)

        // Then
        assertEquals(
            listOf<Long>(
                16, // 000000000000000000000000000000010000
                17, // 000000000000000000000000000000010001
                18, // 000000000000000000000000000000010010
                19, // 000000000000000000000000000000010011
                24, // 000000000000000000000000000000011000
                25, // 000000000000000000000000000000011001
                26, // 000000000000000000000000000000011010
                27, // 000000000000000000000000000000011011
            ), result
        )
    }

    @Test
    fun `should return 208 as memory values total sum`() {
        // Given
        val input = """
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
        """.trimIndent()

        // When
        val result = input.sumAllMemoryValues(memoryHandlerPart2)

        // Then
        assertEquals(208, result)
    }

}