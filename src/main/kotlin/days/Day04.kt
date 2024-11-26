package days

import Utils.filterNotBlank
import Utils.toPair

@Suppress("unused")
object Day04 : DaySolver {
    override val day = 4
    override val part: PuzzlePart
        get() = sampleTestCases.map { it.part }.maxBy { it.levelNumber }

    override val sampleTestCases: List<TestCase> = listOf(
        TestCase(
            part = PuzzlePart.ONE,
            input = """
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8

            """.trimIndent(),
            expectedOutput = "2"
        ),
        TestCase(
            part = PuzzlePart.TWO,
            input = """
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8

            """.trimIndent(),
            expectedOutput = "4"
        ),
    )

    override fun solvePartOne(input: String): String {
        val solution = input.lines()
            .filterNotBlank()
            .map { pairOfElves ->
                val assignments = pairOfElves.split(",").toPair()
                assignments.first.rangeContains(assignments.second)
            }
            .count { it }
        return solution.toString()
    }

    override fun solvePartTwo(input: String): String {
        val solution = input.lines()
            .filterNotBlank()
            .map { pairOfElves ->
                val assignments = pairOfElves.split(",").toPair()
                assignments.first.rangeOverlapps(assignments.second)
            }
            .count { it }
        return solution.toString()
    }

    fun String.rangeContains(other: String): Boolean {
        val one = this.split("-").map(String::toInt).toPair()
        val two = other.split("-").map(String::toInt).toPair()
        return one.first <= two.first && one.second >= two.second ||
                two.first <= one.first && two.second >= one.second
    }

    fun String.rangeOverlapps(other: String): Boolean {
        val one = this.split("-").map(String::toInt).toPair()
        val two = other.split("-").map(String::toInt).toPair()
        return one.first <= two.first && one.second >= two.first ||
                one.first <= two.second && one.second >= two.second ||
                two.first <= one.first && two.second >= one.first ||
                two.first <= one.second && two.second >= one.second
    }

}