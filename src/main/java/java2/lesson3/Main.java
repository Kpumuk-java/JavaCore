package java2.lesson3;

public class Main {

    public static void main(String[] args) {
        /*ArraysUtils arraysUtils = new ArraysUtils();

        String [] str = {" "};
        arraysUtils.uniqueWordsFromArraysWords(arraysUtils.getWords());
        arraysUtils.countWordsFromArraysWords(arraysUtils.getWords());*/

        TelephoneDirectory.add(new TelephoneDirectory("Bill", 12536));
        TelephoneDirectory.add(new TelephoneDirectory("Tom", 12536));


        TelephoneDirectory.add(new TelephoneDirectory("Sergey", 25361));
        TelephoneDirectory.add(new TelephoneDirectory("  BilL  ", 253614));
        TelephoneDirectory.get("Bill");
    }
}
