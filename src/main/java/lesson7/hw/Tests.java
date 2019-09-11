package lesson7.hw;

public class Tests {
    public Tests() {
    }

    @Test(name="NullArrayTest",  priority = 6, expected = RuntimeException.class)
    public void NullArrayTestCreateArrayWithElementsBehindSample() {
        int[] initArray = null;
        int sample = 4;
        TestController.assertArrayEquals((int[])null, Task.createArrayWithElementsBehindSample((int[])initArray, sample));
    }

    //@Test(expected = RuntimeException.class)
    public void createArrayWithElementsBehindSampleEmptyArrayTest() {
        int[] initArray = new int[0];
        int sample = 4;
        //Assert.assertArrayEquals((int[])null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    //@Test(expected = RuntimeException.class)
    public void createArrayWithElementsBehindSampleNotElementsBehindSampleTest() {
        int[] initArray = new int[]{1, 5, 6161, 4};
        int sample = 4;
        //Assert.assertArrayEquals((int[])null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    //@Test(expected = RuntimeException.class)
    public void createArrayWithElementsBehindSampleNotSampleInArrayTest() {
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
}