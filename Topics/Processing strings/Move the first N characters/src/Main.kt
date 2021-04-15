fun main() = readLine()!!.split(" ").let { it[0] to it[1].toInt() }.let { println(it.first.drop(it.second) + it.first.take(it.second)) }
// Ugly, but one row expression without val/var
