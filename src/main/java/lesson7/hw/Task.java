package lesson7.hw;

import java.util.Arrays;

public class Task {

    public static void main(String[] args) {
        int[] initIntArray = {1, 2, 3, 4, 5};
        int[] array = createArrayWithElementsBehindSample(initIntArray, 4);
        System.out.println(Arrays.toString(array));
    }

    public static int[] createArrayWithElementsBehindSample(int[] initArray, int sample) {
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
                if(i != initArray.length - 1){//TODO Лишнее! Уже проверено на входе
                    //возвращаем массив только с элементами после образца
                    return Arrays.copyOfRange(initArray, i + 1, initArray.length);
                }
            }
        }
        //выбрасываем исключение, если в массиве нет ни одного образца
        throw new RuntimeException("***The array does't have a sample!***");
    }
}
