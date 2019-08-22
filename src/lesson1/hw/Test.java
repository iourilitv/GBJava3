package lesson1.hw;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Store store = new Store();
        //инициализируем коллекцию коробок
        store.boxes = new ArrayList<>();
        //создаем 10 коробок по 5 для каждого типа
        store.createBoxes(5);

        System.out.println("store.boxes.get(0).getClass().getTypeName(): " + store.boxes.get(0).getClass().getTypeName());
        //System.out.println("store.boxes.get(0).getClass().getTypeParameters(): " + store.boxes.get(0).getClass().getTypeParameters().toString());

        //Result.
        //store.boxes.get(0).getClass().getTypeName(): lesson1.hw.Box
        //store.boxes.get(0).getClass().getTypeParameters(): [Ljava.lang.reflect.TypeVariable;@1b6d3586

        //TODO
        // Так и не нашел, как у обекта new Box<Apple>("Apple", 10)) в коллекции boxes в классе Store
        // получить тип параметра Apple? Пришлось вводить параметр type в конструкторы Box и Fruit.

        //Ready.
    }
}
