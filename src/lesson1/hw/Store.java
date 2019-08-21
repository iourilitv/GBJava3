package lesson1.hw;

import java.util.ArrayList;
import java.util.List;

public class Store {
    //Apple apple;
    //Orange orange;
    List<Fruit> container;

    //Box<Apple> appleBox;
    //Box<Orange> orangeBox;
    List<Box> boxes;

    public void getOperations(){
        //apple = new Apple(1.0f);
        //orange = new Orange(1.5f);
        container = new ArrayList<>(50);
        //appleBox = new Box<>(10);
        //orangeBox = new Box<>(10);
        boxes = new ArrayList<>();
        //создаем 10 коробок по 5 для каждого типа
        createBoxes(5);
        //printArrayList(boxes);

        //загружаем контейнер разними типами фруктов. num - количество пар
        loadContainer(25);
        System.out.println("Loaded container." + container.size());
        //printArrayList(container);

        unloadContainer(container);
        System.out.println("Loaded container.size(): " + container.size());
        //printArrayList(container);
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

    public void unloadContainer(List<Fruit> container){
        //пролистываем содержимое контейнера
        for (int i = 0; i < container.size(); i++) {

            //TODO временно
            System.out.println("unit(" + i + "): " + container.get(i).getClass().getName());

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

    /*public void unloadContainer(List<Fruit> container){
        //пролистываем содержимое контейнера
        for (int i = 0; i < container.size(); i++) {

            //TODO временно
            System.out.println("unit(" + i + "): " + container.get(i).getClass().getName());

            //если выбранный элемент яблоко
            if(container.get(i).getClass().getName().equals(apple.getClass().getName())){
                //пролистываем коллекцию коробок
                for (Box box : boxes) {

                    //TODO временно
                    //System.out.println("Box: " + box.getClass().getName());
                    //box.printUnits();
                    System.out.println("box.getClass().getTypeParameters(): " + box.getClass().getTypeParameters());
                    System.out.println("appleBox.getClass().getTypeParameters())" + appleBox.getClass().getTypeParameters());


                    //если выбранный элемент коробка с яблоками
                    if(box.getClass().getName().equals(appleBox.getClass().getName()))
                        //если коробка не заполнена полностью
                        if(!box.isFull()){
                            //загружаем яблоко в коробку
                            box.load(container.get(i), 1);
                            break;
                        }
                }
                //удаляем элемент из контейнера
                container.remove(i);
                --i;//возвращаем 0, чтобы не пропустить
                continue;
            }
            //если выбранный элемент апельсин
            if(container.get(i).getClass().getName().equals(orange.getClass().getName())){
                //пролистываем коллекцию коробок
                for (Box box : boxes) {

                    //TODO временно
                    //System.out.println("Box: " + box.getClass().getName());
                    //box.printUnits();
                    System.out.println("box.getClass().getTypeParameters(): " + box.getClass().getTypeParameters());
                    System.out.println("orangeBox.getClass().getTypeParameters())" + orangeBox.getClass().getTypeParameters());


                    //если выбранный элемент коробка с апельсинами
                    if(box.getClass().getTypeParameters().equals(orangeBox.getClass().getTypeParameters()))
                        //если коробка не заполнена полностью
                        if(!box.isFull()){
                            //загружаем апельсин в коробку
                            box.load(container.get(i), 1);
                            break;
                        }
                }
                //удаляем элемент из контейнера
                container.remove(i);
                --i;//возвращаем 0, чтобы не пропустить
            }
        }
        //System.out.println("container.size(): " + container.size());
    }*/

    public void printArrayList(List<?> arrayList){
        for (Object a: arrayList) {
            System.out.println("Fruit type: " + a.getClass().getName());
        }
    }
    //System.out.println("apple.type: " + apple.getType() + ". apple.weight: " + apple.getWeight());
        //System.out.println("orange.type: " + orange.getType() + ". orange.weight: " + orange.getWeight());

}
