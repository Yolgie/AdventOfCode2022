package days

import Utils.filterNotBlank
import Utils.splitOnEmptyLine
import Utils.toPair

@Suppress("unused")
object Day05 : DaySolver {
    override val day = 5
    override val part: PuzzlePart
        get() = sampleTestCases.map{it.part}.maxBy { it.levelNumber }

    override val sampleTestCases: List<TestCase> = listOf(
        TestCase(
            part = PuzzlePart.ONE,
            input = """
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2

            """,
            expectedOutput = "CMZ"
        ),
        TestCase(
            part = PuzzlePart.TWO,
            input = """
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
            """,
            expectedOutput = "MCD"
        )
    )

    val moveRegex = Regex("""move (\d+) from (\d+) to (\d+)""")

    override fun solvePartOne(input: String): String {
        val (mapString, rearrangement) = input.splitOnEmptyLine().toPair()
        val stackIds = mapString.lines().filterNotBlank().last().split(" ").filterNotBlank().map { it.toInt() }
        val stackStrings = mapString.lines().filterNotBlank().dropLast(1).reversed()
        val stacks = List(stackIds.size+1){ ArrayDeque<Char>() }
        stackIds.forEach { index ->
            val accessIndex = 1 + (index-1)*4
            stackStrings.map { it[accessIndex] }
                .filter { it != ' ' }
                .forEach { crate -> stacks[index].addLast(crate) }
            println(stacks[index])
        }

        rearrangement.lines()
            .filterNotBlank()
            .forEach { actionString ->
                val regexMatch = moveRegex.find(actionString) ?: error("counld not parse the move")
                val (count, from, to) = regexMatch.destructured
                println(actionString)
                repeat(count.toInt()) {
                    val crateToMove = stacks[from.toInt()].removeLast()
                    stacks[to.toInt()].addLast(crateToMove)
                }
                stacks.forEach { println(it) }
            }

        return stacks.mapNotNull(ArrayDeque<Char>::lastOrNull)
            .joinToString("")
    }

    override fun solvePartTwo(input: String): String {
        val (mapString, rearrangement) = input.splitOnEmptyLine().toPair()
        val stackIds = mapString.lines().filterNotBlank().last().split(" ").filterNotBlank().map { it.toInt() }
        val stackStrings = mapString.lines().filterNotBlank().dropLast(1).reversed()
        val stacks = List(stackIds.size+1){ ArrayDeque<Char>() }
        stackIds.forEach { index ->
            val accessIndex = 1 + (index-1)*4
            stackStrings.map { it[accessIndex] }
                .filter { it != ' ' }
                .forEach { crate -> stacks[index].addLast(crate) }
            println(stacks[index])
        }

        rearrangement.lines()
            .filterNotBlank()
            .forEach { actionString ->
                val regexMatch = moveRegex.find(actionString) ?: error("counld not parse the move")
                val (count, from, to) = regexMatch.destructured
                println(actionString)

                val cratesToMove = (1..count.toInt())
                    .map { stacks[from.toInt()].removeLast() }
                    .reversed()
                stacks[to.toInt()].addAll(cratesToMove)

                stacks.forEach { println(it) }
            }

        return stacks.mapNotNull(ArrayDeque<Char>::lastOrNull)
            .joinToString("")
    }
}