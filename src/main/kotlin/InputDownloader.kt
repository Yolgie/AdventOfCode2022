import java.io.File
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object InputDownloader {
    private val SESSION_COOKIE: String by lazy {
        System.getenv("AOC_SESSION_COOKIE") ?: throw IllegalStateException("Session cookie not set. Please set the AOC_SESSION_COOKIE environment variable.")
    }
    private const val INPUT_DIR = "inputs"

    private val client: HttpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .build()

    /**
     * Retrieves the input for the specified year and day.
     * If the input file exists locally, it reads from the file.
     * Otherwise, it downloads the input, saves it locally, and returns the content.
     *
     * @param year The Advent of Code year.
     * @param day The day of the challenge (1-25).
     * @return The input content as a String.
     */
    fun getInput(year: Int, day: Int): String {
        require(day in 1..25) { "Invalid day: $day. Advent of Code days are from 1 to 25." }

        val inputDirectory = File(INPUT_DIR)
        if (!inputDirectory.exists()) {
            inputDirectory.mkdirs()
        }

        val inputFile = File(inputDirectory, "day${day.toString().padStart(2, '0')}.txt")

        return if (inputFile.exists()) {
            inputFile.readText()
        } else {
            val input = downloadInput(year, day)
            if (input.isNotEmpty()) {
                inputFile.writeText(input)
            }
            input
        }
    }

    /**
     * Downloads the input for the specified year and day from Advent of Code.
     *
     * @param year The Advent of Code year.
     * @param day The day of the challenge (1-25).
     * @return The downloaded input as a String, or an empty string if failed.
     */
    private fun downloadInput(year: Int, day: Int): String {
        val uri = URI.create("https://adventofcode.com/$year/day/$day/input")
        val request = HttpRequest.newBuilder()
            .uri(uri)
            .header("User-Agent", "aoc-kotlin")
            .header("Cookie", "session=$SESSION_COOKIE")
            .GET()
            .build()

        println("Downloading input for day $day ($year)")

        return try {
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            when (response.statusCode()) {
                200 -> response.body()
                404 -> throw IOException("Input for Day $day not found.")
                else -> throw IOException("Failed to fetch input for Day $day. Status Code: ${response.statusCode()}")
            }
        } catch (e: IOException) {
            println("Error fetching input for Day $day: ${e.message}")
            ""
        } catch (e: InterruptedException) {
            println("Request interrupted while fetching input for Day $day: ${e.message}")
            Thread.currentThread().interrupt()
            ""
        }
    }
}
