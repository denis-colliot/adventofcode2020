/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.adventofcode.day6

import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramTest {

    @Test
    fun `should count 11 distinct answers to which anyone answered 'yes'`() {
        // Given
        val answers = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()

        // Then
        assertEquals(11, countDistinctAnswersToWhichAnyoneAnsweredYes(answers))
    }

    @Test
    fun `should count 6 distinct answers to which everyone answered 'yes'`() {
        // Given
        val answers = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()

        // Then
        assertEquals(6, countDistinctAnswersToWhichEveryoneAnsweredYes(answers))
    }

}