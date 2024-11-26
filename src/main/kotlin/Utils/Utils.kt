@file:Suppress("unused")

package Utils

import java.math.BigInteger

fun <T : Any> List<T>.splitBy(deliminator: T): List<List<T>> {
    return this.fold(mutableListOf(mutableListOf<T>())) { lists, current ->
        if (current == deliminator) {
            lists.add(mutableListOf())
        } else {
            lists.last().add(current)
        }
        lists
    }
}

fun Collection<Int>.multiply() = this.reduce { acc, i -> acc * i }

fun Collection<Long>.multiply() = this.reduce { acc, i -> acc * i }

fun Sequence<BigInteger>.sum(): BigInteger = this.fold(BigInteger.ZERO) { sum, next -> sum + next }

fun Iterable<BigInteger>.sum(): BigInteger = this.fold(BigInteger.ZERO) { sum, next -> sum + next }

fun <T> Iterable<T>.allEqual(): Boolean = this.all { it == this.first() }

fun Collection<String>.filterNotBlank(): List<String> = this.filter(String::isNotBlank)
