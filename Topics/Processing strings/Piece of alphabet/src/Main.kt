fun main() = readLine()!!
    .asSequence()
    .zipWithNext { a, b -> a.isLetter() && a == b - 1 }
    .contains(false)
    .not()
    .let(::println)
