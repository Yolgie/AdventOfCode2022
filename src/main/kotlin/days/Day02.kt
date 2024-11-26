package days


@Suppress("unused")
object Day02 : DaySolver {
    override val day = 2
    override val part: PuzzlePart
        get() = sampleTestCases.map{it.part}.maxBy { it.levelNumber }

    override val sampleTestCases: List<TestCase> = listOf(
        TestCase(
            part = PuzzlePart.ONE,
            input = """
                A Y
                B X
                C Z
            """.trimIndent(),
            expectedOutput = "15"
        ),
        TestCase(
            part = PuzzlePart.TWO,
            input = """
                A Y
                B X
                C Z
            """.trimIndent(),
            expectedOutput = "12"
        ),
    )

    override fun solvePartOne(input: String): String {
        return input.lines()
            .sumOf { round ->
                val opponent = round.substringBefore(' ')
                val myself = round.substringAfter(' ')
                val myValue = when (myself) {
                    "X" -> 1 // ROCK
                    "Y" -> 2 // PAPER
                    "Z" -> 3 // Scissors
                    else -> 0
                }
                val gameValue = when {
                    opponent == "A" && myself == "X" ||
                            opponent == "B" && myself == "Y" ||
                            opponent == "C" && myself == "Z" -> 3

                    opponent == "A" && myself == "Y" ||
                            opponent == "B" && myself == "Z" ||
                            opponent == "C" && myself == "X" -> 6

                    else -> 0
                }
                myValue + gameValue
            }.toString()
    }

    override fun solvePartTwo(input: String): String {
        return input.lines()
            .sumOf { round ->
                val opponent = round.substringBefore(' ')
                val myself = round.substringAfter(' ')
                val gameValue = when (myself) {
                    "X" -> 0 + getLoosingValue(opponent) // ROCK
                    "Y" -> 3 + getDrawValue(opponent) // PAPER
                    "Z" -> 6 + getWinningValue(opponent) // Scissors
                    else -> 0
                }
                gameValue
            }.toString()
    }

    private fun getWinningValue(opponent: String): Int = when (opponent) {
        "C" -> 1 // ROCK
        "A" -> 2 // PAPER
        "B" -> 3 // Scissors
        else -> 0
    }
    private fun getDrawValue(opponent: String): Int = when (opponent) {
        "A" -> 1 // ROCK
        "B" -> 2 // PAPER
        "C" -> 3 // Scissors
        else -> 0
    }
    private fun getLoosingValue(opponent: String): Int = when (opponent) {
        "B" -> 1 // ROCK
        "C" -> 2 // PAPER
        "A" -> 3 // Scissors
        else -> 0
    }
}