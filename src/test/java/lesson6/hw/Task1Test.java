package lesson6.hw;

import org.junit.Assert;
import org.junit.Test;

public class Task1Test {

    @Test(expected = RuntimeException.class)
    public void createArrayWithElementsBehindSampleNullArrayTest(){
        int[] initArray = null;
        int sample = 4;
        Assert.assertArrayEquals(null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test(expected = RuntimeException.class)
    public void createArrayWithElementsBehindSampleEmptyArrayTest(){
        int[] initArray = {};
        int sample = 4;
        Assert.assertArrayEquals(null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test(expected = RuntimeException.class)
    public void createArrayWithElementsBehindSampleNotElementsBehindSampleTest(){
        int[] initArray = {1, 5, 6161, 4};
        int sample = 4;
        Assert.assertArrayEquals(null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test(expected = RuntimeException.class)
    public void createArrayWithElementsBehindSampleNotSampleInArrayTest(){
        int[] initArray = {1, 5, 6161};
        int sample = 4;
        Assert.assertArrayEquals(null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test
    public void createArrayWithElementsBehindSampleCorrectArrayTest(){
        int[] initArray = {1, 5, 6161, 4, 88, 789, 4, 159, 8963, 489};
        int[] expectedArray = {159, 8963, 489};
        int sample = 4;
        Assert.assertArrayEquals(expectedArray, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

}