package com.adventofcode.day8

import com.adventofcode.IsDayProgram
import java.io.File

@Suppress("unused")
class Program : IsDayProgram<Pair<Int, Int>> {
    override fun run(dayNumber: Int): Pair<Int, Int> {
        val inputs = File(javaClass.getResource("/day8/inputs").toURI()).readText()
        return inputs.runProgram().accumulator to inputs.runProgramWithFixAttempts().accumulator
    }
}

// region Part 1

typealias ProgramInstructions = List<String>

data class Instruction(val operation: String, val argument: Int)

sealed class ProgramResult(val accumulator: Int) {
    data class InfiniteLoop(private val accumulatorValue: Int) : ProgramResult(accumulatorValue)
    data class EndOfProgram(private val accumulatorValue: Int) : ProgramResult(accumulatorValue)

    operator fun plus(increment: Instruction): ProgramResult = when (this) {
        is InfiniteLoop -> InfiniteLoop(increment.argument + accumulator)
        is EndOfProgram -> EndOfProgram(increment.argument + accumulator)
    }
}

fun String.runProgram(): ProgramResult =
        lines().runInstruction(0).also { println("Program result: $it") }

private fun ProgramInstructions.runInstruction(lineIndex: Int, executedLines: Set<Int> = emptySet()): ProgramResult =
        if (executedLines.contains(lineIndex)) {
            //println("Infinite loop detected: line `$lineIndex` already executed → $executedLines")
            ProgramResult.InfiniteLoop(0)
        } else {
            getOrNull(lineIndex)?.let(String::toInstruction).let { instruction ->
                //println("Running instruction at line `$lineIndex`: `$instruction`")
                when (instruction) {
                    null -> {
                        println("No instruction at line `$lineIndex` → end of program")
                        ProgramResult.EndOfProgram(0)
                    }
                    else -> when (instruction.operation) {
                        "acc" -> runInstruction(lineIndex + 1, executedLines + lineIndex) + instruction
                        "jmp" -> runInstruction(lineIndex + instruction.argument, executedLines + lineIndex)
                        else -> runInstruction(lineIndex + 1, executedLines + lineIndex)
                    }
                }
            }
        }

private fun String.toInstruction(): Instruction? =
        "(\\w{3}) (.\\d+)".toRegex().matchEntire(orEmpty())?.let {
            Instruction(it.groupValues[1], it.groupValues[2].toInt())
        }

// endregion

// region Part 2

@Throws(NoSuchElementException::class) // If no fix possible
fun String.runProgramWithFixAttempts(): ProgramResult.EndOfProgram {
    val programInstructions = lines()
    return programInstructions.asSequence()
            .mapIndexedNotNull { lineIndex, line -> line.switchOperation()?.let { lineIndex to it } }
            .mapNotNull { (lineIndex, alteredLine) ->
                val alteredInstructions = programInstructions.toMutableList().apply {
                    set(lineIndex, alteredLine)
                }
                when (val result = alteredInstructions.runInstruction(0)) {
                    is ProgramResult.EndOfProgram -> result
                    else -> null
                }
            }
            .first()
}

fun String.switchOperation(): String? = when {
    startsWith("nop") -> replace("nop", "jmp")
    startsWith("jmp") -> replace("jmp", "nop")
    else -> null
}

// endregion