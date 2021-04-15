fun main() {
    readLine()
    val arr = readLine()!!.split(" ").map(String::toInt)
    val (a, b) = readLine()!!.split(" ").map(String::toInt)

    println(if (arr.zipWithNext { i, j -> a to b == i to j || a to b == j to i }
                    .firstOrNull { it } != null) "NO" else "YES")
}
