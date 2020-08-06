package java2.lesson1.model;

import java2.lesson1.PartyMembers;

public class Cat extends PartyMembers {

    private int maxRunLength;
    private int maxJumpHeight;
    private static int count = 1;

    public Cat(String name) {
        super(name + count++);
        this.maxJumpHeight = (int) (175 + Math.random() * 51);
        this.maxRunLength = (int) (100 + Math.random() * 51);
    }

    public int getMaxRunLength() {
        return maxRunLength;
    }

    public int getMaxJumpHeight() {
        return maxJumpHeight;
    }

}
