package ECOO

import kotlin.math.pow
import kotlin.math.sqrt

fun List<Any>.toString(start: String, separate: String, end: String): String {
    val sb = StringBuilder()
    sb.append(start)
    for (i in 0 until size) {
        sb.append(this[i])
        if (i != size - 1) {
            sb.append(separate)
        }
    }
    sb.append(end)
    return sb.toString()
}

fun Int.isOdd() = this % 2 == 1

data class Vec2(var x: Double, var y: Double) {
    constructor(x: Float, y: Float) : this(x.toDouble(), y.toDouble())
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())
    constructor(x: Int, y: Double) : this(x.toDouble(), y.toDouble())
    constructor(x: Int, y: Float) : this(x.toDouble(), y.toDouble())
    constructor(x: Float, y: Int) : this(x.toDouble(), y.toDouble())
    constructor(x: Double, y: Int) : this(x.toDouble(), y.toDouble())
    constructor(x: Float, y: Double) : this(x.toDouble(), y.toDouble())
    constructor(x: Double, y: Float) : this(x.toDouble(), y.toDouble())

    fun distance(other: Vec2): Double {
        return sqrt((x - other.x).pow(2) + (y - other.y).pow(2))
    }
}