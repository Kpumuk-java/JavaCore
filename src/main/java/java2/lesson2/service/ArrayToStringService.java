package java2.lesson2.service;

import java2.lesson2.exceptions.MyArraySizeException;

import java.util.Date;

public class ArrayToStringService {


    private String[][] arrayString =   {{"GH", " 123                      ", " 34 ", "0"},
            {"-23", "  -450 ", "23", "200"},
            {"5258369147", "  -450 ", "LL", "200"},
            {"-23", "  -450 ", "23", "10000"}};
    private int sumArray;
    private static StringBuilder logDataException = new StringBuilder();

    /**
     * Метод принимает на вход двумерный массив String, проверяет на размер,
     * суммирует в sumArray все строки массива, если в ячейках присутствует не корректные данные идет запись в лог
     * logDataException, в конце метода выводится в консоль общая сумма всех ячеек (sumArray)
     * @throws MyArraySizeException не верный размер массива
     */
    public void arrayStringToInteger(String[][] arrayString) throws MyArraySizeException {

        Date date = new Date();

        if (arrayString.length != 4) {
            throw new MyArraySizeException();
        } else {
            for (int i = 0; i < arrayString.length; i++) {
                if (arrayString[i].length != 4) {
                    throw new MyArraySizeException();
                }
            }
        }

        for (int i = 0; i < arrayString.length; i++) {
            for (int j = 0; j < arrayString[i].length; j++) {
                try {
                    sumArray += Integer.parseInt(arrayString[i][j].trim());
                } catch (NumberFormatException e) {
                    logDataException.append(date.toString() + "    в ячейке [" + i + "][" + j +
                            "]   " +  e.fillInStackTrace() +"\n");
                }
            }

        }

        System.out.println("\nОбщая сумма всех элементов массива String: " + sumArray);

    }

    public int getSumArray() {
        return sumArray;
    }

    public StringBuilder getLogDataException() {
        return logDataException;
    }

    public String[][] getArrayString() {
        return arrayString;
    }
}
