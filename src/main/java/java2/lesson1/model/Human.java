package java2.lesson1.model;

import java2.lesson1.PartyMembers;

public class Human extends PartyMembers {

    private int maxRunLength;
    private int maxJumpHeight;
    private static int count = 1;

    public Human(String name) {
        super(name + " " + count++);
        this.maxJumpHeight = (int) (50 + Math.random() * 149);
        this.maxRunLength = (int) (500 + Math.random() * 499);
    }

    @Override
    public int getMaxRunLength() {
        return maxRunLength;
    }

    @Override
    public int getMaxJumpHeight() {
        return maxJumpHeight;
    }
}
