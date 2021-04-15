fun main() {
    val grades = Array(4) { 0 }
    repeat(readLine()!!.toInt()) { grades[readLine()!!.toInt() - 2]++ }
    println(grades.joinToString(" "))
}
