import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val a = scanner.nextInt()
    val b = scanner.nextInt()
    val c = scanner.nextInt()
    val n = scanner.nextInt()

    var password = ""

    for (i in 0 until a) password += 'A' + i % 26
    for (i in 0 until b) password += 'a' + i % 26
    for (i in 0 until c) password += '0' + i % 10

    for (i in 0 until n - a - b - c) password += '!' + i
    println(password)
}