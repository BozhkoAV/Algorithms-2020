package lesson1;

import kotlin.NotImplementedError;
import java.io.*;
import java.util.Scanner;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) throws NotImplementedError, IOException{
        // Трудоёмкость - O(n*log(n))
        // Ресурсоёмкость - O(n)
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

        int[] times = new int[count];
        int i = 0;

        while (line != null) {
            if (line.matches("^(0[1-9]|1[0-2])(:[0-5]\\d){2} [P|A]M$")) {
                String[] lineParts = line.substring(0, 8).split(":");
                int hours = Integer.parseInt(lineParts[0]);
                if (line.substring(9, 11).matches("AM")) {
                    if (hours == 12) {
                        lineParts[0] = "0";
                    }
                } else {
                    if (hours < 12) {
                        hours += 12;
                        lineParts[0] = Integer.toString(hours);
                    }
                }
                times[i] = Integer.parseInt(lineParts[0]) * 10000 + Integer.parseInt(lineParts[1]) * 100
                        + Integer.parseInt(lineParts[2]);
                i++;
            } else {
                if (!line.isEmpty()) {
                    throw new NotImplementedError();
                }
            }
            line = reader.readLine();
        }

        Sorts.quickSort(times);

        File out = new File(outputName);
        FileWriter fileWriter = new FileWriter(out);
        try(BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (int value : times) {
                String time;
                if ((value / 10000 < 13) && (value / 10000 > 0)) {
                    time = String.format("%02d:%02d:%02d ", value / 10000, value % 10000 / 100, value % 100);
                    if (value / 10000 == 12) {
                        time += "PM\n";
                    } else {
                        time += "AM\n";
                    }
                } else {
                    if (value / 10000 > 12) {
                        time = String.format("%02d:%02d:%02d ", value / 10000 - 12, value % 10000 / 100, value % 100);
                        time += "PM\n";
                    } else {
                        time = String.format("%02d:%02d:%02d ", value / 10000 + 12, value % 10000 / 100, value % 100);
                        time += "AM\n";
                    }
                }
                writer.write(time);
            }
        }
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException, NotImplementedError{
        // Трудоёмкость - O(n+k)
        // Ресурсоёмкость - O(n)
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

        int[] degrees = new int[count];
        int i = 0;

        while (line != null) {
            if (line.matches("^(([1-4]?\\d{1,2}.\\d)|(500.0))$")) {
                String[] lineParts = new String[2];
                lineParts[0] = line.substring(0, line.length() - 2);
                lineParts[1] = line.substring(line.length() - 1);
                degrees[i] = Integer.parseInt(lineParts[0]) * 10 + Integer.parseInt(lineParts[1]);
                i++;
            } else {
                if (line.matches("^-0.0$")) {
                    throw new NotImplementedError();
                } else {
                    if (line.matches("^-((1?\\d{1,2})|(2(([0-6]\\d)|(7[0-3])))).\\d$")) {
                        String[] lineParts = new String[2];
                        lineParts[0] = line.substring(1, line.length() - 2);
                        lineParts[1] = line.substring(line.length() - 1);
                        degrees[i] = (Integer.parseInt(lineParts[0]) * 10 + Integer.parseInt(lineParts[1])) * -1;
                        i++;
                    } else {
                        if (!line.isEmpty()) {
                            throw new NotImplementedError();
                        }
                    }
                }
            }
            line = reader.readLine();
        }

        for (int j = 0; j < degrees.length; j++) {
            degrees[j] += 2730;
        }
        int[] sortedDegrees = Sorts.countingSort(degrees, 7730);
        for (int j = 0; j < sortedDegrees.length; j++) {
            sortedDegrees[j] -= 2730;
        }

        File out = new File(outputName);
        FileWriter fileWriter = new FileWriter(out);
        try(BufferedWriter writer = new BufferedWriter(fileWriter)) {
             for (int value : sortedDegrees) {
                 String temperature;
                 if ((value / 10 == 0) && (value < 0)) {
                     temperature = "-" + (value / 10);
                 } else {
                     temperature = "" + (value / 10);
                 }
                 temperature += ".";
                 if (value >= 0) {
                     temperature += "" + (value % 10);
                 } else {
                     temperature += "" + (value % 10) * -1;
                 }
                 temperature += "\n";
                 writer.write(temperature);
             }
        }
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
