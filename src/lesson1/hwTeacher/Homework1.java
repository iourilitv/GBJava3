package lesson1.hwTeacher;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Домашняя работа.
 * @author Anton Batiaev
 * DONE 1. Написать метод, который меняет два элемента массива местами
 *    (массив может быть любого ссылочного типа);
 */
public class Homework1 {
    public static void main(String[] args) {
        Object[] data = new Object[]{"qwe", 43, 123, 123};
        swap(data, 0, 3);
    }

    //<T> для типобезопасности(в данном примере не проверить), чтобы не приводить разные типы друг к другу
    private static <T> void swap(T[] data, int v1, int v2) {
        if(v1 == v2) return;
        if(v1 < 0 || v2 < 0 || v1 > data.length - 1 || v2 > data.length - 1) return;
        T temp = data[v1];
        data[v1] = data[v2];
        data[v2] = temp;
    }

}
