fun main() = readLine()!!.split(" ").maxByOrNull { it.length }.let(::println)