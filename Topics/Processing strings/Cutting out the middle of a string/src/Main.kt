fun main() = readLine()!!
    .run { removeRange(lastIndex / 2, length / 2 + 1) }
    .let(::println)