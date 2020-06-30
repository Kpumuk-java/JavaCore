package ru.geekbrains.lesson1;

public class Lesson1 {

    public static void main(String[] args) {
        //dataType();
        //System.out.println("Возвращаемое значение " + complexCalculations());
        //twoNumbers(10, 5);
        methodDetermineYear ();
    }

    public static void dataType () {
        byte typeByte = 1;
        short typeShort = 15000;
        int typeInt = 100100;
        long typeLong = 10000L;
        float typeFloat = 10.10f;
        double typeDouble = 10.10;
        char typeChar = '\uffff';
        boolean typeBoolean = true;

        System.out.println("Тип данных  - [byte]     Значение переменной - [ " + typeByte + " ]");
        System.out.println("Тип данных  - [short]    Значение переменной - [ " + typeShort + " ]");
        System.out.println("Тип данных  - [int]      Значение переменной - [ " + typeInt + " ]");
        System.out.println("Тип данных  - [long]     Значение переменной - [ " + typeLong + " ]");
        System.out.println("Тип данных  - [float]    Значение переменной - [ " + typeFloat + " ]");
        System.out.println("Тип данных  - [double]   Значение переменной - [ " + typeDouble + " ]");
        System.out.println("Тип данных  - [char]     Значение переменной - [ " + typeChar + " ]");
        System.out.println("Тип данных  - [boolean]  Значение переменной - [ " + typeBoolean + " ]");

    }

    public static float complexCalculations () {
        float a = 0.1f, b = 11f, c = 10f, d = 10f;
        System.out.println("a = " + a + "   b = " + b + "   c = " + c + "   d = " + d);
        //System.out.println("Возвращаемое значение " + (a * (b + (c / d))));
        return a * (b + (c / d));
    }

    public static boolean twoNumbers (int one_number, int two_number) {
        int sum = one_number + two_number;
        if (sum >= 10 && sum <= 20) {
            System.out.println("Сумма двух чисел находится в диапозоне от 10 до 20 включительно");
            return true;
        } else {
            System.out.println("Сумма двух чисел находится за пределом диапозона от 10 до 20 включительно");
            return false;
        }
    }

    public static void determinePositiveOrNegativeNumber (int number) {
        if (number == 0 || number > 0) {
            System.out.println("Число является положительным");}
            else System.out.println("Число является отрицательным");
    }

    public static boolean methodReturnTrueWhenNumberNegative (int number) {
        if (number < 0) return true;
        return false;
    }

    public static void methodHello (String name) {
        System.out.println("Привет, " + name);
    }

    public static void methodDetermineYear () {
        int year = 1199;

        if (year % 4 == 0)
            if (!(year % 100 == 0) || (year % 400 == 0)) System.out.println ("Год является высокосным");
             else System.out.println("Год не является высокосным");
        else System.out.println("Год не является высокосным");
    }

}
