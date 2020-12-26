package com.adventofcode.day16

import com.adventofcode.IsDayProgram
import java.io.File

@Suppress("unused")
class Program : IsDayProgram<Pair<Int, Long>> {
    override fun run(dayNumber: Int): Pair<Int, Long> {
        val inputs = File(javaClass.getResource("/day16/inputs").toURI()).readText()
        return inputs.getTicketScanningErrorRate() to inputs.getDepartureValuesProduct()
    }
}

data class TicketRule(val name: String, val ranges: List<IntRange>)
typealias Ticket = List<Int>

fun String.getTicketRules(): List<TicketRule> = lines()
    .mapNotNull { line -> "(.*): (\\d+)-(\\d+) or (\\d+)-(\\d+)".toRegex().matchEntire(line)?.groupValues }
    .map { groups ->
        val ruleName = groups[1]
        val ruleFirstRange = groups[2].toInt()..groups[3].toInt()
        val ruleSecondRange = groups[4].toInt()..groups[5].toInt()
        TicketRule(ruleName, listOf(ruleFirstRange, ruleSecondRange))
    }

fun String.getTickets(): List<Ticket> = lines()
    .filter { line -> line.matches("^\\d+,.*$".toRegex()) }
    .map { line -> line.split(",").map(String::toInt) }

fun String.getTicketScanningErrorRate(): Int {
    val ticketRules: List<TicketRule> = getTicketRules()
    return getTickets()
        .flatten()
        .filter { ticketValue -> ticketValue.doesNotMatchAnyRule(ticketRules) }
        .sum()
}

fun String.getValidTickets(): List<Ticket> {
    val ticketRules = getTicketRules()
    return getTickets().filterNot { ticket ->
        ticket.any { ticketValue -> ticketValue.doesNotMatchAnyRule(ticketRules) }
    }
}

fun String.getSortedTicketRules(): List<TicketRule> {
    val ticketRules = getTicketRules()
    val consumedRules = mutableSetOf<TicketRule>()
    return getValidTickets().asSequence()
        .drop(1) // Drop my ticket
        .flatMap { ticket -> ticket.mapIndexed { index, value -> index to value } }
        .groupBy(keySelector = { it.first }, valueTransform = { it.second })
        .map { (columnIndex, columnValues) -> columnIndex to ticketRules.filter { rule -> columnValues.matchRule(rule) } }
        .sortedBy { (_, ruleCandidates) -> ruleCandidates.size }
        .map { (columnIndex, ruleCandidates) ->
            val rules = ruleCandidates - consumedRules
            consumedRules.addAll(rules)
            if (rules.size != 1) error("Should not contain more than one rule!")
            columnIndex to rules.first()
        }
        .sortedBy { (columnIndex, _) -> columnIndex }
        .map { (_, rule) -> rule }
}

private fun List<Int>.matchRule(rule: TicketRule): Boolean =
    all { value -> rule.ranges.any { range -> value in range } }

private fun Int.doesNotMatchAnyRule(ticketRules: List<TicketRule>): Boolean =
    ticketRules.flatMap(TicketRule::ranges).none { range -> this in range }

fun String.getDepartureValuesProduct(): Long {
    val myTicket: Ticket = getTickets().first()
    return getSortedTicketRules()
        .mapIndexed { index, rule -> index to rule }
        .filter { (_, rule) -> rule.name.startsWith("departure") }
        .map { (index, rule) ->
            myTicket[index].toLong().also {
                println("Rule `$rule` is at index `$index` → My ticket's corresponding value is `$it`")
            }
        }
        .reduce { acc, value -> acc * value }
}