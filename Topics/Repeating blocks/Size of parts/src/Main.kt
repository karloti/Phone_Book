fun main() = List(readLine()!!.toInt()) { readLine()!!.toInt() }
    .groupingBy { it }
    .eachCount()
    .let { listOf(it[1], it[2], it[0]) }
    .joinToString(" ")
    .let(::println)