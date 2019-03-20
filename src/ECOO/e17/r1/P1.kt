package ECOO.e17.r1

import java.util.*

fun main() {
    val input = Scanner(System.`in`)
    val nf={input.nextFloat()}
    val ni = {input.nextInt()}
    val ns = {input.next()}

    repeat(10){
        val needed=ni()*2
        val y1=nf()
        val y2=nf()
        val y3=nf()
        val y4=nf()
        val peopleAmount=ni()
        val moneyRaised=(y1*peopleAmount).toInt()*12+(y2*peopleAmount).toInt()*10+(y3*peopleAmount).toInt()*7+(y4*peopleAmount).toInt()*5

        if (moneyRaised<needed){
            println("YES")
        }else{
            println("NO")
        }

    }
}