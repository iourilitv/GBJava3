package lesson1.hw;

import java.util.ArrayList;
import java.util.List;

public class Store {
    List<Fruit> container;//коллекция фруктов в контейнере
    List<Box> boxes;//коллекция коробок

    public void getOperations(){
        //инициализируем коллекцию - контейнер для фруктов
        container = new ArrayList<>(50);
        ////инициализируем коллекцию коробок
        boxes = new ArrayList<>();
        //создаем 10 коробок по 5 для каждого типа
        createBoxes(5);
        //printArrayList(boxes);

        //загружаем контейнер разными типами фруктов. num - количество пар
        loadContainer(25);

        //TODO временно
        System.out.println("Loaded container." + container.size());
        //printArrayList(container);

        //выгружаем контейнер в коробки
        unloadContainer(container);

        //TODO временно
        System.out.println("Loaded container.size(): " + container.size());
        //printArrayList(container);

        //выводим в консоль информацию о коробках(тип товара и общий вес)
        boxesInfo(boxes);

        //сравниваем коробки
        System.out.println("Weights of the boxes equal: " + boxes.get(0).compare(boxes.get(1)));


    }

    //метод создания коллекции коробок. num - количество пар
    public void createBoxes(int num){
        for (int i = 0; i < num; i++) {
            boxes.add(new Box<Apple>("Apple", 10));
            boxes.add(new Box<Orange>("Orange", 10));
        }
    }

    //метод загрузки контейнера. num - количество пар.
    // для простоты считаем по штукам, а не по весу и чередуя, а не рандомно
    public void loadContainer(int num){
        for (int i = 0; i < num; i++) {
            container.add(new Apple(1.0f));
            container.add(new Orange(1.5f));
        }
    }

    //метод выгрузки товаров в коробки в зависимости от вида товаров
    public void unloadContainer(List<Fruit> container){
        //пролистываем содержимое контейнера
        for (int i = 0; i < container.size(); i++) {

            //TODO временно
            //System.out.println("unit(" + i + "): " + container.get(i).getClass().getName());

            //пролистываем коллекцию коробок//FIXME много перебора
            for (Box box : boxes) {
                //загружаем товар в коробку
                if(box.load(container.get(i))){
                    //удаляем элемент из контейнера
                    container.remove(i);
                    --i;//возвращаем 0, чтобы не пропустить
                    break;//перестаем перебирать коробки
                }
            }
        }
    }

    //метод выводящий в консоль список коробок с типом товара и весом коробки
    public void boxesInfo(List<Box> arrayList){
        for (Box a: arrayList) {
            System.out.println("Box with type of units: " + a.getType() + ". Box weight: " + a.getWeight());
        }
    }

    //TODO временно
    public void printArrayList(List<?> arrayList){
        for (Object a: arrayList) {
            System.out.println("element type: " + a.getClass().getName());
        }
    }
}
