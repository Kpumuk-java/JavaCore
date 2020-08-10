package java1.lesson3;

import java.util.Scanner;

public class GameGuessWord {

    private static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
            "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    private static Scanner scanner = new Scanner(System.in);
    private static String answerWord;
    // сразу в hint записывается 15 символов #, по условию задано
    private static StringBuilder hint = new StringBuilder("###############");


    public static void main(String[] args) {
        gameGuessWord();
    }

    public static void gameGuessWord () {

        String guessWord = thinksWord();
        System.out.println("Какое слово было загадано?");

        while (true) {
            answerWord = scanner.nextLine();
            if (guessWord.equals(answerWord)) {
                System.out.println("Верно :-)");
                break;
            } else {
                int count = 0;
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals(answerWord)) {
                        checkWord(guessWord, answerWord);
                        count = 1;
                        break;
                    }
                }

                if (count == 0) {
                    System.out.println("Введенное слово не входит в список возможных загаданных слов");
                }

            }
        }
        scanner.close();
    }

    public static String thinksWord () {
        //можно было без этого метода делать,
        // это задел на будущее вдруг логика выбора поменяется, не только от рандома
        int positionWord = (int) (Math.random() * (words.length + 1));
        return words[positionWord];
    }

    public static void checkWord (String word, String answer) {
        // лучше делать через Math.min(word.length(), answer.length())
        int sizeMinString = word.length() <= answer.length() ? word.length() : answer.length();
        for (int i = 0; i < sizeMinString; i++) {
            if (word.charAt(i) == answer.charAt(i)) {
                // StringBuilder очень упрощает написание, иначе пришлось бы добавлять 2 условия
                hint.setCharAt(i, word.charAt(i));
            }
        }


        System.out.println("Не угадал :-( , Подсказка ( " + hint + " ) Попробуй еще раз:");
    }

}
