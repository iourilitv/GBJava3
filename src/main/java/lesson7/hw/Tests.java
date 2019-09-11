package lesson7.hw;

public class Tests {
    public Tests() {
    }

    @BeforeSuite
    public void beforeSuite(){
        //создать коллекцию из методов класса отсортированную по приоритету
        //TestController.
    }
    /*@BeforeSuite
    public void beforeSuite2(){
        //создать коллекцию из методов класса отсортированную по приоритету
        //TestController.
    }*/

    @Test(name="NullArrayTest", priority = 10, expected = RuntimeException.class)
    public void NullArrayTestCreateArrayWithElementsBehindSample() {
        int[] initArray = null;
        int sample = 4;
        TestController.assertArrayEquals((int[])null, Task.createArrayWithElementsBehindSample((int[])initArray, sample));
    }

    @Test(name = "EmptyArrayTest", priority = 8, expected = RuntimeException.class)
    public void EmptyArrayTestCreateArrayWithElementsBehind() {
        int[] initArray = new int[0];
        int sample = 4;
        //Assert.assertArrayEquals((int[])null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test(name = "NotElementsBehindSampleTest", priority = 3, expected = RuntimeException.class)
    public void NotElementsBehindSampleTestCreateArrayWithElementsBehindSample() {
        int[] initArray = new int[]{1, 5, 6161, 4};
        int sample = 4;
        //Assert.assertArrayEquals((int[])null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test(name = "NotSampleInArrayTest",  expected = RuntimeException.class)
    public void NotSampleInArrayTestCreateArrayWithElementsBehindSample() {
        int[] initArray = new int[]{1, 5, 6161};
        int sample = 4;
        //Assert.assertArrayEquals((int[])null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test(name = "CorrectArrayTest",  priority = 1)
    public void CorrectArrayTestCreateArrayWithElementsBehindSample() {
        int[] initArray = new int[]{1, 5, 6161, 4, 88, 789, 4, 159, 8963, 489};
        int[] expectedArray = new int[]{159, 8963, 489};
        int sample = 4;
        System.out.println("CorrectArrayTest");
        TestController.assertArrayEquals(expectedArray, Task.createArrayWithElementsBehindSample(initArray, sample));
    }

    @AfterSuite
    public void afterSuite(){
        //распечатать коллекцию с результатами тестов
        //FIXME
    }
    /*@AfterSuite
    public void afterSuite2(){
        //распечатать коллекцию с результатами тестов
        //FIXME
    }*/
}