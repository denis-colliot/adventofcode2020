package com.adventofcode.day3

import com.adventofcode.IsDayProgram
import java.io.File

class Program : IsDayProgram<Long> {

    override fun run(dayNumber: Int): Long {
        val pattern = File(javaClass.getResource("/day3/pattern").toURI()).readText()
        val start = Coordinates(0, 0)

        return multiplyTreesCounts(pattern, start, slopes = listOf(
                Slope(right = 1, down = 1),
                Slope(right = 3, down = 1),
                Slope(right = 5, down = 1),
                Slope(right = 7, down = 1),
                Slope(right = 1, down = 2)
        ))
    }

    fun multiplyTreesCounts(pattern: String, start: Coordinates, slopes: List<Slope>): Long {
        return slopes.map { slope ->
            countTrees(pattern, start, slope).toLong().also { treesCount ->
                println("From start $start and slope $slope → $treesCount trees")
            }
        }.reduce { accumulator, item -> accumulator * item }
    }

    fun countTrees(pattern: String, start: Coordinates, slope: Slope): Int {
        val patternMap = toPatternMap(pattern)
        val locations = mutableListOf<Char>()
        var coordinates = start

        while (true) {
            val character = patternMap.get(coordinates) ?: break
            coordinates = coordinates.move(slope)
            locations.add(character)
        }

        return locations.count(::isTree)
    }

    fun isTree(character: Char): Boolean = character == '#'

    fun toPatternMap(pattern: String): PatternMap {
        val map = mutableMapOf<Coordinates, Char>()
        val patternLines = pattern.lines()
        patternLines.forEachIndexed { lineIndex, line ->
            line.toCharArray().mapIndexed { columnIndex, character ->
                map.put(Coordinates(columnIndex, lineIndex), character)
            }
        }
        return PatternMap(map, width = patternLines.first().length, height = patternLines.size)
    }

    data class PatternMap(val map: Map<Coordinates, Char>, val width: Int, val height: Int) {
        /**
         * @return Character at given coordinates or `null` if bottom reached.
         */
        fun get(coordinates: Coordinates): Char? = map[Coordinates(coordinates.x % width, coordinates.y)]
    }

    data class Coordinates(val x: Int, val y: Int) {
        fun move(slope: Slope): Coordinates = Coordinates(x = x + slope.right, y = y + slope.down)
    }

    data class Slope(val right: Int, val down: Int)

}