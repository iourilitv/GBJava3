package lesson1.hw;

import java.util.ArrayList;
import java.util.List;

public class Box<F extends Fruit> {
    private int capacity;//вместимость коробки
    protected Float weight;
    private String type;
    private List<F> units;//массив элементов коробки(подтипы класса Friut)

    public Box(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.units = new ArrayList<>(capacity);
    }

    //метод загрузки в коробку в зависимости от типа товара
    public boolean load(F unit){

        //TODO временно
        //System.out.println("unit.getType():" + unit.getType() + ". box.type: " + type);

        //если тип товара и тип коробки совпадают и коробка не пуста
        if(unit.getType().equals(type) && !isFull()){
            //добавляем товар в коллекцию
            units.add(unit);
            this.setWeight();
            return true;
        }
        return false;
    }

    //метод выгрузки из коробки
    public void unload(){

    }

    //метод взвешивания коробки
    public void setWeight(){
        if (units.size() == 0){
            weight = 0.0f;
        } else{
            weight = units.size() * units.get(0).getWeight();
        }
    }

    //getter
    public Float getWeight(){
        return weight;
    }

    //метод сравнения коробок по весу
    public boolean compare(Box<F> another){
        return true;
    }

    //метод, который позволяет пересыпать фрукты из текущей коробки в другую
    // (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами).
    // Соответственно, в текущей коробке фруктов не остается,
    // а в другую перекидываются объекты, которые были в этой коробке;
    public void removeFromBoxToBox(){

    }

    public boolean isFull(){
        if(units.size() >= capacity){
            return true;
        }
        return false;
    }

    public List<F> getUnits() {
        return units;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public void printUnits(){
        System.out.println("Box units.");
        for (Object a: units) {
            System.out.println("Fruit type: " + a.getClass().getName());
        }
    }
}
