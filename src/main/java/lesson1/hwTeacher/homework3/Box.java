package lesson1.hwTeacher.homework3;

import java.util.List;

public class Box<T extends Fruit> implements Comparable<Box<?>> {
    private List<T> values;

    //доступ к элементу коробки
    public T get (int inx){
        return values.get(inx);
    }

    //положить в коробку один элемент
    public void put (T v){
        values.add(v);
    }

    //положить в коробку коллекцию
    public void putAll (Box<T> v){
        values.addAll(v.values);
        v.clear();
    }

    //опустошаем коробку. из которой перекладывали
    public void clear (){
        values.clear();
    }

    //метод расчет веса коробки для любых типов фруктов
    private double getWeight(){
        double weight = 0;
        for (T value: values) {
            weight += value.getWeight();//getWeight() красным пока не добавить extends Fruit в Box<T>
        }
        return weight;
    }

    public boolean compare(Box<?> o) {
        return this.getWeight() == o.getWeight();
    }

    //переопределяем метод от интерфейса Comparable
    //<?> чтобы сравнивать коробки с разными типами, а не только яблоки с яблоками, как с <T>
    @Override
    public int compareTo(Box<?> o) {
        return this.getWeight() == o.getWeight() ? 0 : (int)(this.getWeight() - o.getWeight());
    }



}
