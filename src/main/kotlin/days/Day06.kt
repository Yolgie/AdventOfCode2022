package days

@Suppress("unused")
object Day06 : DaySolver {
    override val day = 6
    override val part: PuzzlePart
        get() = sampleTestCases.map{it.part}.maxBy { it.levelNumber }

    override val sampleTestCases: List<TestCase> = listOf(
        TestCase(
            part = PuzzlePart.ONE,
            input = """
                mjqjpqmgbljsphdztnvjfqwrcgsmlb
            """.trimIndent(),
            expectedOutput = "7"
        ),
        TestCase(
            part = PuzzlePart.ONE,
            input = """
                bvwbjplbgvbhsrlpgdmjqwftvncz
            """.trimIndent(),
            expectedOutput = "5"
        ),
        TestCase(
            part = PuzzlePart.ONE,
            input = """
                nppdvjthqldpwncqszvftbrmjlhg
            """.trimIndent(),
            expectedOutput = "6"
        ),
        TestCase(
            part = PuzzlePart.ONE,
            input = """
                nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg
            """.trimIndent(),
            expectedOutput = "10"
        ),
        TestCase(
            part = PuzzlePart.ONE,
            input = """
                zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
            """.trimIndent(),
            expectedOutput = "11"
        ),
        TestCase(
            part = PuzzlePart.TWO,
            input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
            expectedOutput = "19"
        ),
        TestCase(
            part = PuzzlePart.TWO,
            input = "bvwbjplbgvbhsrlpgdmjqwftvncz",
            expectedOutput = "23"
        ),
        TestCase(
            part = PuzzlePart.TWO,
            input = "nppdvjthqldpwncqszvftbrmjlhg",
            expectedOutput = "23"
        ),
        TestCase(
            part = PuzzlePart.TWO,
            input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
            expectedOutput = "29"
        ),
        TestCase(
            part = PuzzlePart.TWO,
            input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw",
            expectedOutput = "26"
        ),
    )

    override fun solvePartOne(input: String): String {
        val solution = input.windowed(4, 1)
            .indexOfFirst { it.toSet().size == 4 } + 4

        return solution.toString()
    }

    override fun solvePartTwo(input: String): String {
        val messageSize = 14
        val solution = input.windowed(messageSize, 1)
            .indexOfFirst { it.toSet().size == messageSize } + messageSize

        return solution.toString()
    }
}