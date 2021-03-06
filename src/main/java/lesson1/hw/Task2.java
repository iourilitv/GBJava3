package lesson1.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * DONE 2. Написать метод, который преобразует массив в ArrayList;
 */
public class Task2<T> {
    private T[] array;

    //TODO Update.Deleted
    //private ArrayList<T> arrayList;
    //TODO Update.Added
    private List<T> arrayList;

    public Task2(T[] array) {
        this.array = array;
    }

    //TODO Update.Deleted
    /*public void convertArrayToArrayList(T[] array){
        //создаем новую коллекцию с вместимостью равной длине входного массива
        arrayList = new ArrayList<>(array.length);

        System.out.println("arrayList.");
        //наполняем коллекцию перебирая элементы массива
        for (int i = 0; i < array.length; i++) {
            arrayList.add(i, array[i]);
            System.out.print(arrayList.get(i).toString() + ". ");
        }
    }*/
    //TODO Update.Added
    public void convertArrayToArrayList(T[] array){
        arrayList = Arrays.asList(array);
    }
}

class MainTask2{
    public static <T> void main(String[] args) {

        Object[] array = {new Object(), new Integer(656), new Float(1.35f), "text"};
        Task2<Object> task2 = new Task2<>(array);

        System.out.println("Task2.");
        System.out.println("Arrays.toString(array): " + Arrays.toString(array));
        task2.convertArrayToArrayList(array);

        //Result.
        //Task2.
        //Arrays.toString(array): [java.lang.Object@1b6d3586, 656, 1.35, text]
        //arrayList.
        //java.lang.Object@1b6d3586. 656. 1.35. text.
    }
}
