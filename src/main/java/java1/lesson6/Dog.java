package java1.lesson6;

public class Dog extends Animal {

    private static int count;

    public Dog () {
        this.count++;
        setMaxJumpHeight(Math.random());
        setMaxRunDistance(400 + (Math.random() * 200));
        setMaxSwimDistance(5 + (Math.random() * 10));
    }

    public static int getCount() {
        return count;
    }
}
