package java2.lesson3;

public class Main {

    public static void main(String[] args) {
        /*ArraysUtils arraysUtils = new ArraysUtils();

        String [] str = {" "};
        arraysUtils.uniqueWordsFromArraysWords(arraysUtils.getWords());
        arraysUtils.countWordsFromArraysWords(arraysUtils.getWords());*/

        TelephoneDirectory telephoneDirectory = new TelephoneDirectory();

        telephoneDirectory.add(new Contact("Bill", "  e1111"));
        telephoneDirectory.add(new Contact("Клаус", "12345"));
        telephoneDirectory.add(new Contact("Bill", "2"));

        telephoneDirectory.add(new Contact("Jhon", "123"));
        telephoneDirectory.add(new Contact("Zik", "213"));
        telephoneDirectory.add(new Contact("Bill", "    2"));
        telephoneDirectory.add(new Contact("Bill", "    2    "));



        telephoneDirectory.get("BIll");


    }
}
