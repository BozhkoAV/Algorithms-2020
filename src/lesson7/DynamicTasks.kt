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
//Трудоемкость = O(n^2)
//Ресурсоемкость = O(n)
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    if (list.isEmpty()) return emptyList()
    //Массив, в ктором хранится длина наибольшей возрастающей подпоследовательности, оканчивающейся на элементе с индексом i
    val maxLenIncSubSeqForElement = mutableListOf<Int>()
    //Массив, в котором хранится позиция элемента идущего перед текущим в максимальной возрастающей последовательности
    val previous = mutableListOf<Int>()

    for (i in list.indices) {
        maxLenIncSubSeqForElement.add(i, 1)
        previous.add(i, -1)
        for (j in 0 until i) {
            if (list[j] < list[i] && maxLenIncSubSeqForElement[j] + 1 > maxLenIncSubSeqForElement[i]) {
                maxLenIncSubSeqForElement[i] = maxLenIncSubSeqForElement[j] + 1
                previous[i] = j
            }
        }
    }

    //находим длину наибольшего возрастающего ряда
    //находим индекс элемента, которым будет заканчиваться эта последовательность
    var position = 0
    var maxLength = maxLenIncSubSeqForElement[0]
    for (i in list.indices) {
        if (maxLenIncSubSeqForElement[i] > maxLength) {
            position = i
            maxLength = maxLenIncSubSeqForElement[i]
        }
    }

    //восстановление последовательности с помощью previous
    val answer = mutableListOf<Int>()
    while (position != -1) {
        answer.add(list[position])
        position = previous[position]
    }
    return answer.reversed()
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