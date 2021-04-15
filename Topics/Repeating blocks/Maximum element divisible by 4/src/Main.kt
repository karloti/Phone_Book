fun main() {
    val numbers = readLine()!!.toInt()
    var number: Int
    var max = 0

    repeat(numbers) {
        number = readLine()!!.toInt()
        if (number % 4 == 0 && number > max) max = number
    }

    println(max)
}