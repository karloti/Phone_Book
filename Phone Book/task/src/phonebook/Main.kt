package phonebook

import java.io.File
import java.lang.System.currentTimeMillis
import kotlin.math.roundToInt
import kotlin.math.sqrt

var timeBubbleSort: Long? = null
var timeQuickSort: Long? = null
var timeToMap: Long? = null
var timeLinearFilter: Long? = null
var timeJumpFilter: Long? = null
var timeBinaryFilter: Long? = null
var timeMapFilter: Long? = null

data class Contact(val number: String, val fullName: String) : Comparable<Contact> {
    override fun compareTo(other: Contact) = fullName.compareTo(other.fullName)
}

fun List<Contact>.bubbleSort(maxTime: Long): List<Contact>? {
    val timeStart = currentTimeMillis()
    val contacts = toMutableList()
    for (pass in 0 until contacts.lastIndex) {
        // A single pass of bubble sort
        for (currentPosition in 0 until contacts.lastIndex - pass) {
            // When maxTime is exceeded
            if (currentTimeMillis() - timeStart > maxTime) {
                timeBubbleSort = currentTimeMillis() - timeStart
                return null
            }
            // This is a single step
            if (contacts[currentPosition] > contacts[currentPosition + 1]) {
                val tmp = contacts[currentPosition]
                contacts[currentPosition] = contacts[currentPosition + 1]
                contacts[currentPosition + 1] = tmp
            }
        }
    }
    timeBubbleSort = currentTimeMillis() - timeStart
    return contacts
}

fun List<Contact>.quickSort(): List<Contact> = toTypedArray()
    .also {
        val timeStart = currentTimeMillis()
        QuickSort(it)
        timeQuickSort = currentTimeMillis() - timeStart
    }
    .asList()

fun List<Contact>.toMap(): Map<String, Contact> {
    val timeStart = currentTimeMillis()
    val result = associate { it.fullName to it }
    timeToMap = currentTimeMillis() - timeStart
    return result
}

fun List<Contact>.jumpSearch(contactName: String): Contact? {
    // Finding block size to be jumped
    val blockSize = sqrt(size.toDouble()).roundToInt()

    // Finding the block where element is present (if it is present)
    var prev = 0
    var count = blockSize
    while (this[count.coerceAtMost(size) - 1].fullName < contactName) {
        prev = count
        count += blockSize
        if (prev >= size) return null
    }

    // Doing a linear search for x in block beginning with prev.
    while (this[prev].fullName < contactName) {
        prev++

        // If we reached next block or end of array, element is not present.
        if (prev == count.coerceAtMost(size)) return null
    }

    // If element is found
    return if (this[prev].fullName == contactName) this[prev] else null
}

tailrec fun List<Contact>.binarySearch(key: String, fromIndex: Int = 0, toIndex: Int = lastIndex): Contact? {
    val middleIndex = (fromIndex + toIndex) / 2
    val middleValue = this[middleIndex].fullName
    return when {
        fromIndex > toIndex -> null // key not found.
        key < middleValue -> binarySearch(key, fromIndex, middleIndex - 1)
        key > middleValue -> binarySearch(key, middleIndex + 1, toIndex)
        else -> return this[middleIndex] // key found
    }
}

fun List<Contact>.linearFilter(contactsToFind: List<String>): List<Contact> {
    val timeStart = currentTimeMillis()
    val mutableList = mutableListOf<Contact>()
    for (contactName in contactsToFind)
        for (contact in this)
            if (contactName == contact.fullName) {
                mutableList += contact
                break
            }
    timeLinearFilter = currentTimeMillis() - timeStart
    return mutableList
}

fun List<Contact>.jumpFilter(contactsToFind: List<String>): List<Contact> {
    val timeStart = currentTimeMillis()
    val result = contactsToFind.mapNotNull { jumpSearch(it) }
    timeJumpFilter = currentTimeMillis() - timeStart
    return result
}

fun List<Contact>.binaryFilter(contactsToFind: List<String>): List<Contact> {
    val timeStart = currentTimeMillis()
    val result = contactsToFind.mapNotNull(::binarySearch)
    timeBinaryFilter = currentTimeMillis() - timeStart
    return result
}

fun Map<String, Contact>.mapFilter(contactsToFind: List<String>): List<Contact> {
    val timeStart = currentTimeMillis()
    val result = contactsToFind.mapNotNull(::get)
    timeMapFilter = currentTimeMillis() - timeStart
    return result
}

fun main() {
    val file1 = File("c:\\kotlin\\directory.txt")
    val file2 = File("c:\\kotlin\\find.txt")
    val regex = Regex("(\\d+) (.+)")

    val contacts = regex.findAll(file1.readText()).map { Contact(it.groupValues[1], it.groupValues[2]) }.toList()
    val contactsToFind = file2.readLines()

    "Start searching (linear search)...".let(::println)
    val resultLinearFilter = contacts.linearFilter(contactsToFind)
    "Found %1\$d / %2\$d entries. Time taken: %3\$tM min. %3\$tS sec. %3\$tL ms.\n"
        .format(resultLinearFilter.size, contactsToFind.size, timeLinearFilter)
        .let(::println)

    "Start searching (bubble sort + jump search)...".let(::println)
    val bubbleSort = contacts.bubbleSort(timeLinearFilter!! * 10)
    val result = bubbleSort?.jumpFilter(contactsToFind) ?: contacts.linearFilter(contactsToFind)
    "Found %1\$d / %2\$d entries. Time taken: %3\$tM min. %3\$tS sec. %3\$tL ms."
        .format(result.size, contactsToFind.size, timeBubbleSort!! + (timeJumpFilter ?: timeLinearFilter!!))
        .let(::println)
    "Sorting time: : %1\$tM min. %1\$tS sec. %1\$tL ms. %2\$s"
        .format(timeBubbleSort, if (bubbleSort == null) " - STOPPED, moved to linear search" else "")
        .let(::println)
    "Searching time: %1\$tM min. %1\$tS sec. %1\$tL ms.\n"
        .format(timeJumpFilter ?: timeLinearFilter!!)
        .let(::println)

    "Start searching (quick sort + binary search)...".let(::println)
    val quickSort = contacts.quickSort()
    val resultBinaryFilter = quickSort.binaryFilter(contactsToFind)
    "Found %1\$d / %2\$d entries. Time taken: %3\$tM min. %3\$tS sec. %3\$tL ms."
        .format(resultBinaryFilter.size, contactsToFind.size, timeQuickSort!! + timeBinaryFilter!!)
        .let(::println)
    "Sorting time: : %1\$tM min. %1\$tS sec. %1\$tL ms."
        .format(timeQuickSort)
        .let(::println)
    "Searching time: %1\$tM min. %1\$tS sec. %1\$tL ms.\n"
        .format(timeBinaryFilter)
        .let(::println)

    "Start searching (hash table)...".let(::println)
    val map = contacts.toMap()
    val resultMapFilter = map.mapFilter(contactsToFind)
    "Found %1\$d / %2\$d entries. Time taken: %3\$tM min. %3\$tS sec. %3\$tL ms."
        .format(resultMapFilter.size, contactsToFind.size, timeToMap!! + timeMapFilter!!)
        .let(::println)
    "Creating time: %1\$tM min. %1\$tS sec. %1\$tL ms."
        .format(timeToMap)
        .let(::println)
    "Searching time: %1\$tM min. %1\$tS sec. %1\$tL ms.\n"
        .format(timeMapFilter)
        .let(::println)
}