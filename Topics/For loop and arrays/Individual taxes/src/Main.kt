fun main() {
    val n = readLine()!!.toInt()
    val a = IntArray(n) { readLine()!!.toInt() }
    for (i in a.indices) a[i] *= readLine()!!.toInt()
    println(a.indexOf(a.maxOrNull()!!) + 1)
}