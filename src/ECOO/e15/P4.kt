package ECOO.e15

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val input = Scanner(System.`in`)
    fun nextFloat() = input.nextFloat()
    fun nextInt() = input.nextInt()
    fun next() = input.next()

    val words = arrayOf(
            "ook",
            "ookook",
            "oog",
            "ooga",
            "ug",
            "mook",
            "mookmook",
            "oogam",
            "oogum",
            "ugug")
    val possibleLines = ArrayList<String>()

    repeat(10) {
        possibleLines.clear()
        possibleLines.add(next())
        var posibility = 0

        val newPossible = ArrayList<String>()
        do {
            for (line in possibleLines) for (word in words) {
                if (line.indexOf(word) == 0) {
                    if (word.length == line.length) {
                        posibility++
                    } else {
                        newPossible.add(line.substring(word.length))
                    }
                }
            }
            possibleLines.clear()
            possibleLines.addAll(newPossible)
            newPossible.clear()
        }while (possibleLines.size>0)

        println(posibility)
    }

}