import days.DaySolver
import org.reflections.Reflections
import org.reflections.scanners.Scanners
import org.reflections.util.ConfigurationBuilder
import java.io.File

object GenerateDaySolver {
    private const val TEMPLATE_FILE_LOCATION = "src/main/kotlin/days/DaySolver.kt.template"
    private const val OUTPUT_DIR = "src/main/kotlin/days/"

    private val templateFile = File(TEMPLATE_FILE_LOCATION)

    fun generate(day: Int): DaySolver {
        require(day in 1..25) { "Invalid day: $day. Advent of Code days are from 1 to 25." }
        require(templateFile.exists()) { "Template file does not exist." }

        val dayStr = day.toString().padStart(2, '0')
        val outputPath = "$OUTPUT_DIR/Day$dayStr.kt"
        val outputFile = File(outputPath)
        if (outputFile.exists()) {
            println("Solver for Day $dayStr at $outputPath already exists.")
        } else {

            val content = templateFile.readText()
                .replace("{{DAY}}", dayStr)
                .replace("{{DAY_AS_NUMBER}}", day.toString())
            outputFile.writeText(content)
            println("Solver for Day $dayStr has been created at $outputPath")
        }
        return getSolverByDay(day)
    }

    fun discoverSolvers(): List<DaySolver> {
        val reflections = Reflections(
            ConfigurationBuilder()
                .forPackage("days")
                .addScanners(Scanners.TypesAnnotated, Scanners.SubTypes)
        )
        val solverClasses = reflections.getSubTypesOf(DaySolver::class.java)
        return solverClasses.mapNotNull { clazz ->
            try {
                clazz.kotlin.objectInstance ?: clazz.getDeclaredConstructor().newInstance()
            } catch (e: Exception) {
                println("Failed to instantiate solver for class ${clazz.name}: ${e.message}")
                null
            }
        }
    }

    /**
     * Retrieves the DaySolver instance for the specified day.
     *
     * @param day The day number (1-25).
     * @return The corresponding DaySolver instance or null if not found.
     */
    fun getSolverByDay(day: Int): DaySolver {
        return discoverSolvers().find { it.day == day } ?: throw IllegalArgumentException("No day found for $day")
    }
}