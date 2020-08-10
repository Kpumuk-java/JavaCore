package java1.lesson6;

public class Animal {

    private double maxJumpHeight;
    private double maxSwimDistance;
    private double maxRunDistance;

    public double getMaxJumpHeight() {
        return maxJumpHeight;
    }

    public double getMaxSwimDistance() {
        return maxSwimDistance;
    }

    public double getMaxRunDistance() {
        return maxRunDistance;
    }

    public Animal setMaxJumpHeight(double maxJumpHeight) {

        this.maxJumpHeight = maxJumpHeight;
        return this;
    }

    public Animal setMaxSwimDistance(double maxSwimDistance) {

        this.maxSwimDistance = maxSwimDistance;
        return this;
    }

    public Animal setMaxRunDistance(double maxRunDistance) {
        this.maxRunDistance = maxRunDistance;
        return this;
    }

    public void swim(double swimLenght) {
        if (this.maxSwimDistance >= swimLenght) {
            System.out.println("swim: true");
        } else {
            System.out.println("swim: false");
        }
    }

    public void run(double runLenght) {
        if (this.maxRunDistance >= runLenght) {
            System.out.println("run: true");
        } else {
            System.out.println("run: false");
        }
    }

    public void jump(double jumpHeight) {
        if (this.maxJumpHeight >= jumpHeight) {
            System.out.println("jump: true");
        } else {
            System.out.println("jump: false");
        }
    }

    public void animalInfo() {
        System.out.println(getMaxJumpHeight());
        System.out.println(getMaxRunDistance());
        System.out.println(getMaxSwimDistance());
    }


}
