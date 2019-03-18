package ECOO.e16

import java.util.*

fun main(){
    val input = Scanner(System.`in`)
    val percentages= arrayListOf(0,0,0,0)
    var studentPassed: Int
    var totalMark:Float

    repeat(10){
        studentPassed=0
        repeat(4){
            percentages[it]=input.nextInt()
        }
        repeat(input.nextInt()){
            totalMark=0f
            repeat(4){
                totalMark+=input.nextInt().toFloat()*percentages[it]/100f
            }
            if (totalMark>=50){
                studentPassed++
            }
        }
        println(studentPassed)
    }
}