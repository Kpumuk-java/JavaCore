package java2.lesson5;

import java.util.Arrays;

public class MyThreadsRun {
    private static final int size = 1000000;
    private static final int h = size / 2;
    private float[] arr = new float[size];
    private static long buffer = 0;

    public MyThreadsRun() {
        Arrays.fill(arr, 1);
    }

    /**
     * В каждую ячейку массив arr записывается значение по формуле в одном потоке
     */
    public void oneThread() {
        long a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("\nВремя работы заполнения массива в одном потоке: " + (System.currentTimeMillis() - a));
    }

    /**
     * В массив arr в каждую ячейку записывается значенеи по формуле в двух потоках через разбития
     * массива на 2 массива и последующее склеивание
     */
    public void twoThread() {
        Long a = System.currentTimeMillis();

        Thread thread1 = new Thread((Runnable) () -> {
            float[] bufferArr = new float[h];
            System.arraycopy(arr, 0, bufferArr, 0, h);
            bufferArr = massiveFillToForm(bufferArr);
            System.arraycopy(bufferArr, 0, arr, 0, h);
            if (buffer < System.currentTimeMillis() - a) {
                buffer = System.currentTimeMillis() - a;
            }
        });

        Thread thread2 = new Thread((Runnable) () -> {
            float[] bufferArr = new float[h];
            System.arraycopy(arr, h, bufferArr, 0, h);
            bufferArr = massiveFillToForm(bufferArr);
            System.arraycopy(bufferArr, 0, arr, h, h);
            if (buffer < System.currentTimeMillis() - a) {
                buffer = System.currentTimeMillis() - a;
            }
        });

        thread1.start();
        thread2.start();

        while (true) {
            if (!thread1.isAlive() && !thread2.isAlive()) {
                System.out.println("\nВремя работы заполнения массива в двух потоках: " + buffer);
                //getValueMassiveTenNumber();
                break;
            }
        }
    }

    /**
     * @param arr массив float
     * @return возвращает массив со значениями высчитанные по заданной формуле
     */
    public float[] massiveFillToForm(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return arr;
    }

    /**
     * Выводит на консоль первые и последние 10 ячеек массива arr
     */
    public void getValueMassiveTenNumber() {
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i] + "   " + arr[size - i - 1]);
        }
    }

    /**
     * Заполняет массив arr единицами
     */
    public void massiveFillOne() {
        Arrays.fill(arr, 1);
    }
}
