package com.adventofcode

interface IsDayProgram<R> {

    fun run(dayNumber: Int): R

}

fun String.toNumbersList(): List<Long> = lines().map(String::toLong)