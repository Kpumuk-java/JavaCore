package java1.lesson1;

public class Type {

    public static void main(String[] args) {

        

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

       /* System.out.println("Тип данных  - [byte]     Значение переменной - [ " + typeByte + " ]");
        System.out.println("Тип данных  - [short]    Значение переменной - [ " + typeShort + " ]");
        System.out.println("Тип данных  - [int]      Значение переменной - [ " + typeInt + " ]");
        System.out.println("Тип данных  - [long]     Значение переменной - [ " + typeLong + " ]");
        System.out.println("Тип данных  - [float]    Значение переменной - [ " + typeFloat + " ]");
        System.out.println("Тип данных  - [double]   Значение переменной - [ " + typeDouble + " ]");
        System.out.println("Тип данных  - [char]     Значение переменной - [ " + typeChar + " ]");
        System.out.println("Тип данных  - [boolean]  Значение переменной - [ " + typeBoolean + " ]");*/

    }

    public static float getComplexCalculations (float a, float b, float c , float d) {
        System.out.println("a = " + a + "   b = " + b + "   c = " + c + "   d = " + d);
        //System.out.println("Возвращаемое значение " + (a * (b + (c / d))));
        return a * (b + (c / d));
    }

    public static boolean getTwoNumbers (int one_number, int two_number) {
        int sum = one_number + two_number;
        return sum >= 10 && sum <= 20;
    }

    public static void isPositiveOrNegativeNumber (int number) {
        if (number >= 0) {
            System.out.println("Число является положительным");}
            else System.out.println("Число является отрицательным");
    }

    public static boolean getReturnTrueWhenNumberNegative (int number) {
        return number < 0;
    }

    public static void isMethodHello (String name) {
        System.out.println("Привет, " + name);
    }

    public static void isMethodDetermineYear (int year) {

        if (year % 4 == 0)  {
            if (!(year % 100 == 0) || (year % 400 == 0)) System.out.println ("Год является высокосным");
            else System.out.println("Год не является высокосным");
            }
        else {
            System.out.println("Год не является высокосным");
        }
    }

}
