package ECOO.e15

import java.util.*
import kotlin.collections.HashMap

fun main() {
    val input = Scanner(System.`in`)
    val nextFloat = { input.nextFloat() }
    val nextInt = { input.nextInt() }
    val next = { input.next() }

    val map=HashMap<String,Int>()

    repeat(10){
        map.clear()
        map.put("red",0)
        map.put("orange",0)
        map.put("blue",0)
        map.put("green",0)
        map.put("yellow",0)
        map.put("pink",0)
        map.put("violet",0)
        map.put("brown",0)

        while (!input.hasNext("end")){
            val color=next()!!
            map[color]= map[color]!! +1
        }
        next()
        next()
        next()

        var time=0
        map.forEach { color, num ->
            if (color!="red"){
                time+=Math.ceil(num.toDouble()/7).toInt()*13
            }else{
                time+=num*16
            }
        }

        println(time)
    }

}