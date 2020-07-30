package java2.lesson1;

public class PartyMembers implements Movement {

    private String name;
    private int maxRunLength;
    private int maxJumpHeight;

    public PartyMembers(String name) {
        this.name = name;
    }

    /* Здесь нет проверки на тип объекта, потому что проверка проходит в методе partyMembersGoInObstacleCourse в майне
    * Почему здесь нету проверки, из-за полиморфизма и выввода на консоль текста про успешное или провальное прохождение
    * (пришлось бы много делать проверок кто иммено использовал метод и у каждого отдельно писать свой вывод на консоль)
    */
    @Override
    public boolean Run(Object o) {
        return (getMaxRunLength() < ((RunningTrack) o).getDistance());
    }
    // Здесь нет проверки на тип объекта, потому что проверка проходит в методе partyMembersGoInObstacleCourse в майне
    @Override
    public boolean Jump(Object o) {
        return (getMaxJumpHeight() < ((Wall) o).getHeight());
    }

    public String getName() {
        return name;
    }

    public int getMaxRunLength() {
        return maxRunLength;
    }

    public int getMaxJumpHeight() {
        return maxJumpHeight;
    }

}
