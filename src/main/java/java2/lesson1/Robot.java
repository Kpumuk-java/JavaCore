package java2.lesson1;

public class Robot extends PartyMembers {

    private int maxRunLength;
    private int maxJumpHeight;
    private static int count = 1;
    private int energy = 100;

    public Robot(String name) {
        super(name + count++);
        this.maxJumpHeight = (int) (10 + Math.random() * 991);
        this.maxRunLength = (int) (10 + Math.random() * 991);
    }

    public int getMaxRunLength() {
        return maxRunLength;
    }

    public int getMaxJumpHeight() {
        return maxJumpHeight;
    }

    /*
     * у робота переопределен метод Run и Jump. Пока энергии больше нуля препятствие робот все равно пройдет
     * и уже только на следующем препятствии энергии будет меньше 0
     * */
    @Override
    public boolean Run(Object o) {
        if (energy <= 0) {
            System.out.println("Закончилась энергия");
            return true;
        }

        if (getMaxRunLength() > ((RunningTrack) o).getDistance()) {
            energy -= ((RunningTrack) o).getDistance() / (double) (getMaxRunLength() / 100);
        }

        return super.Run(o);
    }

    @Override
    public boolean Jump(Object o) {
        if (energy <= 0) {
            System.out.println("Закончилась энергия");
            return true;
        }

        if (getMaxJumpHeight() > ((Wall) o).getHeight()) {
            energy -= ((Wall) o).getHeight() / (double) (getMaxJumpHeight() / 100);
        }

        return super.Jump(o);
    }
}
