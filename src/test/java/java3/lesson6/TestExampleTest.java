package java3.lesson6;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExampleTest {

    private Integer[] arr1 = {3, 4, 5, 6, 7};
    private Integer[] arr2 = {3, 4, 5, 4, 7};
    private Integer[] arr3 = {1, 2, 3, 4};
    private Integer[] arr4 = null;
    private Integer[] arr5 = {2, 7, 3, 6};
    private Integer[] arr6 = {};
    private Integer[] arr7 = {1};
    private Integer[] arr8 = {4};
    private Integer[] arr9 = {5};
    private final Integer[] arrFinal = {3, 1, 5, 3, 5};



    @Test
    public void returnMassiveAfterFourTest() {
        Integer[] returnArr = {5, 6, 7};
        Assertions.assertArrayEquals(returnArr, TestExample.returnMassiveAfterFour(arr1));

        returnArr = new Integer[]{7};
        Assertions.assertArrayEquals(returnArr, TestExample.returnMassiveAfterFour(arr2));

        Assertions.assertThrows(RuntimeException.class, () -> {
            TestExample.returnMassiveAfterFour(arrFinal);
        });

        Assertions.assertNull(TestExample.returnMassiveAfterFour(arr3));
        Assertions.assertNull(TestExample.returnMassiveAfterFour(arr4));

    }

    @Test
    public void checkMassiveInOneOrFourTest() {
        Assertions.assertTrue(TestExample.checkMassiveInOneOrFour(arr1));
        Assertions.assertTrue(TestExample.checkMassiveInOneOrFour(arrFinal));
        Assertions.assertFalse(TestExample.checkMassiveInOneOrFour(arr4));
        Assertions.assertFalse(TestExample.checkMassiveInOneOrFour(arr5));
        Assertions.assertFalse(TestExample.checkMassiveInOneOrFour(arr6));
        Assertions.assertTrue(TestExample.checkMassiveInOneOrFour(arr7));
        Assertions.assertTrue(TestExample.checkMassiveInOneOrFour(arr8));
        Assertions.assertFalse(TestExample.checkMassiveInOneOrFour(arr9));
    }
}
