package ECOO.e16.r1

import com.sun.javafx.geom.Vec2d
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

fun main() {
    val input = Scanner(System.`in`)
    val houseList = ArrayList<House>()
    val newComerPoses = ArrayList<Vec2d>()
    val circleOffsets = ArrayList<Vec2d>()
    for (x in -50..50) for (y in -50..50) {
        if (Math.sqrt((x * x + y * y).toDouble()) <= 50) {
            circleOffsets.add(Vec2d(x.toDouble(), y.toDouble()))
        }
    }

    repeat(10) {
        newComerPoses.clear()
        houseList.clear()

        val newComerArea = Vec2d(input.nextDouble(), input.nextDouble())
        for (offsets in circleOffsets) {
            newComerPoses.add(Vec2d(newComerArea.x + offsets.x, newComerArea.y + offsets.y))
        }

        repeat(100) {
            houseList.add(House(Vec2d(input.nextDouble(), input.nextDouble()), input.next() == "D"))
        }

        var Dtimes = 0
        var Rtimes = 0

        for (newComerPos in newComerPoses) {
            for (house in houseList) {
                house.distance = newComerPos.distance(house.pos)
            }
            houseList.sortWith(Comparator.comparingDouble { value -> value.distance })

            var Dvote = 0
            var Rvote = 0
            for (i in 0 until houseList.size) {
                if (i < 3) {
                    if (houseList[i].blv){
                        Dvote++
                    }else{
                        Rvote++
                    }
                }else if (houseList[i].distance==houseList[i-1].distance){
                    if (houseList[i].blv){
                        Dvote++
                    }else{
                        Rvote++
                    }
                }else{
                    break
                }
            }
            if (Dvote>=Rvote){
                Dtimes++
            }else{
                Rtimes++
            }
        }
        println(String.format("%.1f", Dtimes.toDouble()*100/(Dtimes+Rtimes)))
    }
}

//true: D false:R
data class House(var pos: Vec2d, var blv: Boolean, var distance: Double = Double.MAX_VALUE)