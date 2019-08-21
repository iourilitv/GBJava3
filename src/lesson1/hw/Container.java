package lesson1.hw;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private int capacity;//вместимость контейнера
    //private Float weight;
    //private List<Fruit> fruits;//массив элементов коробки(подтипы класса Friut)

    public Container(int capacity) {
        this.capacity = capacity;
        //this.fruits = new ArrayList<>(capacity);
    }



    /*//метод загрузки в контейнер разных фруктов
    public void load(Fruit fruit, int number){
        for (int i = 0; i < number; i++) {
            fruits.add(fruit);
        }
    }*/

    /*public List<Fruit> getFruits() {
        return fruits;
    }*/

    /*private U unit;

    public Container(U unit) {
        this.unit = unit;
    }

    public U getUnit(){
        return this.unit = unit;
    }

    public void setUnit(U uUnit){
        this.unit = uUnit;
    }*/
}
