@file:Suppress("UNUSED_PARAMETER")

package lesson7

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
// Трудоёмкость - O(first.length * second.length)
// Ресурсоёмкость - O(first.length * second.length)
fun longestCommonSubSequence(first: String, second: String): String {
    val storage = hashMapOf((0 to 0) to 0)
    fillStorage(first.length, second.length, storage)
    for (i in 1..first.length) {
        for (j in 1..second.length) {
            fillStorage(i, j, first[i - 1], second[j - 1], storage)
        }
    }

    var result = ""
    var firstLength = first.length
    var secondLength = second.length

    while (firstLength > 0 && secondLength > 0) {
        if (first[firstLength - 1] == second[secondLength - 1]) {
            result += first[firstLength - 1]
            firstLength--
            secondLength--
        } else {
            if (storage[firstLength - 1 to secondLength]!! > storage[firstLength to secondLength - 1]!!) {
                firstLength--
            } else {
                secondLength--
            }
        }
    }
    return result.reversed()
}

fun fillStorage(firstSize: Int, secondSize: Int, storage: MutableMap<Pair<Int, Int>, Int> = hashMapOf()) {
    for (i in 1..firstSize) {
        storage[i to 0] = 0
    }
    for (j in 1..secondSize) {
        storage[0 to j] = 0
    }
}

fun fillStorage(
    first: Int, second: Int, firstChar: Char, secondChar: Char,
    storage: MutableMap<Pair<Int, Int>, Int> = hashMapOf()
) {
    if (firstChar == secondChar) {
        storage[first to second] = storage[first - 1 to second - 1]!! + 1
    } else {
        storage[first to second] = Math.max(storage[first to second - 1]!!, storage[first - 1 to second]!!)
    }
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
//Трудоемкость = O(n*log(n))
//Ресурсоемкость = O(n)
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    if (list.isEmpty()) return emptyList()
    //Массив, в котором хранится наименьший последний эелемент для последовательности длины i
    val minLastElementOfSubSeq = mutableListOf<Int>()
    //Массив, в котором хранится индекс элемента массива list, который предшествовал рассматриваемому в подпоследовательности
    val previous = mutableListOf<Int>()

    minLastElementOfSubSeq.add(0, Int.MAX_VALUE)
    for (i in 1..list.size) {
        minLastElementOfSubSeq.add(i, Int.MIN_VALUE)
        previous.add(i - 1, 0)
    }

    //Массив, в котором хранится индекс minLastElementOfSubSeq[i] в масссиве list
    val position = mutableListOf<Int>()
    position.add(0, -1)
    for (i in 1..list.size) {
        position.add(i, 0)
    }

    var maxLength = 0

    for (i in list.size - 1 downTo 0) {
        val j = binarySearch(minLastElementOfSubSeq, list[i])
        if (minLastElementOfSubSeq[j - 1] > list[i] && list[i] > minLastElementOfSubSeq[j]) {
            minLastElementOfSubSeq[j] = list[i]
            position[j] = i
            previous[i] = position[j - 1]
            maxLength = Math.max(maxLength, j)
        }
    }

    //восстановление последовательности с помощью previous и position
    val answer = mutableListOf<Int>()
    var currentPosition = position[maxLength]
    while (currentPosition != -1) {
        answer.add(list[currentPosition])
        currentPosition = previous[currentPosition]
    }
    return answer
}

fun binarySearch(list: List<Int>, value: Int): Int {
    var left = 0
    var right = list.size - 1
    var mid: Int

    while (left < right - 1) {
        mid = (left + right) / 2
        if (list[mid] > value) {
            left = mid
        } else {
            right = mid
        }
    }
    return right
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5