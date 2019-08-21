package lesson1.hw;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * 1. Написать метод, который меняет два элемента массива местами
 *    (массив может быть любого ссылочного типа);
 */
public class Task1<T extends Object> {
    private T[] array;

    public Task1(T[] array) {
        this.array = array;
    }


}

class MainTask1{
    public static void main(String[] args) {
        //Task1<Object>[] array = new Task1<Object>[10];

    }
}
