package ECOO.e16

import java.util.*

fun main(){
    val input = Scanner(System.`in`)
    val spinNums= LinkedHashSet<Int>()
    var expected: Int
    var pass: Boolean

    repeat(10){
        spinNums.clear()
        repeat(input.nextInt()){
            spinNums.add(input.nextInt())
        }
        repeat(5){
            expected=input.nextInt()
            pass= false
            out@ for (i1 in spinNums) for (i2 in spinNums) for (i3 in spinNums){
                if (i1+i2+i3==expected){
                    pass=true
                }else if (i1*i2+i3==expected){
                    pass=true
                }else if (i1*i2*i3==expected){
                    pass=true
                }else if ((i1+i2)*i3==expected){
                    pass=true
                }
                if (pass){
                    break@out
                }
            }
            print(if(pass) "T" else "F")
        }
        println()
    }
}

