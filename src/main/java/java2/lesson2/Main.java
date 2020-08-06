package java2.lesson2;

import java2.lesson2.exceptions.MyArraySizeException;
import java2.lesson2.service.ArrayToStringService;

public class Main {

    public static void main(String[] args) {

        ArrayToStringService arrayToStringService = new ArrayToStringService();

        try {
            arrayToStringService.arrayStringToInteger(arrayToStringService.getArrayString());
        } catch (MyArraySizeException  e) {
            System.out.println("Размер массива не соответствует 4 на 4");
        }

        System.out.println("\nЛог ошибок:");
        System.out.println(arrayToStringService.getLogDataException());
    }

}
