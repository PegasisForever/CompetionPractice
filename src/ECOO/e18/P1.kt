package ECOO.e18

import java.util.*

fun main() {
    val input = Scanner(System.`in`)
    val nextFloat = { input.nextFloat() }
    val nextInt = { input.nextInt() }
    val next = { input.next() }

    repeat(10){
        val T=nextInt()
        val N=nextInt()
        var boxLeft=0
        var day=0
        while (day<N || boxLeft!=0){
            if (day<N && next()=="B"){
                boxLeft+=T
            }
            if (boxLeft>0) boxLeft--
            day++
        }


        println(day-N)
    }

}