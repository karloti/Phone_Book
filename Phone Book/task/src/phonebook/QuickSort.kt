package phonebook

class QuickSort<T : Comparable<T>>(private val a: Array<T>) {
    private lateinit var tmp: T
    private fun swap(i: Int, j: Int) {
        tmp = a[i]; a[i] = a[j]; a[j] = tmp
    }

    init {
        sort(0, a.lastIndex)
    }

    private fun partition(lowIndex: Int, highIndex: Int): Int {
        val middleIndex = (lowIndex + highIndex) / 2
        var pivotIndex = when {
            a[lowIndex] <= a[highIndex] == a[highIndex] <= a[middleIndex] -> highIndex
            a[lowIndex] <= a[middleIndex] == a[middleIndex] <= a[highIndex] -> middleIndex
            else -> lowIndex
        }
        val pivot = a[pivotIndex]
        swap(pivotIndex, highIndex)
        pivotIndex = lowIndex
        for (i in lowIndex until highIndex)
            if (a[i] < pivot) swap(i, pivotIndex++)
        swap(highIndex, pivotIndex)
        return pivotIndex
    }

    private fun sort(lowIndex: Int, highIndex: Int) {
        if (lowIndex < highIndex) {
            val pivotIndex = partition(lowIndex, highIndex)
            sort(lowIndex, pivotIndex - 1)
            sort(pivotIndex + 1, highIndex)
        }
    }
}