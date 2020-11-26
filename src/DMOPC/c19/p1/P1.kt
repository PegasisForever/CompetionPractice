package DMOPC.c19.p1

import java.util.*

val input = Scanner(System.`in`)
fun next() = input.next()!!
fun nextFloat() = input.nextFloat()
fun nextInt() = input.nextInt()

fun main(args: Array<String>) {
    val itemCount = nextInt()
    val assiCount = nextInt()
    var count = 0

    val items = sequence {
        repeat(itemCount) {
            yield(next())
        }
    }.toList()
    repeat(assiCount) {
        val requiredItems = nextInt()
        var pass = true
        if (requiredItems > itemCount) pass = false
        repeat(requiredItems) {
            if (pass) {
                if (!items.contains(next())) pass = false
            } else {
                next()
            }
        }
        if (pass) count++
    }

    print(count)
}