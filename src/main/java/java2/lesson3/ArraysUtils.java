package java2.lesson3;

import java.util.*;

public class ArraysUtils {

    private String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon", "leak", "orange", "orange", "orange", "kiwi", "mango", "mushroom",
            "nut", "olive", "pea", "peanut", "   broccoli", "broccoli", "broccoli", "pear", "pepper", "pineapple",
            "pumpkin", "potato"};

    /**
     * В метод передается массив слов, который убирает все пробелы сначала и с конца слова,
     * а потом преобразовывается в коллекцию TreeSet. На консоль вывводиться
     * колличество уникальных слов и все слова коллекции в отсортированном ввиде. Слова состоящие из пробелов
     * не учитываются при посдсчете
     *
     * @param words нессортированый массив слов
     */
    public void uniqueWordsFromArraysWords(String[] words) {

        wordsTrim(words);
        Set<String> unique = new TreeSet<>(Arrays.asList(words));
        unique.remove("");

        System.out.println("\nКолличество уникальных слов в массиве: " + unique.size());
        System.out.println(unique);
    }


    /**
     * Метод высчитывает сколько раз встречается каждое слово из массива слов words. Непроверяет пустые массивы.
     * Слова состоящие из пробелов не подсчитываются.
     *
     * @param words нессортированый массив слов
     */
    public void countWordsFromArraysWords(String[] words) {

        wordsTrim(words);
        ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
        int count = 1;

        System.out.println();

        list.sort(String::compareTo);


        while (list.size() != 0) {
            count = 1;

            if (list.size() > 1) {
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(0).equals(list.get(i))) {
                        count++;
                    // TODO Посчитал что в отсортированной коллекции, когда сравнение становитсья ложным, можно дальше цикл не гонять
                    } else {
                        break;
                    }

                }
            }

            if (list.get(0).trim().length() > 0) {
                System.out.println(list.get(0) + " встречается " + count + " раз");
                String buffer = list.get(0);
                for (int i = count; i > 0; i--) {
                    list.remove(buffer);
                }

            } else {
                list.remove(list.get(0));
            }


        }
    }

    /**
     *
     * @param words
     * @return массив в котором удалены пробелы сначала и с конца слова
     */
    public String[] wordsTrim (String [] words) {
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }

        return words;
    }

    public String[] getWords() {
        return words;
    }
}
