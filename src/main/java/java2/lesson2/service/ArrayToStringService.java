package java2.lesson2.service;

import java2.lesson2.exceptions.MyArrayDataException;
import java2.lesson2.exceptions.MyArraySizeException;

import java.util.ArrayList;
import java.util.Date;

public class ArrayToStringService {

    private int sumArray;
    private ArrayList<String> logArrayDataException;

    public ArrayToStringService() {
        this.sumArray = 0;
        this.logArrayDataException = new ArrayList<>();
    }

    /*
    * Метод принимает на вход двумерный массив String, проверяет на соответствии размера 4 на 4,
    * при несоответствии выдает исключение  MyArraySizeException()
    * Циклом в переменную sumArray суммируется каждая строка массива с проверкой checkString на соответствии int
    * При несоответствии выдает исключение MyArrayDataException(), в котором идет запись
    * в массив ArrayList c названием logArrayDataException лог в формате строки: дата, ячейка и значение ячейки
    * Вывод на консоль сумму sumArray
    * */
    public void arrayStringToInteger(String[][] arrayString) {

        Date date = new Date();

        if (!(arrayString.length == 4)) {
            throw new MyArraySizeException();
        } else {
            // Задел на будущее, возможность увеличения размера массива, а так здесь можно было и 4 условия использовать
            for (int i = 0; i < arrayString.length; i++) {
                if (!(arrayString[i].length == 4)) {
                    throw new MyArraySizeException();
                }
            }
        }

        for (int i = 0; i < arrayString.length; i++) {
            for (int j = 0; j < arrayString[i].length; j++) {
                try {
                    sumArray += checkString(arrayString[i][j]);
                } catch (MyArrayDataException e) {
                    logArrayDataException.add(date.toString() + "    в ячейке [" + i + "][" + j +
                            "]    неверные данные: " + arrayString[i][j]);
                }
            }

        }

        System.out.println("Общая сумма всех элементов массива String: " + sumArray);

    }

    /*
    * Метод принимает не отформатированную строку, убирает пробелы сначала и с конца строки, потом идет проверка на
    * символов в строке больше 0 и меньше 12, проверка размера строки на 1 и первого символа на минус, следующая на
    * то что символы являются цифрами от 0 до 9, проверка на диапозон int
    * Если проверки не сработали, то при возвращении строку переводит в Integer, в противном случаи выдает исключение
    * MyArrayDataException()
    * */
    public int checkString(String s) {
        String buffer = s.trim();
        char oneSymbol;

        // проверка что строка состоит больше 0 символа и меньше 12 (потому что у отрицательного int 11 символов)
        if (buffer.length() > 0 && buffer.length() < 12) {
            if (buffer.length() == 1 && buffer.charAt(0) == '-') {
                throw new MyArrayDataException();
            }

            if (buffer.charAt(0) == '-') {
                for (int i = 1; i < buffer.length(); i++) {
                    oneSymbol = buffer.charAt(i);
                    if (!(oneSymbol >= '0' && oneSymbol <= '9')) {
                        throw new MyArrayDataException();
                    }
                }
            } else {
                for (int i = 0; i < buffer.length(); i++) {
                    oneSymbol = buffer.charAt(i);
                    if (!(oneSymbol >= '0' && oneSymbol <= '9')) {
                        throw new MyArrayDataException();
                    }
                }
            }
            // проверка на диапозон int
            if (Long.parseLong(buffer) < -2147483648 || Long.parseLong(buffer) > 2147483647) {
                throw new MyArrayDataException();
            }

        } else {
            throw new MyArrayDataException();
        }

        return Integer.parseInt(buffer);
    }


    public int getSumArray() {
        return sumArray;
    }

    public ArrayList<String> getLogArrayDataException() {
        return logArrayDataException;
    }
}
