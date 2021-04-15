import java.math.RoundingMode

fun main() = Array(3) { readLine()!!.toBigDecimal() }
    .sumOf { it }
    .divide(3.toBigDecimal(), 0, RoundingMode.DOWN)
    .let(::println)