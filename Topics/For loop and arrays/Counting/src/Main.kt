fun main() {
    val n = readLine()!!.toInt()
    val a = IntArray(n) { readLine()!!.toInt() }
    val x = readLine()!!.toInt()
    println(a.count { it == x })
}