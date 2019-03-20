package ECOO.e17.r1

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val input = Scanner(System.`in`)
    val nextFloat = { input.nextFloat() }
    val nextInt = { input.nextInt() }
    val next = { input.next() }

    val names = ArrayList<String>()
    val sortedNameMap=HashMap<String,Int>()


    repeat(10) {
        names.clear()
        repeat(nextInt()) {
            names.add(next())
        }

        val sortedNames = names.clone() as ArrayList<String>
        sortedNames.sortWith(kotlin.Comparator.naturalOrder())


        var minStep=names.size
        var del=""
        repeat(names.size){
            sortedNameMap.clear()
            val tempNames=names.clone() as ArrayList<String>
            val removed=tempNames[it]
            tempNames.removeAt(it)
            //
            val tempSortedNames=sortedNames.clone() as ArrayList<String>
            tempSortedNames.remove(removed)
            repeat(tempSortedNames.size){
                sortedNameMap[tempSortedNames[it]] = it
            }


            var step=0
            var done: Boolean
            do {
                done=false
                for (i in 0 until tempNames.size){
                    val expectedPos=sortedNameMap[tempNames[i]]!!
                    if (expectedPos!=i){
                        tempNames.swap(expectedPos,i)
                        step++
                        done=true
                    }
                }
            } while (done)

            if (step<minStep){
                minStep=step
                del=removed
            }
        }

        println("$minStep $del")

    }
}

fun ArrayList<String>.swap(pos1: Int, pos2: Int) {
    val temp=this[pos1]
    this[pos1]=this[pos2]
    this[pos2]=temp
}