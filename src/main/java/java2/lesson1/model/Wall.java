package java2.lesson1.model;

public class Wall {

    private int height;

    public Wall() {
        this.height = (int) (10 + Math.random() * 241);
    }

    public int getHeight() {
        return height;
    }

}
