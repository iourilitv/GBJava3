package lesson1.hw;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Fruit apple = new Apple(1.0f);
        Store store = new Store();
        //инициализируем коллекцию коробок
        store.boxes = new ArrayList<>();
        //создаем 10 коробок по 5 для каждого типа
        store.createBoxes(5);

        System.out.println("store.boxes.get(0).getClass().getTypeName(): " + store.boxes.get(0).getClass().getTypeName());
        System.out.println("store.boxes.get(0).getClass().getSimpleName(): " + store.boxes.get(0).getClass().getSimpleName());
        System.out.println("store.boxes.get(0).getClass().getTypeParameters().getClass().getSimpleName(): " + store.boxes.get(0).getClass().getTypeParameters().getClass().getSimpleName());
        System.out.println("apple.getClass().getSimpleName(): " + apple.getClass().getSimpleName());
        System.out.println("store.boxes.get(0).getUnits().get(0).getClass().getSimpleName(): " + store.boxes.get(0).getUnits().get(0).getClass().getSimpleName());

        //Result.
        //store.boxes.get(0).getClass().getTypeName(): lesson1.hw.Box
        //store.boxes.get(0).getClass().getTypeParameters(): [Ljava.lang.reflect.TypeVariable;@1b6d3586
        //store.boxes.get(0).getClass().getSimpleName(): Box
        //store.boxes.get(0).getClass().getTypeParameters().getClass().getSimpleName(): TypeVariable[]
        //apple.getClass().getSimpleName(): Apple
        //store.boxes.get(0).getUnits().get(0).getClass().getSimpleName():
        // Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0

        //TODO
        // Так и не нашел, как у обекта new Box<Apple>("Apple", 10)) в коллекции boxes в классе Store
        // получить тип параметра Apple? Пришлось вводить параметр type в конструкторы Box и Fruit.

        //Ready.
    }
}
