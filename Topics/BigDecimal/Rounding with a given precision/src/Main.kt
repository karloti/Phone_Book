import java.math.RoundingMode

fun main() = readLine()!!.toBigDecimal().setScale(readLine()!!.toInt(), RoundingMode.HALF_DOWN).let(::println)