import Submitter.submitAnswer
import days.DaySolver

fun main() {
    val year = 2022
    val day = 5
    require(day in 1..25) { "Invalid day: $day. Advent of Code days are from 1 to 25." }

    val input = InputDownloader.getInput(year, day)
    val solver = GenerateDaySolver.generate(day)
    val testResult = runTests(solver)
    val result = solver.part.selectorFunction.invoke(solver, input)
    println("Day $day Result: $result")

    if (testResult.all { it }) {
        println("All tests passed -> trying to auto submit")
        val submitResult = submitAnswer(day, year, solver.part, result)
        if (submitResult.contains("That's the right answer!")) println("Submission looks good -> reload to continue")
        else println()
    }
}

fun runTests(solver: DaySolver): List<Boolean> {
    val part = solver.part
    println("Running test cases for Day ${solver.day}, Part ${part.name}...")
    return solver.sampleTestCases
        .filter { it.part == part }
        .mapIndexed { index, testCase ->
            val actualOutput = solver.part.selectorFunction.invoke(solver, testCase.input)
            val passed = actualOutput == testCase.expectedOutput
            if (passed) {
                println("Test Case ${index + 1}: PASSED")
            } else {
                println("Test Case ${index + 1}: FAILED")
                println("Expected: ${testCase.expectedOutput}")
                println("Actual  : $actualOutput")
            }
            passed
        }
}
