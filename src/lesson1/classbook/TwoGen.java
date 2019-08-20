package lesson1.classbook;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Понятие обобщения. Обобщенные классы, методы и интерфейсы.
 * Наследование обобщенных классов. Ограничения при работе с обобщениями.
 * Пример использования обобщений.
 * Обобщенный класс с несколькими параметрами типа.
 *
 */

public class TwoGen<T, V> {
    private T obj1;
    private V obj2;

    public TwoGen(T obj1, V obj2) {
        this .obj1 = obj1;
        this .obj2 = obj2;
    }

    public void showTypes() {
        System.out.println( "Тип T: " + obj1.getClass().getName());
        System.out.println( "Тип V: " + obj2.getClass().getName());
    }

    public T getObj1() {
        return obj1;
    }

    public V getObj2() {
        return obj2;
    }
}
