package com.example.oversearch

import com.example.oversearch.data.OverwatchPlayerSearchDataSource
import com.example.oversearch.data.OverwatchResourcesDataSource
import com.example.oversearch.data.PlayerRepository
import com.example.oversearch.data.data.OverwatchPlayerDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun readData() {
        val f = File("src/main/res/raw/ow_data.json").readText()
        println(f)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun requests() = runBlocking {
        val client = HttpClient() {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = client.get("https://overwatch.blizzard.com/en-us/search/account-by-name/shalva/")
            .body<List<OverwatchPlayerDTO>>()

        println(response)

    }

    @Test
    fun requests_data(): Unit = runBlocking {
        val client = HttpClient()

//        val responseText = client.get("https://overwatch.blizzard.com/en-us/search").bodyAsText()

        val jsonPattern = "\\{.+}\$".toRegex()
        val jsonString = "const avatar = {\"id\": \"0x0250000000000A90\"}"
        val match = jsonPattern.find(jsonString)
        println(match?.groupValues?.get(0))

        val message = Json.parseToJsonElement(match?.groupValues?.get(0)!!)
        println(message)

        println()
        val currentRelativePath: Path = Paths.get("")
        val s: String = currentRelativePath.toAbsolutePath().toString()
        println("Current absolute path is: $s")
        val html = File("./scratch_1.html").readLines().filter {
            it.startsWith("const ")
        }.map {
            it.removeRange(0..it.indexOf('=') + 1)
        }.map {
            Json.parseToJsonElement(it)
        }
        val titleCode = "0x0250000000006B2E"
        123

//        println(response)
    }

    @Test
    fun asdasdsa() {
        val jsonString = "const avatar = {\"id\": \"0x0250000000000A90\"}"
        println(jsonString.removeRange(0..jsonString.indexOf('=') + 1))
    }

    @Test
    fun asdasd() {
        val text = File("ow_data.json").readText()
        val parsed = Json.parseToJsonElement(text)
        parsed
    }

    @Test
    fun asdasds() = runBlocking {
        val client = HttpClient()
        val repo = PlayerRepository(
            OverwatchPlayerSearchDataSource(client),
            object : OverwatchResourcesDataSource {
                override fun readRawJson(): String {
                    return File("src/main/res/raw/ow_data.json").readText()
                }
            }, Dispatchers.IO
        )

        println(repo.search("blah"))
    }
}

