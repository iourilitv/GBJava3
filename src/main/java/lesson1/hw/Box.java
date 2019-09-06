package lesson1.hw;

import java.util.ArrayList;
import java.util.List;

public class Box<F extends Fruit> {
    private int capacity;//вместимость коробки
    protected Float weight;
    private String type;
    private List<F> units;//массив элементов коробки(подтипы класса Fruit)

    public Box(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.units = new ArrayList<>(capacity);
        this.weight = 0.0f;
    }

    //метод загрузки в коробку в зависимости от типа товара
    public boolean load(F unit){

        //TODO временно
        //System.out.println("unit.getType():" + unit.getType() + ". box.type: " + type);

        //если тип товара и тип коробки совпадают и коробка не заполнена
        if(unit.getType().equals(type) && !isFull()){
            //добавляем товар в коллекцию
            units.add(unit);
            //пересчитываем вес коробки
            this.setWeight();
            return true;
        }
        return false;
    }

    //метод выгрузки из коробки в зависимости от типа товара
    public boolean unload(F unit){

        //TODO временно
        //System.out.println("unit.getType():" + unit.getType() + ". box.type: " + type);

        //если тип товара и тип коробки совпадают и коробка не пуста
        if(unit.getType().equals(type) && units.size() != 0){
            //добавляем товар в коллекцию
            units.remove(unit);
            //пересчитываем вес коробки
            this.setWeight();
            return true;
        }
        return false;
    }

    //метод взвешивания коробки
    public void setWeight(){
        if (units.size() == 0){
            weight = 0.0f;
        } else{
            weight = units.size() * units.get(0).getWeight();
        }
    }

    //метод сравнения коробок по весу не зависимо от типа товара
    public boolean compareWeight(Box<F> another){
        return Math.abs(this.weight - another.weight) < 0.00001;
    }

    //метод сравнения коробок по типу товара
    public boolean compareType(Box<F> another){
        return this.type.equals(another.type);
    }

    //метод, который позволяет пересыпать фрукты из текущей коробки в другую
    // (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами).
    // Соответственно, в текущей коробке фруктов не остается,
    // а в другую перекидываются объекты, которые были в этой коробке;
    //receiveBox - коробка num - требуемое количество
    public Integer unloadFromBoxToBox(Box<F> receiveBox){
        //вычисляем количество не выгруженного из выдающей коробки:
        // остаточная емкость принимающей коробки минус количество товара в выдающей
        Integer rest = (receiveBox.capacity - receiveBox.units.size()) - this.units.size();
        //если (входит) остаточная емкость принимающей коробки больше, чем количество в выгружаемой коробке
        if(rest >= 0) {
            //невыданный остаток выдающей коробки равен нулю
            rest = 0;
            //добавляем все товары из выдающей коробки в принимающую
            receiveBox.units.addAll(units);
            //очищаем коллекцию выдающей коробки
            units.clear();
        } else {//если места в принимающей коробке не хватает
            //берем модуль, чтобы число не было отрицательным
            rest = Math.abs(rest);
            //удаляем из выдающей коробки количество равное остаточной емкости принимающей коробки
            //и добавляем его в принимающую коробку
            for (int i = 0; i < (units.size() - rest); i++) {
                receiveBox.units.add(units.get(i));
                units.remove(i);
                --i;//возвращаем 0, чтобы не пропустить
            }
        }
        //пересчитываем веса коробок после операции
        this.setWeight();
        receiveBox.setWeight();
        //возвращаем не переложенный остаток в выгружаемой коробке
        return rest;
    }

    public boolean isFull(){
        if(units.size() >= capacity){
            return true;
        }
        return false;
    }

    //getter
    public Float getWeight(){
        return weight;
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

    //метод выводящий в консоль информацию о коробке: типом товара, вес коробки,
    public void boxInfo(){
        System.out.println("Type of units:" + this.type + ". Box weight:" + this.weight +
                ". Box capacity:" + this.capacity + ". Number of units:" + units.size());
    }

    public void printUnits(){
        System.out.println("Box units.");
        for (Object a: units) {
            System.out.println("Fruit type: " + a.getClass().getName());
        }
    }
}
