package java3.lesson4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LambdaLesson {

    /**
     *
     * @param n число которое нужно найти в массиве чисел List
     * @param list массив целых чисел
     * @return возвращает индекс элемента первого нахождения числа n в массиве List
     */
    public int search(Integer n, Integer[] list) {
        Predicate<Integer> equalsNumber = number -> number.equals(n);

        if (list != null && list.length > 0) {
            for (int i = 0; i < list.length; i++) {
                if (equalsNumber.test(list[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     *
     * @param s строка которую нужно перевернуть
     * @return перевернутая строка s
     */
    public String reverse(String s) {
        UnaryOperator<String> reverseString = stringReverse -> {
            if (stringReverse != null && stringReverse.trim().length() > 0) {
                StringBuilder str = new StringBuilder(stringReverse);
                return str.reverse().toString();
            } else {
                return stringReverse;
            }
        };
        return reverseString.apply(s);
    }

    /**
     *
     * @param list массив целых числе
     * @return максимальное число из массива list
     */
    public Integer maximum(Integer[] list) {
        Supplier<Integer> maximumMassive = () -> {
            if (list != null) {
                List<Integer> listMassive = new ArrayList<>();
                listMassive.toArray(list);
                return Collections.max(listMassive);
            } else {
                return null;
            }
        };

        return maximumMassive.get();
    }

    /**
     *
     * @param list массив целых чисел
     * @return среднее значение чисел double из массива list
     */
    public Double average(List<Integer> list) {
        Function<List<Integer>, Double> collectionMean = (meanList) -> {
            if (meanList != null && meanList.size() > 0) {
                int sum = 0;
                for (int i = 0; i < meanList.size(); i++) {
                    sum += meanList.get(i);
                }
                return (double) sum / meanList.size();
            }
            return null;
        };

        return collectionMean.apply(list);
    }

    /**
     *
     * @param list список строк
     * @return список всех строк, которые начинаются с буквы «а» (нижний регистр) и имеют ровно 3 буквы в списке List
     */
    public List<String> search(List<String> list) {
        UnaryOperator<List<String>> searchString = listBefore -> {
            List<String> listAfter = new LinkedList<>();
            if (listBefore != null && listBefore.size() > 0) {
                for (String s : listBefore) {
                    if (s.toLowerCase().startsWith("a") && s.length() == 3) {
                        listAfter.add(s);
                    }
                }
            }
            return listAfter;
        };

        return searchString.apply(list);
    }

}
