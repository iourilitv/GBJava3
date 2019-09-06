package lesson1.hwTeacher;

import java.util.Arrays;
import java.util.List;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Домашняя работа.
 * @author Anton Batiaev
 * DONE 2. Написать метод, который преобразует массив в ArrayList;
 */
public class Homework2 {
    public static void main(String[] args) {
        Object[] data = new Object[]{8484848, 43, 123, 123};
        Integer[] data2 = new Integer[]{8484848, 43, 123, 123};
        //List<Integer> objects = convertToList(data); //IDEA показывает ошибку, т.к. типы не совпадают
        List<Integer> objects2 = convertToList(data2);

        //возможна ошибка если в массив добавить строку, а вызвать, как Integer
        //и это может проявиться на позднем этапе разработки или запуска приложения
        List<Object> objects = convertToList(data);
        objects.set(0, "text");
        //Integer integer = objects.get(0);//IDEA показывает ошибку, т.к. типы не совпадают

        //что не происходит на этапе разработки в случае контроля типа с помощью Generics
        //objects2.set(0, "text");//IDEA показывает ошибку, т.к. типы не совпадают
        Integer integer = objects2.get(0);

    }

    //используем обобщение, т.к. нам нужен контроль типов
    //принимаем массив типа T и возвращаем коллекцию типа T
    private static <T> List<T> convertToList(T[] data) {
        return Arrays.asList(data);
    }

}
