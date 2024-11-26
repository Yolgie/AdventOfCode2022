import days.PuzzlePart
import org.jsoup.Jsoup
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets

object Submitter {
    private val SESSION_COOKIE: String by lazy {
        System.getenv("AOC_SESSION_COOKIE")
            ?: throw IllegalStateException("Session cookie not set. Please set the AOC_SESSION_COOKIE environment variable.")
    }

    private val client: HttpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .build()

    /**
     * Submits an answer to Advent of Code.
     *
     * @param day The day of the challenge (1-25).
     * @param part The part of the challenge (PuzzlePart.ONE or PuzzlePart.TWO).
     * @param answer The answer to submit.
     * @return The server's response message.
     */
    fun submitAnswer(day: Int, year: Int, part: PuzzlePart, answer: String): String {
        require(day in 1..25) { "Invalid day: $day. Days are from 1 to 25." }

        val sessionCookie = "session=$SESSION_COOKIE"
        val submissionUrl = "https://adventofcode.com/$year/day/$day/answer"
        val formData = "level=${part.levelNumber}&answer=${URLEncoder.encode(answer, StandardCharsets.UTF_8)}"

        val request = HttpRequest.newBuilder()
            .uri(URI.create(submissionUrl))
            .header("User-Agent", "aoc-kotlin-submitter")
            .header("Cookie", sessionCookie)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString(formData))
            .build()

        return try {
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            when (response.statusCode()) {
                200 -> parseSubmissionResponse(response.body())
                400 -> "Bad Request: Possibly malformed submission."
                404 -> "Not Found: Invalid URL."
                429 -> "Too Many Requests: Rate limited. Please wait before submitting again."
                else -> "Unexpected Status Code: ${response.statusCode()}"
            }
        } catch (e: Exception) {
            "Error during submission: ${e.message}"
        }
    }

    /**
     * Parses the server's HTML response to extract content within <main> tags.
     *
     * @param html The HTML response from the server.
     * @return The extracted text content inside <main></main>.
     */
    private fun parseSubmissionResponse(html: String): String =
        Jsoup.parse(html).selectFirst("main")?.text() ?: html
}
