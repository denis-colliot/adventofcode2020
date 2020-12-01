package com.adventofcode.day1

data class Solution(
        val numbers: List<Int>,
        val product: Int = numbers.reduce { accumulator, item -> accumulator * item }
) {

    constructor(vararg numbers: Int) : this(numbers.toList())

}