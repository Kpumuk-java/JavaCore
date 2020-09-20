package java3.lesson6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestExample<T> {

    public static Integer[] returnMassiveAfterFour(Integer[] massive) throws RuntimeException {

        if (massive != null) {
            List<Integer> returnMassive = new ArrayList<>();
            for (int i = massive.length - 1; i >= 0; i--) {
                if (i == massive.length - 1 && massive[i] == 4) {
                    return null;
                }
                if (massive[i] == 4) {
                    returnMassive.addAll(Arrays.asList(massive).subList(i + 1, massive.length));
                    return returnMassive.toArray(new Integer[0]);
                }
            }
            throw new RuntimeException();
        }
        return null;

    }

    public static boolean checkMassiveInOneOrFour(Integer[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 1 || arr[i] == 4) {
                    return true;
                }
            }
        }
        return false;
    }

}
