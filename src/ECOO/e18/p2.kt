package ECOO.e18

import ECOO.toString
import java.util.*
import kotlin.math.min

fun main() {
    val input = Scanner(System.`in`)
    val nextFloat = { input.nextFloat() }
    val nextInt = { input.nextInt() }
    val next = { input.next() }

    val troubleRoads = ArrayList<Int>()


    repeat(10){
        var minR=1000
        troubleRoads.clear()
        repeat(nextInt()){
            val id=nextInt()
            var thisMinR=1000
            repeat(nextInt()){
                thisMinR= min(nextInt(),thisMinR)
            }
            if (thisMinR<minR){
                minR=thisMinR
                troubleRoads.clear()
                troubleRoads.add(id)
            }else if (thisMinR==minR){
                troubleRoads.add(id)
            }
        }


        print("$minR ${troubleRoads.toString("{",",","}")}")
    }
}