package java3.lesson1;

import java.util.ArrayList;

public class Box <T extends Fruit> {

    private ArrayList<T> box;

    public Box() {
        this.box = new ArrayList<T>();
    }

    /**
     * Добавление elem в box
     * @param elem любой фрукт наследуемый от Fruit
     */
    public void add(T elem) {
        box.add(elem);
    }

    /**
     * Обнуление box
     */
    public void clear () {
        box.clear();
    }

    /**
     *
     * @return колличество фруктов в box
     */
    public int size() {
        return box.size();
    }

    /**
     *
     * @return вернуть коллекцию ArrayList причем обощенную (это нужно для корректного прохождения foreach)
     */
    public ArrayList<T> getBox () {
        return this.box;
    }

    /**
     * Если в коробке хотя бы один фрукт высчитывает какой вес коробки
     * (при условии что все фрукты одного типа, одинакового веса)
     * @return вес коробки
     */
    public float getWeight() {
        if (box != null && box.size() > 0) {
            return box.size() * box.get(0).getWeight();
        }
        return 0;
    }

    /**
     * Сравнивает текущую коробку с varBox
     * @param varBox Любая коробка от Fruit
     * @return true если равны
     */
    public boolean compare (Box<? extends Fruit> varBox) {
        return this.getWeight() == varBox.getWeight();
    }

    /**
     * Собирает в текущую коробку все фрукты из коробки которую передают в параметре,
     * после обнуляет коробку которую передавали в параметре
     * @param box коробка у которой тип совпадает с текущй коробкой
     */
    public void collectInOne (Box<T> box) {
        for (T x: box.getBox()) {
            this.add(x);
        }
        box.clear();
    }

}
