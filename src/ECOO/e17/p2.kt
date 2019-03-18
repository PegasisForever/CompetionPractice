package ECOO.e17

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val input = Scanner(System.`in`)
    val nextFloat = { input.nextFloat() }
    val nextInt = { input.nextInt() }
    val next = {input.next()}


    val chocolates=ArrayList<Chocolate>()
    val winner=ArrayList<Chocolate>()

    repeat(10) {
        chocolates.clear()
        winner.clear()
        repeat(nextInt()) {
            val newChoco=Chocolate(next(),0, arrayListOf(0f,0f,0f))
            while (input.hasNext("J")){
                next()
                newChoco.judges++

                newChoco.scores[0]=newChoco.scores[0]+nextInt()
                newChoco.scores[1]=newChoco.scores[1]+nextInt()
                newChoco.scores[2]=newChoco.scores[2]+nextInt()
            }
            newChoco.calculate()
            chocolates.add(newChoco)
        }

        for (chocolate in chocolates){
            if (winner.isEmpty()){
                winner.add(chocolate)
            }else if (chocolate.total>winner[0].total) {
                winner.clear()
                winner.add(chocolate)
            }else if (chocolate.total==winner[0].total){
                for (i in chocolate.scores.size-1 downTo 0){
                    if (chocolate.scores[i]>winner[0].scores[i]){
                        winner.clear()
                        winner.add(chocolate)
                        break
                    }else if (chocolate.scores[i]<winner[0].scores[i]) {
                        break
                    }else if (chocolate.scores[i]==winner[0].scores[i] && i==chocolate.scores.size-1){
                        winner.add(chocolate)
                    }
                }
            }

        }

        for (i in 0 until winner.size){
            print(winner[i].name)
            if (i!=winner.size-1){
                print(",")
            }
        }
        println()

        if (next()!="*"){
            throw Throwable()
        }
    }

}

class Chocolate(val name: String, var judges: Int, var scores: ArrayList<Float>) {
    var total:Float=0f

    fun calculate() {
        total=scores.sum()
        for (i in 0 until scores.size){
            scores[i]=scores[i]/judges
        }
    }
}