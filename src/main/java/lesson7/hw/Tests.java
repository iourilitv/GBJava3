package lesson7.hw;

import java.lang.reflect.InvocationTargetException;
//READY
public class Tests {
    Tests() {
    }

    @BeforeSuite
    public void beforeSuite(){
        //какое-то действие
        System.out.println("***The Tests have started!***");
    }
    //TODO Проверка
    /*@BeforeSuite
    public void beforeSuite2(){
        //какое-то действие
        System.out.println("***The Tests have started!***");
    }*/

    @Test(name="NullArrayTest", priority = 10, expected = RuntimeException.class)
    public void nullArrayTestCreateArrayWithElementsBehindSample() {
        int[] initArray = null;
        int sample = 4;
        System.out.print("NullArrayTest: ");
        TestController.assertArrayEquals((int[])null, Task.createArrayWithElementsBehindSample((int[])initArray, sample));
    }

    @Test(name = "EmptyArrayTest", priority = 8, expected = RuntimeException.class)
    public void emptyArrayTestCreateArrayWithElementsBehind() {
        int[] initArray = new int[0];
        int sample = 4;
        System.out.print("EmptyArrayTest: ");
        TestController.assertArrayEquals((int[])null, Task.createArrayWithElementsBehindSample((int[])initArray, sample));
    }

    @Test(name = "NotElementsBehindSampleTest", priority = 3, expected = RuntimeException.class)
    public void notElementsBehindSampleTestCreateArrayWithElementsBehindSample() {
        int[] initArray = new int[]{1, 5, 6161, 4};
        int sample = 4;
        System.out.print("NotElementsBehindSampleTest: ");
        TestController.assertArrayEquals((int[])null, Task.createArrayWithElementsBehindSample((int[])initArray, sample));
    }

    @Test(name = "NotSampleInArrayTest",  expected = RuntimeException.class)
    public void notSampleInArrayTestCreateArrayWithElementsBehindSample() {
        int[] initArray = new int[]{1, 5, 6161};
        int sample = 4;
        System.out.print("NotSampleInArrayTest: ");
        TestController.assertArrayEquals((int[])null, Task.createArrayWithElementsBehindSample((int[])initArray, sample));
    }

    //TODO TEST
    @Test(name = "NotSampleInArrayTest2",  expected = InvocationTargetException.class)
    public void notSampleInArrayTest2CreateArrayWithElementsBehindSample() {
        int[] initArray = new int[]{1, 5, 6161};
        int sample = 4;
        System.out.print("NotSampleInArrayTest2: ");
        TestController.assertArrayEquals((int[])null, Task.createArrayWithElementsBehindSample((int[])initArray, sample));
    }

    @Test(name = "CorrectArrayTest",  priority = 1)
    public void correctArrayTestCreateArrayWithElementsBehindSample() {
        int[] initArray = new int[]{1, 5, 6161, 4, 88, 789, 4, 159, 8963, 489};
        int[] expectedArray = new int[]{159, 8963, 489};
        int sample = 4;
        System.out.print("CorrectArrayTest: ");
        TestController.assertArrayEquals(expectedArray, Task.createArrayWithElementsBehindSample(initArray, sample));
    }

    @AfterSuite
    public void afterSuite(){
        //какое-то действие
        System.out.println("***The Tests have finished!***");
    }
    //TODO Проверка
    /*@AfterSuite
    public void afterSuite2(){
        //какое-то действие
        System.out.println("***The Tests have finished!***");
    }*/
}