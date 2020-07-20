package lesson7;

public class Main {

    public static void main(String[] args) {
        int foodCount = 20;
        Cat[] cats = new Cat[5];
        Plate plate = new Plate(foodCount);
        for (int i = 0; i < cats.length; i++) {
            // создается кот с именем cat + счетчик, аппетит от 1 до 25
            cats[i] = new Cat("cat" + i, (int)(1 + (Math.random() * 25)));
            cats[i].eat(plate);
            System.out.println("name: " + cats[i].getName() + "   appetite: " + cats[i].getAppetite() +
                    "   food in plate: " + plate.getFood() + "   satiety: " + cats[i].isSatiety());
        }
    }
}