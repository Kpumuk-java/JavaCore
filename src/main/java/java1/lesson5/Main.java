package java1.lesson5;

public class Main {

    public static void main(String[] args) {
        Employee[] employee = new Employee[5];
        employee[0] = new Employee("Волосков Петр Олегович", "Инженер",
                "Petr@yandex.ru", "89315679464", 30000, 32);
        employee[1] = new Employee("Пунгин Артем Васильевич", "Электро-монтер",
                "Artem@yandex.ru", "84445679641", 45000, 41);
        employee[2] = new Employee("Блинова Виктория Владиславовна", "Начальник отдела по связам с общественностью",
                "VBlinova@yandex.ru", "85554128456", 60000, 31);
        employee[3] = new Employee("Козлов Григорий Артемьянович", "Шут",
                "Kozlov@yandex.ru", "86661111212", 1, 51);
        employee[4] = new Employee("Босс", "Скрывает",
                "Boss@yandex.ru", "81112223344", 1000000, 37);

        for (Employee s: employee) {
            if (s.getAge() > 40) {
                s.dislpAyInfo();
                System.out.println();
            }
        }
    }
}
