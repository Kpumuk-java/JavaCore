package java2.lesson3;

import java.util.*;

public class TelephoneDirectory {


    private List<Contact> telephoneDirectories;

    public TelephoneDirectory() {
        this.telephoneDirectories = new ArrayList<>();
    }

    public void add(Contact contact) {

        if (contact.getPhoneNumber() != null) {
            telephoneDirectories.add(contact);
        }
    }

    /**
     * Метод сортирует коллекцию telephoneDirectories. Ищет совпадения в коллекции telephoneDirectories по lastName
     * класса Contact с параметром взода метода lastName, Если есть совпадения ввыводит на консоль все записи в формате
     * фамилия..........номер. Если параметр входа LastName равен null или пробелами, выводит на консоль "Некорректное
     * значение поиска"
     * @param lastName Строка Фамилии может быть с пробелами и разным регистром
     */
    public void get(String lastName) {
        byte count = 0;
        telephoneDirectories.sort(Contact::compareTo);

        if (lastName != null && !(lastName.isEmpty())) {
            ListIterator iter = telephoneDirectories.listIterator();

            while (iter.hasNext()) {
                Contact another = (Contact) iter.next();
                if (another.getLastName().equalsIgnoreCase(lastName.trim())) {
                    count = 1;
                    System.out.println(another.toString());
                    if (iter.hasNext()) {
                        another = (Contact) iter.next();
                        if (!(another.getLastName().equalsIgnoreCase(lastName.trim()))) {
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
}
