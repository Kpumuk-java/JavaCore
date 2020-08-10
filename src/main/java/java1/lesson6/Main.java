package java1.lesson6;

public class Main {

    public static void main(String[] args) {
        Emulation emulation = new Emulation();
        System.out.println("Проходит соревнование животных, участники 2 кошки и 2 собаки (третьи проспали)");
        System.out.println("В связи с изучением полиморфизма, после любого действия животные будут потеряны и уничтожены GС, ссылки на объекты будут затерты, хорошо что животные виртуальные");
        Animal animal = new Cat();
        System.out.println("Первое задание проплыть 1 метр");
        emulation.swim(animal, 1);
        System.out.println("Второе задание пробежать 500 метров");
        animal = new Dog();
        emulation.run(animal, 550);
        System.out.println("Третье задание прыгнуть на 0.6 метра");
        animal = new Dog();
        emulation.jump(animal, 0.6);
        System.out.println("Четвертое задание пробежать 220 метров");
        animal = new Cat();
        emulation.run(animal, 220);

        System.out.println("Нужно проверить сколько всего было использованно животных");
        System.out.println("Кошек: " + Cat.getCount() + " Собак: " + Dog.getCount());




    }


}
