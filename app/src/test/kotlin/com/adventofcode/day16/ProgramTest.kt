package com.adventofcode.day16

import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramTest {

    @Test
    fun `should get tickets rules from input`() {
        // Given
        val inputs = """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50

            your ticket:
            7,1,14

            nearby tickets:
            7,3,47
            40,4,50
            55,2,20
            38,6,12
        """.trimIndent()

        // When
        val result = inputs.getTicketRules()

        // Then
        assertEquals(
            listOf(
                TicketRule("class", listOf(1..3, 5..7)),
                TicketRule("row", listOf(6..11, 33..44)),
                TicketRule("seat", listOf(13..40, 45..50))
            ), result
        )
    }

    @Test
    fun `should get tickets values from input`() {
        // Given
        val inputs = """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50

            your ticket:
            7,1,14

            nearby tickets:
            7,3,47
            40,4,50
            55,2,20
            38,6,12
        """.trimIndent()

        // When
        val result = inputs.getTickets()

        // Then
        assertEquals(
            listOf(
                listOf(7, 1, 14),
                listOf(7, 3, 47),
                listOf(40, 4, 50),
                listOf(55, 2, 20),
                listOf(38, 6, 12)
            ), result
        )
    }

    @Test
    fun `should return 71 as ticket scanning error rate`() {
        // Given
        val inputs = """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50

            your ticket:
            7,1,14

            nearby tickets:
            7,3,47
            40,4,50
            55,2,20
            38,6,12
        """.trimIndent()

        // When
        val result = inputs.getTicketScanningErrorRate()

        // Then
        assertEquals(71, result)
    }

    @Test
    fun `should return valid tickets`() {
        // Given
        val inputs = """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50

            your ticket:
            7,1,14

            nearby tickets:
            7,3,47
            40,4,50
            55,2,20
            38,6,12
        """.trimIndent()

        // When
        val result = inputs.getValidTickets()

        // Then
        assertEquals(listOf(listOf(7, 1, 14), listOf(7, 3, 47)), result)
    }

    @Test
    fun `should detect ticket fields order as row,class,seat`() {
        // Given
        val inputs = """
            class: 0-1 or 4-19
            row: 0-5 or 8-19
            seat: 0-13 or 16-19
            
            your ticket:
            11,12,13
            
            nearby tickets:
            3,9,18
            15,1,5
            5,14,9
        """.trimIndent()

        // When
        val result = inputs.getSortedTicketRules()

        // Then
        assertEquals(
            listOf(
                TicketRule("row", listOf(0..5, 8..19)),
                TicketRule("class", listOf(0..1, 4..19)),
                TicketRule("seat", listOf(0..13, 16..19))
            ), result
        )
    }

}