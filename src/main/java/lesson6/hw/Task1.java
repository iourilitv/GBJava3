package lesson6.hw;

import java.util.Arrays;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 6. Обзор средств разработки.
 * Логирование. Тестирование с использованием JUnit. Класс Assert. Аннотации.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * DONE 1. Написать метод, которому в качестве аргумента передается не пустой одномерный
 * целочисленный массив. Метод должен вернуть новый массив, который получен путем
 * вытаскивания из исходного массива элементов, идущих после последней четверки.
 * Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить
 * RuntimeException.
 * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 * Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
 */
public class Task1 {

    public static void main(String[] args) {
        int[] initIntArray = {1, 2, 3, 4, 5};
        int[] array = createArrayWithElementsBehindSample(initIntArray, 4);
        System.out.println(Arrays.toString(array));
    }

    public static int[] createArrayWithElementsBehindSample(int[] initArray, int sample) {

        /* Лишнее. Это делает компилятор и не дает запустить даже тест.
        //если в метод переданы агрументы не приемлемых типов
        if (!initArray.getClass().getTypeName().toLowerCase().startsWith("int") ||
                !((Object)sample).getClass().getSimpleName().toLowerCase().startsWith("int")){
            //выбрасываем исключение
            throw new RuntimeException(
                    "***The array or variable have not acceptable type!***"
            );
        }*/

        //если в метод передан пустой или не инициированный массив или последний элемент равен образцу
        if(initArray == null || initArray.length == 0 || initArray[initArray.length - 1] == sample){
            //выбрасываем исключение
            throw new RuntimeException(
                    "***The array is null/empty or the last element of the array is the sample!***"
            );
        }
        //листаем входной массив в обратном порядке
        for (int i = initArray.length - 1; i >= 0; i--) {
            //если текущий элемент - образец
            if(initArray[i] == sample){
                //проверяем не последний ли он в массиве
                if(i != initArray.length - 1){
                    //возвращаем массив только с элементами после образца
                    return Arrays.copyOfRange(initArray, i + 1, initArray.length);
                }
            }
        }
        //выбрасываем исключение, если в массиве нет ни одного образца
        throw new RuntimeException("***The array does't have a sample!***");
    }
}
