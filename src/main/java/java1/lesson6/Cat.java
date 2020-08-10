package java1.lesson6;

public class Cat extends Animal {
    // count сеттер не нужен, изменится не должен, кроме как в конструкторе увеличится
    private static int count;

    public Cat() {
        this.count++;
        setMaxJumpHeight(1 + (Math.random() * 2));
        setMaxRunDistance(150 + (Math.random() * 100));
    }

    @Override
    public void swim(double swimLenght) {
        System.out.println("утонула");
    }

    public static int getCount() {
        return count;
    }
}
