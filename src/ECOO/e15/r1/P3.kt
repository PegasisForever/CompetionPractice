package ECOO.e15.r1

import ECOO.Vec2
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val input = Scanner(System.`in`)
    fun nextFloat() = input.nextFloat()
    fun nextInt() = input.nextInt()
    fun next() = input.next()

    val sq3=Math.sqrt(3.0)
    val triOffset=ArrayList<Vec2>()
    triOffset.add(Vec2(0,0))//1
    triOffset.add(Vec2(-1,sq3))//2
    triOffset.add(Vec2(1,sq3))//3
    triOffset.add(Vec2(-2,2*sq3))//4
    triOffset.add(Vec2(0,2*sq3))//5
    triOffset.add(Vec2(2,2*sq3))//6
    triOffset.add(Vec2(-3,3*sq3))//7
    triOffset.add(Vec2(-1,3*sq3))//8
    triOffset.add(Vec2(1,3*sq3))//9
    triOffset.add(Vec2(3,3*sq3))//10

    repeat(5){
        val firstX= precisedFloat(nextFloat(), nextInt())
        val firstY= precisedFloat(nextFloat(), nextInt())
        val length= precisedFloat(nextFloat(), nextInt()) /3/2
        val precision=nextInt()

        repeat(2){

        }
    }
}

fun precisedFloat(base:Float,times:Int) = base*Math.pow(10.0,times.toDouble()).toFloat()