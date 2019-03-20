package ECOO.e15.r1

import java.util.*

fun main() {
    val input = Scanner(System.`in`)
    fun nextFloat() = input.nextFloat()
    fun nextInt() = input.nextInt()
    fun next() = input.next()

    val words = arrayOf("ook", "ookook", "oog", "ooga", "ug", "mook", "mookmook", "oogam", "oogum", "ugug")

    repeat(10) {
        val line = next()!!
        val possibility = Array(line.length + 1) { 0 }
        possibility[0] = 1

        for (i in 0 until line.length) for (word in words) {
            if (line.indexOf(word, i) == i) {
                possibility[i + word.length] += possibility[i]
            }
        }

        println(possibility[line.length])
    }

}