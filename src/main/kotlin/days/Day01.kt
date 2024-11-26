package days

@Suppress("unused")
object Day01 : DaySolver {
    override val day = 1
    override val part = PuzzlePart.TWO

    override val sampleTestCases: List<TestCase> = listOf(
        TestCase(
            part = PuzzlePart.ONE,
            input = """
                
                1000
                2000
                3000

                4000

                5000
                6000

                7000
                8000
                9000

                10000
            """.trimIndent(),
            expectedOutput = "24000"
        ),
        TestCase(
            part = PuzzlePart.TWO,
            input = """
                
                1000
                2000
                3000

                4000

                5000
                6000

                7000
                8000
                9000

                10000
            """.trimIndent(),
            expectedOutput = "45000"
        )
    )

    override fun solvePartOne(input: String): String {
        return input
            .split(Regex("\\n\\s*\\n"))
            .filter(String::isNotBlank)
            .maxOfOrNull { inputForSingleElf ->
                inputForSingleElf.lines().filter(String::isNotBlank).sumOf { it.toInt() }
            }
            .toString()
    }

    override fun solvePartTwo(input: String): String {
        return input
            .split(Regex("\\n\\s*\\n"))
            .filter(String::isNotBlank)
            .map { inputForSingleElf ->
                inputForSingleElf.lines().filter(String::isNotBlank).sumOf { it.toInt() }
            }
            .sortedDescending()
            .take(3)
            .sum()
            .toString()
    }
}