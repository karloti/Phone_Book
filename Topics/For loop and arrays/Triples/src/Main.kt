fun main() = List(readLine()!!.toInt()) { readLine()!!.toInt() }
    .windowed(3)
    .map { it[0] + 1 == it[1] && it[1] + 1 == it[2] }
    .filter { it }
    .size
    .let(::println)
