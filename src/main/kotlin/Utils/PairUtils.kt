@file:Suppress("unused")

package Utils

fun <T> Pair<T, T>.equals() = this.first == this.second

fun <T> List<T>.toPair(): Pair<T, T> = this[0] to this[1]

fun <T> Iterable<T>.toPair(): Pair<T, T> = this.toList().toPair()