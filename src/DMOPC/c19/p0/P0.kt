package DMOPC.c19.p0

import java.util.*

val input = Scanner(System.`in`)
fun next() = input.next()!!
fun nextFloat() = input.nextFloat()
fun nextInt() = input.nextInt()

fun main(args: Array<String>) {
    val lines = nextInt()
    val cutOff = nextInt()
    repeat(lines) {
        val name = next()
        if (nextInt() > cutOff) {
            println("$name will advance")
        } else {
            println("$name will not advance")
        }
    }
}