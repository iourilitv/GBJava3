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
        System.out.println("Unloaded container.size(): " + container.size());
        //printArrayList(container);

        //выводим в консоль информацию о коробках(тип товара и общий вес)
        boxesInfo(boxes);

        //сравниваем коробки
        int fromIndex = 0;
        int toIndex = 1;
        System.out.println("\n***Trying to weigh the boxes");
        System.out.println("Weights of the boxes(" + fromIndex + ") and boxes(" + toIndex + ") equal: " +
                boxes.get(fromIndex).compareWeight(boxes.get(toIndex)));
        fromIndex = 0;
        toIndex = 2;
        System.out.println("Weights of the boxes(" + fromIndex + ") and boxes(" + toIndex + ") equal: " +
                boxes.get(fromIndex).compareWeight(boxes.get(toIndex)));

        //перекладываем товар из коробки в коробку
        repackBoxes(5, 6);
        repackBoxes(0, 8);

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

    public void repackBoxes(int fromIndex, int toIndex){
        int rest = 0;

        //TODO временно
        System.out.println("\n***Trying to unload ");
        System.out.println("from boxes(" + fromIndex + ")");
        boxes.get(fromIndex).boxInfo();
        System.out.println("to boxes(" + toIndex + ")");
        boxes.get(toIndex).boxInfo();

        if(boxes.get(fromIndex).compareType(boxes.get(toIndex))){
            if(!boxes.get(toIndex).isFull()){
                rest = boxes.get(fromIndex).unloadFromBoxToBox(boxes.get(toIndex));
                System.out.println("The rest in boxes(" + fromIndex + "):" + rest);
            } else{
                System.out.println("The receive box is full!");
            }
        } else{
            System.out.println("The types of boxes are not matched!");
        }
    }


    //метод выводящий в консоль список коробок с типом товара и весом коробки
    public void boxesInfo(List<Box> arrayList){
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print("Box(" + i + "): ");
            arrayList.get(i).boxInfo();
        }
    }

    //TODO временно
    public void printArrayList(List<?> arrayList){
        for (Object a: arrayList) {
            System.out.println("element type: " + a.getClass().getName());
        }
    }
}
