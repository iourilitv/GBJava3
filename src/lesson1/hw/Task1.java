package lesson1.hw;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * DONE 1. Написать метод, который меняет два элемента массива местами
 *    (массив может быть любого ссылочного типа);
 */
public class Task1<T> {
    private T[] array;

    public Task1(T[] array) {
        this.array = array;
    }

    public void replace(T obj1, T obj2){
        int tempIndex1 = 0;
        int tempIndex2 = 1;
        for (int i = 0; i < array.length; i++) {
            //находим индекс obj1 и сохраняем его
            if(array[i].equals(obj1)){
                tempIndex1 = i;
            }
            //находим индекс obj1 и сохраняем его
            if(array[i].equals(obj2)){
                tempIndex2 = i;
            }
        }
        //меняем объекты местами через временные индексы
        array[tempIndex2] = obj1;
        array[tempIndex1] = obj2;
    }
}

class MainTask1{
    public static void main(String[] args) {

        Object[] array = {new Object(), new Integer(656), new Float(1.35f), "text"};
        Task1<Object> task1 = new Task1<>(array);

        System.out.println("Task1.");
        System.out.println("Before replacing. arrayNum: " + Arrays.toString(array));
        task1.replace(array[0], array[2]);
        System.out.println("After replacing. arrayNum: " + Arrays.toString(array));

        //Result.
        //Task1.
        //Before replacing. arrayNum: [java.lang.Object@1b6d3586, 656, 1.35, text]
        //After replacing. arrayNum: [1.35, 656, java.lang.Object@1b6d3586, text]
    }
}
