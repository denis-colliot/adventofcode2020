package com.adventofcode.day6

import com.adventofcode.IsDayProgram
import java.io.File

@Suppress("unused")
class Program : IsDayProgram<Pair<Int, Int>> {
    override fun run(dayNumber: Int): Pair<Int, Int> {
        val inputs = File(javaClass.getResource("/day6/inputs").toURI()).readText()
        return countDistinctAnswersToWhichAnyoneAnsweredYes(inputs) to countDistinctAnswersToWhichEveryoneAnsweredYes(inputs)
    }
}

// region Part 1

fun countDistinctAnswersToWhichAnyoneAnsweredYes(answers: String): Int =
        answers.split("\n\n")
                .map { it.split('\n') }
                .map { it.map { personAnswersString -> personAnswersString.toSet() } }
                .sumBy { it.reduce { accumulator, answersChars -> accumulator + answersChars }.count() }

// endregion

// region Part 2

fun countDistinctAnswersToWhichEveryoneAnsweredYes(answers: String): Int =
        answers.split("\n\n")
                .map { it.split('\n') }
                .map { it.map { personAnswersString -> personAnswersString.toSet() } }
                .sumBy { it.reduce { accumulator, answersChars -> accumulator.intersect(answersChars) }.count() }

// endregion