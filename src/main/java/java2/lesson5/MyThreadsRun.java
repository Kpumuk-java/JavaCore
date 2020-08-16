package java2.lesson5;

import java.util.Arrays;

public class MyThreadsRun {
    private static final int size = 1000000;
    private static final int h = size / 2;
    private float[] arr = new float[size];

    /**
     * В каждую ячейку массив arr записывается значение по формуле в одном потоке
     */
    public void oneThread() {
        initMassive();
        long a = System.currentTimeMillis();
        massiveFillToForm(arr);
        System.out.println("\nВремя работы заполнения массива в одном потоке: " + (System.currentTimeMillis() - a));
    }

    /**
     * В массив arr в каждую ячейку записывается значения по заданой формуле в двух потоках через разбития
     * массива на 2 массива и последующее склеивание. Вывод на консоль время выполнения данного метода
     */
    public void twoThread() {
        initMassive();
        long a = System.currentTimeMillis();

        Thread thread2 = new Thread(() -> {
            float[] bufferArr = new float[h];
            System.arraycopy(arr, 0, bufferArr, 0, h);
            massiveFillToForm(bufferArr);
            System.arraycopy(bufferArr, 0, arr, 0, h);
        });

        thread2.start();

        float[] bufferArr = new float[h];
        System.arraycopy(arr, h, bufferArr, 0, h);
        massiveFillToForm(bufferArr);
        System.arraycopy(bufferArr, 0, arr, h, h);

        while (true) {
            if (!thread2.isAlive()) {
                System.out.println("\nВремя работы заполнения массива в двух потоках: " + (System.currentTimeMillis() - a));
                break;
            }
        }
    }

    /**
     * @param arr массив float
     * @return возвращает массив со значениями высчитанные по заданной формуле
     */
    private float[] massiveFillToForm(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return arr;
    }

    /**
     * Заполняет массив arr единицами
     */
    private void initMassive () {
        Arrays.fill(arr, 1);
    }
}
