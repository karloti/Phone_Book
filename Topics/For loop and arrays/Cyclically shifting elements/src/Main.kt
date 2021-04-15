fun main() = List(readLine()!!.toInt()) { readLine()!! }
    .run { last() + " " + dropLast(1).joinToString(" ") } // rotate right with 1
    .let(::println)