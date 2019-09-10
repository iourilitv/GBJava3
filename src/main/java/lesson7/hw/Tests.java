package lesson7.hw;

public class Tests {
    public Tests() {
    }

    @Test(expected = RuntimeException.class)
    public void createArrayWithElementsBehindSampleNullArrayTest() {
        int[] initArray = null;
        int sample = 4;
        //Assert.assertArrayEquals((int[])null, Task1.createArrayWithElementsBehindSample((int[])initArray, sample));
    }

    @Test(expected = RuntimeException.class)
    public void createArrayWithElementsBehindSampleEmptyArrayTest() {
        int[] initArray = new int[0];
        int sample = 4;
        //Assert.assertArrayEquals((int[])null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test(expected = RuntimeException.class)
    public void createArrayWithElementsBehindSampleNotElementsBehindSampleTest() {
        int[] initArray = new int[]{1, 5, 6161, 4};
        int sample = 4;
        //Assert.assertArrayEquals((int[])null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test(expected = RuntimeException.class)
    public void createArrayWithElementsBehindSampleNotSampleInArrayTest() {
        int[] initArray = new int[]{1, 5, 6161};
        int sample = 4;
        //Assert.assertArrayEquals((int[])null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test
    public void createArrayWithElementsBehindSampleCorrectArrayTest() {
        int[] initArray = new int[]{1, 5, 6161, 4, 88, 789, 4, 159, 8963, 489};
        int[] expectedArray = new int[]{159, 8963, 489};
        int sample = 4;
        //Assert.assertArrayEquals(expectedArray, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }
}