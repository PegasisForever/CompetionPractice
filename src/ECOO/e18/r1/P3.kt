package ECOO.e18.r1

import ECOO.isOdd
import ECOO.toString
import java.lang.StringBuilder
import java.util.*

fun main() {
    val input = Scanner(System.`in`)
    val nextFloat = { input.nextFloat() }
    val nextInt = { input.nextInt() }
    val next = { input.next() }

    val codes = ArrayList<String>()
    val notMatch = ArrayList<Int>()


    repeat(10){
        codes.clear()
        notMatch.clear()
        val lines=nextInt()
        val X=nextInt()
        val Y=nextInt()
        val Z=next()
        repeat(lines){
            val line=next()
            val sb=StringBuilder()
            for (char in line){
                var num=char.toString().toInt()
                if (num==0){
                    sb.append(Z)
                }else if (num.isOdd()){
                    num-=Y
                    if (num<0) num=0
                    sb.append(num)
                }else{
                    sb.append(num+X)
                }
            }
            codes.add(sb.toString())
        }
        next()
        repeat(lines){
            if (codes[it]!=next()) notMatch.add(it+1)
        }
        next()

        if (notMatch.size>0){
            println("FAIL: ${notMatch.toString("",",","")}")
        }else{
            println("MATCH")
        }
    }
}