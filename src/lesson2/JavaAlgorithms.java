package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) throws IOException, NotImplementedError{
        // Трудоёмкость - O(n)
        // Ресурсоёмкость - O(n-1)
        File in = new File(inputName);
        FileReader fileReader = new FileReader(in);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();

        Scanner scanner = new Scanner(in);
        int count = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            count++;
        }

        if (count < 2) throw new NotImplementedError();

        int[] deltaPrice = new int[count - 1];
        int i = 0;

        int first = Integer.parseInt(line);
        line = reader.readLine();
        int second;
        do {
            second = Integer.parseInt(line);
            deltaPrice[i] = second - first;
            first = second;
            i++;
            line = reader.readLine();
        } while (line != null);

        int maxSum = 0;
        int sum = 0;
        int startIndex = 0;
        int from = 0;
        int to = 0;

        for (int j = 0; j < deltaPrice.length; j++) {
            if (sum == 0) startIndex = j;
            sum += deltaPrice[j];
            if (sum > maxSum) {
                from = startIndex;
                to = j;
                maxSum = sum;
            }
            if (sum < 0) sum = 0;
        }

        return new Pair<>(from + 1, to + 2);
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     *
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        // Трудоёмкость - O(n)
        // Ресурсоёмкость - O(1)
        if ((menNumber > 0) && (choiceInterval > 0)) {
            if (menNumber == 1) {
                return 1;
            } else {
                int result = 0;
                for (int i = 1; i <= menNumber; i++) {
                    result = (result + choiceInterval) % i;
                }
                return result + 1;
            }
        } else {
            throw new NotImplementedError();
        }
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String firs, String second) {
        throw new NotImplementedError();
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        if (limit <= 1) return 0;
        ArrayList<Integer> primes = new ArrayList<>();
        primes.add(2);
        for (int i = 3; i <= limit; i++) {
            if (i > 10) {
                if (i % 2 == 0 || i % 5 == 0) continue;
            }
            block:
            {
                for (int j : primes) {
                    if (i % j == 0) break block;
                }
                primes.add(i);
            }
        }
        return primes.size();
    }
}
