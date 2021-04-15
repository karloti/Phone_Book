import java.math.RoundingMode

val startingAmount = readLine()!!.toBigDecimal()
val yearlyPercent = readLine()!!.toBigDecimal().setScale(2)
val years = readLine()!!.toInt()
val bd100 = 100.toBigDecimal()
val finalAmount = (startingAmount * ((bd100 + yearlyPercent) / bd100).pow(years)).setScale(2, RoundingMode.CEILING)

fun main() = println("Amount of money in the account: $finalAmount")