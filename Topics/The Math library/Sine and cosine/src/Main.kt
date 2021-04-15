import kotlin.math.cos
import kotlin.math.sin

fun main() = readLine()!!
    .toDouble()
    .let { sin(it) - cos(it) }
    .let(::println)