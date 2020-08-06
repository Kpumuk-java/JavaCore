package java2.lesson3;

import java.util.*;

public class TelephoneDirectory implements Comparable {

    private String lastName;
    private Integer number;
    private static HashSet<TelephoneDirectory> telephoneDirectories = new HashSet<>();

    public TelephoneDirectory(String lastName, Integer number) {
        if (lastName.trim().length() > 0 && number > 0) {
            if ((lastName.trim().length() == 1)) {
                this.lastName = lastName.trim().toUpperCase();
            } else {
                this.lastName = lastName.trim().toUpperCase().charAt(0) + lastName.trim().substring(1).toLowerCase();
            }
            this.number = number;
        }
    }

    public static void add(TelephoneDirectory subscriber) {
        telephoneDirectories.add(subscriber);
    }

    /**
     * Метод производит поиск в отсортированной коллекции telephoneDirectories по параметру lastName,
     * при нахождении выводит на консоль все совпадения с lastName в формате (Фамилия.........Номер)
     *
     * @param lastName Фамилия может быть с пробелами и с разным регистром
     */
    public static void get(String lastName) {

        if (lastName.trim().length() != 0) {
            byte count = 0;
            lastName = lastName.trim().toUpperCase().charAt(0) + lastName.trim().substring(1).toLowerCase();
            List<TelephoneDirectory> list = new ArrayList<>(telephoneDirectories);
            ListIterator<TelephoneDirectory> iter = list.listIterator();

            while (iter.hasNext()) {
                TelephoneDirectory another = iter.next();
                if (another.equals(lastName)) {
                    count = 1;
                    System.out.println(another.toString());
                    if (iter.hasNext()) {
                        another = iter.next();
                        if (!(another.equals(lastName))) {
                            break;
                        } else {
                            iter.previous();
                        }
                    }
                }
            }

            if (count == 0) {
                System.out.println("Абонента с такой фамилией не найден");
            }
        } else {
            System.out.println("Некорректное значение поиска");
        }
    }

    public boolean equals(String str) {
        return this.lastName.equals(str);
    }

    @Override
    public int compareTo(Object o) {
        TelephoneDirectory another = (TelephoneDirectory) o;
        return lastName.compareTo(another.getLastName());
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "" +
                "" + lastName +
                "..............." + number
                ;
    }
}
