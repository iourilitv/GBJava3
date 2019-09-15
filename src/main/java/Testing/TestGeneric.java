package Testing;

import java.util.ArrayList;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Понятие обобщения. Обобщенные классы, методы и интерфейсы.
 * Наследование обобщенных классов. Ограничения при работе с обобщениями.
 * Пример использования обобщений.
 * В объявлении класса public class TestGeneric<T>, Т представляет собой имя параметра типа, на
 * место которого при создании объекта класса TestGeneric будет подставлен конкретный тип данных.
 */
public class TestGeneric<T> {
    private T obj;

    public TestGeneric(T оbj) {
        this.obj = оbj;
    }

    public TestGeneric() {

    }

    public T getObj() {
        return obj;
    }

    //нет в методичке
    public void setObj(T obj) {
        this.obj = obj;
    }

    public void showType() {
        System.out.println( "Тип T: " + obj.getClass().getName());
    }

    public static void main(String[] args) {
        TestGeneric testGeneric = new TestGeneric<>(new Object());
        ArrayList<TestGeneric> arrayList = new ArrayList<>();
        arrayList.add(testGeneric);
    }

    public void main2() {
        new TestGeneric<>();
    }
}
