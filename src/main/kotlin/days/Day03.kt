package days

import Utils.filterNotBlank

@Suppress("unused")
object Day03 : DaySolver {
    override val day = 3
    override val part: PuzzlePart
        get() = sampleTestCases.map { it.part }.maxBy { it.levelNumber }

    override val sampleTestCases: List<TestCase> = listOf(
        TestCase(
            part = PuzzlePart.ONE,
            input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
            """.trimIndent(),
            expectedOutput = "157"
        ),
        TestCase(
            part = PuzzlePart.TWO,
            input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
                
            """.trimIndent(),
            expectedOutput = "70"
        )
    )

    override fun solvePartOne(input: String): String {
        val solution = input.lines()
            .filter(String::isNotBlank)
            .map { rucksack ->
                val rucksackCompartments = rucksack.splitAtHalf()
                rucksackCompartments.first.toList()
                    .first { rucksackCompartments.second.contains(it) }
            }
            .sumOf { char ->
                when {
                    char >= 'a' && char <= 'z' -> char - 'a' + 1
                    char >= 'A' && char <= 'Z' -> char - 'A' + 27
                    else -> error("no char fonud")
                }
            }

        return solution.toString()
    }

    override fun solvePartTwo(input: String): String {
        val solution = input.lines()
            .filterNotBlank()
            .chunked(3) { rucksack ->
                rucksack[0].toList()
                    .first { rucksack[1].contains(it) && rucksack[2].contains(it) }
            }
            .sumOf { char ->
                when {
                    char >= 'a' && char <= 'z' -> char - 'a' + 1
                    char >= 'A' && char <= 'Z' -> char - 'A' + 27
                    else -> error("no char fonud")
                }
            }

        return solution.toString()
    }

    fun String.splitAt(index: Int): Pair<String, String> {
        require(index in 0..this.length) { "Index out of bounds" }
        return Pair(this.substring(0, index), this.substring(index))
    }

    fun String.splitAtHalf(): Pair<String, String> {
        val mid = (this.length + 1) / 2
        return splitAt(mid)
    }

}
