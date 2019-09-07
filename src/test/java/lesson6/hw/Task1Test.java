package lesson6.hw;

import org.junit.Assert;
import org.junit.Test;

public class Task1Test {

    @Test(expected = RuntimeException.class)
    public void nullArrayTest(){
        int[] initArray = null;
        int sample = 4;
        Assert.assertArrayEquals(null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test(expected = RuntimeException.class)
    public void emptyArrayTest(){
        int[] initArray = {};
        int sample = 4;
        Assert.assertArrayEquals(null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test(expected = RuntimeException.class)
    public void notElementsBehindSampleTest(){
        int[] initArray = {1, 5, 6161, 4};
        int sample = 4;
        Assert.assertArrayEquals(null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test(expected = RuntimeException.class)
    public void notSampleInArrayTest(){
        int[] initArray = {1, 5, 6161};
        int sample = 4;
        Assert.assertArrayEquals(null, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

    @Test
    public void correctArrayTest(){
        int[] initArray = {1, 5, 6161, 4, 88, 789, 4, 159, 8963, 489};
        int[] expectedArray = {159, 8963, 489};
        int sample = 4;
        Assert.assertArrayEquals(expectedArray, Task1.createArrayWithElementsBehindSample(initArray, sample));
    }

}