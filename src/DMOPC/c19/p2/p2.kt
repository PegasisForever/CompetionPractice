package DMOPC.c19.p2

import java.util.*

val input = Scanner(System.`in`)
fun next() = input.next()!!
fun nextFloat() = input.nextFloat()
fun nextInt() = input.nextInt()

abstract class Action(val damage: Int, val sendPlayer: Player, val receivePlayer: Player) {
    open fun onDo(actionBefore: Action?) {}
    open fun onNextRound(action: Action?) {}
}

class Attack(damage: Int, sendPlayer: Player, receivePlayer: Player) : Action(damage, sendPlayer, receivePlayer) {
    override fun onDo(actionBefore: Action?) {
        if (actionBefore !is Dodge) {
            receivePlayer.health -= damage
        }
    }
}

class Dodge(damage: Int, sendPlayer: Player, receivePlayer: Player) : Action(damage, sendPlayer, receivePlayer) {
    override fun onNextRound(action: Action?) {
        if (action !is Attack) {
            sendPlayer.health -= damage
        }
    }
}

data class Player(var health: Int) {
    fun isDead() = health <= 0
}

interface ReadListScope<T> {
    fun yield(value: T)
}

fun <T> readList(count: Int, block: ReadListScope<T>.() -> Unit): ArrayList<T> {
    val list = arrayListOf<T>()
    val scope = object : ReadListScope<T> {
        override fun yield(value: T) {
            list += value
        }
    }
    repeat(count) {
        block(scope)
    }
    return list
}


fun main(args: Array<String>) {
    val actionCount = nextInt()
    val me = Player(nextInt())
    val oppo = Player(me.health)

    val myActions = readList<Action>(actionCount) {
        if (next() == "A") {
            yield(Attack(nextInt(), me, oppo))
        } else {
            yield(Dodge(nextInt(), me, oppo))
        }
    }

    val oppoActions = readList<Action>(actionCount) {
        if (next() == "A") {
            yield(Attack(nextInt(), oppo, me))
        } else {
            yield(Dodge(nextInt(), oppo, me))
        }
    }

    var lastAction: Action? = null
    repeat(actionCount * 2) {
        val isMyRound = it % 2 == 0
        val action = if (isMyRound) {
            myActions[it / 2]
        } else {
            oppoActions[it / 2]
        }

        lastAction?.onNextRound(action)
        action.onDo(lastAction)
        lastAction = action

        if (me.isDead()) {
            println("DEFEAT")
            return
        } else if (oppo.isDead()) {
            println("VICTORY")
            return
        }
    }
    lastAction?.onNextRound(null)
    when {
        me.isDead() -> println("DEFEAT")
        oppo.isDead() -> println("VICTORY")
        else -> println("TIE")
    }

}