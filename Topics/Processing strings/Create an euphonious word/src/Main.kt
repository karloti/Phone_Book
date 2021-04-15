fun main() = Regex("[aeiouy]{3,}|(?:(?![aeiouy])[a-z]){3,}").findAll(readLine()!!) // 10x to regex101.com
    .sumBy { (it.value.length - 1) / 2 }
    .let(::println)