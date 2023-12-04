package `2023Challange`

import java.io.File
import java.nio.file.Paths

fun main() {
    first2()
}
data class Turn(val red: Int, val green: Int, val blue: Int) {

    fun isBigger(other: Turn): Boolean {
        return green > other.green || blue > other.blue || red > other.red
    }

    fun power(): Int {
        return red * green * blue
    }

}

data class Game(val id: Int, val turns: List<Turn>)

fun parseGame(game: String): Game {
    // take `Game <n>`
    game.split(':', limit = 2).also { (gameStr, turnStr) ->
        val id = gameStr.removePrefix("Game ").toInt()
        val turns = turnStr.split(';').map {turn ->
            val components: MutableMap<String, Int> = mutableMapOf()
            turn.split(',').map { component ->
                component.trim().split(' ', limit = 2).also { (numStr, type) ->
                    components[type] = numStr.toInt()
                }
            }
            Turn(components.getOrDefault("red", 0), components.getOrDefault("green", 0),
                components.getOrDefault("blue", 0))
        }
        println("game:  " + Game(id, turns))
        return Game(id, turns)
    }
}
fun first2() {
    val highestTurn = Turn(12, 13, 14)
    var path = Paths.get("").toAbsolutePath().toString()
    path += "/src/main/kotlin/2023Challange/data"
    val result = File("$path/day2.txt").readText().split('\n').sumOf {str ->
        val game = parseGame(str)
        game.turns.forEach { turn ->
            if(turn.isBigger(highestTurn))
                return@sumOf 0
        }
        game.id
    }

    println(result)

    return
}




/*
class day2 {
    var validId: MutableList<Int> = mutableListOf()
    fun dayTwo() {
        var path = Paths.get("").toAbsolutePath().toString()
        path += "/src/main/kotlin/2023Challange/data"
        val text: String = File("$path/day2.txt").readText()
        val formattedText: MutableList<String> = text.split("\n").toMutableList()
        countCubes(text, "green", 13)

    }
    fun splitter(input: String): List<List<String>> {
        val list: List<String> = input.split("\n")
        val returnValue: MutableList<List<String>> = mutableListOf()

        for (i in 0 until list.size) {
            returnValue.add(list[i].split(";"))
        }

        return returnValue
    }

    fun countCubes(input: String, color: String, amount: Int) {
        val withoutGame: String = input.replace(("Game (\\d{1,2}):").toRegex(), "")

        val game: List<List<String>> = splitter(withoutGame)
        println(withoutGame)
        println("game::   " + game)
        println(withoutGame.split(";"))
    }
}
*/
