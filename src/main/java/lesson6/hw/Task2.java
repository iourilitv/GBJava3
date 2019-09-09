package lesson6.hw;

import java.util.Arrays;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 6. Обзор средств разработки.
 * Логирование. Тестирование с использованием JUnit. Класс Assert. Аннотации.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * DONE 2. Написать метод, который проверяет состав массива только из чисел 1 и 4.
 * Если в нем нет хоть одной четверки или единицы, то метод вернет false;
 * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 * [ 1 1 1 4 4 1 4 4 ] -> true
 * [ 1 1 1 1 1 1 ] -> false
 * [ 4 4 4 4 ] -> false
 * [ 1 4 4 1 1 4 3 ] -> false
 */ //READY
public class Task2 {
    public static void main(String[] args) {
        //int[] initArray = {1, 2, 3, 4, 5};
        int[] initArray = {1, 4, 4, 1, 1, 4};
        int[] samples = {1, 4};
        System.out.println(Arrays.toString(initArray) + " --> " + checkArrayWithSamples(initArray, samples));


    }

    public static boolean checkArrayWithSamples(int[] initArray, int[] samples) {
        //если в метод передан пустой или не инициированный массив или последний элемент равен образцу
        if(initArray == null || samples == null || initArray.length == 0 || samples.length == 0){
            //выбрасываем исключение
            throw new RuntimeException(
                    "***The arrays in the arguments are null or empty!***"
            );
        }

        //создаем массив результатов - количество повторений по каждому образцу
        int[] results = new int[samples.length];
        //объявляем временный флаг, чтобы поймать чужого(не совпадающего ни с одним образцом)
        boolean flag = false;
        //листаем входящий массив
        for (int i = 0; i < initArray.length; i++) {
            //сбрасываем временный флаг
            flag = false;
            //листаем массив образцов
            for (int j = 0; j < samples.length; j++) {
                //если элемент совпадает с образцом, инкрементируем значение элемента с индексом образца
                if(initArray[i] == samples[j]){
                    results[j]++;
                    flag = true;
                }
            }
            //если проверяемый элемент не совпал ни с обним образцом(чужой)
            if(!flag){
                //выходим с false
                return false;
            }
        }

        System.out.println("results: " + Arrays.toString(results));

        //проверяем все ли обрацы найдены во входящем массиве
        for (int result: results) {
            flag &= result > 0;
        }
        return flag;
    }

}
