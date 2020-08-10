package java1.lesson5;

public class Employee {

    // phoneNumber String, а вдруг у кого номер телефона Китайский (если не ошибаюсь там 10 знаков) :-)
    private String name, position, email, phoneNumber;
    private int salary, age;

    public Employee(String name, String position, String email, String phoneNumber, int salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public void dislpAyInfo () {
        //ФИО, должность, email, телефон, зарплата, возраст;
        System.out.printf("%10s", "ФИО:");
        System.out.println(" " + getName());
        System.out.printf("%10s", "Должность:");
        System.out.println(" " + getPosition());
        System.out.printf("%10s", "email:");
        System.out.println(" " + getEmail());
        System.out.printf("%10s", "Телефон:");
        System.out.println(" " + getPhoneNumber());
        System.out.printf("%10s", "Зарплата:");
        System.out.println(" " + getSalary());
        System.out.printf("%10s", "Возраст:");
        System.out.println(" " + getAge());
    }
}
