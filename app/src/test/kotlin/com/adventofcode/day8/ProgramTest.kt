package com.adventofcode.day8

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ProgramTest {

    @Test
    fun `should return infinite loop result with accumulator value of 5`() {
        // Given
        val instructions = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()

        // When
        val result = instructions.runProgram()

        // Then
        assertTrue(result is ProgramResult.InfiniteLoop)
        assertEquals(5, result.accumulator)
    }

    @Test
    fun `should switch 'nop' & 'jmp' operations or return null for other operations`() {
        assertEquals("jmp +0", "nop +0".switchOperation())
        assertEquals("nop +12", "jmp +12".switchOperation())
        assertEquals("nop +1", "jmp +1".switchOperation())
        assertEquals(null, "acc +1".switchOperation())
    }

    @Test
    fun `should attempt fix for each 'nop' & 'jmp' operation to eventually end program with accumulator 8`() {
        // Given
        val instructions = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()

        // When
        val result = instructions.runProgramWithFixAttempts()

        // Then
        assertEquals(8, result.accumulator)
    }

}