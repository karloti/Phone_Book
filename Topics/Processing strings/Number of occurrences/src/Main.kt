fun variant1() {
    val st = readLine()!!
    val sub = readLine()!!
    var count = 0
    for (i in 0..st.lastIndex - sub.length + 1)
        if (st.substring(i, sub.length + i) == sub) count++
    println(count)
}

fun variant2() = println(readLine()!!.split(readLine()!!).size - 1)

fun main() {
    when ((1..2).random()) {
        1 -> variant1() // dynamic
        2 -> variant2() // functional
    }
}
