package com.adventofcode.day7

import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramTest {

    // region Part 1

    @Test
    fun `should map bag colors rules to handy containers map structure`() {
        // Given
        val bagRules = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
        """.trimIndent()

        // Then
        assertEquals(mapOf(
                "bright white" to listOf("light red", "dark orange"),
                "muted yellow" to listOf("light red", "dark orange"),
                "shiny gold" to listOf("bright white", "muted yellow"),
                "faded blue" to listOf("muted yellow", "dark olive", "vibrant plum"),
                "dark olive" to listOf("shiny gold"),
                "vibrant plum" to listOf("shiny gold"),
                "dotted black" to listOf("dark olive", "vibrant plum"),
        ), bagRules.toBagColorContainersMap())
    }

    @Test
    fun `should count 4 bag colors that can contain 'shiny gold' bag`() {
        // Given
        val bagRules = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
        """.trimIndent()

        // Then
        assertEquals(4, bagRules.countNumberOfBagColorsThatMayContain("shiny gold"))
    }

    // endregion

    // region Part 2

    @Test
    fun `should map bag colors rules to handy quantity containers map structure`() {
        // Given
        val bagRules = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
            posh purple bags contain 3 posh turquoise bags, 4 dull lime bags, 3 vibrant fuchsia bags, 2 dull green bags.
        """.trimIndent()

        // Then
        assertEquals(mapOf(
                "light red" to mapOf("bright white" to 1, "muted yellow" to 2),
                "dark orange" to mapOf("bright white" to 3, "muted yellow" to 4),
                "bright white" to mapOf("shiny gold" to 1),
                "muted yellow" to mapOf("shiny gold" to 2, "faded blue" to 9),
                "shiny gold" to mapOf("dark olive" to 1, "vibrant plum" to 2),
                "dark olive" to mapOf("faded blue" to 3, "dotted black" to 4),
                "vibrant plum" to mapOf("faded blue" to 5, "dotted black" to 6),
                "posh purple" to mapOf("posh turquoise" to 3, "dull lime" to 4, "vibrant fuchsia" to 3, "dull green" to 2),
        ), bagRules.toBagColorQuantityContainersMap())
    }

    @Test
    fun `should count 32 individuals bags required inside a single 'shiny gold' bag`() {
        // Given
        val bagRules = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
        """.trimIndent()

        // Then
        assertEquals(32, bagRules.countNumberOfIndividualsBagsRequiredInside("shiny gold"))
    }

    @Test
    fun `should count 126 individuals bags required inside a single 'shiny gold' bag`() {
        // Given
        val bagRules = """
            shiny gold bags contain 2 dark red bags.
            dark red bags contain 2 dark orange bags.
            dark orange bags contain 2 dark yellow bags.
            dark yellow bags contain 2 dark green bags.
            dark green bags contain 2 dark blue bags.
            dark blue bags contain 2 dark violet bags.
            dark violet bags contain no other bags.
        """.trimIndent()

        // Then
        assertEquals(126, bagRules.countNumberOfIndividualsBagsRequiredInside("shiny gold"))
    }

    // endregion

}