package ru.geekbrains.lesson2;

public class Lesson2 {



    public static void main(String[] args) {

    }

    public static void isMinAndMaxMassive (int[] massive) {
        if (massive.length > 0) {
            int numberMax = getMaxNumber(massive);
            int numberMin = getMinNumber(massive);
        }
    }


    public static int getMaxNumber (int[] massive) {
        int maxNumber = massive[0];
        for (int i = 1; i < massive.length - 1; i++) {
            if (maxNumber < massive[i]) {
                maxNumber = massive[i];
            }
        }
        return maxNumber;
    }

    public static int getMinNumber (int[] massive) {
        int minNumber = massive[0];
        for (int i = 1; i < massive.length - 1 ; i++) {
            if (minNumber > massive[i]) {
                minNumber = massive[i];
            }
        }
        return minNumber;
    }

    public static boolean getCheckBalance (int[] massive) {
        if (massive.length > 1) {
            for (int i = 0; i < massive.length; i++) {
                if (getLeftSumMassive(massive, i) == getRightSumMassive(massive, i)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int getLeftSumMassive (int[] massive, int check) {
        int sum = 0;

        if (check == 0) {
            sum = massive[check];
            return sum;
        }

        for (int i = 0; i <= check ; i++) {
            sum += massive[i];
        }

        return sum;
    }

    public static int getRightSumMassive (int[] massive, int check) {
        int sum = 0;
        if (check == massive.length - 2) {
            sum = massive[check];
            return sum;
        }
        for (int i = check + 1; i < massive.length ; i++) {
            sum += massive[i];
        }

        return sum;
    }

    public static void isDriftMassive (int[] massive, int n) {
        if ((massive.length > 1) && !(n == 0)) {
            int countDrift;

            if (n < 0) {
                countDrift = massive.length + (n % massive.length);
            } else if (n > massive.length){
                countDrift = n % massive.length;
            } else {
                countDrift = n;
            }

            int bufferNumber1 = 0, bufferNumber2;

            while (countDrift > 0) {

                for (int i = 0; i < massive.length; i++) {
                    if (i == 0) {
                        bufferNumber1 = massive[i + 1];
                        massive[i + 1] = massive[i];
                    } else if (i < massive.length - 1) {
                        bufferNumber2 = massive[i + 1];
                        massive[i + 1] = bufferNumber1;
                        bufferNumber1 = bufferNumber2;
                    } else {
                        massive[0] = bufferNumber1;
                    }

                }

                countDrift--;
            }
        }
    }

    public static void isMassiveZeroAndOne () {
        int[] massive = {1, 0, 0, 0, 1, 1};

        for (int i = 0; i < massive.length; i++) {
            if (massive[i] == 0) {
                massive[i] = 1;
            } else {
                if (massive[i] == 1) {
                    massive[i] = 0;
                }
            }
        }
    }

    public static void isMassiveX3 () {
        int[] massive = new int[8];

        for (int i = 0; i < massive.length; i++) {
            massive[i] = i * 3;
        }
    }

    public static void isMassiveX2ForNumberLessSix () {
        int[] massive = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i = 0; i < massive.length; i++) {

            if (massive[i] < 6) {
                massive[i] = massive[i] * 2;
            }

        }
    }

    public static void isMassiveSquare (int[][] massive) {

        for (int i = 0; i < massive.length ; i++) {
            massive[i][i] = 1;
            massive[massive.length - 1 - i][i] = 1;
        }
    }

    public static void isSortMassive (int[] massive) {

        if (massive.length > 1) {
            int buffer, countBreak;

            for (int i = 0; i < massive.length; i++) {
                countBreak = 0;
                if (!(i == 0) && (countBreak == 0)) {
                    break;
                }
                for (int j = 0; j < massive.length - 1 - i; j++) {
                    if (massive[j] > massive[j + 1]) {
                        buffer = massive[j + 1];
                        massive[j + 1] = massive[j];
                        massive[j] = buffer;
                        countBreak = 1;
                    }
                }
            }
        }
    }


}
