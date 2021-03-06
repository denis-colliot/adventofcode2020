/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.adventofcode.day1

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ProgramTest {

    private val program by lazy { Program() }

    @Test
    fun `should return false when given numbers sum does not equal 2020`() {
        assertFalse(program.isSolution(-1, 0))
        assertFalse(program.isSolution(1000, 1019))
    }

    @Test
    fun `should return true when given numbers sum equals 2020`() {
        assertTrue(program.isSolution(1000, 1020))
        assertTrue(program.isSolution(1000, 1020))
        assertTrue(program.isSolution(2019, 1))
        assertTrue(program.isSolution(2020, 0))
        assertTrue(program.isSolution(0, 2020))
    }
}
