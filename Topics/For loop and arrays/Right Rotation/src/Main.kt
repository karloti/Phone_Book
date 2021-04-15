fun main() {
    val n = readLine()!!.toInt()
    val elements = List(n) { readLine()!! }
    val shift = readLine()!!.toInt()
    val elementsShift = List(n) { elements[(shift + it) % n] }
    println(elementsShift.joinToString(" "))
}
