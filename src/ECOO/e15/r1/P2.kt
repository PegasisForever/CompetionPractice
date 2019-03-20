package ECOO.e15.r1

import java.util.*

fun main() {
    val input = Scanner(System.`in`)
    val nextFloat = { input.nextFloat() }
    val nextInt = { input.nextInt() }
    val next = { input.next() }

    repeat(10) {
        val width = nextInt()
        var widthLeft = width
        input.nextLine()
        val line = input.nextLine()

        var words = line.split(" ")
        var firstWord: Boolean
        val newWords = ArrayList<String>()
        for (word in words) {
            if (word.length<=width){
                newWords.add(word)
            }else{
                val cutTimes=Math.ceil(word.length.toDouble()/width).toInt()
                repeat(cutTimes){
                    if (it==cutTimes-1){
                        newWords.add(word.substring(it*width))
                    }else {
                        newWords.add(word.substring(it * width, (it + 1) * width))
                    }
                }
            }
        }
        words=newWords

        for (word in words) {
            firstWord = width == widthLeft
            val length = word.getLeng(firstWord)
            if (length <= widthLeft) {
                if (!firstWord) print(" ")
                print(word)
                widthLeft -= length
            } else if (length > widthLeft && word.length <= width) {
                print("\n"+word)
                widthLeft=width-word.length
            }
        }
        println("\n=====")
    }
}

fun String.getLeng(first: Boolean): Int {
    return if (first) {
        length
    } else {
        length + 1
    }
}