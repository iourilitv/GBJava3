package lesson6.hwTeacher;

import java.util.Arrays;

/**
 * Похоже, эта реализация расчитана на узкую задачу.
 */
public class Task1 {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Task1.class);

    public static void main(String[] args) {
        int[] initArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        //final int[] res2 = process(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        final int[] res2 = process(initArray);
        //System.out.println(res2);
        System.out.println(Arrays.toString(res2));
        System.out.println(Arrays.toString(initArray));
    }

    public static int[] process(int[] arrIn) {
        int[] backArr = new int[2];//TODO ? Почему 2?
        if (arrIn.length == 0) {
            log.info("В массиве нет элементов");
            return arrIn;
        }
        for (int i = arrIn.length - 3; i >= 0; i--) {//TODO ? Почему - 3?
            if (arrIn[i] == 4) {
                System.arraycopy(arrIn, (i + 1), backArr, 0, 2);
                return backArr;
            }
        }
        throw new RuntimeException("В массиве нет ни одной четверки, после которой есть два числа");
    }
}
