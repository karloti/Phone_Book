import kotlin.math.sqrt

fun main() {
    val (a, b, c) = List(3) { readLine()!!.toDouble() }
    sqrt(b * b - 4 * a * c)
        .let { listOf((-b - it) / (2 * a), (-b + it) / (2 * a)) }
        .sorted()
        .joinToString(" ")
        .let(::println)
}