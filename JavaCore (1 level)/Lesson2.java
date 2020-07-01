package ru.geekbrains.lesson2;

public class Lesson2 {

    public static void main(String[] args) {
        int[] MIN_MAX_MASSIVE = {1, 2, 4, 5, 6};
        getMinNumber(MIN_MAX_MASSIVE);
        getMaxNumber(MIN_MAX_MASSIVE);
    }

    public static boolean getCheckMassive (int [] massive) {
        if (massive.length > 1) {
            return true;
        }

        return false;
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
        if (getCheckMassive(massive)) {
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
        if (getCheckMassive(massive)) {
            int countDrift;

            if (n < 0) {
                countDrift = massive.length - (n % massive.length);
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

}
