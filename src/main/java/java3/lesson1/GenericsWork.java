package java3.lesson1;

import java.util.ArrayList;

public class GenericsWork {

    /**
     * 1 задание
     * В массиве меняет местами первый элемент oneElement со вторым twoElement
     * @return true если поменялись местами и false если нет
     */
    public static <T> boolean swapTwoElementsForMassive(T[] array, T oneElement, T twoElement) {
        if (array != null && array.length > 1 && !(oneElement.equals(twoElement))) {
            int indexOneElement = 0;
            boolean swap = false;
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(oneElement)) {
                    swap = true;
                    indexOneElement = i;
                    break;
                }
            }
            if (swap) {
                for (int i = 0; i < array.length; i++) {
                    if (array[i].equals(twoElement)) {
                        array[indexOneElement] = twoElement;
                        array[i] = oneElement;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 2 задание
     * Делает из массива коллекцию ArrayList (Только не понял что с коллекцией делать потом,
     * потому что она проживет только до конца метода)
     */
    public static <T>void massiveToArrayList (T[] array) {
        if (array != null) {
            ArrayList<T> list = new ArrayList<>();
            for (T t : array) {
                list.add(t);
            }
        }
    }

}
