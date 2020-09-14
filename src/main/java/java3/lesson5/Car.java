package java3.lesson5;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Car implements Runnable {
    private static int CARS_COUNT;

    private static CyclicBarrier barrier = new CyclicBarrier(MainClass.CARS_COUNT);
    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    private static boolean WIN = true;

    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {

        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");

            barrier.await();
            MainClass.RACE_GO.countDown();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        lock.writeLock().lock();
        if (WIN) {
            System.out.println(getName() + " WIN");
            WIN = false;
        }
        lock.writeLock().unlock();
        MainClass.RACE_DOWN.countDown();

    }

}
