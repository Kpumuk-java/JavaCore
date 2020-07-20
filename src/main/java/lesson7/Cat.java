package lesson7;

public class Cat {

    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public void eat(Plate plate) {
        // это условие здесь правильней, потому что коту важнее сколкьо еды на тарелке,
        // чем тарелке знать какой аппетит у кота
        if (appetite <= plate.getFood()){
            plate.decreaseFood(appetite);
            //тут я не понял толи аппетит должен стать 0, толи аппетит это константа
            // сколько еды нужно коту чтобы насытится
            satiety = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public boolean isSatiety() {
        return satiety;
    }

}
