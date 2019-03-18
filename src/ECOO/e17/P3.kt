package ECOO.e17

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val input = Scanner(System.`in`)
    val nextFloat = { input.nextFloat() }
    val nextInt = { input.nextInt() }
    val next = {input.next()}

    val mountains = ArrayList<Mountain>()

    repeat(10){
        mountains.clear()
        repeat(nextInt()){
            val newM=Mountain(nextInt(),it)
            for (mountain in mountains){
                if (isVisible(mountains,mountain,newM)){
                    mountain.view++
                    newM.view++
                }
            }
            mountains.add(newM)
        }

        mountains.sortWith(kotlin.Comparator.comparingInt { value -> -value.view })

        println(mountains[0].position+1)

    }

}

fun isVisible(list:ArrayList<Mountain>,m1:Mountain,m2:Mountain):Boolean {
    val a=(m1.height-m2.height).toFloat()/(m1.position-m2.position)
    val b=m1.height-a*m1.position

    for (i in m1.position+1 until m2.position){
        if (list[i].height>=a*i+b){
            return false
        }
    }
    return true
}
data class Mountain(var height:Int,var position:Int,var view:Int=0)