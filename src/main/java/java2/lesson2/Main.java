package java2.lesson2;

import java2.lesson2.exceptions.MyArraySizeException;
import java2.lesson2.model.ArrayToString;
import java2.lesson2.service.ArrayToStringService;

public class Main {

    public static void main(String[] args) {

        ArrayToString arrayToString = new ArrayToString();
        ArrayToStringService arrayToStringService = new ArrayToStringService();

        try {
            arrayToStringService.arrayStringToInteger(arrayToString.getArrayString());
        } catch (MyArraySizeException e) {
            System.out.println("Размер массива не соответствует 4 на 4");
        }

        System.out.println("\nЛог ошибок:");
        for (String s:  arrayToStringService.getLogArrayDataException()) {
            System.out.println(s);
        }
    }

}
