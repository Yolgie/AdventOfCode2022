package days

interface DaySolver {
    val day: Int
    val part: PuzzlePart

    val sampleTestCases: List<TestCase>

    fun solvePartOne(input: String): String
    fun solvePartTwo(input: String): String
}

enum class PuzzlePart(val levelNumber: Int, val selectorFunction: DaySolver.(input: String) -> String) {
    ONE(1, { input -> this.solvePartOne(input) }),
    TWO(2, { input -> this.solvePartTwo(input) })
}

data class TestCase(
    val part: PuzzlePart,
    val input: String,
    val expectedOutput: String
)