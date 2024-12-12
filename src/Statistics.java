import java.math.BigInteger;

public class Statistics extends FileProc{

    private static int count_int;
    private static int count_float;
    private static int count_strings;

    public Statistics(String filePath) {
        super(filePath);
    }

    private static void _shortStatistic() {
        count_int = integers.size();
        count_float = floats.size();
        count_strings = strings.size();
    }

    public static void shortStatistic(String beginningFile) {
        _shortStatistic();

        if (count_int != 0) {
            System.out.println("Количество элементов, записанных в файл " + beginningFile + "integers" +
                    FILE_EXTENSION + " равно " + count_int);
        }
        if (count_float != 0) {
            System.out.println("Количество элементов, записанных в файл " + beginningFile + "floats" +
                    FILE_EXTENSION + " равно " + count_float);
        }
        if (count_strings != 0) {
            System.out.println("Количество элементов, записанных в файл " + beginningFile + "strings" +
                    FILE_EXTENSION + " равно " + count_strings);
        }
    }

    private static void _fullStatisticsInt() {
        BigInteger bigInt;
        BigInteger min_int;
        double average_int;
        BigInteger sum_int;

        String str1 = integers.getFirst();
        bigInt = new BigInteger(str1);
        BigInteger max_int = bigInt;
        min_int = bigInt;

        sum_int = new BigInteger("0");

        for (String str : integers) {
            bigInt = new BigInteger(str);
            sum_int = sum_int.add(bigInt);
            if (max_int.compareTo(bigInt) < 0) max_int = bigInt;
            if (min_int.compareTo(bigInt) > 0) min_int = bigInt;

        }
        average_int = sum_int.divide(BigInteger.valueOf(count_int)).doubleValue();

        System.out.println("Максимальное значение среди целых чисел равно " + max_int);
        System.out.println("Минимальное значение среди целых чисел равно " + min_int);
        System.out.println("Среднее значение среди целых чисел равно " + average_int);
        System.out.println("Суммарное значение всех целых чисел равно " + sum_int);
    }

    private static void _fullStatisticsFloat() {
        double number;
        double max_float;
        double min_float;
        double average_float;
        double sum_float = 0.0;

        String str1 = floats.getFirst();
        number = Double.parseDouble(str1);
        max_float = number;
        min_float = number;

        for (String str : floats) {
            number = Double.parseDouble(str);
            sum_float += number;
            if (max_float < number) max_float = number;
            if (min_float > number) min_float = number;

        }
        average_float = sum_float / count_float;

        System.out.println("Максимальное значение среди вещественных чисел равно " + max_float);
        System.out.println("Минимальное значение среди вещественных чисел равно " + min_float);
        System.out.println("Среднее значение среди вещественных чисел равно " + average_float);
        System.out.println("Суммарное значение всех вещественных чисел равно " + sum_float);
    }

    private static void _fullStatisticsStrings() {
        int number;
        int max_length;
        int min_length;

        String str1 = strings.getFirst();
        number = str1.length();
        min_length = number;
        max_length = number;

        for (String str : strings) {
            number = str.length();
            if (max_length < number) max_length = number;
            if (min_length > number) min_length = number;
        }

        System.out.println("Максимальная длина строки равна " + max_length);
        System.out.println("Минимальная длина строки равна " + min_length);

    }

    public static void fullStatistics(String beginningFile) {
        _shortStatistic();

        if (count_int != 0) {
            System.out.println("Количество элементов, записанных в файл " + beginningFile + "integers" +
                    FILE_EXTENSION + ", равно " + count_int);
            _fullStatisticsInt();
            System.out.println();
        }

        if (count_float != 0) {
            System.out.println("Количество элементов, записанных в файл " + beginningFile + "floats" +
                    FILE_EXTENSION + ", равно " + count_float);
            _fullStatisticsFloat();
            System.out.println();
        }

        if (count_strings != 0) {
            System.out.println("Количество элементов, записанных в файл " + beginningFile + "strings" +
                    FILE_EXTENSION + ", равно " + count_strings);
            _fullStatisticsStrings();
        }

    }

}
