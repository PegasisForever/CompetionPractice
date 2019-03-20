package ECOO.e18.r1

import java.util.*

fun main() {
    val input = Scanner(System.`in`)
    val nextFloat = { input.nextFloat() }
    val nextInt = { input.nextInt() }
    val next = { input.next() }


    repeat(10) {
        var i = 1
        var n = 1L
        var lastN = 0L
        val v = Vec(1, 0)
        var type: Int = 0
        val inputX=input.nextLong()
        val inputY=input.nextLong()

        while (true) {
            var x: Int
            var y: Int
            when (type) {
                0 -> {
                    x = -1
                    y = 0
                }
                1 -> {
                    x = -1
                    y = 1
                }
                2 -> {
                    x = 0
                    y = 1
                }
                else -> {
                    x = 0
                    y = 0
                }
            }
            val rect = Rect(v.x + n * x, v.y + n * y, n, n)
            when (type) {
                0 -> {
                    x = -1
                    y = -1
                }
                1 -> {
                    x = -1
                    y = 1
                }
                2 -> {
                    x = 1
                    y = 1
                }
                else -> {
                    x = 1
                    y = -1
                }
            }
            v.x += n * x
            v.y += n * y

            if (rect.inMe(inputX,inputY)){
                println(i)
                break
            }

            type++
            if (type > 3) type = 0
            val temp = lastN
            lastN = n
            n += temp
            i++
        }
    }
}

class Vec(var x: Long, var y: Long)

data class Rect(val x: Long, val y: Long, val w: Long, val h: Long) {
    fun inMe(x: Long, y: Long): Boolean {
        return x >= this.x && x <= this.x + w &&
                y >= this.y -h && y <= this.y
    }
}